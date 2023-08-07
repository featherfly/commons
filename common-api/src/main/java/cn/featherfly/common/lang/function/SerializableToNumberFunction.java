package cn.featherfly.common.lang.function;

/**
 * The Interface SerializableToNumberFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface SerializableToNumberFunction<T, R extends Number> extends SerializableFunction<T, R> {

}