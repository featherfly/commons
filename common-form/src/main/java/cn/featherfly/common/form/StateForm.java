
package cn.featherfly.common.form;

/**
 * 有状态表单 .
 *
 * @author zhongj
 * @param <P> 泛型
 */
public interface StateForm<P extends Parameter> {
    /**
     * 提交.
     */
    void submit();
}
