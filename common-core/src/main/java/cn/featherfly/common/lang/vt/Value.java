
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Value.java
 * @Package cn.featherfly.common.model
 * @Description: Value
 * @author: zhongj
 * @date: 2021-11-30 18:24:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.vt;

/**
 * Value.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface Value<T> {

    /**
     * get value.
     *
     * @return value
     */
    T getValue();
}
