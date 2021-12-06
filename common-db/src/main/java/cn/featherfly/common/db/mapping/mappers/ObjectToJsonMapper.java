
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

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.mapping.AbstractJavaSqlTypeMapper;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * The Class ObjectToJsonMapper.
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
    protected boolean support(SQLType sqlType) {
        if (storeAsString) {
            return sqlType == JDBCType.VARCHAR || sqlType == JDBCType.NVARCHAR || sqlType == JDBCType.LONGVARCHAR
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
    public void set(PreparedStatement prep, int columnIndex, E value) {
        try {
            if (value == null) {
                prep.setObject(columnIndex, null);
            }
            if (storeAsString) {
                String json = objectMapper.writerFor(getGenericType().getType()).writeValueAsString(value);
                prep.setString(columnIndex, json);
            } else {
                ByteArrayInputStream is = new ByteArrayInputStream(
                        objectMapper.writerFor(getGenericType().getType()).writeValueAsBytes(value));
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
                if (StringUtils.isBlank(json)) {
                    return null;
                }
                return objectMapper.readerFor(getGenericType().getType()).readValue(json);
            } else {
                Blob blob = rs.getBlob(columnIndex);
                if (blob == null) {
                    return null;
                }
                return objectMapper.readerFor(getGenericType().getType()).readValue(blob.getBinaryStream());
            }
        } catch (IOException | SQLException e) {
            // TODO 优化错误信息
            throw new JdbcException(e);
        }
    }
}
