
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * <p>
 * InitException
 * </p>
 * .
 *
 * @author zhongj
 * @since 1.7.7
 */
public class ReflectException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8142249144857913831L;

    /**
     * Instantiates a new inits the exception.
     */
    public ReflectException() {
        super();
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public ReflectException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param locale  locale
     */
    public ReflectException(String message, Locale locale) {
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
    public ReflectException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public ReflectException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public ReflectException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     */
    public ReflectException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ReflectException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     */
    public ReflectException(String message) {
        super(message);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param ex the ex
     */
    public ReflectException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public ReflectException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public ReflectException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public ReflectException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public ReflectException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public ReflectException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param args    the args
     */
    public ReflectException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ReflectException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new reflect exception.
     *
     * @param message the message
     */
    public ReflectException(LocalizedMessage message) {
        super(message);
    }
}
