
package cn.featherfly.common.gentool.db;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.gentool.GenerateConfig;
import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * DbGenerateConfig
 * </p>
 *
 * @author zhongj
 */
public class DbGenerateConfig extends GenerateConfig {

    public String url;

    public String driverClassName;

    public String username;

    public String password;

    public boolean generateDbModel = true;

    public boolean generateEntity = true;

    public String author;

    // module
    public List<DbModule> modules = new ArrayList<>();

    /**
     */
    public DbGenerateConfig() {
        setTemplateSuffix(".ftl");
        setTemplateDir(ClassUtils.packageToDir(this.getClass()));
    }

}
