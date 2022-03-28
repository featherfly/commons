
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ValidationException.java
 * @Package cn.featherfly.common.exception
 * @Description: ValidationException
 * @author: zhongj
 * @date: 2021-11-25 18:37:25
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * ValidationException.
 *
 * @author zhongj
 */
public class ValidationException extends LocalizedException {

    private static final long serialVersionUID = 5452458564880268259L;

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public ValidationException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public ValidationException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public ValidationException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public ValidationException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public ValidationException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     */
    public ValidationException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ValidationException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     */
    public ValidationException(LocalizedMessage message) {
        super(message);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public ValidationException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public ValidationException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public ValidationException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public ValidationException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public ValidationException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param args    the args
     */
    public ValidationException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ValidationException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param ex the ex
     */
    public ValidationException(Throwable ex) {
        super(ex);
    }
}
