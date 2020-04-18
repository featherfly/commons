package cn.featherfly.common.db.metadata;

import java.sql.SQLException;
import java.util.Locale;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * DatabaseMetadata异常
 * </p>
 *
 * @author zhongj
 */
public class DatabaseMetadataException extends JdbcException {
	
	private static final long serialVersionUID = -7034897190745766939L;


    /**
     * 
     */
    public DatabaseMetadataException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public DatabaseMetadataException(String message, Locale locale, SQLException ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public DatabaseMetadataException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param argus
     * @param locale
     * @param ex
     */
    public DatabaseMetadataException(String message, Object[] argus, Locale locale,
            SQLException ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message
     * @param argus
     * @param locale
     */
    public DatabaseMetadataException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message
     * @param argus
     * @param ex
     */
    public DatabaseMetadataException(String message, Object[] argus, SQLException ex) {
        super(message, argus, ex);
    }

    /**
     * @param message
     * @param argus
     */
    public DatabaseMetadataException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message
     * @param ex
     */
    public DatabaseMetadataException(String message, SQLException ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public DatabaseMetadataException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public DatabaseMetadataException(SQLException ex) {
        super(ex);
    }
}
