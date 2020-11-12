
package cn.featherfly.common.mail;

import java.io.File;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * MailAttach
 * </p>
 *
 * @author 钟冀
 */
public class MailAttach {

    private String fileName;

    private File file;

    /**
     */
    public MailAttach(File file) {
        init(file, null);
    }

    /**
     */
    public MailAttach(File file, String fileName) {
        init(file, fileName);
    }

    private void init(File file, String fileName) {
        AssertIllegalArgument.isNotNull(file, "String fileName");
        AssertIllegalArgument.isExists(file, "File file");
        this.file = file;
        if (Lang.isEmpty(fileName)) {
            this.fileName = file.getName();
        } else {
            this.fileName = fileName;
        }
    }

    /**
     * 返回fileName
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 返回file
     *
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return file.getAbsolutePath() + " : " + fileName;
    }
}
