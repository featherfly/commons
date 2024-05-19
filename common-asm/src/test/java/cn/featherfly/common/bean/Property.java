package cn.featherfly.common.bean;

/**
 * Property.
 *
 * @param <T> the bean type
 * @param <P> the property type
 */
public interface Property<T, V> extends Setter<T, V>, Getter<T, V> {

}