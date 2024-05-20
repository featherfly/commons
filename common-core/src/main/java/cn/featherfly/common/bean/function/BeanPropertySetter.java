
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

import cn.featherfly.common.bean.PropertyDescriptor;
import cn.featherfly.common.function.serializable.SerializableBiConsumer;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LambdaUtils;
import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;

/**
 * BeanPropertySetter.
 *
 * @author zhongj
 * @param <T> the generic bean type
 * @param <V> the generic property value type
 */
public interface BeanPropertySetter<T, V> extends PropertyDescriptor<T, V>, SerializableBiConsumer<T, V> {

    /**
     * Gets the bean type.
     *
     * @return the bean type
     */
    @Override
    @SuppressWarnings("unchecked")
    default Class<T> getInstanceType() {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(this);
        return (Class<T>) ClassUtils.forName(info.getMethodInstanceClassName());
    }

    /**
     * Gets the property type.
     *
     * @return the property type
     */
    @SuppressWarnings("unchecked")
    @Override
    default Class<V> getType() {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(this);
        return (Class<V>) info.getPropertyType();
    }

    /**
     * Gets the property name.
     *
     * @return the property name
     */
    @Override
    default String getName() {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(this);
        return info.getPropertyName();
    }

    @Override
    default int getIndex() {
        return -1;
    }
}
