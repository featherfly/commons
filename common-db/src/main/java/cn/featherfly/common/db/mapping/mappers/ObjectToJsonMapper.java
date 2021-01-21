
package cn.featherfly.common.db.mapping.mappers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
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

    private boolean storeAsString = true;

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
        this(type, objectMapper, true);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type          the type
     * @param objectMapper  the object mapper
     * @param storeAsString the store as string
     */
    public ObjectToJsonMapper(Class<E> type, ObjectMapper objectMapper, boolean storeAsString) {
        this(new GenericClass<>(type), objectMapper, storeAsString);
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
        this(type, objectMapper, true);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param genericType   the generic type
     * @param objectMapper  the object mapper
     * @param storeAsString the store as string
     */
    public ObjectToJsonMapper(GenericType<E> genericType, ObjectMapper objectMapper, boolean storeAsString) {
        super(genericType);
        this.objectMapper = objectMapper;
        this.storeAsString = storeAsString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(SQLType sqlType) {
        if (storeAsString) {
            return sqlType == JDBCType.VARCHAR || sqlType == JDBCType.NVARCHAR || sqlType == JDBCType.LONGVARBINARY
                    || sqlType == JDBCType.LONGNVARCHAR || sqlType == JDBCType.CLOB || sqlType == JDBCType.NCLOB
                    || sqlType == JDBCType.JAVA_OBJECT;
        } else {
            return sqlType == JDBCType.BLOB;
        }
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
        if (storeAsString) {
            return JDBCType.VARCHAR;
        } else {
            return JDBCType.BLOB;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int columnIndex, E value) {
        try {
            if (storeAsString) {
                String json = objectMapper.writerFor(getGenericType().getClass()).writeValueAsString(value);
                prep.setString(columnIndex, json);
            } else {
                ByteArrayInputStream is = new ByteArrayInputStream(
                        objectMapper.writerFor(getGenericType().getClass()).writeValueAsBytes(value));
                prep.setBlob(columnIndex, is);
            }
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
            if (storeAsString) {
                String json = rs.getString(columnIndex);
                return objectMapper.readerFor(getGenericType().getClass()).readValue(json);
            } else {
                Blob blob = rs.getBlob(columnIndex);
                return objectMapper.readerFor(getGenericType().getClass()).readValue(blob.getBinaryStream());
            }
        } catch (IOException | SQLException e) {
            // TODO 优化错误信息
            throw new JdbcException(e);
        }
    }

}
