
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ChainMapImpl.java
 * @Package cn.featherfly.common.structure
 * @Description: ChainMap Implement
 * @author: zhongj
 * @date: 2021-11-18 20:31:18
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * ChainMapImpl.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class ChainMapImpl<K, V> implements ChainMap<K, V> {

    private final Map<K, V> map;

    /**
     * Instantiates a new chain map impl.
     */
    public ChainMapImpl() {
        this(new HashMap<>(0));
    }

    /**
     * Instantiates a new chain map impl.
     *
     * @param map the map
     */
    public ChainMapImpl(Map<K, V> map) {
        super();
        this.map = map;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
        return map.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {
        return map.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {
        return map.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return map.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        map.replaceAll(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V putIfAbsent(K key, V value) {
        return map.putIfAbsent(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return map.replace(key, oldValue, newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V replace(K key, V value) {
        return map.replace(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.computeIfPresent(key, remappingFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.compute(key, remappingFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return map.toString();
    }
}
