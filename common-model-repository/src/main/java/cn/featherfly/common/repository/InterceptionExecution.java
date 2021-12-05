
/*
 * All rights Reserved, Designed By zhongj
 * @Title: InterceptionExecution.java
 * @Package cn.featherfly.common.repository
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-03 20:38:03
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * InterceptionExecution.
 *
 * @author zhongj
 */
public interface InterceptionExecution extends Execution {
    /**
     * Gets the original execution.
     *
     * @return the original execution
     */
    String getOriginalExecution();

    /**
     * Sets the execution.
     *
     * @param execution the new execution
     */
    void setExecution(String execution);

    /**
     * Gets the original params.
     *
     * @return the original params
     */
    Object[] getOriginalParams();

    /**
     * Sets the params.
     *
     * @param params the new params
     */
    void setParams(Object[] params);
}
