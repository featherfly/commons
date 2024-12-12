
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
 */
public class ImmutableCollection<E> implements Collection<E> {

    private final Collection<E> collection;

    /**
     * @param collection
     */
    public ImmutableCollection(Collection<E> collection) {
        super();
        this.collection = collection;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        collection.forEach(action);
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new ImmutableIterator<>(collection.iterator());
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    @Override
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    @Override
    public Stream<E> stream() {
        return collection.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return collection.parallelStream();
    }

    @Override
    public String toString() {
        return collection.toString();
    }
}
