
package cn.featherfly.common.structure;

import java.util.Map;

/**
 * chian put map.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * @author zhongj
 */
public interface ChainMap<K, V> extends Map<K, V> {
    /**
     * 带链式调用的put方法. {@link java.util.Map#put(Object, Object)}.
     *
     * @param key   指定值将要关联的键。
     * @param value 指定键将要关联的值。
     * @return 当前MAP
     */
    ChainMap<K, V> putChain(K key, V value);

    /**
     * 带链式调用的putAll方法. {@link java.util.Map#putAll(Map)}.
     *
     * @param m 要在此映射中存储的映射关系
     * @return 当前MAP
     */
    ChainMap<K, V> putAllChain(Map<? extends K, ? extends V> m);
}
