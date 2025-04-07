
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Type.java
 * @Package cn.featherfly.common.lang.reflect
 * @Description: Type
 * @author: zhongj
 * @date: 2022-11-10 16:56:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.reflect;

/**
 * Type.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface Type<T> extends java.lang.reflect.Type {
    /**
     * get type.
     *
     * @return type
     */
    Class<T> getType();

    /**
     * {@inheritDoc}
     */
    @Override
    default String getTypeName() {
        return getType().getName();
    }
}
