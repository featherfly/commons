
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

/**
 * MapPropertyAccessor.
 *
 * @author zhongj
 * @since 1.13.0
 */
public class MapPropertyAccessor implements PropertyAccessor<Map<String, Object>> {

    private final Instantiator<Map<String, Object>> instantiator;

    private final PropertyAccessorManager manager;

    /**
     * Instantiates a new map property accessor.
     *
     * @param instantiator the instantiator
     * @param manager the manager
     */
    public MapPropertyAccessor(Instantiator<Map<String, Object>> instantiator, PropertyAccessorManager manager) {
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
    public Object getPropertyValue(Map<String, Object> obj, int index) {
        if (obj == null) {
            return null;
        }
        if (index >= obj.size()) {
            throw new NoSuchPropertyException(obj.getClass(), index);
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
    public Object getPropertyValue(Map<String, Object> obj, String name) {
        if (obj == null) {
            return null;
        }
        if (!obj.containsKey(name)) {
            throw new NoSuchPropertyException(obj.getClass(), name);
        }
        return obj.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<String, Object> obj, int index, Object value) {
        throw new UnsupportedException(
            "unsupport method setPropertyValue(Map<String, Object>, int, Object), because can't index elements into a Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<String, Object> obj, String name, Object value) {
        if (obj != null) {
            obj.put(name, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<String, Object> obj, int... indexes) {
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
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], indexes[1], transitional);
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Map<String, Object> obj, String... names) {
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
        assertPropertyAccessor(propertyAccessor, obj, names[0], names[1], transitional);
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<String, Object> obj, int[] indexes, Object value) {
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
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], indexes[1], transitional);
        propertyAccessor.setPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Map<String, Object> obj, String[] names, Object value) {
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
        assertPropertyAccessor(propertyAccessor, obj, names[0], names[1], transitional);
        propertyAccessor.setPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyIndex(String name) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndex(String), because there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getPropertyIndexes(String... names) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndexes(String...), because there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<Map<String, Object>, V> getProperty(int index) {
        throw new UnsupportedException(
            "unsupport method getProperty(int), because there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, V> Property<T1, V> getProperty(int... indexes) {
        throw new UnsupportedException(
            "unsupport method getProperty(int...), because there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<Map<String, Object>, V> getProperty(String name) {
        throw new UnsupportedException(
            "unsupport method getProperty(String), because there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, V> Property<T1, V> getProperty(String... names) {
        throw new UnsupportedException(
            "unsupport method getProperty(String...), there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Map<String, Object>, ?>[] getProperties() {
        throw new UnsupportedException("unsupport method getProperty(String), there is no property metadata for Map");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> instantiate() {
        return instantiator.instantiate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Map<String, Object>> getType() {
        return instantiator.getType();
    }

    private void assertPropertyAccessor(PropertyAccessor<?> propertyAccessor, Object obj, Object nameOrIndex,
        Object nestedNameOrIndex, Object transitional) {
        if (propertyAccessor == null) {
            throw new PropertyAccessException(obj.getClass(), nameOrIndex, nestedNameOrIndex, transitional.getClass());
        }
    }
}
