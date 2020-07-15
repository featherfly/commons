
package cn.featherfly.common.db.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;

import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * SqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface JavaSqlTypeMapper<E extends Object> {

    boolean support(SQLType sqlType);

    boolean support(GenericType<E> type);

    /**
     * Gets the java type.
     *
     * @param sqlType the sql type
     * @return the java type
     */
    Class<E> getJavaType(SQLType sqlType);

    /**
     * Gets the sql type.
     *
     * @param javaType the java type
     * @return the sql type
     */
    SQLType getSqlType(GenericType<E> javaType);

    /**
     * Sets the value.
     *
     * @param prep           the prep
     * @param parameterIndex the parameter index
     * @param value          the value
     */
    void set(PreparedStatement prep, int parameterIndex, E value);

    /**
     * Gets the.
     *
     * @param rs          the rs
     * @param columnIndex the column index
     * @return the e
     */
    E get(ResultSet rs, int columnIndex);
}
