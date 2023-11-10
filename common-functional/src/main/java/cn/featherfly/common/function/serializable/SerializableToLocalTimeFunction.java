package cn.featherfly.common.function.serializable;

import java.time.LocalTime;

import cn.featherfly.common.function.ToLocalTimeFunction;

/**
 * The Interface SerializableToLocalTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalTimeFunction<T> extends ToLocalTimeFunction<T>, SerializableFunction<T, LocalTime> {

}