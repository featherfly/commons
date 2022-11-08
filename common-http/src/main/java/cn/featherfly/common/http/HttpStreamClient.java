
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
 * Stream Client.
 *
 * @author zhongj
 * @param <SS> the generic type
 */
public interface HttpStreamClient<SS> {

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the input stream
     */
    default SS stream(HttpMethod httpMethod, String url) {
        return stream(httpMethod, url, new HashMap<>());
    }

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the input stream
     */
    default SS stream(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return stream(httpMethod, url, params, new HashMap<>());
    }

    /**
     * Stream.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the input stream
     */
    SS stream(HttpMethod httpMethod, String url, Map<String, Serializable> params, Map<String, String> headers);

    /**
     * Stream.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return the input stream
     */
    default SS stream(HttpMethod httpMethod, String url, Object requestBody) {
        return stream(httpMethod, url, requestBody, new HashMap<>());
    }

    /**
     * Stream.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the input stream
     */
    SS stream(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers);
}
