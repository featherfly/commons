
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
     *
     */
    public ValidationException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public ValidationException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public ValidationException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public ValidationException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public ValidationException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public ValidationException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public ValidationException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public ValidationException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ValidationException(LocalizedMessage message) {
        super(message);
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public ValidationException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public ValidationException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public ValidationException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public ValidationException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public ValidationException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public ValidationException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public ValidationException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public ValidationException(Throwable ex) {
        super(ex);
    }
}
