
package cn.featherfly.common.db.mapping.mappers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.mapping.AbstractJavaSqlTypeMapper;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.reflect.ClassType;
import cn.featherfly.common.lang.reflect.Type;

/**
 * The Class ObjectToJsonMapper.
 *
 * @param <E> the element type
 * @author zhongj
 */
public class ObjectToJsonMapper<E extends Object> extends AbstractJavaSqlTypeMapper<E> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ObjectMapper objectMapper;

    private boolean storeAsString = true;

    private JavaType javaType;

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type the type
     */
    public ObjectToJsonMapper(Class<E> type) {
        this(type, MAPPER);
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
        this(new ClassType<>(type), objectMapper, storeAsString);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type the type
     */
    public ObjectToJsonMapper(Type<E> type) {
        this(type, MAPPER);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param type         the type
     * @param objectMapper the object mapper
     */
    public ObjectToJsonMapper(Type<E> type, ObjectMapper objectMapper) {
        this(type, objectMapper, true);
    }

    /**
     * Instantiates a new object to json mapper.
     *
     * @param genericType   the generic type
     * @param objectMapper  the object mapper
     * @param storeAsString the store as string
     */
    public ObjectToJsonMapper(Type<E> genericType, ObjectMapper objectMapper, boolean storeAsString) {
        this(genericType, objectMapper, null, storeAsString);
    }

    /**
     * Instantiates a new Object to json mapper.
     *
     * @param genericType   the generic type
     * @param objectMapper  the object mapper
     * @param javaType      the java type
     * @param storeAsString the store as string
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private ObjectToJsonMapper(Type<E> genericType, ObjectMapper objectMapper, JavaType javaType,
            boolean storeAsString) {
        super(genericType);
        this.objectMapper = objectMapper;
        this.storeAsString = storeAsString;
        this.javaType = javaType;

        if (this.javaType == null) {
            if (genericType.getType().isArray()) { // 处理数组
                this.javaType = objectMapper.getTypeFactory()
                        .constructArrayType(genericType.getType().getComponentType());
            } else {
                if (genericType instanceof BeanProperty) {
                    BeanProperty<?, ?> bp = (BeanProperty<?, ?>) genericType;
                    if (ClassUtils.isParent(Collection.class, bp.getType())) {
                        this.javaType = objectMapper.getTypeFactory().constructCollectionType(
                                (Class<? extends Collection>) bp.getType(), bp.getGenericType());
                    } else if (ClassUtils.isParent(Map.class, bp.getType())) {
                        if (bp.getGenericTypes().size() != 2) {
                            throw new JdbcException(Strings.format(
                                    "bean property {0} type is Map and generic type size must be 2", bp.toString()));
                        }
                        List<Class<?>> genericTypes = bp.getGenericTypes();
                        this.javaType = objectMapper.getTypeFactory().constructMapType(
                                (Class<? extends Map>) bp.getType(), genericTypes.get(0), genericTypes.get(1));
                    }
                }
            }
        }

        if (this.javaType == null) {
            this.javaType = objectMapper.getTypeFactory().constructType(genericType.getType());
        }
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
                String json = objectMapper.writerFor(javaType).writeValueAsString(value);
                prep.setString(columnIndex, json);
            } else {
                ByteArrayInputStream is = new ByteArrayInputStream(
                        objectMapper.writerFor(getType().getType()).writeValueAsBytes(value));
                prep.setBlob(columnIndex, is);
            }
        } catch (JsonProcessingException | SQLException e) {
            // ENHANCE 优化错误信息
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, E value) {
        try {
            if (value == null) {
                call.setObject(parameterName, null);
            }
            if (storeAsString) {
                String json = objectMapper.writerFor(javaType).writeValueAsString(value);
                call.setString(parameterName, json);
            } else {
                ByteArrayInputStream is = new ByteArrayInputStream(
                        objectMapper.writerFor(getType().getType()).writeValueAsBytes(value));
                call.setBlob(parameterName, is);
            }
        } catch (JsonProcessingException | SQLException e) {
            // ENHANCE 优化错误信息
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, E value) {
        try {
            if (value == null) {
                rs.updateObject(parameterIndex, null);
            }
            if (storeAsString) {
                String json = objectMapper.writerFor(javaType).writeValueAsString(value);
                rs.updateString(parameterIndex, json);
            } else {
                ByteArrayInputStream is = new ByteArrayInputStream(
                        objectMapper.writerFor(getType().getType()).writeValueAsBytes(value));
                rs.updateBlob(parameterIndex, is);
            }
        } catch (JsonProcessingException | SQLException e) {
            // ENHANCE 优化错误信息
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
                return objectMapper.readerFor(javaType).readValue(json);
            } else {
                Blob blob = rs.getBlob(columnIndex);
                if (blob == null) {
                    return null;
                }
                return objectMapper.readerFor(getType().getType()).readValue(blob.getBinaryStream());
            }
        } catch (IOException | SQLException e) {
            // ENHANCE 优化错误信息
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(CallableStatement call, int paramIndex) {
        try {
            if (storeAsString) {
                String json = call.getString(paramIndex);
                if (StringUtils.isBlank(json)) {
                    return null;
                }
                return objectMapper.readerFor(javaType).readValue(json);
            } else {
                Blob blob = call.getBlob(paramIndex);
                if (blob == null) {
                    return null;
                }
                return objectMapper.readerFor(getType().getType()).readValue(blob.getBinaryStream());
            }
        } catch (IOException | SQLException e) {
            // ENHANCE 优化错误信息
            throw new JdbcException(e);
        }
    }
}
