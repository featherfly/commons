
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
import java.util.function.Consumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result. This is the four-arity specialization of {@link Consumer}.
 * Unlike most other functional interfaces, {@code BiConsumer} is expected
 * to operate via side-effects.
 *
 * @author zhongj
 * @param <E1> the first function argument type
 * @param <E2> the second function argument type
 * @param <E3> the third function argument type
 * @param <E4> the fourth function argument type
 */
@FunctionalInterface
public interface FoConsumer<E1, E2, E3, E4> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param e1 the first function argument
     * @param e2 the second function argument
     * @param e3 the third function argument
     * @param e4 the fourth function argument
     */
    void accept(E1 e1, E2 e2, E3 e3, E4 e4);

    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default FoConsumer<E1, E2, E3, E4> andThen(FoConsumer<? super E1, ? super E2, ? super E3, ? super E4> after) {
        Objects.requireNonNull(after);
        return (e1, e2, e3, e4) -> {
            accept(e1, e2, e3, e4);
            after.accept(e1, e2, e3, e4);
        };
    }
}
