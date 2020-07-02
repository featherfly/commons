
package cn.featherfly.common.db.mapping.mappers;

import java.io.Serializable;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.mapping.AbstractJavaSqlTypeMapper;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * <p>
 * ObjectToJsonMapper
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> the element type
 */
public class ObjectToJsonMapper<E extends Serializable> extends AbstractJavaSqlTypeMapper<E> {

    private ObjectMapper objectMapper;

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type the type
     */
    public ObjectToJsonMapper(Class<E> type) {
        this(new GenericClass<>(type));
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type         the type
     * @param objectMapper the object mapper
     */
    public ObjectToJsonMapper(Class<E> type, ObjectMapper objectMapper) {
        this(new GenericClass<>(type), objectMapper);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type the type
     */
    public ObjectToJsonMapper(GenericType<E> type) {
        this(type, new ObjectMapper());
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type         the type
     * @param objectMapper the object mapper
     */
    public ObjectToJsonMapper(GenericType<E> type, ObjectMapper objectMapper) {
        super(type);
        this.objectMapper = objectMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(SQLType sqlType) {
        return sqlType == JDBCType.VARCHAR || sqlType == JDBCType.NVARCHAR || sqlType == JDBCType.LONGVARBINARY
                || sqlType == JDBCType.LONGNVARCHAR || sqlType == JDBCType.CLOB || sqlType == JDBCType.BLOB
                || sqlType == JDBCType.NCLOB || sqlType == JDBCType.JAVA_OBJECT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<E> getJavaType(SQLType sqlType) {
        return getGenericType().getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType(GenericType<E> javaType) {
        return JDBCType.VARCHAR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int columnIndex, E value) {
        try {
            String json = objectMapper.writerFor(getGenericType().getClass()).writeValueAsString(value);
            prep.setString(columnIndex, json);
        } catch (JsonProcessingException | SQLException e) {
            // TODO 优化错误信息
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(ResultSet rs, int columnIndex) {
        try {
            String json = rs.getString(columnIndex);
            return objectMapper.readerFor(getGenericType().getClass()).readValue(json);
        } catch (JsonProcessingException | SQLException e) {
            // TODO 优化错误信息
            throw new JdbcException(e);
        }
    }

}
