
package cn.featherfly.common.db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.db.wrapper.PreparedStatementWrapper;
import cn.featherfly.common.db.wrapper.ResultSetWrapper;
import cn.featherfly.common.db.wrapper.StatementWrapper;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.Query;

/**
 * <p>
 * SqlExecutor
 * </p>
 *
 * @author zhongj
 */
public class SqlExecutor {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String END_SQL_SIGN = ";";

    private DataSource dataSource;

    /**
     * @param dataSource
     */
    public SqlExecutor(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /**
     * read sql from file with UTF-8 and execute
     *
     * @param sqlFile
     * @throws IOException
     */
    public void execute(File sqlFile) throws IOException {
        execute(sqlFile, StandardCharsets.UTF_8);
    }

    /**
     * read sql from file with assgin encoding and execute
     *
     * @param sqlFile
     * @param encoding
     * @throws IOException
     */
    public void execute(File sqlFile, String encoding) throws IOException {
        AssertIllegalArgument.isExists(sqlFile, "sqlFile");
        String content = org.apache.commons.io.FileUtils.readFileToString(sqlFile, encoding);
        String[] sqls = content.split(END_SQL_SIGN);
        execute(sqls);
    }

    /**
     * read sql from file with assgin encoding and execute
     *
     * @param sqlFile
     * @param encoding
     * @throws IOException
     */
    public void execute(File sqlFile, Charset charset) throws IOException {
        AssertIllegalArgument.isExists(sqlFile, "sqlFile");
        String content = org.apache.commons.io.FileUtils.readFileToString(sqlFile, charset);
        String[] sqls = content.split(END_SQL_SIGN);
        execute(sqls);
    }

    /**
     * read sql from string sqlContent
     *
     * @param sqlContent
     */
    public void execute(String sqlContent) {
        String[] sqls = sqlContent.split(END_SQL_SIGN);
        execute(sqls);
    }

    /**
     * execute sqls
     *
     * @param sqls
     */
    public void execute(String[] sqls) {
        try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                StatementWrapper statement = connection.createStatement()) {
            for (String sql : sqls) {
                sql = sql.trim();
                if (LangUtils.isNotEmpty(sql)) {
                    logger.debug("add sql -> " + sql);
                    statement.addBatch(sql);
                }
            }
            statement.executeBatch();
        }
    }

    /**
     * sql execute
     *
     * @param sql    sql
     * @param params params
     * @return exuecute success amount
     */
    public int execute(String sql, Object... params) {
        if (LangUtils.isNotEmpty(sql)) {
            sql = sql.trim();
            try (ConnectionWrapper connection = JdbcUtils.getConnectionWrapper(dataSource);
                    PreparedStatementWrapper prep = connection.prepareStatement(sql)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute sql -> {} , params -> {}", sql, ArrayUtils.toString(params));
                }
                JdbcUtils.setParameters(prep, params);
                if (prep.execute()) {
                    return Chars.ONE;
                }
            }
        }
        return Chars.ZERO;
    }

    /**
     * sql execute
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
     * sql execute
     *
     * @param execution execution
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
     * sql query
     *
     * @param query query
     * @return query result list
     */
    public List<Map<String, Object>> query(Query query) {
        if (query != null) {
            return query(query.getExecution(), query.getParams());
        }
        return new ArrayList<>();
    }

    /**
     * sql query
     *
     * @param sql    sql
     * @param params params
     * @return query result list
     */
    public List<Map<String, Object>> query(String sql, Object... params) {
        if (LangUtils.isNotEmpty(sql)) {
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
     * sql query
     *
     * @param query query
     * @return query result list
     */
    public List<Object[]> queryListArray(Query query) {
        if (query != null) {
            return queryListArray(query.getExecution(), query.getParams());
        }
        return new ArrayList<>();
    }

    /**
     * sql query
     *
     * @param sql    sql
     * @param params params
     * @return query result list
     */
    public List<Object[]> queryListArray(String sql, Object... params) {
        if (LangUtils.isNotEmpty(sql)) {
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
}
