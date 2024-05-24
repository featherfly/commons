
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
 * @since 1.13.0
 */
public interface PropertyAccessorFactory extends InstantiatorFactory {

    /**
     * Creates PropertyAccessor.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the property visitor
     */
    @Override
    default <T> PropertyAccessor<T> create(Class<T> type) {
        return create(type, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Creates PropertyAccessor.
     *
     * @param <T> the generic type
     * @param type the type
     * @param classLoader the class loader
     * @return the property visitor
     */
    @Override
    <T> PropertyAccessor<T> create(Class<T> type, ClassLoader classLoader);

    /**
     * cascade creation PropertyAccessor for Property which is in already created PropertyAccessor.
     */
    default void createPropertyAccessorCascade() {
        createPropertyAccessorCascade(Thread.currentThread().getContextClassLoader());
    }

    /**
     * cascade creation PropertyAccessor for Property which is in already created PropertyAccessor.
     *
     * @param classLoader the class loader
     */
    void createPropertyAccessorCascade(ClassLoader classLoader);
}
