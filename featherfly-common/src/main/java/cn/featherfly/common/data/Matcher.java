
package cn.featherfly.common.data;

/**
 * <p>
 * 匹配器
 * </p>
 * @param <O> 泛型
 * @author 钟冀
 */
public interface Matcher<O> {
    /**
     * <p>
     * 匹配
     * </p>
     * @param o 匹配的对象
     * @return 对象是否匹配
     */
    boolean match(O o);
}
