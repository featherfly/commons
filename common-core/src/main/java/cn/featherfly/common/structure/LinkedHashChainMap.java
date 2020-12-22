
package cn.featherfly.common.structure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * chian invoke TreeMap.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class LinkedHashChainMap<K, V> extends LinkedHashMap<K, V> implements ChainMap<K, V> {

    private static final long serialVersionUID = 1913544534596071359L;

    /**
     * Instantiates a new linked hash chain map.
     */
    public LinkedHashChainMap() {
        super();
    }

    /**
     * Instantiates a new linked hash chain map.
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     * @param accessOrder     the access order
     */
    public LinkedHashChainMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * Instantiates a new linked hash chain map.
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     */
    public LinkedHashChainMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Instantiates a new linked hash chain map.
     *
     * @param initialCapacity the initial capacity
     */
    public LinkedHashChainMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Instantiates a new linked hash chain map.
     *
     * @param m the m
     */
    public LinkedHashChainMap(Map<? extends K, ? extends V> m) {
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
