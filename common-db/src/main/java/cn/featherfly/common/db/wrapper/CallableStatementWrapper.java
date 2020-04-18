
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
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * java.sql.PreparedStatement的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class CallableStatementWrapper implements AutoCloseable {

    private CallableStatement callableStatement;

    private ConnectionWrapper connectionWrapper;

    /**
     * @param callableStatement callableStatement
     * @param connectionWrapper connectionWrapper
     */
    public CallableStatementWrapper(CallableStatement callableStatement, ConnectionWrapper connectionWrapper) {
        this.callableStatement = callableStatement;
        this.connectionWrapper = connectionWrapper;
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void addBatch() {
        try {
            callableStatement.addBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearParameters() {
        try {
            callableStatement.clearParameters();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute() {
        try {
            return callableStatement.execute();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSet executeQuery() {
        try {
            return callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate() {
        try {
            return callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetMetaData getMetaData() {
        try {
            return callableStatement.getMetaData();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ParameterMetaData getParameterMetaData() {
        try {
            return callableStatement.getParameterMetaData();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setArray(int parameterIndex, Array x) {
        try {
            callableStatement.setArray(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x) {
        try {
            callableStatement.setAsciiStream(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        try {
            callableStatement.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        try {
            callableStatement.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        try {
            callableStatement.setBigDecimal(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x) {
        try {
            callableStatement.setBinaryStream(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        try {
            callableStatement.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        try {
            callableStatement.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, Blob x) {
        try {
            callableStatement.setBlob(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, InputStream inputStream) {
        try {
            callableStatement.setBlob(parameterIndex, inputStream);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        try {
            callableStatement.setBlob(parameterIndex, inputStream, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBoolean(int parameterIndex, boolean x) {
        try {
            callableStatement.setBoolean(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setByte(int parameterIndex, byte x) {
        try {
            callableStatement.setByte(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBytes(int parameterIndex, byte[] x) {
        try {
            callableStatement.setBytes(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader) {
        try {
            callableStatement.setCharacterStream(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        try {
            callableStatement.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        try {
            callableStatement.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Clob x) {
        try {
            callableStatement.setClob(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Reader reader) {
        try {
            callableStatement.setClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Reader reader, long length) {
        try {
            callableStatement.setClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(int parameterIndex, Date x) {
        try {
            callableStatement.setDate(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        try {
            callableStatement.setDate(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDouble(int parameterIndex, double x) {
        try {
            callableStatement.setDouble(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFloat(int parameterIndex, float x) {
        try {
            callableStatement.setFloat(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setInt(int parameterIndex, int x) {
        try {
            callableStatement.setInt(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setLong(int parameterIndex, long x) {
        try {
            callableStatement.setLong(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(int parameterIndex, Reader value) {
        try {
            callableStatement.setNCharacterStream(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(int parameterIndex, Reader value, long length) {
        try {
            callableStatement.setNCharacterStream(parameterIndex, value, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, NClob value) {
        try {
            callableStatement.setNClob(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, Reader reader) {
        try {
            callableStatement.setNClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, Reader reader, long length) {
        try {
            callableStatement.setNClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNString(int parameterIndex, String value) {
        try {
            callableStatement.setNString(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(int parameterIndex, int sqlType) {
        try {
            callableStatement.setNull(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(int parameterIndex, int sqlType, String typeName) {
        try {
            callableStatement.setNull(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x) {
        try {
            callableStatement.setObject(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        try {
            callableStatement.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        try {
            callableStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setRef(int parameterIndex, Ref x) {
        try {
            callableStatement.setRef(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setRowId(int parameterIndex, RowId x) {
        try {
            callableStatement.setRowId(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
        try {
            callableStatement.setSQLXML(parameterIndex, xmlObject);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setShort(int parameterIndex, short x) {
        try {
            callableStatement.setShort(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setString(int parameterIndex, String x) {
        try {
            callableStatement.setString(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(int parameterIndex, Time x) {
        try {
            callableStatement.setTime(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        try {
            callableStatement.setTime(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(int parameterIndex, Timestamp x) {
        try {
            callableStatement.setTimestamp(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        try {
            callableStatement.setTimestamp(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setURL(int parameterIndex, URL x) {
        try {
            callableStatement.setURL(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     * @deprecated
     */
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) {
        try {
            callableStatement.setUnicodeStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void addBatch(String sql) {
        try {
            callableStatement.addBatch(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void cancel() {
        try {
            callableStatement.cancel();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearBatch() {
        try {
            callableStatement.clearBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearWarnings() {
        try {
            callableStatement.clearWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    @Override
    public void close() {
        try {
            callableStatement.close();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql) {
        try {
            return callableStatement.execute(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, int autoGeneratedKeys) {
        try {
            return callableStatement.execute(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, int[] columnIndexes) {
        try {
            return callableStatement.execute(sql, columnIndexes);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, String[] columnNames) {
        try {
            return callableStatement.execute(sql, columnNames);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int[] executeBatch() {
        try {
            return callableStatement.executeBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSet executeQuery(String sql) {
        try {
            return callableStatement.executeQuery(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql) {
        try {
            return callableStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, int autoGeneratedKeys) {
        try {
            return callableStatement.executeUpdate(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, int[] columnIndexes) {
        try {
            return callableStatement.executeUpdate(sql, columnIndexes);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, String[] columnNames) {
        try {
            return callableStatement.executeUpdate(sql, columnNames);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ConnectionWrapper getConnection() {
        return connectionWrapper;
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getFetchDirection() {
        try {
            return callableStatement.getFetchDirection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getFetchSize() {
        try {
            return callableStatement.getFetchSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSet getGeneratedKeys() {
        try {
            return callableStatement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getMaxFieldSize() {
        try {
            return callableStatement.getMaxFieldSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getMaxRows() {
        try {
            return callableStatement.getMaxRows();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getMoreResults() {
        try {
            return callableStatement.getMoreResults();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getMoreResults(int current) {
        try {
            return callableStatement.getMoreResults(current);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getQueryTimeout() {
        try {
            return callableStatement.getQueryTimeout();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSet getResultSet() {
        try {
            return callableStatement.getResultSet();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetConcurrency() {
        try {
            return callableStatement.getResultSetConcurrency();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetHoldability() {
        try {
            return callableStatement.getResultSetHoldability();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetType() {
        try {
            return callableStatement.getResultSetType();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getUpdateCount() {
        try {
            return callableStatement.getUpdateCount();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public SQLWarning getWarnings() {
        try {
            return callableStatement.getWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isClosed() {
        try {
            return callableStatement.isClosed();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isPoolable() {
        try {
            return callableStatement.isPoolable();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCursorName(String name) {
        try {
            callableStatement.setCursorName(name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setEscapeProcessing(boolean enable) {
        try {
            callableStatement.setEscapeProcessing(enable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFetchDirection(int direction) {
        try {
            callableStatement.setFetchDirection(direction);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFetchSize(int rows) {
        try {
            callableStatement.setFetchSize(rows);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setMaxFieldSize(int max) {
        try {
            callableStatement.setMaxFieldSize(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setMaxRows(int max) {
        try {
            callableStatement.setMaxRows(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setPoolable(boolean poolable) {
        try {
            callableStatement.setPoolable(poolable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setQueryTimeout(int seconds) {
        try {
            callableStatement.setQueryTimeout(seconds);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return callableStatement.isWrapperFor(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return callableStatement.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Array getArray(int parameterIndex) {
        try {
            return callableStatement.getArray(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Array getArray(String parameterName) {
        try {
            return callableStatement.getArray(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public BigDecimal getBigDecimal(int parameterIndex) {
        try {
            return callableStatement.getBigDecimal(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public BigDecimal getBigDecimal(String parameterName) {
        try {
            return callableStatement.getBigDecimal(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     * @deprecated
     */
    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) {
        try {
            return callableStatement.getBigDecimal(parameterIndex, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Blob getBlob(int parameterIndex) {
        try {
            return callableStatement.getBlob(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Blob getBlob(String parameterName) {
        try {
            return callableStatement.getBlob(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getBoolean(int parameterIndex) {
        try {
            return callableStatement.getBoolean(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getBoolean(String parameterName) {
        try {
            return callableStatement.getBoolean(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public byte getByte(int parameterIndex) {
        try {
            return callableStatement.getByte(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public byte getByte(String parameterName) {
        try {
            return callableStatement.getByte(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public byte[] getBytes(int parameterIndex) {
        try {
            return callableStatement.getBytes(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public byte[] getBytes(String parameterName) {
        try {
            return callableStatement.getBytes(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Reader getCharacterStream(int parameterIndex) {
        try {
            return callableStatement.getCharacterStream(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Reader getCharacterStream(String parameterName) {
        try {
            return callableStatement.getCharacterStream(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Clob getClob(int parameterIndex) {
        try {
            return callableStatement.getClob(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Clob getClob(String parameterName) {
        try {
            return callableStatement.getClob(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Date getDate(int parameterIndex) {
        try {
            return callableStatement.getDate(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Date getDate(String parameterName) {
        try {
            return callableStatement.getDate(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Date getDate(int parameterIndex, Calendar cal) {
        try {
            return callableStatement.getDate(parameterIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Date getDate(String parameterName, Calendar cal) {
        try {
            return callableStatement.getDate(parameterName, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public double getDouble(int parameterIndex) {
        try {
            return callableStatement.getDouble(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public double getDouble(String parameterName) {
        try {
            return callableStatement.getDouble(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public float getFloat(int parameterIndex) {
        try {
            return callableStatement.getFloat(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public float getFloat(String parameterName) {
        try {
            return callableStatement.getFloat(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getInt(int parameterIndex) {
        try {
            return callableStatement.getInt(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getInt(String parameterName) {
        try {
            return callableStatement.getInt(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public long getLong(int parameterIndex) {
        try {
            return callableStatement.getLong(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public long getLong(String parameterName) {
        try {
            return callableStatement.getLong(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Reader getNCharacterStream(int parameterIndex) {
        try {
            return callableStatement.getNCharacterStream(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Reader getNCharacterStream(String parameterName) {
        try {
            return callableStatement.getNCharacterStream(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public NClob getNClob(int parameterIndex) {
        try {
            return callableStatement.getNClob(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public NClob getNClob(String parameterName) {
        try {
            return callableStatement.getNClob(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public String getNString(int parameterIndex) {
        try {
            return callableStatement.getNString(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public String getNString(String parameterName) {
        try {
            return callableStatement.getNString(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Object getObject(int parameterIndex) {
        try {
            return callableStatement.getObject(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Object getObject(String parameterName) {
        try {
            return callableStatement.getObject(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) {
        try {
            return callableStatement.getObject(parameterIndex, map);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Object getObject(String parameterName, Map<String, Class<?>> map) {
        try {
            return callableStatement.getObject(parameterName, map);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Ref getRef(int parameterIndex) {
        try {
            return callableStatement.getRef(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Ref getRef(String parameterName) {
        try {
            return callableStatement.getRef(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public RowId getRowId(int parameterIndex) {
        try {
            return callableStatement.getRowId(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public RowId getRowId(String parameterName) {
        try {
            return callableStatement.getRowId(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public SQLXML getSQLXML(int parameterIndex) {
        try {
            return callableStatement.getSQLXML(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public SQLXML getSQLXML(String parameterName) {
        try {
            return callableStatement.getSQLXML(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public short getShort(int parameterIndex) {
        try {
            return callableStatement.getShort(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public short getShort(String parameterName) {
        try {
            return callableStatement.getShort(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public String getString(int parameterIndex) {
        try {
            return callableStatement.getString(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public String getString(String parameterName) {
        try {
            return callableStatement.getString(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Time getTime(int parameterIndex) {
        try {
            return callableStatement.getTime(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Time getTime(String parameterName) {
        try {
            return callableStatement.getTime(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Time getTime(int parameterIndex, Calendar cal) {
        try {
            return callableStatement.getTime(parameterIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Time getTime(String parameterName, Calendar cal) {
        try {
            return callableStatement.getTime(parameterName, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Timestamp getTimestamp(int parameterIndex) {
        try {
            return callableStatement.getTimestamp(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Timestamp getTimestamp(String parameterName) {
        try {
            return callableStatement.getTimestamp(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) {
        try {
            return callableStatement.getTimestamp(parameterIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public Timestamp getTimestamp(String parameterName, Calendar cal) {
        try {
            return callableStatement.getTimestamp(parameterName, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public URL getURL(int parameterIndex) {
        try {
            return callableStatement.getURL(parameterIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public URL getURL(String parameterName) {
        try {
            return callableStatement.getURL(parameterName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(int parameterIndex, int sqlType) {
        try {
            callableStatement.registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(String parameterName, int sqlType) {
        try {
            callableStatement.registerOutParameter(parameterName, sqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) {
        try {
            callableStatement.registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) {
        try {
            callableStatement.registerOutParameter(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(String parameterName, int sqlType, int scale) {
        try {
            callableStatement.registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void registerOutParameter(String parameterName, int sqlType, String typeName) {
        try {
            callableStatement.registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(String parameterName, InputStream x) {
        try {
            callableStatement.setAsciiStream(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(String parameterName, InputStream x, int length) {
        try {
            callableStatement.setAsciiStream(parameterName, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(String parameterName, InputStream x, long length) {
        try {
            callableStatement.setAsciiStream(parameterName, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBigDecimal(String parameterName, BigDecimal x) {
        try {
            callableStatement.setBigDecimal(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(String parameterName, InputStream x) {
        try {
            callableStatement.setBinaryStream(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(String parameterName, InputStream x, int length) {
        try {
            callableStatement.setBinaryStream(parameterName, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(String parameterName, InputStream x, long length) {
        try {
            callableStatement.setBinaryStream(parameterName, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(String parameterName, Blob x) {
        try {
            callableStatement.setBlob(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(String parameterName, InputStream inputStream) {
        try {
            callableStatement.setBlob(parameterName, inputStream);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(String parameterName, InputStream inputStream, long length) {
        try {
            callableStatement.setBlob(parameterName, inputStream, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBoolean(String parameterName, boolean x) {
        try {
            callableStatement.setBoolean(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setByte(String parameterName, byte x) {
        try {
            callableStatement.setByte(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBytes(String parameterName, byte[] x) {
        try {
            callableStatement.setBytes(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(String parameterName, Reader reader) {
        try {
            callableStatement.setCharacterStream(parameterName, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(String parameterName, Reader reader, int length) {
        try {
            callableStatement.setCharacterStream(parameterName, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(String parameterName, Reader reader, long length) {
        try {
            callableStatement.setCharacterStream(parameterName, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(String parameterName, Clob x) {
        try {
            callableStatement.setClob(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(String parameterName, Reader reader) {
        try {
            callableStatement.setClob(parameterName, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(String parameterName, Reader reader, long length) {
        try {
            callableStatement.setClob(parameterName, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(String parameterName, Date x) {
        try {
            callableStatement.setDate(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(String parameterName, Date x, Calendar cal) {
        try {
            callableStatement.setDate(parameterName, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDouble(String parameterName, double x) {
        try {
            callableStatement.setDouble(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFloat(String parameterName, float x) {
        try {
            callableStatement.setFloat(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setInt(String parameterName, int x) {
        try {
            callableStatement.setInt(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setLong(String parameterName, long x) {
        try {
            callableStatement.setLong(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(String parameterName, Reader value) {
        try {
            callableStatement.setNCharacterStream(parameterName, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(String parameterName, Reader value, long length) {
        try {
            callableStatement.setNCharacterStream(parameterName, value, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(String parameterName, NClob value) {
        try {
            callableStatement.setNClob(parameterName, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(String parameterName, Reader reader) {
        try {
            callableStatement.setNClob(parameterName, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(String parameterName, Reader reader, long length) {
        try {
            callableStatement.setNClob(parameterName, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNString(String parameterName, String value) {
        try {
            callableStatement.setNString(parameterName, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(String parameterName, int sqlType) {
        try {
            callableStatement.setNull(parameterName, sqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(String parameterName, int sqlType, String typeName) {
        try {
            callableStatement.setNull(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(String parameterName, Object x) {
        try {
            callableStatement.setObject(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(String parameterName, Object x, int targetSqlType) {
        try {
            callableStatement.setObject(parameterName, x, targetSqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) {
        try {
            callableStatement.setObject(parameterName, x, targetSqlType, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setRowId(String parameterName, RowId x) {
        try {
            callableStatement.setRowId(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setSQLXML(String parameterName, SQLXML xmlObject) {
        try {
            callableStatement.setSQLXML(parameterName, xmlObject);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setShort(String parameterName, short x) {
        try {
            callableStatement.setShort(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setString(String parameterName, String x) {
        try {
            callableStatement.setString(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(String parameterName, Time x) {
        try {
            callableStatement.setTime(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(String parameterName, Time x, Calendar cal) {
        try {
            callableStatement.setTime(parameterName, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(String parameterName, Timestamp x) {
        try {
            callableStatement.setTimestamp(parameterName, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) {
        try {
            callableStatement.setTimestamp(parameterName, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setURL(String parameterName, URL val) {
        try {
            callableStatement.setURL(parameterName, val);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean wasNull() {
        try {
            return callableStatement.wasNull();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @return 返回callableStatement（java.sql.CallableStatement）
     */
    public CallableStatement getCallableStatement() {
        return callableStatement;
    }
}
