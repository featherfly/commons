
package cn.featherfly.common.gentool;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * GenerateConfig
 * </p>
 *
 * @author zhongj
 */
public class GenerateConfig {

    protected static final Logger logger = LoggerFactory.getLogger(GenerateConfig.class);

    private Charset charset = StandardCharsets.UTF_8;

    private String javaSrcDir = "src/main/java";

    private String resourceDir = "src/main/resources";

    private String templateDir = "cn/featherfly/common/gentool/exception/template/";

    private String templateSuffix = ".template";

    /**
     * 返回charset
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 设置charset
     *
     * @param charset charset
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /**
     * 返回javaSrcDir
     *
     * @return javaSrcDir
     */
    public String getJavaSrcDir() {
        return javaSrcDir;
    }

    /**
     * 设置javaSrcDir
     *
     * @param javaSrcDir javaSrcDir
     */
    public void setJavaSrcDir(String javaSrcDir) {
        this.javaSrcDir = javaSrcDir;
    }

    /**
     * 返回resourceDir
     *
     * @return resourceDir
     */
    public String getResourceDir() {
        return resourceDir;
    }

    /**
     * 设置resourceDir
     *
     * @param resourceDir resourceDir
     */
    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    /**
     * 返回templateDir
     *
     * @return templateDir
     */
    public String getTemplateDir() {
        return templateDir;
    }

    /**
     * 设置templateDir
     *
     * @param templateDir templateDir
     */
    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    /**
     * 返回templateSuffix
     *
     * @return templateSuffix
     */
    public String getTemplateSuffix() {
        return templateSuffix;
    }

    /**
     * 设置templateSuffix
     *
     * @param templateSuffix templateSuffix
     */
    public void setTemplateSuffix(String templateSuffix) {
        this.templateSuffix = templateSuffix;
    }

    public void load(String filePath) throws Exception {
        if (LangUtils.isEmpty(filePath)) {
            return;
        }
        logger.debug("read config file -> {}", filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        File file = new File(filePath);
        GenerateConfig newConfig;
        if (file.exists()) {
            newConfig = mapper.readerFor(this.getClass()).readValue(file);
        } else {
            InputStream input = ClassLoaderUtils.getResourceAsStream(filePath, GenerateConfig.class);
            newConfig = mapper.readerFor(this.getClass()).readValue(input);
        }
        BeanUtils.copyProperties(this, newConfig);
    }
    //
    //    public static GenerateConfig create(String filePath) throws Exception {
    //        if (LangUtils.isEmpty(filePath)) {
    //            filePath = "gentool/config.yaml";
    //        }
    //        logger.debug("read config file -> {}", filePath);
    //        YAMLFactory yamlFactory = new YAMLFactory();
    //        ObjectMapper mapper = new ObjectMapper(yamlFactory);
    //        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    //        File file = new File(filePath);
    //        if (file.exists()) {
    //            return mapper.readerFor(GenerateConfig.class).readValue(file);
    //        } else {
    //            InputStream input = ClassLoaderUtils.getResourceAsStream(filePath, GenerateConfig.class);
    //            return mapper.readerFor(GenerateConfig.class).readValue(input);
    //        }
    //    }
}
