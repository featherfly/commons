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
