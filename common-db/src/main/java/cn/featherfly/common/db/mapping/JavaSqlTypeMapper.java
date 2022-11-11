
package cn.featherfly.common.db.mapping;

import java.sql.SQLType;

import cn.featherfly.common.lang.reflect.Type;

/**
 * java type and sql type mapper.
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface JavaSqlTypeMapper<E extends Object> extends JavaTypeSqlTypeOperator<E> {

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
    boolean support(Type<E> type);
}
