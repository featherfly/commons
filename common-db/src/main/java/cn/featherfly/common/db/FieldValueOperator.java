package cn.featherfly.common.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.vt.ValueType;
import cn.featherfly.common.repository.mapping.FieldOperator;

/**
 * field value operator.
 *
 * @author zhongj
 * @param <T> the value type
 */
public class FieldValueOperator<T> implements FieldOperator<T> {

    private JavaTypeSqlTypeOperator<T> operator;

    private T value;

    /**
     * Instantiates a new field value.
     *
     * @param operator the operator
     * @param value    the value
     */
    public FieldValueOperator(JavaTypeSqlTypeOperator<T> operator, ValueType<T> valueType) {
        super();
        AssertIllegalArgument.isNotNull(operator, "operator");
        AssertIllegalArgument.isNotNull(valueType, "valueType");
        this.operator = operator;
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
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex) {
        operator.set(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(ResultSet rs, int parameterIndex) {
        value = operator.get(rs, parameterIndex);
        return value;
    }
}
