
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
import java.time.LocalDate;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * @author zhongj
 */
public class LocalDateTypeSqlTypeOperator implements JavaTypeSqlTypeOperator<LocalDate> {

    /**
     * Instantiates a new local date type sql type operator.
     */
    public LocalDateTypeSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, LocalDate value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, LocalDate value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getLocalDate(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getLocalDate(call, paramIndex);
    }

}
