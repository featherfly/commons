
package cn.featherfly.common.lang;

import java.lang.reflect.Type;

/**
 * GenericType.
 *
 * @param <T> 类型的泛型定义
 * @author zhongj
 */
public interface GenericType<T> extends Type {
    /**
     * 返回type.
     *
     * @return type
     */
    Class<T> getType();
}
