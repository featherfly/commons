package cn.featherfly.common.function;

import java.util.function.Function;

/**
 * The Interface ToEnumFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <E> the type of the enum result of the function
 */
@FunctionalInterface
public interface ToEnumFunction<T, E extends Enum<E>> extends Function<T, E> {

}