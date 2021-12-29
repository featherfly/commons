package cn.featherfly.common.db.mapping.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.model.Value;

/**
 * The Class AbstractValueJavaSqlTypeMapper.
 *
 * @author zhongj
 * @param <V> the value type
 * @param <E> the element type
 */
public abstract class AbstractValueJavaSqlTypeMapper<V extends Value<E>, E>
        extends AbstractGenericJavaSqlTypeMapper<V> {

    /** The element type. */
    protected Class<E> elementType;

    /** The value type. */
    protected Class<V> valueType;

    /**
     * Instantiates a new abstract value java sql type mapper.
     */
    public AbstractValueJavaSqlTypeMapper() {
        super();
        valueType = ClassUtils.getSuperClassGenericType(this.getClass(), 0);
        elementType = ClassUtils.getSuperClassGenericType(this.getClass(), 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<V> type) {
        return ClassUtils.isParent(getGenericType().getType(), type.getType());
    }

    /**
     * Sets the value.
     *
     * @param prep           the prep
     * @param parameterIndex the parameter index
     * @param value          the value
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, V value) {
        if (value != null) {
            JdbcUtils.setParameter(prep, parameterIndex, value.value());
        } else {
            JdbcUtils.setParameter(prep, parameterIndex, -1);
        }
    }

    /**
     * Gets the.
     *
     * @param rs          the rs
     * @param columnIndex the column index
     * @return the e
     */
    @Override
    public V get(ResultSet rs, int columnIndex) {
        @SuppressWarnings("unchecked")
        E value = (E) JdbcUtils.getResultSetValue(rs, columnIndex, elementType);
        if (value != null) {
            return toValue(value);
        } else {
            return null;
        }
    }

    /**
     * Gets the value.
     *
     * @param value the value
     * @return the v
     */
    protected abstract V toValue(E value);
}
