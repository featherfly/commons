package cn.featherfly.common.function.serializable;

import java.time.LocalTime;

/**
 * The Interface SerializableToLocalTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalTimeFunction<T> extends SerializableFunction<T, LocalTime> {

}