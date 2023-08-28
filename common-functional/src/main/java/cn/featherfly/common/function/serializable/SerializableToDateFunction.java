package cn.featherfly.common.function.serializable;

import java.util.Date;

/**
 * The Interface SerializableToDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface SerializableToDateFunction<T, R extends Date> extends SerializableFunction<T, R> {

}