
package cn.featherfly.common.structure;

import java.util.Map;

/**
 * <p>
 * chian put map
 * </p>
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * @author zhongj
 */
public interface ChainMap<K, V> extends Map<K, V> {
    /**
     * <p>
     * 带链式调用的put方法. {@link java.util.Map#put(Object, Object)}
     * </p>
     *
     * @param key   指定值将要关联的键。
     * @param value 指定键将要关联的值。
     * @return 当前MAP
     */
    ChainMap<K, V> putChain(K key, V value);

    /**
     * <p>
     * 带链式调用的putAll方法. {@link java.util.Map#putAll(Map)}
     * </p>
     *
     * @param m 要在此映射中存储的映射关系
     * @return 当前MAP
     */
    ChainMap<K, V> putAllChain(Map<? extends K, ? extends V> m);
}
