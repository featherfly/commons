
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
 * @since 1.13.0
 */
public interface InstantiatorFactory {

    /**
     * Instantiate.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the Instantiator
     */
    default <T> Instantiator<T> create(Class<T> type) {
        return create(type, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Instantiate.
     *
     * @param <T> the generic type
     * @param type the type
     * @param classLoader the class loader
     * @return the Instantiator
     */
    <T> Instantiator<T> create(Class<T> type, ClassLoader classLoader);

}
