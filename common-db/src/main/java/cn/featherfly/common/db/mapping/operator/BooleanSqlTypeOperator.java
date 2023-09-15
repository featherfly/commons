
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
 * The Class BooleanTypeSqlTypeOperator.
 *
 * @author zhongj
 */
public class BooleanSqlTypeOperator implements JavaTypeSqlTypeOperator<Boolean> {

    /**
     * Instantiates a new boolean type sql type operator.
     */
    public BooleanSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Boolean value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Boolean value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getBoolean(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getBoolean(call, paramIndex);
    }

}
