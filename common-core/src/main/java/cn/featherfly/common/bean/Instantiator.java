
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:43:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.function.Supplier;

import cn.featherfly.common.lang.reflect.Type;

/**
 * Instantiator.
 *
 * @author zhongj
 * @param <T> the generic type
 * @since 1.12.2
 */
public interface Instantiator<T> extends Supplier<T>, Type<T> {

    /**
     * Instantiate.
     *
     * @return the u
     */
    T instantiate();

    /**
     * {@inheritDoc}
     */
    @Override
    default T get() {
        return instantiate();
    }
}
