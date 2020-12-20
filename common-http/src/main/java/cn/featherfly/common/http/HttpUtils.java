
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import cn.featherfly.common.lang.Lang;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Http.
 *
 * @author zhongj
 */
public class HttpUtils {

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String DEFAULT_CONTENT_TYPE = "text/html; charset=utf-8";

    /**
     * Gets the content type.
     *
     * @param response the response
     * @return the content type
     */
    public static MediaType getContentType(Response response) {
        return MediaType.parse(response.header("Content-Type", DEFAULT_CONTENT_TYPE));
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
                    formBodyBuilder.add(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return formBodyBuilder.build();
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
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String appendParam(String url, Map<String, Serializable> params) {
        if (Lang.isEmpty(params)) {
            if (!url.contains("?")) {
                url += "?";
            } else {
                url += "&";
            }
            url += toParameString(params);
        }
        return url;
    }
}
