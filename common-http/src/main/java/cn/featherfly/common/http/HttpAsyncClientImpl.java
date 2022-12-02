package cn.featherfly.common.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http async client impl.
 *
 * @author zhongj
 */
public class HttpAsyncClientImpl extends AbstractHttpClient implements HttpAsyncClient {

    /**
     * Instantiates a new http async client.
     */
    public HttpAsyncClientImpl() {
        super();
    }

    /**
     * Instantiates a new http async client.
     *
     * @param config        the config
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpAsyncClientImpl(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param config  the config
     * @param headers the headers
     */
    public HttpAsyncClientImpl(HttpRequestConfig config, Map<String, String> headers) {
        super(config, headers);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpAsyncClientImpl(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        super(config, serialization, mediaType);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param config the config
     */
    public HttpAsyncClientImpl(HttpRequestConfig config) {
        super(config);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param headers the headers
     */
    public HttpAsyncClientImpl(Map<String, String> headers) {
        super(headers);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param client        the client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpAsyncClientImpl(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(client, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param client  the client
     * @param headers the headers
     */
    public HttpAsyncClientImpl(OkHttpClient client, Map<String, String> headers) {
        super(client, headers);
    }

    /**
     * Instantiates a new http async client.
     *
     * @param client        the client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public HttpAsyncClientImpl(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        super(client, serialization, mediaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> get(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> get(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).get().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> head(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> head(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(HttpUtils.appendParams(url, params))
                .headers(createHeaders(headers)).head().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> post(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createRequestBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> post(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .post(HttpUtils.createRequestBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> post(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> post(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> put(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> put(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> put(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> put(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> patch(String url, Map<String, Serializable> params,
            Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> patch(String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers))
                .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> patch(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> patch(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> delete(String url, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<String> delete(String url, Object requestBody, Map<String, String> headers) {
        return completetion(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpRequestCompletion<R> delete(String url, Object requestBody, Map<String, String> headers,
            Class<R> responseType) {
        return completetion(
                new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                        .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<Integer> download(String url, Map<String, Serializable> params,
            Map<String, String> headers, OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
                .get().build();
        HttpRequestCompletionImpl<Integer> completion = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    byte[] bs = response.body().bytes();
                    output.write(bs);
                    completion.setResponse(bs.length);
                } else {
                    completion.setHttpErrorResponse(new HttpErrorResponse(
                            Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                    response.message()),
                            new HttpResponse(response.code(), response.body().bytes(),
                                    HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                    response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                completion.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }
        });
        return completion;
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    private HttpRequestCompletion<String> completetion(final Request request) {
        HttpRequestCompletionImpl<String> completion = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (isSuccess(response)) {
                    completion.setResponse(response.body().string());
                } else {
                    completion.setHttpErrorResponse(new HttpErrorResponse(
                            Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                    response.message()),
                            new HttpResponse(response.code(), response.body().bytes(),
                                    HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                    response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // TODO 需要处理
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
                if (isSuccess(response)) {
                    completion.setResponse(deserialize(response, responseType));
                } else {
                    completion.setHttpErrorResponse(new HttpErrorResponse(
                            Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                                    response.message()),
                            new HttpResponse(response.code(), response.body().bytes(),
                                    HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                                    response.receivedResponseAtMillis() - response.sentRequestAtMillis())));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                completion.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }
        });
        return completion;
    }
}
