
package cn.featherfly.common.i18n;

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
     * {@link java.util.ResourceBundle#getString(java.lang.String) getString}
     */
    String getString(String key);

    /**
     * {@link java.util.ResourceBundle#getStringArray(java.lang.String) getStringArray}
     */
    String[] getStringArray(String key);

    /**
     * {@link java.util.ResourceBundle#getObject(java.lang.String) getObject}
     */
    Object getObject(String key);

    /**
     * {@link java.util.ResourceBundle#getLocale() getLocale}
     */
    Locale getLocale();
    
    /**
     * {@link java.util.ResourceBundle#getKeys() getKeys}
     */
    Enumeration<String> getKeys();

    /**
     * {@link java.util.ResourceBundle#containsKey(java.lang.String) containsKey}
     */
    boolean containsKey(String key);

    /**
     * {@link java.util.ResourceBundle#keySet() keySet}
     */
    Set<String> keySet();	 
}
