
package cn.featherfly.common.form;

/**
 * 表单 .
 *
 * @author zhongj
 * @param <P> 泛型
 */
public interface Form<P extends Parameter> {

    /**
     * <p>
     * 提交
     * </p>
     * .
     *
     * @param params 参数
     */
    void submit(P params);
}
