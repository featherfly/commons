
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * AbstractPropertyAccessor.
 *
 * @author zhongj
 */
public abstract class AbstractPropertyAccessor<T> implements PropertyAccessor<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T obj, String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return getPropertyValue(obj, names[0]);
        }
        Property<T, Object> property = getProperty(names[0]);
        Object transitional = property.get(obj);
        if (transitional == null) {
            return null;
        }
        PropertyAccessor<Object> propertyAccessor = property.getPropertyAccessor();
        assertPropertyAccessor(propertyAccessor, names[0], names[1], property.getType());
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
        Property<T, Object> property = getProperty(indexes[0]);
        Object transitional = property.get(obj);
        if (transitional == null) {
            return null;
        }
        PropertyAccessor<Object> propertyAccessor = property.getPropertyAccessor();
        assertPropertyAccessor(propertyAccessor, indexes[0], indexes[1], property.getType());
        return propertyAccessor.getPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getPropertyIndexes(String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return new int[] { getProperty(names[0]).getIndex() };
        }
        Property<T, Object> property = getProperty(names[0]);
        PropertyAccessor<Object> propertyAccessor = property.getPropertyAccessor();
        assertPropertyAccessor(propertyAccessor, names[0], names[1], property.getType());
        return ArrayUtils.addAll(new int[] { property.getIndex() },
            propertyAccessor.getPropertyIndexes(ArrayUtils.subarray(names, 1, names.length)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyIndex(String name) {
        return getProperty(name).getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T obj, int[] indexes, Object value) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            setPropertyValue(obj, indexes[0], value);
            return;
        }
        Property<T, Object> property = getProperty(indexes[0]);
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();

        assertPropertyAccessor(visitor, indexes[0], indexes[1], property.getType());
        Object transitional = property.get(obj);
        if (transitional == null) {
            transitional = visitor.instantiate();
            property.set(obj, transitional);
        }
        visitor.setPropertyValue(transitional, ArrayUtils.subarray(indexes, 1, indexes.length), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T obj, String[] names, Object value) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            setPropertyValue(obj, names[0], value);
            return;
        }
        Property<T, Object> property = getProperty(names[0]);
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        assertPropertyAccessor(visitor, names[0], names[1], property.getType());
        Object transitional = property.get(obj);
        if (transitional == null) {
            transitional = visitor.instantiate();
            property.set(obj, transitional);
        }
        visitor.setPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length), value);
    }

    private void assertPropertyAccessor(PropertyAccessor<?> propertyAccessor, Object nameOrIndex,
        Object nestedNameOrIndex, Class<?> propType) {
        if (propertyAccessor == null) {
            throw new PropertyAccessException(getType(), nameOrIndex, nestedNameOrIndex, propType);
        }
    }
}
