
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 00:53:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * Getter.
 *
 * @author zhongj
 * @param <T> the bean type
 * @param <V> the get value type
 */
public interface Getter<T, V> {

    V get(T object);
}
