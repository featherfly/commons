
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanPropertyDescriptor.java
 * @Description: BeanPropertyDescriptor
 * @author: zhongj
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import cn.featherfly.common.lang.reflect.Type;

/**
 * BeanPropertyDescriptor.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface PropertyDescriptor<T, V> extends Type<V> {

    /**
     * Gets the instance type.
     *
     * @return the instance type
     */
    Class<T> getInstanceType();

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
}
