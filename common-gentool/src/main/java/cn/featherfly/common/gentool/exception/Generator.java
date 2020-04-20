
package cn.featherfly.common.gentool.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import cn.featherfly.common.constant.Charset;
import cn.featherfly.common.gentool.GenerateConfig;
import cn.featherfly.common.gentool.exception.module.GenModule;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * Generator
 * </p>
 *
 * @author zhongj
 */
public class Generator {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    TemplateEngine templateEngine;

    GenerateConfig config;

    /**
     * @param config
     */
    protected Generator(GenerateConfig config) {
        super();
        this.config = config;
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix(config.getTemplateDir());
        templateResolver.setSuffix(config.getTemplateSuffix());
        // Template cache TTL=1h. If not set, entries would be cached until
        // expelled
        //        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public void generate(String filePath) throws Exception {
        generate(read(filePath));
    }

    public void generate(GenModule genModule) throws IOException {
        Context context = new Context();
        context.setVariable("packageName", genModule.getPackageName());
        context.setVariable("author", genModule.getAuthor());
        context.setVariable("code", genModule.getCode());
        context.setVariable("exception", genModule.getException());

        String javaRootPath = config.getJavaSrcDir();
        String resourceRootPath = config.getResourceDir();

        String exceptionJavaFile = UriUtils.linkUri(javaRootPath,
                ClassUtils.packageToDir(genModule.getException().getPackageName()),
                genModule.getException().getName() + ".java");
        logger.debug("generated exceptionJavaFile -> {}", exceptionJavaFile);

        String codeJavaFile = UriUtils.linkUri(javaRootPath,
                ClassUtils.packageToDir(genModule.getCode().getPackageName()), genModule.getCode().getName() + ".java");
        logger.debug("generated exceptionCodeJavaFile -> {}", codeJavaFile);

        org.apache.commons.io.FileUtils.write(new File(exceptionJavaFile), templateEngine.process("exception", context),
                Charset.UTF_8);
        org.apache.commons.io.FileUtils.write(new File(codeJavaFile), templateEngine.process("code", context),
                Charset.UTF_8);

        String propertiesFile = UriUtils.linkUri(resourceRootPath,
                ClassUtils.packageToDir(genModule.getException().getPackageName()), genModule.getException().getName());

        genModule.getCode().getLocales().forEach((locale, properties) -> {
            // context.setVariable("locale", locale);
            // context.setVariable("properties", properties);
            try {
                File file = new File(propertiesFile + "_" + locale + ".properties");
                if (!file.exists()) {
                    org.apache.commons.io.FileUtils.writeByteArrayToFile(file, new byte[] {});
                }
                OutputStream out = new FileOutputStream(file);
                logger.debug("generated properties file -> {}", file.getAbsolutePath());
                properties.store(out, genModule.getCode().getModule() + " " + locale);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private GenModule read(String filePath) throws JsonProcessingException, IOException {
        logger.debug("read genfile file -> {}", filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        File file = new File(filePath);
        if (file.exists()) {
            return mapper.readerFor(GenModule.class).readValue(file);
        } else {
            InputStream input = ClassLoaderUtils.getResourceAsStream(filePath, this.getClass());
            return mapper.readerFor(GenModule.class).readValue(input);
        }
    }
}
