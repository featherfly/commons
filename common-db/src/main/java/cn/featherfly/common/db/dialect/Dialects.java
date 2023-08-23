
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

    /** The mysql. */
    Dialect MYSQL = new MySQLDialect();

    /** The oracle. */
    Dialect ORACLE = new OracleDialect();

    /** The postgresql. */
    Dialect POSTGRESQL = new PostgreSQLDialect();

    /** The sqlite. */
    Dialect SQLITE = new SQLiteDialect();
}
