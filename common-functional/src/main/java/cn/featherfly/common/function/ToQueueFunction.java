package cn.featherfly.common.function;

import java.util.Queue;

/**
 * The Interface ToQueueFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the Queue element
 */
@FunctionalInterface
public interface ToQueueFunction<T, E> extends ToCollectionFunction<T, Queue<E>, E> {

}