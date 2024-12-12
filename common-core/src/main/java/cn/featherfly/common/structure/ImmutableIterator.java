package cn.featherfly.common.structure;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * immutable iterator.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ImmutableIterator<E> implements Iterator<E> {

    private final Iterator<E> iterator;

    /**
     * Instantiates a new immutable iterator.
     *
     * @param iterator the iterator
     */
    public ImmutableIterator(Iterator<E> iterator) {
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
}