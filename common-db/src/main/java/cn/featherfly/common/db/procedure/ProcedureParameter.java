
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ProcedureParameter.java
 * @Package cn.featherfly.common.db.procedure
 * @Description: ProcedureParameter
 * @author: zhongj
 * @date: 2023-03-01 15:35:01
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.procedure;

import cn.featherfly.common.lang.vt.ValueType;

/**
 * ProcedureParameter.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface ProcedureParameter<T> extends ValueType<T> {

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    void setValue(T value);
}
