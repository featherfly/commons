
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 17:00:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * abstract property.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <V> the value type
 * @since 1.13.0
 */
public abstract class AbstractProperty<T, V> extends AbstractPropertyDescriptor<T, V> implements Property<T, V> {

    private PropertyAccessor<V> propertyAccessor;

    /**
     * Instantiates a new abstract property descriptor.
     *
     * @param instanceType the instance type
     * @param propertyType the property type
     * @param name the name
     * @param index the index
     * @param propertyAccessor the property accessor
     */
    protected AbstractProperty(Class<T> instanceType, Class<V> propertyType, String name, int index,
        PropertyAccessor<V> propertyAccessor) {
        super(instanceType, propertyType, name, index);
        this.propertyAccessor = propertyAccessor;
    }

    /**
     * set propertyAccessor value
     *
     * @param propertyAccessor propertyAccessor
     */
    public void setPropertyAccessor(PropertyAccessor<V> propertyAccessor) {
        this.propertyAccessor = propertyAccessor;
    }

    /**
     * Gets the property visitor.
     *
     * @return the property visitor
     */
    @Override
    public PropertyAccessor<V> getPropertyAccessor() {
        return propertyAccessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getInstanceType() {
        return instanceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<V> getType() {
        return propertyType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
