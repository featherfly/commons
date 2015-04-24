package cn.featherfly.common.storage.file;

import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * 日期格式目录存储
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class FileProjectModuleDateDirStorage extends FileDateLocalDirStorage {

    /**
     */
    public FileProjectModuleDateDirStorage() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRelativeDir() {
        String relative = super.getRelativeDir();
        if (relative == null) {
            relative = "";
        }
        if (project == null) {
            project = "";
        }
        if (module == null) {
            module = "";
        }
        return UriUtils.linkUri(project, relative, module);
    }

    // ********************************************************************
    //
    // ********************************************************************

    private String project;

    private String module;

    /**
     * 返回project
     * 
     * @return project
     */
    public String getProject() {
        return project;
    }

    /**
     * 设置project
     * 
     * @param project
     *            project
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 返回module
     * 
     * @return module
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置module
     * 
     * @param module
     *            module
     */
    public void setModule(String module) {
        this.module = module;
    }
}
