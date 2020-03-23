
package cn.featherfly.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * <p>
 * Properties
 * </p>
 *
 * @author zhongj
 */
public class PropertiesPlus extends java.util.Properties implements cn.featherfly.common.io.Properties {
    /**
     *
     */
    private static final long serialVersionUID = 6262816075822880224L;

    private Properties properties;

    /**
     * Creates an empty property list with no default values.
     */
    public PropertiesPlus() {
        this(null, null);
    }

    /**
     * Creates an empty property list with the specified defaults.
     *
     * @param defaults the defaults.
     */
    public PropertiesPlus(Properties defaults) {
        properties = new PropertiesImpl(defaults);
    }

    /**
     * Creates an empty property list with the specified defaults.
     *
     * @param defaults the defaults.
     * @param charset  charset.
     */
    public PropertiesPlus(java.util.Properties defaults, Charset charset) {
        super(defaults);
        properties = new PropertiesImpl(defaults, charset);
    }

    /**
     * Creates an empty property list with no default values.
     *
     * @param charset charset
     */
    public PropertiesPlus(Charset charset) {
        this(null, charset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setProperty(String key, String value) {
        return properties.setProperty(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setProperty(String key, String value, String comment) {
        return properties.setProperty(key, value, comment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setProperty(Property property) {
        return properties.setProperty(property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property getPropertyPart(String key) {
        return properties.getPropertyPart(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Property> getPropertyParts() {
        return properties.getPropertyParts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getPropertyNames() {
        return properties.getPropertyNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Part> listAll() {
        return properties.listAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Charset getCharset() {
        return properties.getCharset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Properties toJdkProperties() {
        return properties.toJdkProperties();
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public Properties comment(String... comment) {
    //        return properties.comment(comment);
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(OutputStream out) throws IOException {
        properties.store(out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(InputStream is) throws IOException {
        properties.load(is);
    }
}
