
package cn.featherfly.common.repository.mapping;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * MappingException
 * </p>
 *
 * @author zhongj
 */
public class MappingException extends LocalizedException {

    private static final long serialVersionUID = -1261609633836399803L;

    /**
     *
     */
    public MappingException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public MappingException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public MappingException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public MappingException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public MappingException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public MappingException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public MappingException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public MappingException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public MappingException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public MappingException(Throwable ex) {
        super(ex);
    }
}
