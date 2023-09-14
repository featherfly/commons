
package cn.featherfly.common.function.serializable;

import java.util.Map;

import cn.featherfly.common.function.MapSupplier;

/**
 * The Interface SerializableMapSupplier.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 * @see SerializableSupplier
 * @see MapSupplier
 */
@FunctionalInterface
public interface SerializableMapSupplier<K, V> extends SerializableSupplier<Map<K, V>>, MapSupplier<K, V> {

}
