package cn.featherfly.common.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * <p>
 * SimpleResourceBundle
 * </p>
 * 
 * @author 钟冀
 */
public class JdkResourceBundleProxy implements cn.featherfly.common.i18n.ResourceBundle {
    
    private java.util.ResourceBundle bundle;
    
    /**
     * @param bundle java.util.ResourceBundle
     */
    public JdkResourceBundleProxy(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return bundle.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return bundle.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return bundle.toString();
    }

    /**
     * @param key
     * @return
     * @see java.util.ResourceBundle#getString(java.lang.String)
     */
    @Override
    public final String getString(String key) {
        return bundle.getString(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.ResourceBundle#getStringArray(java.lang.String)
     */
    @Override
    public final String[] getStringArray(String key) {
        return bundle.getStringArray(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.ResourceBundle#getObject(java.lang.String)
     */
    @Override
    public final Object getObject(String key) {
        return bundle.getObject(key);
    }

    /**
     * @return
     * @see java.util.ResourceBundle#getLocale()
     */
    @Override
    public Locale getLocale() {
        return bundle.getLocale();
    }

    /**
     * @return
     * @see java.util.ResourceBundle#getKeys()
     */
    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

    /**
     * @param key
     * @return
     * @see java.util.ResourceBundle#containsKey(java.lang.String)
     */
    @Override
    public boolean containsKey(String key) {
        return bundle.containsKey(key);
    }

    /**
     * @return
     * @see java.util.ResourceBundle#keySet()
     */
    @Override
    public Set<String> keySet() {
        return bundle.keySet();
    }
}
