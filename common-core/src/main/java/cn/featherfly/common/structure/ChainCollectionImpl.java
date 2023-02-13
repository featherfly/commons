
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

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * ChainCollectionImpl.
 *
 * @author zhongj
 * @param <E> the element type
 * @param <T> the generic type
 */
public class ChainCollectionImpl<E, T extends Collection<E>> implements ChainCollection<E, T> {

    /** The collection. */
    protected final Collection<E> collection;

    /**
     * Instantiates a new abstract chain collection.
     *
     * @param collection the collection
     */
    public ChainCollectionImpl(Collection<E> collection) {
        super();
        this.collection = collection;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T addChain(E value) {
        add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T addChain(E... values) {
        if (values != null) {
            for (E value : values) {
                add(value);
            }
        }
        return (T) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T addAllChain(Collection<? extends E> c) {
        addAll(c);
        return (T) this;
    }

    /**
     * For each.
     *
     * @param action the action
     * @see java.lang.Iterable#forEach(java.util.function.Consumer)
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        collection.forEach(action);
    }

    /**
     * Size.
     *
     * @return the int
     * @see java.util.Collection#size()
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * Contains.
     *
     * @param o the o
     * @return true, if successful
     * @see java.util.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * Iterator.
     *
     * @return the iterator
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    /**
     * To array.
     *
     * @return the object[]
     * @see java.util.Collection#toArray()
     */
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    /**
     * To array.
     *
     * @param <A> the generic type
     * @param a   the a
     * @return the a[]
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    @Override
    public <A> A[] toArray(A[] a) {
        return collection.toArray(a);
    }

    /**
     * Adds the.
     *
     * @param e the e
     * @return true, if successful
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(E e) {
        return collection.add(e);
    }

    /**
     * Removes the.
     *
     * @param o the o
     * @return true, if successful
     * @see java.util.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    /**
     * Contains all.
     *
     * @param c the c
     * @return true, if successful
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    /**
     * Adds the all.
     *
     * @param c the c
     * @return true, if successful
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return collection.addAll(c);
    }

    /**
     * Removes the all.
     *
     * @param c the c
     * @return true, if successful
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    /**
     * Removes the if.
     *
     * @param filter the filter
     * @return true, if successful
     * @see java.util.Collection#removeIf(java.util.function.Predicate)
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return collection.removeIf(filter);
    }

    /**
     * Retain all.
     *
     * @param c the c
     * @return true, if successful
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    /**
     * Clear.
     *
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear() {
        collection.clear();
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     * @see java.util.Collection#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    /**
     * Hash code.
     *
     * @return the int
     * @see java.util.Collection#hashCode()
     */
    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    /**
     * Spliterator.
     *
     * @return the spliterator
     * @see java.util.Collection#spliterator()
     */
    @Override
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    /**
     * Stream.
     *
     * @return the stream
     * @see java.util.Collection#stream()
     */
    @Override
    public Stream<E> stream() {
        return collection.stream();
    }

    /**
     * Parallel stream.
     *
     * @return the stream
     * @see java.util.Collection#parallelStream()
     */
    @Override
    public Stream<E> parallelStream() {
        return collection.parallelStream();
    }
}
