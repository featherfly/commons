
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
import java.time.LocalDateTime;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;

/**
 * @author zhongj
 */
public class LocalDateTimeSqlTypeOperator implements JavaTypeSqlTypeOperator<LocalDateTime> {

    /**
     */
    public LocalDateTimeSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, LocalDateTime value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, LocalDateTime value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getLocalDateTime(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getLocalDateTime(call, paramIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, LocalDateTime value) {
        JdbcUtils.setParameter(rs, parameterIndex, value);
    }

}
