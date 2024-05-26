
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * ParamedExecutionExecutor.
 *
 * @author zhongj
 */
public interface ParamedExecutionExecutor extends ParamedQueryExecutor {

    /**
     * execute.
     *
     * @return executed success size
     */
    int execute();
}
