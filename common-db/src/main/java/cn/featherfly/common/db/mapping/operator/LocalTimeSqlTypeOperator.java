
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
import java.time.LocalTime;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class LocalTimeTypeSqlTypeOperator.
 *
 * @author zhongj
 */
public class LocalTimeSqlTypeOperator implements JavaTypeSqlTypeOperator<LocalTime> {

    /**
     * Instantiates a new local time type sql type operator.
     */
    public LocalTimeSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, LocalTime value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, LocalTime value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getLocalTime(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getLocalTime(call, paramIndex);
    }

}
