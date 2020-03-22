
package cn.featherfly.common.io.file.rename;

import cn.featherfly.common.io.file.RenamePolicy;


/**
 * <p>
 * 不重名的策略（原样返回）
 * </p>
 *
 * @author zhongj
 */
public class NoRenamePolicy implements RenamePolicy{

    /**
     */
    public NoRenamePolicy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String rename(String fileName) {
        return fileName;
    }

    // ********************************************************************
    //    property
    // ********************************************************************

}
