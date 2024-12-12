
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-11 15:25:11
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * immutable map.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class ImmutableMap<K, V> extends MapProxy<K, V> {

    private static final long serialVersionUID = -8832124293428379343L;

    /**
     * Instantiates a new immutable map.
     */
    public ImmutableMap() {
        this(Collections.emptyMap());
    }

    /**
     * Instantiates a new immutable map.
     *
     * @param map the map
     */
    public ImmutableMap(Map<K, V> map) {
        super(map);
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V replace(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return new ImmutableSet<>(super.keySet());
    }

    @Override
    public Collection<V> values() {
        return new ImmutableCollection<>(super.values());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new ImmutableSet<>(super.entrySet());
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
