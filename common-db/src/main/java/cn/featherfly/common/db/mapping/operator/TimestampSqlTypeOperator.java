
/*
 * All rights Reserved, Designed By zhongj
 * @Title: StringJavaSqlTypeOperator.java
 * @Package cn.featherfly.common.db.mapping.operator
 * @Description: StringJavaSqlTypeOperator
 * @author: zhongj
 * @date: 2022-11-10 17:36:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.mapping.operator;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class TimestampSqlTypeOperator.
 *
 * @author zhongj
 */
public class TimestampSqlTypeOperator implements JavaTypeSqlTypeOperator<Timestamp> {

    /**
     * Instantiates a new timestamp sql type operator.
     */
    public TimestampSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Timestamp value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Timestamp value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getTimestamp(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getTimestamp(call, paramIndex);
    }

}
