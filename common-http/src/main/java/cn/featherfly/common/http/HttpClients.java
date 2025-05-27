
/*
 * All rights Reserved, Designed By zhongj
 * @Title: HttpClient.java
 * @Package cn.featherfly.common.http
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-05 13:45:05
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

import cn.featherfly.common.serialization.Serialization;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * integrate HttpClient HttpAsyncClient HttpRxjavaClient together.
 *
 * @author zhongj
 */
public class HttpClients extends AbstractHttpClient implements HttpSyncClient, HttpDownloadClient<Long> {

    private HttpSyncClient client;

    private HttpAsyncClient asyncClient;

    private HttpRxjavaClient rxjavaClient;

    /**
     * Instantiates a new http client.
     */
    public HttpClients() {
        super();
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpClients(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        super(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     */
    public HttpClients(HttpRequestConfig config, Map<String, String> headers) {
        super(config, headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpClients(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        super(config, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     */
    public HttpClients(HttpRequestConfig config) {
        super(config);
    }

    /**
     * Instantiates a new http client.
     *
     * @param headers the headers
     */
    public HttpClients(Map<String, String> headers) {
        super(headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param okhttpClient the client
     * @param headers the headers
     */
    public HttpClients(OkHttpClient okhttpClient, Map<String, String> headers) {
        super(okhttpClient, headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param okhttpClient the client
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpClients(OkHttpClient okhttpClient, Serialization serialization, MediaType mediaType) {
        super(okhttpClient, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param okhttpClient the client
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpClients(OkHttpClient okhttpClient, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        super(okhttpClient, headers, serialization, mediaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init(OkHttpClient okhttpClient, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        super.init(okhttpClient, headers, serialization, mediaType);
        client = new HttpSyncClientImpl(okhttpClient, headers, serialization, mediaType);
        asyncClient = new HttpAsyncClientImpl(okhttpClient, headers, serialization, mediaType);
        rxjavaClient = new HttpRxjavaClientImpl(okhttpClient, headers, serialization, mediaType);
    }

    /**
     * get autoSubscribeOnIo value.
     *
     * @return autoSubscribeOnIo
     */
    public boolean isAutoSubscribeOnIo() {
        return rxjavaClient.isAutoSubscribeOnIo();
    }

    /**
     * set autoSubscribeOnIo value.
     *
     * @param autoSubscribeOnIo autoSubscribeOnIo
     */
    public void setAutoSubscribeOnIo(boolean autoSubscribeOnIo) {
        rxjavaClient.setAutoSubscribeOnIo(autoSubscribeOnIo);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url) {
        return asyncClient.request(httpMethod, url);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url,
        Map<String, Serializable> params) {
        return asyncClient.request(httpMethod, url, params);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Map<String, String> headers) {
        return asyncClient.request(httpMethod, url, params, headers);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Class<R> responseType) {
        return asyncClient.request(httpMethod, url, responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Class<R> responseType) {
        return asyncClient.request(httpMethod, url, params, responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url,
        Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return asyncClient.request(httpMethod, url, params, headers, responseType);
    }

    /**
     * request body with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url, Object requestBody) {
        return asyncClient.request(httpMethod, url, requestBody);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers) {
        return asyncClient.request(httpMethod, url, requestBody, headers);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
        Class<R> responseType) {
        return asyncClient.request(httpMethod, url, requestBody, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.request(httpMethod, url, requestBody, headers, responseType);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url) {
        return rxjavaClient.request(httpMethod, url);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return rxjavaClient.request(httpMethod, url, params);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return rxjavaClient.request(httpMethod, url, params, headers);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Class<R> responseType) {
        return rxjavaClient.request(httpMethod, url, responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return rxjavaClient.request(httpMethod, url, params, responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return rxjavaClient.request(httpMethod, url, params, headers, responseType);
    }

    /**
     * request body with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Object requestBody) {
        return rxjavaClient.request(httpMethod, url, requestBody);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers) {
        return rxjavaClient.request(httpMethod, url, requestBody, headers);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
        Class<R> responseType) {
        return rxjavaClient.request(httpMethod, url, requestBody, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers, Class<R> responseType) {
        return rxjavaClient.request(httpMethod, url, requestBody, headers, responseType);
    }

    /**
     * get request.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String get(String url) {
        return client.get(url);
    }

    /**
     * get request with params.
     *
     * @param url the url
     * @param params the params
     * @return response string
     */
    @Override
    public String get(String url, Map<String, Serializable> params) {
        return client.get(url, params);
    }

    /**
     * get request with params.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response string
     */
    @Override
    public String get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return client.get(url, params, headers);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R get(String url, Class<R> responseType) {
        return client.get(url, responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return client.get(url, params, responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R get(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return client.get(url, params, headers, responseType);
    }

    /**
     * get request.
     *
     * @param url the url
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url) {
        return asyncClient.get(url);
    }

    /**
     * get request.
     *
     * @param url the url
     * @param params the params
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params) {
        return asyncClient.get(url, params);
    }

    /**
     * get request.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return asyncClient.get(url, params, headers);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Class<R> responseType) {
        return asyncClient.get(url, responseType);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return asyncClient.get(url, params, responseType);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.get(url, params, headers, responseType);
    }

    /**
     * get request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> getObservable(String url) {
        return rxjavaClient.get(url);
    }

    /**
     * get request.
     *
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> getObservable(String url, Map<String, Serializable> params) {
        return rxjavaClient.get(url, params);
    }

    /**
     * get request.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> getObservable(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return rxjavaClient.get(url, params, headers);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> getObservable(String url, Class<R> responseType) {
        return rxjavaClient.get(url, responseType);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> getObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return rxjavaClient.get(url, params, responseType);
    }

    /**
     * get request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> getObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.get(url, params, headers, responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String head(String url) {
        return client.head(url);
    }

    /**
     * head request with params.
     *
     * @param url the url
     * @param params the params
     * @return response string
     */
    @Override
    public String head(String url, Map<String, Serializable> params) {
        return client.head(url, params);
    }

    /**
     * head request with params.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response string
     */
    @Override
    public String head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return client.head(url, params, headers);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R head(String url, Class<R> responseType) {
        return client.head(url, responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return client.head(url, params, responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R head(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return client.head(url, params, headers, responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url) {
        return asyncClient.head(url);
    }

    /**
     * head request.
     *
     * @param url the url
     * @param params the params
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params) {
        return asyncClient.head(url, params);
    }

    /**
     * head request.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return asyncClient.head(url, params, headers);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Class<R> responseType) {
        return asyncClient.head(url, responseType);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return asyncClient.head(url, params, responseType);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.head(url, params, headers, responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> headObservable(String url) {
        return rxjavaClient.head(url);
    }

    /**
     * head request.
     *
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> headObservable(String url, Map<String, Serializable> params) {
        return rxjavaClient.head(url, params);
    }

    /**
     * head request.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> headObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return rxjavaClient.head(url, params, headers);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> headObservable(String url, Class<R> responseType) {
        return rxjavaClient.head(url, responseType);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> headObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return rxjavaClient.head(url, params, responseType);
    }

    /**
     * head request.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> headObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.head(url, params, headers, responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String post(String url) {
        return client.post(url);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response string
     */
    @Override
    public String post(String url, Map<String, Serializable> params) {
        return client.post(url, params);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response string
     */
    @Override
    public String post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return client.post(url, params, headers);
    }

    /**
     * Post params with FormBody and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return response string
     */
    @Override
    public <R> R post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return client.post(url, params, responseType);
    }

    /**
     * Post params with FormBody and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return response string
     */
    @Override
    public <R> R post(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return client.post(url, params, headers, responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response string
     */
    @Override
    public String post(String url, Object requestBody) {
        return client.post(url, requestBody);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response string
     */
    @Override
    public String post(String url, Object requestBody, Map<String, String> headers) {
        return client.post(url, requestBody, headers);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R post(String url, Object requestBody, Class<R> responseType) {
        return client.post(url, requestBody, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return client.post(url, requestBody, headers, responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url) {
        return asyncClient.post(url);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params) {
        return asyncClient.post(url, params);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return asyncClient.post(url, params, headers);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return asyncClient.post(url, params, responseType);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.post(url, params, headers, responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Object requestBody) {
        return asyncClient.post(url, requestBody);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Object requestBody, Map<String, String> headers) {
        return asyncClient.post(url, requestBody, headers);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody, Class<R> responseType) {
        return asyncClient.post(url, requestBody, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return asyncClient.post(url, requestBody, headers, responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> postObservable(String url) {
        return rxjavaClient.post(url);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> postObservable(String url, Map<String, Serializable> params) {
        return rxjavaClient.post(url, params);
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> postObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return rxjavaClient.post(url, params, headers);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> postObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return rxjavaClient.post(url, params, responseType);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> postObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.post(url, params, headers, responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> postObservable(String url, Object requestBody) {
        return rxjavaClient.post(url, requestBody);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> postObservable(String url, Object requestBody, Map<String, String> headers) {
        return rxjavaClient.post(url, requestBody, headers);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> postObservable(String url, Object requestBody, Class<R> responseType) {
        return rxjavaClient.post(url, requestBody, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> postObservable(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.post(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String put(String url) {
        return client.put(url);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response string
     */
    @Override
    public String put(String url, Map<String, Serializable> params) {
        return client.put(url, params);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response string
     */
    @Override
    public String put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return client.put(url, params, headers);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return client.put(url, params, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R put(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return client.put(url, params, headers, responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response string
     */
    @Override
    public String put(String url, Object requestBody) {
        return client.put(url, requestBody);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response string
     */
    @Override
    public String put(String url, Object requestBody, Map<String, String> headers) {
        return client.put(url, requestBody, headers);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the responset type
     * @return responseType instance
     */
    @Override
    public <R> R put(String url, Object requestBody, Class<R> responseType) {
        return client.put(url, requestBody, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the responset type
     * @return responseType instance
     */
    @Override
    public <R> R put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return client.put(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url) {
        return asyncClient.put(url);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params) {
        return asyncClient.put(url, params);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return asyncClient.put(url, params, headers);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return asyncClient.put(url, params, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.put(url, params, headers, responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Object requestBody) {
        return asyncClient.put(url, requestBody);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Object requestBody, Map<String, String> headers) {
        return asyncClient.put(url, requestBody, headers);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody, Class<R> responseType) {
        return asyncClient.put(url, requestBody, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return asyncClient.put(url, requestBody, headers, responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> putObservable(String url) {
        return rxjavaClient.put(url);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> putObservable(String url, Map<String, Serializable> params) {
        return rxjavaClient.put(url, params);
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> putObservable(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return rxjavaClient.put(url, params, headers);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> putObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return rxjavaClient.put(url, params, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> putObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.put(url, params, headers, responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> putObservable(String url, Object requestBody) {
        return rxjavaClient.put(url, requestBody);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> putObservable(String url, Object requestBody, Map<String, String> headers) {
        return rxjavaClient.put(url, requestBody, headers);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> putObservable(String url, Object requestBody, Class<R> responseType) {
        return rxjavaClient.put(url, requestBody, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> putObservable(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.put(url, requestBody, headers, responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String patch(String url) {
        return client.patch(url);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response string
     */
    @Override
    public String patch(String url, Map<String, Serializable> params) {
        return client.patch(url, params);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response string
     */
    @Override
    public String patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return client.patch(url, params, headers);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return client.patch(url, params, responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R patch(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return client.patch(url, params, headers, responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response string
     */
    @Override
    public String patch(String url, Object requestBody) {
        return client.patch(url, requestBody);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response string
     */
    @Override
    public String patch(String url, Object requestBody, Map<String, String> headers) {
        return client.patch(url, requestBody, headers);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the responset type
     * @return responseType instance
     */
    @Override
    public <R> R patch(String url, Object requestBody, Class<R> responseType) {
        return client.patch(url, requestBody, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the responset type
     * @return responseType instance
     */
    @Override
    public <R> R patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return client.patch(url, requestBody, headers, responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url) {
        return asyncClient.patch(url);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params) {
        return asyncClient.patch(url, params);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return asyncClient.patch(url, params, headers);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return asyncClient.patch(url, params, responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return asyncClient.patch(url, params, headers, responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Object requestBody) {
        return asyncClient.patch(url, requestBody);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Object requestBody, Map<String, String> headers) {
        return asyncClient.patch(url, requestBody, headers);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody, Class<R> responseType) {
        return asyncClient.patch(url, requestBody, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return asyncClient.patch(url, requestBody, headers, responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> patchObservable(String url) {
        return rxjavaClient.patch(url);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Map<String, Serializable> params) {
        return rxjavaClient.patch(url, params);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return rxjavaClient.patch(url, params, headers);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patchObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return rxjavaClient.patch(url, params, responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patchObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.patch(url, params, headers, responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Object requestBody) {
        return rxjavaClient.patch(url, requestBody);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Object requestBody, Map<String, String> headers) {
        return rxjavaClient.patch(url, requestBody, headers);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patchObservable(String url, Object requestBody, Class<R> responseType) {
        return rxjavaClient.patch(url, requestBody, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patchObservable(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.patch(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return response string
     */
    @Override
    public String delete(String url) {
        return client.delete(url);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param headers the headers
     * @return response string
     */
    @Override
    public String delete(String url, Map<String, String> headers) {
        return client.delete(url, headers);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return response string
     */
    @Override
    public <R> R delete(String url, Class<R> responseType) {
        return client.delete(url, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param headers the headers
     * @param responseType the response type
     * @return response string
     */
    @Override
    public <R> R delete(String url, Map<String, String> headers, Class<R> responseType) {
        return client.delete(url, headers, responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response string
     */
    @Override
    public String delete(String url, Object requestBody) {
        return client.delete(url, requestBody);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response string
     */
    @Override
    public String delete(String url, Object requestBody, Map<String, String> headers) {
        return client.delete(url, requestBody, headers);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R delete(String url, Object requestBody, Class<R> responseType) {
        return client.delete(url, requestBody, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return responseType instance
     */
    @Override
    public <R> R delete(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return client.delete(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url) {
        return asyncClient.delete(url);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Map<String, String> headers) {
        return asyncClient.delete(url, headers);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Class<R> responseType) {
        return asyncClient.delete(url, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Map<String, String> headers,
        Class<R> responseType) {
        return asyncClient.delete(url, headers, responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody) {
        return asyncClient.delete(url, requestBody);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody, Map<String, String> headers) {
        return asyncClient.delete(url, requestBody, headers);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody, Class<R> responseType) {
        return asyncClient.delete(url, requestBody, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return asyncClient.delete(url, requestBody, headers, responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> deleteObservable(String url) {
        return rxjavaClient.delete(url);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> deleteObservable(String url, Map<String, String> headers) {
        return rxjavaClient.delete(url, headers);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> deleteObservable(String url, Class<R> responseType) {
        return rxjavaClient.delete(url, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> deleteObservable(String url, Map<String, String> headers, Class<R> responseType) {
        return rxjavaClient.delete(url, headers, responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> deleteObservable(String url, Object requestBody) {
        return rxjavaClient.delete(url, requestBody);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> deleteObservable(String url, Object requestBody, Map<String, String> headers) {
        return rxjavaClient.delete(url, requestBody, headers);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> deleteObservable(String url, Object requestBody, Class<R> responseType) {
        return rxjavaClient.delete(url, requestBody, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> deleteObservable(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return rxjavaClient.delete(url, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long download(String url, Map<String, Serializable> params, Map<String, String> headers,
        OutputStream output, BiConsumer<Long, Long> progress) {
        return client.download(url, params, headers, output, progress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream stream(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        return client.stream(httpMethod, url, params, headers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream stream(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
        return client.stream(httpMethod, url, requestBody, headers);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param output the output
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, OutputStream output) {
        return asyncClient.download(url, output);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, OutputStream output,
        BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, output, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param localFile the local file
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, File localFile) {
        return asyncClient.download(url, localFile);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, File localFile, BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        OutputStream output) {
        return asyncClient.download(url, params, output);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        OutputStream output, BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, params, output, progress);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        File localFile) {
        return asyncClient.download(url, params, localFile);
    }

    /**
     * Download.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        File localFile, BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, params, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile) {
        return asyncClient.download(url, params, headers, localFile);
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
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile, BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, params, headers, localFile, progress);
    }

    /**
     * Download completion.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @return the http request completion
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output) {
        return asyncClient.download(url, params, headers, output);
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
     */
    public HttpRequestCompletion<Long> downloadCompletion(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output, BiConsumer<Long, Long> progress) {
        return asyncClient.download(url, params, headers, output, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param output the output
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, OutputStream output) {
        return rxjavaClient.download(url, output);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, OutputStream output, BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, output, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, File localFile) {
        return rxjavaClient.download(url, localFile);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, File localFile, BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, localFile, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params, OutputStream output) {
        return rxjavaClient.download(url, params, output);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params, OutputStream output,
        BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, params, output, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params, File localFile) {
        return rxjavaClient.download(url, params, localFile);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params, File localFile,
        BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, params, localFile, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile) {
        return rxjavaClient.download(url, params, headers, localFile);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param localFile the local file
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers, File localFile, BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, params, headers, localFile, progress);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output) {
        return rxjavaClient.download(url, params, headers, output);
    }

    /**
     * Download observable.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param output the output
     * @param progress the progress
     * @return the http request completion
     */
    public Observable<Long> downloadObservable(String url, Map<String, Serializable> params,
        Map<String, String> headers, OutputStream output, BiConsumer<Long, Long> progress) {
        return rxjavaClient.download(url, params, headers, output, progress);
    }
}
