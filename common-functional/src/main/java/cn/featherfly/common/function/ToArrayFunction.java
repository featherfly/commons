package cn.featherfly.common.function;

import java.util.function.Function;

/**
 * The Interface ToArrayFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the result array element of the function
 */
@FunctionalInterface
public interface ToArrayFunction<T, E> extends Function<T, E[]> {

}