
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 17:08:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * ListPropertyAccessor.
 *
 * @author zhongj
 */
public class ListPropertyAccessor implements PropertyAccessor<List<Object>> {

    private final Instantiator<List<Object>> instantiator;

    private final PropertyAccessorManagerImpl manager;

    /**
     * Instantiates a new list property accessor.
     *
     * @param instantiator the instantiator
     * @param manager the manager
     */
    public ListPropertyAccessor(Instantiator<List<Object>> instantiator, PropertyAccessorManagerImpl manager) {
        super();
        AssertIllegalArgument.isParent(Map.class, instantiator.getType());
        this.instantiator = instantiator;
        this.manager = manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportAccessByName() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supporMetadata() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(List<Object> obj, int index) {
        if (obj == null || index >= obj.size()) {
            return null;
        }
        return obj.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(List<Object> obj, String name) {
        throw new UnsupportedException(
            "unsupport method getPropertyValue(List<Object>, String), because can not get a element by String from a list");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(List<Object> obj, int index, Object value) {
        if (obj == null || index >= obj.size()) {
            return;
        }
        obj.set(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(List<Object> obj, String name, Object value) {
        throw new UnsupportedException(
            "unsupport method setPropertyValue(List<Object>, String, Object), because can not get a element by String from a list");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(List<Object> obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
        Object transitional = getPropertyValue(obj, indexes[0]);
        if (transitional == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        PropertyAccessor<Object> propertyAccessor = (PropertyAccessor<Object>) manager.get(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], indexes[1], transitional);
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(List<Object> obj, String... names) {
        throw new UnsupportedException(
            "unsupport method getPropertyValue(List<Object>, String...), because can not get a element by String from a list");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(List<Object> obj, int[] indexes, Object value) {
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
        PropertyAccessor<Object> propertyAccessor = (PropertyAccessor<Object>) manager.get(transitional.getClass());
        assertPropertyAccessor(propertyAccessor, obj, indexes[0], indexes[1], transitional);
        propertyAccessor.setPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(List<Object> obj, String[] names, Object value) {
        throw new UnsupportedException(
            "unsupport method getPropertyValue(List<Object>, String[], Object), because can not get a element by String from a list");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyIndex(String name) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndex(String name), because list does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getPropertyIndexes(String... names) {
        throw new UnsupportedException(
            "unsupport method getPropertyIndexes(String... names), because list does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<List<Object>, V> getProperty(int index) {
        throw new UnsupportedException(
            "unsupport method getProperty(int index), because list does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<List<Object>, V> getProperty(String name) {
        throw new UnsupportedException(
            "unsupport method getProperty(String index), because list does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<List<Object>, ?>[] getProperties() {
        throw new UnsupportedException(
            "unsupport method getProperty(String index), because list does not have property metadata");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> instantiate() {
        return instantiator.instantiate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<List<Object>> getType() {
        return instantiator.getType();
    }

    private void assertPropertyAccessor(PropertyAccessor<?> propertyAccessor, Object obj, Object nameOrIndex,
        Object nestedNameOrIndex, Object transitional) {
        if (propertyAccessor == null) {
            throw new PropertyAccessException(obj.getClass(), nameOrIndex, nestedNameOrIndex, transitional.getClass());
        }
    }
}
