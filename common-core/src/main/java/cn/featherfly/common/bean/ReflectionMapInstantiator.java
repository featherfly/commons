
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-31 18:16:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.CollectionUtils;

/**
 * MapInstantiator.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class ReflectionMapInstantiator<K, V> implements Instantiator<Map<K, V>> {

    private final Class<?> type;

    /**
     * Instantiates a new map instantiator.
     */
    @SuppressWarnings("unchecked")
    public ReflectionMapInstantiator() {
        this(HashMap.class);
    }

    /**
     * Instantiates a new map instantiator.
     *
     * @param <M> the Map type
     * @param type the type
     */
    public <M extends Map<K, V>> ReflectionMapInstantiator(Class<M> type) {
        super();
        this.type = type;
    }

    /**
     * Creates MapInstantiator.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param type map type
     * @return the map instantiator
     */
    @SuppressWarnings("unchecked")
    public static <K, V> ReflectionMapInstantiator<K, V> create(Class<?> type) {
        AssertIllegalArgument.isParent(Map.class, type);
        return new ReflectionMapInstantiator<>((Class<Map<K, V>>) type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<K, V> instantiate() {
        return CollectionUtils.newMap(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class<Map<K, V>> getType() {
        return (Class<Map<K, V>>) type;
    }
}
