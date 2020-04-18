package cn.featherfly.common.db;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * JDBC操作包装异常
 * </p>
 *
 * @author zhongj
 */
public class JdbcException extends LocalizedException {

    private static final long serialVersionUID = -7034897190745766939L;

    /**
     *
     */
    public JdbcException() {
        super();
    }

    /**
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public JdbcException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message message
     * @param locale  locale
     */
    public JdbcException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public JdbcException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public JdbcException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public JdbcException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     */
    public JdbcException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message message
     * @param ex      ex
     */
    public JdbcException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message message
     */
    public JdbcException(String message) {
        super(message);
    }

    /**
     * @param ex ex
     */
    public JdbcException(Throwable ex) {
        super(ex);
    }

}
