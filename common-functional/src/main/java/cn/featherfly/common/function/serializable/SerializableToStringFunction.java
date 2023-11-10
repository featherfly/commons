package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.ToStringFunction;

/**
 * The Interface SerializableToStringFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToStringFunction<T> extends ToStringFunction<T>, SerializableFunction<T, String> {

}