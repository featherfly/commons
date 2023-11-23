package cn.featherfly.common.function.serializable;

import java.io.Serializable;

import cn.featherfly.common.function.ToShortFunction;

/**
 * The Interface SerializableToShortFunction.
 *
 * @param <T> the type of the input to the function
 * @see ToShortFunction
 */
@FunctionalInterface
public interface SerializableToShortFunction<T> extends Serializable, ToShortFunction<T> {

}