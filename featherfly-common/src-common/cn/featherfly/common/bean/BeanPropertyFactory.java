
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
     * @param <T> 泛型
     * @return 创建的BeanProperty
     */
    <T> BeanProperty<T> create(Class<?> type, Field field, Class<T> fieldType, Method setMethod, Method getMethod);
    
//    /**
//     * <p>
//     * 创建指定类型指定属性对应的BeanProperty
//     * </p>
//     * @param type 类型
//     * @param propertyName 属性名
//     * @param <T> 泛型
//     * @return 创建的BeanProperty子类
//     */
//    <T> BeanProperty<T> create(Class<T> type, String propertyName);    
}
