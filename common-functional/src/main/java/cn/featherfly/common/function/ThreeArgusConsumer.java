
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

/**
 * ThreeArgusConsumer.
 *
 * @author zhongj
 * @param <E1> the first function argument type
 * @param <E2> the second function argument type
 * @param <E3> the third function argument type
 */
@FunctionalInterface
public interface ThreeArgusConsumer<E1, E2, E3> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param e1 the first function argument
     * @param e2 the second function argument
     * @param e3 the third function argument
     */
    void accept(E1 e1, E2 e2, E3 e3);

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
    default ThreeArgusConsumer<E1, E2, E3> andThen(ThreeArgusConsumer<? super E1, ? super E2, ? super E3> after) {
        Objects.requireNonNull(after);
        return (e1, e2, e3) -> {
            accept(e1, e2, e3);
            after.accept(e1, e2, e3);
        };
    }
}
