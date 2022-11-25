
package cn.featherfly.common.policy;

import java.util.Collection;

/**
 * 黑名单策略.
 *
 * @param <T> 需要判断的类型
 * @param <P> 黑名单类型
 * @author zhongj
 */
public interface BlackListPolicy<T, P extends BlackListPolicy<T, P>> extends AllowPolicy<T> {
    /**
     * <p>
     * 添加项到黑名单
     * </p>
     *
     * @param t 项
     * @return this
     */
    P addBlack(T t);

    /**
     * <p>
     * 添加多个项到黑名单
     * </p>
     *
     * @param t 项数组
     * @return this
     */
    P addBlack(@SuppressWarnings("unchecked") T... t);

    /**
     * <p>
     * 从黑名单移除项
     * </p>
     *
     * @param t 项
     * @return this
     */
    P removeBlack(T t);

    /**
     * <p>
     * 清除黑名单
     * </p>
     *
     * @return this
     */
    P clearBlackList();

    /**
     * <p>
     * 获取黑名单集合
     * </p>
     *
     * @return 黑名单集合
     */
    Collection<T> getBlackList();

    /**
     * <p>
     * 设置策略
     * </p>
     *
     * @param blackList 策略
     * @return this;
     */
    P setBlackList(Collection<T> blackList);
}
