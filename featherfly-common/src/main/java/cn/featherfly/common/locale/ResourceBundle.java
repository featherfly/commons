package cn.featherfly.common.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

/**
 * <p>
 * ResourceBundle
 * </p>
 * 
 * @author 钟冀
 */
public interface ResourceBundle {
    
    /**
     * {@link java.util.ResourceBundle#getString(String)}
     */
    String getString(String key);

    /**
     * {@link java.util.ResourceBundle#getStringArray(String)}
     */
    String[] getStringArray(String key);

    /**
     * {@link java.util.ResourceBundle#getObject(java.lang.String)}
     */
    Object getObject(String key);

    /**
     * {@link java.util.ResourceBundle#getLocale()}
     */
    Locale getLocale();
    
    /**
     * {@link java.util.ResourceBundle#getKeys()}
     */
    Enumeration<String> getKeys();

    /**
     * {@link java.util.ResourceBundle#containsKey(java.lang.String)}
     */
    boolean containsKey(String key);

    /**
     * {@link java.util.ResourceBundle#keySet()}
     */
    Set<String> keySet();     
}
