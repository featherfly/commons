
package cn.featherfly.common.gentool.db;

/**
 * <p>
 * GeneratorRunner
 * </p>
 *
 * @author zhongj
 */
public class GeneratorRunner {

    public static void main(String[] args) throws Exception {
        String configFile = null;
        if (args.length == 1) {
            configFile = args[0];
        } else if (args.length > 1) {
            configFile = args[0];
        }

        DbGenerateConfig config = new DbGenerateConfig();
        if (configFile != null) {
            config.load(configFile);
        }

        Generator generator = new Generator(config);
        generator.generate();
    }
}
