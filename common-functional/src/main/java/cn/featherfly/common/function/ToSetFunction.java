package cn.featherfly.common.function;

import java.util.Set;

/**
 * The Interface ToSetFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the Set element
 */
@FunctionalInterface
public interface ToSetFunction<T, E> extends ToCollectionFunction<T, Set<E>, E> {

}