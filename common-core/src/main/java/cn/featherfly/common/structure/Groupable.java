
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Groupable.java
 * @Package cn.featherfly.common.structure
 * @Description: Groupable
 * @author: zhongj
 * @date: 2021-11-18 20:19:18
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.featherfly.common.lang.Lang;

/**
 * Groupable.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class Groupable<K, V> {

    private V defaultValue;

    private final Map<K, V> groupValues;

    /**
     * Instantiates a new groupable.
     *
     * @param defaultValue the default value
     */
    public Groupable(V defaultValue) {
        this(defaultValue, null);
    }

    /**
     * Instantiates a new groupable.
     *
     * @param defaultValue the default value
     * @param groupValues  the group values
     */
    public Groupable(V defaultValue, Map<K, V> groupValues) {
        super();
        this.defaultValue = defaultValue;
        if (groupValues == null) {
            this.groupValues = new HashMap<>(0);
        } else {
            this.groupValues = groupValues;
        }
    }

    /**
     * Adds the group.
     *
     * @param group the group
     * @param value the value
     * @return the groupable
     */
    public Groupable<K, V> addGroup(K group, V value) {
        groupValues.put(group, value);
        return this;
    }

    /**
     * Removes the group.
     *
     * @param key the key
     * @return the v
     */
    public V removeGroup(Object key) {
        return groupValues.remove(key);
    }

    /**
     * Gets the value by group.
     *
     * @param group the group
     * @return the value by group
     */
    public V getValueByGroup(K group) {
        return groupValues.get(group);
    }

    /**
     * Gets the value.
     *
     * @param group the group
     * @return the value
     */
    public V getValue(K group) {
        V d = getValueByGroup(group);
        if (Lang.isNotEmpty(d)) {
            return d;
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public V getValue() {
        return defaultValue;
    }

    /**
     * Contains group.
     *
     * @param group the group
     * @return true, if successful
     */
    public boolean containsGroup(K group) {
        return groupValues.containsKey(group);
    }

    /**
     * Contains value.
     *
     * @param value the value
     * @return true, if successful
     */
    public boolean containsValue(V value) {
        return groupValues.containsValue(value);
    }

    /**
     * Entry set.
     *
     * @return the sets the
     */
    public Set<Entry<K, V>> entrySet() {
        return groupValues.entrySet();
    }

    /**
     * Group set.
     *
     * @return the sets the
     */
    public Set<K> groupSet() {
        return groupValues.keySet();
    }

    /**
     * Values.
     *
     * @return the collection
     */
    public Collection<V> values() {
        return groupValues.values();
    }

}
