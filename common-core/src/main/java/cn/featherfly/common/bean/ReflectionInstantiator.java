
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 19:02:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import cn.featherfly.common.lang.ClassUtils;

/**
 * reflection instantiator.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class ReflectionInstantiator<T> implements Instantiator<T> {

    private final Class<T> type;

    /**
     * Instantiates a new reflection instantiator.
     *
     * @param type the type
     */
    public ReflectionInstantiator(Class<T> type) {
        super();
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T instantiate() {
        return ClassUtils.newInstance(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }
}
