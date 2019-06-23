
package cn.featherfly.common.form;

/**
 * <p>
 * 有状态表单
 * </p>
 * @param <P> 泛型
 * @author 钟冀
 */
public interface StateForm<P extends Parameter> {
    /**
     * <p>
     * 提交
     * </p>
     */
    void submit();
}
