package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.ToIntFunction;

/**
 * The Interface SerializableFunction.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.ToIntFunction
 */
@FunctionalInterface
public interface SerializableToIntFunction<T> extends Serializable, ToIntFunction<T> {

}