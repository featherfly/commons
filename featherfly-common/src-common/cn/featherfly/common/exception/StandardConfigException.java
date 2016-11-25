package cn.featherfly.common.exception;

import java.util.Locale;




/**
 * <p>
 * 框架使用的配置信息异常
 * </p>
 * 
 * @author 钟冀
 */
public class StandardConfigException extends LocalizedException {

    private static final long serialVersionUID = -8304486676180024022L;

    /**
     * @param exceptionCode exceptionCode
     * @param locale locale
     * @param ex ex
     */
    public StandardConfigException(ExceptionCode exceptionCode, Locale locale,
            Throwable ex) {
        super(exceptionCode, locale, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param locale locale
     */
    public StandardConfigException(ExceptionCode exceptionCode, Locale locale) {
        super(exceptionCode, locale);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param locale locale
     * @param ex ex
     */
    public StandardConfigException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale, Throwable ex) {
        super(exceptionCode, argus, locale, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param locale locale
     */
    public StandardConfigException(ExceptionCode exceptionCode, Object[] argus,
            Locale locale) {
        super(exceptionCode, argus, locale);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     * @param ex ex
     */
    public StandardConfigException(ExceptionCode exceptionCode, Object[] argus,
            Throwable ex) {
        super(exceptionCode, argus, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param argus argus
     */
    public StandardConfigException(ExceptionCode exceptionCode, Object[] argus) {
        super(exceptionCode, argus);
    }

    /**
     * @param exceptionCode exceptionCode
     * @param ex ex
     */
    public StandardConfigException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }

    /**
     * @param exceptionCode exceptionCode
     */
    public StandardConfigException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    /**
     * 构造方法
     */
    public StandardConfigException() {
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    public StandardConfigException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    public StandardConfigException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param ex 异常
     */
    public StandardConfigException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    public StandardConfigException(String message, Throwable ex) {
        super(message, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     * @param locale locale
     */
    public StandardConfigException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 信息绑定参数
     */
    public StandardConfigException(String message, Object[] argus) {
        super(message, argus);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     */
    public StandardConfigException(String message, Locale locale) {
        super(message, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     */
    public StandardConfigException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * @param ex 异常
     */
    public StandardConfigException(Throwable ex) {
        super(ex);
    }
    
//    /**
//     * 构造方法
//     * @param messageCode 常量码与信息映射对象
//     */
//    public StandardConfigException(MessageCode messageCode) {
//        super(messageCode);
//    }
//    
//    /**
//     * 构造方法
//     * @param messageCode 常量码与信息映射对象
//     * @param ex 异常
//     */
//    public StandardConfigException(MessageCode messageCode, Throwable ex) {
//        super(messageCode, ex);
//    }
}
