
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * NotImplementedException.
 *
 * @author zhongj
 * @since 1.12.0
 */
public class NotImplementedException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1456430559125327924L;

    /**
     * Instantiates a new inits the exception.
     */
    public NotImplementedException() {
        super("#NotImplemented");
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public NotImplementedException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param locale  locale
     */
    public NotImplementedException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param args    args
     * @param locale  locale
     * @param ex      ex
     */
    public NotImplementedException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public NotImplementedException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public NotImplementedException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     */
    public NotImplementedException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public NotImplementedException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     */
    public NotImplementedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param ex the ex
     */
    public NotImplementedException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public NotImplementedException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public NotImplementedException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public NotImplementedException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public NotImplementedException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public NotImplementedException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     */
    public NotImplementedException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public NotImplementedException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     */
    public NotImplementedException(LocalizedMessage message) {
        super(message);
    }
}
