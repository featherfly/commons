package cn.featherfly.common.exception;

import java.nio.charset.Charset;
import java.util.Locale;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.locale.LocalizedMessage;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * 支持国际化消息输出的异常
 * </p>
 *
 * @author zhongj
 */
public abstract class LocalizedException extends BaseException {

    private static final long serialVersionUID = -580152334157640022L;

    private Object[] args;

    private Locale locale;

    private String localizedMessage;

    protected Charset charset;

    private LocalizedMessage localeMessage;

    /**
     * 构造方法
     */
    protected LocalizedException() {
    }

    /**
     * 构造方法
     *
     * @param ex 异常
     */
    protected LocalizedException(Throwable ex) {
        super(ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    protected LocalizedException(String message, Throwable ex) {
        this(message, new Object[] {}, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    protected LocalizedException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, ex);
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    protected LocalizedException(String message, Locale locale, Throwable ex) {
        this(message, new Object[] {}, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param ex      异常
     */
    protected LocalizedException(String message, Object[] args, Throwable ex) {
        this(message, args, null, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param locale  locale
     */
    protected LocalizedException(String message, Object[] args, Locale locale) {
        super(message);
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    protected LocalizedException(String message, Locale locale) {
        this(message, new Object[] {}, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     */
    protected LocalizedException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    protected LocalizedException(String message) {
        this(message, new Object[] {});
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    protected LocalizedException(LocalizedMessage message, Throwable ex) {
        this(message, new Object[] {}, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message.getMessage(args), ex);
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    protected LocalizedException(LocalizedMessage message, Locale locale, Throwable ex) {
        this(message, new Object[] {}, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param ex      异常
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Throwable ex) {
        this(message, args, null, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     * @param locale  locale
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message.getMessage(args));
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    protected LocalizedException(LocalizedMessage message, Locale locale) {
        this(message, new Object[] {}, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param args    消息绑定参数
     */
    protected LocalizedException(LocalizedMessage message, Object[] args) {
        super(message.getMessage(args));
        this.args = args;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    protected LocalizedException(LocalizedMessage message) {
        this(message, new Object[] {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedMessage() {
        if (localeMessage != null) {
            return getMessage();
        }
        if (LangUtils.isEmpty(localizedMessage)) {
            String message = getMessage();
            if (LangUtils.isEmpty(message)) {
                return message;
            }
            int keyIndex = message.indexOf(ResourceBundleUtils.KEY_SIGN);
            char firstChar = message.charAt(0);
            if (firstChar == ResourceBundleUtils.RESOURCE_SIGN && keyIndex != -1) {
                localizedMessage = ResourceBundleUtils.getString(message, args, locale, charset);
            } else if (firstChar == ResourceBundleUtils.KEY_SIGN) {
                localizedMessage = ResourceBundleUtils.getString(this.getClass(), message.substring(1), args, locale,
                        charset);
            } else {
                localizedMessage = message;
            }
        }
        return localizedMessage;
    }

    /**
     * 返回charset
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 设置charset
     *
     * @param charset charset
     */
    public LocalizedException setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
}
