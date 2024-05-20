
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanPropertyDescriptor.java
 * @Description: BeanPropertyDescriptor
 * @author: zhongj
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * BeanPropertyDescriptor.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface PropertyDescriptor<T, V> {

    /**
     * Gets the instance type.
     *
     * @return the instance type
     */
    Class<T> getInstanceType();

    /**
     * Gets the property type.
     *
     * @return the property type
     */
    Class<V> getType();

    /**
     * Gets the property name.
     *
     * @return the property name
     */
    String getName();

    /**
     * Gets the index.
     *
     * @return the property index
     */
    int getIndex();

    //    /**
    //     * Gets the value.
    //     *
    //     * @param obj the obj
    //     * @return the value
    //     */
    //    <TS extends T> V getValue(TS obj);

}
