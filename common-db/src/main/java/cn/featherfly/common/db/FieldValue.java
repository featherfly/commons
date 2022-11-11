
/*
 * All rights Reserved, Designed By zhongj
 * @Title: FieldValue.java
 * @Package cn.featherfly.common.db
 * @Description: FieldValue
 * @author: zhongj
 * @date: 2022-11-10 17:10:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * FieldValue.
 *
 * @author zhongj
 * @param <T> the value type
 */
// FIXME 后续来确定此类的名称，先用这个临时名称把逻辑写完
public class FieldValue<T> {

    private JavaTypeSqlTypeOperator<T> operator;

    private T value;

    /**
     * Instantiates a new field value.
     *
     * @param operator the operator
     * @param value    the value
     */
    public FieldValue(JavaTypeSqlTypeOperator<T> operator, T value) {
        super();
        AssertIllegalArgument.isNotNull(operator, "operator");
        AssertIllegalArgument.isNotNull(value, "value");
        this.operator = operator;
        this.value = value;
    }

    /**
     * get operator value.
     *
     * @return operator
     */
    public JavaTypeSqlTypeOperator<T> getOperator() {
        return operator;
    }

    /**
     * set operator value.
     *
     * @param operator operator
     */
    public void setOperator(JavaTypeSqlTypeOperator<T> operator) {
        this.operator = operator;
    }

    /**
     * get value value.
     *
     * @return value
     */
    public T getValue() {
        return value;
    }

    /**
     * set value value.
     *
     * @param value value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Sets the.
     *
     * @param prep           the prep
     * @param parameterIndex the parameter index
     */
    public void set(PreparedStatement prep, int parameterIndex) {
        operator.set(prep, parameterIndex, value);
    }

    /**
     * Gets the.
     *
     * @param rs             the rs
     * @param parameterIndex the parameter index
     * @return the v
     */
    public T get(ResultSet rs, int parameterIndex) {
        value = operator.get(rs, parameterIndex);
        return value;
    }
}
