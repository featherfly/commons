
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-31 18:16:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.CollectionUtils;

/**
 * ListInstantiator.
 *
 * @author zhongj
 * @param <E> type of List value
 */
public class ReflectionListInstantiator<E> implements Instantiator<List<E>> {

    private final Class<?> type;

    /**
     * Instantiates a new list instantiator.
     */
    @SuppressWarnings("unchecked")
    public ReflectionListInstantiator() {
        this(ArrayList.class);
    }

    /**
     * Instantiates a new list instantiator.
     *
     * @param <L> the List type
     * @param type the type
     */
    public <L extends List<E>> ReflectionListInstantiator(Class<L> type) {
        super();
        this.type = type;
    }

    /**
     * Creates ListInstantiator.
     *
     * @param <E> the element type
     * @param type list type
     * @return the list instantiator
     */
    @SuppressWarnings("unchecked")
    public static <E> ReflectionListInstantiator<E> create(Class<?> type) {
        AssertIllegalArgument.isParent(List.class, type);
        return new ReflectionListInstantiator<>((Class<List<E>>) type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> instantiate() {
        return CollectionUtils.newList(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class<List<E>> getType() {
        return (Class<List<E>>) type;
    }
}
