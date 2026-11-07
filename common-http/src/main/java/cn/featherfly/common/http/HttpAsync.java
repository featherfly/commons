
package cn.featherfly.common.http;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import cn.featherfly.common.http.request.BasicMethodHttpRequestBuilder;
import cn.featherfly.common.http.request.FormMethodHttpRequestBuilder;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.FormMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpRequestAware;

/**
 * Http.
 *
 * @author zhongj
 */
public final class HttpAsync {

    private static final HttpAsyncClientImpl CLIENT = new HttpAsyncClientImpl();

    //    static {
    //        Runtime.getRuntime().addShutdownHook(new Thread(() -> CLIENT.shutdown()));
    //    }

    /**
     * Instantiates a new http async.
     */
    private HttpAsync() {
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
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#get(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> get(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.get(requestBuilder);
    }

    /**
     * Gets the.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#get(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> get(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.get(requestBuilder, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param url the url
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String)
     */
    public static HttpRequestCompletion<String> get(String url) {
        return CLIENT.get(url);
    }

    /**
     * Gets the completion.
     *
     * @param url the url
     * @param params the params
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> get(String url, Map<String, Serializable> params) {
        return CLIENT.get(url, params);
    }

    /**
     * Gets the completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> get(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.get(url, params, headers);
    }

    /**
     * Gets the completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> get(String url, Class<R> responseType) {
        return CLIENT.get(url, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> get(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return CLIENT.get(url, params, responseType);
    }

    /**
     * Gets the completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#get(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> get(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.get(url, params, headers, responseType);
    }

    /**
     * Head.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#head(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> head(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.head(requestBuilder, responseType);
    }

    /**
     * Head.
     *
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#head(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> head(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.head(requestBuilder);
    }

    /**
     * Head completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String)
     */
    public static HttpRequestCompletion<String> head(String url) {
        return CLIENT.head(url);
    }

