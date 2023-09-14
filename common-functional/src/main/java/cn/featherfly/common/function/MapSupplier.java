
package cn.featherfly.common.function;

import java.util.Map;
import java.util.function.Supplier;

/**
 * MapSupplier.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 * @see java.util.function.Supplier
 */
public interface MapSupplier<K, V> extends Supplier<Map<K, V>> {

}
