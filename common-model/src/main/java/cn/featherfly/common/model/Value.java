
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Value.java
 * @Package cn.featherfly.common.model
 * @Description: Value
 * @author: zhongj
 * @date: 2021-11-30 18:24:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.model;

import java.io.Serializable;

/**
 * Value.
 *
 * @author zhongj
 * @param <V> the value type
 */
public interface Value<V> extends Serializable {

    /**
     * Value.
     *
     * @return the v
     */
    V value();
}
