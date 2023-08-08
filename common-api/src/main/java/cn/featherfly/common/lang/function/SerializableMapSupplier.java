
package cn.featherfly.common.lang.function;

import java.util.Map;

/**
 * The Interface SerializableMapSupplier.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
@FunctionalInterface
public interface SerializableMapSupplier<K, V> extends SerializableSupplier<Map<K, V>> {

}
