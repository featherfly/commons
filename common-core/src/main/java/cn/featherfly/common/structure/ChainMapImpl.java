
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
     * Size.
     *
     * @return the int
     * @see java.util.Map#size()
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     * @see java.util.Map#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Contains key.
     *
     * @param key the key
     * @return true, if successful
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * Contains value.
     *
     * @param value the value
     * @return true, if successful
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the v
     * @see java.util.Map#get(java.lang.Object)
     */
    @Override
    public V get(Object key) {
        return map.get(key);
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     * @return the v
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    /**
     * Removes the.
     *
     * @param key the key
     * @return the v
     * @see java.util.Map#remove(java.lang.Object)
     */
    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    /**
     * Put all.
     *
     * @param m the m
     * @see java.util.Map#putAll(java.util.Map)
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    /**
     * Clear.
     *
     * @see java.util.Map#clear()
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * Key set.
     *
     * @return the sets the
     * @see java.util.Map#keySet()
     */
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Values.
     *
     * @return the collection
     * @see java.util.Map#values()
     */
    @Override
    public Collection<V> values() {
        return map.values();
    }

    /**
     * Entry set.
     *
     * @return the sets the
     * @see java.util.Map#entrySet()
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     * @see java.util.Map#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    /**
     * Hash code.
     *
     * @return the int
     * @see java.util.Map#hashCode()
     */
    @Override
    public int hashCode() {
        return map.hashCode();
    }

    /**
     * Gets the or default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the or default
     * @see java.util.Map#getOrDefault(java.lang.Object, java.lang.Object)
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * For each.
     *
     * @param action the action
     * @see java.util.Map#forEach(java.util.function.BiConsumer)
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    /**
     * Replace all.
     *
     * @param function the function
     * @see java.util.Map#replaceAll(java.util.function.BiFunction)
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        map.replaceAll(function);
    }

    /**
     * Put if absent.
     *
     * @param key   the key
     * @param value the value
     * @return the v
     * @see java.util.Map#putIfAbsent(java.lang.Object, java.lang.Object)
     */
    @Override
    public V putIfAbsent(K key, V value) {
        return map.putIfAbsent(key, value);
    }

    /**
     * Removes the.
     *
     * @param key   the key
     * @param value the value
     * @return true, if successful
     * @see java.util.Map#remove(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    /**
     * Replace.
     *
     * @param key      the key
     * @param oldValue the old value
     * @param newValue the new value
     * @return true, if successful
     * @see java.util.Map#replace(java.lang.Object, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return map.replace(key, oldValue, newValue);
    }

    /**
     * Replace.
     *
     * @param key   the key
     * @param value the value
     * @return the v
     * @see java.util.Map#replace(java.lang.Object, java.lang.Object)
     */
    @Override
    public V replace(K key, V value) {
        return map.replace(key, value);
    }

    /**
     * Compute if absent.
     *
     * @param key             the key
     * @param mappingFunction the mapping function
     * @return the v
     * @see java.util.Map#computeIfAbsent(java.lang.Object,
     *      java.util.function.Function)
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    /**
     * Compute if present.
     *
     * @param key               the key
     * @param remappingFunction the remapping function
     * @return the v
     * @see java.util.Map#computeIfPresent(java.lang.Object,
     *      java.util.function.BiFunction)
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.computeIfPresent(key, remappingFunction);
    }

    /**
     * Compute.
     *
     * @param key               the key
     * @param remappingFunction the remapping function
     * @return the v
     * @see java.util.Map#compute(java.lang.Object,
     *      java.util.function.BiFunction)
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.compute(key, remappingFunction);
    }

    /**
     * Merge.
     *
     * @param key               the key
     * @param value             the value
     * @param remappingFunction the remapping function
     * @return the v
     * @see java.util.Map#merge(java.lang.Object, java.lang.Object,
     *      java.util.function.BiFunction)
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return map.toString();
    }
}
