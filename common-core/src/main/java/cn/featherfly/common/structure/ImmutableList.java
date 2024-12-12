
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-12 16:35:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * immutable list.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ImmutableList<E> implements List<E> {

    private final List<E> list;

    /**
     * Instantiates a new immutable list.
     *
     * @param list the set
     */
    public ImmutableList(List<E> list) {
        super();
        this.list = list;
    }

    /**
     * For each.
     *
     * @param action the action
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        list.forEach(action);
    }

    /**
     * Size.
     *
     * @return the int
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Contains.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * Iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ImmutableIterator<>(list.iterator());
    }

    /**
     * To array.
     *
     * @return the object[]
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
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
        return list.toArray(a);
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
        return list.containsAll(c);
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
        return list.retainAll(c);
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
        return list.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return list.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * Spliterator.
     *
     * @return the spliterator
     */
    @Override
    public Spliterator<E> spliterator() {
        return list.spliterator();
    }

    /**
     * Stream.
     *
     * @return the stream
     */
    @Override
    public Stream<E> stream() {
        return list.stream();
    }

    /**
     * Parallel stream.
     *
     * @return the stream
     */
    @Override
    public Stream<E> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        list.sort(c);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ImmutableListIterator<>(list.listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ImmutableListIterator<>(list.listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

}
