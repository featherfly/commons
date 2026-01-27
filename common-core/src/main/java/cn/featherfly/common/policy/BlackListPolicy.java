
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
     * 添加项到黑名单.
     *
     * @param t 项
     * @return this
     */
    P addBlack(T t);

    /**
     * 添加多个项到黑名单.
     *
     * @param t 项数组
     * @return this
     */
    P addBlack(@SuppressWarnings("unchecked") T... t);

    /**
     * 从黑名单移除项.
     *
     * @param t 项
     * @return this
     */
    P removeBlack(T t);

    /**
     * 清除黑名单.
     *
     * @return this
     */
    P clearBlackList();

    /**
     * 获取黑名单集合.
     *
     * @return 黑名单集合
     */
    Collection<T> getBlackList();

    /**
     * 设置策略.
     *
     * @param blackList 策略
     * @return this;
     */
    P setBlackList(Collection<T> blackList);
}
