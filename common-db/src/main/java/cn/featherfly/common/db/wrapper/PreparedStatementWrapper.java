
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

    /** The prepared statement. */
    private PreparedStatement preparedStatement;

    /** The connection wrapper. */
    private ConnectionWrapper connectionWrapper;

    /**
     * Instantiates a new prepared statement wrapper.
     *
     * @param preparedStatement the prepared statement
     * @param connectionWrapper the connection wrapper
     */
    public PreparedStatementWrapper(PreparedStatement preparedStatement, ConnectionWrapper connectionWrapper) {
        this.preparedStatement = preparedStatement;
        this.connectionWrapper = connectionWrapper;
    }

    /**
     * Adds the batch.
     *
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
     * Clear parameters.
     *
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
     * Execute.
     *
     * @return true, if successful
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
     * Execute query.
     *
     * @return the result set wrapper
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
     * Execute update.
     *
     * @return the int
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
     * Gets the meta data.
     *
     * @return the meta data
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
     * Gets the parameter meta data.
     *
     * @return the parameter meta data
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
     * Sets the array.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the ascii stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the ascii stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param length         the length
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
     * Sets the ascii stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param length         the length
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
     * Sets the big decimal.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the binary stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the binary stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param length         the length
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
     * Sets the binary stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param length         the length
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
     * Sets the blob.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the blob.
     *
     * @param parameterIndex the parameter index
     * @param inputStream    the input stream
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
     * Sets the blob.
     *
     * @param parameterIndex the parameter index
     * @param inputStream    the input stream
     * @param length         the length
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
     * Sets the boolean.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the byte.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the bytes.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the character stream.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
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
     * Sets the character stream.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
     * @param length         the length
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
     * Sets the character stream.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
     * @param length         the length
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
     * Sets the clob.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the clob.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
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
     * Sets the clob.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
     * @param length         the length
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
     * Sets the date.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the date.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param cal            the cal
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
     * Sets the double.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the float.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the int.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the long.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the N character stream.
     *
     * @param parameterIndex the parameter index
     * @param value          the value
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
     * Sets the N character stream.
     *
     * @param parameterIndex the parameter index
     * @param value          the value
     * @param length         the length
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
     * Sets the N clob.
     *
     * @param parameterIndex the parameter index
     * @param value          the value
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
     * Sets the N clob.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
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
     * Sets the N clob.
     *
     * @param parameterIndex the parameter index
     * @param reader         the reader
     * @param length         the length
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
     * Sets the N string.
     *
     * @param parameterIndex the parameter index
     * @param value          the value
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
     * Sets the null.
     *
     * @param parameterIndex the parameter index
     * @param sqlType        the sql type
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
     * Sets the null.
     *
     * @param parameterIndex the parameter index
     * @param sqlType        the sql type
     * @param typeName       the type name
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
     * Sets the object.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the object.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param targetSqlType  the target sql type
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
     * Sets the object.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param targetSqlType  the target sql type
     * @param scaleOrLength  the scale or length
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
     * Sets the ref.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the row id.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the SQLXML.
     *
     * @param parameterIndex the parameter index
     * @param xmlObject      the xml object
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
     * Sets the short.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the string.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the time.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the time.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param cal            the cal
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
     * Sets the timestamp.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the timestamp.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param cal            the cal
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
     * Sets the URL.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
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
     * Sets the unicode stream.
     *
     * @param parameterIndex the parameter index
     * @param x              the x
     * @param length         the length
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
     * Adds the batch.
     *
     * @param sql the sql
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
     * Cancel.
     *
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
     * Clear batch.
     *
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
     * Clear warnings.
     *
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
     * Close.
     *
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
     * Execute.
     *
     * @param sql the sql
     * @return true, if successful
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
     * Execute.
     *
     * @param sql               the sql
     * @param autoGeneratedKeys the auto generated keys
     * @return true, if successful
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
     * Execute.
     *
     * @param sql           the sql
     * @param columnIndexes the column indexes
     * @return true, if successful
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
     * Execute.
     *
     * @param sql         the sql
     * @param columnNames the column names
     * @return true, if successful
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
     * Execute batch.
     *
     * @return the int[]
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
     * Execute query.
     *
     * @param sql the sql
     * @return the result set wrapper
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
     * Execute update.
     *
     * @param sql the sql
     * @return the int
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
     * Execute update.
     *
     * @param sql               the sql
     * @param autoGeneratedKeys the auto generated keys
     * @return the int
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
     * Execute update.
     *
     * @param sql           the sql
     * @param columnIndexes the column indexes
     * @return the int
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
     * Execute update.
     *
     * @param sql         the sql
     * @param columnNames the column names
     * @return the int
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
     * Gets the connection.
     *
     * @return the connection
     * @see java.sql.PreparedStatement
     */
    public ConnectionWrapper getConnection() {
        return connectionWrapper;
    }

    /**
     * Gets the fetch direction.
     *
     * @return the fetch direction
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
     * Gets the fetch size.
     *
     * @return the fetch size
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
     * Gets the generated keys.
     *
     * @return the generated keys
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
     * Gets the max field size.
     *
     * @return the max field size
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
     * Gets the max rows.
     *
     * @return the max rows
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
     * Gets the more results.
     *
     * @return the more results
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
     * Gets the more results.
     *
     * @param current the current
     * @return the more results
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
     * Gets the query timeout.
     *
     * @return the query timeout
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
     * Gets the result set.
     *
     * @return the result set
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
     * Gets the result set concurrency.
     *
     * @return the result set concurrency
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
     * Gets the result set holdability.
     *
     * @return the result set holdability
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
     * Gets the result set type.
     *
     * @return the result set type
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
     * Gets the update count.
     *
     * @return the update count
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
     * Gets the warnings.
     *
     * @return the warnings
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
     * Checks if is closed.
     *
     * @return true, if is closed
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
     * Checks if is poolable.
     *
     * @return true, if is poolable
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
     * Sets the cursor name.
     *
     * @param name the new cursor name
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
     * Sets the escape processing.
     *
     * @param enable the new escape processing
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
     * Sets the fetch direction.
     *
     * @param direction the new fetch direction
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
     * Sets the fetch size.
     *
     * @param rows the new fetch size
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
     * Sets the max field size.
     *
     * @param max the new max field size
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
     * Sets the max rows.
     *
     * @param max the new max rows
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
     * Sets the poolable.
     *
     * @param poolable the new poolable
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
     * Sets the query timeout.
     *
     * @param seconds the new query timeout
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
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for
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
     * Unwrap.
     *
     * @param <T>   the generic type
     * @param iface the iface
     * @return the t
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
     * Gets the prepared statement.
     *
     * @return 返回preparedStatement（java.sql.PreparedStatement）
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

}
