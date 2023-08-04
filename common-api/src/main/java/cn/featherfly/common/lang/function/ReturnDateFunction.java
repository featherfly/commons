package cn.featherfly.common.lang.function;

import java.util.Date;

/**
 * The Interface ReturnDateFunction.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <R> the generic type
 */
//* @deprecated use {@link SerializableToDateFunction} instead
//@Deprecated
@FunctionalInterface
public interface ReturnDateFunction<T, R extends Date> extends SerializableToDateFunction<T, R> {

}