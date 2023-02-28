
package cn.featherfly.common.db;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.db.wrapper.ResultSetWrapper;
import cn.featherfly.common.db.wrapper.StatementWrapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.mapping.RowMapper;

/**
 * <p>
 * SqlExecutor
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlExecutor {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The Constant END_SQL_SIGN. */
    private static final String END_SQL_SIGN = ";";

    /** The data source. */
    private DataSource dataSource;

    private char namedParamStartSymbol = SqlUtils.PARAM_NAME_START_SYMBOL;

    private Character namedParamEndSymbol;

    /**
     * Instantiates a new sql executor.
     *
     * @param dataSource the data source
     */
    public SqlExecutor(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /**
     * read sql from file with UTF-8 and execute.
     *
     * @param sqlFile the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void execute(SqlFile sqlFile) throws IOException {
        execute(Lang.toArray(sqlFile.getSqlList(), String.class));
    }

    /**
     * read sql from file with UTF-8 and execute.
     *
     * @param sqlFile the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void execute(File sqlFile) throws IOException {
        execute(SqlFile.read(sqlFile));
    }

    //    /**
    //     * read sql from file with assgin encoding and execute.
    //     *
    //     * @param sqlFile  the sql file
    //     * @param encoding the encoding
    //     * @throws IOException Signals that an I/O exception has occurred.
    //     */
    //    public void execute(File sqlFile, String encoding) throws IOException {
    //        AssertIllegalArgument.isExists(sqlFile, "sqlFile");
    //        String content = org.apache.commons.io.FileUtils.readFileToString(sqlFile, encoding);
    //        String[] sqls = content.split(END_SQL_SIGN);
    //        execute(sqls);
    //    }

    /**
     * read sql from file with assgin encoding and execute.
     *
     * @param sqlFile sqlFile
     * @param charset charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void execute(File sqlFile, Charset charset) throws IOException {
        AssertIllegalArgument.isExists(sqlFile, "sqlFile");
        execute(SqlFile.read(sqlFile, charset));
    }

    /**
     * read sql from file with assgin encoding and execute.
     *
     * @param sqlResource sqlResource
     * @param charset     charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void execute(InputStream sqlResource, Charset charset) throws IOException {
        AssertIllegalArgument.isNotNull(sqlResource, "sqlFile");
        String content = IOUtils.toString(sqlResource, charset);
        String[] sqls = content.split(END_SQL_SIGN);
        execute(sqls);
    }

    /**
     * read sql from string sqlContent.
     *
     * @param sqlContent the sql content
     */
    public void execute(String sqlContent) {
        String[] sqls = sqlContent.split(END_SQL_SIGN);
        execute(sqls);
    }

    /**
     * execute sqls.
     *
     * @param sqls the sqls
     */
    public void execute(String[] sqls) {
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                StatementWrapper statement = connection.createStatement()) {
            for (String sql : sqls) {
                sql = sql.trim();
                if (Lang.isNotEmpty(sql)) {
                    logger.debug("add sql -> " + sql);
                    statement.addBatch(sql);
                }
            }
            statement.executeBatch();
        }
    }

    /**
     * sql execute.
     *
     * @param sql    sql
     * @param params params
     * @return exuecute success amount
     */
    public int execute(String sql, Object... params) {
        if (Lang.isNotEmpty(sql)) {
            sql = sql.trim();
            try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                    PreparedStatementWrapper prep = connection.prepareStatement(sql)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute sql -> {} , params -> {}", sql, ArrayUtils.toString(params));
                }
                JdbcUtils.setParameters(prep, params);
                return prep.executeUpdate();
            }
        }
        return Chars.ZERO;
    }

    /**
     * sql execute.
     *
     * @param sql    named param sql.
     * @param params map params
     * @return exuecute success amount
     */
    public int execute(String sql, Map<String, Object> params) {
        return execute(SqlUtils.convertNamedParamSql(sql, params, namedParamStartSymbol, namedParamEndSymbol));
    }

    /**
     * sql execute.
     *
     * @param execution execution
     * @return exuecute success amount
     */
    public int execute(Execution execution) {
        if (execution != null) {
            return execute(execution.getExecution(), execution.getParams());
        }
        return Chars.ZERO;
    }

    /**
     * sql execute.
     *
     * @param executions executions
     * @return exuecute success amount
     */
    public int execute(Execution... executions) {
        int result = 0;
        if (executions != null) {
            for (Execution execution : executions) {
                result += execute(execution);
            }
        }
        return result;
    }

    /**
     * sql query.
     *
     * @param <E>    the element type
     * @param query  query
     * @param mapper the mapper
     * @return query result list
     */
    public <E> List<E> query(Execution query, RowMapper<E> mapper) {
        if (query != null) {
            return query(query.getExecution(), mapper, query.getParams());
        }
        return new ArrayList<>();
    }

    /**
     * sql query.
     *
     * @param <E>    the element type
     * @param sql    sql
     * @param mapper the mapper
     * @param params params
     * @return query result list
     */
    public <E> List<E> query(String sql, RowMapper<E> mapper, Map<String, Object> params) {
        return query(SqlUtils.convertNamedParamSql(sql, params, namedParamStartSymbol, namedParamEndSymbol), mapper);
    }

    /**
     * sql query.
     *
     * @param <E>    the element type
     * @param sql    sql
     * @param mapper the mapper
     * @param params params
     * @return query result list
     */
    public <E> List<E> query(String sql, RowMapper<E> mapper, Object... params) {
        if (Lang.isNotEmpty(sql)) {
            sql = sql.trim();
            try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                    PreparedStatementWrapper prep = connection.prepareStatement(sql)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute sql -> {} , params -> {}", sql, ArrayUtils.toString(params));
                }
                JdbcUtils.setParameters(prep, params);
                ResultSetWrapper rs = prep.executeQuery();
                return JdbcUtils.getResultSetObjects(rs, mapper);
            }
        }
        return new ArrayList<>();
    }

    /**
     * sql query.
     *
     * @param query query
     * @return query result list
     */
    public List<Map<String, Object>> query(Execution query) {
        if (query != null) {
            return query(query.getExecution(), query.getParams());
        }
        return new ArrayList<>();
    }

    /**
     * Query.
     *
     * @param sql    the sql
     * @param params the params
     * @return the list
     */
    public List<Map<String, Object>> query(String sql, Map<String, Object> params) {
        return query(SqlUtils.convertNamedParamSql(sql, params, namedParamStartSymbol, namedParamEndSymbol));
    }

    /**
     * sql query.
     *
     * @param sql    sql
     * @param params params
     * @return query result list
     */
    public List<Map<String, Object>> query(String sql, Object... params) {
        if (Lang.isNotEmpty(sql)) {
            sql = sql.trim();
            try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                    PreparedStatementWrapper prep = connection.prepareStatement(sql)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute sql -> {} , params -> {}", sql, ArrayUtils.toString(params));
                }
                JdbcUtils.setParameters(prep, params);
                ResultSetWrapper rs = prep.executeQuery();
                return JdbcUtils.getResultSetMaps(rs);
            }
        }
        return new ArrayList<>();
    }

    /**
     * sql query.
     *
     * @param query query
     * @return query result list
     */
    public List<Object[]> queryListArray(Execution query) {
        if (query != null) {
            return queryListArray(query.getExecution(), query.getParams());
        }
        return new ArrayList<>();
    }

    /**
     * Query list array.
     *
     * @param sql    the sql
     * @param params the params
     * @return the list
     */
    public List<Object[]> queryListArray(String sql, Map<String, Object> params) {
        return queryListArray(SqlUtils.convertNamedParamSql(sql, params, namedParamStartSymbol, namedParamEndSymbol));
    }

    /**
     * sql query.
     *
     * @param sql    sql
     * @param params params
     * @return query result list
     */
    public List<Object[]> queryListArray(String sql, Object... params) {
        if (Lang.isNotEmpty(sql)) {
            sql = sql.trim();
            try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                    PreparedStatementWrapper prep = connection.prepareStatement(sql)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute sql -> {} , params -> {}", sql, ArrayUtils.toString(params));
                }
                JdbcUtils.setParameters(prep, params);
                ResultSetWrapper rs = prep.executeQuery();
                return JdbcUtils.getResultSetArrays(rs);
            }
        }
        return new ArrayList<>();
    }

    /**
     * 返回namedParamStartSymbol.
     *
     * @return namedParamStartSymbol
     */
    public char getNamedParamStartSymbol() {
        return namedParamStartSymbol;
    }

    /**
     * 设置namedParamStartSymbol.
     *
     * @param namedParamStartSymbol namedParamStartSymbol
     */
    public void setNamedParamStartSymbol(char namedParamStartSymbol) {
        this.namedParamStartSymbol = namedParamStartSymbol;
    }

    /**
     * 返回namedParamEndSymbol.
     *
     * @return namedParamEndSymbol
     */
    public Character getNamedParamEndSymbol() {
        return namedParamEndSymbol;
    }

    /**
     * 设置namedParamEndSymbol.
     *
     * @param namedParamEndSymbol namedParamEndSymbol
     */
    public void setNamedParamEndSymbol(Character namedParamEndSymbol) {
        this.namedParamEndSymbol = namedParamEndSymbol;
    }

    /**
     * 返回dataSource
     *
     * @return dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
