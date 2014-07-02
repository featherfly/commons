
package cn.featherfly.common.form;

/**
 * <p>
 * 表单
 * </p>
 *
 * @author 钟冀
 */
public interface Form<P extends Parameter> {
	/**
	 * <p>
	 * 提交
	 * </p>
	 * @param params 参数
	 */
	void submit(P params);
}
