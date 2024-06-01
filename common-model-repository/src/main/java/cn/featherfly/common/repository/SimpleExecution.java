
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.Arrays;

/**
 * simple execution.
 *
 * @author zhongj
 */
public class SimpleExecution implements Execution {

    private String execution;

    private Serializable[] params;

    /**
     * @param execution execution
     * @param params params
     */
    public SimpleExecution(String execution, Serializable... params) {
        this.execution = execution;
        this.params = params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExecution() {
        return execution;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable[] getParams() {
        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SimpleExecution [execution=" + execution + ", params=" + Arrays.toString(params) + "]";
    }
}
