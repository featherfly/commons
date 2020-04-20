
package cn.featherfly.common.gentool.exception;

import org.testng.annotations.Test;

import cn.featherfly.common.gentool.GenerateConfig;

/**
 * <p>
 * GeneratorTest
 * </p>
 * 
 * @author zhongj
 */
public class GeneratorTest {
    
    @Test
    public void g1() throws Exception {
        GeneratorRunner.main(new String[] {"genfile/hello.yaml"});
    }
    
    @Test
    public void g2() throws Exception {
        GeneratorRunner.main(new String[] {"gentool/config2.yaml", "genfile/practice.yaml"});
    }
    
    @Test
    public void g3() throws Exception {
        GeneratorRunner.main(new String[] {"gentool/config2.yaml", "genfile/practice.yaml", "genfile/hello.yaml"});
    }
    
    public static void main(String[] args) throws Exception {
        GenerateConfig config = new GenerateConfig();
        config.setJavaSrcDir("src/test/java");
        config.setResourceDir("src/test/resources");
//        
        Generator generator = new Generator(config);
        generator.generate("genfile/hello.yaml");
        generator.generate("genfile/practice.yaml");
    }
}
