
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 19:52:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;

/**
 * array params execution executor.
 *
 * @author zhongj
 * @param <E> the element type
 */
public interface ArrayParamsExecutionExecutor<E> extends ArrayParamsQueryExecutor<E> {

    /**
     * execute.
     *
     * @param execution the execution
     * @param params the params
     * @return executed success size
     */
    int execute(E execution, Serializable... params);
}
