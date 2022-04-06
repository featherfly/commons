
package cn.featherfly.common.http;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * HttpException.
 *
 * @author zhongj
 */
public class HttpException extends LocalizedException {

    private static final long serialVersionUID = 6808611112386674974L;

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    public HttpException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    public HttpException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param ex      异常
     */
    public HttpException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    public HttpException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     */
    public HttpException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     */
    public HttpException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    public HttpException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    public HttpException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param ex 异常
     */
    public HttpException(Throwable ex) {
        super(ex);
    }
}
