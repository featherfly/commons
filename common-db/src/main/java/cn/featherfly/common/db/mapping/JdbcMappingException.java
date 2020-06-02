
package cn.featherfly.common.db.mapping;

import java.util.Locale;

import cn.featherfly.common.repository.mapping.MappingException;

/**
 * <p>
 * JdbcMappingException
 * </p>
 * .
 *
 * @author zhongj
 */
public class JdbcMappingException extends MappingException {

    private static final long serialVersionUID = 4628524073238713895L;

    /**
     * Instantiates a new jdbc mapping exception.
     */
    public JdbcMappingException() {
        super();
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public JdbcMappingException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public JdbcMappingException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public JdbcMappingException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public JdbcMappingException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public JdbcMappingException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param args    the args
     */
    public JdbcMappingException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public JdbcMappingException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param message the message
     */
    public JdbcMappingException(String message) {
        super(message);
    }

    /**
     * Instantiates a new jdbc mapping exception.
     *
     * @param ex the ex
     */
    public JdbcMappingException(Throwable ex) {
        super(ex);
    }

}
