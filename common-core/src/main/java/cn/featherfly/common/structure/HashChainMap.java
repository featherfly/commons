
package cn.featherfly.common.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 带链式调用的HASHMAP.
 *
 * @author zhongj
 * @param <K> 键的类型
 * @param <V> 值的类型
 */
public class HashChainMap<K, V> extends HashMap<K, V> implements ChainMap<K, V> {

    private static final long serialVersionUID = 7403898439744127401L;

    /**
     * Instantiates a new hash chain map.
     *
     * @see java.util.HashMap
     */
    public HashChainMap() {
        super();
    }

    /**
     * Instantiates a new hash chain map.
     *
     * @param initialCapacity 初始化的大小.
     * @see java.util.HashMap
     */
    public HashChainMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Instantiates a new hash chain map.
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     */
    public HashChainMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Instantiates a new hash chain map.
     *
     * @param m the m
     */
    public HashChainMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChainMap<K, V> putChain(K key, V value) {
        put(key, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChainMap<K, V> putAllChain(Map<? extends K, ? extends V> m) {
        putAll(m);
        return this;
    }
}
