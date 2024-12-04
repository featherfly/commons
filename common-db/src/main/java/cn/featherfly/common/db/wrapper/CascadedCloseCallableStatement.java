
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
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
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
import java.util.Map;

/**
 * The Class CascadedCloseCallableStatement.
 *
 * @author zhongj
 */
public class CascadedCloseCallableStatement extends AbstractCascadedCloseStatement<CallableStatement>
    implements CallableStatement {

    /**
     * Instantiates a new cascaded close callable statement.
     *
     * @param call the call
     */
    public CascadedCloseCallableStatement(CallableStatement call) {
        super(call);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeQuery() throws SQLException {
        return stat.executeQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType);
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
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, scale);
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
    public boolean wasNull() throws SQLException {
        return stat.wasNull();
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
    public String getString(int parameterIndex) throws SQLException {
        return stat.getString(parameterIndex);
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
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return stat.getBoolean(parameterIndex);
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
    public byte getByte(int parameterIndex) throws SQLException {
        return stat.getByte(parameterIndex);
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
    public short getShort(int parameterIndex) throws SQLException {
        return stat.getShort(parameterIndex);
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
    public int getInt(int parameterIndex) throws SQLException {
        return stat.getInt(parameterIndex);
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
    public long getLong(int parameterIndex) throws SQLException {
        return stat.getLong(parameterIndex);
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
    public float getFloat(int parameterIndex) throws SQLException {
        return stat.getFloat(parameterIndex);
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
    public double getDouble(int parameterIndex) throws SQLException {
        return stat.getDouble(parameterIndex);
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
    @Deprecated
    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return stat.getBigDecimal(parameterIndex, scale);
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
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return stat.getBytes(parameterIndex);
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
    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return stat.getDate(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return stat.getTime(parameterIndex);
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
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return stat.getTimestamp(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return stat.getObject(parameterIndex);
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
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return stat.getBigDecimal(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return stat.getObject(parameterIndex, map);
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
    public Ref getRef(int parameterIndex) throws SQLException {
        return stat.getRef(parameterIndex);
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
    public Blob getBlob(int parameterIndex) throws SQLException {
        return stat.getBlob(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return stat.getClob(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return stat.getArray(parameterIndex);
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
    public long executeLargeUpdate() throws SQLException {
        return stat.executeLargeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getDate(parameterIndex, cal);
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
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getTime(parameterIndex, cal);
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
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getTimestamp(parameterIndex, cal);
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
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, typeName);
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
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType);
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
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, scale);
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
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, typeName);
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
    public URL getURL(int parameterIndex) throws SQLException {
        return stat.getURL(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        stat.setURL(parameterName, val);
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
    public void setNull(String parameterName, int sqlType) throws SQLException {
        stat.setNull(parameterName, sqlType);
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
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        stat.setBoolean(parameterName, x);
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
    public void setByte(String parameterName, byte x) throws SQLException {
        stat.setByte(parameterName, x);
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
    public void setShort(String parameterName, short x) throws SQLException {
        stat.setShort(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        stat.setInt(parameterName, x);
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
    public void setLong(String parameterName, long x) throws SQLException {
        stat.setLong(parameterName, x);
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
    public void setFloat(String parameterName, float x) throws SQLException {
        stat.setFloat(parameterName, x);
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
    public void setDouble(String parameterName, double x) throws SQLException {
        stat.setDouble(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        stat.setBigDecimal(parameterName, x);
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
    public void setString(String parameterName, String x) throws SQLException {
        stat.setString(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        stat.setBytes(parameterName, x);
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
    public void setDate(String parameterName, Date x) throws SQLException {
        stat.setDate(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        stat.setTime(parameterName, x);
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
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        stat.setTimestamp(parameterName, x);
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
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        stat.setAsciiStream(parameterName, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        stat.setBinaryStream(parameterName, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType, scale);
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
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType);
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
    public void setObject(String parameterName, Object x) throws SQLException {
        stat.setObject(parameterName, x);
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
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        stat.setCharacterStream(parameterName, reader, length);
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
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        stat.setDate(parameterName, x, cal);
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
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        stat.setTime(parameterName, x, cal);
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
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        stat.setTimestamp(parameterName, x, cal);
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
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        stat.setNull(parameterName, sqlType, typeName);
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
    public String getString(String parameterName) throws SQLException {
        return stat.getString(parameterName);
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
    public boolean getBoolean(String parameterName) throws SQLException {
        return stat.getBoolean(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(String parameterName) throws SQLException {
        return stat.getByte(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(String parameterName) throws SQLException {
        return stat.getShort(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(String parameterName) throws SQLException {
        return stat.getInt(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(String parameterName) throws SQLException {
        return stat.getLong(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(String parameterName) throws SQLException {
        return stat.getFloat(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(String parameterName) throws SQLException {
        return stat.getDouble(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return stat.getBytes(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDate(String parameterName) throws SQLException {
        return stat.getDate(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time getTime(String parameterName) throws SQLException {
        return stat.getTime(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return stat.getTimestamp(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(String parameterName) throws SQLException {
        return stat.getObject(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return stat.getBigDecimal(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return stat.getObject(parameterName, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return stat.getRef(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return stat.getBlob(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return stat.getClob(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Array getArray(String parameterName) throws SQLException {
        return stat.getArray(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return stat.getDate(parameterName, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return stat.getTime(parameterName, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return stat.getTimestamp(parameterName, cal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getURL(String parameterName) throws SQLException {
        return stat.getURL(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return stat.getRowId(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return stat.getRowId(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        stat.setRowId(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        stat.setNString(parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        stat.setNCharacterStream(parameterName, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        stat.setNClob(parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        stat.setClob(parameterName, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        stat.setBlob(parameterName, inputStream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        stat.setNClob(parameterName, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return stat.getNClob(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return stat.getNClob(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        stat.setSQLXML(parameterName, xmlObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return stat.getSQLXML(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return stat.getSQLXML(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return stat.getNString(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNString(String parameterName) throws SQLException {
        return stat.getNString(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return stat.getNCharacterStream(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return stat.getNCharacterStream(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return stat.getCharacterStream(parameterIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return stat.getCharacterStream(parameterName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        stat.setBlob(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        stat.setClob(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        stat.setAsciiStream(parameterName, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        stat.setBinaryStream(parameterName, x, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        stat.setCharacterStream(parameterName, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        stat.setAsciiStream(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        stat.setBinaryStream(parameterName, x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        stat.setCharacterStream(parameterName, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        stat.setNCharacterStream(parameterName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        stat.setClob(parameterName, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        stat.setBlob(parameterName, inputStream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        stat.setNClob(parameterName, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return stat.getObject(parameterIndex, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return stat.getObject(parameterName, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength)
        throws SQLException {
        stat.setObject(parameterName, x, targetSqlType, scaleOrLength);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, typeName);
    }

}
