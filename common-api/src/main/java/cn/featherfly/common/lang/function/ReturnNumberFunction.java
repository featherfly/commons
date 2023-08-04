package cn.featherfly.common.lang.function;

/**
 * The Interface ReturnNumberFunction.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <R> the generic type
 */
//* @deprecated use {@link SerializableToNumberFunction} instead
//@Deprecated
@FunctionalInterface
public interface ReturnNumberFunction<T, R extends Number> extends SerializableToNumberFunction<T, R> {

}