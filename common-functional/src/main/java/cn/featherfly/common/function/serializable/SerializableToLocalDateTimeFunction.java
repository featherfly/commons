package cn.featherfly.common.function.serializable;

import java.time.LocalDateTime;

import cn.featherfly.common.function.ToLocalDateTimeFunction;

/**
 * The Interface SerializableToLocalDateTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalDateTimeFunction<T>
        extends ToLocalDateTimeFunction<T>, SerializableFunction<T, LocalDateTime> {

}