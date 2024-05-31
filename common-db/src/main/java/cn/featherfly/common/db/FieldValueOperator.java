package cn.featherfly.common.db;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.stream.Collectors;

import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.db.mapping.operator.BasicOperators;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.vt.Value;

/**
 * field value operator.
 *
 * @author zhongj
 * @param <T> the value type
 */
public class FieldValueOperator<T extends Serializable> implements FieldOperator<T>, Value<T> {

    /** The Constant EMPTY_ARRAY. */
    public static final FieldValueOperator<?>[] EMPTY_ARRAY = new FieldValueOperator[0];

    /** The operator. */
    private JavaTypeSqlTypeOperator<T> operator;

    /** The value. */
    private T value;

    private Class<T> type;

    /**
     * Instantiates a new field value.
     *
     * @param operator the operator
     * @param value the value
     */
    public FieldValueOperator(JavaTypeSqlTypeOperator<T> operator, T value) {
        this(operator, value, ClassUtils.getClass(value));
    }

    /**
     * Instantiates a new field value.
     *
     * @param operator the operator
     * @param value the value
     * @param type the type
     */
    private FieldValueOperator(JavaTypeSqlTypeOperator<T> operator, T value, Class<T> type) {
        super();
        AssertIllegalArgument.isNotNull(operator, "operator");
        AssertIllegalArgument.isNotNull(value, "value");
        this.operator = operator;
        this.value = value;
        this.type = type;
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
    public void update(ResultSet rs, int parameterIndex) {
        operator.update(rs, parameterIndex, value);
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
     * Creates FieldValueOperator or array or list.
     * <ul>
     * <li>when value is array create FieldValueOperator array
     * <li>when value is collection create FieldValueOperator list
     * <li>when value is FieldValueOperator return it
     * <li>otherwise create FieldValueOperator.
     * </ul>
     *
     * @param pm the JdbcPropertyMapping
     * @param value the value
     * @return FieldValueOperator or FieldValueOperator[] or
     *         List&lt;FieldValueOperator&gt;
     */
    @SuppressWarnings("unchecked")
    protected Object createAll(JdbcPropertyMapping pm, Object value) {
        Object param = null;
        if (value != null) {
            if (value.getClass().isArray()) {
                int length = Array.getLength(value);
                param = Array.newInstance(FieldValueOperator.class, length);
                for (int i = 0; i < length; i++) {
                    Array.set(param, i, create(pm, (Serializable) Array.get(value, i)));
                }
            } else if (value instanceof Collection) {
                param = ((Collection<Serializable>) value).stream().map(op -> create(pm, op))
                    .collect(Collectors.toList());
            } else if (value instanceof FieldValueOperator) {
                param = value;
            } else {
                param = create(pm, (Serializable) value);
            }
        }
        return param;
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param <E> the element type
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    @SuppressWarnings("unchecked")
    public static <E extends Serializable> FieldValueOperator<E> create(JdbcPropertyMapping pm, E value) {
        return value == null ? null
            : new FieldValueOperator<>((JavaTypeSqlTypeOperator<E>) pm.getJavaTypeSqlTypeOperator(), value);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Integer> create(JdbcPropertyMapping pm, int value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Integer.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Long> create(JdbcPropertyMapping pm, long value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Long.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Double> create(JdbcPropertyMapping pm, double value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Double.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Float> create(JdbcPropertyMapping pm, float value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Float.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Boolean> create(JdbcPropertyMapping pm, boolean value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Boolean.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Byte> create(JdbcPropertyMapping pm, byte value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Byte.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param pm the pm
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Short> create(JdbcPropertyMapping pm, short value) {
        return new FieldValueOperator<>(pm.getJavaTypeSqlTypeOperator(), value, Short.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param <E> the element type
     * @param value the value
     * @return the field value operator
     */
    public static <E extends Serializable> FieldValueOperator<E> create(E value) {
        if (value == null) {
            return null;
        }
        JavaTypeSqlTypeOperator<E> operator = BasicOperators.get(ClassUtils.getClass(value));
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(ClassUtils.getClass(value));
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Integer> create(int value) {
        JavaTypeSqlTypeOperator<Integer> operator = BasicOperators.get(Integer.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Integer.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Long> create(long value) {
        JavaTypeSqlTypeOperator<Long> operator = BasicOperators.get(Long.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Long.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Double> create(double value) {
        JavaTypeSqlTypeOperator<Double> operator = BasicOperators.get(Double.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Double.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Float> create(float value) {
        JavaTypeSqlTypeOperator<Float> operator = BasicOperators.get(Float.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Float.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Boolean> create(boolean value) {
        JavaTypeSqlTypeOperator<Boolean> operator = BasicOperators.get(Boolean.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Boolean.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Byte> create(byte value) {
        JavaTypeSqlTypeOperator<Byte> operator = BasicOperators.get(Byte.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Byte.TYPE);
    }

    /**
     * Creates FieldValueOperator.
     *
     * @param value the value
     * @return the field value operator
     */
    public static FieldValueOperator<Short> create(short value) {
        JavaTypeSqlTypeOperator<Short> operator = BasicOperators.get(Short.TYPE);
        if (operator != null) {
            return new FieldValueOperator<>(operator, value);
        }
        return throwNoJavaTypeSqlTypeOperatorSupport(Short.TYPE);
    }

    private static <T extends Serializable> FieldValueOperator<T> throwNoJavaTypeSqlTypeOperatorSupport(Class<T> type) {
        throw new JdbcException("no JavaTypeSqlTypeOperator support for " + type.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }
}
