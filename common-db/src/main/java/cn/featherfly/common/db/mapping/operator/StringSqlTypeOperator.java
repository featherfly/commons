
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
 * The Class StringTypeSqlTypeOperator.
 *
 * @author zhongj
 */
public class StringSqlTypeOperator implements JavaTypeSqlTypeOperator<String> {

    /**
     * Instantiates a new string type sql type operator.
     */
    public StringSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, String value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, String value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getString(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getString(call, paramIndex);
    }

}
