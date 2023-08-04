package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.ToLongFunction;

/**
 * The Interface SerializableFunction.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.ToLongFunction
 */
@FunctionalInterface
public interface SerializableToLongFunction<T> extends Serializable, ToLongFunction<T> {

}