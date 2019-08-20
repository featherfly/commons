
package cn.featherfly.common.spring.cache;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * CacheException
 * </p>
 * <p>
 * 2019-08-08
 * </p>
 * .
 *
 * @author zhongj
 */
public class CacheException extends LocalizedException {

    private static final long serialVersionUID = 7750572696100325488L;

    /**
     * Instantiates a new cache exception.
     */
    public CacheException() {
        super();
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public CacheException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public CacheException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public CacheException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public CacheException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public CacheException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param args    the args
     */
    public CacheException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public CacheException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param message the message
     */
    public CacheException(String message) {
        super(message);
    }

    /**
     * Instantiates a new cache exception.
     *
     * @param ex the ex
     */
    public CacheException(Throwable ex) {
        super(ex);
    }

}
