package cn.featherfly.common.exception;

import java.lang.reflect.Field;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.i18n.ResourceBundleUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LogUtils;


/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 * @deprecated 使用{@link LocalizedException}
 * @author 钟冀
 */
@Deprecated
public abstract class StandardResourceBundleException extends LocalizedException{
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final long serialVersionUID = -580152334157640022L;
    
    /**
     * 构造方法
     */
    protected StandardResourceBundleException() {
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    protected StandardResourceBundleException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, ex);
        setMessage(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    protected StandardResourceBundleException(String message, Locale locale, Throwable ex) {
        this(message, new Object[]{}, locale, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param ex 异常
     */
    protected StandardResourceBundleException(String message, Object[] argus, Throwable ex) {
        this(message, argus, Locale.getDefault(), ex);
    }
    
    /**
     * 构造方法
     * @param message 信息
     * @param ex 异常
     */
    protected StandardResourceBundleException(String message, Throwable ex) {
        this(message, new Object[]{}, ex);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     * @param locale locale
     */
    protected StandardResourceBundleException(String message, Object[] argus, Locale locale) {
        super(message);
        setMessage(message, argus, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     * @param argus 消息绑定参数
     */
    protected StandardResourceBundleException(String message, Object[] argus) {
        this(message, argus, Locale.getDefault());
    }
    /**
     * 构造方法
     * @param message 信息
     * @param locale locale
     */
    protected StandardResourceBundleException(String message, Locale locale) {
        this(message, new Object[]{}, locale);
    }
    /**
     * 构造方法
     * @param message 信息
     */
    protected StandardResourceBundleException(String message) {
        this(message, new Object[]{});
    }

    /**
     * 构造方法
     * @param ex 异常
     */
    protected StandardResourceBundleException(Throwable ex) {
        super(ex);
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
