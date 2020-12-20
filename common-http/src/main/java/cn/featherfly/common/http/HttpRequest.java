package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.Map;

import io.reactivex.Observable;

/**
 * The Interface HttpRequest.
 *
 * @author zhongj
 */
public interface HttpRequest {

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <R>          请求对象类型
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param requestBody  请求体参数
     * @param responseType 返回类型
     * @return the http request handler
     */
    <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody, Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <R>          请求对象类型
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param requestBody  请求体参数
     * @param responseType 返回类型
     * @return the http request handler
     */
    <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody, Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <R>          请求对象类型
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param requestBody  请求体参数
     * @param headers      请求头
     * @param responseType 返回类型
     * @return the http request handler
     */
    <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <R>          请求对象类型
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param requestBody  请求体参数
     * @param headers      请求头
     * @param responseType 返回类型
     * @return the http request handler
     */
    <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，使用Future转换为同步方法调用方式.
     *
     * @param <R>           请求对象类型
     * @param <T>           返回内容转换的对象类型
     * @param method        httpmethod
     * @param url           url
     * @param requestBody   请求体参数
     * @param responseType  返回类型
     * @param errorListener 错误监听器
     * @return 返回内容转换后的对象
     */
    <R, T> T send(HttpMethod method, String url, R requestBody, Class<T> responseType, ErrorListener errorListener);

    /**
     * 发送请求（请求体作为参数），异步网络请求，使用Future转换为同步方法调用方式.
     *
     * @param <R>           请求对象类型
     * @param <T>           返回内容转换的对象类型
     * @param method        httpmethod
     * @param url           url
     * @param requestBody   请求体参数
     * @param headers       请求头
     * @param responseType  返回类型
     * @param errorListener 错误监听器
     * @return 返回内容转换后的对象
     */
    <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers, Class<T> responseType,
            ErrorListener errorListener);

    /**
     * 发送请求（请求体作为参数），异步网络请求，使用Future转换为同步方法调用方式.
     *
     * @param <R>                   请求对象类型
     * @param <T>                   返回内容转换的对象类型
     * @param method                httpmethod
     * @param url                   url
     * @param requestBody           请求体参数
     * @param headers               请求头
     * @param responseType          返回类型
     * @param errorListener         错误监听器
     * @param requestTimeoutSeconds 请求超时时间
     * @return 返回内容转换后的对象
     */
    <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers, Class<T> responseType,
            ErrorListener errorListener, long requestTimeoutSeconds);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param params       请求参数
     * @param headers      请求头
     * @param responseType 返回类型
     * @return the http request handler
     */
    <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param params       请求参数
     * @param headers      请求头
     * @param responseType 返回类型
     * @return the http request handler
     */
    <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param params       请求参数
     * @param responseType 返回类型
     * @return the http request handler
     */
    <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，回调处理.
     *
     * @param <T>          返回内容转换的对象类型
     * @param method       httpmethod
     * @param url          url
     * @param params       请求参数
     * @param responseType 返回类型
     * @return the http request handler
     */
    <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType);

    /**
     * 发送请求（请求体作为参数），异步网络请求，使用Future转换为同步方法调用方式.
     *
     * @param <T>           返回内容转换的对象类型
     * @param method        httpmethod
     * @param url           url
     * @param params        请求参数
     * @param headers       请求头
     * @param responseType  返回类型
     * @param errorListener 错误监听器
     * @return 返回内容转换后的对象
     */
    <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener);

    /**
     * 发送请求（请求体作为参数），异步网络请求，使用Future转换为同步方法调用方式.
     *
     * @param <T>           返回内容转换的对象类型
     * @param method        httpmethod
     * @param url           url
     * @param params        请求参数
     * @param responseType  返回类型
     * @param errorListener 错误监听器
     * @return 返回内容转换后的对象
     */
    <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Class<T> responseType,
            ErrorListener errorListener);
}
