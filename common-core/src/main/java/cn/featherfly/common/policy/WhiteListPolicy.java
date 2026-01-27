
package cn.featherfly.common.policy;

import java.util.Collection;

/**
 * 白名单策略.
 *
 * @param <T> 需要判断的类型
 * @param <P> 白名单类型
 * @author zhongj
 */
public interface WhiteListPolicy<T, P extends WhiteListPolicy<T, P>> extends AllowPolicy<T> {
    /**
     * 添加项到白名单.
     *
     * @param t 项
     * @return this
     */
    P addWhite(T t);

    /**
     * 添加多个项到白名单.
     *
     * @param t 项数组
     * @return this
     */
    P addWhite(@SuppressWarnings("unchecked") T... t);

    /**
     * 从白名单移除项.
     *
     * @param t 项
     * @return this
     */
    P removeWhite(T t);

    /**
     * 清除白名单.
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
     * 设置策略.
     *
     * @param whiteList 策略
     */
    P setWhiteList(Collection<T> whiteList);
}
