
package cn.featherfly.common.policy;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * 拒绝名单策略.
 *
 * @author zhongj
 * @param <T> 需要判断的类型
 * @param <D> 拒绝名单类型
 */
public interface DenyListPolicy<T, D extends DenyListPolicy<T, D>> extends AllowPolicy<T> {

    /**
     * Adds the deny.
     *
     * @param deny deny policy
     * @return this
     */
    D addDeny(Predicate<T> deny);

    /**
     * Adds the deny array.
     *
     * @param denys deny policy array
     * @return this
     */
    D addDeny(@SuppressWarnings("unchecked") Predicate<T>... denys);

    /**
     * add deny policy collection.
     *
     * @param denyCollection the deny collection
     * @return this;
     */
    D addDeny(Collection<Predicate<T>> denyCollection);

    /**
     * emove deny policy from exists denys.
     *
     * @param deny deny policy
     * @return this
     */
    D removeDeny(T deny);

    /**
     * clear exists deny policys.
     *
     * @return this
     */
    D clearDeny();

    /**
     * get deny policy array.
     *
     * @return deny policy array
     */
    Predicate<T>[] getDenys();
}
