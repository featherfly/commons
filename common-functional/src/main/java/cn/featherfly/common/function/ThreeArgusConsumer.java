
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

/**
 * ThreeArgusConsumer.
 *
 * @author zhongj
 * @param <E1> the first function argument type
 * @param <E2> the second function argument type
 * @param <E3> the third function argument type
 * @deprecated use {@link ThConsumer} instead
 */
@Deprecated
@FunctionalInterface
public interface ThreeArgusConsumer<E1, E2, E3> extends ThConsumer<E1, E2, E3> {
}
