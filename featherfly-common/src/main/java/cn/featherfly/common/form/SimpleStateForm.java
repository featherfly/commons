
package cn.featherfly.common.form;

/**
 * <p>
 * 有状态表单
 * </p>
 * @param <P> 泛型
 * @author 钟冀
 */
public class SimpleStateForm<P extends Parameter> implements StateForm<P>{

    private P parameter;

    private Form<P> form;

    /**
     * @param form 表单
     * @param parameter 参数
     */
    public SimpleStateForm(Form<P> form, P parameter) {
        this.parameter = parameter;
        this.form = form;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void submit() {
        form.submit(parameter);
    }
}
