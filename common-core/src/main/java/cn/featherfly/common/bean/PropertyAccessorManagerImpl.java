
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 02:10:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import cn.featherfly.common.lang.pool.ObjectPool;

/**
 * property accessor manager .
 *
 * @author zhongj
 * @since 1.13.0
 */
public class PropertyAccessorManagerImpl extends ObjectPool<PropertyAccessor<?>, PropertyAccessorManagerImpl>
    implements PropertyAccessorManager {

    /**
     * Instantiates a new property accessor manager.
     */
    public PropertyAccessorManagerImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PropertyAccessor<T> getPropertyAccessor(Class<T> type) {
        return (PropertyAccessor<T>) get(type);
    }
}
