package cn.featherfly.common.function;

import java.util.function.Function;

/**
 * The Interface ToNumberFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <N> the type of the result of the function
 */
@FunctionalInterface
public interface ToNumberFunction<T, N extends Number> extends Function<T, N> {

}