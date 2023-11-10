package cn.featherfly.common.function;

import java.time.LocalTime;
import java.util.function.Function;

/**
 * The Interface ToLocalTimeFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface ToLocalTimeFunction<T> extends Function<T, LocalTime> {

}