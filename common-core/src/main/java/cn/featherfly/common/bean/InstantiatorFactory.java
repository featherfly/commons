
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:43:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

/**
 * instantiator factory.
 *
 * @author zhongj
 * @param <T> the generic type
 * @since 1.12.1
 */
public interface InstantiatorFactory {

    /**
     * Instantiate.
     *
     * @return the u
     */
    <T> Instantiator<T> create(Class<T> type, ClassLoader classLoader);
}
