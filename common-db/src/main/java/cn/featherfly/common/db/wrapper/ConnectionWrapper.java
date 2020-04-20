
package cn.featherfly.common.db.wrapper;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * java.sql.Connection的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class ConnectionWrapper implements AutoCloseable {

    /** The connection. */
    private Connection connection;

    /**
     * Instantiates a new connection wrapper.
     *
     * @param connection the connection
     */
    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    /**
     * Clear warnings.
     *
     * @see java.sql.Connection
     */
    public void clearWarnings() {
        try {
            connection.clearWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Close.
     *
     * @see java.sql.Connection
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Commit.
     *
     * @see java.sql.Connection
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the array of.
     *
     * @param typeName the type name
     * @param elements the elements
     * @return the array
     * @throws SQLException the SQL exception
     * @see java.sql.Connection
     */
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        try {
            return connection.createArrayOf(typeName, elements);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the blob.
     *
     * @return the blob
     * @see java.sql.Connection
     */
    public Blob createBlob() {
        try {
            return connection.createBlob();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the clob.
     *
     * @return the clob
     * @see java.sql.Connection
     */
    public Clob createClob() {
        try {
            return connection.createClob();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the N clob.
     *
     * @return the n clob
     * @see java.sql.Connection
     */
    public NClob createNClob() {
        try {
            return connection.createNClob();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the SQLXML.
     *
     * @return the sqlxml
     * @see java.sql.Connection
     */
    public SQLXML createSQLXML() {
        try {
            return connection.createSQLXML();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the statement.
     *
     * @return the statement wrapper
     * @see java.sql.Connection
     */
    public StatementWrapper createStatement() {
        try {
            return new StatementWrapper(connection.createStatement(), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the statement.
     *
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the statement wrapper
     * @throws SQLException the SQL exception
     * @see java.sql.Connection
     */
    public StatementWrapper createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return new StatementWrapper(connection.createStatement(resultSetType, resultSetConcurrency), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the statement.
     *
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the statement wrapper
     * @throws SQLException the SQL exception
     * @see java.sql.Connection
     */
    public StatementWrapper createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        try {
            return new StatementWrapper(
                    connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Creates the struct.
     *
     * @param typeName   the type name
     * @param attributes the attributes
     * @return the struct
     * @throws SQLException the SQL exception
     * @see java.sql.Connection
     */
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        try {
            return connection.createStruct(typeName, attributes);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the auto commit.
     *
     * @return the auto commit
     * @see java.sql.Connection
     */
    public boolean getAutoCommit() {
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the catalog.
     *
     * @return the catalog
     * @see java.sql.Connection
     */
    public String getCatalog() {
        try {
            return connection.getCatalog();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the client info.
     *
     * @return the client info
     * @see java.sql.Connection
     */
    public Properties getClientInfo() {
        try {
            return connection.getClientInfo();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the client info.
     *
     * @param name the name
     * @return the client info
     * @see java.sql.Connection
     */
    public String getClientInfo(String name) {
        try {
            return connection.getClientInfo(name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the holdability.
     *
     * @return the holdability
     * @see java.sql.Connection
     */
    public int getHoldability() {
        try {
            return connection.getHoldability();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the meta data.
     *
     * @return the meta data
     * @see java.sql.Connection
     */
    public DatabaseMetaData getMetaData() {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the transaction isolation.
     *
     * @return the transaction isolation
     * @see java.sql.Connection
     */
    public int getTransactionIsolation() {
        try {
            return connection.getTransactionIsolation();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the type map.
     *
     * @return the type map
     * @see java.sql.Connection
     */
    public Map<String, Class<?>> getTypeMap() {
        try {
            return connection.getTypeMap();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the warnings.
     *
     * @return the warnings
     * @see java.sql.Connection
     */
    public SQLWarning getWarnings() {
        try {
            return connection.getWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is closed.
     *
     * @return true, if is closed
     * @see java.sql.Connection
     */
    public boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is read only.
     *
     * @return true, if is read only
     * @see java.sql.Connection
     */
    public boolean isReadOnly() {
        try {
            return connection.isReadOnly();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is valid.
     *
     * @param timeout the timeout
     * @return true, if is valid
     * @see java.sql.Connection
     */
    public boolean isValid(int timeout) {
        try {
            return connection.isValid(timeout);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Native SQL.
     *
     * @param sql the sql
     * @return the string
     * @see java.sql.Connection
     */
    public String nativeSQL(String sql) {
        try {
            return connection.nativeSQL(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare call.
     *
     * @param sql the sql
     * @return the callable statement wrapper
     * @see java.sql.Connection
     */
    public CallableStatementWrapper prepareCall(String sql) {
        try {
            return new CallableStatementWrapper(connection.prepareCall(sql), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare call.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the callable statement wrapper
     * @see java.sql.Connection
     */
    public CallableStatementWrapper prepareCall(String sql, int resultSetType, int resultSetConcurrency) {
        try {
            return new CallableStatementWrapper(connection.prepareCall(sql, resultSetType, resultSetConcurrency), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare call.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the callable statement wrapper
     * @see java.sql.Connection
     */
    public CallableStatementWrapper prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) {
        try {
            return new CallableStatementWrapper(
                    connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql the sql
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql) {
        try {
            return new PreparedStatementWrapper(connection.prepareStatement(sql), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql               the sql
     * @param autoGeneratedKeys the auto generated keys
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql, int autoGeneratedKeys) {
        try {
            return new PreparedStatementWrapper(connection.prepareStatement(sql, autoGeneratedKeys), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql           the sql
     * @param columnIndexes the column indexes
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql, int[] columnIndexes) {
        try {
            return new PreparedStatementWrapper(connection.prepareStatement(sql, columnIndexes), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql         the sql
     * @param columnNames the column names
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql, String[] columnNames) {
        try {
            return new PreparedStatementWrapper(connection.prepareStatement(sql, columnNames), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql, int resultSetType, int resultSetConcurrency) {
        try {
            return new PreparedStatementWrapper(connection.prepareStatement(sql, resultSetType, resultSetConcurrency),
                    this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Prepare statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the prepared statement wrapper
     * @see java.sql.Connection
     */
    public PreparedStatementWrapper prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) {
        try {
            return new PreparedStatementWrapper(
                    connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability), this);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Release savepoint.
     *
     * @param savepoint the savepoint
     * @see java.sql.Connection
     */
    public void releaseSavepoint(Savepoint savepoint) {
        try {
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Rollback.
     *
     * @see java.sql.Connection
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Rollback.
     *
     * @param savepoint the savepoint
     * @see java.sql.Connection
     */
    public void rollback(Savepoint savepoint) {
        try {
            connection.rollback(savepoint);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the auto commit.
     *
     * @param autoCommit the new auto commit
     * @see java.sql.Connection
     */
    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the catalog.
     *
     * @param catalog the new catalog
     * @see java.sql.Connection
     */
    public void setCatalog(String catalog) {
        try {
            connection.setCatalog(catalog);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the client info.
     *
     * @param properties the new client info
     * @see java.sql.Connection
     */
    public void setClientInfo(Properties properties) {
        try {
            connection.setClientInfo(properties);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the client info.
     *
     * @param name  the name
     * @param value the value
     * @throws SQLClientInfoException the SQL client info exception
     * @see java.sql.Connection
     */
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        try {
            connection.setClientInfo(name, value);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the holdability.
     *
     * @param holdability the new holdability
     * @see java.sql.Connection
     */
    public void setHoldability(int holdability) {
        try {
            connection.setHoldability(holdability);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the read only.
     *
     * @param readOnly the new read only
     * @see java.sql.Connection
     */
    public void setReadOnly(boolean readOnly) {
        try {
            connection.setReadOnly(readOnly);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the savepoint.
     *
     * @return the savepoint
     * @see java.sql.Connection
     */

    public Savepoint setSavepoint() {
        try {
            return connection.setSavepoint();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the savepoint.
     *
     * @param name the name
     * @return the savepoint
     * @see java.sql.Connection
     */
    public Savepoint setSavepoint(String name) {
        try {
            return connection.setSavepoint(name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the transaction isolation.
     *
     * @param level the new transaction isolation
     * @see java.sql.Connection
     */
    public void setTransactionIsolation(int level) {
        try {
            connection.setTransactionIsolation(level);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the type map.
     *
     * @param map the map
     * @see java.sql.Connection
     */
    public void setTypeMap(Map<String, Class<?>> map) {
        try {
            connection.setTypeMap(map);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for
     * @see java.sql.Connection
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return connection.isWrapperFor(iface);
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
     * @see java.sql.Connection
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return connection.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the connection.
     *
     * @return 返回connection（java.sql.Connection）
     */
    public Connection getConnection() {
        return connection;
    }
}
