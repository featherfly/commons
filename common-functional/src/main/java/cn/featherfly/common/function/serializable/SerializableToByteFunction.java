package cn.featherfly.common.function.serializable;

import java.io.Serializable;

import cn.featherfly.common.function.ToByteFunction;

/**
 * The Interface SerializableToByteFunction.
 *
 * @param <T> the type of the input to the function
 * @see ToByteFunction
 */
@FunctionalInterface
public interface SerializableToByteFunction<T> extends Serializable, ToByteFunction<T> {

}