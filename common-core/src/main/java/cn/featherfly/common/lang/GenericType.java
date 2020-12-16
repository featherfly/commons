
package cn.featherfly.common.lang;

import java.lang.reflect.Type;

/**
 * <p>
 * GenericType
 * 
 * @param <T> 泛型类型接口
 *            </p>
 * @author zhongj
 */
public interface GenericType<T> extends Type {
    /**
     * <p>
     * 返回type
     * </p>
     * 
     * @return type
     */
    Class<T> getType();
}
