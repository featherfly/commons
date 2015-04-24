
package cn.featherfly.common.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>
 * JdkResourceBundleFactory
 * </p>
 * 
 * @author 钟冀
 */
public class JdkResourceBundleFactory implements ResourceBundleFactory{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public cn.featherfly.common.i18n.ResourceBundle getBundle(String baseName, Locale locale) {
		return new JdkResourceBundleProxy(ResourceBundle.getBundle(baseName,locale));
	}
}
