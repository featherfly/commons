
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-24 15:06:24
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * AbstractReadWriteProperty.
 *
 * @author zhongj
 */
public abstract class AbstractReadWriteProperty<T, V> extends AbstractProperty<T, V> {

    /**
     * Instantiates a new abstract read write property.
     *
     * @param instanceType the instance type
     * @param propertyType the property type
     * @param name the name
     * @param index the index
     * @param propertyAccessor the property accessor
     */
    protected AbstractReadWriteProperty(Class<T> instanceType, Class<V> propertyType, String name, int index,
        PropertyAccessor<V> propertyAccessor) {
        super(instanceType, propertyType, name, index, propertyAccessor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWritable() {
        return true;
    }
}
