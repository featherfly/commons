package cn.featherfly.common.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.serialization.SerializationException;
import cn.featherfly.common.serialization.SerializationExceptionCode;
import cn.featherfly.common.serialization.Serializer;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
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
public class HttpClient {

    private static final Map<String, String> EMPTY = new HashMap<>();

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean deserializeWithContentType;

    private OkHttpClient client;

    private Serialization serialization;

    private Serializer serializer;

    private MediaType mediaType;

    private Headers headers;

    private boolean autoSubscribeOnIo = true;

    private Map<String, String> headersMap = new HashMap<>();

    /**
     * Instantiates a new http client.
     */
    public HttpClient() {
        this(null);
    }

    /**
     * Instantiates a new http client.
     *
     * @param headers the headers
     */
    public HttpClient(Map<String, String> headers) {
        this(null, headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client  the client
     * @param headers the headers
     */
    public HttpClient(OkHttpClient client, Map<String, String> headers) {
        this(client, null, null);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client        the client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClient(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        this(client, null, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client        the client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClient(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        init(client, headers, serialization, mediaType);
    }

    /**
     * Inits the.
     *
     * @param client        the client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    protected void init(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        if (client == null) {
            this.client = new OkHttpClient.Builder()
                    .cache(new okhttp3.Cache(org.apache.commons.io.FileUtils.getTempDirectory(), 1024 * 10))
                    .connectTimeout(60, TimeUnit.SECONDS).build();
        } else {
            this.client = client;
        }
        if (serialization == null) {
            this.serialization = Serialization.getDefault();
        } else {
            this.serialization = serialization;
        }
        if (mediaType == null) {
            this.mediaType = HttpUtils.JSON_MEDIA_TYPE;
        } else {
            this.mediaType = mediaType;
        }
        serializer = getSerializer(this.mediaType, true);

        if (headers != null) {
            headersMap.putAll(headers);
        }
        this.headers = HttpUtils.createHeaders(headersMap);

        //        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        client.dispatcher().executorService().shutdown();
    }

    /**
     * get deserializeWithContentType value.
     *
     * @return deserializeWithContentType
     */
    public boolean isDeserializeWithContentType() {
        return deserializeWithContentType;
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
     * set deserializeWithContentType value.
     *
     * @param deserializeWithContentType deserializeWithContentType
     */
    public void setDeserializeWithContentType(boolean deserializeWithContentType) {
        this.deserializeWithContentType = deserializeWithContentType;
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return response string
     */
    public String request(HttpMethod httpMethod, String url) {
        return request(httpMethod, url, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return response string
     */
    public String request(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return request(httpMethod, url, params, EMPTY);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return response string
     */
    public String request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
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
     * @return responseType instance
     */
    public <R> R request(HttpMethod httpMethod, String url, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R request(HttpMethod httpMethod, String url, Map<String, Serializable> params, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R request(HttpMethod httpMethod, String url, Map<String, Serializable> params,
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
     * @return response string
     */
    public String request(HttpMethod httpMethod, String url, Object requestBody) {
        return request(httpMethod, url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response string
     */
    public String request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
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
    public <R> R request(HttpMethod httpMethod, String url, Object requestBody, Class<R> responseType) {
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
    public <R> R request(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers,
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
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url) {
        return requestCompletion(httpMethod, url, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url,
            Map<String, Serializable> params) {
        return requestCompletion(httpMethod, url, params, EMPTY);
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @param headers    the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url,
            Map<String, Serializable> params, Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                return getCompletion(url, params, headers);
            case POST:
                return postCompletion(url, params, headers);
            case PUT:
                return putCompletion(url, params, headers);
            case DELETE:
                return deleteCompletion(url, headers);
            case HEAD:
                return headCompletion(url, params, headers);
            case PATCH:
                return patchCompletion(url, params, headers);
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
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Class<R> responseType) {
        return requestCompletion(httpMethod, url, new HashMap<>(), responseType);
    }

    /**
     * request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param httpMethod   the http method
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url,
            Map<String, Serializable> params, Class<R> responseType) {
        return requestCompletion(httpMethod, url, params, EMPTY, responseType);
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
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url,
            Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                return getCompletion(url, params, headers, responseType);
            case POST:
                return postCompletion(url, params, headers, responseType);
            case PUT:
                return putCompletion(url, params, headers, responseType);
            case DELETE:
                return deleteCompletion(url, headers, responseType);
            case HEAD:
                return headCompletion(url, params, headers, responseType);
            case PATCH:
                return patchCompletion(url, params, headers, responseType);
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
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url, Object requestBody) {
        return requestCompletion(httpMethod, url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param httpMethod  the http method
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return postCompletion(url, requestBody, headers);
            case PUT:
                return putCompletion(url, requestBody, headers);
            case DELETE:
                return deleteCompletion(url, requestBody, headers);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patchCompletion(url, requestBody, headers);
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
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
            Class<R> responseType) {
        return requestCompletion(httpMethod, url, requestBody, EMPTY, responseType);
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
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> requestCompletion(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return postCompletion(url, requestBody, headers, responseType);
            case PUT:
                return putCompletion(url, requestBody, headers, responseType);
            case DELETE:
                return deleteCompletion(url, requestBody, headers, responseType);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patchCompletion(url, requestBody, headers, responseType);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url) {
        return requestObservable(httpMethod, url, new HashMap<>());
    }

    /**
     * Request.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param params     the params
     * @return the observable
     */
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params) {
        return requestObservable(httpMethod, url, params, EMPTY);
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
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                return getObservable(url, params, headers);
            case POST:
                return postObservable(url, params, headers);
            case PUT:
                return putObservable(url, params, headers);
            case DELETE:
                return deleteObservable(url, headers);
            case HEAD:
                return headObservable(url, params, headers);
            case PATCH:
                return patchObservable(url, params, headers);
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
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Class<R> responseType) {
        return requestObservable(httpMethod, url, new HashMap<>(), responseType);
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
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return requestObservable(httpMethod, url, params, EMPTY, responseType);
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
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                return getObservable(url, params, headers, responseType);
            case POST:
                return postObservable(url, params, headers, responseType);
            case PUT:
                return putObservable(url, params, headers, responseType);
            case DELETE:
                return deleteObservable(url, headers, responseType);
            case HEAD:
                return headObservable(url, params, headers, responseType);
            case PATCH:
                return patchObservable(url, params, headers, responseType);
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
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Object requestBody) {
        return requestObservable(httpMethod, url, requestBody, EMPTY);
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
    public Observable<String> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return postObservable(url, requestBody, headers);
            case PUT:
                return putObservable(url, requestBody, headers);
            case DELETE:
                return deleteObservable(url, requestBody, headers);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patchObservable(url, requestBody, headers);
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
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
            Class<R> responseType) {
        return requestObservable(httpMethod, url, requestBody, EMPTY, responseType);
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
    public <R> Observable<R> requestObservable(HttpMethod httpMethod, String url, Object requestBody,
            Map<String, String> headers, Class<R> responseType) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return postObservable(url, requestBody, headers, responseType);
            case PUT:
                return putObservable(url, requestBody, headers, responseType);
            case DELETE:
                return deleteObservable(url, requestBody, headers, responseType);
            case HEAD:
                throw new HttpException("http head method can not send request body");
            //                return head(url, new HashMap<>(), headers);
            case PATCH:
                return patchObservable(url, requestBody, headers, responseType);
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    /**
     * get request.
     *
     * @param url the url
     * @return response string
     */
    public String get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * get request with params.
     *
     * @param url    the url
     * @param params the params
     * @return response string
     */
    public String get(String url, Map<String, Serializable> params) {
        return get(url, params, EMPTY);
    }

    /**
     * get request with params.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response string
     */
    public String get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build());
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R get(String url, Class<R> responseType) {
        return get(url, new HashMap<>(), responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R get(String url, Map<String, Serializable> params, Class<R> responseType) {
        return get(url, params, EMPTY, responseType);
    }

    /**
     * get request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R get(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build(), responseType);
    }

    /**
     * get request.
     *
     * @param url the url
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url) {
        return getCompletion(url, new HashMap<>());
    }

    /**
     * get request.
     *
     * @param url    the url
     * @param params the params
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params) {
        return getCompletion(url, params, EMPTY);
    }

    /**
     * get request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the completion
     */
    public HttpRequestCompletion<String> getCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build());
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Class<R> responseType) {
        return getCompletion(url, new HashMap<>(), responseType);
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return getCompletion(url, params, EMPTY, responseType);
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> getCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build(), responseType);
    }

    /**
     * get request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> getObservable(String url) {
        return getObservable(url, new HashMap<>());
    }

    /**
     * get request.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> getObservable(String url, Map<String, Serializable> params) {
        return getObservable(url, params, EMPTY);
    }

    /**
     * get request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> getObservable(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build());
    }

    /**
     * get request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> getObservable(String url, Class<R> responseType) {
        return getObservable(url, new HashMap<>(), responseType);
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
    public <R> Observable<R> getObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return getObservable(url, params, EMPTY, responseType);
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
    public <R> Observable<R> getObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build(), responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return response string
     */
    public String head(String url) {
        return head(url, new HashMap<>());
    }

    /**
     * head request with params.
     *
     * @param url    the url
     * @param params the params
     * @return response string
     */
    public String head(String url, Map<String, Serializable> params) {
        return head(url, params, EMPTY);
    }

    /**
     * head request with params.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response string
     */
    public String head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .head().build());
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R head(String url, Class<R> responseType) {
        return head(url, new HashMap<>(), responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R head(String url, Map<String, Serializable> params, Class<R> responseType) {
        return head(url, params, EMPTY, responseType);
    }

    /**
     * head request with params and deserialize response .
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .head().build(), responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url) {
        return headCompletion(url, new HashMap<>());
    }

    /**
     * head request.
     *
     * @param url    the url
     * @param params the params
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params) {
        return headCompletion(url, params, EMPTY);
    }

    /**
     * head request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the completion
     */
    public HttpRequestCompletion<String> headCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build());
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Class<R> responseType) {
        return headCompletion(url, new HashMap<>(), responseType);
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return headCompletion(url, params, EMPTY, responseType);
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the completion
     */
    public <R> HttpRequestCompletion<R> headCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build(), responseType);
    }

    /**
     * head request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> headObservable(String url) {
        return headObservable(url, new HashMap<>());
    }

    /**
     * head request.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> headObservable(String url, Map<String, Serializable> params) {
        return headObservable(url, params, EMPTY);
    }

    /**
     * head request.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> headObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .head().build());
    }

    /**
     * head request.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the observable
     */
    public <R> Observable<R> headObservable(String url, Class<R> responseType) {
        return headObservable(url, new HashMap<>(), responseType);
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
    public <R> Observable<R> headObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return headObservable(url, params, EMPTY, responseType);
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
    public <R> Observable<R> headObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .head().build(), responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return response string
     */
    public String post(String url) {
        return post(url, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response string
     */
    public String post(String url, Map<String, Serializable> params) {
        return post(url, params, EMPTY);
    }

    /**
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response string
     */
    public String post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Post params with FormBody and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return response string
     */
    public <R> R post(String url, Map<String, Serializable> params, Class<R> responseType) {
        return post(url, params, EMPTY, responseType);
    }

    /**
     * Post params with FormBody and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return response string
     */
    public <R> R post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response string
     */
    public String post(String url, Object requestBody) {
        return post(url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response string
     */
    public String post(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R post(String url, Object requestBody, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url) {
        return postCompletion(url, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params) {
        return postCompletion(url, params, EMPTY);
    }

    /**
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Post params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return postCompletion(url, params, EMPTY, responseType);
    }

    /**
     * Post params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Object requestBody) {
        return postCompletion(url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> postCompletion(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody, Class<R> responseType) {
        return postCompletion(url, requestBody, EMPTY, responseType);
    }

    /**
     * Post requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> postCompletion(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * post request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> postObservable(String url) {
        return postObservable(url, new HashMap<>());
    }

    /**
     * Post params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> postObservable(String url, Map<String, Serializable> params) {
        return postObservable(url, params, EMPTY);
    }

    /**
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> postObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
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
    public <R> Observable<R> postObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return postObservable(url, params, EMPTY, responseType);
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
    public <R> Observable<R> postObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
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
    public Observable<String> postObservable(String url, Object requestBody) {
        return postObservable(url, requestBody, EMPTY);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> postObservable(String url, Object requestBody, Map<String, String> headers) {
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
    public <R> Observable<R> postObservable(String url, Object requestBody, Class<R> responseType) {
        return postObservable(url, requestBody, EMPTY, responseType);
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
    public <R> Observable<R> postObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return response string
     */
    public String put(String url) {
        return put(url, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response string
     */
    public String put(String url, Map<String, Serializable> params) {
        return put(url, params, EMPTY);
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response string
     */
    public String put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Put params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R put(String url, Map<String, Serializable> params, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R put(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response string
     */
    public String put(String url, Object requestBody) {
        return put(url, requestBody, EMPTY);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response string
     */
    public String put(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the responset type
     * @return responseType instance
     */
    public <R> R put(String url, Object requestBody, Class<R> responseType) {
        return put(url, requestBody, EMPTY, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the responset type
     * @return responseType instance
     */
    public <R> R put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url) {
        return putCompletion(url, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params) {
        return putCompletion(url, params, EMPTY);
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * Put params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return putCompletion(url, params, EMPTY, responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Object requestBody) {
        return putCompletion(url, requestBody, EMPTY);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> putCompletion(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody, Class<R> responseType) {
        return putCompletion(url, requestBody, EMPTY, responseType);
    }

    /**
     * Put requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> putCompletion(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Put.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> putObservable(String url) {
        return putObservable(url, new HashMap<>());
    }

    /**
     * Put params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> putObservable(String url, Map<String, Serializable> params) {
        return putObservable(url, params, EMPTY);
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> putObservable(String url, Map<String, Serializable> params, Map<String, String> headers) {
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
    public <R> Observable<R> putObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return putObservable(url, params, EMPTY, responseType);
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
    public <R> Observable<R> putObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
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
    public Observable<String> putObservable(String url, Object requestBody) {
        return putObservable(url, requestBody, EMPTY);
    }

    /**
     * Put requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> putObservable(String url, Object requestBody, Map<String, String> headers) {
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
    public <R> Observable<R> putObservable(String url, Object requestBody, Class<R> responseType) {
        return putObservable(url, requestBody, EMPTY, responseType);
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
    public <R> Observable<R> putObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return response string
     */
    public String patch(String url) {
        return patch(url, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return response string
     */
    public String patch(String url, Map<String, Serializable> params) {
        return patch(url, params, EMPTY);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return response string
     */
    public String patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R patch(String url, Map<String, Serializable> params, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response string
     */
    public String patch(String url, Object requestBody) {
        return patch(url, requestBody, EMPTY);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response string
     */
    public String patch(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the responset type
     * @return responseType instance
     */
    public <R> R patch(String url, Object requestBody, Class<R> responseType) {
        return patch(url, requestBody, EMPTY, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the responset type
     * @return responseType instance
     */
    public <R> R patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url) {
        return patchCompletion(url, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params) {
        return patchCompletion(url, params, EMPTY);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
            Class<R> responseType) {
        return patchCompletion(url, params, EMPTY, responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Object requestBody) {
        return patchCompletion(url, requestBody, EMPTY);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> patchCompletion(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody, Class<R> responseType) {
        return patchCompletion(url, requestBody, EMPTY, responseType);
    }

    /**
     * patch request requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> patchCompletion(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * patch request.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> patchObservable(String url) {
        return patchObservable(url, new HashMap<>());
    }

    /**
     * patch request params with FormBody.
     *
     * @param url    the url
     * @param params the params
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Map<String, Serializable> params) {
        return patchObservable(url, params, EMPTY);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
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
    public <R> Observable<R> patchObservable(String url, Map<String, Serializable> params, Class<R> responseType) {
        return patchObservable(url, params, EMPTY, responseType);
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
    public <R> Observable<R> patchObservable(String url, Map<String, Serializable> params, Map<String, String> headers,
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
    public Observable<String> patchObservable(String url, Object requestBody) {
        return patchObservable(url, requestBody, EMPTY);
    }

    /**
     * patch request requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> patchObservable(String url, Object requestBody, Map<String, String> headers) {
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
    public <R> Observable<R> patchObservable(String url, Object requestBody, Class<R> responseType) {
        return patchObservable(url, requestBody, EMPTY, responseType);
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
    public <R> Observable<R> patchObservable(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(), responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return response string
     */
    public String delete(String url) {
        return delete(url, EMPTY);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return response string
     */
    public String delete(String url, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return response string
     */
    public <R> R delete(String url, Class<R> responseType) {
        return delete(url, EMPTY, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return response string
     */
    public <R> R delete(String url, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(), responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return response string
     */
    public String delete(String url, Object requestBody) {
        return delete(url, requestBody, EMPTY);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return response string
     */
    public String delete(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
                .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return responseType instance
     */
    public <R> R delete(String url, Object requestBody, Class<R> responseType) {
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
     * @return responseType instance
     */
    public <R> R delete(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(
                new Request.Builder().url(url).headers(createHeaders(headers))
                        .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(),
                responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url) {
        return deleteCompletion(url, EMPTY);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Class<R> responseType) {
        return deleteCompletion(url, EMPTY, responseType);
    }

    /**
     * Delete request and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(),
                responseType);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody) {
        return deleteCompletion(url, requestBody, EMPTY);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the http request completion
     */
    public HttpRequestCompletion<String> deleteCompletion(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build());
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody, Class<R> responseType) {
        return deleteCompletion(url, requestBody, EMPTY, responseType);
    }

    /**
     * Delete requestBody with medieType format and deserialize response.
     *
     * @param <R>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @return the http request completion
     */
    public <R> HttpRequestCompletion<R> deleteCompletion(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(
                new Request.Builder().url(url).headers(createHeaders(headers))
                        .delete(RequestBody.create(mediaType, serializer.serialize(requestBody))).build(),
                responseType);
    }

    /**
     * Delete.
     *
     * @param url the url
     * @return the observable
     */
    public Observable<String> deleteObservable(String url) {
        return deleteObservable(url, EMPTY);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the observable
     */
    public Observable<String> deleteObservable(String url, Map<String, String> headers) {
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
    public <R> Observable<R> deleteObservable(String url, Class<R> responseType) {
        return deleteObservable(url, EMPTY, responseType);
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
    public <R> Observable<R> deleteObservable(String url, Map<String, String> headers, Class<R> responseType) {
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
    public Observable<String> deleteObservable(String url, Object requestBody) {
        return deleteObservable(url, requestBody, EMPTY);
    }

    /**
     * Delete requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    public Observable<String> deleteObservable(String url, Object requestBody, Map<String, String> headers) {
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
    public <R> Observable<R> deleteObservable(String url, Object requestBody, Class<R> responseType) {
        return deleteObservable(url, requestBody, EMPTY, responseType);
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
    public <R> Observable<R> deleteObservable(String url, Object requestBody, Map<String, String> headers,
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
     */
    public void download(String url, OutputStream output) {
        download(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     */
    public void download(String url, File localFile) {
        download(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     */
    public void download(String url, Map<String, Serializable> params, OutputStream output) {
        download(url, params, EMPTY, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     */
    public void download(String url, Map<String, Serializable> params, File localFile) {
        download(url, params, EMPTY, localFile);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param headers   the headers
     * @param localFile the local file
     */
    public void download(String url, Map<String, Serializable> params, Map<String, String> headers, File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            download(url, params, headers, new FileOutputStream(localFile));
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
     */
    public void download(String url, Map<String, Serializable> params, Map<String, String> headers,
            OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build();
        try {
            Response response = client.newCall(request).execute();
            output.write(response.body().bytes());
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     * @return the http request completion
     */
    public HttpRequestCompletion<Integer> downloadCompletion(String url, OutputStream output) {
        return downloadCompletion(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     * @return the http request completion
     */
    public HttpRequestCompletion<Integer> downloadCompletion(String url, File localFile) {
        return downloadCompletion(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     * @return the http request completion
     */
    public HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            OutputStream output) {
        return downloadCompletion(url, params, EMPTY, output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     * @return the http request completion
     */
    public HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            File localFile) {
        return downloadCompletion(url, params, EMPTY, localFile);
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
    public HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            return downloadCompletion(url, params, headers, new FileOutputStream(localFile));
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
    public HttpRequestCompletion<Integer> downloadCompletion(String url, Map<String, Serializable> params,
            Map<String, String> headers, OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build();
        HttpRequestCompletionImpl<Integer> completion = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bs = response.body().bytes();
                output.write(bs);
                completion.setResponse(bs.length);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                completion.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }
        });
        return completion;
    }

    private String request(final Request request) {
        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    private <R> R request(final Request request, Class<R> responseType) {
        try {
            return deserialize(client.newCall(request).execute(), responseType);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    private HttpRequestCompletion<String> completetion(final Request request) {
        HttpRequestCompletionImpl<String> completion = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                completion.setResponse(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                completion.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }
        });
        return completion;
    }

    private <R> HttpRequestCompletion<R> completetion(final Request request, final Class<R> responseType) {
        HttpRequestCompletionImpl<R> completion = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                completion.setResponse(deserialize(response, responseType));
            }

            @Override
            public void onFailure(Call call, IOException e) {
                completion.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }
        });
        return completion;
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
                    if (response.code() == 200) {
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
                    if (response.code() == 200) {
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

    private <T> T deserialize(Response response, final Class<T> responseType) throws IOException {
        Serializer serializer = null;
        if (deserializeWithContentType) {
            MediaType mediaType = response.body().contentType();
            if (mediaType == null) {
                mediaType = HttpUtils.JSON_MEDIA_TYPE;
            }
            serializer = getSerializer(mediaType, false);
        }
        if (serializer == null) {
            serializer = this.serializer;
        }
        return serializer.deserialize(response.body().bytes(), responseType);
    }

    private Serializer getSerializer(MediaType mediaType, boolean throwExceptionNoSerializer) {
        Serializer serializer = null;
        try {
            MimeType mimeType = new MimeType(mediaType.type(), mediaType.subtype());
            serializer = serialization.getSerializer(mimeType);
            if (serializer == null && throwExceptionNoSerializer) {
                throw new SerializationException(
                        SerializationExceptionCode.createNoSerializerForMimeTypeCode(mimeType.getBaseType()));
            }
            logger.warn("no serializer found for content-type {}", mimeType.getBaseType());
        } catch (MimeTypeParseException e) {
        }
        return serializer;
    }

    private Headers createHeaders(Map<String, String> headers) {
        if (Lang.isEmpty(headers)) {
            return this.headers;
        } else {
            return HttpUtils.createHeaders(headers, headersMap);
        }
    }
}
