package cn.featherfly.common.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.db.mapper.SqlResultSet;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.wrapper.CallableStatementWrapper;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.DataSourceWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.db.wrapper.ResultSetWrapper;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.TypeNames;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.repository.mapper.RowMapper;

/**
 * JDBC操作的工具类 .
 *
 * @author zhongj
 */
public final class JdbcUtils {

    private static final String SQL_TIMESTAMP = "java.sql.Timestamp";
    private static final String ORACLE_TIMESTAMP = "oracle.sql.TIMESTAMP";
    private static final String ORACLE_TIMESTAMPTZ = "oracle.sql.TIMESTAMPTZ";
    private static final String ORACLE_DATE = "oracle.sql.DATE";

    /**
     * Instantiates a new jdbc utils.
     */
    private JdbcUtils() {
    }

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtils.class);

    /**
     * Gets the connection.
     *
     * @param driverClassName the driver class name
     * @param uri the uri
     * @param username the username
     * @param password the password
     * @return the connection
     */
    public static ConnectionWrapper getConnection(String driverClassName, String uri, String username,
        String password) {
        try {
            ClassUtils.forName(driverClassName);
            return new ConnectionWrapper(DriverManager.getConnection(uri, username, password));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the connection.
     *
     * @param driverClassName the driver class name
     * @param uri the uri
     * @return the connection
     * @throws JdbcException if a database access error occurs
     */
    public static ConnectionWrapper getConnection(String driverClassName, String uri) {
        try {
            ClassUtils.forName(driverClassName);
            return new ConnectionWrapper(DriverManager.getConnection(uri));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 关闭<code>Connection</code>, 忽略null的情况.
     *
     * @param conn the conn
     * @throws JdbcException if a database access error occurs
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
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
     * @param rs ResultSet.
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
     * commit transaction.
     *
     * @param conn the conn
     * @throws JdbcException if a database access error occurs
     */
    public static void commit(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 提交<code>Connection</code>以后关闭, 忽略null的情况.
     *
     * @param conn the conn
     * @throws JdbcException if a database access error occurs
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
     * Rollback any changes made on the given connection.
     *
     * @param conn the conn
     * @throws JdbcException if a database access error occurs
     */
    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * Rollback any changes made on the given connection.
     *
     * @param conn the conn
     * @param savepoint the savepoint
     * @throws JdbcException if a database access error occurs
     */
    public static void rollback(Connection conn, Savepoint savepoint) {
        if (conn != null) {
            try {
                if (savepoint != null) {
                    conn.rollback(savepoint);
                } else {
                    conn.rollback();
                }
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * Performs a rollback on the <code>Connection</code> then closes it, avoid
     * closing if null.
     *
     * @param conn the conn
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

    /**
     * Warp data source.
     *
     * @param dataSource the data source
     * @return the data source wrapper
     */
    public static DataSourceWrapper warpDataSource(DataSource dataSource) {
        return new DataSourceWrapper(dataSource);
    }

    /**
     * Warp connection.
     *
     * @param connection the connection
     * @return the connection wrapper
     */
    public static ConnectionWrapper warpConnection(Connection connection) {
        return new ConnectionWrapper(connection);
    }

    /**
     * Gets the connection.
     *
     * @param dataSource the data source
     * @return the connection
     */
    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the connection wrapper.
     *
     * @param dataSource the data source
     * @return the connection wrapper
     */
    public static ConnectionWrapper getConnectionWrapper(DataSource dataSource) {
        return warpConnection(getConnection(dataSource));
    }

    /**
     * Gets the catalog.
     *
     * @param dataSource the data source
     * @return the catalog
     */
    public static String getCatalog(DataSource dataSource) {
        String catalog = null;
        Connection conn = getConnection(dataSource);
        catalog = getCatalog(conn);
        close(conn);
        return catalog;
    }

    /**
     * Gets the catalog.
     *
     * @param conn the conn
     * @return the catalog
     */
    public static String getCatalog(Connection conn) {
        try {
            return conn.getCatalog();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数 .
     *
     * @param prep PreparedStatementWrapper
     * @param values 参数
     */
    public static void setParameters(PreparedStatementWrapper prep, Serializable... values) {
        setParameters(prep, true, values);
    }

    /**
     * 设置参数 .
     *
     * @param prep PreparedStatementWrapper
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(PreparedStatementWrapper prep, boolean enumWithOridinal, Serializable... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(prep, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param values 参数
     */
    public static void setParameters(PreparedStatement prep, Serializable... values) {
        setParameters(prep, true, values);
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(PreparedStatement prep, boolean enumWithOridinal, Object... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(prep, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(PreparedStatement prep, boolean enumWithOridinal, Serializable... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(prep, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatementWrapper
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatementWrapper prep, int position, Object value) {
        setParameter(prep, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatementWrapper
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatementWrapper prep, int position, Serializable value) {
        setParameter(prep, position, value, true);
    }

    /**
     * 设置参数 .
     *
     * @param prep PreparedStatementWrapper
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(PreparedStatementWrapper prep, int position, Object value,
        boolean enumWithOridinal) {
        setParameter(prep.getPreparedStatement(), position, value, enumWithOridinal);
    }

    /**
     * 设置参数 .
     *
     * @param prep PreparedStatementWrapper
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(PreparedStatementWrapper prep, int position, Serializable value,
        boolean enumWithOridinal) {
        setParameter(prep.getPreparedStatement(), position, value, enumWithOridinal);
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Object value) {
        setParameter(prep, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Serializable value) {
        setParameter(prep, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     */
    public static void setParameterNull(PreparedStatement prep, int position) {
        try {
            prep.setObject(position, null);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, String value) {
        try {
            prep.setString(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, boolean value) {
        try {
            prep.setBoolean(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Boolean value) {
        if (value != null) {
            setParameter(prep, position, value.booleanValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, char value) {
        setParameter(prep, position, String.valueOf(value));
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Character value) {
        setParameter(prep, position, value.toString());
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, byte value) {
        try {
            prep.setByte(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Byte value) {
        if (value != null) {
            setParameter(prep, position, value.byteValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, short value) {
        try {
            prep.setShort(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Short value) {
        if (value != null) {
            setParameter(prep, position, value.shortValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, int value) {
        try {
            prep.setInt(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Integer value) {
        if (value != null) {
            setParameter(prep, position, value.intValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, long value) {
        try {
            prep.setLong(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Long value) {
        if (value != null) {
            setParameter(prep, position, value.longValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, BigInteger value) {
        if (value != null) {
            setParameter(prep, position, new BigDecimal(value));
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, float value) {
        try {
            prep.setFloat(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Float value) {
        if (value != null) {
            setParameter(prep, position, value.floatValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, double value) {
        try {
            prep.setDouble(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Double value) {
        if (value != null) {
            setParameter(prep, position, value.doubleValue());
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, BigDecimal value) {
        try {
            prep.setBigDecimal(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, AtomicBoolean value) {
        if (value == null) {
            setParameterNull(prep, position);
        } else {
            setParameter(prep, position, value.get());
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, AtomicInteger value) {
        if (value == null) {
            setParameterNull(prep, position);
        } else {
            setParameter(prep, position, value.intValue());
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, AtomicLong value) {
        if (value == null) {
            setParameterNull(prep, position);
        } else {
            setParameter(prep, position, value.longValue());
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, java.sql.Date value) {
        try {
            prep.setDate(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Time value) {
        try {
            prep.setTime(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Timestamp value) {
        try {
            prep.setTimestamp(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, Date value) {
        if (value != null) {
            setParameter(prep, position, new java.sql.Date(value.getTime()));
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, LocalDate value) {
        if (value != null) {
            setParameter(prep, position, java.sql.Date.valueOf(value));
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, LocalDateTime value) {
        if (value != null) {
            Timestamp timestamp = Timestamp.valueOf(value);
            timestamp.setNanos(0); // 去掉nano
            setParameter(prep, position, timestamp);
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(PreparedStatement prep, int position, LocalTime value) {
        if (value != null) {
            setParameter(prep, position, Time.valueOf(value));
        } else {
            setParameterNull(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E> void setParameter(PreparedStatement prep, int position, FieldValueOperator<E> value) {
        if (value == null) {
            setParameterNull(prep, position);
        } else {
            value.set(prep, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E extends Enum<?>> void setParameter(PreparedStatement prep, int position, E value) {
        setParameter(prep, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal the enum with oridinal
     */
    public static <E extends Enum<?>> void setParameter(PreparedStatement prep, int position, E value,
        boolean enumWithOridinal) {
        if (value == null) {
            setParameterNull(prep, position);
        } else {
            try {
                if (enumWithOridinal) {
                    prep.setInt(position, value.ordinal());
                } else {
                    prep.setString(position, value.name());
                }
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E> void setParameter(PreparedStatement prep, int position, Optional<E> value) {
        setParameter(prep, position, value.orElse(null));
    }

    /**
     * 设置参数.
     *
     * @param prep PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(PreparedStatement prep, int position, Object value, boolean enumWithOridinal) {
        try {
            if (value == null) {
                prep.setObject(position, value);
                return;
            }

            if (value.getClass().isEnum()) {
                if (enumWithOridinal) {
                    prep.setInt(position, ((Enum<?>) value).ordinal());
                } else {
                    prep.setString(position, ((Enum<?>) value).name());
                }
                return;
            }

            switch (value.getClass().getName()) {
                case TypeNames.STRING_NAME:
                    prep.setString(position, (String) value);
                    return;

                case TypeNames.BOOL_NAME:
                case TypeNames.BOOLEAN_NAME:
                    prep.setBoolean(position, ((Boolean) value).booleanValue());
                    return;

                case TypeNames.CHAR_NAME:
                case TypeNames.CHARACTER_NAME:
                    prep.setString(position, ((Character) value).toString());
                    return;

                case TypeNames.INT_NAME:
                case TypeNames.INTEGER_NAME:
                    prep.setInt(position, ((Integer) value).intValue());
                    return;

                case TypeNames.LONG_NAME:
                case TypeNames.LONG_TYPE_NAME:
                    prep.setLong(position, ((Long) value).longValue());
                    return;

                case TypeNames.BYTE_NAME:
                case TypeNames.BYTE_TYPE_NAME:
                    prep.setByte(position, ((Byte) value).byteValue());
                    return;

                case TypeNames.SHORT_NAME:
                case TypeNames.SHORT_TYPE_NAME:
                    prep.setShort(position, ((Short) value).shortValue());
                    return;

                case TypeNames.FLOAT_NAME:
                case TypeNames.FLOAT_TYPE_NAME:
                    prep.setFloat(position, ((Float) value).floatValue());
                    return;

                case TypeNames.DOUBLE_NAME:
                case TypeNames.DOUBLE_TYPE_NAME:
                    prep.setDouble(position, ((Double) value).doubleValue());
                    return;

                case TypeNames.BIGDECIMAL_NAME:
                    prep.setBigDecimal(position, (BigDecimal) value);
                    return;
                case TypeNames.BIGINTEGER_NAME:
                    prep.setBigDecimal(position, new BigDecimal((BigInteger) value));
                    return;

                case TypeNames.DATE_NAME:
                    prep.setTimestamp(position, new Timestamp(((Date) value).getTime()));
                    return;
                case TypeNames.SQL_DATE_NAME:
                    prep.setDate(position, (java.sql.Date) value);
                    return;
                case TypeNames.SQL_TIME_NAME:
                    prep.setTime(position, (Time) value);
                    return;
                case TypeNames.SQL_TIMESTAMP_NAME:
                    prep.setTimestamp(position, (Timestamp) value);
                    return;
                case TypeNames.LOCALDATE_NAME:
                    prep.setDate(position, java.sql.Date.valueOf((LocalDate) value));
                    return;
                case TypeNames.LOCALTIME_NAME:
                    prep.setTime(position, Time.valueOf((LocalTime) value));
                    return;
                case TypeNames.LOCALDATETIME_NAME:
                    prep.setTimestamp(position, Timestamp.valueOf((LocalDateTime) value));
                    return;

                case TypeNames.FIELDVALUEOPERATOR_NAME:
                    setParameter(prep, position, (FieldValueOperator<?>) value);
                    return;

                case TypeNames.OPTIONAL_NAME:
                    setParameter(prep, position, ((Optional<?>) value).orElse(null), enumWithOridinal);
                    return;

                case TypeNames.ATOMICINTEGER_NAME:
                    prep.setInt(position, ((AtomicInteger) value).get());
                    return;
                case TypeNames.ATOMICLONG_NAME:
                    prep.setLong(position, ((AtomicLong) value).get());
                    return;
                case TypeNames.ATOMICBOOLEAN_NAME:
                    prep.setBoolean(position, ((AtomicBoolean) value).get());
                    return;

                default:
                    prep.setObject(position, value);
                    return;
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    // ****************************************************************************************************************
    //  set parameter for ResultSet
    // ****************************************************************************************************************

    /**
     * 设置参数 .
     *
     * @param res ResultSetWrapper
     * @param values 参数
     */
    public static void setParameters(ResultSetWrapper res, Serializable... values) {
        setParameters(res, true, values);
    }

    /**
     * 设置参数 .
     *
     * @param res ResultSetWrapper
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(ResultSetWrapper res, boolean enumWithOridinal, Serializable... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(res, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param values 参数
     */
    public static void setParameters(ResultSet res, Serializable... values) {
        setParameters(res, true, values);
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(ResultSet res, boolean enumWithOridinal, Serializable... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(res, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param enumWithOridinal the enum with oridinal
     * @param values 参数
     */
    public static void setParameters(ResultSet res, boolean enumWithOridinal, Object... values) {
        if (Lang.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                setParameter(res, i + 1, values[i], enumWithOridinal);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param res ResultSetWrapper
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSetWrapper res, int position, Object value) {
        setParameter(res, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param res ResultSetWrapper
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSetWrapper res, int position, Serializable value) {
        setParameter(res, position, value, true);
    }

    /**
     * 设置参数 .
     *
     * @param res ResultSetWrapper
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(ResultSetWrapper res, int position, Object value, boolean enumWithOridinal) {
        setParameter(res.getResultSet(), position, value, enumWithOridinal);
    }

    /**
     * 设置参数 .
     *
     * @param res ResultSetWrapper
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(ResultSetWrapper res, int position, Serializable value, boolean enumWithOridinal) {
        setParameter(res.getResultSet(), position, value, enumWithOridinal);
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Object value) {
        setParameter(res, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Serializable value) {
        setParameter(res, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     */
    public static void setParameterNull(ResultSet res, int position) {
        try {
            res.updateObject(position, null);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, String value) {
        try {
            res.updateString(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, boolean value) {
        try {
            res.updateBoolean(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Boolean value) {
        if (value != null) {
            setParameter(res, position, value.booleanValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, char value) {
        setParameter(res, position, String.valueOf(value));
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Character value) {
        setParameter(res, position, value.toString());
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, byte value) {
        try {
            res.updateByte(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Byte value) {
        if (value != null) {
            setParameter(res, position, value.byteValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, short value) {
        try {
            res.updateShort(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Short value) {
        if (value != null) {
            setParameter(res, position, value.shortValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, int value) {
        try {
            res.updateInt(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Integer value) {
        if (value != null) {
            setParameter(res, position, value.intValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, long value) {
        try {
            res.updateLong(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Long value) {
        if (value != null) {
            setParameter(res, position, value.longValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, BigInteger value) {
        if (value != null) {
            setParameter(res, position, value.longValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, float value) {
        try {
            res.updateFloat(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Float value) {
        if (value != null) {
            setParameter(res, position, value.floatValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, double value) {
        try {
            res.updateDouble(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Double value) {
        if (value != null) {
            setParameter(res, position, value.doubleValue());
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, BigDecimal value) {
        try {
            res.updateBigDecimal(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, AtomicBoolean value) {
        if (value == null) {
            setParameterNull(res, position);
        } else {
            setParameter(res, position, value.get());
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, AtomicInteger value) {
        if (value == null) {
            setParameterNull(res, position);
        } else {
            setParameter(res, position, value.intValue());
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, AtomicLong value) {
        if (value == null) {
            setParameterNull(res, position);
        } else {
            setParameter(res, position, value.longValue());
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, java.sql.Date value) {
        try {
            res.updateDate(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Time value) {
        try {
            res.updateTime(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Timestamp value) {
        try {
            res.updateTimestamp(position, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, Date value) {
        if (value != null) {
            setParameter(res, position, new java.sql.Date(value.getTime()));
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, LocalDate value) {
        if (value != null) {
            setParameter(res, position, java.sql.Date.valueOf(value));
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, LocalDateTime value) {
        if (value != null) {
            Timestamp timestamp = Timestamp.valueOf(value);
            timestamp.setNanos(0); // 去掉nano
            setParameter(res, position, timestamp);
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static void setParameter(ResultSet res, int position, LocalTime value) {
        if (value != null) {
            setParameter(res, position, Time.valueOf(value));
        } else {
            setParameterNull(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E> void setParameter(ResultSet res, int position, FieldValueOperator<E> value) {
        if (value == null) {
            setParameterNull(res, position);
        } else {
            value.update(res, position);
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E extends Enum<?>> void setParameter(ResultSet res, int position, E value) {
        setParameter(res, position, value, true);
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     * @param enumWithOridinal the enum with oridinal
     */
    public static <E extends Enum<?>> void setParameter(ResultSet res, int position, E value,
        boolean enumWithOridinal) {
        if (value == null) {
            setParameterNull(res, position);
        } else {
            try {
                if (enumWithOridinal) {
                    res.updateInt(position, value.ordinal());
                } else {
                    res.updateString(position, value.name());
                }
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param res PreparedStatement
     * @param position 占位符位置
     * @param value 参数
     */
    public static <E> void setParameter(ResultSet res, int position, Optional<E> value) {
        setParameter(res, position, value.orElse(null));
    }

    /**
     * 设置参数.
     *
     * @param res the res
     * @param position param position
     * @param value param value
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(ResultSet res, int position, Object value, boolean enumWithOridinal) {
        try {
            if (value == null) {
                res.updateObject(position, value);
                return;
            }

            if (value.getClass().isEnum()) {
                if (enumWithOridinal) {
                    res.updateInt(position, ((Enum<?>) value).ordinal());
                } else {
                    res.updateString(position, ((Enum<?>) value).name());
                }
                return;
            }

            switch (value.getClass().getName()) {
                case TypeNames.STRING_NAME:
                    res.updateString(position, (String) value);
                    return;

                case TypeNames.BOOL_NAME:
                case TypeNames.BOOLEAN_NAME:
                    res.updateBoolean(position, ((Boolean) value).booleanValue());
                    return;

                case TypeNames.CHAR_NAME:
                case TypeNames.CHARACTER_NAME:
                    res.updateString(position, ((Character) value).toString());
                    return;

                case TypeNames.INT_NAME:
                case TypeNames.INTEGER_NAME:
                    res.updateInt(position, ((Integer) value).intValue());
                    return;

                case TypeNames.LONG_NAME:
                case TypeNames.LONG_TYPE_NAME:
                    res.updateLong(position, ((Long) value).longValue());
                    return;

                case TypeNames.BYTE_NAME:
                case TypeNames.BYTE_TYPE_NAME:
                    res.updateByte(position, ((Byte) value).byteValue());
                    return;

                case TypeNames.SHORT_NAME:
                case TypeNames.SHORT_TYPE_NAME:
                    res.updateShort(position, ((Short) value).shortValue());
                    return;

                case TypeNames.FLOAT_NAME:
                case TypeNames.FLOAT_TYPE_NAME:
                    res.updateFloat(position, ((Float) value).floatValue());
                    return;

                case TypeNames.DOUBLE_NAME:
                case TypeNames.DOUBLE_TYPE_NAME:
                    res.updateDouble(position, ((Double) value).doubleValue());
                    return;

                case TypeNames.BIGDECIMAL_NAME:
                    res.updateBigDecimal(position, (BigDecimal) value);
                    return;
                case TypeNames.BIGINTEGER_NAME:
                    res.updateBigDecimal(position, new BigDecimal((BigInteger) value));
                    return;

                case TypeNames.DATE_NAME:
                    res.updateTimestamp(position, new Timestamp(((Date) value).getTime()));
                    return;
                case TypeNames.SQL_DATE_NAME:
                    res.updateDate(position, (java.sql.Date) value);
                    return;
                case TypeNames.SQL_TIME_NAME:
                    res.updateTime(position, (Time) value);
                    return;
                case TypeNames.SQL_TIMESTAMP_NAME:
                    res.updateTimestamp(position, (Timestamp) value);
                    return;
                case TypeNames.LOCALDATE_NAME:
                    res.updateDate(position, java.sql.Date.valueOf((LocalDate) value));
                    return;
                case TypeNames.LOCALTIME_NAME:
                    res.updateTime(position, Time.valueOf((LocalTime) value));
                    return;
                case TypeNames.LOCALDATETIME_NAME:
                    res.updateTimestamp(position, Timestamp.valueOf((LocalDateTime) value));
                    return;

                case TypeNames.FIELDVALUEOPERATOR_NAME:
                    setParameter(res, position, (FieldValueOperator<?>) value);
                    return;

                case TypeNames.OPTIONAL_NAME:
                    setParameter(res, position, ((Optional<?>) value).orElse(null), enumWithOridinal);
                    return;

                case TypeNames.ATOMICINTEGER_NAME:
                    res.updateInt(position, ((AtomicInteger) value).get());
                    return;
                case TypeNames.ATOMICLONG_NAME:
                    res.updateLong(position, ((AtomicLong) value).get());
                    return;
                case TypeNames.ATOMICBOOLEAN_NAME:
                    res.updateBoolean(position, ((AtomicBoolean) value).get());
                    return;

                default:
                    res.updateObject(position, value);
                    return;
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    // ****************************************************************************************************************
    //	set parameter for CallableStatement
    // ****************************************************************************************************************

    /**
     * 设置参数.
     *
     * @param <E> the element type
     * @param call the CallableStatement
     * @param name the parameter name
     * @param value the value
     */
    public static <E> void setParameter(CallableStatement call, String name, FieldValueOperator<E> value) {
        if (value == null) {
            setParameterNull(call, name);
        } else {
            value.set(call, name);
        }
    }

    /**
     * 设置参数.
     *
     * @param call the CallableStatement
     * @param name the parameter name
     */
    public static void setParameterNull(CallableStatement call, String name) {
        try {
            call.setObject(name, null);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * set proceduce param.
     *
     * @param call CallableStatement
     * @param name param name
     * @param value param value
     */
    public static void setParameter(CallableStatement call, String name, Serializable value) {
        setParameter(call, name, value, true);
    }

    /**
     * set proceduce param.
     *
     * @param call CallableStatement
     * @param name param name
     * @param value param value
     */
    public static void setParameter(CallableStatement call, String name, Object value) {
        setParameter(call, name, value, true);
    }

    /**
     * set proceduce param.
     *
     * @param call CallableStatement
     * @param name param name
     * @param value param value
     * @param enumWithOridinal enum with oridinal
     */
    public static void setParameter(CallableStatement call, String name, Object value, boolean enumWithOridinal) {
        try {
            if (value == null) {
                call.setObject(name, value);
                return;
            }

            if (value.getClass().isEnum()) {
                if (enumWithOridinal) {
                    call.setInt(name, ((Enum<?>) value).ordinal());
                } else {
                    call.setString(name, ((Enum<?>) value).name());
                }
                return;
            }

            switch (value.getClass().getName()) {
                case TypeNames.STRING_NAME:
                    call.setString(name, (String) value);
                    return;

                case TypeNames.BOOL_NAME:
                case TypeNames.BOOLEAN_NAME:
                    call.setBoolean(name, ((Boolean) value).booleanValue());
                    return;

                case TypeNames.CHAR_NAME:
                case TypeNames.CHARACTER_NAME:
                    call.setString(name, ((Character) value).toString());
                    return;

                case TypeNames.INT_NAME:
                case TypeNames.INTEGER_NAME:
                    call.setInt(name, ((Integer) value).intValue());
                    return;

                case TypeNames.LONG_NAME:
                case TypeNames.LONG_TYPE_NAME:
                    call.setLong(name, ((Long) value).longValue());
                    return;

                case TypeNames.BYTE_NAME:
                case TypeNames.BYTE_TYPE_NAME:
                    call.setByte(name, ((Byte) value).byteValue());
                    return;

                case TypeNames.SHORT_NAME:
                case TypeNames.SHORT_TYPE_NAME:
                    call.setShort(name, ((Short) value).shortValue());
                    return;

                case TypeNames.FLOAT_NAME:
                case TypeNames.FLOAT_TYPE_NAME:
                    call.setFloat(name, ((Float) value).floatValue());
                    return;

                case TypeNames.DOUBLE_NAME:
                case TypeNames.DOUBLE_TYPE_NAME:
                    call.setDouble(name, ((Double) value).doubleValue());
                    return;

                case TypeNames.BIGDECIMAL_NAME:
                    call.setBigDecimal(name, (BigDecimal) value);
                    return;
                case TypeNames.BIGINTEGER_NAME:
                    call.setBigDecimal(name, new BigDecimal((BigInteger) value));
                    return;

                case TypeNames.DATE_NAME:
                    call.setTimestamp(name, new Timestamp(((Date) value).getTime()));
                    return;
                case TypeNames.SQL_DATE_NAME:
                    call.setDate(name, (java.sql.Date) value);
                    return;
                case TypeNames.SQL_TIME_NAME:
                    call.setTime(name, (Time) value);
                    return;
                case TypeNames.SQL_TIMESTAMP_NAME:
                    call.setTimestamp(name, (Timestamp) value);
                    return;
                case TypeNames.LOCALDATE_NAME:
                    call.setDate(name, java.sql.Date.valueOf((LocalDate) value));
                    return;
                case TypeNames.LOCALTIME_NAME:
                    call.setTime(name, Time.valueOf((LocalTime) value));
                    return;
                case TypeNames.LOCALDATETIME_NAME:
                    call.setTimestamp(name, Timestamp.valueOf((LocalDateTime) value));
                    return;

                case TypeNames.FIELDVALUEOPERATOR_NAME:
                    setParameter(call, name, (FieldValueOperator<?>) value);
                    return;

                case TypeNames.OPTIONAL_NAME:
                    setParameter(call, name, ((Optional<?>) value).orElse(null), enumWithOridinal);
                    return;

                case TypeNames.ATOMICINTEGER_NAME:
                    call.setInt(name, ((AtomicInteger) value).get());
                    return;
                case TypeNames.ATOMICLONG_NAME:
                    call.setLong(name, ((AtomicLong) value).get());
                    return;
                case TypeNames.ATOMICBOOLEAN_NAME:
                    call.setBoolean(name, ((AtomicBoolean) value).get());
                    return;

                default:
                    call.setObject(name, value);
                    return;
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * set proceduce param.
     *
     * @param call the call
     * @param values the values
     * @return the out parameter index and class map
     */
    public static Map<Integer, Class<? extends Serializable>> setParameters(CallableStatement call,
        Serializable... values) {
        return setParameters(call, true, values);
    }

    //    /**
    //     * set proceduce param.
    //     *
    //     * @param call the call
    //     * @param values the values
    //     * @return the out parameter index and class map
    //     */
    //    public static Map<Integer, Class<?>> setParameters(CallableStatement call, Object... values) {
    //        return setParameters(call, true, values);
    //    }

    /**
     * set proceduce param.
     *
     * @param call the call
     * @param enumWithOridinal the enum with oridinal
     * @param values the values
     * @return the out parameter index and class map
     */
    public static Map<Integer, Class<? extends Serializable>> setParameters(CallableStatement call,
        boolean enumWithOridinal, Serializable... values) {
        Map<Integer, Class<? extends Serializable>> outParamMap = new HashMap<>(0);
        try {
            ParameterMetaData meta = call.getParameterMetaData();
            if (meta.getParameterCount() != values.length) {
                // ENHANCE 后续来优化异常信息
                throw new JdbcException(
                    Str.format("procedure parameter count[{0}] not equals parameter values length[{1}]",
                        meta.getParameterCount(), values.length));
            }
            for (int i = 1; i <= values.length; i++) {
                Serializable arg = values[i - 1];
                int mode = meta.getParameterMode(i);
                if (mode == ParameterMetaData.parameterModeOut) {
                    setOutParamMap(outParamMap, i, arg, meta);
                } else if (mode == ParameterMetaData.parameterModeInOut) {
                    setOutParamMap(outParamMap, i, arg, meta);
                    setParameter(call, i, arg, enumWithOridinal);
                } else {
                    setParameter(call, i, arg, enumWithOridinal);
                }
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
        return outParamMap;
    }

    /**
     * Sets the out param map.
     *
     * @param outParamMap the out param map
     * @param index the index
     * @param arg the arg
     * @param meta the meta
     * @throws SQLException the SQL exception
     */
    @SuppressWarnings("unchecked")
    private static void setOutParamMap(Map<Integer, Class<? extends Serializable>> outParamMap, int index,
        Serializable arg, ParameterMetaData meta) throws SQLException {
        if (arg == null) {
            outParamMap.put(index,
                (Class<? extends Serializable>) ClassUtils.forName(meta.getParameterClassName(index)));
        } else {
            outParamMap.put(index, arg.getClass());
        }
    }

    //    /**
    //     * Sets the out param map.
    //     *
    //     * @param outParamMap the out param map
    //     * @param index the index
    //     * @param arg the arg
    //     * @param meta the meta
    //     * @throws SQLException the SQL exception
    //     */
    //    private static void setOutParamMap(Map<Integer, Class<?>> outParamMap, int index, Object arg,
    //        ParameterMetaData meta) throws SQLException {
    //        if (arg == null) {
    //            outParamMap.put(index, ClassUtils.forName(meta.getParameterClassName(index)));
    //        } else {
    //            outParamMap.put(index, arg.getClass());
    //        }
    //    }

    /**
     * Gets the column name.
     *
     * @param rs the rs
     * @param columnIndex the column index
     * @return the column name
     */
    public static String getColumnName(ResultSet rs, int columnIndex) {
        try {
            return getColumnName(rs.getMetaData(), columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column name.
     *
     * @param metaData the meta data
     * @param columnIndex the column index
     * @return the column name
     */
    public static String getColumnName(ResultSetMetaData metaData, int columnIndex) {
        try {
            return metaData.getColumnName(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the table name.
     *
     * @param rs the rs
     * @param columnIndex the column index
     * @return the table name
     */
    public static String getTableName(ResultSet rs, int columnIndex) {
        try {
            return getTableName(rs.getMetaData(), columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the table name.
     *
     * @param metaData the meta data
     * @param columnIndex the column index
     * @return the table name
     */
    public static String getTableName(ResultSetMetaData metaData, int columnIndex) {
        try {
            return metaData.getTableName(columnIndex);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column index.
     *
     * @param rs the rs
     * @param name the name
     * @return the column index
     */
    public static int getColumnIndex(ResultSet rs, String name) {
        try {
            return getColumnIndex(rs.getMetaData(), name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column index.
     *
     * @param metaData the meta data
     * @param name the name
     * @return the column index
     */
    public static int getColumnIndex(ResultSetMetaData metaData, String name) {
        try {
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String n = JdbcUtils.lookupColumnName(metaData, i);
                if (n.equals(name)) {
                    return i;
                }
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
        throw new JdbcException(String.format("column named [%s] not found in ResultSet", name));
    }

    /**
     * Gets the parameter SQL type.
     *
     * @param prep the prep
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getParameterType(PreparedStatementWrapper prep, int index) {
        if (prep == null) {
            return null;
        }
        return getParameterType(prep.getPreparedStatement(), index);
    }

    /**
     * Gets the parameter SQL type.
     *
     * @param prep the prep
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getParameterType(PreparedStatement prep, int index) {
        if (prep == null) {
            return null;
        }
        try {
            return JDBCType.valueOf(prep.getMetaData().getColumnType(index));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result SQL type.
     *
     * @param rs the rs
     * @param index the index
     * @return the result SQL type
     * @deprecated {@link #getResultSetType(ResultSet, int)}
     */
    @Deprecated
    public static SQLType getResultSQLType(ResultSetWrapper rs, int index) {
        return getResultSetType(rs, index);
    }

    /**
     * Gets the result SQL type.
     *
     * @param rs the rs
     * @param index the index
     * @return the result SQL type
     * @deprecated {@link #getResultSetType(ResultSet, int)}
     */
    @Deprecated
    public static SQLType getResultSQLType(ResultSet rs, int index) {
        return getResultSetType(rs, index);
    }

    /**
     * Gets the resultset SQL type.
     *
     * @param rs the rs
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getResultSetType(ResultSetWrapper rs, int index) {
        if (rs == null) {
            return null;
        }
        return getResultSetType(rs.getResultSet(), index);
    }

    /**
     * Gets the resultset SQL type.
     *
     * @param rs the rs
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getResultSetType(ResultSet rs, int index) {
        if (rs == null) {
            return null;
        }
        try {
            return JDBCType.valueOf(rs.getMetaData().getColumnType(index));
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
     * @param <E> the element type
     * @param rs the rs
     * @param index the index
     * @param requiredType the required type
     * @return the value object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <E> E getResultSetValue(ResultSet rs, int index, Class<E> requiredType) {
        if (requiredType == null) {
            return (E) getResultSetValue(rs, index);
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
                value = Byte.valueOf(rs.getByte(index));
                wasNullCheck = true;
            } else if (short.class.equals(requiredType) || Short.class.equals(requiredType)) {
                value = Short.valueOf(rs.getShort(index));
                wasNullCheck = true;
            } else if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
                value = Integer.valueOf(rs.getInt(index));
                wasNullCheck = true;
            } else if (long.class.equals(requiredType) || Long.class.equals(requiredType)) {
                value = Long.valueOf(rs.getLong(index));
                wasNullCheck = true;
            } else if (float.class.equals(requiredType) || Float.class.equals(requiredType)) {
                value = Float.valueOf(rs.getFloat(index));
                wasNullCheck = true;
            } else if (double.class.equals(requiredType) || Double.class.equals(requiredType)
                || Number.class.equals(requiredType)) {
                value = Double.valueOf(rs.getDouble(index));
                wasNullCheck = true;
            } else if (byte[].class.equals(requiredType)) {
                value = rs.getBytes(index);
            } else if (java.sql.Date.class.equals(requiredType)) {
                value = rs.getDate(index);
            } else if (java.sql.Time.class.equals(requiredType)) {
                value = rs.getTime(index);
            } else if (java.sql.Timestamp.class.equals(requiredType) || java.util.Date.class.equals(requiredType)) {
                value = rs.getTimestamp(index);
            } else if (LocalDate.class.equals(requiredType)) {
                value = rs.getDate(index);
                if (value != null) {
                    value = Dates.toLocalDate(new Date(((java.sql.Date) value).getTime()));
                }
            } else if (LocalTime.class.equals(requiredType)) {
                value = rs.getTime(index);
                if (value != null) {
                    value = Dates.toLocalTime(new Date(((java.sql.Time) value).getTime()));
                }
            } else if (LocalDateTime.class.equals(requiredType)) {
                value = rs.getTimestamp(index);
                if (value != null) {
                    value = Dates.toLocalDateTime(new Date(((java.sql.Timestamp) value).getTime()));
                }
            } else if (BigDecimal.class.equals(requiredType)) {
                value = rs.getBigDecimal(index);
            } else if (BigInteger.class.equals(requiredType)) {
                value = Lang.ifNotNull(rs.getBigDecimal(index), bd -> bd.toBigIntegerExact());
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
                        value = Lang.toEnum((Class<Enum>) requiredType, rs.getInt(index));
                        break;
                    default:
                        value = Lang.toEnum((Class<Enum>) requiredType, rs.getString(index));
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
            return (E) value;
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
     * @param rs the rs
     * @param index the index
     * @return the value object
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    public static Serializable getResultSetValue(ResultSet rs, int index) {
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
                && (ORACLE_TIMESTAMP.equals(className) || ORACLE_TIMESTAMPTZ.equals(className))) {
                obj = rs.getTimestamp(index);
            } else if (className != null && className.startsWith(ORACLE_DATE)) {
                String metaDataClassName = rs.getMetaData().getColumnClassName(index);
                if (SQL_TIMESTAMP.equals(metaDataClassName) || ORACLE_TIMESTAMP.equals(metaDataClassName)) {
                    obj = rs.getTimestamp(index);
                } else {
                    obj = rs.getDate(index);
                }
            } else if (obj instanceof java.sql.Date
                && SQL_TIMESTAMP.equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getTimestamp(index);
            }
            return (Serializable) obj;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set map.
     *
     * @param rs the rs
     * @return the result set map
     */
    public static Map<String, Serializable> getResultSetMap(ResultSet rs) {
        try {
            Map<String, Serializable> resultMap = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                resultMap.put(JdbcUtils.lookupColumnName(metaData, i, true), JdbcUtils.getResultSetValue(rs, i));
            }
            return resultMap;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set map.
     *
     * @param rs the rs
     * @param manager the manager
     * @return the result set map
     */
    public static Map<String, Serializable> getResultSetMap(ResultSet rs, SqlTypeMappingManager manager) {
        try {
            Map<String, Serializable> resultMap = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Class<? extends Serializable> type = manager.getJavaType(JDBCType.valueOf(metaData.getColumnType(i)));
                Serializable value = null;
                if (type != null) {
                    value = manager.get(rs, i, type);
                } else {
                    value = JdbcUtils.getResultSetValue(rs, i);
                }
                resultMap.put(JdbcUtils.lookupColumnName(metaData, i, true), value);
            }
            return resultMap;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set objects.
     *
     * @param <E> the element type
     * @param rs the rs
     * @param mapper the mapper
     * @return the result set objects
     */
    public static <E> List<E> getResultSetObjects(ResultSetWrapper rs, RowMapper<E> mapper) {
        return getResultSetObjects(rs.getResultSet(), mapper);
    }

    /**
     * Gets the result set objects.
     *
     * @param <E> the element type
     * @param rs the rs
     * @param mapper the mapper
     * @return the result set objects
     */
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

    /**
     * Gets the result set maps.
     *
     * @param rs the rs
     * @return the result set maps
     */
    public static List<Map<String, Serializable>> getResultSetMaps(ResultSetWrapper rs) {
        return getResultSetMaps(rs.getResultSet());
    }

    /**
     * Gets the result set maps.
     *
     * @param rs the rs
     * @return the result set maps
     */
    public static List<Map<String, Serializable>> getResultSetMaps(ResultSet rs) {
        try {
            List<Map<String, Serializable>> list = new ArrayList<>();
            while (rs.next()) {
                list.add(getResultSetMap(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set maps.
     *
     * @param rs the rs
     * @param manager the manager
     * @return the result set maps
     */
    public static List<Map<String, Serializable>> getResultSetMaps(ResultSet rs, SqlTypeMappingManager manager) {
        try {
            List<Map<String, Serializable>> list = new ArrayList<>();
            while (rs.next()) {
                list.add(getResultSetMap(rs, manager));
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set array.
     *
     * @param rs the rs
     * @return the result set array
     */
    private static Object[] getResultSetArray(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            Object[] result = new Object[metaData.getColumnCount()];
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                result[i] = JdbcUtils.getResultSetValue(rs, i + 1);
            }
            return result;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set arrays.
     *
     * @param rs the rs
     * @return the result set arrays
     */
    public static List<Object[]> getResultSetArrays(ResultSetWrapper rs) {
        return getResultSetArrays(rs.getResultSet());
    }

    /**
     * Gets the result set arrays.
     *
     * @param rs the rs
     * @return the result set arrays
     */
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
     * Gets the result SQL type.
     *
     * @param call the call
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getCallableSQLType(CallableStatementWrapper call, int index) {
        return getCallableSQLType(call.getCallableStatement(), index);
    }

    /**
     * Gets the result SQL type.
     *
     * @param call the call
     * @param index the index
     * @return the result SQL type
     */
    public static SQLType getCallableSQLType(CallableStatement call, int index) {
        try {
            return JDBCType.valueOf(call.getParameterMetaData().getParameterType(index));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a CallableStatement, using the
     * specified value type.
     *
     * @param <E> the element type
     * @param call the call
     * @param name the name
     * @param requiredType the required type
     * @return the value object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <E> E getCallableParam(CallableStatement call, String name, Class<E> requiredType) {
        if (requiredType == null) {
            return (E) getCallableParam(call, name);
        }
        try {
            Object value = null;
            boolean wasNullCheck = false;
            // Explicitly extract typed value, as far as possible.
            if (String.class.equals(requiredType)) {
                value = call.getString(name);
            } else if (boolean.class.equals(requiredType) || Boolean.class.equals(requiredType)) {
                value = Boolean.valueOf(call.getBoolean(name));
                wasNullCheck = true;
            } else if (byte.class.equals(requiredType) || Byte.class.equals(requiredType)) {
                value = Byte.valueOf(call.getByte(name));
                wasNullCheck = true;
            } else if (short.class.equals(requiredType) || Short.class.equals(requiredType)) {
                value = Short.valueOf(call.getShort(name));
                wasNullCheck = true;
            } else if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
                value = Integer.valueOf(call.getInt(name));
                wasNullCheck = true;
            } else if (long.class.equals(requiredType) || Long.class.equals(requiredType)) {
                value = Long.valueOf(call.getLong(name));
                wasNullCheck = true;
            } else if (float.class.equals(requiredType) || Float.class.equals(requiredType)) {
                value = Float.valueOf(call.getFloat(name));
                wasNullCheck = true;
            } else if (double.class.equals(requiredType) || Double.class.equals(requiredType)
                || Number.class.equals(requiredType)) {
                value = Double.valueOf(call.getDouble(name));
                wasNullCheck = true;
            } else if (byte[].class.equals(requiredType)) {
                value = call.getBytes(name);
            } else if (java.sql.Date.class.equals(requiredType)) {
                value = call.getDate(name);
            } else if (java.sql.Time.class.equals(requiredType)) {
                value = call.getTime(name);
            } else if (java.sql.Timestamp.class.equals(requiredType) || java.util.Date.class.equals(requiredType)) {
                value = call.getTimestamp(name);
            } else if (LocalDate.class.equals(requiredType)) {
                value = call.getDate(name);
                if (value != null) {
                    value = Dates.toLocalDate(new Date(((java.sql.Date) value).getTime()));
                }
            } else if (LocalTime.class.equals(requiredType)) {
                value = call.getTime(name);
                if (value != null) {
                    value = Dates.toLocalTime(new Date(((java.sql.Time) value).getTime()));
                }
            } else if (LocalDateTime.class.equals(requiredType)) {
                value = call.getTimestamp(name);
                if (value != null) {
                    value = Dates.toLocalDateTime(new Date(((java.sql.Timestamp) value).getTime()));
                }
            } else if (BigDecimal.class.equals(requiredType)) {
                value = call.getBigDecimal(name);
            } else if (Blob.class.equals(requiredType)) {
                value = call.getBlob(name);
            } else if (Clob.class.equals(requiredType)) {
                value = call.getClob(name);
            } else if (requiredType.isEnum()) {
                value = Lang.toEnum((Class<Enum>) requiredType, call.getObject(name));
            } else {
                // Some unknown type desired -> rely on getObject.
                value = getCallableParam(call, name);
            }
            // Perform was-null check if demanded (for results that the
            // JDBC driver returns as primitives).
            if (wasNullCheck && value != null && call.wasNull()) {
                value = null;
            }
            return (E) value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a CallableStatement, using the most
     * appropriate value type. The returned value should be a detached value
     * object, not having any ties to the active ResultSet: in particular, it
     * should not be a Blob or Clob object but rather a byte array respectively
     * String representation.
     * <p>
     * Uses the <code>getObject(index)</code> method, but includes additional
     * "hacks" to get around Oracle 10g returning a non-standard object for its
     * TIMESTAMP datatype and a <code>java.sql.Date</code> for DATE columns
     * leaving out the time portion: These columns will explicitly be extracted
     * as standard <code>java.sql.Timestamp</code> object.
     *
     * @param call the call
     * @param name the name
     * @return the value object
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    public static Object getCallableParam(CallableStatement call, String name) {
        try {
            Object obj = call.getObject(name);
            String className = null;
            if (obj != null) {
                className = obj.getClass().getName();
            }
            if (obj instanceof Blob) {
                obj = call.getBytes(name);
            } else if (obj instanceof Clob) {
                obj = call.getString(name);
            } else if (className != null
                && (ORACLE_TIMESTAMP.equals(className) || ORACLE_TIMESTAMPTZ.equals(className))) {
                obj = call.getTimestamp(name);
            }
            // FIXME 后续看看能不能实现
            //            else if (className != null && className.startsWith(ORACLE_DATE)) {
            //                String metaDataClassName = call.getParameterMetaData().getParameterClassName(name);
            //                if (SQL_TIMESTAMP.equals(metaDataClassName)
            //                        || ORACLE_TIMESTAMP.equals(metaDataClassName)) {
            //                    obj = call.getTimestamp(name);
            //                } else {
            //                    obj = call.getDate(name);
            //                }
            //            } else if (obj != null && obj instanceof java.sql.Date) {
            //                ParameterMetaData meta = call.getParameterMetaData();
            //                if (SQL_TIMESTAMP.equals(call.getParameterMetaData().getParameterClassName(name))) {
            //                    obj = call.getTimestamp(name);
            //                }
            //            }
            return obj;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a CallableStatement, using the
     * specified value type.
     *
     * @param <E> the element type
     * @param call the call
     * @param index the index
     * @param requiredType the required type
     * @return the value object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <E> E getCallableParam(CallableStatement call, int index, Class<E> requiredType) {
        if (requiredType == null) {
            return (E) getCallableParam(call, index);
        }
        try {
            Object value = null;
            boolean wasNullCheck = false;
            // Explicitly extract typed value, as far as possible.
            if (String.class.equals(requiredType)) {
                value = call.getString(index);
            } else if (boolean.class.equals(requiredType) || Boolean.class.equals(requiredType)) {
                value = Boolean.valueOf(call.getBoolean(index));
                wasNullCheck = true;
            } else if (byte.class.equals(requiredType) || Byte.class.equals(requiredType)) {
                value = Byte.valueOf(call.getByte(index));
                wasNullCheck = true;
            } else if (short.class.equals(requiredType) || Short.class.equals(requiredType)) {
                value = Short.valueOf(call.getShort(index));
                wasNullCheck = true;
            } else if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
                value = Integer.valueOf(call.getInt(index));
                wasNullCheck = true;
            } else if (long.class.equals(requiredType) || Long.class.equals(requiredType)) {
                value = Long.valueOf(call.getLong(index));
                wasNullCheck = true;
            } else if (float.class.equals(requiredType) || Float.class.equals(requiredType)) {
                value = Float.valueOf(call.getFloat(index));
                wasNullCheck = true;
            } else if (double.class.equals(requiredType) || Double.class.equals(requiredType)
                || Number.class.equals(requiredType)) {
                value = Double.valueOf(call.getDouble(index));
                wasNullCheck = true;
            } else if (byte[].class.equals(requiredType)) {
                value = call.getBytes(index);
            } else if (java.sql.Date.class.equals(requiredType)) {
                value = call.getDate(index);
            } else if (java.sql.Time.class.equals(requiredType)) {
                value = call.getTime(index);
            } else if (java.sql.Timestamp.class.equals(requiredType) || java.util.Date.class.equals(requiredType)) {
                value = call.getTimestamp(index);
            } else if (LocalDate.class.equals(requiredType)) {
                value = call.getDate(index);
                if (value != null) {
                    value = Dates.toLocalDate(new Date(((java.sql.Date) value).getTime()));
                }
            } else if (LocalTime.class.equals(requiredType)) {
                value = call.getTime(index);
                if (value != null) {
                    value = Dates.toLocalTime(new Date(((java.sql.Time) value).getTime()));
                }
            } else if (LocalDateTime.class.equals(requiredType)) {
                value = call.getTimestamp(index);
                if (value != null) {
                    value = Dates.toLocalDateTime(new Date(((java.sql.Timestamp) value).getTime()));
                }
            } else if (BigDecimal.class.equals(requiredType)) {
                value = call.getBigDecimal(index);
            } else if (Blob.class.equals(requiredType)) {
                value = call.getBlob(index);
            } else if (Clob.class.equals(requiredType)) {
                value = call.getClob(index);
            } else if (requiredType.isEnum()) {
                ParameterMetaData meta = call.getParameterMetaData();
                switch (meta.getParameterType(index)) {
                    case Types.TINYINT:
                    case Types.SMALLINT:
                    case Types.INTEGER:
                    case Types.BIGINT:
                        value = Lang.toEnum((Class<Enum>) requiredType, call.getInt(index));
                        break;
                    default:
                        value = Lang.toEnum((Class<Enum>) requiredType, call.getString(index));
                        break;
                }
            } else {
                // Some unknown type desired -> rely on getObject.
                value = getCallableParam(call, index);
            }
            // Perform was-null check if demanded (for results that the
            // JDBC driver returns as primitives).
            if (wasNullCheck && value != null && call.wasNull()) {
                value = null;
            }
            return (E) value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Retrieve a JDBC column value from a CallableStatement, using the most
     * appropriate value type. The returned value should be a detached value
     * object, not having any ties to the active ResultSet: in particular, it
     * should not be a Blob or Clob object but rather a byte array respectively
     * String representation.
     * <p>
     * Uses the <code>getObject(index)</code> method, but includes additional
     * "hacks" to get around Oracle 10g returning a non-standard object for its
     * TIMESTAMP datatype and a <code>java.sql.Date</code> for DATE columns
     * leaving out the time portion: These columns will explicitly be extracted
     * as standard <code>java.sql.Timestamp</code> object.
     *
     * @param call the call
     * @param index the index
     * @return the value object
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    public static Object getCallableParam(CallableStatement call, int index) {
        try {
            Object obj = call.getObject(index);
            String className = null;
            if (obj != null) {
                className = obj.getClass().getName();
            }
            if (obj instanceof Blob) {
                obj = call.getBytes(index);
            } else if (obj instanceof Clob) {
                obj = call.getString(index);
            } else if (className != null
                && (ORACLE_TIMESTAMP.equals(className) || ORACLE_TIMESTAMPTZ.equals(className))) {
                obj = call.getTimestamp(index);
            } else if (className != null && className.startsWith(ORACLE_DATE)) {
                String metaDataClassName = call.getParameterMetaData().getParameterClassName(index);
                if (SQL_TIMESTAMP.equals(metaDataClassName) || ORACLE_TIMESTAMP.equals(metaDataClassName)) {
                    obj = call.getTimestamp(index);
                } else {
                    obj = call.getDate(index);
                }
            } else if (obj instanceof java.sql.Date
                && SQL_TIMESTAMP.equals(call.getParameterMetaData().getParameterClassName(index))) {
                obj = call.getTimestamp(index);
            }
            return obj;
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
     * @param resultSetMetaData the result set meta data
     * @param columnIndex the column index
     * @return the column name to use
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) {
        return lookupColumnName(resultSetMetaData, columnIndex, false);
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
     * @param resultSetMetaData the result set meta data
     * @param columnIndex the column index
     * @param camelCaseWithUnderLine the camel case with under line
     * @return the column name to use
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex,
        boolean camelCaseWithUnderLine) {
        try {
            String name = resultSetMetaData.getColumnLabel(columnIndex);
            if (Lang.isEmpty(name)) {
                name = resultSetMetaData.getColumnName(columnIndex);
                if (camelCaseWithUnderLine) {
                    name = WordUtils.parseToUpperFirst(name.toLowerCase(), '_');
                }
            }
            return name;
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
     * @param resultSetMetaData the result set meta data
     * @param columnIndex the column index
     * @param camelCaseWithSymbol the camel case with symbol
     * @return the column name to use
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex,
        char camelCaseWithSymbol) {
        try {
            String name = resultSetMetaData.getColumnLabel(columnIndex);
            if (Lang.isEmpty(name)) {
                name = resultSetMetaData.getColumnName(columnIndex);
                name = WordUtils.parseToUpperFirst(name.toLowerCase(), camelCaseWithSymbol);
            }
            return name;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    // ****************************************************************************************************************
    //	get value
    // ****************************************************************************************************************

    /**
     * Gets the bool.
     *
     * @param rs the rs
     * @param index the index
     * @return the bool
     */
    public static boolean getBool(ResultSet rs, int index) {
        try {
            return rs.getBoolean(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the boolean.
     *
     * @param rs the rs
     * @param index the index
     * @return the boolean
     */
    public static Boolean getBoolean(ResultSet rs, int index) {
        try {
            boolean value = rs.getBoolean(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte value.
     *
     * @param rs the rs
     * @param index the index
     * @return the byte value
     */
    public static byte getByteValue(ResultSet rs, int index) {
        try {
            return rs.getByte(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte.
     *
     * @param rs the rs
     * @param index the index
     * @return the byte
     */
    public static Byte getByte(ResultSet rs, int index) {
        try {
            byte value = rs.getByte(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short value.
     *
     * @param rs the rs
     * @param index the index
     * @return the short value
     */
    public static short getShortValue(ResultSet rs, int index) {
        try {
            return rs.getShort(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short.
     *
     * @param rs the rs
     * @param index the index
     * @return the short
     */
    public static Short getShort(ResultSet rs, int index) {
        try {
            short value = rs.getShort(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the int.
     *
     * @param rs the rs
     * @param index the index
     * @return the int
     */
    public static int getInt(ResultSet rs, int index) {
        try {
            return rs.getInt(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the integer.
     *
     * @param rs the rs
     * @param index the index
     * @return the integer
     */
    public static Integer getInteger(ResultSet rs, int index) {
        try {
            int value = rs.getInt(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long.
     *
     * @param rs the rs
     * @param index the index
     * @return the long
     */
    public static Long getLong(ResultSet rs, int index) {
        try {
            long value = rs.getLong(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long value.
     *
     * @param rs the rs
     * @param index the index
     * @return the long value
     */
    public static long getLongValue(ResultSet rs, int index) {
        try {
            return rs.getLong(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float.
     *
     * @param rs the rs
     * @param index the index
     * @return the float
     */
    public static Float getFloat(ResultSet rs, int index) {
        try {
            float value = rs.getFloat(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float value.
     *
     * @param rs the rs
     * @param index the index
     * @return the float value
     */
    public static float getFloatValue(ResultSet rs, int index) {
        try {
            return rs.getFloat(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double.
     *
     * @param rs the rs
     * @param index the index
     * @return the double
     */
    public static Double getDouble(ResultSet rs, int index) {
        try {
            double value = rs.getDouble(index);
            if (rs.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double value.
     *
     * @param rs the rs
     * @param index the index
     * @return the double value
     */
    public static double getDoubleValue(ResultSet rs, int index) {
        try {
            return rs.getDouble(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the string.
     *
     * @param rs the rs
     * @param index the index
     * @return the string
     */
    public static String getString(ResultSet rs, int index) {
        try {
            return rs.getString(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param rs the rs
     * @param index the index
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(ResultSet rs, int index) {
        try {
            return rs.getBigDecimal(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big integer.
     *
     * @param rs the rs
     * @param index the index
     * @return the big integer
     */
    public static BigInteger getBigInteger(ResultSet rs, int index) {
        return Lang.ifNotNull(getBigDecimal(rs, index), bd -> bd.toBigIntegerExact());
    }

    /**
     * Gets the date.
     *
     * @param rs the rs
     * @param index the index
     * @return the date
     */
    public static java.sql.Date getDate(ResultSet rs, int index) {
        try {
            return rs.getDate(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param rs the rs
     * @param index the index
     * @return the timestamp
     */
    public static Timestamp getTimestamp(ResultSet rs, int index) {
        try {
            return rs.getTimestamp(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param rs the rs
     * @param index the index
     * @return the time
     */
    public static Time getTime(ResultSet rs, int index) {
        try {
            return rs.getTime(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the local date.
     *
     * @param rs the rs
     * @param index the index
     * @return the local date
     */
    public static LocalDate getLocalDate(ResultSet rs, int index) {
        java.sql.Date value = getDate(rs, index);
        if (value != null) {
            return value.toLocalDate();
        }
        return null;
    }

    /**
     * Gets the local time.
     *
     * @param rs the rs
     * @param index the index
     * @return the local time
     */
    public static LocalTime getLocalTime(ResultSet rs, int index) {
        Time value = getTime(rs, index);
        if (value != null) {
            return value.toLocalTime();
        }
        return null;
    }

    /**
     * Gets the local date time.
     *
     * @param rs the rs
     * @param index the index
     * @return the local date time
     */
    public static LocalDateTime getLocalDateTime(ResultSet rs, int index) {
        Timestamp value = getTimestamp(rs, index);
        if (value != null) {
            return value.toLocalDateTime();
        }
        return null;
    }

    /**
     * Gets the enum.
     *
     * @param <E> the element type
     * @param rs the rs
     * @param index the index
     * @param enumType the enum type
     * @return the local date time
     */
    public static <E extends Enum<E>> E getEnum(ResultSet rs, int index, Class<E> enumType) {
        AssertIllegalArgument.isNotNull(enumType, "enumType");
        if (enumType.isEnum()) {
            try {
                ResultSetMetaData meta = rs.getMetaData();
                switch (meta.getColumnType(index)) {
                    case Types.TINYINT:
                    case Types.SMALLINT:
                    case Types.INTEGER:
                    case Types.BIGINT:
                        return Lang.toEnum(enumType, rs.getInt(index));
                    default:
                        return Lang.toEnum(enumType, rs.getString(index));
                }
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        } else {
            throw new JdbcException(enumType.getName() + " is not enum");
        }
    }

    /**
     * Gets the bytes.
     *
     * @param rs the rs
     * @param index the index
     * @return the bytes
     */
    public static byte[] getBytes(ResultSet rs, int index) {
        try {
            return rs.getBytes(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    // ****************************************************************************************************************

    /**
     * Gets the bool.
     *
     * @param call the call
     * @param index the index
     * @return the bool
     */
    public static boolean getBool(CallableStatement call, int index) {
        try {
            return call.getBoolean(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the boolean.
     *
     * @param call the call
     * @param index the index
     * @return the boolean
     */
    public static Boolean getBoolean(CallableStatement call, int index) {
        try {
            boolean value = call.getBoolean(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte value.
     *
     * @param call the call
     * @param index the index
     * @return the byte value
     */
    public static byte getByteValue(CallableStatement call, int index) {
        try {
            return call.getByte(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the byte.
     *
     * @param call the call
     * @param index the index
     * @return the byte
     */
    public static Byte getByte(CallableStatement call, int index) {
        try {
            byte value = call.getByte(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short value.
     *
     * @param call the call
     * @param index the index
     * @return the short value
     */
    public static short getShortValue(CallableStatement call, int index) {
        try {
            return call.getShort(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the short.
     *
     * @param call the call
     * @param index the index
     * @return the short
     */
    public static Short getShort(CallableStatement call, int index) {
        try {
            short value = call.getShort(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the int.
     *
     * @param call the call
     * @param index the index
     * @return the int
     */
    public static int getInt(CallableStatement call, int index) {
        try {
            return call.getInt(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the integer.
     *
     * @param call the call
     * @param index the index
     * @return the integer
     */
    public static Integer getInteger(CallableStatement call, int index) {
        try {
            int value = call.getInt(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long.
     *
     * @param call the call
     * @param index the index
     * @return the long
     */
    public static Long getLong(CallableStatement call, int index) {
        try {
            long value = call.getLong(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the long value.
     *
     * @param call the call
     * @param index the index
     * @return the long value
     */
    public static long getLongValue(CallableStatement call, int index) {
        try {
            return call.getLong(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float.
     *
     * @param call the call
     * @param index the index
     * @return the float
     */
    public static Float getFloat(CallableStatement call, int index) {
        try {
            float value = call.getFloat(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the float value.
     *
     * @param call the call
     * @param index the index
     * @return the float value
     */
    public static float getFloatValue(CallableStatement call, int index) {
        try {
            return call.getFloat(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double.
     *
     * @param call the call
     * @param index the index
     * @return the double
     */
    public static Double getDouble(CallableStatement call, int index) {
        try {
            double value = call.getDouble(index);
            if (call.wasNull()) {
                return null;
            }
            return value;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the double value.
     *
     * @param call the call
     * @param index the index
     * @return the double value
     */
    public static double getDoubleValue(CallableStatement call, int index) {
        try {
            return call.getDouble(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the string.
     *
     * @param call the call
     * @param index the index
     * @return the string
     */
    public static String getString(CallableStatement call, int index) {
        try {
            return call.getString(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big decimal.
     *
     * @param rs the rs
     * @param index the index
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(CallableStatement rs, int index) {
        try {
            return rs.getBigDecimal(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the big integer.
     *
     * @param rs the rs
     * @param index the index
     * @return the big integer
     */
    public static BigInteger getBigInteger(CallableStatement rs, int index) {
        return Lang.ifNotNull(getBigDecimal(rs, index), bd -> bd.toBigIntegerExact());
    }

    /**
     * Gets the date.
     *
     * @param rs the rs
     * @param index the index
     * @return the date
     */
    public static java.sql.Date getDate(CallableStatement rs, int index) {
        try {
            return rs.getDate(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the timestamp.
     *
     * @param rs the rs
     * @param index the index
     * @return the timestamp
     */
    public static Timestamp getTimestamp(CallableStatement rs, int index) {
        try {
            return rs.getTimestamp(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the time.
     *
     * @param rs the rs
     * @param index the index
     * @return the time
     */
    public static Time getTime(CallableStatement rs, int index) {
        try {
            return rs.getTime(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the local date.
     *
     * @param rs the rs
     * @param index the index
     * @return the local date
     */
    public static LocalDate getLocalDate(CallableStatement rs, int index) {
        java.sql.Date value = getDate(rs, index);
        if (value != null) {
            return value.toLocalDate();
        }
        return null;
    }

    /**
     * Gets the local time.
     *
     * @param rs the rs
     * @param index the index
     * @return the local time
     */
    public static LocalTime getLocalTime(CallableStatement rs, int index) {
        Time value = getTime(rs, index);
        if (value != null) {
            return value.toLocalTime();
        }
        return null;
    }

    /**
     * Gets the local date time.
     *
     * @param rs the rs
     * @param index the index
     * @return the local date time
     */
    public static LocalDateTime getLocalDateTime(CallableStatement rs, int index) {
        Timestamp value = getTimestamp(rs, index);
        if (value != null) {
            return value.toLocalDateTime();
        }
        return null;
    }

    /**
     * Gets the bytes.
     *
     * @param rs the rs
     * @param index the index
     * @return the bytes
     */
    public static byte[] getBytes(CallableStatement rs, int index) {
        try {
            return rs.getBytes(index);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }
}
