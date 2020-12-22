
package cn.featherfly.common.structure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * chian invoke ConcurrentHashMap.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class ConcurrentHashChainMap<K, V> extends ConcurrentHashMap<K, V> implements ChainMap<K, V> {

    private static final long serialVersionUID = 1913544534596071359L;

    /**
     * Instantiates a new concurrent hash chain map.
     */
    public ConcurrentHashChainMap() {
        super();
    }

    /**
     * Instantiates a new concurrent hash chain map.
     *
     * @param initialCapacity  the initial capacity
     * @param loadFactor       the load factor
     * @param concurrencyLevel the concurrency level
     */
    public ConcurrentHashChainMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

    /**
     * Instantiates a new concurrent hash chain map.
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     */
    public ConcurrentHashChainMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Instantiates a new concurrent hash chain map.
     *
     * @param initialCapacity the initial capacity
     */
    public ConcurrentHashChainMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Instantiates a new concurrent hash chain map.
     *
     * @param m the m
     */
    public ConcurrentHashChainMap(Map<? extends K, ? extends V> m) {
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
