
package cn.featherfly.common.i18n;

import java.text.MessageFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.ServiceLoaderUtils;

/**
 * <p>
 * ResourceBundleUtils
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class ResourceBundleUtils {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ResourceBundleUtils.class);
    
    private static ResourceBundleFactory resourceBundleFactory = ServiceLoaderUtils.load(ResourceBundleFactory.class
            , new JdkResourceBundleFactory());
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
     * getBundle,use default locale
     * </p>
     * @param baseName
     *        the base name of the resource bundle, a fully qualified class name
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName) {
        return resourceBundleFactory.getBundle(baseName, Locale.getDefault());
    }
    /**
     * <p>
     * getBundle
     * </p>
     * @param baseName
     *        the base name of the resource bundle, a fully qualified class name
     * @param locale
     *        the locale for which a resource bundle is desired
     * @return ResourceBundle
     */
    public static ResourceBundle getBundle(String baseName, Locale locale) {
        return resourceBundleFactory.getBundle(baseName, locale);
    }
    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern 查找字符串的模式
     * @return the string for the given key
     */
    public static String getString(String pattern) {
        return getString(pattern, null);
    }
    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern 查找字符串的模式
     * @param argus argus
     * @return the string for the given key
     */
    public static String getString(String pattern, Object[] argus) {
        return getString(pattern, argus, Locale.getDefault());                
    }
    /**
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param pattern 查找字符串的模式
     * @param argus argus
     * @param locale locale
     * @return the string for the given key
     */
    public static String getString(String pattern, Object[] argus, Locale locale) {
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
     * @see java.util.ResourceBundle#getString(java.lang.String) getString
     * @param baseName bundle baseName
     * @param key string key
     * @param argus argus
     * @param locale locale
     * @return the string for the given key
     */
    public static String getString(String baseName, String key, Object[] argus, Locale locale) {
        return format(
                getBundle(baseName, locale).getString(key), argus);
    }
    
    private static String getStringFromBundle(String code, String baseName, String key
                    , Object[] argus, Locale locale) {
        String string = format(
                getBundle(baseName, locale).getString(key), argus);
        LOGGER.debug("match ResourceBundle pattern -> [{}] : baseName[{}] and key[{}], message -> {}"
                        , new Object[]{code, baseName, key, string});
        return string;
    }
    
    private static String format(String string, Object[] argus) {
        if (LangUtils.isNotEmpty(argus)) {
            return MessageFormat.format(string, argus);
        }
        return string;
    }
}
