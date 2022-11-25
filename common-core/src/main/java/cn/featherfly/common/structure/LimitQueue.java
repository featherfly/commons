package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定长度队列.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class LimitQueue<E> implements Queue<E> {

    //队列长度
    private int limit;

    private Queue<E> queue;

    private Lock lock;

    /**
     * Instantiates a new limit queue.
     *
     * @param limit 队列长度
     */
    public LimitQueue(int limit) {
        this(limit, null);
    }

    /**
     * Instantiates a new limit queue.
     *
     * @param limit 队列长度
     * @param queue 队列
     */
    public LimitQueue(int limit, Queue<E> queue) {
        this(limit, queue, null);
    }

    /**
     * Instantiates a new limit queue.
     *
     * @param limit 队列长度
     * @param queue 队列
     * @param lock  锁
     */
    public LimitQueue(int limit, Queue<E> queue, Lock lock) {
        this.limit = limit;
        if (queue == null) {
            queue = new LinkedList<>();
        }
        this.queue = queue;
        if (lock == null) {
            lock = new ReentrantLock();
        }
        this.lock = lock;
    }

    /**
     * 入队.
     *
     * @param e element
     * @return true, if successful
     */
    @Override
    public boolean offer(E e) {
        lock.lock();
        if (queue.size() >= limit) {
            //如果超出长度,入队时,先出队
            queue.poll();
        }
        lock.unlock();
        return queue.offer(e);
    }

    /**
     * 出队.
     *
     * @return element
     */
    @Override
    public E poll() {
        return queue.poll();
    }

    /**
     * 获取队列.
     *
     * @return queue
     */
    public Queue<E> getQueue() {
        return queue;
    }

    /**
     * 获取限制大小.
     *
     * @return limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        return queue.offer(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E element() {
        return queue.element();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
        return queue.peek();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove() {
        return queue.remove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        //        return queue.addAll(c);
        throw new UnsupportedOperationException("unsport");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        queue.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    /**
     * Equals.
     *
     * @param o object
     * @return true if the specified object is equal to this
     * @see java.util.Collection#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        return queue.equals(o);
    }

    /**
     * Hash code.
     *
     * @return the hash code value for this queue
     * @see java.util.Collection#hashCode()
     */
    @Override
    public int hashCode() {
        return queue.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}