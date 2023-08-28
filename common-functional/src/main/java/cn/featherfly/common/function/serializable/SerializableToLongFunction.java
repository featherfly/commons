package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.ToLongFunction;

/**
 * The Interface SerializableToLongFunction.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.ToLongFunction
 */
@FunctionalInterface
public interface SerializableToLongFunction<T> extends Serializable, ToLongFunction<T> {

}