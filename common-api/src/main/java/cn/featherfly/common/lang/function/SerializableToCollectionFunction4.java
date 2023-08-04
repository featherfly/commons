package cn.featherfly.common.lang.function;

import java.util.Collection;

/**
 * The Interface SerializableToCollectionFunction4.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <C> the collection type of the result of the function
 * @param <R> the type with collection
 */
@FunctionalInterface
public interface SerializableToCollectionFunction4<T, C extends Collection<R>, R>
        extends SerializableToCollectionFunction<T, C, R> {

}