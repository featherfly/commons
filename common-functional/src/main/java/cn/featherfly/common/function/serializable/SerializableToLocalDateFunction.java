package cn.featherfly.common.function.serializable;

import java.time.LocalDate;

import cn.featherfly.common.function.ToLocalDateFunction;

/**
 * The Interface SerializableToLocalDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalDateFunction<T> extends ToLocalDateFunction<T>, SerializableFunction<T, LocalDate> {

}