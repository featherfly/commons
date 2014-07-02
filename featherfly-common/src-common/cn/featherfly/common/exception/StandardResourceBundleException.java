package cn.featherfly.common.exception;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.i18n.JdkResourceBundleFactory;
import cn.featherfly.common.i18n.ResourceBundleFactory;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.ServiceLoaderUtils;


/**
 * <p>
 * 标准异常
 * </p>
 *
 * @author 钟冀
 */
public abstract class StandardResourceBundleException extends RuntimeException{
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ResourceBundleFactory resourceBundleFactory = ServiceLoaderUtils.load(ResourceBundleFactory.class, new JdkResourceBundleFactory());

	private static final long serialVersionUID = -580152334157640022L;
	
	private static final char RESOURCE_SIGN = '@';
	
	private static final char KEY_SIGN = '#';
	
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
		String baseName = this.getClass().getSimpleName();
		String key = null;
		String msg = null;
		int keyIndex = message.indexOf(KEY_SIGN);
		char firstChar = message.charAt(0);
		if (firstChar == RESOURCE_SIGN && keyIndex != -1) {
			baseName = message.substring(1, message.indexOf(KEY_SIGN));
			key = message.substring(message.indexOf(KEY_SIGN));
			msg = getMessageFromBundle(message, baseName, key, argus, locale);
		} else if (firstChar == KEY_SIGN) {
			key = message.substring(1);
			msg = getMessageFromBundle(message, baseName, key, argus, locale);
		} else {
			msg = message;
		}
		try {
			Field f = ClassUtils.getField(Throwable.class, "detailMessage");
			f.setAccessible(true);
			f.set(this, msg);
			f.setAccessible(false);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	private String getMessageFromBundle(String message, String baseName, String key, Object[] argus, Locale locale) {
		String msg = resourceBundleFactory.getBundle(baseName, locale).getString(key);
		if (LangUtils.isNotEmpty(argus)) {
			msg = MessageFormat.format(msg, argus);
		}
		logger.debug("match ResourceBundle pattern -> [{}] : baseName[{}] and key[{}], message -> {}", new Object[]{message, baseName, key, msg});
		return msg;
	}
}
