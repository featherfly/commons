
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-24 16:39:24
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * ReloadableClassloader.
 *
 * @author zhongj
 */
public abstract class ReloadableClassloader {

    /** The class loader. */
    protected ClassLoader classLoader;

    /**
     * prepare classLoader.
     *
     * @param classLoader the class loader
     * @return the class loader
     */
    protected ClassLoader prepare(ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        if (this.classLoader == null) {
            // 第一次加载
            this.classLoader = classLoader;
        }
        if (this.classLoader != classLoader) {
            // 表示使用的classLoader没了，使用新的classLoader重新加载，一般出现在热部署时，如springboot-dev-tool的RestartClassLoader
            doClassLoaderReload();
            this.classLoader = classLoader;
        }
        return this.classLoader;
    }

    /**
     * do when classLoader is reload
     */
    protected abstract void doClassLoaderReload();

}
