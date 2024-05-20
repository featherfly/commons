
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 18:11:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * PropertyAccessorManager.
 *
 * @author zhongj
 */
public interface PropertyAccessorManager {

    <T> PropertyAccessor<T> getPropertyAccessor(Class<T> type);
}
