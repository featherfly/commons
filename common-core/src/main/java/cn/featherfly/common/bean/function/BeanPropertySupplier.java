
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanPropertySupplier.java
 * @Package cn.featherfly.common.lang.function
 * @Description: BeanPropertySupplier
 * @author: zhongj
 * @date: 2023-07-18 17:18:18
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean.function;

import java.io.Serializable;
import java.util.function.Supplier;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LambdaUtils;
import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;

/**
 * BeanPropertySupplier.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <V> the generic property value type
 */
public interface BeanPropertySupplier<T, V> extends Supplier<V>, Serializable {

    /**
     * Gets the bean type.
     *
     * @return the bean type
     */
    @SuppressWarnings("unchecked")
    default Class<T> getBeanType() {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(this);
        return (Class<T>) ClassUtils.forName(info.getMethodInstanceClassName());
    }

    /**
     * Gets the property name.
     *
     * @return the property name
     */
    default String getPropertyName() {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(this);
        return info.getPropertyName();
    }
}
