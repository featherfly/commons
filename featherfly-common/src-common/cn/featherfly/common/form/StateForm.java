
package cn.featherfly.common.form;

/**
 * <p>
 * 有状态表单
 * </p>
 *
 * @author 钟冀
 */
public interface StateForm<P extends Parameter> {
	/**
	 * <p>
	 * 提交
	 * </p>
	 * @param params 参数
	 */
	void submit();
}
