package cn.featherfly.common.bean;

/**
 * Property.
 *
 * @author zhongj
 * @param <T> the bean type
 * @param <V> the value type
 * @since 1.13.0
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

    /**
     * whether the current property is a readable property and(has getter).
     * 当前属性是否是可读属性，拥有getter .
     *
     * @return true, if is readable
     */
    boolean isReadable();

    /**
     * whether the current property is writable(has setter).
     * 当前属性是否是可写属性，拥有setter .
     *
     * @return true, if is writable
     */
    boolean isWritable();
}