
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
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of three arguments. This is
 * the three-arity specialization of {@link Predicate}.
 * <p>
 * This is a functional interface
 * whose functional method is {@link #test(Object, Object,Object)}.
 *
 * @author zhongj
 * @param <E1> the type of the first argument to the predicate
 * @param <E2> the type of the second argument the predicate
 * @param <E3> the type of the third argument the predicate
 */
@FunctionalInterface
public interface ThPredicate<E1, E2, E3> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param e1 the first function argument
     * @param e2 the second function argument
     * @param e3 the third function argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(E1 e1, E2 e2, E3 e3);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *        predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default ThPredicate<E1, E2, E3> and(ThPredicate<? super E1, ? super E2, ? super E3> other) {
        Objects.requireNonNull(other);
        return (E1 e1, E2 e2, E3 e3) -> test(e1, e2, e3) && other.test(e1, e2, e3);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     */
    default ThPredicate<E1, E2, E3> negate() {
        return (E1 e1, E2 e2, E3 e3) -> !test(e1, e2, e3);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *        predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default ThPredicate<E1, E2, E3> or(ThPredicate<? super E1, ? super E2, ? super E3> other) {
        Objects.requireNonNull(other);
        return (E1 e1, E2 e2, E3 e3) -> test(e1, e2, e3) || other.test(e1, e2, e3);
    }
}
