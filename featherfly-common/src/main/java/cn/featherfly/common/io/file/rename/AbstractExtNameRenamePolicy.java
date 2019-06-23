
package cn.featherfly.common.io.file.rename;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.io.file.RenamePolicy;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 是否包含扩展名的抽象重命名策略
 * </p>
 *
 * @author 钟冀
 */
public abstract class AbstractExtNameRenamePolicy implements RenamePolicy{

    /**
     */
    public AbstractExtNameRenamePolicy() {
    }

    /**
     * <p>
     * 为重命名的文件名称附加扩展名
     * </p>
     * @param newFileName 重命名后的名称
     * @param fileName 原始文件名称
     * @return 重命名的文件名称附加扩展名后的完整字符串
      */
    protected String appendExtName(String newFileName, String fileName) {
        if (withExtName) {
            String extName = FileUtils.getFileExtName(fileName);
            if (LangUtils.isNotEmpty(extName)) {
                return newFileName + "." + FileUtils.getFileExtName(fileName);
            }
        }
        return newFileName;
    }

    // ********************************************************************
    //    property
    // ********************************************************************


    /**
     * 是否包含扩展名
     */
    protected boolean withExtName;

    /**
     * 返回是否包含扩展名
     * @return withExtName
     */
    public boolean isWithExtName() {
        return withExtName;
    }

    /**
     * 设置是否包含扩展名
     * @param withExtName withExtName
     */
    public void setWithExtName(boolean withExtName) {
        this.withExtName = withExtName;
    }
}
