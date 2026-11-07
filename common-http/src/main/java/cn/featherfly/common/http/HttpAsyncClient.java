
/*
 * All rights Reserved, Designed By zhongj
 * @Title: HttpAsyncClient.java
 * @Package cn.featherfly.common.http
 * @Description: HttpAsyncClient
 * @author: zhongj
 * @date: 2022-12-02 15:38:02
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.http.request.BasicMethodHttpRequestBuilder;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.BasicMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.FormMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpRequest;
import cn.featherfly.common.http.request.HttpRequestAware;

/**
 * HttpAsyncClient.
 *
 * @author zhongj
 */
public interface HttpAsyncClient extends HttpClient<HttpRequestCompletion<String>, HttpAsyncClient>,
    HttpDownloadClient<HttpRequestCompletion<Long>> {

    /**
     * request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param httpMethod the http method
     * @param url the url
     * @param responseType the response type
     * @return the http request completion
     */
    default <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return request(httpMethod, url, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Class<R> responseType) {
        return request(httpMethod, url, params, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
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
    default <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Object requestBody,
        Class<R> responseType) {
        return request(httpMethod, url, requestBody, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> request(HttpMethod httpMethod, String url, Object requestBody,
        Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(0), headers);
            case POST:
                return post(url, requestBody, headers, responseType);
            case PUT:
                return put(url, requestBody, headers, responseType);
            case DELETE:
                return delete(url, requestBody, headers, responseType);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(0), headers);
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
    default <R> HttpRequestCompletion<R> get(Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder,
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
    default <R> HttpRequestCompletion<R> get(String url, Class<R> responseType) {
        return get(url, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return get(url, params, new HashMap<>(0), responseType);
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
    <R> HttpRequestCompletion<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType);

    /**
     * head request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> HttpRequestCompletion<R> head(Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder,
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
    default <R> HttpRequestCompletion<R> head(String url, Class<R> responseType) {
        return head(url, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return head(url, params, new HashMap<>(0), responseType);
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
    <R> HttpRequestCompletion<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType);

    /**
     * post request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> HttpRequestCompletion<R> post(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return post(url, params, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
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
    default <R> HttpRequestCompletion<R> post(String url, Object requestBody, Class<R> responseType) {
        return post(url, requestBody, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> post(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return post(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * Post params with FormBody and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return response string
     */
    <R> HttpRequestCompletion<R> post(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * put request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> HttpRequestCompletion<R> put(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return put(url, params, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return put(url, null, params, headers, responseType);
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
    default <R> HttpRequestCompletion<R> put(String url, Object requestBody, Class<R> responseType) {
        return put(url, requestBody, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> put(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return put(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @param responseType the response type
     * @return the http request completion
     */
    <R> HttpRequestCompletion<R> put(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * patch request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> HttpRequestCompletion<R> patch(
        Function<FormMethodHttpRequestBuilderImpl, HttpRequestAware> requestBuilder, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return patch(url, params, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> patch(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
        return patch(url, null, params, headers, responseType);
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
    <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R> the generic type
     * @param url the url
     * @param requestBody the request body
     * @param responseType the responset type
     * @return the http request completion
     */
    default <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Class<R> responseType) {
        return patch(url, requestBody, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Map<String, String> headers,
        Class<R> responseType) {
        return patch(url, requestBody, new HashMap<>(0), headers, responseType);
    }

    /**
     * delete request with params and deserialize response .
     *
     * @param <R> the generic type
     * @param requestBuilder the request builder
     * @param responseType the response type
     * @return responseType instance
     */
    default <R> HttpRequestCompletion<R> delete(
        Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> delete(String url, Class<R> responseType) {
        return delete(url, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> delete(String url, Map<String, Serializable> params, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> delete(String url, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType) {
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
    default <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Class<R> responseType) {
        return delete(url, requestBody, new HashMap<>(0), responseType);
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
    default <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Map<String, String> headers,
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
    <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Map<String, Serializable> params,
        Map<String, String> headers, Class<R> responseType);

}
