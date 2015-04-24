
package cn.featherfly.common.io.file;

import java.io.File;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.WordUtils;

/**
 * <p>
 * 文件包装类
 * </p>
 *
 * @author 钟冀
 */
public class FileWrapper {

    private static final String DEFAULT_ZERO_SIZE = "0KB";

    /**
     * 构造方法
     * @param file file
     */
    public FileWrapper(File file) {
        this.file = file;
    }

    private String storageId;

    private File file;

    private String fileName;

    private String contentType;

    private String extName;

    /**
     * 返回extName
     * @return extName
     */
    public String getExtName() {
        return extName;
    }

    /**
     * @return 返回file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return 返回fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 设置fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.extName = FileUtils.getFileExtName(fileName);
    }

    /**
     * @return 返回contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType 设置contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * <p>
     * 返回文件带单位表示的大小，单位（KB,MB,GB等）.
     * </p>
     * @return 文件带单位表示的大小
     */
    public String getSize() {
        if (this.file == null) {
            return DEFAULT_ZERO_SIZE;
        } else {
            return WordUtils.parseUnit(this.file.length());
        }
    }
    /**
     * <p>
     * 返回文件大小.
     * </p>
     * @return 文件大小
     */
    public long getLength() {
        if (this.file == null) {
            return 0;
        } else {
            return this.file.length();
        }
    }

    /**
     * 返回storageId
     * @return storageId
     */
    public String getStorageId() {
        return storageId;
    }

    /**
     * 设置storageId
     * @param storageId storageId
     */
    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
