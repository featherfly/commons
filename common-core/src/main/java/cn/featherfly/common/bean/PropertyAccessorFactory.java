
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 02:10:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * property accessor factory .
 *
 * @author zhongj
 */
public interface PropertyAccessorFactory {

    /**
     * Creates PropertyAccessor.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the property visitor
     */
    <T> PropertyAccessor<T> create(Class<T> type);
}
