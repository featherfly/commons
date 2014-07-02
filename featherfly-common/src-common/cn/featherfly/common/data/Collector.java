
package cn.featherfly.common.data;

/**
 * <p>
 * Collector
 * </p>
 *
 * @author 钟冀
 */
public interface Collector<D, P> {
	/**
	 * <p>
	 * 返回数据
	 * </p>
	 * @param param 参数
	 * @return 数据
	 */
	D getData(P param);
}
