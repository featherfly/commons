package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * The Interface SerializableBiFunction.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 * @see java.util.function.BiFunction
 */
@FunctionalInterface
public interface SerializableBiFunction<T, U, R> extends Serializable, BiFunction<T, U, R> {

}