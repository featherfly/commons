
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ChainCollectionImpl.java
 * @Package cn.featherfly.common.structure
 * @Description: ChainCollectionImpl
 * @author: zhongj
 * @date: 2023-01-29 15:57:29
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * ChainCollectionImpl.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ChainListImpl<E> extends ChainCollectionImpl<E, ChainListImpl<E>> implements List<E> {

    /**
     * Instantiates a new chain map impl, work same as HashMap.
     */
    public ChainListImpl() {
        this(new ArrayList<E>(0));
    }

    /**
     * Instantiates a new chain map impl with map argu.
     *
     * @param collection the collection
     */
    public ChainListImpl(List<E> collection) {
        super(collection);
    }

    private List<E> getList() {
        return (List<E>) collection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return getList().addAll(index, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) {
        return getList().get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        return getList().set(index, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
        getList().add(index, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        return getList().remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        return getList().indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        return getList().lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator() {
        return getList().listIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return getList().listIterator(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return getList().subList(fromIndex, toIndex);
    }
}
