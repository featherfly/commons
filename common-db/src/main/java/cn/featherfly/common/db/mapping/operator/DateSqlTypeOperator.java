
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
import java.util.Date;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class DateTypeSqlTypeOperator.
 *
 * @author zhongj
 */
public class DateSqlTypeOperator implements JavaTypeSqlTypeOperator<Date> {

    /**
     * Instantiates a new date type sql type operator.
     */
    public DateSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Date value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Date value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getDate(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getDate(call, paramIndex);
    }

}
