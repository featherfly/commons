
package cn.featherfly.common.db.mapping;

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
     * Gets the.
     *
     * @param rs          the rs
     * @param columnIndex the column index
     * @return the e
     */
    E get(ResultSet rs, int columnIndex);
}
