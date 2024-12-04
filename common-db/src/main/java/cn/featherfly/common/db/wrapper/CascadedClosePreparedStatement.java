
/*
 * All rights Reserved, Designed By zhongj
 * @Title: AutoClosePreparedStatement.java
 * @Description: AutoClosePreparedStatement
 * @author: zhongj
 * @date: 2023-09-18 15:43:18
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.wrapper;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * The Class CascadedClosePreparedStatement.
 *
 * @author zhongj
 */
public class CascadedClosePreparedStatement extends AbstractCascadedCloseStatement<PreparedStatement>
    implements PreparedStatement {

    /**
     * Instantiates a new auto close prepared statement.
     *
     * @param prep the prep
     */
    public CascadedClosePreparedStatement(PreparedStatement prep) {
        super(prep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeQuery() throws SQLException {
        return addResultSet(stat.executeQuery());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate() throws SQLException {
        return stat.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        stat.setNull(parameterIndex, sqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        stat.setBoolean(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        stat.setByte(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        stat.setShort(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        stat.setInt(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        stat.setLong(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        stat.setFloat(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        stat.setDouble(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        stat.setBigDecimal(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        stat.setString(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        stat.setBytes(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        stat.setDate(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        stat.setTime(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        stat.setTimestamp(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setAsciiStream(parameterIndex, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setUnicodeStream(parameterIndex, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setBinaryStream(parameterIndex, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearParameters() throws SQLException {
        stat.clearParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        stat.setObject(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws SQLException {
        return stat.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBatch() throws SQLException {
        stat.addBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        stat.setRef(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        stat.setBlob(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        stat.setClob(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        stat.setArray(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return stat.getMetaData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        stat.setDate(parameterIndex, x, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        stat.setTime(parameterIndex, x, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        stat.setTimestamp(parameterIndex, x, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        stat.setNull(parameterIndex, sqlType, typeName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        stat.setURL(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return stat.getParameterMetaData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        stat.setRowId(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        stat.setNString(parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        stat.setNCharacterStream(parameterIndex, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        stat.setNClob(parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setClob(parameterIndex, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        stat.setBlob(parameterIndex, inputStream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setNClob(parameterIndex, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        stat.setSQLXML(parameterIndex, xmlObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        stat.setAsciiStream(parameterIndex, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        stat.setBinaryStream(parameterIndex, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        stat.setAsciiStream(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        stat.setBinaryStream(parameterIndex, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        stat.setNCharacterStream(parameterIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        stat.setClob(parameterIndex, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        stat.setBlob(parameterIndex, inputStream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        stat.setNClob(parameterIndex, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long executeLargeUpdate() throws SQLException {
        return stat.executeLargeUpdate();
    }

}
