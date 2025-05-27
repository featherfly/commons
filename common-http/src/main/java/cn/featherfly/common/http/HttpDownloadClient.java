
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
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;

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
     * @param url the url
     * @param output the output
     * @return the download size result
     */
    default DS download(String url, OutputStream output) {
        return download(url, Collections.emptyMap(), output);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param output the output
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    default DS download(String url, OutputStream output, BiConsumer<Long, Long> progress) {
        return download(url, Collections.emptyMap(), output, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, File localFile) {
        return download(url, Collections.emptyMap(), localFile);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param localFile the local file
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    default DS download(String url, File localFile, BiConsumer<Long, Long> progress) {
        return download(url, Collections.emptyMap(), localFile, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, OutputStream output) {
        return download(url, params, Collections.emptyMap(), output);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, OutputStream output,
        BiConsumer<Long, Long> progress) {
        return download(url, params, Collections.emptyMap(), output, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, File localFile) {
        return download(url, params, Collections.emptyMap(), localFile);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, File localFile,
        BiConsumer<Long, Long> progress) {
        return download(url, params, Collections.emptyMap(), localFile, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, Map<String, String> headers, File localFile) {
        return download(url, params, headers, localFile, (r, t) -> {
        });
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, Map<String, String> headers, File localFile,
        BiConsumer<Long, Long> progress) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            return download(url, params, headers, new FileOutputStream(localFile), progress);
        } catch (FileNotFoundException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @return the download size result
     */
    default DS download(String url, Map<String, Serializable> params, Map<String, String> headers,
        OutputStream output) {
        return download(url, params, headers, output, (r, t) -> {
        });
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @param progress the progress
     *        argu0 readed bytes
     *        argu1 length
     * @return the download size result
     */
    DS download(String url, Map<String, Serializable> params, Map<String, String> headers, OutputStream output,
        BiConsumer<Long, Long> progress);
}
