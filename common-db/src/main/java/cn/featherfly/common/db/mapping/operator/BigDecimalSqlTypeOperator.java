
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

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class BigDecimalSqlTypeOperator.
 *
 * @author zhongj
 */
public class BigDecimalSqlTypeOperator implements JavaTypeSqlTypeOperator<BigDecimal> {

    /**
     * Instantiates a new big decimal sql type operator.
     */
    public BigDecimalSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, BigDecimal value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, BigDecimal value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getBigDecimal(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getBigDecimal(call, paramIndex);
    }

}
