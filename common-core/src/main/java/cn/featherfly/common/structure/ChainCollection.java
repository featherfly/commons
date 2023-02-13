
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ChainCollection.java
 * @Package cn.featherfly.common.structure
 * @Description: ChainCollection
 * @author: zhongj
 * @date: 2023-01-29 15:51:29
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;

/**
 * ChainCollection.
 *
 * @author zhongj
 * @param <E> the element type
 * @param <T> the generic type
 */
public interface ChainCollection<E, T extends Collection<E>> extends Collection<E> {

    /**
     * 带链式调用的add方法. {@link java.util.Set#add(Object)}.
     *
     * @param value 指定键将要关联的值。
     * @return 当前Set
     */
    T addChain(E value);

    /**
     * Adds the chain.
     *
     * @param values the value array
     * @return the t
     */
    T addChain(@SuppressWarnings("unchecked") E... values);

    /**
     * 带链式调用的addAll方法. {@link java.util.Set#addAll(Collection)}.
     *
     * @param c 要在此映射中存储的集合
     * @return 当前Set
     */
    T addAllChain(Collection<? extends E> c);
}
