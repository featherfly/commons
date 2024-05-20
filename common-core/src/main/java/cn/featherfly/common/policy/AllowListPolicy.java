
package cn.featherfly.common.policy;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * 允许名单策略.
 *
 * @author zhongj
 * @param <T> 需要判断的类型
 * @param <A> 允许名单类型
 */
public interface AllowListPolicy<T, A extends AllowListPolicy<T, A>> extends AllowPolicy<T> {
    /**
     * add allow policy.
     *
     * @param allow allow policy
     * @return this
     */
    A addAllow(Predicate<T> allow);

    /**
     * add allow policy array.
     *
     * @param allows allow policy array
     * @return this
     */
    A addAllow(@SuppressWarnings("unchecked") Predicate<T>... allows);

    /**
     * add allow list.
     *
     * @param allows allow policy collection
     * @return this
     */
    A addAllow(Collection<Predicate<T>> allows);

    /**
     * remove allow policy from exists allows.
     *
     * @param allow allow policy
     * @return this
     */
    A removeAllow(Predicate<T> allow);

    /**
     * clear exists allows.
     *
     * @return this
     */
    A clearAllow();

    /**
     * get exists allows.
     *
     * @return allow policy array
     */
    Predicate<T>[] getAllows();
}
