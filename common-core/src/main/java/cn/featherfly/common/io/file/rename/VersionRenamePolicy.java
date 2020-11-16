
package cn.featherfly.common.io.file.rename;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.io.file.RenamePolicy;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * VersionDateRenamePolicy
 * </p>
 *
 * @author zhongj
 */
public class VersionRenamePolicy implements RenamePolicy {

    private String version;

    /**
     * Instantiates a new version date rename policy.
     *
     * @param version the version
     */
    public VersionRenamePolicy(String version) {
        super();
        AssertIllegalArgument.isNotEmpty(version, "version");
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String rename(String fileName) {
        String extName = FileUtils.getFileExtName(fileName);
        String name = StringUtils.substringBeforeLast(extName, ".");
        if (Lang.isNotEmpty(extName)) {
            return name + "-" + version + "." + extName;
        } else {
            return name + "-" + version;
        }
    }

    /**
     * 返回version
     *
     * @return version
     */
    public String getVersion() {
        return version;
    }

}
