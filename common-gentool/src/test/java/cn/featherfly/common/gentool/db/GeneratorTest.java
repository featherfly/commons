
package cn.featherfly.common.gentool.db;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * <p>
 * GeneratorTest
 * </p>
 *
 * @author zhongj
 */
public class GeneratorTest {

    @Test
    public void g0() throws Exception {
        DbGenerateConfig config = new DbGenerateConfig();
        config.setJavaSrcDir("src/test/java");
        config.setResourceDir("src/test/resources");
        config.driverClassName = "com.mysql.jdbc.Driver";
        config.url = "jdbc:mysql://127.0.0.1:3306/db_test?useUnicode=true&characterEncoding=UTF-8";
        config.username = "root";
        config.password = "123456";
        DbModule m = new DbModule();
        m.author = "zhongj";
        m.packageName = "gencode.db";
        config.modules.add(m);

        Generator generator = new Generator(config);
        generator.generate();
    }

    @Test
    public void g1() throws Exception {
        GeneratorRunner.main(new String[] { "db/gen/db_gen.yaml" });
    }

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        DbGenerateConfig config = new DbGenerateConfig();
        config.setJavaSrcDir("src/test/java");
        config.setResourceDir("src/test/resources");
        config.driverClassName = "com.mysql.jdbc.Driver";
        config.url = "jdbc:mysql://127.0.0.1:3306/db_test?useUnicode=true&characterEncoding=UTF-8";
        config.username = "root";
        config.password = "123456";
        DbModule m = new DbModule();
        m.author = "zhongj";
        m.packageName = "gencode.db";
        config.modules.add(m);

        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.writerFor(DbGenerateConfig.class).writeValue(System.out, config);
    }
}
