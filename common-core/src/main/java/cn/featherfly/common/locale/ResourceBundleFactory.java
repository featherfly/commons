package cn.featherfly.common.locale;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * <p>
 * ResourceBundleFactory
 * </p>
 *
 * @author zhongj
 */
public interface ResourceBundleFactory {
    /**
     * <p>
     * getBundle
     * </p>
     * 
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @param locale   the locale for which a resource bundle is desired
     * @return ResourceBundle
     */
    ResourceBundle getBundle(String baseName, Locale locale);

    /**
     * <p>
     * getBundle
     * </p>
     * 
     * @param baseName the base name of the resource bundle, a fully qualified
     *                 class name
     * @param locale   the locale for which a resource bundle is desired
     * @param charset  the bundle text charset
     * @return ResourceBundle
     */
    ResourceBundle getBundle(String baseName, Locale locale, Charset charset);
}
