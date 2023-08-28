package cn.featherfly.common.function.serializable;

import java.time.LocalDateTime;

/**
 * The Interface SerializableToLocalDateTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalDateTimeFunction<T> extends SerializableFunction<T, LocalDateTime> {

}