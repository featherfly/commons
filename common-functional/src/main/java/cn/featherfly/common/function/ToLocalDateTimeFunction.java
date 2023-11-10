package cn.featherfly.common.function;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * The Interface ToLocalDateTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface ToLocalDateTimeFunction<T> extends Function<T, LocalDateTime> {

}