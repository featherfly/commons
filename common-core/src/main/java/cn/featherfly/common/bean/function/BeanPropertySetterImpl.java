package cn.featherfly.common.bean.function;

import cn.featherfly.common.bean.BeanUtils;

/**
 * The Class BeanPropertySetterImpl.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <V> the generic property value type
 */
public class BeanPropertySetterImpl<T, V> implements BeanPropertySetter<T, V> {

    private static final long serialVersionUID = -3604947046086702819L;

    private final Class<T> beanType;

    private final Class<V> propertyType;

    private final String propertyName;

    private final int index;

    /**
     * Instantiates a new bean property supplier impl.
     *
     * @param beanType the bean type
     * @param propertyType the property type
     * @param propertyName the property name
     * @param index the index
     */
    public BeanPropertySetterImpl(Class<T> beanType, Class<V> propertyType, String propertyName, int index) {
        super();
        this.beanType = beanType;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getInstanceType() {
        return beanType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return propertyName;
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
    public void accept(T t, V u) {
        BeanUtils.setProperty(t, propertyName, u);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return index;
    }
}