
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 19:52:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.Map;

/**
 * map params execution executor.
 *
 * @author zhongj
 * @param <E> the element type
 */
public interface MapParamsExecutionExecutor<E> extends MapParamsQueryExecutor<E> {

    /**
     * execute.
     *
     * @param execution the execution
     * @param params the params
     * @return executed success size
     */
    default int execute(E execution, Params params) {
        return execute(execution, (Map<String, Serializable>) params);
    }

    /**
     * execute.
     *
     * @param execution the execution
     * @param params the params
     * @return executed success size
     */
    int execute(E execution, Map<String, Serializable> params);
}
