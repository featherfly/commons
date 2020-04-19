
package cn.featherfly.common.repository;

import java.util.Arrays;

/**
 * <p>
 * simple sql execution
 * </p>
 *
 * @author zhongj
 */
public class SimpleExecution implements Execution {

    private String execution;

    private Object[] params;

    /**
     * @param execution execution
     * @param params    params
     */
    public SimpleExecution(String execution, Object... params) {
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
    public Object[] getParams() {
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
