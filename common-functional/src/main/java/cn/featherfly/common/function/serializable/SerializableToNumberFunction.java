package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.ToNumberFunction;

/**
 * The Interface SerializableToNumberFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <N> the type of the result of the function
 */
@FunctionalInterface
public interface SerializableToNumberFunction<T, N extends Number>
        extends ToNumberFunction<T, N>, SerializableFunction<T, N> {

}