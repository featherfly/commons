package cn.featherfly.common.db.mapping.mappers;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.AbstractGenericJavaSqlTypeMapper;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.model.Value;

/**
 * The type Product category java sql type mapper.
 *
 * @author zhongj
 * @param <V> the value type
 * @param <E> the element type
 */
public abstract class AbstractValueJavaSqlTypeMapper<V extends Value<E>, E>
        extends AbstractGenericJavaSqlTypeMapper<V> {

    /** The element type. */
    protected Class<E> elementType;

    /**
     * Instantiates a new abstract value java sql type mapper.
     */
    public AbstractValueJavaSqlTypeMapper() {
        super();
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
     * Gets the java type.
     *
     * @param sqlType the sql type
     * @return the java type
     */
    @Override
    public Class<V> getJavaType(SQLType sqlType) {
        return getJavaType();
    }

    /**
     * Gets the sql type.
     *
     * @param javaType the java type
     * @return the sql type
     */
    @Override
    public SQLType getSqlType(GenericType<V> javaType) {
        return JDBCType.INTEGER;
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
