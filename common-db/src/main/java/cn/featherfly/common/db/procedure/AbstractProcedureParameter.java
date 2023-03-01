
/*
 * All rights Reserved, Designed By zhongj
 * @Title: AbstractProcedureParameter.java
 * @Package cn.featherfly.common.db.procedure
 * @Description: AbstractProcedureParameter
 * @author: zhongj
 * @date: 2023-03-01 15:35:01
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.procedure;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * The Class AbstractProcedureParameter.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public abstract class AbstractProcedureParameter<T> implements ProcedureParameter<T> {

    private T value;

    private Class<T> type;

    /**
     * Instantiates a new abstract procedure parameter.
     *
     * @param type the type
     */
    public AbstractProcedureParameter(Class<T> type) {
        super();
        this.type = type;
    }

    /**
     * Instantiates a new abstract procedure parameter.
     *
     * @param value the value
     */
    @SuppressWarnings("unchecked")
    public AbstractProcedureParameter(T value) {
        super();
        AssertIllegalArgument.isNotNull(value, "value");
        this.value = value;
        this.type = (Class<T>) value.getClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(T value) {
        this.value = value;
    }

}
