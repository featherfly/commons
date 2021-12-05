
package cn.featherfly.common.repository;

/**
 * ExecutionInterceptor.
 *
 * @author zhongj
 */
public interface ExecutionInterceptor<E extends InterceptionExecution> {

    /**
     * Pre handle.
     *
     * @param execution the execution
     */
    void preHandle(E execution);

    /**
     * Post handle.
     *
     * @param execution the execution
     */
    void postHandle(E execution);

}
