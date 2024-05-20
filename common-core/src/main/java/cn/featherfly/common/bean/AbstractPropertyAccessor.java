
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
import cn.featherfly.common.lang.Strings;

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
        Object value = property.get(obj);
        if (value == null) {
            return null;
        }
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(Strings.format("object {} property {} type {} can not be visit property",
                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
        }
        return visitor.getPropertyValue(value, ArrayUtils.subarray(names, 1, names.length));
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
        Object value = property.get(obj);
        if (value == null) {
            return null;
        }
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(Strings.format("object {} property {} type {} can not be visit property",
                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
        }
        return visitor.getPropertyValue(value, ArrayUtils.subarray(indexes, 1, indexes.length));
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
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(
                Strings.format("object {} property {} type {} can not be visit property", "后续来加入名称", "后续来加入类型"));
        }
        return ArrayUtils.addAll(new int[] { property.getIndex() },
            visitor.getPropertyIndexes(ArrayUtils.subarray(names, 1, names.length)));
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
        if (visitor == null) {
            throw new IllegalArgumentException(Strings.format("object {} property {} type {} can not be visit property",
                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
        }
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
        if (visitor == null) {
            throw new IllegalArgumentException(
                Strings.format("can not be visit property object {} property {} type {} ", obj.getClass().getName(),
                    "后续来加入名称", "后续来加入类型"));
        }
        Object transitional = property.get(obj);
        if (transitional == null) {
            transitional = visitor.instantiate();
            property.set(obj, transitional);
        }
        visitor.setPropertyValue(transitional, ArrayUtils.subarray(names, 1, names.length), value);
    }

    //    protected abstract <V> PropertyVisitor<V> getPropertyVisitor(int index);
}
