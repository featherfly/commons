package cn.featherfly.common.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * HttpSyncClientImpl.
 *
 * @author zhongj
 */
public class HttpSyncClientImpl extends AbstractHttpClient implements HttpSyncClient {

    /**
     * Instantiates a new http client.
     */
    public HttpSyncClientImpl() {
        super();
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpSyncClientImpl(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        super(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     */
    public HttpSyncClientImpl(HttpRequestConfig config, Map<String, String> headers) {
        super(config, headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpSyncClientImpl(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        super(config, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     */
    public HttpSyncClientImpl(HttpRequestConfig config) {
        super(config);
    }

    /**
     * Instantiates a new http client.
     *
     * @param headers the headers
     */
    public HttpSyncClientImpl(Map<String, String> headers) {
        super(headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpSyncClientImpl(OkHttpClient client, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        super(client, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param headers the headers
     */
    public HttpSyncClientImpl(OkHttpClient client, Map<String, String> headers) {
        super(client, headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public HttpSyncClientImpl(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        super(client, serialization, mediaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
            .get().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R get(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
            .get().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String head(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
            .head().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R head(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return request(new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
            .head().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .post(HttpUtils.createRequestBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R post(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .post(HttpUtils.createRequestBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
            .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R post(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(
            new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
            responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .put(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R put(String url, Map<String, Serializable> params, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .put(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
            .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R put(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(
            new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
            responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String patch(String url, Map<String, Serializable> params, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .patch(HttpUtils.createFormBody(params)).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R patch(String url, Map<String, Serializable> params, Map<String, String> headers,
        Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers))
            .patch(HttpUtils.createFormBody(params)).build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String patch(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
            .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R patch(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(
            new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
            responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delete(String url, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R delete(String url, Map<String, String> headers, Class<R> responseType) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delete(String url, Object requestBody, Map<String, String> headers) {
        return request(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
            .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R delete(String url, Object requestBody, Map<String, String> headers, Class<R> responseType) {
        return request(
            new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build(),
            responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer download(String url, Map<String, Serializable> params, Map<String, String> headers,
        OutputStream output) {
        Request request = new Request.Builder().url(HttpUtils.appendParams(url, params)).headers(createHeaders(headers))
            .get().build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                byte[] bs = response.body().bytes();
                output.write(bs);
                return bs.length;
            } else {
                throw new HttpErrorResponseException(
                    Strings.format("{0} error, code {1}, message {2}", request.url(), response.code(),
                        response.message()),
                    new HttpResponse(response.code(), response.body().bytes(),
                        HttpUtils.headersToMap(response.headers()), deserializeWithContentType,
                        response.receivedResponseAtMillis() - response.sentRequestAtMillis()));
            }
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream stream(HttpMethod httpMethod, String url, Map<String, Serializable> params,
        Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                return stream(new Request.Builder().url(HttpUtils.appendParams(url, params))
                    .headers(createHeaders(headers)).get().build());
            case POST:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers))
                    .post(HttpUtils.createRequestBody(params)).build());
            case PUT:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers))
                    .put(HttpUtils.createRequestBody(params)).build());
            case DELETE:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers)).delete().build());
            case HEAD:
                return stream(new Request.Builder().url(HttpUtils.appendParams(url, params))
                    .headers(createHeaders(headers)).head().build());
            case PATCH:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers))
                    .patch(HttpUtils.createFormBody(params)).build());
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream stream(HttpMethod httpMethod, String url, Object requestBody, Map<String, String> headers) {
        switch (httpMethod) {
            case GET:
                throw new HttpException("http get method can not send request body");
            //                return get(url, new HashMap<>(), headers);
            case POST:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                    .post(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
            case PUT:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                    .put(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
            case DELETE:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                    .delete(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
            case HEAD:
                throw new HttpException("http head method can not send request body");
            case PATCH:
                return stream(new Request.Builder().url(url).headers(createHeaders(headers, requestBody))
                    .patch(RequestBody.create(getMediaType(requestBody, headers), serialize(requestBody))).build());
            default:
                throw new HttpException("unsupport http method " + httpMethod.toString());
        }
    }

    // ********************************************************************
    //
    // ********************************************************************

    private String request(final Request request) {
        try {
            return getSuccessResponse(request).body().string();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    private <R> R request(final Request request, Class<R> responseType) {
        try {
            return deserialize(getSuccessResponse(request), responseType);
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    private InputStream stream(final Request request) {
        return getSuccessResponse(request).body().byteStream();
    }

}
