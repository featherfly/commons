
package cn.featherfly.common.db.wrapper;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import cn.featherfly.common.db.JdbcException;

/**
 * <p>
 * javax.sql.DataSource的包装类，包装所有检查异常（SQLEception）为非检查异常（JdbcException）
 * </p>
 *
 * @author zhongj
 */
public class DataSourceWrapper {

    /** The data source. */
    private DataSource dataSource;

    /**
     * Instantiates a new data source wrapper.
     *
     * @param dataSource the data source
     */
    public DataSourceWrapper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @see javax.sql.DataSource
     */
    public ConnectionWrapper getConnection() {
        try {
            return new ConnectionWrapper(dataSource.getConnection());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the connection.
     *
     * @param username the username
     * @param password the password
     * @return the connection
     * @see javax.sql.DataSource
     */
    public ConnectionWrapper getConnection(String username, String password) {
        try {
            return new ConnectionWrapper(dataSource.getConnection(username, password));
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the log writer.
     *
     * @return the log writer
     * @see javax.sql.DataSource
     */
    public PrintWriter getLogWriter() {
        try {
            return dataSource.getLogWriter();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the login timeout.
     *
     * @return the login timeout
     * @see javax.sql.DataSource
     */
    public int getLoginTimeout() {
        try {
            return dataSource.getLoginTimeout();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the log writer.
     *
     * @param out the new log writer
     * @see javax.sql.DataSource
     */
    public void setLogWriter(PrintWriter out) {
        try {
            dataSource.setLogWriter(out);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Sets the login timeout.
     *
     * @param seconds the new login timeout
     * @see javax.sql.DataSource
     */
    public void setLoginTimeout(int seconds) {
        try {
            dataSource.setLoginTimeout(seconds);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for
     * @see javax.sql.DataSource
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return dataSource.isWrapperFor(iface);
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
     * @see javax.sql.DataSource
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return dataSource.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the data source.
     *
     * @return 返回dataSource（javax.sql.DataSource）
     */
    public DataSource getDataSource() {
        return dataSource;
    }

}
