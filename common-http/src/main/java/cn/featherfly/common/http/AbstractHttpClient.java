package cn.featherfly.common.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.serialization.SerializationException;
import cn.featherfly.common.serialization.SerializationExceptionCode;
import cn.featherfly.common.serialization.Serializer;
import okhttp3.Dispatcher;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http client.
 *
 * @author zhongj
 */
public abstract class AbstractHttpClient {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The client. */
    protected OkHttpClient client;

    /** The deserialize with content type. */
    protected boolean deserializeWithContentType;

    /** The serialization. */
    protected Serialization serialization;

    private Serializer serializer;

    private MediaType mediaType;

    /** The text plain. */
    protected MediaType textPlain;

    /** The headers. */
    protected Headers headers;

    /** The headers map. */
    protected Map<String, String> headersMap = new HashMap<>();

    private Set<Integer> codeSameAsSuccess = new HashSet<>();

    protected int downloadProgressPerSize = 1024 * 256;

    /**
     * Instantiates a new http client.
     */
    public AbstractHttpClient() {
        this(new HashMap<>());
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     */
    public AbstractHttpClient(HttpRequestConfig config) {
        this(config, null);
    }

    /**
     * Instantiates a new http client.
     *
     * @param headers the headers
     */
    public AbstractHttpClient(Map<String, String> headers) {
        this(new HttpRequestConfig(), headers);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     */
    public AbstractHttpClient(HttpRequestConfig config, Map<String, String> headers) {
        this(config, null, null);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public AbstractHttpClient(HttpRequestConfig config, Serialization serialization, MediaType mediaType) {
        this(config, null, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param config the config
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public AbstractHttpClient(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        init(config, headers, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param headers the headers
     */
    public AbstractHttpClient(OkHttpClient client, Map<String, String> headers) {
        this(client, null, null);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public AbstractHttpClient(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        this(client, null, serialization, mediaType);
    }

    /**
     * Instantiates a new http client.
     *
     * @param client the client
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    public AbstractHttpClient(OkHttpClient client, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        init(client, headers, serialization, mediaType);
    }

    /**
     * Inits the.
     *
     * @param config the config
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    protected void init(HttpRequestConfig config, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        init(okHttpClient(config), headers, serialization, mediaType);
    }

    private OkHttpClient okHttpClient(HttpRequestConfig config) {
        if (config == null) {
            config = new HttpRequestConfig();
        }
        Dispatcher dispatcher = new Dispatcher(config.executorService);
        dispatcher.setMaxRequests(config.maxRequests);
        dispatcher.setMaxRequestsPerHost(config.maxRequestsPerHost);
        return new OkHttpClient.Builder().cache(new okhttp3.Cache(config.cacheDir, config.cacheMaxSize))
            .dispatcher(dispatcher).connectTimeout(config.connectTimeout, TimeUnit.SECONDS).build();
    }

    /**
     * Inits the.
     *
     * @param client the client
     * @param headers the headers
     * @param serialization the serialization
     * @param mediaType the media type
     */
    protected void init(OkHttpClient client, Map<String, String> headers, Serialization serialization,
        MediaType mediaType) {
        if (client == null) {
            this.client = okHttpClient(null);
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
        if (this.mediaType.charset() != null) {
            textPlain = MediaType.parse("text/plain; charset=" + this.mediaType.charset());
            this.serialization.setCharset(this.mediaType.charset());
        } else {
            textPlain = MediaType.parse("text/plain;");
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
     * get codeSameAsSuccess value.
     *
     * @return codeSameAsSuccess
     */
    public Set<Integer> getCodeSameAsSuccess() {
        return codeSameAsSuccess;
    }

    /**
     * set codeSameAsSuccess value.
     *
     * @param codeSameAsSuccess codeSameAsSuccess
     */
    public void setCodeSameAsSuccess(Set<Integer> codeSameAsSuccess) {
        this.codeSameAsSuccess = codeSameAsSuccess;
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
     * serialize.
     *
     * @param requestBody the request body
     * @return the serialized byte[]
     */
    protected byte[] serialize(Object requestBody) {
        if (requestBody instanceof String) {
            return ((String) requestBody).getBytes(mediaType.charset());
        } else if (requestBody instanceof byte[]) {
            return (byte[]) requestBody;
        } else {
            return serializer.serialize(requestBody);
        }
    }

    /**
     * Deserialize.
     *
     * @param <T> the generic type
     * @param response the response
     * @param responseType the response type
     * @return the deserialized object
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected <T> T deserialize(Response response, final Class<T> responseType) throws IOException {
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

    /**
     * Gets the serializer.
     *
     * @param mediaType the media type
     * @param throwExceptionNoSerializer the throw exception no serializer
     * @return the serializer
     */
    protected Serializer getSerializer(MediaType mediaType, boolean throwExceptionNoSerializer) {
        Serializer serializer = null;
        try {
            MimeType mimeType = new MimeType(mediaType.type(), mediaType.subtype());
            serializer = serialization.getSerializer(mimeType);
            if (serializer == null) {
                if (throwExceptionNoSerializer) {
                    throw new SerializationException(
                        SerializationExceptionCode.createNoSerializerForMimeTypeCode(mimeType.getBaseType()));
                }
                logger.warn("no serializer found for content-type {}", mimeType.getBaseType());
            }
        } catch (MimeTypeParseException e) {
            logger.error(e.getMessage(), e);
        }
        return serializer;
    }

    /**
     * Creates the headers.
     *
     * @param headers the headers
     * @return the headers
     */
    protected Headers createHeaders(Map<String, String> headers) {
        if (Lang.isEmpty(headers)) {
            return this.headers;
        } else {
            return HttpUtils.createHeaders(headers, headersMap);
        }
    }

    /**
     * Creates the headers.
     *
     * @param headers the headers
     * @param requestObject the request object
     * @return the headers
     */
    protected Headers createHeaders(Map<String, String> headers, Object requestObject) {
        if (Lang.isEmpty(headers)) {
            headers = new HashMap<>();
        }
        if (requestObject instanceof String) {
            addContentType(headers, textPlain.toString(), false);
        } else if (requestObject instanceof byte[]) {
            addContentType(headers, HttpUtils.STREAM_CONTENT_TYPE, true);
        } else {
            addContentType(headers, mediaType.toString(), true);
        }
        return createHeaders(headers);
    }

    /**
     * Gets the media type.
     *
     * @param requestObject the request object
     * @param headers the headers
     * @return the media type
     */
    protected MediaType getMediaType(Object requestObject, Map<String, String> headers) {
        if (requestObject instanceof String) {
            if (headers.containsKey("content-type")) {
                return mediaType;
            } else {
                return textPlain;
            }
        } else if (requestObject instanceof byte[]) {
            return HttpUtils.STREAM_MEDIA_TYPE;
        } else {
            return mediaType;
        }
    }

    private void addContentType(Map<String, String> headers, String value, boolean replace) {
        if (replace) {
            headers.put("content-type", value);
        } else {
            if (!headers.containsKey("content-type")) {
                headers.put("content-type", value);
            }
        }
    }

    /**
     * Checks if is success.
     *
     * @param response the response
     * @return true, if is success
     */
    protected boolean isSuccess(Response response) {
        return response.code() == HttpCode.OK || isCodeSameAsSuccess(response);
    }

    /**
     * Checks if is code same as success.
     *
     * @param response the response
     * @return true, if is code same as success
     */
    protected boolean isCodeSameAsSuccess(Response response) {
        return codeSameAsSuccess.contains(response.code());
    }

    /**
     * Gets the success response.
     *
     * @param request the request
     * @return the success response
     */
    protected Response getSuccessResponse(final Request request) {
        try {
            Response response = client.newCall(request).execute();
            if (isSuccess(response)) {
                return response;
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
}
