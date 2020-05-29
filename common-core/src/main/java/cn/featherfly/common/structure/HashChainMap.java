
package cn.featherfly.common.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 带链式调用的HASHMAP
 * </p>
 *
 * @param <K> 键的类型
 * @param <V> 值的类型
 * @author zhongj
 */
public class HashChainMap<K, V> extends HashMap<K, V> implements ChainMap<K, V> {

    private static final long serialVersionUID = 7403898439744127401L;

    /**
     * @see java.util.HashMap
     */
    public HashChainMap() {
        super();
    }

    /**
     * @see java.util.HashMap
     * @param initialCapacity 初始化的大小.
     */
    public HashChainMap(int initialCapacity) {
        super(initialCapacity);
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
