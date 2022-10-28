
/*
 * All rights Reserved, Designed By zhongj
 * @Title: UploadFile.java
 * @Package cn.featherfly.common.http
 * @Description: UploadFile
 * @author: zhongj
 * @date: 2022-10-28 12:34:28
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import java.io.Serializable;

/**
 * UploadFile.
 *
 * @author zhongj
 */
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 2975415516474351577L;

    private String filename;

    private String mediaType;

    private byte[] content;

    /**
     * Instantiates a new upload file.
     *
     * @param filename  the filename
     * @param mediaType the media type
     * @param content   the content
     */
    public UploadFile(String filename, String mediaType, byte[] content) {
        super();
        this.filename = filename;
        this.mediaType = mediaType;
        this.content = content;
    }

    /**
     * get filename value.
     *
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * get mediaType value.
     *
     * @return mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * get content value.
     *
     * @return content
     */
    public byte[] getContent() {
        return content;
    }
}
