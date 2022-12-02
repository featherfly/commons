package cn.featherfly.common.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.vt.Value;

/**
 * field value operator.
 *
 * @author zhongj
 * @param <T> the value type
 */
public class FieldValueOperator<T> implements FieldOperator<T>, Value<T> {

    /** The operator. */
    private JavaTypeSqlTypeOperator<T> operator;

    /** The value. */
    private T value;

    /**
     * Instantiates a new field value.
     *
     * @param operator the operator
     * @param value    the value
     */
    public FieldValueOperator(JavaTypeSqlTypeOperator<T> operator, T value) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return value;
    }

    /**
     * 设置value
     *
     * @param value value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FieldValueOperator [value=" + value + ", operator=" + operator.getClass().getName() + "]";
    }

    @SuppressWarnings("unchecked")
    public static <E> FieldValueOperator<E> craete(JdbcPropertyMapping pm, E value) {
        return value == null ? null
                : new FieldValueOperator<>((JavaTypeSqlTypeOperator<E>) pm.getJavaTypeSqlTypeOperator(), value);
    }

}
