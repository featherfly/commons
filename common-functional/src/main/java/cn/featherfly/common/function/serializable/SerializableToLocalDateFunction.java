package cn.featherfly.common.function.serializable;

import java.time.LocalDate;

/**
 * The Interface SerializableToLocalDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToLocalDateFunction<T> extends SerializableFunction<T, LocalDate> {

}