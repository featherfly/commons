
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.featherfly.common.algorithm.AlgorithmException;
import cn.featherfly.common.algorithm.URL;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.serialization.Serialization;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * http utils.
 *
 * @author zhongj
 */
public final class HttpUtils {

    /** The Constant STREAM_CONTENT_TYPE. */
    public static final String STREAM_CONTENT_TYPE = "application/octet-stream";

    /** The Constant STREAM_MEDIA_TYPE. */
    public static final MediaType STREAM_MEDIA_TYPE = MediaType.parse(STREAM_CONTENT_TYPE);

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String HTML_CONTENT_TYPE = "text/html; charset=utf-8";

    /** The Constant HTML_MEDIA_TYPE. */
    public static final MediaType HTML_MEDIA_TYPE = MediaType.parse(HTML_CONTENT_TYPE);

    /** The Constant JSON_CONTENT_TYPE. */
    public static final String JSON_CONTENT_TYPE = Serialization.MIME_TYPE_JSON + "; charset=utf-8";

    /** The Constant JSON_MEDIA_TYPE. */
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse(JSON_CONTENT_TYPE);

    /** The Constant JSON_CONTENT_TYPE. */
    public static final String XML_CONTENT_TYPE = Serialization.MIME_TYPE_XML + "; charset=utf-8";

    /** The Constant JSON_MEDIA_TYPE. */
    public static final MediaType XML_MEDIA_TYPE = MediaType.parse(XML_CONTENT_TYPE);

    /** The Constant KRYO_CONTENT_TYPE. */
    public static final String KRYO_CONTENT_TYPE = Serialization.MIME_TYPE_KRYO + "; charset=utf-8";

    /** The Constant KRYO_MEDIA_TYPE. */
    public static final MediaType KRYO_MEDIA_TYPE = MediaType.parse(KRYO_CONTENT_TYPE);

    /** The Constant PROTOBUFF_CONTENT_TYPE. */
    public static final String PROTOBUFF_CONTENT_TYPE = Serialization.MIME_TYPE_PROTOBUFF + "; charset=utf-8";

    /** The Constant PROTOBUFF_MEDIA_TYPE. */
    public static final MediaType PROTOBUFF_MEDIA_TYPE = MediaType.parse(PROTOBUFF_CONTENT_TYPE);

    private HttpUtils() {
    }

    /**
     * Creates the form body.
     *
     * @param params the params
     * @return the form body
     */
    public static FormBody createFormBody(Map<String, Serializable> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (Lang.isNotEmpty(params)) {
            for (Map.Entry<String, Serializable> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    formBodyBuilder.addEncoded(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return formBodyBuilder.build();
    }

    /**
     * Creates the multipart body.
     *
     * @param params the params
     * @return the multipart body
     */
    public static MultipartBody createMultipartBody(Map<String, Serializable> params) {
        MultipartBody.Builder multiparBuilder = new MultipartBody.Builder();
        for (Map.Entry<String, Serializable> entry : params.entrySet()) {
            Serializable value = entry.getValue();
            if (value instanceof UploadFile) {
                UploadFile uploadFile = (UploadFile) value;
                try {
                    multiparBuilder.addFormDataPart(entry.getKey(),
                        URL.encodeURL(uploadFile.getFilename()),
                        RequestBody.create(MediaType.parse(uploadFile.getMediaType()),
                            uploadFile.getContent()));
                } catch (AlgorithmException e) {
                    multiparBuilder.addFormDataPart(entry.getKey(), uploadFile.getFilename(),
                        RequestBody.create(MediaType.parse(uploadFile.getMediaType()),
                            uploadFile.getContent()));
                }
            } else {
                multiparBuilder.addFormDataPart(entry.getKey(), value.toString());
            }
        }
        return multiparBuilder.build();
    }

    /**
     * Creates the request body, support upload file.
     *
     * @param params the params
     * @return the request body
     */
    public static RequestBody createRequestBody(Map<String, Serializable> params) {
        boolean isMultipar = false;
        if (Lang.isNotEmpty(params)) {
            for (Serializable value : params.values()) {
                if (value instanceof UploadFile) {
                    isMultipar = true;
                    break;
                }
            }
        }

        if (isMultipar) {
            return createMultipartBody(params);
        }

        return createFormBody(params);
    }

    /**
     * Headers to map.
     *
     * @param headers the headers
     * @return the headers
     */
    public static Map<String, String> headersToMap(Headers headers) {
        Map<String, String> headerMap = new HashMap<>();
        for (String name : headers.names()) {
            headerMap.put(name, headers.get(name));
        }
        return headerMap;
    }

    /**
     * Creates the headers.
     *
     * @param headers the headers
     * @return the headers
     */
    public static Headers createHeaders(Map<String, String> headers) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * Creates the headers.
     *
     * @param headers the headers
     * @param defaultHeaders the default headers
     * @return the headers
     */
    public static Headers createHeaders(Map<String, String> headers, Map<String, String> defaultHeaders) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
            if (!headers.containsKey(entry.getKey())) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * To parame string.
     *
     * @param params the params
     * @return the string
     */
    public static String toParameString(Map<String, Serializable> params) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Serializable> entry : params.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * Append param.
     *
     * @param url the url
     * @param name the name
     * @param value the value
     * @return the url string with param
     */
    public static String appendParam(String url, String name, Serializable value) {
        String uri = url;
        if (Lang.isNotEmpty(uri)) {
            if (uri.contains(Chars.QUESTION)) {
                uri += Chars.AMP;
            } else {
                uri += Chars.QUESTION;
            }
            uri += name + "=" + value;
        }
        return uri;
    }

    /**
     * Append param.
     *
     * @param url the url
     * @param params the params
     * @return the url string with params
     */
    public static String appendParams(String url, Map<String, Serializable> params) {
        if (Lang.isNotEmpty(params)) {
            if (url.contains(Chars.QUESTION)) {
                url += Chars.AMP;
            } else {
                url += Chars.QUESTION;
            }
            url += toParameString(params);
        }
        return url;
    }
}
