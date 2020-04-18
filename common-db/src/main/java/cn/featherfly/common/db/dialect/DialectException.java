package cn.featherfly.common.db.dialect;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * Dialect Exception
 * </p>
 *
 * @author zhongj
 */
public class DialectException extends LocalizedException {

    private static final long serialVersionUID = -4560008692923038454L;

    /**
     *
     */
    public DialectException() {
        super();
    }

    /**
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public DialectException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message message
     * @param locale  locale
     */
    public DialectException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public DialectException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public DialectException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public DialectException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     */
    public DialectException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message message
     * @param ex      ex
     */
    public DialectException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message message
     */
    public DialectException(String message) {
        super(message);
    }

    /**
     * @param ex ex
     */
    public DialectException(Throwable ex) {
        super(ex);
    }

}
