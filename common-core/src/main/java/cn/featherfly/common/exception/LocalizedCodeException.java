package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 * .
 *
 * @author zhongj
 * @version 1.7
 * @since 1.7
 */
public abstract class LocalizedCodeException extends LocalizedException
        implements ExceptionCodeSupport<LocalizedExceptionCode> {

    private static final long serialVersionUID = -580152334157640022L;

    private LocalizedExceptionCode localizedExceptionCode;

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode) {
        super(ResourceBundleUtils.KEY_SIGN + exceptionCode.getKey(), exceptionCode.getArgus(),
                exceptionCode.getLocale());
        localizedExceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param ex            异常
     */
    protected LocalizedCodeException(LocalizedExceptionCode exceptionCode, Throwable ex) {
        super(ResourceBundleUtils.KEY_SIGN + exceptionCode.getKey(), exceptionCode.getArgus(),
                exceptionCode.getLocale(), ex);
        localizedExceptionCode = exceptionCode;
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    protected LocalizedCodeException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    protected LocalizedCodeException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    protected LocalizedCodeException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    protected LocalizedCodeException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    protected LocalizedCodeException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     */
    protected LocalizedCodeException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    protected LocalizedCodeException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     */
    protected LocalizedCodeException(LocalizedMessage message) {
        super(message);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    protected LocalizedCodeException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    protected LocalizedCodeException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    protected LocalizedCodeException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    protected LocalizedCodeException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    protected LocalizedCodeException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param args    the args
     */
    protected LocalizedCodeException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    protected LocalizedCodeException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param message the message
     */
    protected LocalizedCodeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new localized code exception.
     *
     * @param ex the ex
     */
    protected LocalizedCodeException(Throwable ex) {
        super(ex);
    }

    /**
     * 返回exceptionCode.
     *
     * @return exceptionCode
     */
    @Override
    public LocalizedExceptionCode getExceptionCode() {
        return localizedExceptionCode;
    }
}
