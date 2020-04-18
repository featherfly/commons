package cn.featherfly.common.db.data;

import java.util.Locale;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * import异常
 * </p>
 *
 * @author zhongj
 */
public class ImportException extends JdbcException {

    /**
     *
     */
    public ImportException() {
        super();
    }

    /**
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public ImportException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message message
     * @param locale  locale
     */
    public ImportException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public ImportException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public ImportException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public ImportException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     */
    public ImportException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message
     * @param ex
     */
    public ImportException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ImportException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public ImportException(Throwable ex) {
        super(ex);
    }

    private static final long serialVersionUID = -7034897190745766939L;

}
