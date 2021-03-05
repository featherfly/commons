
package cn.featherfly.common.http;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * Http.
 *
 * @author zhongj
 */
public final class HttpRxjava {

    private static final HttpRxjavaClient CLIENT = new HttpRxjavaClient();

    //    static {
    //        Runtime.getRuntime().addShutdownHook(new Thread(() -> CLIENT.shutdown()));
    //    }

    private HttpRxjava() {
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
     * Gets the observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String)
     */
    public static Observable<String> get(String url) {
        return CLIENT.get(url);
    }

    /**
     * Gets the observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> get(String url, Map<String, Serializable> params) {
        return CLIENT.get(url, params);
    }

    /**
     * Gets the observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.get(url, params, headers);
    }

    /**
     * Gets the observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> get(String url, Class<R> responseType) {
        return CLIENT.get(url, responseType);
    }

    /**
     * Gets the observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.get(url, params, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#get(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.get(url, params, headers, responseType);
    }

    /**
     * Head observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String)
     */
    public static Observable<String> head(String url) {
        return CLIENT.head(url);
    }

    /**
     * Head observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> head(String url, Map<String, Serializable> params) {
        return CLIENT.head(url, params);
    }

    /**
     * Head observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.head(url, params, headers);
    }

    /**
     * Head observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> head(String url, Class<R> responseType) {
        return CLIENT.head(url, responseType);
    }

    /**
     * Head observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.head(url, params, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#head(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.head(url, params, headers, responseType);
    }

    /**
     * Post observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String)
     */
    public static Observable<String> post(String url) {
        return CLIENT.post(url);
    }

    /**
     * Post observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> post(String url, Map<String, Serializable> params) {
        return CLIENT.post(url, params);
    }

    /**
     * Post observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.post(url, params, headers);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.post(url, params, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.post(url, params, headers, responseType);
    }

    /**
     * Post observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> post(String url, Object requestBody) {
        return CLIENT.post(url, requestBody);
    }

    /**
     * Post observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> post(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.post(url, requestBody, headers);
    }

    /**
     * Post observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> post(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.post(url, requestBody, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#post(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> post(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.post(url, requestBody, headers, responseType);
    }

    /**
     * Put observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String)
     */
    public static Observable<String> put(String url) {
        return CLIENT.put(url);
    }

    /**
     * Put observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> put(String url, Map<String, Serializable> params) {
        return CLIENT.put(url, params);
    }

    /**
     * Put observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.put(url, params, headers);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.put(url, params, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.put(url, params, headers, responseType);
    }

    /**
     * Put observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> put(String url, Object requestBody) {
        return CLIENT.put(url, requestBody);
    }

    /**
     * Put observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> put(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.put(url, requestBody, headers);
    }

    /**
     * Put observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> put(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.put(url, requestBody, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#put(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> put(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.put(url, requestBody, headers, responseType);
    }

    /**
     * Patch observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String)
     */
    public static Observable<String> patch(String url) {
        return CLIENT.patch(url);
    }

    /**
     * Patch observable.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> patch(String url, Map<String, Serializable> params) {
        return CLIENT.patch(url, params);
    }

    /**
     * Patch observable.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static Observable<String> patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.patch(url, params, headers);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.patch(url, params, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.patch(url, params, headers, responseType);
    }

    /**
     * Patch observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> patch(String url, Object requestBody) {
        return CLIENT.patch(url, requestBody);
    }

    /**
     * Patch observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.patch(url, requestBody, headers);
    }

    /**
     * Patch observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> patch(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#patch(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> patch(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.patch(url, requestBody, headers, responseType);
    }

    /**
     * Delete observable.
     *
     * @param url the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String)
     */
    public static Observable<String> delete(String url) {
        return CLIENT.delete(url);
    }

    /**
     * Delete observable.
     *
     * @param url     the url
     * @param headers the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.util.Map)
     */
    public static Observable<String> delete(String url, Map<String, String> headers) {
        return CLIENT.delete(url, headers);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> Observable<R> delete(String url, Class<R> responseType) {
        return CLIENT.delete(url, responseType);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, headers, responseType);
    }

    /**
     * Delete observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Object)
     */
    public static Observable<String> delete(String url, Object requestBody) {
        return CLIENT.delete(url, requestBody);
    }

    /**
     * Delete observable.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static Observable<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.delete(url, requestBody, headers);
    }

    /**
     * Delete observable.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> delete(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, responseType);
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
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> delete(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return CLIENT.delete(url, requestBody, headers, responseType);
    }

    /**
     * Request observable.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String)
     */
    public static Observable<String> request(HttpMethod httpMethod, String url) {
        return CLIENT.request(httpMethod, url);
    }

    /**
     * Request observable.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map)
     */
    public static Observable<String> request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return CLIENT.request(httpMethod, url, params);
    }

    /**
     * Request observable.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map)
     */
    public static Observable<String> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, params, headers);
    }

    /**
     * Request observable.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Class)
     */
    public static <R> Observable<R> request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, responseType);
    }

    /**
     * Request observable.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, responseType);
    }

    /**
     * Request observable.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, headers, responseType);
    }

    /**
     * Request observable.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object)
     */
    public static Observable<String> request(HttpMethod httpMethod, String url, Object requestBody) {
        return CLIENT.request(httpMethod, url, requestBody);
    }

    /**
     * Request observable.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map)
     */
    public static Observable<String> request(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, requestBody, headers);
    }

    /**
     * Request observable.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.lang.Class)
     */
    public static <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody,
            Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, responseType);
    }

    /**
     * Request observable.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     * @see cn.featherfly.common.http.HttpClient#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, headers, responseType);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.io.OutputStream)
     */
    public static Observable<Integer> download(String url, OutputStream output) {
        return CLIENT.download(url, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.io.File)
     */
    public static Observable<Integer> download(String url, File localFile) {
        return CLIENT.download(url, localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.util.Map, java.io.OutputStream)
     */
    public static Observable<Integer> download(String url, Map<String, Serializable> params, OutputStream output) {
        return CLIENT.download(url, params, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.util.Map, java.io.File)
     */
    public static Observable<Integer> download(String url, Map<String, Serializable> params, File localFile) {
        return CLIENT.download(url, params, localFile);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.File)
     */
    public static Observable<Integer> download(String url, Map<String, Serializable> params,
            Map<String, String> headers, File localFile) {
        return CLIENT.download(url, params, headers, localFile);
    }

    /**
     * Download.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @param output  the output
     * @return the observable
     * @see cn.featherfly.common.http.HttpRxjavaClient#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream)
     */
    public static Observable<Integer> download(String url, Map<String, Serializable> params,
            Map<String, String> headers, OutputStream output) {
        return CLIENT.download(url, params, headers, output);
    }

}
