
/*
 * All rights Reserved, Designed By zhongj
 * @Title: AutoClosePreparedStatement.java
 * @Description: AutoClosePreparedStatement
 * @author: zhongj
 * @date: 2023-09-18 15:43:18
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.wrapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The Class AbstractCascadedCloseStatement.
 *
 * @author zhongj
 * @param <S> the generic type
 */
public abstract class AbstractCascadedCloseStatement<S extends Statement> implements Statement {

    /** The stat. */
    protected S stat;

    private Set<ResultSet> resultSets = new LinkedHashSet<>();

    /**
     * Instantiates a new result set creatable.
     *
     * @param stat the stat
     */
    protected AbstractCascadedCloseStatement(S stat) {
        super();
        this.stat = stat;
    }

    /**
     * Adds the result set.
     *
     * @param res the res
     * @return the result set
     */
    protected ResultSet addResultSet(ResultSet res) {
        resultSets.add(res);
        return res;
    }

    /**
     * Close result sets.
     *
     * @throws SQLException the SQL exception
     */
    protected void closeResultSets() throws SQLException {
        for (ResultSet resultSet : resultSets) {
            if (!resultSet.isClosed()) {
                resultSet.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws SQLException {
        // close ResultSet self create
        closeResultSets();
        stat.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return stat.unwrap(iface);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return addResultSet(stat.executeQuery(sql));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return stat.isWrapperFor(iface);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate(String sql) throws SQLException {
        return stat.executeUpdate(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxFieldSize() throws SQLException {
        return stat.getMaxFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        stat.setMaxFieldSize(max);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxRows() throws SQLException {
        return stat.getMaxRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxRows(int max) throws SQLException {
        stat.setMaxRows(max);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        stat.setEscapeProcessing(enable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQueryTimeout() throws SQLException {
        return stat.getQueryTimeout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        stat.setQueryTimeout(seconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancel() throws SQLException {
        stat.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return stat.getWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearWarnings() throws SQLException {
        stat.clearWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCursorName(String name) throws SQLException {
        stat.setCursorName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute(String sql) throws SQLException {
        return stat.execute(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet getResultSet() throws SQLException {
        return addResultSet(stat.getResultSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getUpdateCount() throws SQLException {
        return stat.getUpdateCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getMoreResults() throws SQLException {
        return stat.getMoreResults();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFetchDirection(int direction) throws SQLException {
        stat.setFetchDirection(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFetchDirection() throws SQLException {
        return stat.getFetchDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFetchSize(int rows) throws SQLException {
        stat.setFetchSize(rows);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFetchSize() throws SQLException {
        return stat.getFetchSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResultSetConcurrency() throws SQLException {
        return stat.getResultSetConcurrency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResultSetType() throws SQLException {
        return stat.getResultSetType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBatch(String sql) throws SQLException {
        stat.addBatch(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearBatch() throws SQLException {
        stat.clearBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] executeBatch() throws SQLException {
        return stat.executeBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() throws SQLException {
        return stat.getConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return stat.getMoreResults(current);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return stat.getGeneratedKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return stat.executeUpdate(sql, autoGeneratedKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return stat.executeUpdate(sql, columnIndexes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return stat.executeUpdate(sql, columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return stat.execute(sql, autoGeneratedKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return stat.execute(sql, columnIndexes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return stat.execute(sql, columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResultSetHoldability() throws SQLException {
        return stat.getResultSetHoldability();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed() throws SQLException {
        return stat.isClosed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        stat.setPoolable(poolable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPoolable() throws SQLException {
        return stat.isPoolable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeOnCompletion() throws SQLException {
        stat.closeOnCompletion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return stat.isCloseOnCompletion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLargeUpdateCount() throws SQLException {
        return stat.getLargeUpdateCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        stat.setLargeMaxRows(max);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLargeMaxRows() throws SQLException {
        return stat.getLargeMaxRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] executeLargeBatch() throws SQLException {
        return stat.executeLargeBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return stat.executeLargeUpdate(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return stat.executeLargeUpdate(sql, autoGeneratedKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return stat.executeLargeUpdate(sql, columnIndexes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return stat.executeLargeUpdate(sql, columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return stat.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return stat.hashCode();
    }

    // ****************************************************************************************************************
    //	jdk 17
    // ****************************************************************************************************************

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public String enquoteLiteral(String val) throws SQLException {
    //        return stat.enquoteLiteral(val);
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public String enquoteIdentifier(String identifier, boolean alwaysQuote) throws SQLException {
    //        return stat.enquoteIdentifier(identifier, alwaysQuote);
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public boolean isSimpleIdentifier(String identifier) throws SQLException {
    //        return stat.isSimpleIdentifier(identifier);
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public String enquoteNCharLiteral(String val) throws SQLException {
    //        return stat.enquoteNCharLiteral(val);
    //    }

    // ****************************************************************************************************************
    //  jdk 17
    // ****************************************************************************************************************

}
