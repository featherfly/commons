
package cn.featherfly.common.db.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * java.sql.Statement的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class StatementWrapper implements AutoCloseable {

    /** The statement. */
    private Statement statement;

    /** The connection wrapper. */
    private ConnectionWrapper connectionWrapper;

    /**
     * Instantiates a new statement wrapper.
     *
     * @param statement         the statement
     * @param connectionWrapper the connection wrapper
     */
    public StatementWrapper(Statement statement, ConnectionWrapper connectionWrapper) {
        this.statement = statement;
        this.connectionWrapper = connectionWrapper;
    }

    /**
     * Adds the batch.
     *
     * @param sql the sql
     * @see java.sql.Statement#addBatch(String)
     */
    public void addBatch(String sql) {
        try {
            statement.addBatch(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Cancel.
     *
     * @see java.sql.Statement#cancel()
     */
    public void cancel() {
        try {
            statement.cancel();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Clear batch.
     *
     * @see java.sql.Statement#clearBatch()
     */
    public void clearBatch() {
        try {
            statement.clearBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Clear warnings.
     *
     * @see java.sql.Statement#clearWarnings()
     */
    public void clearWarnings() {
        try {
            statement.clearWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Close.
     *
     * @see java.sql.Statement#close()
     */
    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Execute.
     *
     * @param sql the sql
     * @return true, if successful
     * @see java.sql.Statement
     */
    public boolean execute(String sql) {
        try {
            return statement.execute(sql);
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
     * @see java.sql.Statement
     */
    public boolean execute(String sql, int autoGeneratedKeys) {
        try {
            return statement.execute(sql, autoGeneratedKeys);
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
     * @see java.sql.Statement
     */
    public boolean execute(String sql, int[] columnIndexes) {
        try {
            return statement.execute(sql, columnIndexes);
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
     * @see java.sql.Statement
     */
    public boolean execute(String sql, String[] columnNames) {
        try {
            return statement.execute(sql, columnNames);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Execute batch.
     *
     * @return the int[]
     * @see java.sql.Statement
     */
    public int[] executeBatch() {
        try {
            return statement.executeBatch();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Execute query.
     *
     * @param sql the sql
     * @return the result set
     * @see java.sql.Statement
     */
    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Execute update.
     *
     * @param sql the sql
     * @return the int
     * @see java.sql.Statement
     */
    public int executeUpdate(String sql) {
        try {
            return statement.executeUpdate(sql);
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
     * @see java.sql.Statement
     */
    public int executeUpdate(String sql, int autoGeneratedKeys) {
        try {
            return statement.executeUpdate(sql, autoGeneratedKeys);
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
     * @see java.sql.Statement
     */
    public int executeUpdate(String sql, int[] columnIndexes) {
        try {
            return statement.executeUpdate(sql, columnIndexes);
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
     * @see java.sql.Statement
     */
    public int executeUpdate(String sql, String[] columnNames) {
        try {
            return statement.executeUpdate(sql, columnNames);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @see java.sql.Statement
     */
    public ConnectionWrapper getConnection() {
        return connectionWrapper;
    }

    /**
     * Gets the fetch direction.
     *
     * @return the fetch direction
     * @see java.sql.Statement
     */
    public int getFetchDirection() {
        try {
            return statement.getFetchDirection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the fetch size.
     *
     * @return the fetch size
     * @see java.sql.Statement
     */
    public int getFetchSize() {
        try {
            return statement.getFetchSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the generated keys.
     *
     * @return the generated keys
     * @see java.sql.Statement
     */
    public ResultSet getGeneratedKeys() {
        try {
            return statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the max field size.
     *
     * @return the max field size
     * @see java.sql.Statement
     */
    public int getMaxFieldSize() {
        try {
            return statement.getMaxFieldSize();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the max rows.
     *
     * @return the max rows
     * @see java.sql.Statement
     */
    public int getMaxRows() {
        try {
            return statement.getMaxRows();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the more results.
     *
     * @return the more results
     * @see java.sql.Statement
     */
    public boolean getMoreResults() {
        try {
            return statement.getMoreResults();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the more results.
     *
     * @param current the current
     * @return the more results
     * @see java.sql.Statement
     */
    public boolean getMoreResults(int current) {
        try {
            return statement.getMoreResults(current);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the query timeout.
     *
     * @return the query timeout
     * @see java.sql.Statement
     */
    public int getQueryTimeout() {
        try {
            return statement.getQueryTimeout();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set.
     *
     * @return the result set
     * @see java.sql.Statement
     */
    public ResultSet getResultSet() {
        try {
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set concurrency.
     *
     * @return the result set concurrency
     * @see java.sql.Statement
     */
    public int getResultSetConcurrency() {
        try {
            return statement.getResultSetConcurrency();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set holdability.
     *
     * @return the result set holdability
     * @see java.sql.Statement
     */
    public int getResultSetHoldability() {
        try {
            return statement.getResultSetHoldability();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the result set type.
     *
     * @return the result set type
     * @see java.sql.Statement
     */
    public int getResultSetType() {
        try {
            return statement.getResultSetType();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the update count.
     *
     * @return the update count
     * @see java.sql.Statement
     */
    public int getUpdateCount() {
        try {
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the warnings.
     *
     * @return the warnings
     * @see java.sql.Statement
     */
    public SQLWarning getWarnings() {
        try {
            return statement.getWarnings();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is closed.
     *
     * @return true, if is closed
     * @see java.sql.Statement
     */
    public boolean isClosed() {
        try {
            return statement.isClosed();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is poolable.
     *
     * @return true, if is poolable
     * @see java.sql.Statement
     */
    public boolean isPoolable() {
        try {
            return statement.isPoolable();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the cursor name.
     *
     * @param name the new cursor name
     * @see java.sql.Statement
     */
    public void setCursorName(String name) {
        try {
            statement.setCursorName(name);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the escape processing.
     *
     * @param enable the new escape processing
     * @see java.sql.Statement
     */
    public void setEscapeProcessing(boolean enable) {
        try {
            statement.setEscapeProcessing(enable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the fetch direction.
     *
     * @param direction the new fetch direction
     * @see java.sql.Statement
     */
    public void setFetchDirection(int direction) {
        try {
            statement.setFetchDirection(direction);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the fetch size.
     *
     * @param rows the new fetch size
     * @see java.sql.Statement
     */
    public void setFetchSize(int rows) {
        try {
            statement.setFetchSize(rows);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the max field size.
     *
     * @param max the new max field size
     * @see java.sql.Statement
     */
    public void setMaxFieldSize(int max) {
        try {
            statement.setMaxFieldSize(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the max rows.
     *
     * @param max the new max rows
     * @see java.sql.Statement
     */
    public void setMaxRows(int max) {
        try {
            statement.setMaxRows(max);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the poolable.
     *
     * @param poolable the new poolable
     * @see java.sql.Statement
     */
    public void setPoolable(boolean poolable) {
        try {
            statement.setPoolable(poolable);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the query timeout.
     *
     * @param seconds the new query timeout
     * @see java.sql.Statement
     */
    public void setQueryTimeout(int seconds) {
        try {
            statement.setQueryTimeout(seconds);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for
     * @see java.sql.Statement
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return statement.isWrapperFor(iface);
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
     * @see java.sql.Statement
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return statement.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the statement.
     *
     * @return 返回statement（java.sql.Statement）
     */
    public Statement getStatement() {
        return statement;
    }
}
