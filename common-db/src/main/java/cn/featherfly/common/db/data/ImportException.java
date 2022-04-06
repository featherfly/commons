package cn.featherfly.common.db.data;

import java.util.Locale;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * import异常
 * </p>
 * .
 *
 * @author zhongj
 */
public class ImportException extends JdbcException {

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public ImportException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param locale  locale
     */
    public ImportException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public ImportException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public ImportException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public ImportException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message message
     * @param argus   argus
     */
    public ImportException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ImportException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param message the message
     */
    public ImportException(String message) {
        super(message);
    }

    /**
     * Instantiates a new import exception.
     *
     * @param ex the ex
     */
    public ImportException(Throwable ex) {
        super(ex);
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7034897190745766939L;

}
