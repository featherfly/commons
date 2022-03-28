
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * <p>
 * IOException
 * </p>
 * .
 *
 * @author zhongj
 */
public class IOException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3021170338073615630L;

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public IOException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public IOException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public IOException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public IOException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public IOException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     */
    public IOException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public IOException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param ex the ex
     */
    public IOException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public IOException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public IOException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public IOException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public IOException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public IOException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param args    the args
     */
    public IOException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public IOException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new IO exception.
     *
     * @param message the message
     */
    public IOException(LocalizedMessage message) {
        super(message);
    }
}
