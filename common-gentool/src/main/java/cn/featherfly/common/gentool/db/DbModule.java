
package cn.featherfly.common.gentool.db;

import cn.featherfly.common.gentool.Module;

/**
 * DbModule.
 *
 * @author zhongj
 */
public class DbModule extends Module {

    private boolean generateDbModel = false;

    private boolean generateEntity = false;

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

}
