package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JdkResourceBundleFactory.
 *
 * @author zhongj
 */
public class JdkResourceBundleFactory implements ResourceBundleFactory {

    /**
     * Instantiates a new jdk resource bundle factory.
     */
    public JdkResourceBundleFactory() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public cn.featherfly.common.locale.ResourceBundle getBundle(String baseName, Locale locale) {
        return getBundle(baseName, locale, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public cn.featherfly.common.locale.ResourceBundle getBundle(String baseName, Locale locale, Charset charset) {
        return new JdkResourceBundleProxy(ResourceBundle.getBundle(baseName, ResourceBundleUtils.getLocale(locale)),
                charset);
    }
}
