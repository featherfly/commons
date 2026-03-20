
package cn.featherfly.common.gentool.db;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.gentool.GenerateConfig;
import cn.featherfly.common.lang.ClassUtils;

/**
 * DbGenerateConfig.
 *
 * @author zhongj
 */
public class DbGenerateConfig extends GenerateConfig {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

    private boolean generateDbModel = true;

    private boolean generateEntity = true;

    private String author;

    // module
    private List<DbModule> modules = new ArrayList<>();

    /**
     * Instantiates a new db generate config.
     */
    public DbGenerateConfig() {
        setTemplateSuffix(".ftl");
        setTemplateDir(ClassUtils.packageToDir(this.getClass()));
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the driver class name.
     *
     * @return the driver class name
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * Sets the driver class name.
     *
     * @param driverClassName the new driver class name
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if is generate db model.
     *
     * @return true, if is generate db model
     */
    public boolean isGenerateDbModel() {
        return generateDbModel;
    }

    /**
     * Sets the generate db model.
     *
     * @param generateDbModel the new generate db model
     */
    public void setGenerateDbModel(boolean generateDbModel) {
        this.generateDbModel = generateDbModel;
    }

    /**
     * Checks if is generate entity.
     *
     * @return true, if is generate entity
     */
    public boolean isGenerateEntity() {
        return generateEntity;
    }

    /**
     * Sets the generate entity.
     *
     * @param generateEntity the new generate entity
     */
    public void setGenerateEntity(boolean generateEntity) {
        this.generateEntity = generateEntity;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the modules.
     *
     * @return the modules
     */
    public List<DbModule> getModules() {
        return modules;
    }

    /**
     * Sets the modules.
     *
     * @param modules the new modules
     */
    public void setModules(List<DbModule> modules) {
        this.modules = modules;
    }

}
