
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Client.java
 * @Package cn.featherfly.common.http
 * @Description: Client
 * @author: zhongj
 * @date: 2022-11-07 17:25:07
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * Client.
 *
 * @author zhongj
 * @param <DS> the generic download response type
 */
public interface HttpDownloadClient<DS> {

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @return the download size result
     */
    default DS download(String url, OutputStream output) {
        return download(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, File localFile) {
        return download(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, OutputStream output) {
        return download(url, params, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, File localFile) {
        return download(url, params, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, Map<String, String> headers, File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            return download(url, params, headers, new FileOutputStream(localFile));
        } catch (FileNotFoundException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @param output  the output
     * @return the download size result
     */
    DS download(String url, Map<String, Serializable> params, Map<String, String> headers, OutputStream output);
}
