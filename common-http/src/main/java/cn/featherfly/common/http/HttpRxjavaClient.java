package cn.featherfly.common.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http client.
 *
 * @author zhongj
 */
public class HttpRxjavaClient extends AbstractHttpClient {

    private boolean autoSubscribeOnIo = true;

    /**
     * Instantiates a new http rxjava client.
     */
    public HttpRxjavaClient() {
        super();
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config        the config
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClient(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config  the config
     * @param headers the headers
     */
    public HttpRxjavaClient(HttpRequestConfig config, Map<String, String> headers) {
        super(config, headers);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClient(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        super(config, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config the config
     */
    public HttpRxjavaClient(HttpRequestConfig config) {
        super(config);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param headers the headers
     */
    public HttpRxjavaClient(Map<String, String> headers) {
        super(headers);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param client        the client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClient(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(client, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param client  the client
     * @param headers the headers
     */
    public HttpRxjavaClient(OkHttpClient client, Map<String, String> headers) {
        super(client, headers);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param client        the client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClient(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        super(client, serialization, mediaType);
    }

    /**
     * get autoSubscribeOnIo value.
     *
     * @return autoSubscribeOnIo
     */
    public boolean isAutoSubscribeOnIo() {
        return autoSubscribeOnIo;
    }

    /**
     * set autoSubscribeOnIo value.
     *
     * @param autoSubscribeOnIo autoSubscribeOnIo
     */
    public void setAutoSubscribeOnIo(boolean autoSubscribeOnIo) {
        this.autoSubscribeOnIo = autoSubscribeOnIo;
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the observable
     */
    public Observable<String> request(HttpMethod httpMethod, String url) {
        return request(httpMethod, url, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the observable
     */
    public Observable<String> request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return request(httpMethod, url, params, EMPTY);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the observable
     */
    public Observable<String> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
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
     * request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> request(HttpMethod httpMethod, String url, Class<R> responseType) {
        return request(httpMethod, url, new HashMap<>(), responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return request(httpMethod, url, params, EMPTY, responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
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
     * request body with medieType format.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> request(HttpMethod httpMethod, String url, Object requestBody) {
        return request(httpMethod, url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> request(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers) {
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

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody, Class<R> responseType) {
        return request(httpMethod, url, requestBody, EMPTY, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> Observable<R> request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
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
     * get request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * get request.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> get(String url, Map<String, Serializable> params) {
        return get(url, params, EMPTY);
    }

    /**
     * get request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build());
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> get(String url, Class<R> responseType) {
        return get(url, new HashMap<>(), responseType);
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return get(url, params, EMPTY, responseType);
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build(), responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> head(String url) {
        return head(url, new HashMap<>());
    }

    /**
     * head request.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> head(String url, Map<String, Serializable> params) {
        return head(url, params, EMPTY);
    }

    /**
     * head request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build());
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> head(String url, Class<R> responseType) {
        return head(url, new HashMap<>(), responseType);
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return head(url, params, EMPTY, responseType);
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build(), responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> post(String url) {
        return post(url, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> post(String url, Map<String, Serializable> params) {
        return post(url, params, EMPTY);
    }

    /**
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Post params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return post(url, params, EMPTY, responseType);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> post(String url, Object requestBody) {
        return post(url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> post(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> post(String url, Object requestBody, Class<R> responseType) {
        return post(url, requestBody, EMPTY, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> put(String url) {
        return put(url, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> put(String url, Map<String, Serializable> params) {
        return put(url, params, EMPTY);
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Put params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> put(String url, Map<String, Serializable> params, Class<R> responseType) {
        return put(url, params, EMPTY, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> put(String url, Object requestBody) {
        return put(url, requestBody, EMPTY);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> put(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> put(String url, Object requestBody, Class<R> responseType) {
        return put(url, requestBody, EMPTY, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> patch(String url) {
        return patch(url, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> patch(String url, Map<String, Serializable> params) {
        return patch(url, params, EMPTY);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patch(String url, Map<String, Serializable> params, Class<R> responseType) {
        return patch(url, params, EMPTY, responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> patch(String url, Object requestBody) {
        return patch(url, requestBody, EMPTY);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patch(String url, Object requestBody, Class<R> responseType) {
        return patch(url, requestBody, EMPTY, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> delete(String url) {
        return delete(url, EMPTY);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> delete(String url, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> delete(String url, Class<R> responseType) {
        return delete(url, EMPTY, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(),
                responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the observable
     */
    public Observable<String> delete(String url, Object requestBody) {
        return delete(url, requestBody, EMPTY);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> delete(String url, Object requestBody, Class<R> responseType) {
        return delete(url, requestBody, EMPTY, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> delete(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers))
                        .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(),
                responseType);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @return the http request completion
     */
    public Observable<Integer> download(String url, OutputStream output) {
        return download(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Integer> download(String url, File localFile) {
        return download(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     */
    public Observable<Integer> download(String url, Map<String, Serializable> params, OutputStream output) {
        return download(url, params, EMPTY, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Integer> download(String url, Map<String, Serializable> params, File localFile) {
        return download(url, params, EMPTY, localFile);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     * @return the http request completion
     */
    public Observable<Integer> download(String url, Map<String, Serializable> params, Map<String, String> headers,
            File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            return download(url, params, headers, new FileOutputStream(localFile));
        } catch (FileNotFoundException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @param output  the output
     * @return the http request completion
     */
    public Observable<Integer> download(String url, Map<String, Serializable> params, Map<String, String> headers,
            OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build();
        Observable<Integer> observable = Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("error at url: {}", request.url());
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == HttpCode.SUCCESS) {
                        byte[] bs = response.body().bytes();
                        output.write(bs);
                        emitter.onNext(bs.length);
                    } else {
                        emitter.onError(new HttpException(Strings.format("{0} error, code {1}, message {2}",
                                request.url(), response.code(), response.message())));
                    }
                }
            });
        });
        if (autoSubscribeOnIo) {
            return observable.subscribeOn(Schedulers.io());
        }
        return observable;
    }

    private Observable<String> observation(final Request request) {
        Observable<String> observable = Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("error at url: {}", request.url());
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == HttpCode.SUCCESS || isCodeSameAsSuccess(response)) {
                        emitter.onNext(response.body().string());
                    } else {
                        emitter.onError(new HttpException(Strings.format("{0} error, code {1}, message {2}",
                                request.url(), response.code(), response.message())));
                    }
                }
            });
        });
        if (autoSubscribeOnIo) {
            return observable.subscribeOn(Schedulers.io());
        }
        return observable;
    }

    private <R> Observable<R> observation(final Request request, final Class<R> responseType) {
        Observable<R> observable = Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("error at url: {}", request.url());
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == HttpCode.SUCCESS || isCodeSameAsSuccess(response)) {
                        emitter.onNext(deserialize(response, responseType));
                    } else {
                        emitter.onError(new HttpException(Strings.format("{0} error, code {1}, message {2}",
                                request.url(), response.code(), response.message())));
                    }
                }
            });
        });
        if (autoSubscribeOnIo) {
            return observable.subscribeOn(Schedulers.io());
        }
        return observable;
    }

}
