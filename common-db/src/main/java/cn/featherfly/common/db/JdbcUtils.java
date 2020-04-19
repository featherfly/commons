package cn.featherfly.common.db;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.db.mapping.SqlResultSet;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.DataSourceWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.db.wrapper.ResultSetWrapper;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.repository.mapping.RowMapper;

/**
 * <p>
 * JDBC操作的工具类
 * </p>
 *
 * @author zhongj
 */
public final class JdbcUtils {

    private JdbcUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtils.class);

    /**
     * 关闭<code>Connection</code>, 忽略null的情况.
     *
     * @param conn 需要关闭的连接.
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 关闭<code>ResultSet</code>, 忽略null的情况.
     *
     * @param rs 需要关闭的结果集.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 关闭<code>Statement</code>, 忽略null的情况.
     *
     * @param stmt 需要关闭的Statement.
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 关闭<code>Connection</code>, <code>Statement</code> 和
     * <code>ResultSet</code>. 忽略null的情况以及隐藏掉所有发生的SQL异常.
     *
     * @param conn Connection.
     * @param stmt Statement.
     * @param rs   ResultSet.
     */
    public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
        try {
            closeQuietly(rs);
        } finally {
            try {
                closeQuietly(stmt);
            } finally {
                closeQuietly(conn);
            }
        }

    }

    /**
     * 关闭<code>Connection</code>, 忽略null的情况以及隐藏掉所有发生的SQL异常.
     *
     * @param conn 要关闭的数据库连接.
     */
    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (Exception e) {
            LogUtils.debug(e, LOGGER);
        }
    }

    /**
     * 关闭<code>ResultSet</code>, 忽略null的情况以及隐藏掉所有发生的SQL异常.
     *
     * @param rs ResultSet.
     */
    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (Exception e) {
            LogUtils.debug(e, LOGGER);
        }
    }

    /**
     * 关闭<code>Statement</code>, 忽略null的情况以及隐藏掉所有发生的SQL异常.
     *
     * @param stmt Statement.
     */
    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (Exception e) {
            LogUtils.debug(e, LOGGER);
        }
    }

    /**
     * 提交<code>Connection</code>以后关闭, 忽略null的情况.
     *
     * @param conn Connection
     */
    public static void commitAndClose(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new JdbcException(e);
            } finally {
                close(conn);
            }
        }
    }

    /**
     * 提交<code>Connection</code>以后关闭, 忽略null的情况以及隐藏掉所有发生的SQL异常.
     *
     * @param conn Connection to close.
     */
    public static void commitAndCloseQuietly(Connection conn) {
        try {
            commitAndClose(conn);
        } catch (JdbcException e) {
            LogUtils.debug(e, LOGGER);
        }
    }

    /**
     * Loads and registers a database driver class. If this succeeds, it returns
     * true, else it returns false.
     *
     * @param driverClassName of driver to load
     * @return boolean <code>true</code> if the driver was found, otherwise
     *         <code>false</code>
     */
    public static boolean loadDriver(String driverClassName) {
        boolean result = false;
        try {
            Class.forName(driverClassName).newInstance();
            result = true;

        } catch (ClassNotFoundException e) {
            result = false;

        } catch (IllegalAccessException e) {
            // Constructor is private, OK for DriverManager contract
            result = true;

        } catch (InstantiationException e) {
            result = false;

        } catch (Throwable e) {
            result = false;
        }
        return result;
    }

    /**
     * Print the stack trace for a SQLException to STDERR.
     *
     * @param e SQLException to print stack trace of
     */
    public static void printStackTrace(SQLException e) {
        printStackTrace(e, new PrintWriter(System.err));
    }

    /**
     * Print the stack trace for a SQLException to a specified PrintWriter.
     *
     * @param e  SQLException to print stack trace of
     * @param pw PrintWriter to print to
     */
    public static void printStackTrace(SQLException e, PrintWriter pw) {
        SQLException next = e;
        while (next != null) {
            next.printStackTrace(pw);
            next = next.getNextException();
            if (next != null) {
                pw.println("Next SQLException:");
            }
        }
    }

    /**
     * Print warnings on a Connection to STDERR.
     *
     * @param conn Connection to print warnings from
     */
    public static void printWarnings(Connection conn) {
        printWarnings(conn, new PrintWriter(System.err));
    }

    /**
     * Print warnings on a Connection to a specified PrintWriter.
     *
     * @param conn Connection to print warnings from
     * @param pw   PrintWriter to print to
     */
    public static void printWarnings(Connection conn, PrintWriter pw) {
        if (conn != null) {
            try {
                printStackTrace(conn.getWarnings(), pw);
            } catch (SQLException e) {
                printStackTrace(e, pw);
            }
        }
    }

    /**
     * Rollback any changes made on the given connection.
     *
     * @param conn Connection to rollback. A null value is legal.
     * @throws SQLException if a database access error occurs
     */
    public static void rollback(Connection conn) throws SQLException {
        if (conn != null) {
            conn.rollback();
        }
    }

    /**
     * Performs a rollback on the <code>Connection</code> then closes it, avoid
     * closing if null.
     *
     * @param conn Connection to rollback. A null value is legal.
     * @throws SQLException if a database access error occurs
     * @since DbUtils 1.1
     */
    public static void rollbackAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.rollback();
            } finally {
                conn.close();
            }
        }
    }

    /**
     * Performs a rollback on the <code>Connection</code> then closes it, avoid
     * closing if null and hide any SQLExceptions that occur.
     *
     * @param conn Connection to rollback. A null value is legal.
     * @since DbUtils 1.1
     */
    public static void rollbackAndCloseQuietly(Connection conn) {
        try {
            rollbackAndClose(conn);
        } catch (SQLException e) {
            LogUtils.debug(e, LOGGER);
        }
    }

    public static DataSourceWrapper warpDataSource(DataSource dataSource) {
        return new DataSourceWrapper(dataSource);
    }

    public static ConnectionWrapper warpConnection(Connection connection) {
        return new ConnectionWrapper(connection);
    }

    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public static ConnectionWrapper getConnectionWrapper(DataSource dataSource) {
        return warpConnection(getConnection(dataSource));
    }

    public static String getCatalog(DataSource dataSource) {
        String catalog = null;
        Connection conn = getConnection(dataSource);
        catalog = getCatalog(conn);
        close(conn);
        return catalog;
    }

    public static String getCatalog(Connection conn) {
        try {
            return conn.getCatalog();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * <p>
     * 设置参数
     * </p>
     *
     * @param prep   PreparedStatementWrapper
     * @param values 参数
     */
    public static void setParameters(PreparedStatementWrapper prep, Object... values) {
        if (LangUtils.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(prep, i + 1, values[i]);
            }
        }
    }

    /**
     * <p>
     * 设置参数
     * </p>
     *
     * @param prep   PreparedStatement
     * @param values 参数
     */
    public static void setParameters(PreparedStatement prep, Object... values) {
        if (LangUtils.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(prep, i + 1, values[i]);
            }
        }
    }

    /**
     * <p>
     * 设置参数
     * </p>
     *
     * @param prep     PreparedStatementWrapper
     * @param position 占位符位置
     * @param value    参数
     */
    public static void setParameter(PreparedStatementWrapper prep, int position, Object value) {
        if (value == null) {
            prep.setObject(position, value);
        } else if (value instanceof Boolean) {
            prep.setBoolean(position, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
            prep.setString(position, (String) value);
        } else if (value instanceof Integer) {
            prep.setInt(position, ((Integer) value).intValue());
        } else if (value instanceof Long) {
            prep.setLong(position, ((Long) value).longValue());
        } else if (value instanceof Float) {
            prep.setFloat(position, ((Float) value).floatValue());
        } else if (value instanceof Double) {
            prep.setDouble(position, ((Double) value).doubleValue());
        } else if (value instanceof BigDecimal) {
            prep.setBigDecimal(position, (BigDecimal) value);
        } else if (value instanceof Byte) {
            prep.setByte(position, ((Byte) value).byteValue());
        } else if (value instanceof Character) {
            prep.setString(position, ((Character) value).toString());
        } else if (value instanceof Short) {
            prep.setShort(position, ((Short) value).shortValue());
        } else if (value instanceof Date) {
            prep.setDate(position, (Date) value);
        } else if (value instanceof Time) {
            prep.setTime(position, (Time) value);
        } else if (value instanceof Timestamp) {
            prep.setTimestamp(position, (Timestamp) value);
        } else if (value instanceof java.util.Date) {
            prep.setObject(position, value);
        } else if (value.getClass().isEnum()) {
            prep.setInt(position, ((Enum<?>) value).ordinal());
        } else {
            prep.setObject(position, value);
        }
    }

    /**
     * <p>
     * 设置参数
     * </p>
     *
     * @param prep     PreparedStatement
     * @param position 占位符位置
     * @param value    参数
     */
    public static void setParameter(PreparedStatement prep, int position, Object value) {
        try {
            if (value == null) {
                prep.setObject(position, value);
            } else if (value instanceof Boolean) {
                prep.setBoolean(position, ((Boolean) value).booleanValue());
            } else if (value instanceof String) {
                prep.setString(position, (String) value);
            } else if (value instanceof Integer) {
                prep.setInt(position, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                prep.setLong(position, ((Long) value).longValue());
            } else if (value instanceof Float) {
                prep.setFloat(position, ((Float) value).floatValue());
            } else if (value instanceof Double) {
                prep.setDouble(position, ((Double) value).doubleValue());
            } else if (value instanceof BigDecimal) {
                prep.setBigDecimal(position, (BigDecimal) value);
            } else if (value instanceof Byte) {
                prep.setByte(position, ((Byte) value).byteValue());
            } else if (value instanceof Character) {
                prep.setString(position, ((Character) value).toString());
            } else if (value instanceof Short) {
                prep.setShort(position, ((Short) value).shortValue());
            } else if (value instanceof Date) {
                prep.setDate(position, (Date) value);
            } else if (value instanceof Time) {
                prep.setTime(position, (Time) value);
            } else if (value instanceof Timestamp) {
                prep.setTimestamp(position, (Timestamp) value);
            } else if (value instanceof java.util.Date) {
                prep.setObject(position, value);
            } else if (value.getClass().isEnum()) {
                prep.setInt(position, ((Enum<?>) value).ordinal());
            } else {
                prep.setObject(position, value);
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a ResultSet, using the specified value
     * type.
     * <p>
     * Uses the specifically typed ResultSet accessor methods, falling back to
     * {@link #getResultSetValue(java.sql.ResultSet, int)} for unknown types.
     * <p>
     * Note that the returned value may not be assignable to the specified
     * required type, in case of an unknown type. Calling code needs to deal
     * with this case appropriately, e.g. throwing a corresponding exception.
     *
     * @param rs           is the ResultSet holding the data
     * @param index        is the column index
     * @param requiredType the required value type (may be <code>null</code>)
     * @return the value object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object getResultSetValue(ResultSet rs, int index, Class<?> requiredType) {
        if (requiredType == null) {
            return getResultSetValue(rs, index);
        }
        try {
            Object value = null;
            boolean wasNullCheck = false;
            // Explicitly extract typed value, as far as possible.
            if (String.class.equals(requiredType)) {
                value = rs.getString(index);
            } else if (boolean.class.equals(requiredType) || Boolean.class.equals(requiredType)) {
                value = Boolean.valueOf(rs.getBoolean(index));
                wasNullCheck = true;
            } else if (byte.class.equals(requiredType) || Byte.class.equals(requiredType)) {
                value = new Byte(rs.getByte(index));
                wasNullCheck = true;
            } else if (short.class.equals(requiredType) || Short.class.equals(requiredType)) {
                value = new Short(rs.getShort(index));
                wasNullCheck = true;
            } else if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
                value = new Integer(rs.getInt(index));
                wasNullCheck = true;
            } else if (long.class.equals(requiredType) || Long.class.equals(requiredType)) {
                value = new Long(rs.getLong(index));
                wasNullCheck = true;
            } else if (float.class.equals(requiredType) || Float.class.equals(requiredType)) {
                value = new Float(rs.getFloat(index));
                wasNullCheck = true;
            } else if (double.class.equals(requiredType) || Double.class.equals(requiredType)
                    || Number.class.equals(requiredType)) {
                value = new Double(rs.getDouble(index));
                wasNullCheck = true;
            } else if (byte[].class.equals(requiredType)) {
                value = rs.getBytes(index);
            } else if (java.sql.Date.class.equals(requiredType)) {
                value = rs.getDate(index);
            } else if (java.sql.Time.class.equals(requiredType)) {
                value = rs.getTime(index);
            } else if (java.sql.Timestamp.class.equals(requiredType) || java.util.Date.class.equals(requiredType)) {
                value = rs.getTimestamp(index);
            } else if (BigDecimal.class.equals(requiredType)) {
                value = rs.getBigDecimal(index);
            } else if (Blob.class.equals(requiredType)) {
                value = rs.getBlob(index);
            } else if (Clob.class.equals(requiredType)) {
                value = rs.getClob(index);
            } else if (requiredType.isEnum()) {
                ResultSetMetaData meta = rs.getMetaData();
                switch (meta.getColumnType(index)) {
                    case Types.TINYINT:
                    case Types.SMALLINT:
                    case Types.INTEGER:
                    case Types.BIGINT:
                        value = LangUtils.toEnum((Class<Enum>) requiredType, rs.getInt(index));
                        break;
                    default:
                        value = LangUtils.toEnum((Class<Enum>) requiredType, rs.getString(index));
                        break;
                }
            } else {
                // Some unknown type desired -> rely on getObject.
                value = getResultSetValue(rs, index);
            }
            // Perform was-null check if demanded (for results that the
            // JDBC driver returns as primitives).
            if (wasNullCheck && value != null && rs.wasNull()) {
                value = null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a ResultSet, using the most appropriate
     * value type. The returned value should be a detached value object, not
     * having any ties to the active ResultSet: in particular, it should not be
     * a Blob or Clob object but rather a byte array respectively String
     * representation.
     * <p>
     * Uses the <code>getObject(index)</code> method, but includes additional
     * "hacks" to get around Oracle 10g returning a non-standard object for its
     * TIMESTAMP datatype and a <code>java.sql.Date</code> for DATE columns
     * leaving out the time portion: These columns will explicitly be extracted
     * as standard <code>java.sql.Timestamp</code> object.
     *
     * @param rs    is the ResultSet holding the data
     * @param index is the column index
     * @return the value object
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    public static Object getResultSetValue(ResultSet rs, int index) {
        try {
            Object obj = rs.getObject(index);
            String className = null;
            if (obj != null) {
                className = obj.getClass().getName();
            }
            if (obj instanceof Blob) {
                obj = rs.getBytes(index);
            } else if (obj instanceof Clob) {
                obj = rs.getString(index);
            } else if (className != null
                    && ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className))) {
                obj = rs.getTimestamp(index);
            } else if (className != null && className.startsWith("oracle.sql.DATE")) {
                String metaDataClassName = rs.getMetaData().getColumnClassName(index);
                if ("java.sql.Timestamp".equals(metaDataClassName)
                        || "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                    obj = rs.getTimestamp(index);
                } else {
                    obj = rs.getDate(index);
                }
            } else if (obj != null && obj instanceof java.sql.Date) {
                if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                    obj = rs.getTimestamp(index);
                }
            }
            return obj;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private static Map<String, Object> getResultSetMap(ResultSet rs) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                Object value = JdbcUtils.getResultSetValue(rs, i);
                String name = JdbcUtils.lookupColumnName(metaData, i);
                resultMap.put(name, value);
            }
            return resultMap;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public static <E> List<E> getResultSetObjects(ResultSetWrapper rs, RowMapper<E> mapper) {
        return getResultSetObjects(rs.getResultSet(), mapper);
    }

    public static <E> List<E> getResultSetObjects(ResultSet rs, RowMapper<E> mapper) {
        try {
            List<E> list = new ArrayList<>();
            int index = 1;
            while (rs.next()) {
                list.add(mapper.mapRow(new SqlResultSet(rs), index));
                index++;
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public static List<Map<String, Object>> getResultSetMaps(ResultSetWrapper rs) {
        return getResultSetMaps(rs.getResultSet());
    }

    public static List<Map<String, Object>> getResultSetMaps(ResultSet rs) {
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                list.add(getResultSetMap(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private static Object[] getResultSetArray(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            Object[] result = new Object[metaData.getColumnCount()];
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                result[i] = JdbcUtils.getResultSetValue(rs, i);
            }
            return result;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public static List<Object[]> getResultSetArrays(ResultSetWrapper rs) {
        return getResultSetArrays(rs.getResultSet());
    }

    public static List<Object[]> getResultSetArrays(ResultSet rs) {
        try {
            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                list.add(getResultSetArray(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Determine the column name to use. The column name is determined based on
     * a lookup using ResultSetMetaData.
     * <p>
     * This method implementation takes into account recent clarifications
     * expressed in the JDBC 4.0 specification:
     * <p>
     * <i>columnLabel - the label for the column specified with the SQL AS
     * clause. If the SQL AS clause was not specified, then the label is the
     * name of the column</i>.
     *
     * @return the column name to use
     * @param resultSetMetaData the current meta data to use
     * @param columnIndex       the index of the column for the look up
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) {
        try {
            String name = resultSetMetaData.getColumnLabel(columnIndex);
            if (LangUtils.isEmpty(name)) {
                name = resultSetMetaData.getColumnName(columnIndex);
            }
            return name;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }
}
