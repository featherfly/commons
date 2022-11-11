
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

import cn.featherfly.common.lang.GenericType;

/**
 * Type.
 *
 * @author zhongj
 */
//public interface Type<T> extends java.lang.reflect.Type {
// YUFEI_TODO 先使用这个兼容模式，后续删除GenericType后就直接继承Type
public interface Type<T> extends GenericType<T> {
    /**
     * get type.
     *
     * @return type
     */
    @Override
    Class<T> getType();
}
