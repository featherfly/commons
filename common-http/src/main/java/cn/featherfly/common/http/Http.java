
package cn.featherfly.common.http;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Http.
 *
 * @author zhongj
 */
public final class Http {

    private static final HttpSyncClientImpl CLIENT = new HttpSyncClientImpl();

    //    static {
    //        Runtime.getRuntime().addShutdownHook(new Thread(() -> CLIENT.shutdown()));
    //    }

    private Http() {
    }

    /**
     * Shutdown.
     */
    public static void shutdown() {
        CLIENT.shutdown();
    }

    /**
     * Checks if is deserialize with content type.
     *
     * @return true, if is deserialize with content type
     * @see cn.featherfly.common.http.HttpSyncClientImpl#isDeserializeWithContentType()
     */
    public static boolean isDeserializeWithContentType() {
        return CLIENT.isDeserializeWithContentType();
    }

    /**
     * Sets the deserialize with content type.
     *
     * @param deserializeWithContentType the new deserialize with content type
     * @see cn.featherfly.common.http.HttpSyncClientImpl#setDeserializeWithContentType(boolean)
     */
    public static void setDeserializeWithContentType(boolean deserializeWithContentType) {
        CLIENT.setDeserializeWithContentType(deserializeWithContentType);
    }

    /**
     * Gets the.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String)
     */
    public static String get(String url) {
        return CLIENT.get(url);
    }

    /**
     * Gets the.
     *
     * @param url    the url
     * @param params the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map)
     */
    public static String get(String url, Map<String, Serializable> params) {
        return CLIENT.get(url, params);
    }

    /**
     * Gets the.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static String get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.get(url, params, headers);
    }

    /**
     * Gets the.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> R get(String url, Class<R> responseType) {
        return CLIENT.get(url, responseType);
    }

    /**
     * Gets the.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.get(url, params, responseType);
    }

    /**
     * Gets the.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.get(url, params, headers, responseType);
    }

    /**
     * Head.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String)
     */
    public static String head(String url) {
        return CLIENT.head(url);
    }

    /**
     * Head.
     *
     * @param url    the url
     * @param params the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map)
     */
    public static String head(String url, Map<String, Serializable> params) {
        return CLIENT.head(url, params);
    }

    /**
     * Head.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static String head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.head(url, params, headers);
    }

    /**
     * Head.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> R head(String url, Class<R> responseType) {
        return CLIENT.head(url, responseType);
    }

    /**
     * Head.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.head(url, params, responseType);
    }

    /**
     * Head.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.head(url, params, headers, responseType);
    }

    /**
     * Post.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String)
     */
    public static String post(String url) {
        return CLIENT.post(url);
    }

    /**
     * Post.
     *
     * @param url    the url
     * @param params the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map)
     */
    public static String post(String url, Map<String, Serializable> params) {
        return CLIENT.post(url, params);
    }

    /**
     * Post.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static String post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.post(url, params, headers);
    }

    /**
     * Post.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.post(url, params, responseType);
    }

    /**
     * Post.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.post(url, params, headers, responseType);
    }

    /**
     * Post.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object)
     */
    public static String post(String url, Object requestBody) {
        return CLIENT.post(url, requestBody);
    }

    /**
     * Post.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static String post(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.post(url, requestBody, headers);
    }

    /**
     * Post.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> R post(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.post(url, requestBody, responseType);
    }

    /**
     * Post.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.post(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String)
     */
    public static String put(String url) {
        return CLIENT.put(url);
    }

    /**
     * Put.
     *
     * @param url    the url
     * @param params the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map)
     */
    public static String put(String url, Map<String, Serializable> params) {
        return CLIENT.put(url, params);
    }

    /**
     * Put.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static String put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.put(url, params, headers);
    }

    /**
     * Put.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.put(url, params, responseType);
    }

    /**
     * Put.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R put(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.put(url, params, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object)
     */
    public static String put(String url, Object requestBody) {
        return CLIENT.put(url, requestBody);
    }

    /**
     * Put.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static String put(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.put(url, requestBody, headers);
    }

    /**
     * Put.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> R put(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.put(url, requestBody, responseType);
    }

    /**
     * Put.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.put(url, requestBody, headers, responseType);
    }

    /**
     * Patch.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String)
     */
    public static String patch(String url) {
        return CLIENT.patch(url);
    }

