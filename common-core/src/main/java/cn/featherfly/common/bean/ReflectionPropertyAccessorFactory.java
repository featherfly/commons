
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-10-31 15:50:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * ReflectionPropertyAccessorFactory.
 *
 * @author zhongj
 */
public class ReflectionPropertyAccessorFactory implements PropertyAccessorFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> PropertyAccessor<T> create(Class<T> type, ClassLoader classLoader) {
        return BeanDescriptor.getBeanDescriptor(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPropertyAccessorCascade(ClassLoader classLoader) {
        // do nothing
    }

}
