
package cn.featherfly.common.repository;

import java.io.Serializable;

/**
 * execution.
 *
 * @author zhongj
 */
public interface Execution {
    /**
     * get execution string
     *
     * @return execution
     */
    String getExecution();

    /**
     * get params
     *
     * @return params
     */
    Serializable[] getParams();
}
