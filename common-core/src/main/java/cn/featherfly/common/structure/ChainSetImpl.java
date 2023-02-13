
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ChainCollectionImpl.java
 * @Package cn.featherfly.common.structure
 * @Description: ChainSetImpl
 * @author: zhongj
 * @date: 2023-01-29 15:57:29
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.HashSet;
import java.util.Set;

/**
 * ChainSetImpl.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ChainSetImpl<E> extends ChainCollectionImpl<E, ChainSetImpl<E>> implements Set<E> {

    /**
     * Instantiates a new chain map impl, work same as HashMap.
     */
    public ChainSetImpl() {
        this(new HashSet<E>(0));
    }

    /**
     * Instantiates a new chain map impl with map argu.
     *
     * @param collection the collection
     */
    public ChainSetImpl(Set<E> collection) {
        super(collection);
    }
}
