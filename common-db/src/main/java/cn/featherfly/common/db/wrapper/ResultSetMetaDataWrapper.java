package cn.featherfly.common.db.wrapper;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import cn.featherfly.common.db.JdbcException;

/**
 * ResultSetMetaDataWrapper.
 *
 * @author zhongj
 */
public class ResultSetMetaDataWrapper {

    private ResultSetMetaData resultSetMetaData;

    /**
     * Instantiates a new result set meta data wrapper.
     *
     * @param resultSetMetaData the result set meta data
     */
    public ResultSetMetaDataWrapper(ResultSetMetaData resultSetMetaData) {
        super();
        this.resultSetMetaData = resultSetMetaData;
    }

    /**
     * Unwrap.
     *
     * @param <T>   the generic type
     * @param iface the iface
     * @return the t @ the SQL exception
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    public <T> T unwrap(Class<T> iface) {
        try {
            return resultSetMetaData.unwrap(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column count.
     *
     * @return the column count @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnCount()
     */
    public int getColumnCount() {
        try {
            return resultSetMetaData.getColumnCount();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is auto increment.
     *
     * @param column the column
     * @return true, if is auto increment @ the SQL exception
     * @see java.sql.ResultSetMetaData#isAutoIncrement(int)
     */
    public boolean isAutoIncrement(int column) {
        try {
            return resultSetMetaData.isAutoIncrement(column);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is case sensitive.
     *
     * @param column the column
     * @return true, if is case sensitive @ the SQL exception
     * @see java.sql.ResultSetMetaData#isCaseSensitive(int)
     */
    public boolean isCaseSensitive(int column) {
        try {
            return resultSetMetaData.isCaseSensitive(column);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is searchable.
     *
     * @param column the column
     * @return true, if is searchable @ the SQL exception
     * @see java.sql.ResultSetMetaData#isSearchable(int)
     */
    public boolean isSearchable(int column) {
        try {
            return resultSetMetaData.isSearchable(column);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is wrapper for.
     *
     * @param iface the iface
     * @return true, if is wrapper for @ the SQL exception
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    public boolean isWrapperFor(Class<?> iface) {
        try {
            return resultSetMetaData.isWrapperFor(iface);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is currency.
     *
     * @param column the column
     * @return true, if is currency @ the SQL exception
     * @see java.sql.ResultSetMetaData#isCurrency(int)
     */
    public boolean isCurrency(int column) {
        try {
            return resultSetMetaData.isCurrency(column);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is nullable.
     *
     * @param column the column
     * @return the int @ the SQL exception
     * @see java.sql.ResultSetMetaData#isNullable(int)
     */
    public int isNullable(int column) {
        try {
            return resultSetMetaData.isNullable(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is signed.
     *
     * @param column the column
     * @return true, if is signed @ the SQL exception
     * @see java.sql.ResultSetMetaData#isSigned(int)
     */
    public boolean isSigned(int column) {
        try {
            return resultSetMetaData.isSigned(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column display size.
     *
     * @param column the column
     * @return the column display size @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnDisplaySize(int)
     */
    public int getColumnDisplaySize(int column) {
        try {
            return resultSetMetaData.getColumnDisplaySize(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column label.
     *
     * @param column the column
     * @return the column label @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnLabel(int)
     */
    public String getColumnLabel(int column) {
        try {
            return resultSetMetaData.getColumnLabel(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column name.
     *
     * @param column the column
     * @return the column name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnName(int)
     */
    public String getColumnName(int column) {
        try {
            return resultSetMetaData.getColumnName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the schema name.
     *
     * @param column the column
     * @return the schema name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getSchemaName(int)
     */
    public String getSchemaName(int column) {
        try {
            return resultSetMetaData.getSchemaName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the precision.
     *
     * @param column the column
     * @return the precision @ the SQL exception
     * @see java.sql.ResultSetMetaData#getPrecision(int)
     */
    public int getPrecision(int column) {
        try {
            return resultSetMetaData.getPrecision(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the scale.
     *
     * @param column the column
     * @return the scale @ the SQL exception
     * @see java.sql.ResultSetMetaData#getScale(int)
     */
    public int getScale(int column) {
        try {
            return resultSetMetaData.getScale(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the table name.
     *
     * @param column the column
     * @return the table name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getTableName(int)
     */
    public String getTableName(int column) {
        try {
            return resultSetMetaData.getTableName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the catalog name.
     *
     * @param column the column
     * @return the catalog name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getCatalogName(int)
     */
    public String getCatalogName(int column) {
        try {
            return resultSetMetaData.getCatalogName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column type.
     *
     * @param column the column
     * @return the column type @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnType(int)
     */
    public int getColumnType(int column) {
        try {
            return resultSetMetaData.getColumnType(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column type name.
     *
     * @param column the column
     * @return the column type name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnTypeName(int)
     */
    public String getColumnTypeName(int column) {
        try {
            return resultSetMetaData.getColumnTypeName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is read only.
     *
     * @param column the column
     * @return true, if is read only @ the SQL exception
     * @see java.sql.ResultSetMetaData#isReadOnly(int)
     */
    public boolean isReadOnly(int column) {
        try {
            return resultSetMetaData.isReadOnly(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is writable.
     *
     * @param column the column
     * @return true, if is writable @ the SQL exception
     * @see java.sql.ResultSetMetaData#isWritable(int)
     */
    public boolean isWritable(int column) {
        try {
            return resultSetMetaData.isWritable(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Checks if is definitely writable.
     *
     * @param column the column
     * @return true, if is definitely writable @ the SQL exception
     * @see java.sql.ResultSetMetaData#isDefinitelyWritable(int)
     */
    public boolean isDefinitelyWritable(int column) {
        try {
            return resultSetMetaData.isDefinitelyWritable(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * Gets the column class name.
     *
     * @param column the column
     * @return the column class name @ the SQL exception
     * @see java.sql.ResultSetMetaData#getColumnClassName(int)
     */
    public String getColumnClassName(int column) {
        try {
            return resultSetMetaData.getColumnClassName(column);

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * get resultSetMetaData value.
     *
     * @return resultSetMetaData
     */
    public ResultSetMetaData getResultSetMetaData() {
        return resultSetMetaData;
    }
}
