
package cn.featherfly.common.gentool.exception;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.gentool.GenerateConfig;

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
        List<String> yamlFiles = new ArrayList<>();
        if (args.length == 0) {
            throw new IllegalArgumentException("args error args.length must > 0");
        }
        if (args.length == 1) {
            yamlFiles.add(args[0]);
        } else if (args.length > 1) {
            configFile = args[0];
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    yamlFiles.add(args[i]);
                }
            }
        }

        GenerateConfig config = new GenerateConfig();
        if (configFile != null) {
            config.load(configFile);
            //            config = GenerateConfig.create(configFile);
        } else {
            config.setTemplateSuffix(".template");
            config.setTemplateDir("cn/featherfly/common/gentool/exception/template/");
        }

        Generator generator = new Generator(config);
        yamlFiles.forEach(yamlFile -> {
            try {
                generator.generate(yamlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