    /**
     * Patch.
     *
     * @param url    the url
     * @param params the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map)
     */
    public static String patch(String url, Map<String, Serializable> params) {
        return CLIENT.patch(url, params);
    }

    /**
     * Patch.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static String patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.patch(url, params, headers);
    }

    /**
     * Patch.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.patch(url, params, responseType);
    }

    /**
     * Patch.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.patch(url, params, headers, responseType);
    }

    /**
     * Patch.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object)
     */
    public static String patch(String url, Object requestBody) {
        return CLIENT.patch(url, requestBody);
    }

    /**
     * Patch.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static String patch(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.patch(url, requestBody, headers);
    }

    /**
     * Patch.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> R patch(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, responseType);
    }

    /**
     * Patch.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String)
     */
    public static String delete(String url) {
        return CLIENT.delete(url);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.util.Map)
     */
    public static String delete(String url, Map<String, String> headers) {
        return CLIENT.delete(url, headers);
    }

    /**
     * Delete.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> R delete(String url, Class<R> responseType) {
        return CLIENT.delete(url, responseType);
    }

    /**
     * Delete.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> R delete(String url, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object)
     */
    public static String delete(String url, Object requestBody) {
        return CLIENT.delete(url, requestBody);
    }

    /**
     * Delete.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static String delete(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.delete(url, requestBody, headers);
    }

    /**
     * Delete.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> R delete(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, responseType);
    }

    /**
     * Delete.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R delete(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, headers, responseType);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.OutputStream)
     */
    public static void download(String url, OutputStream output) {
        CLIENT.download(url, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.File)
     */
    public static void download(String url, File localFile) {
        CLIENT.download(url, localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.OutputStream)
     */
    public static void download(String url, Map<String, Serializable> params, OutputStream output) {
        CLIENT.download(url, params, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.File)
     */
    public static void download(String url, Map<String, Serializable> params, File localFile) {
        CLIENT.download(url, params, localFile);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.File)
     */
    public static void download(String url, Map<String, Serializable> params, Map<String, String> headers,
            File localFile) {
        CLIENT.download(url, params, headers, localFile);
    }

    /**
     * Download.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @param output  the output
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream)
     */
    public static void download(String url, Map<String, Serializable> params, Map<String, String> headers,
            OutputStream output) {
        CLIENT.download(url, params, headers, output);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String)
     */
    public static String request(HttpMethod httpMethod, String url) {
        return CLIENT.request(httpMethod, url);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map)
     */
    public static String request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return CLIENT.request(httpMethod, url, params);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map)
     */
    public static String request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, params, headers);
    }

    /**
     * Request.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Class)
     */
    public static <R> R request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, responseType);
    }

    /**
     * Request.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.lang.Class)
     */
    public static <R> R request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, responseType);
    }

    /**
     * Request.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, headers, responseType);
    }

    /**
     * Request.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object)
     */
    public static String request(HttpMethod httpMethod, String url, Object requestBody) {
        return CLIENT.request(httpMethod, url, requestBody);
    }

    /**
     * Request.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the response string
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map)
     */
    public static String request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, requestBody, headers);
    }

    /**
     * Request.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.lang.Class)
     */
    public static <R> R request(HttpMethod httpMethod, String url, Object requestBody, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, responseType);
    }

    /**
     * Request.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the r
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, headers, responseType);
    }

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the input stream
     * @see cn.featherfly.common.http.HttpSyncClientImpl#stream(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String)
     */
    public static InputStream stream(HttpMethod httpMethod, String url) {
        return CLIENT.stream(httpMethod, url);
    }

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the input stream
     * @see cn.featherfly.common.http.HttpSyncClientImpl#stream(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map)
     */
    public static InputStream stream(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return CLIENT.stream(httpMethod, url, params);
    }

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the input stream
     * @see cn.featherfly.common.http.HttpSyncClientImpl#stream(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map)
     */
    public static InputStream stream(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.stream(httpMethod, url, params, headers);
    }

    /**
     * Stream.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return the input stream
     * @see cn.featherfly.common.http.HttpSyncClientImpl#stream(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object)
     */
    public static InputStream stream(HttpMethod httpMethod, String url, Object requestBody) {
        return CLIENT.stream(httpMethod, url, requestBody);
    }

    /**
     * Stream.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the input stream
     * @see cn.featherfly.common.http.HttpSyncClientImpl#stream(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map)
     */
    public static InputStream stream(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.stream(httpMethod, url, requestBody, headers);
    }
}
