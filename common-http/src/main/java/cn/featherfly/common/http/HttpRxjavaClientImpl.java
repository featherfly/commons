package cn.featherfly.common.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

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
 * HttpRxjavaClientImpl.
 *
 * @author zhongj
 */
public class HttpRxjavaClientImpl extends AbstractHttpClient implements HttpRxjavaClient {

    private boolean autoSubscribeOnIo = true;

    /**
     * Instantiates a new http rxjava client.
     */
    public HttpRxjavaClientImpl() {
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
    public HttpRxjavaClientImpl(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config  the config
     * @param headers the headers
     */
    public HttpRxjavaClientImpl(HttpRequestConfig config, Map<String, String> headers) {
        super(config, headers);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClientImpl(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        super(config, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param config the config
     */
    public HttpRxjavaClientImpl(HttpRequestConfig config) {
        super(config);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param headers the headers
     */
    public HttpRxjavaClientImpl(Map<String, String> headers) {
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
    public HttpRxjavaClientImpl(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(client, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param client  the client
     * @param headers the headers
     */
    public HttpRxjavaClientImpl(OkHttpClient client, Map<String, String> headers) {
        super(client, headers);
    }

    /**
     * Instantiates a new http rxjava client.
     *
     * @param client        the client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpRxjavaClientImpl(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        super(client, serialization, mediaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoSubscribeOnIo() {
        return autoSubscribeOnIo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutoSubscribeOnIo(boolean autoSubscribeOnIo) {
        this.autoSubscribeOnIo = autoSubscribeOnIo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createRequestBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createRequestBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> post(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> put(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> delete(String url, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Observable<R> delete(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<Integer> download(String url, Map<String, Serializable> params, Map<String, String> headers,
            OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build();
        Observable<Integer> observable = Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("error at url: {}", request.url());
                    emitter.onError(new HttpException(e));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        byte[] bs = response.body().bytes();
                        output.write(bs);
                        emitter.onNext(bs.length);
                    } else {
                        emitter.onError(new HttpErrorResponseException(
                                Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                        response.message()),
                                new HttpResponse(response.code(), response.body().bytes(),
                                        HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                        response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
                    }
                }
            });
        });
        if (autoSubscribeOnIo) {
            return observable.subscribeOn(Schedulers.io());
        }
        return observable;
    }

    // ********************************************************************
    //
    // ********************************************************************

    private Observable<String> observation(final Request request) {
        Observable<String> observable = Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("error at url: {}", request.url());
                    emitter.onError(new HttpException(e));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (isSuccess(response)) {
                        emitter.onNext(response.body().string());
                    } else {
                        emitter.onError(new HttpErrorResponseException(
                                Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                        response.message()),
                                new HttpResponse(response.code(), response.body().bytes(),
                                        HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                        response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
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
                    emitter.onError(new HttpException(e));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (isSuccess(response)) {
                        emitter.onNext(deserialize(response, responseType));
                    } else {
                        emitter.onError(new HttpErrorResponseException(
                                Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                        response.message()),
                                new HttpResponse(response.code(), response.body().bytes(),
                                        HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                        response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
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
