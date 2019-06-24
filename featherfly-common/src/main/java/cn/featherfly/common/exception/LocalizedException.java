package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author zhongj
 * 
 */
public abstract class LocalizedException extends BaseException {

    private static final long serialVersionUID = -580152334157640022L;
    
    private Object[] args;
    
    private Locale locale;
    
    private String localizedMessage;
    
    /**
     * 构造方法
     * 
     */
    protected LocalizedException() {
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
     * @param args
     *            消息绑定参数
     * @param locale
     *            locale
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Object[] args, Locale locale,
            Throwable ex) {
        super(message, ex);
        this.args = args;
        this.locale = locale;
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
     * @param args
     *            消息绑定参数
     * @param ex
     *            异常
     */
    protected LocalizedException(String message, Object[] args, Throwable ex) {
        this(message, args, null, ex);
    }

    /**
     * 构造方法
     * 
     * @param message
     *            信息
     * @param args
     *            消息绑定参数
     * @param locale
     *            locale
     */
    protected LocalizedException(String message, Object[] args,
            Locale locale) {
        super(message);
        this.args = args;
        this.locale = locale;
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
     * @param args
     *            消息绑定参数
     */
    protected LocalizedException(String message, Object[] args) {
        super(message);
        this.args = args;
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
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedMessage() {
        if (LangUtils.isEmpty(localizedMessage)) {
            String message = this.getMessage();
            if (LangUtils.isEmpty(message)) {
                return message;
            }            
            int keyIndex = message.indexOf(ResourceBundleUtils.KEY_SIGN);
            char firstChar = message.charAt(0);
            if (firstChar == ResourceBundleUtils.RESOURCE_SIGN && keyIndex != -1) {
                localizedMessage = ResourceBundleUtils.getString(message, args, locale);
            } else if (firstChar == ResourceBundleUtils.KEY_SIGN) {
                localizedMessage = ResourceBundleUtils.getString(this.getClass(), message.substring(1), args, locale);
            } else {
                localizedMessage = message;
            }    
        }
        return localizedMessage;
    }
}
