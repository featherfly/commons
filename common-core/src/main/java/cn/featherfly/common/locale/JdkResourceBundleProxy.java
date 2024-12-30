package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Str;

/**
 * <p>
 * SimpleResourceBundle
 * </p>
 *
 * @author zhongj
 */
public class JdkResourceBundleProxy implements cn.featherfly.common.locale.ResourceBundle {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    private java.util.ResourceBundle bundle;

    private Charset charset;

    /**
     * @param bundle java.util.ResourceBundle
     */
    public JdkResourceBundleProxy(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * @param bundle  java.util.ResourceBundle
     * @param charset text charset
     */
    public JdkResourceBundleProxy(ResourceBundle bundle, Charset charset) {
        this.bundle = bundle;
        this.charset = charset;
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
        return encode(bundle.toString());
    }

    /**
     * @param key key
     * @return String String
     * @see java.util.ResourceBundle#getString(java.lang.String)
     */
    @Override
    public final String getString(String key) {
        return encode(bundle.getString(key));
    }

    /**
     * @param key key
     * @return String String
     * @see java.util.ResourceBundle#getStringArray(java.lang.String)
     */
    @Override
    public final String[] getStringArray(String key) {
        return Stream.of(bundle.getStringArray(key)).map(this::encode).collect(Collectors.toList())
                .toArray(new String[] {});
    }

    /**
     * @return Locale
     * @see java.util.ResourceBundle#getLocale()
     */
    @Override
    public Locale getLocale() {
        return bundle.getLocale();
    }

    /**
     * @see java.util.ResourceBundle#getKeys()
     * @return Key Enum
     */
    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

    /**
     * @param key key
     * @return boolean
     * @see java.util.ResourceBundle#containsKey(java.lang.String)
     */
    @Override
    public boolean containsKey(String key) {
        return bundle.containsKey(key);
    }

    /**
     * @return keySet
     * @see java.util.ResourceBundle#keySet()
     */
    @Override
    public Set<String> keySet() {
        return bundle.keySet();
    }

    private String encode(String str) {
        if (Lang.isNotEmpty(str) && charset != null) {
            return Str.encode(str, DEFAULT_CHARSET, charset);
        }
        return str;
    }
}
