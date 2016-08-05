package cn.featherfly.common.exception;

import java.util.Locale;



/**
 * <p>
 * 应用异常（高层次）
 * </p>
 * 
 * @author 钟冀
*
 */
public class StandardAppException extends LocalizedException{

    private static final long serialVersionUID = -7005360573113235374L;

    /**
     * 构造方法
     */
    public StandardAppException() {
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    public StandardAppException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    public StandardAppException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param ex 异常
     */
    public StandardAppException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    public StandardAppException(String message, Throwable ex) {
        super(message, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     */
    public StandardAppException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     */
    public StandardAppException(String message, Object[] argus) {
        super(message, argus);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     */
    public StandardAppException(String message, Locale locale) {
        super(message, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     */
    public StandardAppException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * @param ex 异常
     */
    public StandardAppException(Throwable ex) {
        super(ex);
    }

    /**
     * @param exceptionCode
     * @param locale
     * @param ex
     */
    public StandardAppException(ExceptionCode exceptionCode, Locale locale,
            Throwable ex) {
        super(exceptionCode, locale, ex);
    }

    /**
     * @param exceptionCode
     * @param locale
     */
    public StandardAppException(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode, locale);
    }

    /**
     * @param exceptionCode
     * @param argus
     * @param locale
     * @param ex
     */
    public StandardAppException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale, Throwable ex) {
        super(exceptionCode, argus, locale, ex);
    }

    /**
     * @param exceptionCode
     * @param argus
     * @param locale
     */
    public StandardAppException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale) {
        super(exceptionCode, argus, locale);
    }

    /**
     * @param exceptionCode
     * @param argus
     * @param ex
     */
    public StandardAppException(ExceptionCode exceptionCode, Object[] argus,
            Throwable ex) {
        super(exceptionCode, argus, ex);
    }

    /**
     * @param exceptionCode
     * @param argus
     */
    public StandardAppException(ExceptionCode exceptionCode, Object[] argus) {
        super(exceptionCode, argus);
    }

    /**
     * @param exceptionCode
     * @param ex
     */
    public StandardAppException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }

    /**
     * @param exceptionCode
     */
    public StandardAppException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
    
    
}
