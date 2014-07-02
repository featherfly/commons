
package cn.featherfly.common.i18n;

import java.util.Locale;

/**
 * <p>
 * ResourceBundleFactory
 * </p>
 * 
 * @author 钟冀
 */
public interface ResourceBundleFactory {
	/**
	 * <p>
	 * getBundle
	 * </p>
	 * @param baseName
     *        the base name of the resource bundle, a fully qualified class name
     * @param locale
     *        the locale for which a resource bundle is desired
	 * @return
	 */
	public ResourceBundle getBundle(String baseName, Locale locale);
}
