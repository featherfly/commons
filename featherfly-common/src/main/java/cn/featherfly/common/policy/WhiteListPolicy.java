
package cn.featherfly.common.policy;

import java.util.Collection;

/**
 * <p>
 * 白名单策略
 * </p>
 * 
 * @param <T>
 *            需要判断的类型
 * @param <P>
 *            白名单类型
 * @author 钟冀
 */
public interface WhiteListPolicy<T, P extends WhiteListPolicy<T, P>> extends AllowPolicy<T> {
    /**
     * <p>
     * 添加项到白名单
     * </p>
     * 
     * @param t
     *            项
     * @return this
     */
    P addWhite(T t);
    /**
     * <p>
     * 添加多个项到白名单
     * </p>
     * 
     * @param t
     *            项数组
     * @return this
     */
    P addWhite(@SuppressWarnings("unchecked") T... t);
    /**
     * <p>
     * 从白名单移除项
     * </p>
     * 
     * @param t
     *            项
     * @return this
     */
    P removeWhite(T t);

    /**
     * <p>
     * 清除白名单
     * </p>
     * 
     * @return this
     */
    P clearWhiteList();

    /**
     * 返回whiteList
     * 
     * @return whiteList
     */
    Collection<T> getWhiteList();

    /**
     * <p>
     * 设置策略
     * </p>
     * 
     * @param whiteList
     *            策略
     */
    void setWhiteList(Collection<T> whiteList);
}
