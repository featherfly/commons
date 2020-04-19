
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * <p>
 * IOException
 * </p>
 *
 * @author zhongj
 */
public class IOException extends LocalizedException {

    private static final long serialVersionUID = 3021170338073615630L;

    /**
     *
     */
    public IOException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public IOException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public IOException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public IOException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public IOException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public IOException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public IOException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public IOException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public IOException(Throwable ex) {
        super(ex);
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public IOException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public IOException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public IOException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public IOException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public IOException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public IOException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public IOException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public IOException(LocalizedMessage message) {
        super(message);
    }
}