    /**
     * Head completion.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> head(String url, Map<String, Serializable> params) {
        return CLIENT.head(url, params);
    }

    /**
     * Head completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> head(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.head(url, params, headers);
    }

    /**
     * Head completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> head(String url, Class<R> responseType) {
        return CLIENT.head(url, responseType);
    }

    /**
     * Head completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> head(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return CLIENT.head(url, params, responseType);
    }

    /**
     * Head completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#head(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> head(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.head(url, params, headers, responseType);
    }

    /**
     * Post.
     *
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#post(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> post(
        Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.post(requestBuilder);
    }

    /**
     * Post.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#post(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.post(url, requestBody, params, headers, responseType);
    }

    /**
     * Post.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#post(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> post(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.post(url, requestBody, params, headers);
    }

    /**
     * Post.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#post(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.post(requestBuilder, responseType);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String)
     */
    public static HttpRequestCompletion<String> post(String url) {
        return CLIENT.post(url);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> post(String url, Map<String, Serializable> params) {
        return CLIENT.post(url, params);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> post(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.post(url, params, headers);
    }

    /**
     * Post completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return CLIENT.post(url, params, responseType);
    }

    /**
     * Post completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.post(url, params, headers, responseType);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> post(String url, Object requestBody) {
        return CLIENT.post(url, requestBody);
    }

    /**
     * Post completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> post(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.post(url, requestBody, headers);
    }

    /**
     * Post completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.post(url, requestBody, responseType);
    }

    /**
     * Post completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#post(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> post(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return CLIENT.post(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#put(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> put(
        Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.put(requestBuilder);
    }

    /**
     * Put.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#put(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> put(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.put(url, requestBody, params, headers);
    }

    /**
     * Put.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#put(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.put(url, requestBody, params, headers, responseType);
    }

    /**
     * Put.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#put(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.put(requestBuilder, responseType);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String)
     */
    public static HttpRequestCompletion<String> put(String url) {
        return CLIENT.put(url);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> put(String url, Map<String, Serializable> params) {
        return CLIENT.put(url, params);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> put(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.put(url, params, headers);
    }

    /**
     * Put completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return CLIENT.put(url, params, responseType);
    }

    /**
     * Put completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.put(url, params, headers, responseType);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> put(String url, Object requestBody) {
        return CLIENT.put(url, requestBody);
    }

    /**
     * Put completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> put(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.put(url, requestBody, headers);
    }

    /**
     * Put completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.put(url, requestBody, responseType);
    }

    /**
     * Put completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#put(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> put(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return CLIENT.put(url, requestBody, headers, responseType);
    }

    /**
     * Patch.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#patch(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> patch(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.patch(url, requestBody, params, headers);
    }

    /**
     * Patch.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#patch(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, params, headers, responseType);
    }

    /**
     * Patch.
     *
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#patch(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> patch(
        Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.patch(requestBuilder);
    }

    /**
     * Patch.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#patch(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.patch(requestBuilder, responseType);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String)
     */
    public static HttpRequestCompletion<String> patch(String url) {
        return CLIENT.patch(url);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> patch(String url, Map<String, Serializable> params) {
        return CLIENT.patch(url, params);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> patch(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.patch(url, params, headers);
    }

    /**
     * Patch completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return CLIENT.patch(url, params, responseType);
    }

    /**
     * Patch completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.patch(url, params, headers, responseType);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> patch(String url, Object requestBody) {
        return CLIENT.patch(url, requestBody);
    }

    /**
     * Patch completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.patch(url, requestBody, headers);
    }

    /**
     * Patch completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.patch(url, requestBody, responseType);
    }

    /**
     * Patch completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#patch(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return CLIENT.patch(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param requestBuilder the request builder
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#delete(java.util.function.Function)
     */
    public static HttpRequestCompletion<String> delete(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        return CLIENT.delete(requestBuilder);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpClient#delete(java.lang.String, java.util.Map,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> delete(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.delete(url, params, headers);
    }

    /**
     * Delete.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#delete(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, params, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClientImpl#delete(java.lang.String, java.lang.Object,
     *      java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> delete(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers) {
        return CLIENT.delete(url, requestBody, params, headers);
    }

    /**
     * Delete.
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#delete(java.util.function.Function,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder, Class<R> responseType) {
        return CLIENT.delete(requestBuilder, responseType);
    }

    /**
     * Delete.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpAsyncClient#delete(java.lang.String, java.util.Map,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, params, headers, responseType);
    }

    /**
     * Delete completion.
     *
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String)
     */
    public static HttpRequestCompletion<String> delete(String url) {
        return CLIENT.delete(url);
    }

    /**
     * Delete completion.
     *
     * @param url the url
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.util.Map)
     */
    public static HttpRequestCompletion<String> delete(String url, Map<String, String> headers) {
        return CLIENT.delete(url, headers);
    }

    /**
     * Delete completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Class<R> responseType) {
        return CLIENT.delete(url, responseType);
    }

    /**
     * Delete completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.delete(url, headers, responseType);
    }

    /**
     * Delete completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object)
     */
    public static HttpRequestCompletion<String> delete(String url, Object requestBody) {
        return CLIENT.delete(url, requestBody);
    }

    /**
     * Delete completion.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return CLIENT.delete(url, requestBody, headers);
    }

    /**
     * Delete completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Class<R> responseType) {
        return CLIENT.delete(url, requestBody, responseType);
    }

    /**
     * Delete completion.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#delete(java.lang.String,
     *      java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return CLIENT.delete(url, requestBody, headers, responseType);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param output the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.OutputStream)
     */
    public static HttpRequestCompletion<Long> download(String url, OutputStream output) {
        return CLIENT.download(url, output);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.OutputStream, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, OutputStream output,
        BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, output, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.File)
     */
    public static HttpRequestCompletion<Long> download(String url, File localFile) {
        return CLIENT.download(url, localFile);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.io.File, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, File localFile, BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.OutputStream)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        OutputStream output) {
        return CLIENT.download(url, params, output);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.OutputStream, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        OutputStream output, BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, params, output, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.File)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params, File localFile) {
        return CLIENT.download(url, params, localFile);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.io.File, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params, File localFile,
        BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, params, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.File)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile) {
        return CLIENT.download(url, params, headers, localFile);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.File, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile, BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, params, headers, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output) {
        return CLIENT.download(url, params, headers, output);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#download(java.lang.String,
     *      java.util.Map, java.util.Map, java.io.OutputStream, BiConsumer)
     */
    public static HttpRequestCompletion<Long> download(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output, BiConsumer<Long, Long> progress) {
        return CLIENT.download(url, params, headers, output, progress);
    }

    /**
     * Request completion.
     *
     * @param httpMethod the http method
     * @param url the url
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String)
     */
    public static HttpRequestCompletion<String> request(HttpMethod httpMethod, String url) {
        return CLIENT.request(httpMethod, url);
    }

    /**
     * Request completion.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map)
     */
    public static HttpRequestCompletion<String> request(HttpMethod httpMethod, String url,
        Map<String, Serializable> params) {
        return CLIENT.request(httpMethod, url, params);
    }

    /**
     * Request completion.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map)
     */
    public static HttpRequestCompletion<String> request(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, params, headers);
    }

    /**
     * Request completion.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, responseType);
    }

    /**
     * Request completion.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, responseType);
    }

    /**
     * Request completion.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.util.Map, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, params, headers, responseType);
    }

    /**
     * Request completion.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object)
     */
    public static HttpRequestCompletion<String> request(HttpMethod httpMethod, String url, Object requestBody) {
        return CLIENT.request(httpMethod, url, requestBody);
    }

    /**
     * Request completion.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map)
     */
    public static HttpRequestCompletion<String> request(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers) {
        return CLIENT.request(httpMethod, url, requestBody, headers);
    }

    /**
     * Request completion.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Object requestBody,
        Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, responseType);
    }

    /**
     * Request completion.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     * @see cn.featherfly.common.http.HttpSyncClientImpl#request(cn.featherfly.common.http.HttpMethod,
     *      java.lang.String, java.lang.Object, java.util.Map, java.lang.Class)
     */
    public static <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers, Class<R> responseType) {
        return CLIENT.request(httpMethod, url, requestBody, headers, responseType);
    }
}
