
package cn.featherfly.common.db.migration;

import cn.featherfly.common.constant.Chars;

/**
 * <p>
 * Version
 * </p>
 * .
 *
 * @author zhongj
 */
public class Version {

    private int major;

    private int minor;

    private int patch;

    /**
     * Instantiates a new default version.
     *
     * @param major the major
     * @param minor the minor
     * @param patch the patch
     */
    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Creates the.
     *
     * @param version the version
     * @return the version
     */
    public static Version create(String version) {
        String[] vs = version.split("\\.");
        switch (vs.length) {
            case 3:
                return new Version(Integer.parseInt(vs[0]), Integer.parseInt(vs[1]), Integer.parseInt(vs[2]));
            case 2:
                return new Version(Integer.parseInt(vs[0]), Integer.parseInt(vs[1]), 0);
            case 1:
                return new Version(Integer.parseInt(vs[0]), 0, 0);
            default:
                throw new IllegalArgumentException(String.format("version[%s] format is error", version));
        }
    }

    /**
     * 返回major.
     *
     * @return major
     */
    public int getMajor() {
        return major;
    }

    /**
     * 返回minor.
     *
     * @return minor
     */
    public int getMinor() {
        return minor;
    }

    /**
     * 返回patch.
     *
     * @return patch
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return major + Chars.DOT + minor + Chars.DOT + patch;
    }

    /**
     * Gets the next version.
     *
     * @return the next version
     */
    public String getNextVersion() {
        return major + 1 + Chars.DOT + minor + Chars.DOT + patch;
    }

    /**
     * Gets the next minor version.
     *
     * @return the next minor version
     */
    public String getNextMinorVersion() {
        return major + Chars.DOT + (minor + 1) + Chars.DOT + patch;
    }

    /**
     * Gets the next patch version.
     *
     * @return the next patch version
     */
    public String getNextPatchVersion() {
        return major + Chars.DOT + minor + Chars.DOT + (patch + 1);
    }
}
