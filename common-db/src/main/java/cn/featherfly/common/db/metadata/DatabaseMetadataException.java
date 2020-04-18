package cn.featherfly.common.db.metadata;

import java.util.Locale;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * database metadata exception
 * </p>
 * .
 *
 * @author zhongj
 */
public class DatabaseMetadataException extends JdbcException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7034897190745766939L;

    /**
     * Instantiates a new database metadata exception.
     */
    public DatabaseMetadataException() {
        super();
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public DatabaseMetadataException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public DatabaseMetadataException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param locale  the locale
     * @param ex      the ex
     */
    public DatabaseMetadataException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param locale  the locale
     */
    public DatabaseMetadataException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param ex      the ex
     */
    public DatabaseMetadataException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param argus   the argus
     */
    public DatabaseMetadataException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public DatabaseMetadataException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param message the message
     */
    public DatabaseMetadataException(String message) {
        super(message);
    }

    /**
     * Instantiates a new database metadata exception.
     *
     * @param ex the ex
     */
    public DatabaseMetadataException(Throwable ex) {
        super(ex);
    }
}
