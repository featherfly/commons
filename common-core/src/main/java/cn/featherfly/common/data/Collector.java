
package cn.featherfly.common.data;

/**
 * Collector.
 *
 * @param <D> 泛型
 * @param <P> 泛型
 * @author zhongj
 */
@FunctionalInterface
public interface Collector<D, P> {
    /**
     * <p>
     * 返回数据
     * </p>
     *
     * @param param 参数
     * @return 数据
     */
    D getData(P param);
}
