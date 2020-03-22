
package cn.featherfly.common.bean.javassist;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 * 
 * @author zhongj
*
 */
public interface BeanPropertyAccess<T, V> {
	
	void setValue(T target, V value); 
	
	V getValue(T target);
}
