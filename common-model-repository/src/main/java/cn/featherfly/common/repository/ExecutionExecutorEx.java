
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 19:52:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * execution executor.
 *
 * @author zhongj
 * @param <E> the element type
 */
public interface ExecutionExecutorEx<E>
    extends ExecutionExecutor<E>, ArrayParamsExecutionExecutorEx<E>, MapParamsExecutionExecutorEx<E> {
}
