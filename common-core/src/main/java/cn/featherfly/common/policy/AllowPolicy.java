
package cn.featherfly.common.policy;

import java.util.function.Predicate;

/**
 * 允许列表策略.
 *
 * @param <T> 泛型
 * @author zhongj
 */
@FunctionalInterface
public interface AllowPolicy<T> extends Predicate<T> {
    /**
     * 是否允许.
     *
     * @param t 需要判断的对象
     * @return 是否允许
     */
    boolean isAllow(T t);

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean test(T t) {
        return isAllow(t);
    }
}
