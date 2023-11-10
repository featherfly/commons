package cn.featherfly.common.function;

import java.util.List;

/**
 * The Interface ToListFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the List element
 */
@FunctionalInterface
public interface ToListFunction<T, E> extends ToCollectionFunction<T, List<E>, E> {

}