
package cn.featherfly.common.bean.condition;

import cn.featherfly.common.bean.BeanProperty;

/**
 * <p>
 * 类型匹配的属性查找条件类
 * </p>
 * @author 钟冀
 */
public class BeanPropertyClassMatcher implements BeanPropertyMatcher{

	private Class<?>[] propertyClasses;

	/**
	 * 使用交集判断逻辑来匹配.
	 * @param propertyClasses 属性类型
	 */
	public BeanPropertyClassMatcher(Class<?>...propertyClasses) {
		this.propertyClasses = propertyClasses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean match(BeanProperty<?> beanProperty) {
		for (Class<?> propertyClass : propertyClasses) {
			if (beanProperty.getType() == propertyClass) {
				return true;
			}
		}
		return false;
	}
}
