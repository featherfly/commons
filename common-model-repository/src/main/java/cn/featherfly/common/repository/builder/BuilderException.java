package cn.featherfly.common.repository.builder;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * dml builder exception
 * </p>
 *
 * @author zhongj
 */
public class BuilderException extends LocalizedException {

    private static final long serialVersionUID = -7034897190745766939L;

    /**
     *
     */
    public BuilderException() {
        super();
    }

    /**
     * @param message message
     * @param locale  locale
     * @param ex      ex
     */
    public BuilderException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message message
     * @param locale  locale
     */
    public BuilderException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     * @param ex      ex
     */
    public BuilderException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param locale  locale
     */
    public BuilderException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message message
     * @param argus   argus
     * @param ex      ex
     */
    public BuilderException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * @param message message
     * @param argus   argus
     */
    public BuilderException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message message
     * @param ex      ex
     */
    public BuilderException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message message
     */
    public BuilderException(String message) {
        super(message);
    }

    /**
     * @param ex ex
     */
    public BuilderException(Throwable ex) {
        super(ex);
    }

}
