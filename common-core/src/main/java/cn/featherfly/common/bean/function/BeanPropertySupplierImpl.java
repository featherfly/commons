package cn.featherfly.common.bean.function;

/**
 * The Class BeanPropertySupplierImpl.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <V> the generic property value type
 */
public class BeanPropertySupplierImpl<T, V> implements BeanPropertySupplier<T, V> {

    private static final long serialVersionUID = -3604947046086702819L;

    private Class<T> beanType;

    private Class<V> propertyType;

    private V propertyValue;

    private String propertyName;

    /**
     * Instantiates a new bean property supplier impl.
     *
     * @param beanType      the bean type
     * @param propertyType  the property type
     * @param propertyValue the property value
     * @param propertyName  the property name
     */
    public BeanPropertySupplierImpl(Class<T> beanType, Class<V> propertyType, V propertyValue, String propertyName) {
        super();
        this.beanType = beanType;
        this.propertyType = propertyType;
        this.propertyValue = propertyValue;
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
    @Override
    public V get() {
        return propertyValue;
    }
}