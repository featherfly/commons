
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 21:03:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * AsmException.
 *
 * @author zhongj
 */
public class AsmException extends LocalizedException {

    private static final long serialVersionUID = 4647324062218607205L;

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param locale the locale
     * @param ex the ex
     */
    public AsmException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param locale the locale
     */
    public AsmException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param args the args
     * @param locale the locale
     * @param ex the ex
     */
    public AsmException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param args the args
     * @param locale the locale
     */
    public AsmException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param args the args
     * @param ex the ex
     */
    public AsmException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param args the args
     */
    public AsmException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     * @param ex the ex
     */
    public AsmException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param message the message
     */
    public AsmException(String message) {
        super(message);
    }

    /**
     * Instantiates a new asm exception.
     *
     * @param ex the ex
     */
    public AsmException(Throwable ex) {
        super(ex);
    }

}
