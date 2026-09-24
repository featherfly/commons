
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-09-24 18:11:24
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.exception;

import java.util.Date;
import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;
import cn.featherfly.common.tuple.Tuple2;

/**
 * ExceptionCodeExceptionImpl.
 * 
 * @author zhongj
 */
public class ExceptionCodeExceptionImpl extends ExceptionCodeException {

    public static final ExceptionCodeExceptionCreator<String, ExceptionCodeExceptionImpl> STRING =
        new ExceptionCodeExceptionCreator<>("CODE", 100001, "#string", ExceptionCodeExceptionImpl::new,
            ExceptionCodeExceptionImpl::new);

    public static final ExceptionCodeExceptionCreator<Integer, ExceptionCodeExceptionImpl> INTEGER =
        new ExceptionCodeExceptionCreator<>("CODE", 100001, "#integer", ExceptionCodeExceptionImpl::new,
            ExceptionCodeExceptionImpl::new);

    public static final ExceptionCodeExceptionCreator<Tuple2<String, Integer>,
        ExceptionCodeExceptionImpl> STRING_INTEGER =
            new ExceptionCodeExceptionCreator<>("CODE", 100001, "#string_integer", ExceptionCodeExceptionImpl::new,
                ExceptionCodeExceptionImpl::new);

    public static final ExceptionCodeExceptionCreator<Date, ExceptionCodeExceptionImpl> DATE =
        new ExceptionCodeExceptionCreator<>("CODE", 100001, "#date", ExceptionCodeExceptionImpl::new,
            ExceptionCodeExceptionImpl::new);

    private static final long serialVersionUID = -2340167234899586120L;

    /**
     * @param exceptionCode
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Locale locale, Throwable ex) {
        super(exceptionCode, locale, ex);
    }

    /**
     * @param exceptionCode
     * @param locale
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode, locale);
    }

    /**
     * @param exceptionCode
     * @param args
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Object[] args, Locale locale, Throwable ex) {
        super(exceptionCode, args, locale, ex);
    }

    /**
     * @param exceptionCode
     * @param args
     * @param locale
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Object[] args, Locale locale) {
        super(exceptionCode, args, locale);
    }

    /**
     * @param exceptionCode
     * @param args
     * @param ex
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Object[] args, Throwable ex) {
        super(exceptionCode, args, ex);
    }

    /**
     * @param exceptionCode
     * @param args
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Object[] args) {
        super(exceptionCode, args);
    }

    /**
     * @param exceptionCode
     * @param ex
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }

    /**
     * @param exceptionCode
     */
    public ExceptionCodeExceptionImpl(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ExceptionCodeExceptionImpl(LocalizedMessage message) {
        super(message);
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public ExceptionCodeExceptionImpl(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public ExceptionCodeExceptionImpl(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public ExceptionCodeExceptionImpl(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public ExceptionCodeExceptionImpl(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public ExceptionCodeExceptionImpl(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public ExceptionCodeExceptionImpl(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ExceptionCodeExceptionImpl(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public ExceptionCodeExceptionImpl(Throwable ex) {
        super(ex);
    }
}
