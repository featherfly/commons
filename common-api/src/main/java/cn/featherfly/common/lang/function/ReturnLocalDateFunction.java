package cn.featherfly.common.lang.function;

/**
 * The Interface ReturnLocalDateFunction.
 *
 * @author zhongj
 * @param <T> the generic type
 * @deprecated use {@link SerializableToLocalDateFunction} instead
 */
@Deprecated
@FunctionalInterface
public interface ReturnLocalDateFunction<T> extends SerializableToLocalDateFunction<T> {

}