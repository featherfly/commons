package cn.featherfly.common.exception;

import java.util.Locale;




/**
 * <p>
 * 系统异常（低层次）
 * </p>
 * 
 * @author 钟冀
 */
public class StandardSysException extends LocalizedException {

    private static final long serialVersionUID = 4425897945460700161L;

    /**
     * @param exceptionCode exceptionCode
     * @param locale locale
     * @param ex ex
     */
    public StandardSysException(ExceptionCode exceptionCode, Locale locale,
            Throwable ex) {
        super(exceptionCode, locale, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param locale locale
     */
    public StandardSysException(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode, locale);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param locale locale
     * @param ex ex
     */
    public StandardSysException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale, Throwable ex) {
        super(exceptionCode, argus, locale, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param locale locale
     */
    public StandardSysException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale) {
        super(exceptionCode, argus, locale);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param ex ex
     */
    public StandardSysException(ExceptionCode exceptionCode, Object[] argus,
            Throwable ex) {
        super(exceptionCode, argus, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     */
    public StandardSysException(ExceptionCode exceptionCode, Object[] argus) {
        super(exceptionCode, argus);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public StandardSysException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     */
    public StandardSysException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * 构造方法
     */
    public StandardSysException() {
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    public StandardSysException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    public StandardSysException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param ex 异常
     */
    public StandardSysException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    public StandardSysException(String message, Throwable ex) {
        super(message, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     */
    public StandardSysException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     */
    public StandardSysException(String message, Object[] argus) {
        super(message, argus);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     */
    public StandardSysException(String message, Locale locale) {
        super(message, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     */
    public StandardSysException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * @param ex 异常
     */
    public StandardSysException(Throwable ex) {
        super(ex);
    }
    
//    /**
//     * 构造方法
//     * @param messageCode 常量码与信息映射对象
//     */
//    public StandardSysException(MessageCode messageCode) {
//        super(messageCode);
//    }
//    
//    /**
//     * 构造方法
//     * @param messageCode 常量码与信息映射对象
//     * @param ex 异常
//     */
//    public StandardSysException(MessageCode messageCode, Throwable ex) {
//        super(messageCode, ex);
//    }
}
