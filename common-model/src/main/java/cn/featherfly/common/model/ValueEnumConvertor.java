
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ValueEnumConvertor.java
 * @Package cn.featherfly.common.model
 * @Description: ValueEnumConvertor
 * @author: zhongj
 * @date: 2021-11-30 19:15:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.model;

import cn.featherfly.common.lang.EnumConvertor;

/**
 * ValueEnumConvertor.
 *
 * @author zhongj
 */
public class ValueEnumConvertor implements EnumConvertor {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Enum<T>> T toEnum(Class<T> toClass, Object object) {
        if (toClass == null || object == null) {
            return null;
        }
        if (toClass.isEnum() && Value.class.isAssignableFrom(toClass)) {
            T[] es = toClass.getEnumConstants();
            if (es.length > 0) {
                // 类型不匹配，直接返回空
                Class<?> valueType = ((Value<?>) es[0]).value().getClass();
                if (!valueType.isAssignableFrom(object.getClass())) {
                    return null;
                }
            }
            for (T e : es) {
                if (object.equals(((Value<?>) e).value())) {
                    return e;
                }
            }
        }
        return null;
    }

}
