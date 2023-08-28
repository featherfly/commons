
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SerializableUnaryOperator.java
 * @Description: SerializableUnaryOperator
 * @author: zhongj
 * @date: 2023-08-28 15:06:28
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function.serializable;

import java.util.function.UnaryOperator;

/**
 * The Interface SerializableUnaryOperator.
 *
 * @author zhongj
 * @param <T> the type of the operand and result of the operator
 * @see java.util.function.UnaryOperator
 */
@FunctionalInterface
public interface SerializableUnaryOperator<T> extends SerializableFunction<T, T>, UnaryOperator<T> {

}
