
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
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * java.sql.PreparedStatement的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class PreparedStatementWrapper implements AutoCloseable {

    private PreparedStatement preparedStatement;

    private ConnectionWrapper connectionWrapper;

    public PreparedStatementWrapper(PreparedStatement preparedStatement, ConnectionWrapper connectionWrapper) {
        this.preparedStatement = preparedStatement;
        this.connectionWrapper = connectionWrapper;
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void addBatch() {
        try {
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearParameters() {
        try {
            preparedStatement.clearParameters();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute() {
        try {
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetWrapper executeQuery() {
        try {
            return new ResultSetWrapper(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate() {
        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetMetaData getMetaData() {
        try {
            return preparedStatement.getMetaData();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ParameterMetaData getParameterMetaData() {
        try {
            return preparedStatement.getParameterMetaData();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setArray(int parameterIndex, Array x) {
        try {
            preparedStatement.setArray(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x) {
        try {
            preparedStatement.setAsciiStream(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        try {
            preparedStatement.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        try {
            preparedStatement.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        try {
            preparedStatement.setBigDecimal(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x) {
        try {
            preparedStatement.setBinaryStream(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        try {
            preparedStatement.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        try {
            preparedStatement.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, Blob x) {
        try {
            preparedStatement.setBlob(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, InputStream inputStream) {
        try {
            preparedStatement.setBlob(parameterIndex, inputStream);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        try {
            preparedStatement.setBlob(parameterIndex, inputStream, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBoolean(int parameterIndex, boolean x) {
        try {
            preparedStatement.setBoolean(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setByte(int parameterIndex, byte x) {
        try {
            preparedStatement.setByte(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setBytes(int parameterIndex, byte[] x) {
        try {
            preparedStatement.setBytes(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader) {
        try {
            preparedStatement.setCharacterStream(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        try {
            preparedStatement.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        try {
            preparedStatement.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Clob x) {
        try {
            preparedStatement.setClob(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Reader reader) {
        try {
            preparedStatement.setClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setClob(int parameterIndex, Reader reader, long length) {
        try {
            preparedStatement.setClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(int parameterIndex, Date x) {
        try {
            preparedStatement.setDate(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        try {
            preparedStatement.setDate(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setDouble(int parameterIndex, double x) {
        try {
            preparedStatement.setDouble(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFloat(int parameterIndex, float x) {
        try {
            preparedStatement.setFloat(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setInt(int parameterIndex, int x) {
        try {
            preparedStatement.setInt(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setLong(int parameterIndex, long x) {
        try {
            preparedStatement.setLong(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(int parameterIndex, Reader value) {
        try {
            preparedStatement.setNCharacterStream(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNCharacterStream(int parameterIndex, Reader value, long length) {
        try {
            preparedStatement.setNCharacterStream(parameterIndex, value, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, NClob value) {
        try {
            preparedStatement.setNClob(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, Reader reader) {
        try {
            preparedStatement.setNClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNClob(int parameterIndex, Reader reader, long length) {
        try {
            preparedStatement.setNClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNString(int parameterIndex, String value) {
        try {
            preparedStatement.setNString(parameterIndex, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(int parameterIndex, int sqlType) {
        try {
            preparedStatement.setNull(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setNull(int parameterIndex, int sqlType, String typeName) {
        try {
            preparedStatement.setNull(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x) {
        try {
            preparedStatement.setObject(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        try {
            preparedStatement.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        try {
            preparedStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setRef(int parameterIndex, Ref x) {
        try {
            preparedStatement.setRef(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setRowId(int parameterIndex, RowId x) {
        try {
            preparedStatement.setRowId(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
        try {
            preparedStatement.setSQLXML(parameterIndex, xmlObject);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setShort(int parameterIndex, short x) {
        try {
            preparedStatement.setShort(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setString(int parameterIndex, String x) {
        try {
            preparedStatement.setString(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(int parameterIndex, Time x) {
        try {
            preparedStatement.setTime(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        try {
            preparedStatement.setTime(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(int parameterIndex, Timestamp x) {
        try {
            preparedStatement.setTimestamp(parameterIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        try {
            preparedStatement.setTimestamp(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setURL(int parameterIndex, URL x) {
        try {
            preparedStatement.setURL(parameterIndex, x);
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
            preparedStatement.setUnicodeStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void addBatch(String sql) {
        try {
            preparedStatement.addBatch(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void cancel() {
        try {
            preparedStatement.cancel();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearBatch() {
        try {
            preparedStatement.clearBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void clearWarnings() {
        try {
            preparedStatement.clearWarnings();
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
            preparedStatement.close();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql) {
        try {
            return preparedStatement.execute(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, int autoGeneratedKeys) {
        try {
            return preparedStatement.execute(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, int[] columnIndexes) {
        try {
            return preparedStatement.execute(sql, columnIndexes);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean execute(String sql, String[] columnNames) {
        try {
            return preparedStatement.execute(sql, columnNames);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int[] executeBatch() {
        try {
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetWrapper executeQuery(String sql) {
        try {
            return new ResultSetWrapper(preparedStatement.executeQuery(sql));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql) {
        try {
            return preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, int autoGeneratedKeys) {
        try {
            return preparedStatement.executeUpdate(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, int[] columnIndexes) {
        try {
            return preparedStatement.executeUpdate(sql, columnIndexes);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int executeUpdate(String sql, String[] columnNames) {
        try {
            return preparedStatement.executeUpdate(sql, columnNames);
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
            return preparedStatement.getFetchDirection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getFetchSize() {
        try {
            return preparedStatement.getFetchSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetWrapper getGeneratedKeys() {
        try {
            return new ResultSetWrapper(preparedStatement.getGeneratedKeys());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getMaxFieldSize() {
        try {
            return preparedStatement.getMaxFieldSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getMaxRows() {
        try {
            return preparedStatement.getMaxRows();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getMoreResults() {
        try {
            return preparedStatement.getMoreResults();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean getMoreResults(int current) {
        try {
            return preparedStatement.getMoreResults(current);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getQueryTimeout() {
        try {
            return preparedStatement.getQueryTimeout();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public ResultSetWrapper getResultSet() {
        try {
            return new ResultSetWrapper(preparedStatement.getResultSet());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetConcurrency() {
        try {
            return preparedStatement.getResultSetConcurrency();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetHoldability() {
        try {
            return preparedStatement.getResultSetHoldability();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getResultSetType() {
        try {
            return preparedStatement.getResultSetType();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public int getUpdateCount() {
        try {
            return preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public SQLWarning getWarnings() {
        try {
            return preparedStatement.getWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isClosed() {
        try {
            return preparedStatement.isClosed();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isPoolable() {
        try {
            return preparedStatement.isPoolable();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setCursorName(String name) {
        try {
            preparedStatement.setCursorName(name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setEscapeProcessing(boolean enable) {
        try {
            preparedStatement.setEscapeProcessing(enable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFetchDirection(int direction) {
        try {
            preparedStatement.setFetchDirection(direction);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setFetchSize(int rows) {
        try {
            preparedStatement.setFetchSize(rows);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setMaxFieldSize(int max) {
        try {
            preparedStatement.setMaxFieldSize(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setMaxRows(int max) {
        try {
            preparedStatement.setMaxRows(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setPoolable(boolean poolable) {
        try {
            preparedStatement.setPoolable(poolable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public void setQueryTimeout(int seconds) {
        try {
            preparedStatement.setQueryTimeout(seconds);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return preparedStatement.isWrapperFor(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @see java.sql.PreparedStatement
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return preparedStatement.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * @return 返回preparedStatement（java.sql.PreparedStatement）
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

}
