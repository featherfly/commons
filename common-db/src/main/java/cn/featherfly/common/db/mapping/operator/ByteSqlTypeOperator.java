
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
 * The Class ByteSqlTypeOperator.
 *
 * @author zhongj
 */
public class ByteSqlTypeOperator implements JavaTypeSqlTypeOperator<Byte> {

    /**
     * Instantiates a new byte sql type operator.
     */
    public ByteSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, Byte value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, Byte value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getByte(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getByte(call, paramIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, Byte value) {
        JdbcUtils.setParameter(rs, parameterIndex, value);
    }

}
