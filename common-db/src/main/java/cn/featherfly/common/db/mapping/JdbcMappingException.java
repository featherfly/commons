
package cn.featherfly.common.db.mapping;

import java.util.Locale;

import cn.featherfly.common.repository.mapping.MappingException;

/**
 * <p>
 * JdbcMappingException
 * </p>
 *
 * @author zhongj
 */
public class JdbcMappingException extends MappingException {

    private static final long serialVersionUID = 4628524073238713895L;

    /**
     *
     */
    public JdbcMappingException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public JdbcMappingException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public JdbcMappingException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public JdbcMappingException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public JdbcMappingException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public JdbcMappingException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public JdbcMappingException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public JdbcMappingException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public JdbcMappingException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public JdbcMappingException(Throwable ex) {
        super(ex);
    }

}
