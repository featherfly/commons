
package cn.featherfly.common.app;

/**
 * Version.
 *
 * @author zhongj
 */
public interface Version {

    /**
     * Gets the version.
     *
     * @return the version
     */
    long version();

    /**
     * Gets the version name.
     *
     * @return the version name
     */
    String versionName();
}
