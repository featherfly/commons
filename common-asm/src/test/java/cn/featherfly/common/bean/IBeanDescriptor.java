
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 00:57:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

import cn.featherfly.common.function.serializable.SerializableBiConsumer;
import cn.featherfly.common.function.serializable.SerializableConsumer;
import cn.featherfly.common.function.serializable.SerializableFunction;
import cn.featherfly.common.function.serializable.SerializableSupplier;

/**
 * BeanDescriptor.
 *
 * @author zhongj
 */
public interface IBeanDescriptor<T> extends PropertyVisitor<T> {
    /**
     * Gets the bean properties.
     *
     * @return 返回beanProperties
     */
    Collection<Property<T, ?>> getProperties();

    /**
     * 返回指定索引属性.
     *
     * @param <P> the property type
     * @param index 索引
     * @return 指定属性
     */
    <P> Property<T, P> getProperty(int index);

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <B> the generic type
     * @param <E> the element type
     * @param name 属性名
     * @return 指定属性
     */
    <P> BeanProperty<T, P> getProperty(String name);

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    <R> BeanProperty<T, R> getProperty(SerializableFunction<T, R> property);

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <E> the element type
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    <E, R> BeanProperty<T, R> getProperty(SerializableBiConsumer<E, R> property);

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    <R> BeanProperty<T, R> getProperty(SerializableConsumer<R> property);

    /**
     * 返回指定属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param <R> the property type
     * @param property 属性
     * @return 指定属性
     */
    <R> Property<T, R> getProperty(SerializableSupplier<R> property);

    /**
     * 返回指定属性是否存在
     * .
     *
     * @param name 属性名
     * @return 指定属性是否存在
     */
    boolean hasProperty(String name);

    /**
     * 返回指定子孙属性. 如果没有则抛出NoSuchPropertyException异常.
     *
     * @param name 属性名
     * @return 指定属性
     */
    BeanProperty<?, ?> getChildBeanProperty(String name);

    /**
     * 查找并返回所有符合条件BeanProperty的集合. 如果没有则返回一个长度为0的集合.
     *
     * @param condition 条件判断
     * @return 所有符合条件BeanProperty的集合
     */
    Collection<Property<?, ?>> findPropertys(Predicate<Property<T, ?>> condition);

    /**
     * Whether the current bean type contains the specified annotation.
     *
     * @param <A> annotation type
     * @param annotationClass annotaion class
     * @return contains the specified annotation.
     */
    <A extends Annotation> boolean hasAnnotation(Class<A> annotationClass);

    /**
     * get the specified annotation of bean type.
     *
     * @param <A> annotation type
     * @param annotationClass annotation class type
     * @return annotation.
     */
    <A extends Annotation> A getAnnotation(Class<A> annotationClass);

    /**
     * get all annotation array of bean type.
     *
     * @return annotation array
     */
    Annotation[] getAnnotations();

    /**
     * get bean type.
     *
     * @return the bean type
     */
    Class<T> getBeanType();

    /**
     * add property value. If the property type is Collection, the parameter is added, and if it is
     * of any other type, it is the same as setPropertyValue
     * 添加属性值.
     * 如果属性类型是{@link java.util.Collection Collection}
     * 则会把参数添加进去，如果是其他类型则和{@link #setPropertyValue(Object, String, Object) setPropertyValue}一样
     *
     * @param bean bean object
     * @param name property name
     * @param value property value
     */
    void addPropertyValue(T bean, String name, Object value);

}
