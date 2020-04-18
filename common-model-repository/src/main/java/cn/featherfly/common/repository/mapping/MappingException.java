
package cn.featherfly.common.repository.mapping;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * MappingException
 * </p>
 * .
 *
 * @author zhongj
 */
public class MappingException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1261609633836399803L;

    /**
     * Instantiates a new mapping exception.
     */
    public MappingException() {
        super();
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public MappingException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public MappingException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public MappingException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public MappingException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public MappingException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param args    the args
     */
    public MappingException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public MappingException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param message the message
     */
    public MappingException(String message) {
        super(message);
    }

    /**
     * Instantiates a new mapping exception.
     *
     * @param ex the ex
     */
    public MappingException(Throwable ex) {
        super(ex);
    }
}
