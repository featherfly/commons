
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
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * java.sql.ResultSet的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class ResultSetWrapper implements AutoCloseable {

    /** The result set. */
    private ResultSet resultSet;

    /**
     * Instantiates a new result set wrapper.
     *
     * @param res the res
     */
    public ResultSetWrapper(ResultSet res) {
        resultSet = res;
    }

    /**
     * Absolute.
     *
     * @param row the row
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean absolute(int row) {
        try {
            return resultSet.absolute(row);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * After last.
     *
     * @see java.sql.ResultSet
     */
    public void afterLast() {
        try {
            resultSet.afterLast();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Before first.
     *
     * @see java.sql.ResultSet
     */
    public void beforeFirst() {
        try {
            resultSet.beforeFirst();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Cancel row updates.
     *
     * @see java.sql.ResultSet
     */
    public void cancelRowUpdates() {
        try {
            resultSet.cancelRowUpdates();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Clear warnings.
     *
     * @see java.sql.ResultSet
     */
    public void clearWarnings() {
        try {
            resultSet.clearWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Close.
     *
     * @see java.sql.ResultSet
     */
    @Override
    public void close() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Delete row.
     *
     * @see java.sql.ResultSet
     */
    public void deleteRow() {
        try {
            resultSet.deleteRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Find column.
     *
     * @param columnLabel the column label
     * @return the int
     * @see java.sql.ResultSet
     */
    public int findColumn(String columnLabel) {
        try {
            return resultSet.findColumn(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * First.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean first() {
        try {
            return resultSet.first();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the array.
     *
     * @param columnIndex the column index
     * @return the array
     * @see java.sql.ResultSet
     */
    public Array getArray(int columnIndex) {
        try {
            return resultSet.getArray(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the array.
     *
     * @param columnLabel the column label
     * @return the array
     * @see java.sql.ResultSet
     */
    public Array getArray(String columnLabel) {
        try {
            return resultSet.getArray(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the ascii stream.
     *
     * @param columnIndex the column index
     * @return the ascii stream
     * @see java.sql.ResultSet
     */
    public InputStream getAsciiStream(int columnIndex) {
        try {
            return resultSet.getAsciiStream(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the ascii stream.
     *
     * @param columnLabel the column label
     * @return the ascii stream
     * @see java.sql.ResultSet
     */
    public InputStream getAsciiStream(String columnLabel) {
        try {
            return resultSet.getAsciiStream(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param columnIndex the column index
     * @return the big decimal
     * @see java.sql.ResultSet
     */
    public BigDecimal getBigDecimal(int columnIndex) {
        try {
            return resultSet.getBigDecimal(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param columnLabel the column label
     * @return the big decimal
     * @see java.sql.ResultSet
     */
    public BigDecimal getBigDecimal(String columnLabel) {
        try {
            return resultSet.getBigDecimal(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param columnIndex the column index
     * @param scale       the scale
     * @return the big decimal
     * @see java.sql.ResultSet
     * @deprecated
     */
    @Deprecated
    public BigDecimal getBigDecimal(int columnIndex, int scale) {
        try {
            return resultSet.getBigDecimal(columnIndex, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param columnLabel the column label
     * @param scale       the scale
     * @return the big decimal
     * @see java.sql.ResultSet
     * @deprecated
     */
    @Deprecated
    public BigDecimal getBigDecimal(String columnLabel, int scale) {
        try {
            return resultSet.getBigDecimal(columnLabel, scale);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the binary stream.
     *
     * @param columnIndex the column index
     * @return the binary stream
     * @see java.sql.ResultSet
     */
    public InputStream getBinaryStream(int columnIndex) {
        try {
            return resultSet.getBinaryStream(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the binary stream.
     *
     * @param columnLabel the column label
     * @return the binary stream
     * @see java.sql.ResultSet
     */
    public InputStream getBinaryStream(String columnLabel) {
        try {
            return resultSet.getBinaryStream(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the blob.
     *
     * @param columnIndex the column index
     * @return the blob
     * @see java.sql.ResultSet
     */
    public Blob getBlob(int columnIndex) {
        try {
            return resultSet.getBlob(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the blob.
     *
     * @param columnLabel the column label
     * @return the blob
     * @see java.sql.ResultSet
     */
    public Blob getBlob(String columnLabel) {
        try {
            return resultSet.getBlob(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the boolean.
     *
     * @param columnIndex the column index
     * @return the boolean
     * @see java.sql.ResultSet
     */
    public boolean getBoolean(int columnIndex) {
        try {
            return resultSet.getBoolean(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the boolean.
     *
     * @param columnLabel the column label
     * @return the boolean
     * @see java.sql.ResultSet
     */
    public boolean getBoolean(String columnLabel) {
        try {
            return resultSet.getBoolean(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte.
     *
     * @param columnIndex the column index
     * @return the byte
     * @see java.sql.ResultSet
     */
    public byte getByte(int columnIndex) {
        try {
            return resultSet.getByte(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte.
     *
     * @param columnLabel the column label
     * @return the byte
     * @see java.sql.ResultSet
     */
    public byte getByte(String columnLabel) {
        try {
            return resultSet.getByte(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the bytes.
     *
     * @param columnIndex the column index
     * @return the bytes
     * @see java.sql.ResultSet
     */
    public byte[] getBytes(int columnIndex) {
        try {
            return resultSet.getBytes(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the bytes.
     *
     * @param columnLabel the column label
     * @return the bytes
     * @see java.sql.ResultSet
     */
    public byte[] getBytes(String columnLabel) {
        try {
            return resultSet.getBytes(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the character stream.
     *
     * @param columnIndex the column index
     * @return the character stream
     * @see java.sql.ResultSet
     */
    public Reader getCharacterStream(int columnIndex) {
        try {
            return resultSet.getCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the character stream.
     *
     * @param columnLabel the column label
     * @return the character stream
     * @see java.sql.ResultSet
     */
    public Reader getCharacterStream(String columnLabel) {
        try {
            return resultSet.getCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the clob.
     *
     * @param columnIndex the column index
     * @return the clob
     * @see java.sql.ResultSet
     */
    public Clob getClob(int columnIndex) {
        try {
            return resultSet.getClob(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the clob.
     *
     * @param columnLabel the column label
     * @return the clob
     * @see java.sql.ResultSet
     */
    public Clob getClob(String columnLabel) {
        try {
            return resultSet.getClob(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the concurrency.
     *
     * @return the concurrency
     * @see java.sql.ResultSet
     */
    public int getConcurrency() {
        try {
            return resultSet.getConcurrency();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the cursor name.
     *
     * @return the cursor name
     * @see java.sql.ResultSet
     */
    public String getCursorName() {
        try {
            return resultSet.getCursorName();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the date.
     *
     * @param columnIndex the column index
     * @return the date
     * @see java.sql.ResultSet
     */
    public Date getDate(int columnIndex) {
        try {
            return resultSet.getDate(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the date.
     *
     * @param columnLabel the column label
     * @return the date
     * @see java.sql.ResultSet
     */
    public Date getDate(String columnLabel) {
        try {
            return resultSet.getDate(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the date.
     *
     * @param columnIndex the column index
     * @param cal         the cal
     * @return the date
     * @see java.sql.ResultSet
     */
    public Date getDate(int columnIndex, Calendar cal) {
        try {
            return resultSet.getDate(columnIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the date.
     *
     * @param columnLabel the column label
     * @param cal         the cal
     * @return the date
     * @see java.sql.ResultSet
     */
    public Date getDate(String columnLabel, Calendar cal) {
        try {
            return resultSet.getDate(columnLabel, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double.
     *
     * @param columnIndex the column index
     * @return the double
     * @see java.sql.ResultSet
     */
    public double getDouble(int columnIndex) {
        try {
            return resultSet.getDouble(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double.
     *
     * @param columnLabel the column label
     * @return the double
     * @see java.sql.ResultSet
     */
    public double getDouble(String columnLabel) {
        try {
            return resultSet.getDouble(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the fetch direction.
     *
     * @return the fetch direction
     * @see java.sql.ResultSet
     */
    public int getFetchDirection() {
        try {
            return resultSet.getFetchDirection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the fetch size.
     *
     * @return the fetch size
     * @see java.sql.ResultSet
     */
    public int getFetchSize() {
        try {
            return resultSet.getFetchSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float.
     *
     * @param columnIndex the column index
     * @return the float
     * @see java.sql.ResultSet
     */
    public float getFloat(int columnIndex) {
        try {
            return resultSet.getFloat(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float.
     *
     * @param columnLabel the column label
     * @return the float
     * @see java.sql.ResultSet
     */
    public float getFloat(String columnLabel) {
        try {
            return resultSet.getFloat(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the holdability.
     *
     * @return the holdability
     * @see java.sql.ResultSet
     */
    public int getHoldability() {
        try {
            return resultSet.getHoldability();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the int.
     *
     * @param columnIndex the column index
     * @return the int
     * @see java.sql.ResultSet
     */
    public int getInt(int columnIndex) {
        try {
            return resultSet.getInt(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the int.
     *
     * @param columnLabel the column label
     * @return the int
     * @see java.sql.ResultSet
     */
    public int getInt(String columnLabel) {
        try {
            return resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long.
     *
     * @param columnIndex the column index
     * @return the long
     * @see java.sql.ResultSet
     */
    public long getLong(int columnIndex) {
        try {
            return resultSet.getLong(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long.
     *
     * @param columnLabel the column label
     * @return the long
     * @see java.sql.ResultSet
     */
    public long getLong(String columnLabel) {
        try {
            return resultSet.getLong(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the meta data.
     *
     * @return the meta data
     * @see java.sql.ResultSet
     */
    public ResultSetMetaDataWrapper getMetaData() {
        try {
            return new ResultSetMetaDataWrapper(resultSet.getMetaData());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n character stream.
     *
     * @param columnIndex the column index
     * @return the n character stream
     * @see java.sql.ResultSet
     */
    public Reader getNCharacterStream(int columnIndex) {
        try {
            return resultSet.getNCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n character stream.
     *
     * @param columnLabel the column label
     * @return the n character stream
     * @see java.sql.ResultSet
     */
    public Reader getNCharacterStream(String columnLabel) {
        try {
            return resultSet.getNCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n clob.
     *
     * @param columnIndex the column index
     * @return the n clob
     * @see java.sql.ResultSet
     */
    public NClob getNClob(int columnIndex) {
        try {
            return resultSet.getNClob(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n clob.
     *
     * @param columnLabel the column label
     * @return the n clob
     * @see java.sql.ResultSet
     */
    public NClob getNClob(String columnLabel) {
        try {
            return resultSet.getNClob(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n string.
     *
     * @param columnIndex the column index
     * @return the n string
     * @see java.sql.ResultSet
     */
    public String getNString(int columnIndex) {
        try {
            return resultSet.getNString(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the n string.
     *
     * @param columnLabel the column label
     * @return the n string
     * @see java.sql.ResultSet
     */
    public String getNString(String columnLabel) {
        try {
            return resultSet.getNString(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the object.
     *
     * @param columnIndex the column index
     * @return the object
     * @see java.sql.ResultSet
     */
    public Object getObject(int columnIndex) {
        try {
            return resultSet.getObject(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the object.
     *
     * @param columnLabel the column label
     * @return the object
     * @see java.sql.ResultSet
     */
    public Object getObject(String columnLabel) {
        try {
            return resultSet.getObject(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the object.
     *
     * @param columnIndex the column index
     * @param map         the map
     * @return the object
     * @see java.sql.ResultSet
     */
    public Object getObject(int columnIndex, Map<String, Class<?>> map) {
        try {
            return resultSet.getObject(columnIndex, map);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the object.
     *
     * @param columnLabel the column label
     * @param map         the map
     * @return the object
     * @see java.sql.ResultSet
     */
    public Object getObject(String columnLabel, Map<String, Class<?>> map) {
        try {
            return resultSet.getObject(columnLabel, map);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the ref.
     *
     * @param columnIndex the column index
     * @return the ref
     * @see java.sql.ResultSet
     */
    public Ref getRef(int columnIndex) {
        try {
            return resultSet.getRef(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the ref.
     *
     * @param columnLabel the column label
     * @return the ref
     * @see java.sql.ResultSet
     */
    public Ref getRef(String columnLabel) {
        try {
            return resultSet.getRef(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the row.
     *
     * @return the row
     * @see java.sql.ResultSet
     */
    public int getRow() {
        try {
            return resultSet.getRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the row id.
     *
     * @param columnIndex the column index
     * @return the row id
     * @see java.sql.ResultSet
     */
    public RowId getRowId(int columnIndex) {
        try {
            return resultSet.getRowId(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the row id.
     *
     * @param columnLabel the column label
     * @return the row id
     * @see java.sql.ResultSet
     */
    public RowId getRowId(String columnLabel) {
        try {
            return resultSet.getRowId(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the sqlxml.
     *
     * @param columnIndex the column index
     * @return the sqlxml
     * @see java.sql.ResultSet
     */
    public SQLXML getSQLXML(int columnIndex) {
        try {
            return resultSet.getSQLXML(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the sqlxml.
     *
     * @param columnLabel the column label
     * @return the sqlxml
     * @see java.sql.ResultSet
     */
    public SQLXML getSQLXML(String columnLabel) {
        try {
            return resultSet.getSQLXML(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short.
     *
     * @param columnIndex the column index
     * @return the short
     * @see java.sql.ResultSet
     */
    public short getShort(int columnIndex) {
        try {
            return resultSet.getShort(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short.
     *
     * @param columnLabel the column label
     * @return the short
     * @see java.sql.ResultSet
     */
    public short getShort(String columnLabel) {
        try {
            return resultSet.getShort(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the statement.
     *
     * @return the statement
     * @see java.sql.ResultSet
     */
    public Statement getStatement() {
        try {
            return resultSet.getStatement();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the string.
     *
     * @param columnIndex the column index
     * @return the string
     * @see java.sql.ResultSet
     */
    public String getString(int columnIndex) {
        try {
            return resultSet.getString(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the string.
     *
     * @param columnLabel the column label
     * @return the string
     * @see java.sql.ResultSet
     */
    public String getString(String columnLabel) {
        try {
            return resultSet.getString(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param columnIndex the column index
     * @return the time
     * @see java.sql.ResultSet
     */
    public Time getTime(int columnIndex) {
        try {
            return resultSet.getTime(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param columnLabel the column label
     * @return the time
     * @see java.sql.ResultSet
     */
    public Time getTime(String columnLabel) {
        try {
            return resultSet.getTime(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param columnIndex the column index
     * @param cal         the cal
     * @return the time
     * @see java.sql.ResultSet
     */
    public Time getTime(int columnIndex, Calendar cal) {
        try {
            return resultSet.getTime(columnIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param columnLabel the column label
     * @param cal         the cal
     * @return the time
     * @see java.sql.ResultSet
     */
    public Time getTime(String columnLabel, Calendar cal) {
        try {
            return resultSet.getTime(columnLabel, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param columnIndex the column index
     * @return the timestamp
     * @see java.sql.ResultSet
     */
    public Timestamp getTimestamp(int columnIndex) {
        try {
            return resultSet.getTimestamp(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param columnLabel the column label
     * @return the timestamp
     * @see java.sql.ResultSet
     */
    public Timestamp getTimestamp(String columnLabel) {
        try {
            return resultSet.getTimestamp(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param columnIndex the column index
     * @param cal         the cal
     * @return the timestamp
     * @see java.sql.ResultSet
     */
    public Timestamp getTimestamp(int columnIndex, Calendar cal) {
        try {
            return resultSet.getTimestamp(columnIndex, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param columnLabel the column label
     * @param cal         the cal
     * @return the timestamp
     * @see java.sql.ResultSet
     */
    public Timestamp getTimestamp(String columnLabel, Calendar cal) {
        try {
            return resultSet.getTimestamp(columnLabel, cal);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the type.
     *
     * @return the type
     * @see java.sql.ResultSet
     */
    public int getType() {
        try {
            return resultSet.getType();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the url.
     *
     * @param columnIndex the column index
     * @return the url
     * @see java.sql.ResultSet
     */
    public URL getURL(int columnIndex) {
        try {
            return resultSet.getURL(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the url.
     *
     * @param columnLabel the column label
     * @return the url
     * @see java.sql.ResultSet
     */
    public URL getURL(String columnLabel) {
        try {
            return resultSet.getURL(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the unicode stream.
     *
     * @param columnIndex the column index
     * @return the unicode stream
     * @see java.sql.ResultSet
     * @deprecated
     */
    @Deprecated
    public InputStream getUnicodeStream(int columnIndex) {
        try {
            return resultSet.getUnicodeStream(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the unicode stream.
     *
     * @param columnLabel the column label
     * @return the unicode stream
     * @see java.sql.ResultSet
     * @deprecated
     */
    @Deprecated
    public InputStream getUnicodeStream(String columnLabel) {
        try {
            return resultSet.getUnicodeStream(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the warnings.
     *
     * @return the warnings
     * @see java.sql.ResultSet
     */
    public SQLWarning getWarnings() {
        try {
            return resultSet.getWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Insert row.
     *
     * @see java.sql.ResultSet
     */
    public void insertRow() {
        try {
            resultSet.insertRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is after last.
     *
     * @return true, if is after last
     * @see java.sql.ResultSet
     */
    public boolean isAfterLast() {
        try {
            return resultSet.isAfterLast();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is before first.
     *
     * @return true, if is before first
     * @see java.sql.ResultSet
     */
    public boolean isBeforeFirst() {
        try {
            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is closed.
     *
     * @return true, if is closed
     * @see java.sql.ResultSet
     */
    public boolean isClosed() {
        try {
            return resultSet.isClosed();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is first.
     *
     * @return true, if is first
     * @see java.sql.ResultSet
     */
    public boolean isFirst() {
        try {
            return resultSet.isFirst();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is last.
     *
     * @return true, if is last
     * @see java.sql.ResultSet
     */
    public boolean isLast() {
        try {
            return resultSet.isLast();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Last.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean last() {
        try {
            return resultSet.last();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Move to current row.
     *
     * @see java.sql.ResultSet
     */
    public void moveToCurrentRow() {
        try {
            resultSet.moveToCurrentRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Move to insert row.
     *
     * @see java.sql.ResultSet
     */
    public void moveToInsertRow() {
        try {
            resultSet.moveToInsertRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Next.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean next() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Previous.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean previous() {
        try {
            return resultSet.previous();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Refresh row.
     *
     * @see java.sql.ResultSet
     */
    public void refreshRow() {
        try {
            resultSet.refreshRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Relative.
     *
     * @param rows the rows
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean relative(int rows) {
        try {
            return resultSet.relative(rows);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Row deleted.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean rowDeleted() {
        try {
            return resultSet.rowDeleted();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Row inserted.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean rowInserted() {
        try {
            return resultSet.rowInserted();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Row updated.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean rowUpdated() {
        try {
            return resultSet.rowUpdated();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the fetch direction.
     *
     * @param direction the new fetch direction
     * @see java.sql.ResultSet
     */
    public void setFetchDirection(int direction) {
        try {
            resultSet.setFetchDirection(direction);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the fetch size.
     *
     * @param rows the new fetch size
     * @see java.sql.ResultSet
     */
    public void setFetchSize(int rows) {
        try {
            resultSet.setFetchSize(rows);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update array.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateArray(int columnIndex, Array x) {
        try {
            resultSet.updateArray(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update array.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateArray(String columnLabel, Array x) {
        try {
            resultSet.updateArray(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(int columnIndex, InputStream x) {
        try {
            resultSet.updateAsciiStream(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(String columnLabel, InputStream x) {
        try {
            resultSet.updateAsciiStream(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(int columnIndex, InputStream x, int length) {
        try {
            resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(String columnLabel, InputStream x, int length) {
        try {
            resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(int columnIndex, InputStream x, long length) {
        try {
            resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ascii stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateAsciiStream(String columnLabel, InputStream x, long length) {
        try {
            resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update big decimal.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBigDecimal(int columnIndex, BigDecimal x) {
        try {
            resultSet.updateBigDecimal(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update big decimal.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBigDecimal(String columnLabel, BigDecimal x) {
        try {
            resultSet.updateBigDecimal(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(int columnIndex, InputStream x) {
        try {
            resultSet.updateBinaryStream(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(String columnLabel, InputStream x) {
        try {
            resultSet.updateBinaryStream(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(int columnIndex, InputStream x, int length) {
        try {
            resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(String columnLabel, InputStream x, int length) {
        try {
            resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(int columnIndex, InputStream x, long length) {
        try {
            resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update binary stream.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBinaryStream(String columnLabel, InputStream x, long length) {
        try {
            resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBlob(int columnIndex, Blob x) {
        try {
            resultSet.updateBlob(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBlob(String columnLabel, Blob x) {
        try {
            resultSet.updateBlob(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnIndex the column index
     * @param inputStream the input stream
     * @see java.sql.ResultSet
     */
    public void updateBlob(int columnIndex, InputStream inputStream) {
        try {
            resultSet.updateBlob(columnIndex, inputStream);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnLabel the column label
     * @param inputStream the input stream
     * @see java.sql.ResultSet
     */
    public void updateBlob(String columnLabel, InputStream inputStream) {
        try {
            resultSet.updateBlob(columnLabel, inputStream);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnIndex the column index
     * @param inputStream the input stream
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBlob(int columnIndex, InputStream inputStream, long length) {
        try {
            resultSet.updateBlob(columnIndex, inputStream, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update blob.
     *
     * @param columnLabel the column label
     * @param inputStream the input stream
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateBlob(String columnLabel, InputStream inputStream, long length) {
        try {
            resultSet.updateBlob(columnLabel, inputStream, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update boolean.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBoolean(int columnIndex, boolean x) {
        try {
            resultSet.updateBoolean(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update boolean.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBoolean(String columnLabel, boolean x) {
        try {
            resultSet.updateBoolean(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update byte.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateByte(int columnIndex, byte x) {
        try {
            resultSet.updateByte(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update byte.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateByte(String columnLabel, byte x) {
        try {
            resultSet.updateByte(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update bytes.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBytes(int columnIndex, byte[] x) {
        try {
            resultSet.updateBytes(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update bytes.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateBytes(String columnLabel, byte[] x) {
        try {
            resultSet.updateBytes(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(int columnIndex, Reader x) {
        try {
            resultSet.updateCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(String columnLabel, Reader reader) {
        try {
            resultSet.updateCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(int columnIndex, Reader x, int length) {
        try {
            resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(String columnLabel, Reader reader, int length) {
        try {
            resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(int columnIndex, Reader x, long length) {
        try {
            resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update character stream.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateCharacterStream(String columnLabel, Reader reader, long length) {
        try {
            resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateClob(int columnIndex, Clob x) {
        try {
            resultSet.updateClob(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateClob(String columnLabel, Clob x) {
        try {
            resultSet.updateClob(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnIndex the column index
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateClob(int columnIndex, Reader reader) {
        try {
            resultSet.updateClob(columnIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateClob(String columnLabel, Reader reader) {
        try {
            resultSet.updateClob(columnLabel, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnIndex the column index
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateClob(int columnIndex, Reader reader, long length) {
        try {
            resultSet.updateClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update clob.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateClob(String columnLabel, Reader reader, long length) {
        try {
            resultSet.updateClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update date.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateDate(int columnIndex, Date x) {
        try {
            resultSet.updateDate(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update date.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateDate(String columnLabel, Date x) {
        try {
            resultSet.updateDate(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update double.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateDouble(int columnIndex, double x) {
        try {
            resultSet.updateDouble(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update double.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateDouble(String columnLabel, double x) {
        try {
            resultSet.updateDouble(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update float.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateFloat(int columnIndex, float x) {
        try {
            resultSet.updateFloat(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update float.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateFloat(String columnLabel, float x) {
        try {
            resultSet.updateFloat(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update int.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateInt(int columnIndex, int x) {
        try {
            resultSet.updateInt(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update int.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateInt(String columnLabel, int x) {
        try {
            resultSet.updateInt(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update long.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateLong(int columnIndex, long x) {
        try {
            resultSet.updateLong(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update long.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateLong(String columnLabel, long x) {
        try {
            resultSet.updateLong(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N character stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateNCharacterStream(int columnIndex, Reader x) {
        try {
            resultSet.updateNCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N character stream.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateNCharacterStream(String columnLabel, Reader reader) {
        try {
            resultSet.updateNCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N character stream.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateNCharacterStream(int columnIndex, Reader x, long length) {
        try {
            resultSet.updateNCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N character stream.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) {
        try {
            resultSet.updateNCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnIndex the column index
     * @param nClob       the n clob
     * @see java.sql.ResultSet
     */
    public void updateNClob(int columnIndex, NClob nClob) {
        try {
            resultSet.updateNClob(columnIndex, nClob);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnLabel the column label
     * @param nClob       the n clob
     * @see java.sql.ResultSet
     */
    public void updateNClob(String columnLabel, NClob nClob) {
        try {
            resultSet.updateNClob(columnLabel, nClob);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnIndex the column index
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateNClob(int columnIndex, Reader reader) {
        try {
            resultSet.updateNClob(columnIndex, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @see java.sql.ResultSet
     */
    public void updateNClob(String columnLabel, Reader reader) {
        try {
            resultSet.updateNClob(columnLabel, reader);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnIndex the column index
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateNClob(int columnIndex, Reader reader, long length) {
        try {
            resultSet.updateNClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N clob.
     *
     * @param columnLabel the column label
     * @param reader      the reader
     * @param length      the length
     * @see java.sql.ResultSet
     */
    public void updateNClob(String columnLabel, Reader reader, long length) {
        try {
            resultSet.updateNClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N string.
     *
     * @param columnIndex the column index
     * @param nString     the n string
     * @see java.sql.ResultSet
     */
    public void updateNString(int columnIndex, String nString) {
        try {
            resultSet.updateNString(columnIndex, nString);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update N string.
     *
     * @param columnLabel the column label
     * @param nString     the n string
     * @see java.sql.ResultSet
     */
    public void updateNString(String columnLabel, String nString) {
        try {
            resultSet.updateNString(columnLabel, nString);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update null.
     *
     * @param columnIndex the column index
     * @see java.sql.ResultSet
     */
    public void updateNull(int columnIndex) {
        try {
            resultSet.updateNull(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update null.
     *
     * @param columnLabel the column label
     * @see java.sql.ResultSet
     */
    public void updateNull(String columnLabel) {
        try {
            resultSet.updateNull(columnLabel);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update object.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateObject(int columnIndex, Object x) {
        try {
            resultSet.updateObject(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update object.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateObject(String columnLabel, Object x) {
        try {
            resultSet.updateObject(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update object.
     *
     * @param columnIndex   the column index
     * @param x             the x
     * @param scaleOrLength the scale or length
     * @see java.sql.ResultSet
     */
    public void updateObject(int columnIndex, Object x, int scaleOrLength) {
        try {
            resultSet.updateObject(columnIndex, x, scaleOrLength);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update object.
     *
     * @param columnLabel   the column label
     * @param x             the x
     * @param scaleOrLength the scale or length
     * @see java.sql.ResultSet
     */
    public void updateObject(String columnLabel, Object x, int scaleOrLength) {
        try {
            resultSet.updateObject(columnLabel, x, scaleOrLength);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ref.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateRef(int columnIndex, Ref x) {
        try {
            resultSet.updateRef(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update ref.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateRef(String columnLabel, Ref x) {
        try {
            resultSet.updateRef(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update row.
     *
     * @see java.sql.ResultSet
     */
    public void updateRow() {
        try {
            resultSet.updateRow();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update row id.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateRowId(int columnIndex, RowId x) {
        try {
            resultSet.updateRowId(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update row id.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateRowId(String columnLabel, RowId x) {
        try {
            resultSet.updateRowId(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update SQLXML.
     *
     * @param columnIndex the column index
     * @param xmlObject   the xml object
     * @see java.sql.ResultSet
     */
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
        try {
            resultSet.updateSQLXML(columnIndex, xmlObject);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update SQLXML.
     *
     * @param columnLabel the column label
     * @param xmlObject   the xml object
     * @see java.sql.ResultSet
     */
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) {
        try {
            resultSet.updateSQLXML(columnLabel, xmlObject);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update short.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateShort(int columnIndex, short x) {
        try {
            resultSet.updateShort(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update short.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateShort(String columnLabel, short x) {
        try {
            resultSet.updateShort(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update string.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateString(int columnIndex, String x) {
        try {
            resultSet.updateString(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update string.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateString(String columnLabel, String x) {
        try {
            resultSet.updateString(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update time.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateTime(int columnIndex, Time x) {
        try {
            resultSet.updateTime(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update time.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateTime(String columnLabel, Time x) {
        try {
            resultSet.updateTime(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update timestamp.
     *
     * @param columnIndex the column index
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateTimestamp(int columnIndex, Timestamp x) {
        try {
            resultSet.updateTimestamp(columnIndex, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Update timestamp.
     *
     * @param columnLabel the column label
     * @param x           the x
     * @see java.sql.ResultSet
     */
    public void updateTimestamp(String columnLabel, Timestamp x) {
        try {
            resultSet.updateTimestamp(columnLabel, x);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Was null.
     *
     * @return true, if successful
     * @see java.sql.ResultSet
     */
    public boolean wasNull() {
        try {
            return resultSet.wasNull();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for
     * @see java.sql.ResultSet
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return resultSet.isWrapperFor(iface);
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
     * @see java.sql.ResultSet
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return resultSet.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set.
     *
     * @return 返回resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }
}
