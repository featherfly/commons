package cn.featherfly.common.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
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
 * http client.
 *
 * @author zhongj
 */
public class HttpRxjavaClient extends AbstractHttpClient
        implements HttpClient<Observable<String>>, HttpDownloadClient<Observable<Integer>> {

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
        return request(httpMethod, url, params, new HashMap<>(), responseType);
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
        return request(httpMethod, url, requestBody, new HashMap<>(), responseType);
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
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    @Override
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
        return get(url, params, new HashMap<>(), responseType);
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
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    @Override
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
        return head(url, params, new HashMap<>(), responseType);
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
     * Post params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    @Override
    public Observable<String> post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createRequestBody(params)).build());
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
        return post(url, params, new HashMap<>(), responseType);
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
                .post(HttpUtils.createRequestBody(params)).build(), responseType);
    }

    /**
     * Post requestBody with medieType format.
     *
     * @param url         the url
     * @param requestBody the request body
     * @param headers     the headers
     * @return the observable
     */
    @Override
    public Observable<String> post(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
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
        return post(url, requestBody, new HashMap<>(), responseType);
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
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * Put params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    @Override
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
        return put(url, params, new HashMap<>(), responseType);
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
     * @param headers     the headers
     * @return the observable
     */
    @Override
    public Observable<String> put(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
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
        return put(url, requestBody, new HashMap<>(), responseType);
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
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * patch request params with FormBody.
     *
     * @param url     the url
     * @param params  the params
     * @param headers the headers
     * @return the observable
     */
    @Override
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
        return patch(url, params, new HashMap<>(), responseType);
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
     * @param headers     the headers
     * @return the observable
     */
    @Override
    public Observable<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
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
        return patch(url, requestBody, new HashMap<>(), responseType);
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
        return observation(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * Delete.
     *
     * @param url     the url
     * @param headers the headers
     * @return the observable
     */
    @Override
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
        return delete(url, new HashMap<>(), responseType);
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
     * @param headers     the headers
     * @return the observable
     */
    @Override
    public Observable<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return observation(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
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
        return delete(url, requestBody, new HashMap<>(), responseType);
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
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
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
