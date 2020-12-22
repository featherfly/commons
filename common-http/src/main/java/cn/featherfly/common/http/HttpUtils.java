
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.serialization.Serialization;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;

/**
 * Http.
 *
 * @author zhongj
 */
public class HttpUtils {

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

    //    /**
    //     * Gets the content type.
    //     *
    //     * @param response the response
    //     * @return the content type
    //     */
    //    public static MediaType getContentType(Response response) {
    //        return MediaType.parse(response.header("Content-Type", HTML_CONTENT_TYPE));
    //    }

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
                    formBodyBuilder.add(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return formBodyBuilder.build();
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
     * @param headers        the headers
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
     * @param url   the url
     * @param name  the name
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
     * @param url    the url
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
