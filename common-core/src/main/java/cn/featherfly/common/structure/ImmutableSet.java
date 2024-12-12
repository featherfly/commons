
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-12 16:35:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * immutable set.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ImmutableSet<E> implements Set<E> {

    private final Set<E> set;

    /**
     * Instantiates a new immutable set.
     *
     * @param set the set
     */
    public ImmutableSet(Set<E> set) {
        super();
        this.set = set;
    }

    /**
     * For each.
     *
     * @param action the action
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        set.forEach(action);
    }

    /**
     * Size.
     *
     * @return the int
     */
    @Override
    public int size() {
        return set.size();
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Contains.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    /**
     * Iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ImmutableIterator<>(set.iterator());
    }

    /**
     * To array.
     *
     * @return the object[]
     */
    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    /**
     * To array.
     *
     * @param <T> the generic type
     * @param a the a
     * @return the t[]
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    /**
     * Adds the.
     *
     * @param e the e
     * @return true, if successful
     */
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Contains all.
     *
     * @param c the c
     * @return true, if successful
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    /**
     * Adds the all.
     *
     * @param c the c
     * @return true, if successful
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the all.
     *
     * @param c the c
     * @return true, if successful
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the if.
     *
     * @param filter the filter
     * @return true, if successful
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retain all.
     *
     * @param c the c
     * @return true, if successful
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return set.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }

    /**
     * Spliterator.
     *
     * @return the spliterator
     */
    @Override
    public Spliterator<E> spliterator() {
        return set.spliterator();
    }

    /**
     * Stream.
     *
     * @return the stream
     */
    @Override
    public Stream<E> stream() {
        return set.stream();
    }

    /**
     * Parallel stream.
     *
     * @return the stream
     */
    @Override
    public Stream<E> parallelStream() {
        return set.parallelStream();
    }

}
