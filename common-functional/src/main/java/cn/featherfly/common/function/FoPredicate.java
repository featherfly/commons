
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
 * Represents a predicate (boolean-valued function) of four arguments. This is
 * the four-arity specialization of {@link Predicate}.
 * <p>
 * This is a functional interface
 * whose functional method is
 * {@link #test(Object, Object, Object, Object)}.
 *
 * @author zhongj
 * @param <E1> the type of the first argument to the predicate
 * @param <E2> the type of the second argument the predicate
 * @param <E3> the type of the third argument the predicate
 * @param <E4> the type of the fourth argument the predicate
 */
@FunctionalInterface
public interface FoPredicate<E1, E2, E3, E4> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param e1 the first argument
     * @param e2 the second argument
     * @param e3 the third argument
     * @param e4 the fourth argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(E1 e1, E2 e2, E3 e3, E4 e4);

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
    default FoPredicate<E1, E2, E3, E4> and(FoPredicate<? super E1, ? super E2, ? super E3, ? super E4> other) {
        Objects.requireNonNull(other);
        return (E1 e1, E2 e2, E3 e3, E4 e4) -> test(e1, e2, e3, e4) && other.test(e1, e2, e3, e4);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     */
    default FoPredicate<E1, E2, E3, E4> negate() {
        return (E1 e1, E2 e2, E3 e3, E4 e4) -> !test(e1, e2, e3, e4);
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
    default FoPredicate<E1, E2, E3, E4> or(FoPredicate<? super E1, ? super E2, ? super E3, ? super E4> other) {
        Objects.requireNonNull(other);
        return (E1 e1, E2 e2, E3 e3, E4 e4) -> test(e1, e2, e3, e4) || other.test(e1, e2, e3, e4);
    }
}
