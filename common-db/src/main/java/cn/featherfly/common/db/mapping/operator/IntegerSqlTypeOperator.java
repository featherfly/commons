
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

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * The Class IntegerTypeSqlTypeOperator.
 *
 * @author zhongj
 */
public class IntegerSqlTypeOperator implements JavaTypeSqlTypeOperator<Integer> {

    /**
     * Instantiates a new integer type sql type operator.
     */
    public IntegerSqlTypeOperator() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Integer value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Integer value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getInteger(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getInteger(call, paramIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, Integer value) {
        JdbcUtils.setParameter(rs, parameterIndex, value);
    }

}
