package cn.featherfly.common.bean.function;

import cn.featherfly.common.bean.BeanUtils;

/**
 * The Class BeanPropertySupplierImpl.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <V> the generic property value type
 */
public class BeanPropertyGetterImpl<T, V> implements BeanPropertyGetter<T, V> {

    private static final long serialVersionUID = -3604947046086702819L;

    private Class<T> beanType;

    private Class<V> propertyType;

    private String propertyName;

    /**
     * Instantiates a new bean property supplier impl.
     *
     * @param beanType     the bean type
     * @param propertyType the property type
     * @param propertyName the property name
     */
    public BeanPropertyGetterImpl(Class<T> beanType, Class<V> propertyType, String propertyName) {
        super();
        this.beanType = beanType;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
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

    @Override
    public Class<V> getType() {
        return propertyType;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public V apply(T t) {
        return (V) BeanUtils.getProperty(t, propertyName);
    }
}