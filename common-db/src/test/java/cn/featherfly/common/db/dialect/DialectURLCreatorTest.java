
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:39:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.creator.MysqlDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.OracleDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.PostgreSQLDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.SQLiteDialectURLCreator;

/**
 * DialectURLCreatorTest.
 *
 * @author zhongj
 */
public class DialectURLCreatorTest {

    String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
    String oracleUrl = "jdbc:oracle:thin:@192.168.1.99:1521:ORCL10G";
    String postgresqlUrl = "jdbc:postgresql://localhost:5432/db_test";
    String sqliteUrl = "jdbc:sqlite:D:\\db_test.sqlite3.db";

    @Test
    void testMysql() {
        MysqlDialectURLCreator creator = new MysqlDialectURLCreator();

        Dialect dialect = creator.apply(mysqlUrl);
        assertEquals(dialect.getClass(), MySQLDialect.class);

        assertNull(creator.apply(oracleUrl));
        assertNull(creator.apply(postgresqlUrl));
        assertNull(creator.apply(sqliteUrl));
    }

    @Test
    void testPostgreSQL() {
        PostgreSQLDialectURLCreator creator = new PostgreSQLDialectURLCreator();

        Dialect dialect = creator.apply(postgresqlUrl);
        assertEquals(dialect.getClass(), PostgreSQLDialect.class);

        assertNull(creator.apply(oracleUrl));
        assertNull(creator.apply(mysqlUrl));
        assertNull(creator.apply(sqliteUrl));
    }

    @Test
    void testSQLite() {
        SQLiteDialectURLCreator creator = new SQLiteDialectURLCreator();

        Dialect dialect = creator.apply(sqliteUrl);
        assertEquals(dialect.getClass(), SQLiteDialect.class);

        assertNull(creator.apply(oracleUrl));
        assertNull(creator.apply(postgresqlUrl));
        assertNull(creator.apply(postgresqlUrl));
    }

    @Test
    void testOracle() {
        OracleDialectURLCreator creator = new OracleDialectURLCreator();

        Dialect dialect = creator.apply(oracleUrl);
        assertEquals(dialect.getClass(), OracleDialect.class);

        assertNull(creator.apply(mysqlUrl));
        assertNull(creator.apply(postgresqlUrl));
        assertNull(creator.apply(sqliteUrl));
    }
}
