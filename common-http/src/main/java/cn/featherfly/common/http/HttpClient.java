
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

/**
 * Client.
 *
 * @author zhongj
 * @param <RS> the generic request response type
 */
public interface HttpClient<RS> {

    /**
     * get request.
     *
     * @param url the url
     * @return response content
     */
    default RS get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * get request with params.
     *
     * @param url    the url
     * @param params the params
     * @return response content
     */
    default RS get(String url, Map<String, Serializable> params) {
        return get(url, params, new HashMap<>());
    }

    /**
     * get request with params.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response content
     */
    RS get(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * post request.
     *
     * @param url the url
     * @return response content
     */
    default RS post(String url) {
        return post(url, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response content
     */
    default RS post(String url, Map<String, Serializable> params) {
        return post(url, params, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response content
     */
    RS post(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response content
     */
    default RS post(String url, Object requestBody) {
        return post(url, requestBody, new HashMap<>());
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response content
     */
    RS post(String url, Object requestBody, Map<String, String> headers);

    /**
     * Put.
     *
     * @param url the url
     * @return response content
     */
    default RS put(String url) {
        return put(url, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response content
     */
    default RS put(String url, Map<String, Serializable> params) {
        return put(url, params, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response content
     */
    RS put(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response content
     */
    default RS put(String url, Object requestBody) {
        return put(url, requestBody, new HashMap<>());
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response content
     */
    RS put(String url, Object requestBody, Map<String, String> headers);

    /**
     * Delete.
     *
     * @param url the url
     * @return response content
     */
    default RS delete(String url) {
        return delete(url, new HashMap<>());
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return response content
     */
    RS delete(String url, Map<String, String> headers);

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response content
     */
    default RS delete(String url, Object requestBody) {
        return delete(url, requestBody, new HashMap<>());
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response content
     */
    RS delete(String url, Object requestBody, Map<String, String> headers);

    /**
     * patch request.
     *
     * @param url the url
     * @return response content
     */
    default RS patch(String url) {
        return patch(url, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response content
     */
    default RS patch(String url, Map<String, Serializable> params) {
        return patch(url, params, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response content
     */
    RS patch(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response content
     */
    default RS patch(String url, Object requestBody) {
        return patch(url, requestBody, new HashMap<>());
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response content
     */
    RS patch(String url, Object requestBody, Map<String, String> headers);

    /**
     * head request.
     *
     * @param url the url
     * @return response content
     */
    default RS head(String url) {
        return head(url, new HashMap<>());
    }

    /**
     * head request with params.
     *
     * @param url    the url
     * @param params the params
     * @return response content
     */
    default RS head(String url, Map<String, Serializable> params) {
        return head(url, params, new HashMap<>());
    }

    /**
     * head request with params.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response content
     */
    RS head(String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url) {
        return request(httpMethod, url, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return request(httpMethod, url, params, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
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
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Object requestBody) {
        return request(httpMethod, url, requestBody, new HashMap<>());
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response content
     */
    default RS request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return post(url, requestBody, headers);
            case PUT:
                return put(url, requestBody, headers);
            case DELETE:
                return delete(url, requestBody, headers);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patch(url, requestBody, headers);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }
}
