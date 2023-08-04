package cn.featherfly.common.lang.function;

/**
 * The Interface ReturnEnumFunction.
 *
 * @author zhongj
 * @param <T> the generic type
 * @param <R> the generic type
 */
//* @deprecated use {@link SerializableToEnumFunction} instead
//@Deprecated
@FunctionalInterface
public interface ReturnEnumFunction<T, R extends Enum<R>> extends SerializableToEnumFunction<T, R> {

}