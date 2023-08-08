package cn.featherfly.common.lang.function;

/**
 * The Interface ReturnLocalDateTimeFunction.
 *
 * @author zhongj
 * @param <T> the generic type
 * @deprecated use {@link SerializableToLocalDateTimeFunction} instead
 */
@Deprecated
@FunctionalInterface
public interface ReturnLocalDateTimeFunction<T> extends SerializableToLocalDateTimeFunction<T> {

}