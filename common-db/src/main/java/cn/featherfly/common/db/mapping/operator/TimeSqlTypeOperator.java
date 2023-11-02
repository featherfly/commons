
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
import java.sql.Time;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class TimeSqlTypeOperator.
 *
 * @author zhongj
 */
public class TimeSqlTypeOperator implements JavaTypeSqlTypeOperator<Time> {

    /**
     * Instantiates a new time sql type operator.
     */
    public TimeSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Time value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Time value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getTime(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getTime(call, paramIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, Time value) {
        JdbcUtils.setParameter(rs, parameterIndex, value);
    }

}
