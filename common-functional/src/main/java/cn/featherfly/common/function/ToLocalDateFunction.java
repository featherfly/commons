package cn.featherfly.common.function;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * The Interface ToLocalDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface ToLocalDateFunction<T> extends Function<T, LocalDate> {

}