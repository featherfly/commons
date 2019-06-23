package cn.featherfly.common.exception;

import java.lang.reflect.Field;
import java.util.Locale;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author 钟冀
 * @deprecated 因为使用了hack手段，所以不建议使用  {@link LocalizedExceptionUtils#throwException}
 */
@Deprecated
public abstract class LocalizedException extends BaseException {

    private static final long serialVersionUID = -580152334157640022L;

    /**
     * 构造方法
     */
    protected LocalizedException() {
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param argus
     *            消息绑定参数
     * @param locale
     *            locale
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Object[] argus, Locale locale,
            Throwable ex) {
        super(message, ex);
        setMessage(message, argus, locale);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param locale
     *            locale
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Locale locale, Throwable ex) {
        this(message, new Object[] {}, locale, ex);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param argus
     *            消息绑定参数
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Object[] argus, Throwable ex) {
        this(message, argus, ResourceBundleUtils.getLocale(), ex);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Throwable ex) {
        this(message, new Object[] {}, ex);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param argus
     *            消息绑定参数
     * @param locale
     *            locale
     */
    protected LocalizedException(String message, Object[] argus,
            Locale locale) {
        super(message);
        setMessage(message, argus, locale);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param argus
     *            消息绑定参数
     */
    protected LocalizedException(String message, Object[] argus) {
        this(message, argus, ResourceBundleUtils.getLocale());
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param locale
     *            locale
     */
    protected LocalizedException(String message, Locale locale) {
        this(message, new Object[] {}, locale);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     */
    protected LocalizedException(String message) {
        this(message, new Object[] {});
    }

    /**
     * 构造方法
     * 
     * @param ex
     *            异常
     */
    protected LocalizedException(Throwable ex) {
        super(ex);
    }

    private void setMessage(String message, Object[] argus, Locale locale) {
        String msg = null;        
        int keyIndex = message.indexOf(ResourceBundleUtils.KEY_SIGN);
        char firstChar = message.charAt(0);
        if (firstChar == ResourceBundleUtils.RESOURCE_SIGN && keyIndex != -1) {
            msg = ResourceBundleUtils.getString(message, argus, locale);
        } else if (firstChar == ResourceBundleUtils.KEY_SIGN) {
            msg = ResourceBundleUtils.getString(this.getClass(), message.substring(1), argus, locale);
//            try {
//                msg = ResourceBundleUtils.getString(
//                        ResourceBundleUtils.RESOURCE_SIGN
//                                + this.getClass().getName() + message,
//                        argus, locale);
//            } catch (MissingResourceException e) {
//                msg = ResourceBundleUtils.getString(
//                        ResourceBundleUtils.RESOURCE_SIGN
//                                + this.getClass().getSimpleName() + message,
//                        argus, locale);
//            }
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
