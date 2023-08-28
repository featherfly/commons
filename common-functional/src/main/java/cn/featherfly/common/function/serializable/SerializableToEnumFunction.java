package cn.featherfly.common.function.serializable;

/**
 * The Interface ReturnEnumFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <R> the type of the enum result of the function
 */
@FunctionalInterface
public interface SerializableToEnumFunction<T, R extends Enum<R>> extends SerializableFunction<T, R> {

}