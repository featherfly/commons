
/**
 * @author zhongj - yufei
 *         Aug 22, 2008
 */
package cn.featherfly.common.lang;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import cn.featherfly.common.constant.Chars;

/**
 * <p>
 * uri的工具类
 * </p>
 *
 * @author zhongj
 */
public final class UriUtils {

    private UriUtils() {
    }

    /**
     * uri路径分隔符
     */
    public static final char SEPARATOR = '/';

    /**
     * 转换 \ 为 /
     *
     * @param uri 待转换的uri
     * @return 转换后的字符串
     */
    public static String convertSeparator(String uri) {
        return uri.replace('\\', SEPARATOR);
    }

    /**
     * 返回以 / 分割 字符串（例如：/a/b/c/d/e，）后的字符串数组
     *
     * @param uri 待分割的uri
     * @return 以/分割后的字符串数组
     */
    public static String[] splitUri(String uri) {
        String[] uris = null;
        uri = removeStartAndEndSeparator(uri);
        if (uri.indexOf(SEPARATOR) != -1) {
            uris = Str.split(uri, SEPARATOR);
        }
        return uris;
    }

    /**
     * link startUri and endUri ignoreCase the separator end of startUri and
     * the separator start and end of endUri
     * linkUri("/a/b/","/c/d/") return /a/b/c/d
     *
     * @param startUri 开始uri
     * @param endUri 结束uri
     * @return 连接后的url
     */
    public static String linkUri(String startUri, String endUri) {
        return linkUri(startUri, endUri, true);
    }

    /**
     * link startUri and endUri ignoreCase the separator end of startUri and
     * the separator start and end of endUri
     * linkUri("/a/b/","/c/d/") return /a/b/c/d
     * if ignoreCaseEmpty is true and the startUri is empty,will return endUri
     * if ignoreCaseEmpty = true linkUri("","c/d/") return c/d
     * if ignoreCaseEmpty = false linkUri("","c/d/") return /c/d
     *
     * @param startUri 开始uri
     * @param endUri 结束uri
     * @param ignoreCaseEmpty 如果开始uri为空，则忽略
     * @return 连接后的uri
     */
    public static String linkUri(String startUri, String endUri, boolean ignoreCaseEmpty) {
        if (ignoreCaseEmpty && Lang.isEmpty(startUri)) {
            return endUri;
        }
        if (ignoreCaseEmpty && Lang.isEmpty(endUri)) {
            return startUri;
        }
        startUri = removeEndSeparator(startUri);
        endUri = removeStartSeparator(endUri);
        return startUri + UriUtils.SEPARATOR + endUri;
    }

    /**
     * link given uris as given linkUri("/a/b/","c/d","/11/22/","/33/44/")
     * return /a/b/c/d/11/22/33/44
     *
     * @param uris 待连接的uri
     * @return 连接后的uri
     */
    public static String linkUri(String... uris) {
        return linkUri(true, uris);
    }

    /**
     * link given uris as given linkUri("/a/b/","c/d","/11/22/","/33/44/")
     * return /a/b/c/d/11/22/33/44
     * if ignoreCaseEmpty is true and the beforeUri is empty,will return afterUri
     * if ignoreCaseEmpty = true linkUri("","","c/d") return c/d
     * if ignoreCaseEmpty = false linkUri("","","c/d") return /c/d
     *
     * @param ignoreCaseEmpty 如果开始uri为空，则忽略
     * @param uris 待连接的uri
     * @return 连接后的uri
     */
    public static String linkUri(boolean ignoreCaseEmpty, String... uris) {
        String resultUri = null;
        if (uris != null && uris.length > 0) {
            resultUri = uris[0];
            if (uris.length > 1) {
                for (int i = 1; i < uris.length; i++) {
                    resultUri = linkUri(resultUri, uris[i], ignoreCaseEmpty);
                }
            }
        }
        return resultUri;
    }

    /**
     * remove start separator of uri /aaa/ -&gt; aaa/
     *
     * @param uri 待处理的uri
     * @return 移除开始/后的uri
     */
    public static String removeStartSeparator(String uri) {
        int start = 0;
        while (uri.indexOf(SEPARATOR, start) == start) {
            start++;
        }
        return start == 0 ? uri : uri.substring(start, uri.length());
    }

    /**
     * remove end separator of uri /aaa/ -&gt; /aaa
     *
     * @param uri 待处理的uri
     * @return 移除结尾/后的uri
     */
    public static String removeEndSeparator(String uri) {
        int end = uri.length() - 1;
        while (uri.lastIndexOf(UriUtils.SEPARATOR, end) == end) {
            end--;
        }
        return end == uri.length() - 1 ? uri : uri.substring(0, end + 1);
    }

    /**
     * remove start and end separator of uri /aaa/ -&gt; aaa
     *
     * @param uri 待处理的uri
     * @return 移除开始结束/后的uri
     */
    public static String removeStartAndEndSeparator(String uri) {
        uri = removeStartSeparator(uri);
        uri = removeEndSeparator(uri);
        return uri;
    }

    /**
     * to parameter name value string. example: name=yufei&age=1
     *
     * @param params the params
     * @return parameter name value string
     */
    public static String toParameString(Map<String, Serializable> params) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Serializable> entry : params.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            sb.append("&").append(toParameString(entry.getKey(), entry.getValue()));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * to parameter name value string. example:name=yufei or name=yufei&name=yi
     *
     * @param name the name
     * @param value the value
     * @return parameter name value string
     */
    public static String toParameString(String name, Serializable value) {
        StringBuilder sb = new StringBuilder();
        if (value == null) {
            return "";
        }
        Lang.eachObj(value, v -> sb.append("&").append(name).append("=").append(v));
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
            uri += toParameString(name, value);
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
    public static String appendParam(String url, Map<String, Serializable> params) {
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
