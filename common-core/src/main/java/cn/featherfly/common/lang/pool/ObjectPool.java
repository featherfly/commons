
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 17:27:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.pool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ObjectPool.
 *
 * @author zhongj
 * @param <O> the generic type
 * @param <P> the generic type
 */
public class ObjectPool<O, P extends ObjectPool<O, P>> implements Pool<Class<?>, O, P> {

    private final Map<Class<?>, O> objects = new HashMap<>(0);

    /**
     * Instantiates a new property accessor manager.
     */
    public ObjectPool() {
        super();
    }

    /**
     * Gets the object.
     *
     * @param type the type
     * @return the o
     */
    @Override
    public O get(Class<?> type) {
        return objects.get(type);
    }

    /**
     * Adds the.
     *
     * @param type the type
     * @param obj the obj
     * @return the property accessor manager
     */
    @Override
    @SuppressWarnings("unchecked")
    public P add(Class<?> type, O obj) {
        objects.put(type, obj);
        return (P) this;
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    @Override
    public Collection<O> getAll() {
        return objects.values();
    }

    /**
     * Contains type.
     *
     * @param type the type
     * @return true, if successful
     */
    public boolean containsType(Class<?> type) {
        return objects.containsKey(type);
    }

    /**
     * Contains object.
     *
     * @param obj the obj
     * @return true, if successful
     */
    public boolean containsObject(Object obj) {
        return objects.containsValue(obj);
    }

    @Override
    public O remove(Class<?> key) {
        return objects.remove(key);
    }

    @Override
    public boolean remove(Class<?> key, O value) {
        return objects.remove(key, value);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        objects.clear();
    }

    /**
     * Adds the all.
     *
     * @param objs the objs
     * @return the property accessor manager
     */
    @Override
    @SuppressWarnings("unchecked")
    public P addAll(Map<Class<?>, O> objs) {
        objects.putAll(objs);
        return (P) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Class<?> type) {
        return containsType(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(O value) {
        return containsObject(value);
    }
}
