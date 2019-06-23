
package cn.featherfly.common.policy;

/**
 * <p>
 * 允许列表策略
 * </p>
 *
 * @param <T> 泛型
 * @author 钟冀
 */
public interface AllowPolicy<T> {
    /**
     * <p>
     * 是否允许
     * </p>
     * @param t 需要判断的对象
     * @return 是否允许
     */
    boolean isAllow(T t);
}
