package cn.featherfly.common.function.serializable;

import java.util.Date;

import cn.featherfly.common.function.ToDateFunction;

/**
 * The Interface SerializableToDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <D> the type of the result of the function
 */
@FunctionalInterface
public interface SerializableToDateFunction<T, D extends Date>
        extends ToDateFunction<T, D>, SerializableFunction<T, D> {

}