package cn.featherfly.common.bean;

/**
 * Property.
 *
 * @author zhongj
 * @param <T> the bean type
 * @param <V> the value type
 */
public interface Property<T, V> extends Setter<T, V>, Getter<T, V>, PropertyDescriptor<T, V> {

    Property<?, ?>[] EMPTY_ARRAY = new Property[0];

    /**
     * Gets the property accessor.
     *
     * @return the property accessor
     */
    default PropertyAccessor<V> getPropertyAccessor() {
        return null;
    }
}