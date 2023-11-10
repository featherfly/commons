package cn.featherfly.common.function;

import java.util.Collection;
import java.util.function.Function;

/**
 * The Interface ToCollectionFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <C> the collection type of the result of the function
 * @param <E> the type of the collection element
 */
@FunctionalInterface
public interface ToCollectionFunction<T, C extends Collection<E>, E> extends Function<T, C> {

}