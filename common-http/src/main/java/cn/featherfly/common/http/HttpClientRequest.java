package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.serialization.Serialization;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * The Class OkHttpRequest.
 *
 * @author zhongj
 */
public class HttpClientRequest implements HttpRequest {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpClient client;
    private HttpAsyncClient asyncClient;
    private HttpRxjavaClient rxjavaClient;

    /**
     * Instantiates a new ok http request.
     *
     * @param config the config
     */
    public HttpClientRequest(HttpRequestConfig config) {
        this(config, Serialization.getDefault());
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config  the config
     * @param headers the headers
     */
    public HttpClientRequest(HttpRequestConfig config, Map<String, String> headers) {
        this(config, headers, Serialization.getDefault(), HttpUtils.JSON_MEDIA_TYPE);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param serialization the serialization
     */
    public HttpClientRequest(HttpRequestConfig config, Serialization serialization) {
        this(config, serialization, HttpUtils.JSON_MEDIA_TYPE);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClientRequest(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        this(config, new HashMap<>(), serialization, mediaType);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClientRequest(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        this(new OkHttpClient.Builder().cache(new okhttp3.Cache(config.cacheDir, config.cacheMaxSize))
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS).build(), headers, serialization, mediaType);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param okHttpClient  the ok http client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClientRequest(OkHttpClient okHttpClient, Serialization serialization, MediaType mediaType) {
        this(okHttpClient, new HashMap<>(), serialization, mediaType);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param okHttpClient  the ok http client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpClientRequest(OkHttpClient okHttpClient, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        client = new HttpClient(okHttpClient, headers, serialization, mediaType);
        asyncClient = new HttpAsyncClient(okHttpClient, headers, serialization, mediaType);
        rxjavaClient = new HttpRxjavaClient(okHttpClient, headers, serialization, mediaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, final Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);
        final String finalUrl = newUrl.toString();
        return asyncClient.request(method, finalUrl, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        return send(method, url, requestBody, headers, responseType, errorListener, -1l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener, long requestTimeoutSeconds) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);
        final String finalUrl = newUrl.toString();
        try {
            return client.request(method, finalUrl, requestBody, headers, responseType);
        } catch (Exception e) {
            errorListener.error(new HttpErrorResponse(e.getMessage()));
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, final Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);
        final String finalUrl = newUrl.toString();
        return asyncClient.request(method, finalUrl, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);
        final String finalUrl = newUrl.toString();
        try {
            return client.request(method, finalUrl, params, headers, responseType);
        } catch (Exception e) {
            errorListener.error(new HttpErrorResponse(e.getMessage()));
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Class<T> responseType) {
        return sendCompletion(method, url, requestBody, new HashMap<String, String>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Class<T> responseType,
            ErrorListener errorListener) {
        return send(method, url, requestBody, new HashMap<String, String>(), responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType) {
        return sendCompletion(method, url, params, new HashMap<String, String>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody, Class<T> responseType) {
        return sendObservable(method, url, requestBody, new HashMap<>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);
        final String finalUrl = newUrl.toString();
        return rxjavaClient.request(method, finalUrl, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);
        final String finalUrl = newUrl.toString();
        return rxjavaClient.request(method, finalUrl, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType) {
        return sendObservable(method, url, params, new HashMap<>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Class<T> responseType,
            ErrorListener errorListener) {
        return send(method, url, params, new HashMap<String, String>(), responseType, errorListener);
    }

    /**
     * Pre send.
     *
     * @param <T>          the generic type
     * @param method       the method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     */
    protected <T> void preSend(HttpMethod method, StringBuilder url, Object params, Map<String, String> headers,
            Class<T> responseType) {
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        client.shutdown();
    }

    /**
     * Gets the http client.
     *
     * @return the http client
     */
    public HttpClient getHttpClient() {
        return client;
    }
}
