
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
 * The Class BytesSqlTypeOperator.
 *
 * @author zhongj
 */
public class BytesSqlTypeOperator implements JavaTypeSqlTypeOperator<byte[]> {

    /**
     * Instantiates a new bytes sql type operator.
     */
    public BytesSqlTypeOperator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, byte[] value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, byte[] value) {
        JdbcUtils.setParameter(call, parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getBytes(rs, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getBytes(call, paramIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ResultSet rs, int parameterIndex, byte[] value) {
        JdbcUtils.setParameter(rs, parameterIndex, value);
    }

}
