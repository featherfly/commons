package cn.featherfly.common.function.serializable;

import java.io.Serializable;

import cn.featherfly.common.function.ToCharFunction;

/**
 * The Interface SerializableToCharFunction.
 *
 * @param <T> the type of the input to the function
 * @see ToCharFunction
 */
@FunctionalInterface
public interface SerializableToCharFunction<T> extends Serializable, ToCharFunction<T> {

}