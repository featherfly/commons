
package cn.featherfly.common.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.ServiceLoaderUtils;

/**
 * <p>
 * ResourceBundleUtils
 * </p>
 * 
 * @author zhongj
 */
public final class ResourceBundleUtils {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ResourceBundleUtils.class);

    private static final ResourceBundleFactory RESOURCE_BUNDLE_FACTORY = ServiceLoaderUtils
            .load(ResourceBundleFactory.class, new JdkResourceBundleFactory());

    private static final LocaleManager LOCALE_MANAGER = ServiceLoaderUtils
            .load(LocaleManager.class, new DefaultLocaleManager());

    /**
     * 
     */
    private ResourceBundleUtils() {
        super();
    }

    /**
     * Bundle标识符
     */
    public static final char RESOURCE_SIGN = '@';
    /**
     * key标识符
     */
    public static final char KEY_SIGN = '#';

    /**
     * <p>
     * 获取ResourceBundleUtils在没有传入Locale时使用的默认Locale,默认Locale使用LocaleManager加载
     * </p>
     * 
     * @return Locale
     */
    public static Locale getLocale() {
        return LOCALE_MANAGER.getLocale();
    }

    /**
     * <p>
     * getBundle,use default locale
     * </p>
     * 
     * @param baseName
     *            the base name of the resource bundle, a fully qualified class
     *            name
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName) {
        return RESOURCE_BUNDLE_FACTORY.getBundle(baseName,
                LOCALE_MANAGER.getLocale());
    }

    /**
     * <p>
     * getBundle
     * </p>
     * 
     * @param baseName
     *            the base name of the resource bundle, a fully qualified class
     *            name
     * @param locale
     *            the locale for which a resource bundle is desired
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName, Locale locale) {
        return RESOURCE_BUNDLE_FACTORY.getBundle(baseName, locale);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern
     *            查找字符串的模式
     * @return the string for the given key
     */
    public static String getString(String pattern) {
        return getString(pattern, null);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern
     *            查找字符串的模式
     * @param argus
     *            string format argus
     * @return the string for the given key
     */
    public static String getString(String pattern, Object[] argus) {
        return getString(pattern, argus, LOCALE_MANAGER.getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern
     *            查找字符串的模式
     * @param argus
     *            string format argus
     * @param locale
     *            locale
     * @return the string for the given key
     */
    public static String getString(String pattern, Object[] argus,
            Locale locale) {
        String baseName = null;
        String key = null;
        int keyIndex = pattern.indexOf(KEY_SIGN);
        char firstChar = pattern.charAt(0);
        if (firstChar == RESOURCE_SIGN && keyIndex != -1) {
            baseName = pattern.substring(1, pattern.indexOf(KEY_SIGN));
            key = pattern.substring(pattern.indexOf(KEY_SIGN) + 1);
            return getStringFromBundle(pattern, baseName, key, argus, locale);
        } else {
            throw new IllegalArgumentException(
                    "argu code must start with @ and split bundle baseName and key with #, example: @bundleBaseName#key");
        }
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param baseName
     *            bundle baseName
     * @param key
     *            string key
     * @param argus
     *            string format argus
     * @param locale
     *            locale
     * @return the string for the given key
     */
    public static String getString(String baseName, String key, Object[] argus,
            Locale locale) {
        return getStringFromBundle(baseName, key, argus, locale);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param type
     *            resolve for classType
     * @param key
     *            string key
     * @return the string for the given key
     */
    public static String getString(Class<?> type, String key) {
        return getString(type, key, getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param type
     *            resolve for classType
     * @param key
     *            string key
     * @param locale
     *            locale
     * @return the string for the given key
     */
    public static String getString(Class<?> type, String key, Locale locale) {
        return getString(type, key, new Object[] {}, locale);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param type
     *            resolve for classType
     * @param key
     *            string key
     * @param argus
     *            string format argus
     * @return the string for the given key
     */
    public static String getString(Class<?> type, String key, Object[] argus) {
        return getString(type, key, argus, getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * 
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param type
     *            resolve for classType
     * @param key
     *            string key
     * @param argus
     *            string format argus
     * @param locale
     *            locale
     * @return the string for the given key
     */
    public static String getString(Class<?> type, String key, Object[] argus,
            Locale locale) {
        try {
            return getString(type.getName(), key,argus, locale);            
        } catch (MissingResourceException e) {
            return getString(type.getSimpleName(), key,argus, locale);
        }
    }
    
    private static String getStringFromBundle(String baseName,
            String key, Object[] argus, Locale locale) {
        if (locale == null) {
            locale = getLocale();
        }
        String string = format(getBundle(baseName, locale).getString(key),
                argus);
        return string;
    }

    private static String getStringFromBundle(String code, String baseName,
            String key, Object[] argus, Locale locale) {
        if (locale == null) {
            locale = getLocale();
        }
        String string = format(getBundle(baseName, locale).getString(key),
                argus);
        LOGGER.debug(
                "match ResourceBundle pattern -> [{}] : baseName[{}] and key[{}], message -> {}",
                new Object[] { code, baseName, key, string });
        return string;
    }

    private static String format(String string, Object[] argus) {
        if (LangUtils.isNotEmpty(argus)) {
            return MessageFormat.format(string, argus);
        }
        return string;
    }
}
