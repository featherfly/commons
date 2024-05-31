
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 18:17:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.pool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractPool.
 *
 * @author zhongj
 */
public class PoolImpl<K, V, P extends Pool<K, V, P>> implements Pool<K, V, P> {

    private final Map<K, V> map = new HashMap<>(0);

    /**
     * Instantiates a new property accessor manager.
     */
    public PoolImpl() {
        super();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public P add(K key, V value) {
        map.put(key, value);
        return (P) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public P addAll(Map<K, V> values) {
        map.putAll(values);
        return (P) this;
    }

    @Override
    public Collection<V> getAll() {
        return map.values();
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(K key, V value) {
        return map.remove(key, value);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(K type) {
        return map.containsKey(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return map.containsValue(value);
    }
}
