
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

	private DataSource dataSource;

	public DataSourceWrapper(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
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
	 * @see javax.sql.DataSource
	 */
	public ConnectionWrapper getConnection(String username, String password)
			 {
		try {
			return new ConnectionWrapper(dataSource.getConnection(username, password));
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	/**
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
	 * @return 返回dataSource（javax.sql.DataSource）
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

}
