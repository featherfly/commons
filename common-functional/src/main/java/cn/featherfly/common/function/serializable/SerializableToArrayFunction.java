package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.ToArrayFunction;

/**
 * The Interface SerializableToArrayFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the result array element of the function
 */
@FunctionalInterface
public interface SerializableToArrayFunction<T, E> extends SerializableFunction<T, E[]>, ToArrayFunction<T, E> {

}