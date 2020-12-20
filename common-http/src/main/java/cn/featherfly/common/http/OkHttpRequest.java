package cn.featherfly.common.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.serialization.SerializationException;
import cn.featherfly.common.serialization.SerializationExceptionCode;
import cn.featherfly.common.serialization.Serializer;
import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * The Class OkHttpRequest.
 *
 * @author zhongj
 */
public class OkHttpRequest implements HttpRequest {

    private static final String JSON_STR = "application/json; charset=utf-8";
    private static final MediaType JSON = MediaType.parse(JSON_STR);

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Serialization serialization;

    private OkHttpClient client;

    private Serializer serializer;

    private MediaType mediaType;

    private boolean deserializeWithContentType = true;

    /**
     * Instantiates a new ok http request.
     *
     * @param config the config
     */
    public OkHttpRequest(HttpRequestConfig config) {
        this(config, Serialization.getDefault());
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param serialization the serialization
     */
    public OkHttpRequest(HttpRequestConfig config, Serialization serialization) {
        this(config, serialization, JSON);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param charset       the charset
     */
    public OkHttpRequest(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        this(new OkHttpClient.Builder().cache(new okhttp3.Cache(config.cacheDir, config.cacheMaxSize))
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS).build(), serialization, mediaType);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param okHttpClient  the ok http client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public OkHttpRequest(OkHttpClient okHttpClient, Serialization serialization, MediaType mediaType) {
        client = okHttpClient;
        if (serialization == null) {
            this.serialization = Serialization.getDefault();
        } else {
            this.serialization = serialization;
        }
        if (mediaType == null) {
            this.mediaType = JSON;
        } else {
            this.mediaType = mediaType;
        }
        serializer = getSerializer(this.mediaType, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, final Class<T> responseType) {
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            throw new IllegalArgumentException("send方法请求method只能是POST或者PUT");
        }
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);

        final String finalUrl = newUrl.toString();

        RequestBody body = RequestBody.create(mediaType, serializer.serialize(requestBody));
        Request request = new Request.Builder().url(finalUrl).method(method.toOkHttpCode(), body).build();

        HttpRequestCompletionImpl<T> hanlder = new HttpRequestCompletionImpl<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("网络错误的URL:" + newUrl.toString());
                hanlder.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    hanlder.setResponse(deserialize(response, responseType));
                } else {
                    hanlder.setHttpErrorResponse(new HttpErrorResponse(Strings.format(
                            "{0} error, code {1}, message {2}", finalUrl, response.code(), response.message())));
                }
            }
        });
        return hanlder;
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
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            throw new IllegalArgumentException("send方法请求method只能是POST或者PUT");
        }
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);
        url = newUrl.toString();
        RequestBody body = RequestBody.create(mediaType, serializer.serialize(requestBody));
        Request request = new Request.Builder().url(url).method(method.toOkHttpCode(), body).build();
        try {
            return deserialize(client.newCall(request).execute(), responseType);
        } catch (IOException e) {
            logger.error("网络错误的URL:" + url);
            errorListener.error(new HttpErrorResponse(e.getMessage()));
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, String> params,
            Map<String, String> headers, final Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);
        final String finalUrl = newUrl.toString();

        Request request = null;
        if (method == HttpMethod.GET || method == HttpMethod.HEAD) {
            request = new Request.Builder().url(url).method(method.toOkHttpCode(), null).build();
        } else {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            RequestBody requestBody = formBodyBuilder.build();
            request = new Request.Builder().url(url).method(method.toOkHttpCode(), requestBody).build();
        }
        HttpRequestCompletionImpl<T> hanlder = new HttpRequestCompletionImpl<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("网络错误的URL:" + newUrl.toString());
                hanlder.setHttpErrorResponse(new HttpErrorResponse(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    hanlder.setResponse(deserialize(response, responseType));
                } else {
                    hanlder.setHttpErrorResponse(new HttpErrorResponse(Strings.format(
                            "{0} error, code {1}, message {2}", finalUrl, response.code(), response.message())));
                }
            }
        });
        return hanlder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, String> params, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);
        url = newUrl.toString();
        Request request = null;
        if (method == HttpMethod.GET || method == HttpMethod.HEAD) {
            request = new Request.Builder().url(url).method(method.toOkHttpCode(), null).build();
        } else {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            RequestBody requestBody = formBodyBuilder.build();
            request = new Request.Builder().url(url).method(method.toOkHttpCode(), requestBody).build();
        }
        try {
            return deserialize(client.newCall(request).execute(), responseType);
        } catch (IOException e) {
            logger.error("网络错误的URL:" + url);
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
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, String> params,
            Class<T> responseType) {
        return sendCompletion(method, url, params, new HashMap<String, String>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, String> params, Class<T> responseType,
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

    private <T> T deserialize(Response response, final Class<T> responseType) throws IOException {
        Serializer serializer = null;
        if (deserializeWithContentType) {
            serializer = getSerializer(MediaType.parse(response.header("Content-Type", JSON_STR)), false);
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

    /**
     * Shutdown.
     */
    public void shutdown() {
        client.dispatcher().executorService().shutdown();
    }

    /**
     * get deserializeWithContentType value
     *
     * @return deserializeWithContentType
     */
    public boolean isDeserializeWithContentType() {
        return deserializeWithContentType;
    }

    /**
     * set deserializeWithContentType value
     *
     * @param deserializeWithContentType deserializeWithContentType
     */
    public void setDeserializeWithContentType(boolean deserializeWithContentType) {
        this.deserializeWithContentType = deserializeWithContentType;
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
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            throw new IllegalArgumentException("send方法请求method只能是POST或者PUT");
        }
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);

        final String finalUrl = newUrl.toString();

        RequestBody body = RequestBody.create(mediaType, serializer.serialize(requestBody));
        Request request = new Request.Builder().url(finalUrl).method(method.toOkHttpCode(), body).build();

        return Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("网络错误的URL:" + newUrl.toString());
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        emitter.onNext(deserialize(response, responseType));
                    } else {
                        emitter.onError(new HttpException(Strings.format("{0} error, code {1}, message {2}", finalUrl,
                                response.code(), response.message())));
                    }
                }
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, String> params,
            Map<String, String> headers, Class<T> responseType) {
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, params, headers, responseType);

        final String finalUrl = newUrl.toString();
        final Request request;
        if (method == HttpMethod.GET || method == HttpMethod.HEAD) {
            request = new Request.Builder().url(finalUrl).method(method.toOkHttpCode(), null).build();
        } else {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            RequestBody requestBody = formBodyBuilder.build();
            request = new Request.Builder().url(finalUrl).method(method.toOkHttpCode(), requestBody).build();
        }

        return Observable.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.error("网络错误的URL:" + newUrl.toString());
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        emitter.onNext(deserialize(response, responseType));
                    } else {
                        emitter.onError(new HttpException(Strings.format("{0} error, code {1}, message {2}", finalUrl,
                                response.code(), response.message())));
                    }
                }
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, String> params,
            Class<T> responseType) {
        return sendObservable(method, url, params, new HashMap<>(), responseType);
    }
}
