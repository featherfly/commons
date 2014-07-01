
package cn.featherfly.common.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>
 * BeanPropertyFactory接口
 * </p>
 *
 * @author 钟冀
*
 */
public interface BeanPropertyFactory {
	/**
	 *<p>
	 * 创建指定类型指定属性对应的BeanProperty
	 * </p>
	 * @param type 类型
	 * @param field 存取数据的字段
	 * @param setMethod 设置方法
	 * @param getMethod 读取方法
	 * @return 创建的BeanProperty
	 */
	BeanProperty create(Class<?> type, Field field, Method setMethod, Method getMethod);
	
	/**
	 * <p>
	 * 创建指定类型指定属性对应的BeanProperty
	 * </p>
	 * @param type 类型
	 * @param propertyName 属性名
	 * @return 创建的BeanProperty子类
	 */
	public BeanProperty create(Class<?> type, String propertyName);	
}
