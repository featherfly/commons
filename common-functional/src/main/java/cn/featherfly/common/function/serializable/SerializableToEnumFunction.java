package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.ToEnumFunction;

/**
 * The Interface ReturnEnumFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the enum result of the function
 */
@FunctionalInterface
public interface SerializableToEnumFunction<T, E extends Enum<E>>
        extends ToEnumFunction<T, E>, SerializableFunction<T, E> {

}