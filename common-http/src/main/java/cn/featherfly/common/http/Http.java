
package cn.featherfly.common.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http.
 *
 * @author zhongj
 */
public class Http {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .cache(new okhttp3.Cache(FileUtils.getTempDirectory(), 1024)).connectTimeout(60, TimeUnit.SECONDS).build();

    /**
     * Gets the.
     *
     * @param url the url
     * @return the string
     */
    public static String get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * Gets the.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String get(String url, Map<String, Serializable> params) {
        Request request = new Request.Builder().url(HttpUtils.appendParam(url, params)).get().build();
        try {
            return CLIENT.newCall(request).execute().body().toString();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Post.
     *
     * @param url the url
     * @return the string
     */
    public static String post(String url) {
        return post(url, new HashMap<>());
    }

    /**
     * Post.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String post(String url, Map<String, Serializable> params) {
        Request request = new Request.Builder().url(url).post(HttpUtils.createFormBody(params)).build();
        try {
            return CLIENT.newCall(request).execute().body().toString();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the string
     */
    public static String put(String url) {
        return put(url, new HashMap<>());
    }

    /**
     * Put.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String put(String url, Map<String, Serializable> params) {
        Request request = new Request.Builder().url(url).put(HttpUtils.createFormBody(params)).build();
        try {
            return CLIENT.newCall(request).execute().body().toString();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the string
     */
    public static String delete(String url) {
        Request request = new Request.Builder().url(url).delete().build();
        try {
            return CLIENT.newCall(request).execute().body().toString();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     */
    public static final void download(String url, OutputStream output) {
        download(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     */
    public static final void download(String url, File localFile) {
        download(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     */
    public static final void download(String url, Map<String, Serializable> params, OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParam(url, params)).get().build();
        try {
            Response response = CLIENT.newCall(request).execute();
            output.write(response.body().bytes());
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     */
    public static final void download(String url, Map<String, Serializable> params, File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            download(url, params, new FileOutputStream(localFile));
        } catch (FileNotFoundException e) {
            throw new HttpException(e);
        }
    }
}
