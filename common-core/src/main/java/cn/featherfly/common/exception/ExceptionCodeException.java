package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * ExceptionCodeException.
 *
 * @author zhongj
 * @version 1.7
 * @since 1.7
 */
public abstract class ExceptionCodeException extends LocalizedException implements ExceptionCodeSupport<ExceptionCode> {

    private static final long serialVersionUID = 5710245517160140690L;

    private ExceptionCode exceptionCode;

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param ex            异常
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode.getMessage(), ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args) {
        super(exceptionCode.getMessage(), args);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param ex            Throwable
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Throwable ex) {
        super(exceptionCode.getMessage(), args, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param locale        locale
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode.getMessage(), locale);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param locale        locale
     * @param ex            Throwable
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Locale locale, Throwable ex) {
        super(exceptionCode.getMessage(), locale, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param locale        locale
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Locale locale) {
        super(exceptionCode.getMessage(), args, locale);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法.
     *
     * @param exceptionCode 错误码
     * @param args          消息文本绑定参数
     * @param locale        locale
     * @param ex            异常
     */
    protected ExceptionCodeException(ExceptionCode exceptionCode, Object[] args, Locale locale, Throwable ex) {
        super(exceptionCode.getMessage(), args, locale, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    protected ExceptionCodeException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    protected ExceptionCodeException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    protected ExceptionCodeException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    protected ExceptionCodeException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    protected ExceptionCodeException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     */
    protected ExceptionCodeException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    protected ExceptionCodeException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     */
    protected ExceptionCodeException(LocalizedMessage message) {
        super(message);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    protected ExceptionCodeException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    protected ExceptionCodeException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    protected ExceptionCodeException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    protected ExceptionCodeException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    protected ExceptionCodeException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param args    the args
     */
    protected ExceptionCodeException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    protected ExceptionCodeException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param message the message
     */
    protected ExceptionCodeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new exception code exception.
     *
     * @param ex the ex
     */
    protected ExceptionCodeException(Throwable ex) {
        super(ex);
    }

    /**
     * 返回exceptionCode.
     *
     * @return exceptionCode
     */
    @Override
    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
