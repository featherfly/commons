
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
     * @param prep           the prep
     * @param parameterIndex the parameter index
     * @param value          the value
     */
    void set(PreparedStatement prep, int parameterIndex, E value);

    /**
     * Gets the call param.
     *
     * @param call       the call
     * @param paramIndex the param index
     * @return the e
     */
    E get(CallableStatement call, int paramIndex);

    /**
     * Gets the result value.
     *
     * @param rs          the rs
     * @param columnIndex the column index
     * @return the e
     */
    E get(ResultSet rs, int columnIndex);
}
