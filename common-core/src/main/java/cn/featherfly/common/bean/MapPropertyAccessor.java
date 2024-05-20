
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 17:08:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Strings;

/**
 * MapPropertyAccessor.
 *
 * @author zhongj
 */
public class MapPropertyAccessor implements PropertyAccessor<Map<Object, Object>> {

    private final Instantiator<Map<Object, Object>> instantiator;

    private final PropertyAccessorManager manager;

    /**
     * Instantiates a new map property accessor.
     *
     * @param instantiator the instantiator
     * @param manager the manager
     */
    public MapPropertyAccessor(Instantiator<Map<Object, Object>> instantiator, PropertyAccessorManager manager) {
        super();
        AssertIllegalArgument.isParent(Map.class, instantiator.getType());
        this.instantiator = instantiator;
        this.manager = manager;
    }

    @Override
    public boolean supporMetadata() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<Object, Object> obj, int index) {
        if (obj == null || index >= obj.size()) {
            return null;
        }
        int i = 0;
        for (Object value : obj.values()) {
            if (index == i) {
                return value;
            }
            i++;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<Object, Object> obj, String name) {
        return obj.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<Object, Object> obj, int index, Object value) {
        if (obj == null || index >= obj.size()) {
            return;
        }
        int i = 0;
        for (Object key : obj.keySet()) {
            if (index == i) {
                obj.put(key, value);
                return;
            }
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<Object, Object> obj, String name, Object value) {
        obj.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<Object, Object> obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
        Object transitional = getPropertyValue(obj, indexes[0]);
        if (transitional == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        PropertyAccessor<
            Object> propertyAccessor = (PropertyAccessor<Object>) manager.getPropertyAccessor(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], transitional);
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<Object, Object> obj, String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return getPropertyValue(obj, names[0]);
        }
        Object transitional = getPropertyValue(obj, names[0]);
        if (transitional == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        PropertyAccessor<
            Object> propertyAccessor = (PropertyAccessor<Object>) manager.getPropertyAccessor(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, names[0], transitional);
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<Object, Object> obj, int[] indexes, Object value) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            setPropertyValue(obj, indexes[0], value);
            return;
        }

        Object transitional = getPropertyValue(obj, indexes[0]);
        if (transitional == null) {
            // map does not have transitional object metadata, can not instantiate it
            return;
        }
        @SuppressWarnings("unchecked")
        PropertyAccessor<
            Object> propertyAccessor = (PropertyAccessor<Object>) manager.getPropertyAccessor(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], transitional);
        propertyAccessor.setPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<Object, Object> obj, String[] names, Object value) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            setPropertyValue(obj, names[0], value);
            return;
        }
        Object transitional = getPropertyValue(obj, names[0]);
        if (transitional == null) {
            // map does not have transitional object metadata, can not instantiate it
            return;
        }
        @SuppressWarnings("unchecked")
        PropertyAccessor<
            Object> propertyAccessor = (PropertyAccessor<Object>) manager.getPropertyAccessor(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, names[0], transitional);
        propertyAccessor.setPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyIndex(String name) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndex(String), because map does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getPropertyIndexes(String... names) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndexes(String...), because map does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<Map<Object, Object>, V> getProperty(int index) {
        throw new UnsupportedException(
            "unsupport method getProperty(int), because map does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<Map<Object, Object>, V> getProperty(String name) {
        throw new UnsupportedException(
            "unsupport method getProperty(String), because map does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Map<Object, Object>, ?>[] getProperties() {
        throw new UnsupportedException(
            "unsupport method getProperty(String), because map does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Object, Object> instantiate() {
        return instantiator.instantiate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Map<Object, Object>> getType() {
        return instantiator.getType();
    }

    private void assertPropertyAccessor(PropertyAccessor<?> propertyAccessor, Object obj, Object nameOrIndex,
        Object transitional) {
        if (propertyAccessor == null) {
            throw new IllegalArgumentException(Strings.format("object [{}] property [{}] type [{}] is inaccessible",
                obj.getClass().getName(), nameOrIndex, transitional.getClass().getName()));
        }
    }
}
