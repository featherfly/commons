package cn.featherfly.common.function;

import java.util.Date;
import java.util.function.Function;

/**
 * The Interface ToDateFunction.
 *
 * @author zhongj
 * @param <T> the type of the input to the function
 * @param <D> the type of the result of the function
 */
@FunctionalInterface
public interface ToDateFunction<T, D extends Date> extends Function<T, D> {

}