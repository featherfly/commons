
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 01:34:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * property accessor.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface PropertyAccessor<T> extends Instantiator<T> {

    /**
     * Support access by index.
     *
     * @return true, if successful
     */
    default boolean supportAccessByIndex() {
        return true;
    }

    /**
     * Support access by name.
     *
     * @return true, if successful
     */
    default boolean supportAccessByName() {
        return true;
    }

    /**
     * Support metadata.
     *
     * @return true, if successful
     */
    default boolean supporMetadata() {
        return true;
    }

    /**
     * gets the value from bean object of the specified name property. <br>
     * 返回属性值.
     *
     * @param obj the obj
     * @param index the propety index
     * @return propery value
     */
    Object getPropertyValue(T obj, int index);

    /**
     * gets the value from bean object of the specified name property. <br>
     * 返回属性值.
     *
     * @param obj the obj
     * @param indexes the indexes
     * @return propery value
     */
    Object getPropertyValue(T obj, int... indexes);

    /**
     * gets the value from bean object of the specified name property. <br>
     * 返回属性值.
     *
     * @param obj the obj
     * @param names property names
     * @return propery value
     */
    Object getPropertyValue(T obj, String... names);

    /**
     * gets the value from bean object of the specified name property. <br>
     * 返回属性值.
     *
     * @param obj the obj
     * @param name property name
     * @return propery value
     */
    Object getPropertyValue(T obj, String name);

    /**
     * sets the value of the specified name property of the bean object. <br>
     * 设置属性值.
     *
     * @param obj the obj
     * @param index the property index
     * @param value property value
     */
    void setPropertyValue(T obj, int index, Object value);

    /**
     * sets the value of the specified name property of the bean object. <br>
     * 设置属性值.
     *
     * @param obj the obj
     * @param indexes the property indexes
     * @param value property value
     */
    void setPropertyValue(T obj, int[] indexes, Object value);

    /**
     * sets the value of the specified name property of the bean object. <br>
     * 设置属性值.
     *
     * @param obj the obj
     * @param name property name
     * @param value property value
     */
    void setPropertyValue(T obj, String name, Object value);

    /**
     * sets the value of the specified name property of the bean object. <br>
     * 设置属性值.
     *
     * @param obj the obj
     * @param names property names
     * @param value property value
     */
    void setPropertyValue(T obj, String[] names, Object value);

    /**
     * Gets the property index.
     *
     * @param name the property name
     * @return the index
     */
    int getPropertyIndex(String name);

    /**
     * Gets the index.
     *
     * @param names the names
     * @return the index
     */
    int[] getPropertyIndexes(String... names);

    /**
     * Gets the property.
     *
     * @param <V> the value type
     * @param index the index
     * @return the property
     */
    <V> Property<T, V> getProperty(int index);

    /**
     * Gets the property.
     *
     * @param <V> the value type
     * @param name the name
     * @return the property
     */
    <V> Property<T, V> getProperty(String name);

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    Property<T, ?>[] getProperties();
}
