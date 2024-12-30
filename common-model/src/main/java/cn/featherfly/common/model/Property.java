
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Property.java
 * @Package cn.featherfly.model
 * @Description: property
 * @author: zhongj
 * @date: 2021-05-18 12:16:18
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.model;

/**
 * Property.
 *
 * @author zhongj
 * @param <V> the value type
 */
public interface Property<V> extends Value<V> {

    /**
     * Name.
     *
     * @return the string
     */
    String name();
}
