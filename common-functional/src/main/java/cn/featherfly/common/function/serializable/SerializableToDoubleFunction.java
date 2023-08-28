package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.ToDoubleFunction;

/**
 * The Interface SerializableFunction.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.ToDoubleFunction
 */
@FunctionalInterface
public interface SerializableToDoubleFunction<T> extends Serializable, ToDoubleFunction<T> {

}