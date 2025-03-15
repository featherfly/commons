
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ThreeParamsFunction.java
 * @Package cn.featherfly.common.function
 * @Description: ThreeParamsFunction
 * @author: zhongj
 * @date: 2023-07-18 15:16:18
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the nine-arity specialization of {@link Function}..
 *
 * @author zhongj
 * @param <E1> the first function argument type
 * @param <E2> the second function argument type
 * @param <E3> the third function argument type
 * @param <E4> the fourth function argument type
 * @param <E5> the fifth function argument type
 * @param <E6> the sixth function argument type
 * @param <E7> the seventh function argument type
 * @param <E8> the eightth function argument type
 * @param <E9> the ninth function argument type
 * @param <R> the function result type
 */
@FunctionalInterface
public interface NiFunction<E1, E2, E3, E4, E5, E6, E7, E8, E9, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param e1 the first function argument
     * @param e2 the second function argument
     * @param e3 the third function argument
     * @param e4 the fourth function argument
     * @param e5 the fifth function argument
     * @param e6 the sixth function argument
     * @param e7 the seventh function argument
     * @param e8 the eightth function argument
     * @param e9 the ninth function argument
     * @return the function result
     */
    R apply(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6, E7 e7, E8 e8, E9 e9);

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *        composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     *         applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> NiFunction<E1, E2, E3, E4, E5, E6, E7, E8, E9, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6, E7 e7, E8 e8, E9 e9) -> after
            .apply(apply(e1, e2, e3, e4, e5, e6, e7, e8, e9));
    }
}
