
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-22 04:10:22
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package impl;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;

/**
 * AbstractPropertyVisitor2.
 *
 * @author zhongj
 */
public class AbstractPropertyVisitorTest<T> extends AbstractPropertyAccessor<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T obj, int index) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(T bean, String name) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T obj, int index, Object value) {
        // YUFEI_TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(T bean, String name, Object value) {
        // YUFEI_TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<T, V> getProperty(int index) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> Property<T, V> getProperty(String name) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T instantiate() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    protected Object noSuchProperty(Class<?> c, int i) {
        throw new NoSuchPropertyException(c, i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<T, ?>[] getProperties() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

}
