package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * The Interface SerializableFunction.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface SerializableFunction<T, R> extends Serializable, Function<T, R> {

}