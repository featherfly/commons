
package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.ServiceLoaderUtils;

/**
 * <p>
 * ResourceBundleUtils
 * </p>
 * .
 *
 * @author zhongj
 */
public final class ResourceBundleUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleUtils.class);

    private static final ResourceBundleFactory RESOURCE_BUNDLE_FACTORY = ServiceLoaderUtils
            .load(ResourceBundleFactory.class, new JdkResourceBundleFactory());

    private static final LocaleManager LOCALE_MANAGER = ServiceLoaderUtils.load(LocaleManager.class,
            new DefaultLocaleManager());

    /**
     *
     */
    private ResourceBundleUtils() {
        super();
    }

    /** Bundle标识符. */
    public static final char RESOURCE_SIGN = '@';

    /** key标识符. */
    public static final char KEY_SIGN = '#';

    /**
     * <p>
     * 获取ResourceBundleUtils在没有传入Locale时使用的默认Locale,默认Locale使用LocaleManager加载
     * </p>
     * .
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
     * .
     *
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName) {
        return getBundle(baseName, LOCALE_MANAGER.getLocale());
    }

    /**
     * <p>
     * getBundle,use default locale
     * </p>
     * .
     *
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @param charset  bundle text charset
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName, Charset charset) {
        return getBundle(baseName, LOCALE_MANAGER.getLocale(), charset);
    }

    /**
     * <p>
     * getBundle
     * </p>
     * .
     *
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @param locale   the locale for which a resource bundle is desired
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName, Locale locale) {
        return RESOURCE_BUNDLE_FACTORY.getBundle(baseName, locale);
    }

    /**
     * <p>
     * getBundle
     * </p>
     * .
     *
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @param locale   the locale for which a resource bundle is desired
     * @param charset  bundle text charset
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName, Locale locale, Charset charset) {
        return RESOURCE_BUNDLE_FACTORY.getBundle(baseName, locale, charset);
    }

    /**
     * getBundle,use default locale.
     *
     * @param type the type
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(Class<?> type) {
        return getBundle(type, LOCALE_MANAGER.getLocale());
    }

    /**
     * <p>
     * getBundle,use default locale
     * </p>
     * .
     *
     * @param type    the type
     * @param charset bundle text charset
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(Class<?> type, Charset charset) {
        return getBundle(type, LOCALE_MANAGER.getLocale(), charset);
    }

    /**
     * getBundle.
     *
     * @param type   the type
     * @param locale the locale for which a resource bundle is desired
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(Class<?> type, Locale locale) {
        return getBundle(type, locale, null);
    }

    /**
     * <p>
     * getBundle
     * </p>
     * .
     *
     * @param type    the type
     * @param locale  the locale for which a resource bundle is desired
     * @param charset bundle text charset
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(Class<?> type, Locale locale, Charset charset) {
        try {
            return RESOURCE_BUNDLE_FACTORY.getBundle(type.getName(), locale, charset);
        } catch (MissingResourceException e) {
            return RESOURCE_BUNDLE_FACTORY.getBundle(type.getSimpleName(), locale, charset);
        }
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern) {
        return getString(pattern, new Object[] {});
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern, Charset charset) {
        return getString(pattern, new Object[] {}, charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @param argus   string format argus
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern, Object[] argus) {
        return getString(pattern, argus, LOCALE_MANAGER.getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @param argus   string format argus
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern, Object[] argus, Charset charset) {
        return getString(pattern, argus, LOCALE_MANAGER.getLocale(), charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @param argus   string format argus
     * @param locale  locale
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern, Object[] argus, Locale locale) {
        return getString(pattern, argus, locale, null);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param pattern 查找字符串的模式
     * @param argus   string format argus
     * @param locale  locale
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String pattern, Object[] argus, Locale locale, Charset charset) {
        String baseName = null;
        String key = null;
        int keyIndex = pattern.indexOf(KEY_SIGN);
        char firstChar = pattern.charAt(0);
        if (firstChar == RESOURCE_SIGN && keyIndex != -1) {
            baseName = pattern.substring(1, pattern.indexOf(KEY_SIGN));
            key = pattern.substring(pattern.indexOf(KEY_SIGN) + 1);
            return getStringFromBundle(pattern, baseName, key, argus, locale, charset);
        } else {
            throw new IllegalArgumentException(
                    "argu code must start with @ and split bundle baseName and key with #, example: @bundleBaseName#key");
        }
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param baseName bundle baseName
     * @param key      string key
     * @param argus    string format argus
     * @param locale   locale
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String baseName, String key, Object[] argus, Locale locale) {
        return getString(baseName, key, argus, locale, null);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param baseName bundle baseName
     * @param key      string key
     * @param argus    string format argus
     * @param locale   locale
     * @param charset  bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(String baseName, String key, Object[] argus, Locale locale, Charset charset) {
        return getStringFromBundle(baseName, key, argus, locale, charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type resolve for classType
     * @param key  string key
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key) {
        return getString(type, key, getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type    resolve for classType
     * @param key     string key
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Charset charset) {
        return getString(type, key, getLocale(), charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type   resolve for classType
     * @param key    string key
     * @param locale locale
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Locale locale) {
        return getString(type, key, new Object[] {}, locale);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type    resolve for classType
     * @param key     string key
     * @param locale  locale
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Locale locale, Charset charset) {
        return getString(type, key, new Object[] {}, locale, charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type  resolve for classType
     * @param key   string key
     * @param argus string format argus
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Object[] argus) {
        return getString(type, key, argus, getLocale());
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type    resolve for classType
     * @param key     string key
     * @param argus   string format argus
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Object[] argus, Charset charset) {
        return getString(type, key, argus, getLocale(), charset);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type   resolve for classType
     * @param key    string key
     * @param argus  string format argus
     * @param locale locale
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Object[] argus, Locale locale) {
        return getString(type, key, argus, locale, null);
    }

    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     *
     * @param type    resolve for classType
     * @param key     string key
     * @param argus   string format argus
     * @param locale  locale
     * @param charset bundle text charset
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     */
    public static String getString(Class<?> type, String key, Object[] argus, Locale locale, Charset charset) {
        try {
            return getString(type.getName(), key, argus, locale, charset);
        } catch (MissingResourceException e) {
            return getString(type.getSimpleName(), key, argus, locale, charset);
        }
    }

    private static String getStringFromBundle(String baseName, String key, Object[] argus, Locale locale,
            Charset charset) {
        if (locale == null) {
            locale = getLocale();
        }
        String string = format(getBundle(baseName, locale, charset).getString(key), argus);
        return string;
    }

    private static String getStringFromBundle(String code, String baseName, String key, Object[] argus, Locale locale,
            Charset charset) {
        if (locale == null) {
            locale = getLocale();
        }
        String string = format(getBundle(baseName, locale, charset).getString(key), argus);
        LOGGER.debug("match ResourceBundle pattern -> [{}] : baseName[{}] and key[{}], message -> {}",
                new Object[] { code, baseName, key, string });
        return string;
    }

    private static String format(String string, Object[] argus) {
        if (Lang.isNotEmpty(argus)) {
            return MessageFormat.format(string, argus);
        }
        return string;
    }
}
