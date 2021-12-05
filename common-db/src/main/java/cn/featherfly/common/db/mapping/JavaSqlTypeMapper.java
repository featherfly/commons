
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

    /**
     * Support.
     *
     * @param sqlType    the sql type
     * @param tableName  the table name
     * @param columnName the column name
     * @return true, if successful
     */
    boolean support(SQLType sqlType, String tableName, String columnName);

    /**
     * Support.
     *
     * @param type the type
     * @return true, if successful
     */
    boolean support(GenericType<E> type);

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
