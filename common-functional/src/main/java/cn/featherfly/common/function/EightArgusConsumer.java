
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
 * eight arguments consumer.
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
 * @deprecated use {@link EiConsumer} instead
 */
@Deprecated
@FunctionalInterface
public interface EightArgusConsumer<E1, E2, E3, E4, E5, E6, E7, E8> extends EiConsumer<E1, E2, E3, E4, E5, E6, E7, E8> {

}
