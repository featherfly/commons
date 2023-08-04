package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import cn.featherfly.common.structure.Groupable;

/**
 * multiple implement.
 *
 * @author zhongj
 */
public class ResourceBundleMultiImplFactory implements ResourceBundleFactory {

    private Groupable<String, ResourceBundleFactory> factorys;

    /**
     * Instantiates a new resource bundle multi impl factory.
     */
    public ResourceBundleMultiImplFactory() {
        this(new JdkResourceBundleFactory());
    }

    /**
     * Instantiates a new resource bundle multi impl factory.
     *
     * @param defaultResourceBundleFactory the default resource bundle factory
     */
    public ResourceBundleMultiImplFactory(ResourceBundleFactory defaultResourceBundleFactory) {
        if (defaultResourceBundleFactory == null) {
            factorys = new Groupable<>(new JdkResourceBundleFactory());
        } else {
            factorys = new Groupable<>(defaultResourceBundleFactory);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale) {
        return factorys.getValue(baseName).getBundle(baseName, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale, Charset charset) {
        return factorys.getValue(baseName).getBundle(baseName, locale, charset);
    }

    /**
     * Register factory.
     *
     * @param baseName              the base name
     * @param resourceBundleFactory the resource bundle factory
     * @return this
     */
    public ResourceBundleMultiImplFactory registerFactory(String baseName,
            ResourceBundleFactory resourceBundleFactory) {
        factorys.addGroup(baseName, resourceBundleFactory);
        return this;
    }

    /**
     * Gets the factory.
     *
     * @param baseName the base name
     * @return the ResourceBundleFactory
     */
    public ResourceBundleFactory getFactory(String baseName) {
        return factorys.getValue(baseName);
    }

    /**
     * Gets the default ResourceBundleFactory.
     *
     * @return the default ResourceBundleFactory
     */
    public ResourceBundleFactory getFactory() {
        return factorys.getValue();
    }

    /**
     * Contains base name.
     *
     * @param group the group
     * @return true, if successful
     */
    public boolean containsName(String group) {
        return factorys.containsGroup(group);
    }

    /**
     * Contains ResourceBundleFactory.
     *
     * @param value the value
     * @return true, if successful
     */
    public boolean containsFactory(ResourceBundleFactory value) {
        return factorys.containsValue(value);
    }

    /**
     * Entry&lt;baseName,ResourceBundleFactory&gt; set.
     *
     * @return the &lt;baseName,ResourceBundleFactory&gt; sets
     */
    public Set<Entry<String, ResourceBundleFactory>> entrySet() {
        return factorys.entrySet();
    }

    /**
     * BaseName set.
     *
     * @return the baseName sets
     */
    public Set<String> nameSet() {
        return factorys.groupSet();
    }

    /**
     * Factorys.
     *
     * @return the ResourceBundleFactory collection
     */
    public Collection<ResourceBundleFactory> factorys() {
        return factorys.values();
    }

}
