package cn.featherfly.common.function.serializable;

import java.util.Collection;

/**
 * The Interface SerializableToCollectionFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <C> the collection type of the result of the function
 * @param <R> the type with collection
 */
@FunctionalInterface
public interface SerializableToCollectionFunction<T, C extends Collection<R>, R> extends SerializableFunction<T, C> {

}