package cn.featherfly.common.db.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.Optional;

import cn.featherfly.common.bean.BeanProperty;
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
     * @param <E>         the element type
     * @param prep        the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @return true, if successful
     */
    <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue);

    /**
     * Sets the.
     *
     * @param <E>         the element type
     * @param prep        the prep
     * @param columnIndex the column index
     * @param valueType   the value type
     * @return true, if successful
     */
    <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue, BeanProperty<E> valueType);

    /**
     * Gets the.
     *
     * @param <E>         the element type
     * @param rs          the rs
     * @param columnIndex the column index
     * @param javaType    the java type
     * @return the e
     */
    <E> Optional<E> get(ResultSet rs, int columnIndex, Class<E> javaType);

    /**
     * Gets the.
     *
     * @param <E>         the element type
     * @param rs          the rs
     * @param columnIndex the column index
     * @param javaType    the java type
     * @return the e
     */
    <E> Optional<E> get(ResultSet rs, int columnIndex, BeanProperty<E> valueType);

    /**
     * Gets the sql type.
     *
     * @param <E>      the element type
     * @param javaType the java type
     * @return the sql type
     */
    <E> SQLType getSqlType(Class<E> javaType);

    /**
     * Gets the sql type.
     *
     * @param <E>      the element type
     * @param javaType the java type
     * @return the sql type
     */
    <E> SQLType getSqlType(Type<E> javaType);

    /**
     * Gets the java type.
     *
     * @param <E>     the element type
     * @param sqlType the sql type
     * @return the java type
     */
    <E> Class<E> getJavaType(SQLType sqlType);

    /**
     * add SqlTypeToJavaRegister.
     *
     * @param register SqlTypeToJavaRegister
     */
    void add(SqlTypeToJavaRegister<?> register);

    /**
     * add JavaToSqlTypeRegister.
     *
     * @param register the register
     */
    void add(JavaToSqlTypeRegister<?> register);
}
