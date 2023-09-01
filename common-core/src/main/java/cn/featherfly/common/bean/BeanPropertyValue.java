
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanPropertyValue.java
 * @Package cn.featherfly.common.bean
 * @Description: BeanPropertyValue
 * @author: zhongj
 * @date: 2021-12-05 18:45:05
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import cn.featherfly.common.lang.vt.ValueType;

/**
 * BeanPropertyValue.
 *
 * @author zhongj
 * @param <T> the bean generic type
 * @param <V> the property generic type
 */
public class BeanPropertyValue<T, V> implements ValueType<V> {

    private BeanProperty<T, V> beanProperty;

    private V value;

    /**
     * Instantiates a new bean property value.
     *
     * @param beanProperty the bean property
     * @param value        the value
     */
    public BeanPropertyValue(BeanProperty<T, V> beanProperty, V value) {
        super();
        this.beanProperty = beanProperty;
        this.value = value;
    }

    /**
     * get beanProperty value.
     *
     * @return beanProperty
     */
    public BeanProperty<T, V> getBeanProperty() {
        return beanProperty;
    }

    /**
     * get value value.
     *
     * @return value
     */
    @Override
    public V getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[beanProperty=" + beanProperty + ", value=" + value + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<V> getType() {
        return beanProperty.getType();
    }

}
