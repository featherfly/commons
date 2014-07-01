
package cn.featherfly.common.bean.condition;

import cn.featherfly.common.bean.BeanProperty;

/**
 * <p>
 * 属性查找接口
 * </p>
 *
 * @author 钟冀
 */
public interface BeanPropertyCondition {
	/**
	 * <p>
	 * 传入属性是否匹配
	 * </p>
	 * @param beanProperty 属性
	 * @return 传入属性是否匹配
	 */
	boolean match(BeanProperty beanProperty);
}
