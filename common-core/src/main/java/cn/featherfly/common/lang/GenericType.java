
package cn.featherfly.common.lang;

import java.lang.reflect.Type;

/**
 * GenericType.
 *
 * @param <T> 类型的泛型定义
 * @author zhongj
 * @deprecated 后续删除，请使用 {@link cn.featherfly.common.lang.reflect.Type}
 */
@Deprecated
public interface GenericType<T> extends Type {
    /**
     * get type.
     *
     * @return type
     */
    Class<T> getType();
}
