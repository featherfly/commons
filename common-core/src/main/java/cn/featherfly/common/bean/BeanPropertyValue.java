
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

/**
 * BeanPropertyValue.
 *
 * @author zhongj
 */
public class BeanPropertyValue<T> {

    private BeanProperty<T> beanProperty;

    private T value;

    /**
     * @param beanProperty
     * @param value
     */
    public BeanPropertyValue(BeanProperty<T> beanProperty, T value) {
        super();
        this.beanProperty = beanProperty;
        this.value = value;
    }

    /**
     * get beanProperty value
     *
     * @return beanProperty
     */
    public BeanProperty<T> getBeanProperty() {
        return beanProperty;
    }

    /**
     * get value value
     *
     * @return value
     */
    public T getValue() {
        return value;
    }

}
