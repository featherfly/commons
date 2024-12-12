package cn.featherfly.common.structure;

import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * immutable iterator.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ImmutableListIterator<E> implements ListIterator<E> {

    private final ListIterator<E> iterator;

    /**
     * Instantiates a new immutable iterator.
     *
     * @param iterator the iterator
     */
    public ImmutableListIterator(ListIterator<E> iterator) {
        super();
        this.iterator = iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
        return iterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        iterator.forEachRemaining(action);
    }

    @Override
    public boolean hasPrevious() {
        return iterator.hasPrevious();
    }

    @Override
    public E previous() {
        return iterator.previous();
    }

    @Override
    public int nextIndex() {
        return iterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return iterator.previousIndex();
    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException();
    }

}