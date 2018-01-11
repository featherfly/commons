package cn.featherfly.common.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定长度队列
 * @author zhongj
 *
 */
public class LimitQueue<E> implements Queue<E>{

    //队列长度
    private int limit;

    private Queue<E> queue;

    private Lock lock;

    /**
     * @param limit 队列长度
     */
    public LimitQueue(int limit){
        this(limit, null);
    }

    /**
     * @param limit 队列长度
     * @param queue 队列
     */
    public LimitQueue(int limit, Queue<E> queue){
        this(limit, queue, null);
    }

    /**
     * @param limit 队列长度
     * @param queue 队列
     * @param lock 锁
     */
    public LimitQueue(int limit, Queue<E> queue, Lock lock){
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
     * 入队
     * @param e
     */
    @Override
    public boolean offer(E e){
        lock.lock();
        if(queue.size() >= limit){
            //如果超出长度,入队时,先出队
            queue.poll();
        }
        lock.unlock();
        return queue.offer(e);
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E poll() {
        return queue.poll();
    }

    /**
     * 获取队列
     * @return
     */
    public Queue<E> getQueue(){
        return queue;
    }

    /**
     * 获取限制大小
     * @return
     */
    public int getLimit(){
        return limit;
    }

    @Override
    public boolean add(E e) {
        return queue.offer(e);
    }

    @Override
    public E element() {
        return queue.element();
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public E remove() {
        return queue.remove();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        //        return queue.addAll(c);
        throw new UnsupportedOperationException("unsport");
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    /**
     * @param o
     * @return
     * @see java.util.Collection#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        return queue.equals(o);
    }

    /**
     * @return
     * @see java.util.Collection#hashCode()
     */
    @Override
    public int hashCode() {
        return queue.hashCode();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}