
package cn.featherfly.common.db.dialect;

/**
 * Dialects.
 *
 * @author zhongj
 */
public interface Dialects {

    /**
     * Mysql.
     *
     * @return the dialect
     */
    static Dialect mysql() {
        return new MySQLDialect();
    }

    /**
     * Postgresql.
     *
     * @return the dialect
     */
    static Dialect postgresql() {
        return new PostgreSQLDialect();
    }

    /**
     * Oracle.
     *
     * @return the dialect
     */
    static Dialect oracle() {
        return new OracleDialect();
    }

    /**
     * Sqlite.
     *
     * @return the dialect
     */
    static Dialect sqlite() {
        return new SQLiteDialect();
    }

    /**
     * The mysql.
     *
     * @deprecated use {@link #mysql()} instead, because dialect have state, you need manager instance by youself
     */
    @Deprecated
    Dialect MYSQL = new MySQLDialect();

    /**
     * The oracle.
     *
     * @deprecated use {@link #oracle()} instead, because dialect have state, you need manager instance by youself
     */
    @Deprecated
    Dialect ORACLE = new OracleDialect();

    /**
     * The postgresql.
     *
     * @deprecated use {@link #postgresql()} instead, because dialect have state, you need manager instance by youself
     */
    @Deprecated
    Dialect POSTGRESQL = new PostgreSQLDialect();

    /**
     * The sqlite.
     *
     * @deprecated use {@link #sqlite()} instead, because dialect have state, you need manager instance by youself
     */
    @Deprecated
    Dialect SQLITE = new SQLiteDialect();
}
