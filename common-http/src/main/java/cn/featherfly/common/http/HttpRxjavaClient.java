
/*
 * All rights Reserved, Designed By zhongj
 * @Title: HttpRxjavaClient.java
 * @Package cn.featherfly.common.http
 * @Description: HttpRxjavaClient
 * @author: zhongj
 * @date: 2022-12-02 15:38:02
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.BasicMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.FormMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpRequest;
import cn.featherfly.common.http.request.HttpRequestAware;
import io.reactivex.rxjava3.core.Observable;

/**
 * HttpRxjavaClient.
 *
 * @author zhongj
 */
public interface HttpRxjavaClient
    extends HttpClient<Observable<String>, HttpRxjavaClient>, HttpDownloadClient<Observable<Long>> {

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> Observable<R> request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return request(httpMethod, url, new HashMap<>(), responseType);
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
    default <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return request(httpMethod, url, params, new HashMap<>(), responseType);
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
    default <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                return get(url, params, headers, responseType);
            case POST:
                return post(url, params, headers, responseType);
            case PUT:
                return put(url, params, headers, responseType);
            case DELETE:
                return delete(url, headers, responseType);
            case HEAD:
                return head(url, params, headers, responseType);
            case PATCH:
                return patch(url, params, headers, responseType);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
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
    default <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody, Class<R> responseType) {
        return request(httpMethod, url, requestBody, new HashMap<>(), responseType);
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
    default <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return post(url, requestBody, headers, responseType);
            case PUT:
                return put(url, requestBody, headers, responseType);
            case DELETE:
                return delete(url, requestBody, headers, responseType);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patch(url, requestBody, headers, responseType);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> get(Function<BasicMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.GET))
            .getHttpRequest();
        return get(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> Observable<R> get(String url, Class<R> responseType) {
        return get(url, new HashMap<>(), responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> Observable<R> get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return get(url, params, new HashMap<>(), responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    <R> Observable<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType);

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> head(Function<BasicMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.HEAD))
            .getHttpRequest();
        return head(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> Observable<R> head(String url, Class<R> responseType) {
        return head(url, new HashMap<>(), responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> Observable<R> head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return head(url, params, new HashMap<>(), responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    <R> Observable<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType);

    /**
     * post request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> post(Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.POST))
            .getHttpRequest();
        return post(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
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
    default <R> Observable<R> post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return post(url, params, new HashMap<>(), responseType);
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
    default <R> Observable<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return post(url, null, params, headers, responseType);
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
    default <R> Observable<R> post(String url, Object requestBody, Class<R> responseType) {
        return post(url, requestBody, new HashMap<>(), responseType);
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
    default <R> Observable<R> post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return post(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    <R> Observable<R> post(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * put request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> put(Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.PUT))
            .getHttpRequest();
        return put(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
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
    default <R> Observable<R> put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return put(url, params, new HashMap<>(), responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the responset type
     * @return the http request completion
     */
    default <R> Observable<R> put(String url, Object requestBody, Class<R> responseType) {
        return put(url, requestBody, new HashMap<>(), responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the responset type
     * @return the http request completion
     */
    default <R> Observable<R> put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return put(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the responset type
     * @return the http request completion
     */
    <R> Observable<R> put(String url, Object requestBody, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType);

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
    default <R> Observable<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return put(url, null, params, headers, responseType);
    }

    /**
     * patch request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> patch(Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.PATCH))
            .getHttpRequest();
        return patch(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
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
    default <R> Observable<R> patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return patch(url, params, new HashMap<>(), responseType);
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
    default <R> Observable<R> patch(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return patch(url, null, params, headers, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the responset type
     * @return the http request completion
     */
    default <R> Observable<R> patch(String url, Object requestBody, Class<R> responseType) {
        return patch(url, requestBody, new HashMap<>(), responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @param responseType the responset type
     * @return the http request completion
     */
    default <R> Observable<R> patch(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return patch(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the responset type
     * @return the http request completion
     */
    <R> Observable<R> patch(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * delete request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> Observable<R> delete(Function<BasicMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder,
        Class<R> responseType) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.DELETE))
            .getHttpRequest();
        return delete(request.getUrl(), request.getParameters(), request.getHeaders(), responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param responseType the response type
     * @return response string
     */
    default <R> Observable<R> delete(String url, Class<R> responseType) {
        return delete(url, new HashMap<>(), responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param responseType the response type
     * @return response string
     */
    default <R> Observable<R> delete(String url, Map<String, Serializable> params, Class<R> responseType) {
        return delete(url, params, new HashMap<>(0), responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return response string
     */
    default <R> Observable<R> delete(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return delete(url, null, params, headers, responseType);
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
    default <R> Observable<R> delete(String url, Object requestBody, Class<R> responseType) {
        return delete(url, requestBody, new HashMap<>(), responseType);
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
    default <R> Observable<R> delete(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return delete(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    <R> Observable<R> delete(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * get autoSubscribeOnIo value.
     *
     * @return autoSubscribeOnIo
     */
    boolean isAutoSubscribeOnIo();

    /**
     * set autoSubscribeOnIo value.
     *
     * @param autoSubscribeOnIo autoSubscribeOnIo
     */
    void setAutoSubscribeOnIo(boolean autoSubscribeOnIo);
}
