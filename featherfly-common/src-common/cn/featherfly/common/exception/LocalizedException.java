package cn.featherfly.common.exception;

import java.lang.reflect.Field;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;


/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author 钟冀
 */
public abstract class LocalizedException extends RuntimeException{
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final long serialVersionUID = -580152334157640022L;
    
    private ExceptionCode exceptionCode;
    
    /**
     * 构造方法
     */
    protected LocalizedException() {
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, ex);
        setMessage(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(String message, Locale locale, Throwable ex) {
        this(message, new Object[]{}, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param ex 异常
     */
    protected LocalizedException(String message, Object[] argus, Throwable ex) {
        this(message, argus, ResourceBundleUtils.getLocale(), ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    protected LocalizedException(String message, Throwable ex) {
        this(message, new Object[]{}, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param locale locale
     */
    protected LocalizedException(String message, Object[] argus, Locale locale) {
        super(message);
        setMessage(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     */
    protected LocalizedException(String message, Object[] argus) {
        this(message, argus, ResourceBundleUtils.getLocale());
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     */
    protected LocalizedException(String message, Locale locale) {
        this(message, new Object[]{}, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     */
    protected LocalizedException(String message) {
        this(message, new Object[]{});
    }

    /**
     * 构造方法
     * @param ex 异常
     */
    protected LocalizedException(Throwable ex) {
        super(ex);
    }
    
    /**
     * 构造方法
     * @param exceptionCode 错误码
     */
    protected LocalizedException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     * @param exceptionCode 错误码
     * @param argus 信息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(ExceptionCode exceptionCode, Object[] argus, Locale locale, Throwable ex) {
        this(exceptionCode.getMessage(), argus, locale, ex);
        this.exceptionCode = exceptionCode;
    }
    /**
     * 构造方法
     * @param exceptionCode 错误码
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(ExceptionCode exceptionCode, Locale locale, Throwable ex) {
        this(exceptionCode.getMessage(), locale, ex);
        this.exceptionCode = exceptionCode;
    }
    /**
     * 构造方法
     * @param argus 信息绑定参数
     * @param exceptionCode 错误码
     * @param ex 异常
     */
    protected LocalizedException(ExceptionCode exceptionCode, Object[] argus, Throwable ex) {
        this(exceptionCode.getMessage(), argus, ex);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构造方法
     * @param exceptionCode 错误码
     * @param ex 异常
     */
    protected LocalizedException(ExceptionCode exceptionCode, Throwable ex) {
        this(exceptionCode.getMessage(), ex);
        this.exceptionCode = exceptionCode;
    }
    /**
     * 构造方法
     * @param argus 信息绑定参数
     * @param locale locale
     * @param exceptionCode 错误码
     */
    protected LocalizedException(ExceptionCode exceptionCode, Object[] argus, Locale locale) {
        this(exceptionCode.getMessage(), argus, locale);
        this.exceptionCode = exceptionCode;
    }
    /**
     * 构造方法
     * @param argus 信息绑定参数
     * @param exceptionCode 错误码
     */
    protected LocalizedException(ExceptionCode exceptionCode, Object[] argus) {
        this(exceptionCode.getMessage(), argus);
        this.exceptionCode = exceptionCode;
    }
    /**
     * 构造方法
     * @param locale locale
     * @param exceptionCode 错误码
     */
    protected LocalizedException(ExceptionCode exceptionCode, Locale locale) {
        this(exceptionCode.getMessage(), locale);
        this.exceptionCode = exceptionCode;
    }
    
    /**
     * 返回exceptionCode
     * @return exceptionCode
     */
    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
    private void setMessage(String message, Object[] argus, Locale locale) {
        String msg = null;
        char keySign = ResourceBundleUtils.KEY_SIGN;
        char resourceSign = ResourceBundleUtils.RESOURCE_SIGN;
        int keyIndex = message.indexOf(keySign);
        char firstChar = message.charAt(0);
        if (firstChar == resourceSign && keyIndex != -1) {
            msg = ResourceBundleUtils.getString(message, argus, locale);
        } else if (firstChar == keySign) {
            msg = ResourceBundleUtils.getString(
                    ResourceBundleUtils.RESOURCE_SIGN + this.getClass().getSimpleName() + message, argus, locale);
        } else {
            msg = message;
        }
        try {
            Field f = ClassUtils.getField(Throwable.class, "detailMessage");
            f.setAccessible(true);
            f.set(this, msg);
            f.setAccessible(false);
        } catch (Exception e) {        
            LogUtils.error(e, logger);
        }
    }
}