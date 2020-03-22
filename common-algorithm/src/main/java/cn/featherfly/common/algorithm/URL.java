
package cn.featherfly.common.algorithm;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * Coders
 * </p>
 *
 * @author zhongj
 */
public abstract class URL {

    /**
     * decode URL
     *
     * @param url the url string to decode
     * @return decoded url
     */
    public static String decodeURL(String url) {
        return decodeURL(url, StandardCharsets.UTF_8);
    }

    /**
     * decode URL
     *
     * @param url     the url string to decode
     * @param charset charset
     * @return decoded url
     */
    public static String encodeURL(String url, Charset charset) {
        try {
            return URLEncoder.encode(url, charset.displayName());
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * decode URL
     *
     * @param url url string
     * @return decoded url
     */
    public static String encodeURL(String url) {
        return encodeURL(url, StandardCharsets.UTF_8);
    }

    /**
     * decode URL
     *
     * @param url     url string
     * @param charset charset
     * @return decoded url
     */
    public static String decodeURL(String url, Charset charset) {
        try {
            return URLDecoder.decode(url, charset.displayName());
        } catch (UnsupportedEncodingException e) {
            throw new AlgorithmException(e);
        }
    }
}
