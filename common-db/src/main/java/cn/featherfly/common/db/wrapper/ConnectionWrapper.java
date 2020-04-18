
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

    private Connection connection;

    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    /**
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
     * @return 返回connection（java.sql.Connection）
     */
    public ConnectionWrapper getConnection() {
        return new ConnectionWrapper(connection);
    }
}
