
package cn.featherfly.common.db.mapping;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JavaSqlTypeOperator.
 *
 * @author zhongj
 * @param <E> to regist java type
 */
public interface JavaTypeSqlTypeOperator<E> {

    /**
     * Sets the value.
     *
     * @param prep           the PreparedStatement
     * @param parameterIndex the parameter index
     * @param value          the parameter value
     */
    void set(PreparedStatement prep, int parameterIndex, E value);

    /**
     * Sets the value.
     *
     * @param call          the CallableStatement
     * @param parameterName the parameter name
     * @param value         the parameter value
     */
    void set(CallableStatement call, String parameterName, E value);

    /**
     * Gets the result value.
     *
     * @param rs          the ResultSet
     * @param columnIndex the column index
     * @return the value
     */
    E get(ResultSet rs, int columnIndex);

    /**
     * Gets the call param.
     *
     * @param call       the CallableStatement
     * @param paramIndex the param index
     * @return the value
     */
    E get(CallableStatement call, int paramIndex);
}
