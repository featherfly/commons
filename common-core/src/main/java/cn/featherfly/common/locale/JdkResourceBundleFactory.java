package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>
 * JdkResourceBundleFactory
 * </p>
 *
 * @author zhongj
 */
public class JdkResourceBundleFactory implements ResourceBundleFactory {

    /**
     */
    public JdkResourceBundleFactory() {
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
        return new JdkResourceBundleProxy(
                ResourceBundle.getBundle(baseName, locale == null ? ResourceBundleUtils.getLocale() : locale), charset);
    }
}
