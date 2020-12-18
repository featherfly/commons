package cn.featherfly.common.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.serialization.Serializer;
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

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Serialization serialization;

    private Charset charset;

    private OkHttpClient client;

    private Serializer jsonSerializer;

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
        this(config, serialization, StandardCharsets.UTF_8);
    }

    /**
     * Instantiates a new ok http request.
     *
     * @param config        the config
     * @param serialization the serialization
     * @param charset       the charset
     */
    public OkHttpRequest(HttpRequestConfig config, Serialization serialization, Charset charset) {
        client = new OkHttpClient.Builder().cache(new okhttp3.Cache(config.cacheDir, config.cacheMaxSize))
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS).build();
        if (serialization == null) {
            this.serialization = Serialization.getDefault();
        } else {
            this.serialization = serialization;
        }
        this.charset = charset;
        try {
            jsonSerializer = this.serialization.getSerializer(new MimeType(JSON.type(), JSON.subtype()));
        } catch (MimeTypeParseException e) {
        }
    }

    /**
     * Post body.
     *
     * @param <R>          the generic type
     * @param <T>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     */
    public <R, T> void postBody(String url, R requestBody, Map<String, String> headers, Class<T> responseType) {
        send(HttpMethod.POST, url, requestBody, headers, responseType);
    }

    /**
     * Put body.
     *
     * @param <R>          the generic type
     * @param <T>          the generic type
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     */
    public <R, T> void putBody(String url, R requestBody, Map<String, String> headers, Class<T> responseType) {
        send(HttpMethod.PUT, url, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestHandler<T> send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            final Class<T> responseType) {
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            throw new IllegalArgumentException("send方法请求method只能是POST或者PUT");
        }
        final StringBuilder newUrl = new StringBuilder(url);
        preSend(method, newUrl, requestBody, headers, responseType);

        final String finalUrl = newUrl.toString();

        RequestBody body = RequestBody.create(JSON, jsonSerializer.serialize(requestBody));
        Request request = new Request.Builder().url(finalUrl).method(method.toOkHttpCode(), body).build();

        HttpRequestHandlerImpl<T> hanlder = new HttpRequestHandlerImpl<>();

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
        RequestBody body = RequestBody.create(JSON, jsonSerializer.serialize(requestBody));
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
    public <T> HttpRequestHandler<T> send(HttpMethod method, String url, Map<String, String> params,
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
        HttpRequestHandlerImpl<T> hanlder = new HttpRequestHandlerImpl<>();
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
        return send(method, url, params, headers, responseType, errorListener, -1l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, String> params, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener, long requestTimeoutSeconds) {
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
    public <R, T> HttpRequestHandler<T> send(HttpMethod method, String url, R requestBody, Class<T> responseType) {
        return send(method, url, requestBody, new HashMap<String, String>(), responseType);
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
    public <T> HttpRequestHandler<T> send(HttpMethod method, String url, Map<String, String> params,
            Class<T> responseType) {
        return send(method, url, params, new HashMap<String, String>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, String> params, Class<T> responseType,
            ErrorListener errorListener, long requestTimeoutSeconds) {
        return send(method, url, params, new HashMap<String, String>(), responseType, errorListener,
                requestTimeoutSeconds);
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
        //    TODO 根据response的content-type来选择
        return jsonSerializer.deserialize(response.body().bytes(), responseType);
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        client.dispatcher().executorService().shutdown();
    }
}
