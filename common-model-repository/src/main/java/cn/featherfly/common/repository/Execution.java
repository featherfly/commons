
package cn.featherfly.common.repository;

/**
 * <p>
 * execution
 * </p>
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
    Object[] getParams();
}
