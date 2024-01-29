package cn.featherfly.common.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

/**
 * ResourceBundle.
 *
 * @author zhongj
 */
public interface ResourceBundle {

    /**
     * get string.
     *
     * @param key key
     * @return the string for the given key
     * @see java.util.ResourceBundle#getString(String)
     */
    String getString(String key);

    /**
     * get string array.
     *
     * @param key key
     * @return the string array for the given key
     * @see java.util.ResourceBundle#getStringArray(String)
     */
    String[] getStringArray(String key);

    //    /**
    //     * @param key key {@link java.util.ResourceBundle#getObject(java.lang.String)}
    //     * @return the object for the given key
    //     */
    //    Object getObject(String key);

    /**
     * get locale.
     *
     * @return the locale of this resource bundle
     * @see java.util.ResourceBundle#getLocale()
     */
    Locale getLocale();

    /**
     * get keys
     *
     * @return Enumeration Kyes
     * @see java.util.ResourceBundle#getKeys()
     */
    Enumeration<String> getKeys();

    /**
     * contains key.
     *
     * @param key key
     * @return boolean containsKey
     * @see java.util.ResourceBundle#containsKey(java.lang.String)
     */
    boolean containsKey(String key);

    /**
     * key set.
     *
     * @return keySet
     * @see java.util.ResourceBundle#keySet()
     */
    Set<String> keySet();
}
