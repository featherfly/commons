package cn.featherfly.common.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * field operator.
 *
 * @author zhongj
 * @param <T> the field value type
 */
public interface FieldOperator<T> {

    /**
     * set the field value.
     *
     * @param prep           the prep
     * @param parameterIndex the parameter index
     */
    void set(PreparedStatement prep, int parameterIndex);

    /**
     * set the field value.
     *
     * @param prep          the prep
     * @param parameterName the parameter name
     */
    void set(CallableStatement prep, String parameterName);

    /**
     * get the field value.
     *
     * @param rs             the rs
     * @param parameterIndex the parameter index
     * @return the v
     */
    T get(ResultSet rs, int parameterIndex);
}
