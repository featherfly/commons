
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-15 15:41:15
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.string;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * StringFormatterException.
 *
 * @author zhongj
 */
public class StringFormatterException extends LocalizedException {

    private static final long serialVersionUID = 2190584529775324020L;

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public StringFormatterException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public StringFormatterException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public StringFormatterException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public StringFormatterException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public StringFormatterException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param args    the args
     */
    public StringFormatterException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public StringFormatterException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param message the message
     */
    public StringFormatterException(String message) {
        super(message);
    }

    /**
     * Instantiates a new string formatter exception.
     *
     * @param ex the ex
     */
    public StringFormatterException(Throwable ex) {
        super(ex);
    }

}
