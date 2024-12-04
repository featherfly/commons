
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

public class AutoCloseCallableStatement extends ResultSetCreatable<CallableStatement> implements CallableStatement {

    public AutoCloseCallableStatement(CallableStatement call) {
        super(call);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return stat.executeQuery();
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public int executeUpdate() throws SQLException {
        return stat.executeUpdate();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        stat.setNull(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        stat.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        stat.setByte(parameterIndex, x);
    }

    @Override
    public boolean wasNull() throws SQLException {
        return stat.wasNull();
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        stat.setShort(parameterIndex, x);
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return stat.getString(parameterIndex);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        stat.setInt(parameterIndex, x);
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return stat.getBoolean(parameterIndex);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        stat.setLong(parameterIndex, x);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return stat.getByte(parameterIndex);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        stat.setFloat(parameterIndex, x);
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return stat.getShort(parameterIndex);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        stat.setDouble(parameterIndex, x);
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return stat.getInt(parameterIndex);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        stat.setBigDecimal(parameterIndex, x);
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return stat.getLong(parameterIndex);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        stat.setString(parameterIndex, x);
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return stat.getFloat(parameterIndex);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        stat.setBytes(parameterIndex, x);
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return stat.getDouble(parameterIndex);
    }

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

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        stat.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        stat.setTimestamp(parameterIndex, x);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return stat.getBytes(parameterIndex);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return stat.getDate(parameterIndex);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return stat.getTime(parameterIndex);
    }

    @Deprecated
    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return stat.getTimestamp(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return stat.getObject(parameterIndex);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        stat.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return stat.getBigDecimal(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return stat.getObject(parameterIndex, map);
    }

    @Override
    public void clearParameters() throws SQLException {
        stat.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return stat.getRef(parameterIndex);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        stat.setObject(parameterIndex, x);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return stat.getBlob(parameterIndex);
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return stat.getClob(parameterIndex);
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return stat.getArray(parameterIndex);
    }

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

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getDate(parameterIndex, cal);
    }

    @Override
    public void addBatch() throws SQLException {
        stat.addBatch();
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getTime(parameterIndex, cal);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return stat.getTimestamp(parameterIndex, cal);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        stat.setRef(parameterIndex, x);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        stat.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        stat.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        stat.setArray(parameterIndex, x);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return stat.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        stat.setDate(parameterIndex, x, cal);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        stat.setTime(parameterIndex, x, cal);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        stat.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        stat.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return stat.getURL(parameterIndex);
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        stat.setURL(parameterName, val);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        stat.setURL(parameterIndex, x);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        stat.setNull(parameterName, sqlType);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return stat.getParameterMetaData();
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        stat.setBoolean(parameterName, x);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        stat.setRowId(parameterIndex, x);
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        stat.setByte(parameterName, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        stat.setNString(parameterIndex, value);
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        stat.setShort(parameterName, x);
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        stat.setInt(parameterName, x);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        stat.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        stat.setLong(parameterName, x);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        stat.setNClob(parameterIndex, value);
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        stat.setFloat(parameterName, x);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        stat.setDouble(parameterName, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        stat.setBigDecimal(parameterName, x);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        stat.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        stat.setString(parameterName, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        stat.setBytes(parameterName, x);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        stat.setDate(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        stat.setTime(parameterName, x);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        stat.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        stat.setTimestamp(parameterName, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        stat.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        stat.setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        stat.setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType, scale);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        stat.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        stat.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        stat.setObject(parameterName, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        stat.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        stat.setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        stat.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        stat.setDate(parameterName, x, cal);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        stat.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        stat.setTime(parameterName, x, cal);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        stat.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        stat.setTimestamp(parameterName, x, cal);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        stat.setClob(parameterIndex, reader);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        stat.setNull(parameterName, sqlType, typeName);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        stat.setBlob(parameterIndex, inputStream);
    }

    @Override
    public String getString(String parameterName) throws SQLException {
        return stat.getString(parameterName);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        stat.setNClob(parameterIndex, reader);
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return stat.getBoolean(parameterName);
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return stat.getByte(parameterName);
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return stat.getShort(parameterName);
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return stat.getInt(parameterName);
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return stat.getLong(parameterName);
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return stat.getFloat(parameterName);
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return stat.getDouble(parameterName);
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return stat.getBytes(parameterName);
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return stat.getDate(parameterName);
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return stat.getTime(parameterName);
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return stat.getTimestamp(parameterName);
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return stat.getObject(parameterName);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return stat.getBigDecimal(parameterName);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return stat.getObject(parameterName, map);
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return stat.getRef(parameterName);
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return stat.getBlob(parameterName);
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return stat.getClob(parameterName);
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        return stat.getArray(parameterName);
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return stat.getDate(parameterName, cal);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return stat.getTime(parameterName, cal);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return stat.getTimestamp(parameterName, cal);
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return stat.getURL(parameterName);
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return stat.getRowId(parameterIndex);
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return stat.getRowId(parameterName);
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        stat.setRowId(parameterName, x);
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        stat.setNString(parameterName, value);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        stat.setNCharacterStream(parameterName, value, length);
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        stat.setNClob(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        stat.setClob(parameterName, reader, length);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        stat.setBlob(parameterName, inputStream, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        stat.setNClob(parameterName, reader, length);
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return stat.getNClob(parameterIndex);
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return stat.getNClob(parameterName);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        stat.setSQLXML(parameterName, xmlObject);
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return stat.getSQLXML(parameterIndex);
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return stat.getSQLXML(parameterName);
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return stat.getNString(parameterIndex);
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return stat.getNString(parameterName);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return stat.getNCharacterStream(parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return stat.getNCharacterStream(parameterName);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return stat.getCharacterStream(parameterIndex);
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return stat.getCharacterStream(parameterName);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        stat.setBlob(parameterName, x);
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        stat.setClob(parameterName, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        stat.setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        stat.setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        stat.setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        stat.setAsciiStream(parameterName, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        stat.setBinaryStream(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        stat.setCharacterStream(parameterName, reader);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        stat.setNCharacterStream(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        stat.setClob(parameterName, reader);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        stat.setBlob(parameterName, inputStream);
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        stat.setNClob(parameterName, reader);
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return stat.getObject(parameterIndex, type);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return stat.getObject(parameterName, type);
    }

    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength)
        throws SQLException {
        stat.setObject(parameterName, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        stat.setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        stat.registerOutParameter(parameterName, sqlType, typeName);
    }

}
