package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.Optional;

import cn.featherfly.common.bean.Property;
import cn.featherfly.common.lang.reflect.Type;

/**
 * The Interface Store.
 *
 * @author zhongj
 */
public interface Store {

    /**
     * Sets the.
     *
     * @param <E> the element type
     * @param prep the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @return true, if successful
     */
    <E extends Serializable> boolean set(PreparedStatement prep, int columnIndex, E columnValue);

    /**
     * Sets the.
     *
     * @param <E> the element type
     * @param prep the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @param valueType the value type
     * @return true, if successful
     */
    <E extends Serializable> boolean set(PreparedStatement prep, int columnIndex, E columnValue,
        Property<?, E> valueType);

    /**
     * Gets the.
     *
     * @param <E> the element type
     * @param rs the rs
     * @param columnIndex the column index
     * @param javaType the java type
     * @return the e
     */
    <E extends Serializable> Optional<E> get(ResultSet rs, int columnIndex, Class<E> javaType);

    /**
     * Gets the.
     *
     * @param <E> the element type
     * @param call the call
     * @param columnIndex the column index
     * @param javaType the java type
     * @return the e
     */
    <E extends Serializable> Optional<E> get(CallableStatement call, int columnIndex, Class<E> javaType);

    /**
     * Gets the.
     *
     * @param <E> the element type
     * @param rs the rs
     * @param columnIndex the column index
     * @param valueType the value type
     * @return the e
     */
    <E extends Serializable> Optional<E> get(ResultSet rs, int columnIndex, Property<?, E> valueType);

    /**
     * Gets the sql type.
     *
     * @param <E> the element type
     * @param javaType the java type
     * @return the sql type
     */
    <E extends Serializable> SQLType getSqlType(Class<E> javaType);

    /**
     * Gets the sql type.
     *
     * @param <E> the element type
     * @param javaType the java type
     * @return the sql type
     */
    <E extends Serializable> SQLType getSqlType(Type<E> javaType);

    /**
     * Gets the java type.
     *
     * @param <E> the element type
     * @param sqlType the sql type
     * @return the java type
     */
    <E extends Serializable> Class<E> getJavaType(SQLType sqlType);

    /**
     * add SqlTypeToJavaRegister.
     *
     * @param register SqlTypeToJavaRegister
     */
    void add(SqlTypeToJavaRegister<Serializable> register);

    /**
     * add JavaToSqlTypeRegister.
     *
     * @param register the register
     */
    void add(JavaToSqlTypeRegister<Serializable> register);
}
