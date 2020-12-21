
package cn.featherfly.common.http;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Http.
 *
 * @author zhongj
 */
public final class Http {

    private static final HttpClient CLIENT = new HttpClient();

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
     * @see cn.featherfly.common.http.HttpClient#isDeserializeWithContentType()
     */
    public static boolean isDeserializeWithContentType() {
        return CLIENT.isDeserializeWithContentType();
    }

    /**
     * Sets the deserialize with content type.
     *
     * @param deserializeWithContentType the new deserialize with content type
     * @see cn.featherfly.common.http.HttpClient#setDeserializeWithContentType(boolean)
     */
    public static void setDeserializeWithContentType(boolean deserializeWithContentType) {
        CLIENT.setDeserializeWithContentType(deserializeWithContentType);
    }

    /**
     * Gets the.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String)
     */
    public static String get(String url) {
        return CLIENT.get(url);
    }

    /**
     * Gets the.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.get(url, params, headers, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param url the url
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> getCompletion(String url) {
        return CLIENT.getCompletion(url);
    }

    /**
     * Gets the completion.
     *
     * @param url    the url
     * @param params the params
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params) {
        return CLIENT.getCompletion(url, params);
    }

    /**
     * Gets the completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.getCompletion(url, params, headers);
    }

    /**
     * Gets the completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> getCompletion(String url, Class<R> responseType) {
        return CLIENT.getCompletion(url, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.getCompletion(url, params, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpClient#getCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.getCompletion(url, params, headers, responseType);
    }

    /**
     * Gets the observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String)
     */
    public static Observable<String> getObservable(String url) {
        return CLIENT.getObservable(url);
    }

    /**
     * Gets the observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> getObservable(String url, Map<String, Serializable> params) {
        return CLIENT.getObservable(url, params);
    }

    /**
     * Gets the observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> getObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.getObservable(url, params, headers);
    }

    /**
     * Gets the observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> getObservable(String url, Class<R> responseType) {
        return CLIENT.getObservable(url, responseType);
    }

    /**
     * Gets the observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> getObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.getObservable(url, params, responseType);
    }

    /**
     * Gets the observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#getObservable(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> getObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.getObservable(url, params, headers, responseType);
    }

    /**
     * Head.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String)
     */
    public static String head(String url) {
        return CLIENT.head(url);
    }

    /**
     * Head.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> R head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.head(url, params, headers, responseType);
    }

    /**
     * Head completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> headCompletion(String url) {
        return CLIENT.headCompletion(url);
    }

    /**
     * Head completion.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params) {
        return CLIENT.headCompletion(url, params);
    }

    /**
     * Head completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.headCompletion(url, params, headers);
    }

    /**
     * Head completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> headCompletion(String url, Class<R> responseType) {
        return CLIENT.headCompletion(url, responseType);
    }

    /**
     * Head completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.headCompletion(url, params, responseType);
    }

    /**
     * Head completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#headCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.headCompletion(url, params, headers, responseType);
    }

    /**
     * Head observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String)
     */
    public static Observable<String> headObservable(String url) {
        return CLIENT.headObservable(url);
    }

    /**
     * Head observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> headObservable(String url, Map<String, Serializable> params) {
        return CLIENT.headObservable(url, params);
    }

    /**
     * Head observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> headObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.headObservable(url, params, headers);
    }

    /**
     * Head observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> headObservable(String url, Class<R> responseType) {
        return CLIENT.headObservable(url, responseType);
    }

    /**
     * Head observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> headObservable(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.headObservable(url, params, responseType);
    }

    /**
     * Head observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#headObservable(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> headObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.headObservable(url, params, headers, responseType);
    }

    /**
     * Post.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String)
     */
    public static String post(String url) {
        return CLIENT.post(url);
    }

