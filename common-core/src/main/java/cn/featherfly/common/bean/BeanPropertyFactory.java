
package cn.featherfly.common.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * BeanPropertyFactory接口.
 *
 * @author zhongj
 */
public interface BeanPropertyFactory {

    /**
     * 创建指定类型指定属性对应的BeanProperty.
     *
     * @param <T>           the bean generic type
     * @param <V>           the property generic type
     * @param propertyName  属性名称
     * @param field         存取数据的字段
     * @param propertyType  属性类型
     * @param setMethod     设置方法
     * @param getMethod     读取方法
     * @param ownerType     属性所在的类型
     * @param declaringType 定义属性的类型 （可能是ownerType的父类，也可能一样）
     * @return 创建的BeanProperty
     */
    <T, V> BeanProperty<T, V> create(String propertyName, Field field, Class<V> propertyType, Method setMethod,
            Method getMethod, Class<T> ownerType, Class<?> declaringType);

    // /**
    // * <p>
    // * 创建指定类型指定属性对应的BeanProperty
    // * </p>
    // * @param type 类型
    // * @param propertyName 属性名
    // * @param <T> 泛型
    // * @return 创建的BeanProperty子类
    // */
    // <T> BeanProperty<T> create(Class<T> type, String propertyName);
}
