
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

import java.util.function.Supplier;

/**
 * ArraySupplier.
 *
 * @author zhongj
 * @param <E> the element type
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ArraySupplier<E> extends Supplier<E[]> {
}
