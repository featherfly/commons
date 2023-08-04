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

    private Class<T> type;

    private V value;

    private String propertyName;

    /**
     * Instantiates a new bean property supplier impl.
     *
     * @param type         the type
     * @param value        the value
     * @param propertyName the property name
     */
    public BeanPropertySupplierImpl(Class<T> type, V value, String propertyName) {
        super();
        this.type = type;
        this.value = value;
        this.propertyName = propertyName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getBeanType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get() {
        return value;
    }
}