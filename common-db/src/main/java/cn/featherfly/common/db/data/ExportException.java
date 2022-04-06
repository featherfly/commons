package cn.featherfly.common.db.data;

import java.util.Locale;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * export异常
 * </p>
 * .
 *
 * @author zhongj
 */
public class ExportException extends JdbcException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7034897190745766939L;

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public ExportException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param locale  locale
     */
    public ExportException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public ExportException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public ExportException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public ExportException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param argus   argus
     */
    public ExportException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     * @param ex      ex
     */
    public ExportException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param message message
     */
    public ExportException(String message) {
        super(message);
    }

    /**
     * Instantiates a new export exception.
     *
     * @param ex ex
     */
    public ExportException(Throwable ex) {
        super(ex);
    }

}
