
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 17:31:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.pool;

import java.util.Collection;
import java.util.Map;

/**
 * Pool.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 * @param <P> the generic type
 */
public interface Pool<K, V, P extends Pool<K, V, P>> {

    /**
     * Gets the object.
     *
     * @param key the key
     * @return the o
     */
    V get(K key);

    /**
     * Adds the.
     *
     * @param key the key
     * @param obj the obj
     * @return the property accessor manager
     */
    P add(K key, V obj);

    /**
     * Adds the all.
     *
     * @param objs the objs
     * @return the property accessor manager
     */
    P addAll(Map<K, V> objs);

    /**
     * Gets the all.
     *
     * @return the all
     */
    Collection<V> getAll();

    /**
     * Contains key.
     *
     * @param type the type
     * @return true, if successful
     */
    boolean containsKey(K type);

    /**
     * Contains value.
     *
     * @param value the value
     * @return true, if successful
     */
    boolean containsValue(V value);

    /**
     * Removes the.
     *
     * @param key the key
     * @return the o
     */
    V remove(K key);

    /**
     * Removes the.
     *
     * @param key the key
     * @param value the value
     * @return true, if successful
     */
    boolean remove(K key, V value);

    /**
     * Clear.
     */
    void clear();
}
