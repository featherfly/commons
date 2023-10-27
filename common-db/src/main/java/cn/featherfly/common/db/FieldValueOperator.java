package cn.featherfly.common.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.db.mapping.operator.BasicOperators;
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
    public void set(CallableStatement prep, String parameterName) {
        operator.set(prep, parameterName, value);
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
     * 设置value.
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FieldValueOperator<?> other = (FieldValueOperator<?>) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FieldValueOperator [value=" + value + ", operator=" + operator.getClass().getName() + "]";
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param <E>   the element type
     * @param pm    the pm
     * @param value the value
     * @return the field value operator
     */
    @SuppressWarnings("unchecked")
    public static <E> FieldValueOperator<E> create(JdbcPropertyMapping pm, E value) {
        return value == null ? null
                : new FieldValueOperator<>((JavaTypeSqlTypeOperator<E>) pm.getJavaTypeSqlTypeOperator(), value);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param <E>   the element type
     * @param value the value
     * @return the field value operator
     */
    public static <E> FieldValueOperator<E> create(E value) {
        if (value == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        JavaTypeSqlTypeOperator<E> operator = (JavaTypeSqlTypeOperator<E>) BasicOperators.getOperator(value.getClass());
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        throw new JdbcException("no JavaTypeSqlTypeOperator support for " + value.getClass().getName());
    }
}
