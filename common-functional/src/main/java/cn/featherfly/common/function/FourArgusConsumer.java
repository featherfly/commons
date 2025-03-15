
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
 * FourArgusConsumer.
 *
 * @author zhongj
 * @param <E1> the first function argument type
 * @param <E2> the second function argument type
 * @param <E3> the third function argument type
 * @param <E4> the fourth function argument type
 * @deprecated use {@link FoConsumer} instead
 */
@Deprecated
@FunctionalInterface
public interface FourArgusConsumer<E1, E2, E3, E4> extends FoConsumer<E1, E2, E3, E4> {

}
