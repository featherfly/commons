
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 01:34:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * PropertyVisitor.
 *
 * @author zhongj
 */
public interface PropertyVisitor<T> {

    /**
     * gets the value from bean object of the specified name property. <br>
     * 返回属性值.
     *
     * @param bean bean object
     * @param name property name
     * @return propery value
     */
    <V> V getPropertyValue(T bean, String name);

    /**
     * sets the value of the specified name property of the bean object. <br>
     * 设置属性值.
     *
     * @param bean bean object
     * @param name property name
     * @param value property value
     */
    void setPropertyValue(T bean, String name, Object value);
}
