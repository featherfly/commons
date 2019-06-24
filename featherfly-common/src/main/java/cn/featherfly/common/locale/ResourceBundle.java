package cn.featherfly.common.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

/**
 * <p>
 * ResourceBundle
 * </p>
 * 
 * @author zhongj
 */
public interface ResourceBundle {
    
    /**
     * @param key key {@link java.util.ResourceBundle#getString(String)}
     * @return the string for the given key
     */
    String getString(String key);

    /**
     * @param key key {@link java.util.ResourceBundle#getStringArray(String)}
     * @return the string array for the given key
     */
    String[] getStringArray(String key);

    /**
     * @param key key {@link java.util.ResourceBundle#getObject(java.lang.String)}
     * @return the object for the given key
     */
    Object getObject(String key);

    /**
     * {@link java.util.ResourceBundle#getLocale()}
     * @return the locale of this resource bundle
     */
    Locale getLocale();
    
    /**
     * {@link java.util.ResourceBundle#getKeys()}
     * @return Enumeration Kyes
     */
    Enumeration<String> getKeys();

    /**
     * @param key key {@link java.util.ResourceBundle#containsKey(java.lang.String)}
     * @return boolean containsKey
     */
    boolean containsKey(String key);

    /**
     * {@link java.util.ResourceBundle#keySet()}
     * @return keySet
     */
    Set<String> keySet();     
}
