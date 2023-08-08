
package cn.featherfly.common.lang.function;

/**
 * The Interface MapSupplier.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 * @deprecated {@link SerializableMapSupplier}
 */
@FunctionalInterface
@Deprecated
public interface MapSupplier<K, V> extends SerializableMapSupplier<K, V> {

}
