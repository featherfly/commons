
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 17:00:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * AbstractPropertyDescriptor.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <V> the value type
 */
public abstract class AbstractPropertyDescriptor<T, V> implements PropertyDescriptor<T, V> {

    /** The instance type. */
    protected final Class<T> instanceType;

    /** The property type. */
    protected final Class<V> propertyType;

    /** The name. */
    protected final String name;

    /** The index. */
    protected int index = -1;

    /**
     * Instantiates a new abstract property descriptor.
     *
     * @param instanceType the instance type
     * @param propertyType the property type
     * @param name the name
     */
    public AbstractPropertyDescriptor(Class<T> instanceType, Class<V> propertyType, String name) {
        this(instanceType, propertyType, name, -1);
    }

    /**
     * Instantiates a new abstract property descriptor.
     *
     * @param instanceType the instance type
     * @param propertyType the property type
     * @param name the name
     * @param index the index
     */
    public AbstractPropertyDescriptor(Class<T> instanceType, Class<V> propertyType, String name, int index) {
        super();
        this.instanceType = instanceType;
        this.propertyType = propertyType;
        this.name = name;
        this.index = index;
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

    /**
     * Gets the index.
     *
     * @return the index
     */
    @Override
    public int getIndex() {
        return index;
    }
}
