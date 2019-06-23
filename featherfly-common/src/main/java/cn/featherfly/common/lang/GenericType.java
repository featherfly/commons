
package cn.featherfly.common.lang;

/**
 * <p>
 * GenericType
 * @param <T> 泛型类型接口
 * </p>
 * 
 * @author 钟冀
 */
public interface GenericType<T> {
    /**
     * <p>
     * 返回type
     * </p>
     * @return type
     */
    Class<T> getType();
}