    /**
     * Post.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.post(url, requestBody, headers, responseType);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> postCompletion(String url) {
        return CLIENT.postCompletion(url);
    }

    /**
     * Post completion.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params) {
        return CLIENT.postCompletion(url, params);
    }

    /**
     * Post completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.postCompletion(url, params, headers);
    }

    /**
     * Post completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.postCompletion(url, params, responseType);
    }

    /**
     * Post completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.postCompletion(url, params, headers, responseType);
    }

    /**
     * Post completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> postCompletion(String url, Object requestBody) {
        return CLIENT.postCompletion(url, requestBody);
    }

    /**
     * Post completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> postCompletion(String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.postCompletion(url, requestBody, headers);
    }

    /**
     * Post completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.postCompletion(url, requestBody, responseType);
    }

    /**
     * Post completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#postCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.postCompletion(url, requestBody, headers, responseType);
    }

    /**
     * Post observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String)
     */
    public static Observable<String> postObservable(String url) {
        return CLIENT.postObservable(url);
    }

    /**
     * Post observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> postObservable(String url, Map<String, Serializable> params) {
        return CLIENT.postObservable(url, params);
    }

    /**
     * Post observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> postObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.postObservable(url, params, headers);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> postObservable(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.postObservable(url, params, responseType);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> postObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.postObservable(url, params, headers, responseType);
    }

    /**
     * Post observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> postObservable(String url, Object requestBody) {
        return CLIENT.postObservable(url, requestBody);
    }

    /**
     * Post observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> postObservable(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.postObservable(url, requestBody, headers);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> postObservable(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.postObservable(url, requestBody, responseType);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#postObservable(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> postObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.postObservable(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String)
     */
    public static String put(String url) {
        return CLIENT.put(url);
    }

    /**
     * Put.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.put(url, requestBody, headers, responseType);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> putCompletion(String url) {
        return CLIENT.putCompletion(url);
    }

    /**
     * Put completion.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params) {
        return CLIENT.putCompletion(url, params);
    }

    /**
     * Put completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.putCompletion(url, params, headers);
    }

    /**
     * Put completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.putCompletion(url, params, responseType);
    }

    /**
     * Put completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.putCompletion(url, params, headers, responseType);
    }

    /**
     * Put completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> putCompletion(String url, Object requestBody) {
        return CLIENT.putCompletion(url, requestBody);
    }

    /**
     * Put completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> putCompletion(String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.putCompletion(url, requestBody, headers);
    }

    /**
     * Put completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.putCompletion(url, requestBody, responseType);
    }

    /**
     * Put completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#putCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.putCompletion(url, requestBody, headers, responseType);
    }

    /**
     * Put observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String)
     */
    public static Observable<String> putObservable(String url) {
        return CLIENT.putObservable(url);
    }

    /**
     * Put observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> putObservable(String url, Map<String, Serializable> params) {
        return CLIENT.putObservable(url, params);
    }

    /**
     * Put observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> putObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.putObservable(url, params, headers);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> putObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.putObservable(url, params, responseType);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> putObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.putObservable(url, params, headers, responseType);
    }

    /**
     * Put observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> putObservable(String url, Object requestBody) {
        return CLIENT.putObservable(url, requestBody);
    }

    /**
     * Put observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> putObservable(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.putObservable(url, requestBody, headers);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> putObservable(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.putObservable(url, requestBody, responseType);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#putObservable(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> putObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.putObservable(url, requestBody, headers, responseType);
    }

    /**
     * Patch.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String)
     */
    public static String patch(String url) {
        return CLIENT.patch(url);
    }

    /**
     * Patch.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, headers, responseType);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> patchCompletion(String url) {
        return CLIENT.patchCompletion(url);
    }

    /**
     * Patch completion.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params) {
        return CLIENT.patchCompletion(url, params);
    }

    /**
     * Patch completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.patchCompletion(url, params, headers);
    }

    /**
     * Patch completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.patchCompletion(url, params, responseType);
    }

    /**
     * Patch completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patchCompletion(url, params, headers, responseType);
    }

    /**
     * Patch completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> patchCompletion(String url, Object requestBody) {
        return CLIENT.patchCompletion(url, requestBody);
    }

    /**
     * Patch completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> patchCompletion(String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.patchCompletion(url, requestBody, headers);
    }

    /**
     * Patch completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.patchCompletion(url, requestBody, responseType);
    }

    /**
     * Patch completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patchCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patchCompletion(url, requestBody, headers, responseType);
    }

    /**
     * Patch observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String)
     */
    public static Observable<String> patchObservable(String url) {
        return CLIENT.patchObservable(url);
    }

