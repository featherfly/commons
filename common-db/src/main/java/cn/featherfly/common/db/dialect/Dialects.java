
package cn.featherfly.common.db.dialect;

/**
 * Dialects.
 *
 * @author zhongj
 */
public interface Dialects {

    /** The mysql. */
    Dialect MYSQL = new MySQLDialect();

    /** The oracle. */
    Dialect ORACLE = new OracleDialect();

    /** The postgresql. */
    Dialect POSTGRESQL = new PostgreSQLDialect();

    /** The sqlite. */
    Dialect SQLITE = new SQLiteDialect();
}
