
package cn.featherfly.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.exception.UnsupportedException;

/**
 * java.util.Properties Plus.
 *
 * @author zhongj
 */
public class PropertiesPlus extends java.util.Properties implements cn.featherfly.common.io.Properties {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 6262816075822880224L;

    private Properties properties;

    /**
     * Creates an property list with no default values.
     */
    public PropertiesPlus() {
        this((Properties) null);
    }

    /**
     * Creates an property list with the specified defaults.
     *
     * @param defaults the defaults.
     */
    public PropertiesPlus(Properties defaults) {
        properties = new PropertiesImpl(defaults, StandardCharsets.ISO_8859_1);
    }

    /**
     * Creates an property list with the specified defaults.
     *
     * @param defaults the defaults.
     */
    public PropertiesPlus(java.util.Properties defaults) {
        super(defaults);
        properties = new PropertiesImpl(defaults);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String setProperty(String key, String value) {
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
    public void store(OutputStream out, Charset charset) throws IOException {
        properties.store(out, charset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(OutputStream out, String comment) throws IOException {
        //        if (withJdkPropertiesFormat) {
        //            super.store(out, comment);
        //        } else {
        //            logger.warn("comment argu is ignore");
        //            properties.store(out);
        //        }
        logger.warn("comment argu is ignore");
        properties.store(out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(Writer writer, String comment) throws IOException {
        throw new UnsupportedException("use store(OutputStream out) instead");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void load(InputStream is) throws IOException {
        properties.load(is);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void load(Reader reader) throws IOException {
        throw new UnsupportedException("use load(InputStream is) instead");
    }

}
