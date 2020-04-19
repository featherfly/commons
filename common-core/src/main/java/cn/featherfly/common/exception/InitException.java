
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * <p>
 * InitException
 * </p>
 * <p>
 * 2019-08-07
 * </p>
 * .
 *
 * @author zhongj
 * @since 1.7.3
 */
public class InitException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1456430559125327924L;

    /**
     * Instantiates a new inits the exception.
     */
    public InitException() {
        super();
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public InitException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param locale  locale
     */
    public InitException(String message, Locale locale) {
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
    public InitException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public InitException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public InitException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     */
    public InitException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public InitException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     */
    public InitException(String message) {
        super(message);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param ex the ex
     */
    public InitException(Throwable ex) {
        super(ex);
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public InitException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public InitException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public InitException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public InitException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public InitException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public InitException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public InitException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public InitException(LocalizedMessage message) {
        super(message);
    }

}
