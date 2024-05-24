
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 00:53:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * Setter.
 *
 * @author zhongj
 * @param <T> the bean type
 * @param <V> the set value type
 * @since 1.13.0
 */
public interface Setter<T, V> {

    /**
     * Sets the.
     *
     * @param object the object
     * @param value the value
     */
    void set(T object, V value);
}
