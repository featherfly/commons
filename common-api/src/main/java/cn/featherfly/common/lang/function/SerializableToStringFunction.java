package cn.featherfly.common.lang.function;

/**
 * The Interface SerializableToStringFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface SerializableToStringFunction<T> extends SerializableFunction<T, String> {

}