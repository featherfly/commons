
package cn.featherfly.common.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * ThreadLocalMap
 * </p>
 * .
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class ThreadLocalMap<K, V> extends ThreadLocal<Map<K, V>> {

    /**
     * Put value with key.
     *
     * @param key   the key
     * @param value the value
     * @return the thread local map
     */
    public ThreadLocalMap<K, V> put(K key, V value) {
        get().put(key, value);
        return this;
    }

    /**
     * Gets the key of value.
     *
     * @param key the key
     * @return the v
     */
    public V get(K key) {
        return get().get(key);
    }

    /**
     * Removes the key.
     *
     * @param key the key
     * @return the thread local map
     */
    public ThreadLocalMap<K, V> remove(K key) {
        get().remove(key);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<K, V> initialValue() {
        return new HashMap<>();
    }
}
