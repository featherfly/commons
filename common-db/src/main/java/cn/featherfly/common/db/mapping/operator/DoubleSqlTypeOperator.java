
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
 * The Class DubleSqlTypeOperator.
 *
 * @author zhongj
 */
public class DoubleSqlTypeOperator implements JavaTypeSqlTypeOperator<Double> {

    /**
     * Instantiates a new duble sql type operator.
     */
    public DoubleSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Double value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Double value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getDouble(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getDouble(call, paramIndex);
    }

}