    /**
     * Patch observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> patchObservable(String url, Map<String, Serializable> params) {
        return CLIENT.patchObservable(url, params);
    }

    /**
     * Patch observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> patchObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.patchObservable(url, params, headers);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patchObservable(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.patchObservable(url, params, responseType);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patchObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patchObservable(url, params, headers, responseType);
    }

    /**
     * Patch observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> patchObservable(String url, Object requestBody) {
        return CLIENT.patchObservable(url, requestBody);
    }

    /**
     * Patch observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> patchObservable(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.patchObservable(url, requestBody, headers);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> patchObservable(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.patchObservable(url, requestBody, responseType);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patchObservable(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patchObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.patchObservable(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String)
     */
    public static String delete(String url) {
        return CLIENT.delete(url);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @return the string
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> R delete(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, headers, responseType);
    }

    /**
     * Delete completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String)
     */
    public static HttpRequestCompletion<String> deleteCompletion(String url) {
        return CLIENT.deleteCompletion(url);
    }

    /**
     * Delete completion.
     *
     * @param url     the url
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> deleteCompletion(String url, Map<String, String> headers) {
        return CLIENT.deleteCompletion(url, headers);
    }

    /**
     * Delete completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> deleteCompletion(String url, Class<R> responseType) {
        return CLIENT.deleteCompletion(url, responseType);
    }

    /**
     * Delete completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> deleteCompletion(String url, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.deleteCompletion(url, headers, responseType);
    }

    /**
     * Delete completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody) {
        return CLIENT.deleteCompletion(url, requestBody);
    }

    /**
     * Delete completion.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.deleteCompletion(url, requestBody, headers);
    }

    /**
     * Delete completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.deleteCompletion(url, requestBody, responseType);
    }

    /**
     * Delete completion.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#deleteCompletion(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.deleteCompletion(url, requestBody, headers, responseType);
    }

    /**
     * Delete observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String)
     */
    public static Observable<String> deleteObservable(String url) {
        return CLIENT.deleteObservable(url);
    }

    /**
     * Delete observable.
     *
     * @param url     the url
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> deleteObservable(String url, Map<String, String> headers) {
        return CLIENT.deleteObservable(url, headers);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> deleteObservable(String url, Class<R> responseType) {
        return CLIENT.deleteObservable(url, responseType);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> deleteObservable(String url, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.deleteObservable(url, headers, responseType);
    }

    /**
     * Delete observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> deleteObservable(String url, Object requestBody) {
        return CLIENT.deleteObservable(url, requestBody);
    }

    /**
     * Delete observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> deleteObservable(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.deleteObservable(url, requestBody, headers);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> deleteObservable(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.deleteObservable(url, requestBody, responseType);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#deleteObservable(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> deleteObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.deleteObservable(url, requestBody, headers, responseType);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
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
     * @see cn.featherfly.common.http.HttpClient#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream)
     */
    public static void download(String url, Map<String, Serializable> params, Map<String, String> headers,
            OutputStream output) {
        CLIENT.download(url, params, headers, output);
    }

    /**
     * Download completion.
     *
     * @param url    the url
     * @param output the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.io.OutputStream)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, OutputStream output) {
        return CLIENT.downloadCompletion(url, output);
    }

    /**
     * Download completion.
     *
     * @param url       the url
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.io.File)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, File localFile) {
        return CLIENT.downloadCompletion(url, localFile);
    }

    /**
     * Download completion.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.util.Map, java.io.OutputStream)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            OutputStream output) {
        return CLIENT.downloadCompletion(url, params, output);
    }

    /**
     * Download completion.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.util.Map, java.io.File)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            File localFile) {
        return CLIENT.downloadCompletion(url, params, localFile);
    }

    /**
     * Download completion.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.File)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, File localFile) {
        return CLIENT.downloadCompletion(url, params, headers, localFile);
    }

    /**
     * Download completion.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @param output  the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#downloadCompletion(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream)
     */
    public static HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, OutputStream output) {
        return CLIENT.downloadCompletion(url, params, headers, output);
    }

}
