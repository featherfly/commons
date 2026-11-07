
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.http.request.BasicMethodHttpRequestBuilder;
import cn.featherfly.common.http.request.FormMethodHttpRequestBuilder;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.BasicMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpReQuestBuilderImpl.FormMethodHttpRequestBuilderImpl;
import cn.featherfly.common.http.request.HttpRequest;
import cn.featherfly.common.http.request.HttpRequestAware;
import cn.featherfly.common.http.request.HttpRequestBuilder;

/**
 * Client.
 *
 * @author zhongj
 * @param <RS> the generic request response type
 * @param <C> the generic type
 */
public interface HttpClient<RS, C> {

    /**
     * add HttpListener listener.
     *
     * @param listener the listener
     * @return the c
     */
    C on(HttpListener listener);

    /**
     * remove HttpListener listener.
     *
     * @param listener the listener
     * @return the c
     */
    C remove(HttpListener listener);

    /**
     * get request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS get(Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.GET))
            .getHttpRequest();
        return get(request.getUrl(), request.getParameters(), request.getHeaders());
    }

    /**
     * get request.
     *
     * @param url the url
     * @return response content
     */
    default RS get(String url) {
        return get(url, new HashMap<>(0));
    }

    /**
     * get request with params.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS get(String url, Map<String, Serializable> params) {
        return get(url, params, new HashMap<>(0));
    }

    /**
     * get request with params.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    RS get(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * post request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS post(Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.POST))
            .getHttpRequest();
        return post(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
    }

    /**
     * post request.
     *
     * @param url the url
     * @return response content
     */
    default RS post(String url) {
        return post(url, new HashMap<>(0));
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS post(String url, Map<String, Serializable> params) {
        return post(url, params, new HashMap<>(0));
    }

    /**
     * Post params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    default RS post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return post(url, null, params, headers);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response content
     */
    default RS post(String url, Object requestBody) {
        return post(url, requestBody, new HashMap<>(0));
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    default RS post(String url, Object requestBody, Map<String, String> headers) {
        return post(url, requestBody, new HashMap<>(0), headers);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    RS post(String url, Object requestBody, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * put request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS put(Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.PUT))
            .getHttpRequest();
        return put(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
    }

    /**
     * Put.
     *
     * @param url the url
     * @return response content
     */
    default RS put(String url) {
        return put(url, new HashMap<>(0));
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS put(String url, Map<String, Serializable> params) {
        return put(url, params, new HashMap<>(0));
    }

    /**
     * Put params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    default RS put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return put(url, null, params, headers);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response content
     */
    default RS put(String url, Object requestBody) {
        return put(url, requestBody, new HashMap<>(0));
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    default RS put(String url, Object requestBody, Map<String, String> headers) {
        return put(url, requestBody, new HashMap<>(0), headers);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    RS put(String url, Object requestBody, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * delete request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS delete(Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.DELETE))
            .getHttpRequest();
        return delete(request.getUrl(), request.getParameters(), request.getHeaders());
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return response content
     */
    default RS delete(String url) {
        return delete(url, new HashMap<>(0));
    }

    /**
     * get request with params.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS delete(String url, Map<String, Serializable> params) {
        return delete(url, params, new HashMap<>(0));
    }

    /**
     * Delete.
     *
     * @param url the url
     * @param headers the headers
     * @return response content
     */
    default RS delete(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return delete(url, null, params, headers);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response content
     */
    default RS delete(String url, Object requestBody) {
        return delete(url, requestBody, new HashMap<>(0));
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    default RS delete(String url, Object requestBody, Map<String, String> headers) {
        return delete(url, requestBody, new HashMap<>(0), headers);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    RS delete(String url, Object requestBody, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * patch request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS patch(Function<FormMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new FormMethodHttpRequestBuilderImpl(HttpMethod.PATCH))
            .getHttpRequest();
        return patch(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return response content
     */
    default RS patch(String url) {
        return patch(url, new HashMap<>(0));
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS patch(String url, Map<String, Serializable> params) {
        return patch(url, params, new HashMap<>(0));
    }

    /**
     * patch request params with FormBody.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    default RS patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return patch(url, null, new HashMap<>(0), headers);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @return response content
     */
    default RS patch(String url, Object requestBody) {
        return patch(url, requestBody, new HashMap<>(0));
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    default RS patch(String url, Object requestBody, Map<String, String> headers) {
        return patch(url, requestBody, new HashMap<>(0), headers);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url the url
     * @param requestBody the request body
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    RS patch(String url, Object requestBody, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * head request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS head(Function<BasicMethodHttpRequestBuilder, HttpRequestAware> requestBuilder) {
        HttpRequest request = requestBuilder.apply(new BasicMethodHttpRequestBuilderImpl(HttpMethod.HEAD))
            .getHttpRequest();
        return head(request.getUrl(), request.getParameters(), request.getHeaders());
    }

    /**
     * head request.
     *
     * @param url the url
     * @return response content
     */
    default RS head(String url) {
        return head(url, new HashMap<>(0));
    }

    /**
     * head request with params.
     *
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS head(String url, Map<String, Serializable> params) {
        return head(url, params, new HashMap<>(0));
    }

    /**
     * head request with params.
     *
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    RS head(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * Request.
     *
     * @param <T> the generic type
     * @param requestBuilder the request builder
     * @return the rs
     */
    default RS request(Function<HttpRequestBuilder, HttpRequestAware> requestBuilder) {
        // TODO 没有创建HttpRequestBuilder
        HttpRequest request = requestBuilder.apply(null).getHttpRequest();
        switch (request.getMethod()) {
            case GET:
                return get(request.getUrl(), request.getParameters(), request.getHeaders());
            case POST:
                return post(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
            case PUT:
                return put(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
            case DELETE:
                return delete(request.getUrl(), request.getParameters(), request.getHeaders());
            case HEAD:
                return head(request.getUrl(), request.getParameters(), request.getHeaders());
            case PATCH:
                return patch(request.getUrl(), request.getRequestBody(), request.getParameters(), request.getHeaders());
            default:
                throw new HttpException("unsupport http method " + request.getMethod().toString());
        }
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url) {
        return request(httpMethod, url, new HashMap<>(0));
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return request(httpMethod, url, params, new HashMap<>(0));
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param params the params
     * @param headers the headers
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                return get(url, params, headers);
            case POST:
                return post(url, params, headers);
            case PUT:
                return put(url, params, headers);
            case DELETE:
                return delete(url, headers);
            case HEAD:
                return head(url, params, headers);
            case PATCH:
                return patch(url, params, headers);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    /**
     * request body with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Object requestBody) {
        return request(httpMethod, url, requestBody, new HashMap<>(0));
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod the http method
     * @param url the url
     * @param requestBody the request body
     * @param headers the headers
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(0), headers);
            case POST:
                return post(url, requestBody, headers);
            case PUT:
                return put(url, requestBody, headers);
            case DELETE:
                return delete(url, requestBody, headers);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(0), headers);
            case PATCH:
                return patch(url, requestBody, headers);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }
}
