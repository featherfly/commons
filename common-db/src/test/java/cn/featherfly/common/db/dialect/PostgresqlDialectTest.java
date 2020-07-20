
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * <p>
 * MysqlDialectTest
 * </p>
 *
 * @author zhongj
 */
public class PostgresqlDialectTest extends DialectTest {

    Dialect dialect = Dialects.POSTGRESQL;

    @Override
    @Test
    void testCreateDatabase() {
        String database = "db_test";
        String sql = dialect.buildCreateDataBaseDDL(database);
        assertEquals(sql, "CREATE DATABASE \"db_test\"");
    }

    @Override
    @Test
    void testDrop() {
        String sql = dialect.buildDropDataBaseDDL(database);
        assertEquals(sql, "DROP DATABASE \"db_test\"");

        sql = dialect.buildDropTableDDL(table);
        assertEquals(sql, "DROP TABLE \"user\"");

        sql = dialect.buildDropTableDDL(database, table);
        assertEquals(sql, "DROP TABLE \"db_test\".\"user\"");

        sql = dialect.buildDropViewDDL(view);
        assertEquals(sql, "DROP VIEW \"user_view\"");

        sql = dialect.buildDropViewDDL(database, view);
        assertEquals(sql, "DROP VIEW \"db_test\".\"user_view\"");

        sql = dialect.buildDropIndexDDL(table, index);
        assertEquals(sql, "ALTER TABLE \"user\" DROP INDEX \"username_uq\"");

        sql = dialect.buildDropIndexDDL(database, table, index);
        assertEquals(sql, "ALTER TABLE \"db_test\".\"user\" DROP INDEX \"username_uq\"");
    }

    @Override
    @Test
    void testDropIfExists() {
        String sql = dialect.buildDropDataBaseDDL(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS \"db_test\"");

        sql = dialect.buildDropTableDDL(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS \"user\"");

        sql = dialect.buildDropTableDDL(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS \"db_test\".\"user\"");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.buildCreateTableDDL(getTableModel());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //      String s = "";
        //      assertEquals(sql, s);
    }

    @Override
    @Test
    void testCreateTableMulitiKey() {
        String sql = dialect.buildCreateTableDDL(getMultiKeyTableModel());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableDropColumn() {
        String sql = dialect.buildAlterTableDropColumnDDL(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableAddColumns() {
        String sql = dialect.buildAlterTableAddColumnDDL(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableModifyColumns() {
        String sql = dialect.buildAlterTableModifyColumnDDL("user_info", getModifyColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testBuildInsertBatchSql() {
        String sql = null;

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 1);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 2);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 3);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 5);
        assertEquals(sql,
                "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testParamNamedPaginationSql() {
        String pageNamedParamSql = dialect.getParamNamedPaginationSql("select * from user", 11, 10);
        System.out.println(pageNamedParamSql);
        assertEquals(pageNamedParamSql, "select * from user LIMIT :dialect_paging_limit OFFSET :dialect_paging_start");
    }
}
