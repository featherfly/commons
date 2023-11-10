package cn.featherfly.common.function;

import java.util.function.Function;

/**
 * The Interface ToStringFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 */
@FunctionalInterface
public interface ToStringFunction<T> extends Function<T, String> {

}