package cn.featherfly.common.exception;

import java.nio.charset.Charset;
import java.util.Locale;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.locale.LocalizedMessage;
import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * support i18n message exception. <br>
 * 支持国际化消息输出的异常.
 *
 * @author zhongj
 */
public abstract class LocalizedException extends BaseException {

    private static final long serialVersionUID = -580152334157640022L;

    private final Object[] args;

    private final Locale locale;

    private String localizedMessage;

    /** The charset. */
    protected Charset charset;

    private LocalizedMessage localeMessage;

    /**
     * 构造方法.
     *
     * @param ex 异常
     */
    protected LocalizedException(Throwable ex) {
        super(ex);
        locale = getDefaultLocale();
        args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param ex 异常
     */
    protected LocalizedException(String message, Throwable ex) {
        this(message, new Object[] {}, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, ex);
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(String message, Locale locale, Throwable ex) {
        this(message, new Object[] {}, locale, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param ex 异常
     */
    protected LocalizedException(String message, Object[] args, Throwable ex) {
        this(message, args, null, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param locale locale
     */
    protected LocalizedException(String message, Object[] args, Locale locale) {
        super(message);
        this.args = args;
        if (locale == null) {
            this.locale = getDefaultLocale();
        } else {
            this.locale = locale;
        }
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param locale locale
     */
    protected LocalizedException(String message, Locale locale) {
        this(message, new Object[] {}, locale);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     */
    protected LocalizedException(String message, Object[] args) {
        this(message, args, (Locale) null);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     */
    protected LocalizedException(String message) {
        this(message, new Object[] {});
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param ex 异常
     */
    protected LocalizedException(LocalizedMessage message, Throwable ex) {
        this(message, new Object[] {}, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message.getMessage(locale, args), ex);
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param locale locale
     * @param ex 异常
     */
    protected LocalizedException(LocalizedMessage message, Locale locale, Throwable ex) {
        this(message, new Object[] {}, locale, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param ex 异常
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Throwable ex) {
        this(message, args, (Locale) null, ex);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     * @param locale locale
     */
    protected LocalizedException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message.getMessage(locale, args));
        this.args = args;
        this.locale = locale;
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param locale locale
     */
    protected LocalizedException(LocalizedMessage message, Locale locale) {
        this(message, new Object[] {}, locale);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     * @param args 消息绑定参数
     */
    protected LocalizedException(LocalizedMessage message, Object[] args) {
        this(message, args, (Locale) null);
    }

    /**
     * 构造方法.
     *
     * @param message 信息
     */
    protected LocalizedException(LocalizedMessage message) {
        this(message, new Object[] {});
    }

    /**
     * Gets the default locale.
     *
     * @return the default locale
     */
    protected Locale getDefaultLocale() {
        return Locale.getDefault();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        if (localeMessage != null) {
            return super.getMessage();
        }
        if (Lang.isEmpty(localizedMessage)) {
            String message = super.getMessage();
            if (Lang.isEmpty(message)) {
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
                localizedMessage = Lang.isEmpty(args) ? message : Strings.format(message, args);
            }
        }
        return localizedMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * 返回charset.
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 设置charset.
     *
     * @param charset charset
     * @return the localized exception
     */
    public LocalizedException setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
}
