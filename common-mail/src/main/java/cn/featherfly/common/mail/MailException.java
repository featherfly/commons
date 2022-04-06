
package cn.featherfly.common.mail;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * MailException
 * </p>
 *
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public class MailException extends LocalizedException {

    private static final long serialVersionUID = 6808611112386674974L;

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    public MailException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    public MailException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param ex      异常
     */
    public MailException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    public MailException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     */
    public MailException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     */
    public MailException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    public MailException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    public MailException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param ex 异常
     */
    public MailException(Throwable ex) {
        super(ex);
    }
}
