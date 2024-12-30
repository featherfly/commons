
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
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * immutable collection.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ImmutableCollection<E> implements Collection<E> {

    private final Collection<E> collection;

    /**
     * Instantiates a new immutable collection.
     *
     * @param collection the collection
     */
    public ImmutableCollection(Collection<E> collection) {
        super();
        this.collection = collection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        collection.forEach(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new ImmutableIterator<>(collection.iterator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    /**
     * {@inheritDoc}
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
        return collection.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<E> stream() {
        return collection.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<E> parallelStream() {
        return collection.parallelStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return collection.toString();
    }
}
