
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 01:57:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * PropertyAccessor holder.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface PropertyAccessorHolder<T> {

    /**
     * Gets the holding property accessor.
     *
     * @return the holding property accessor
     */
    PropertyAccessor<T> getHoldingPropertyAccessor();
}
