
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
 * @param <T> the generic type
 */
public class BeanPropertyValue<T> implements ValueType<T> {

    private BeanProperty<T> beanProperty;

    private T value;

    /**
     * Instantiates a new bean property value.
     *
     * @param beanProperty the bean property
     * @param value        the value
     */
    public BeanPropertyValue(BeanProperty<T> beanProperty, T value) {
        super();
        this.beanProperty = beanProperty;
        this.value = value;
    }

    /**
     * get beanProperty value.
     *
     * @return beanProperty
     */
    public BeanProperty<T> getBeanProperty() {
        return beanProperty;
    }

    /**
     * get value value.
     *
     * @return value
     */
    @Override
    public T getValue() {
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
    public Class<T> getType() {
        return beanProperty.getType();
    }

}
