
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * PostgresqlDialectTest.
 *
 * @author zhongj
 */
public class PostgresqlDialectTest extends DialectTest {

    Dialect dialect = Dialects.postgresql();

    @Override
    @Test
    void testCreateDatabase() {
        String database = "db_test";
        String sql = dialect.ddl().createDataBase(database);
        assertEquals(sql, "CREATE DATABASE \"db_test\";");
    }

    @Override
    @Test
    void testDrop() {
        String sql = dialect.ddl().dropDataBase(database);
        assertEquals(sql, "DROP DATABASE \"db_test\";");

        sql = dialect.ddl().dropTable(table);
        assertEquals(sql, "DROP TABLE \"user\";");

        sql = dialect.ddl().dropTable(database, table);
        assertEquals(sql, "DROP TABLE \"db_test\".\"user\";");

        sql = dialect.ddl().dropView(view);
        assertEquals(sql, "DROP VIEW \"user_view\";");

        sql = dialect.ddl().dropView(database, view);
        assertEquals(sql, "DROP VIEW \"db_test\".\"user_view\";");

        sql = dialect.ddl().dropIndex(table, index);
        assertEquals(sql, "DROP INDEX \"username_uq\";");

        sql = dialect.ddl().dropIndex(database, table, index);
        assertEquals(sql, "DROP INDEX \"db_test\".\"username_uq\";");
    }

    @Override
    @Test
    void testDropIfExists() {
        String sql = dialect.ddl().dropDataBase(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS \"db_test\";");

        sql = dialect.ddl().dropTable(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS \"user\";");

        sql = dialect.ddl().dropTable(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS \"db_test\".\"user\";");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.ddl().createTable(getTableModel());
        System.out.println(sql);
        //        assertEquals(sql,
        //                "CREATE TABLE \"db_test\".\"user\" (\r\n" + " \"id\" SERIAL4 NOT NULL,\r\n"
        //                        + " \"name\" VARCHAR(255) NOT NULL,\r\n" + " \"money\" DECIMAL(11,2) NOT NULL,\r\n"
        //                        + " \"state\" INT2 NOT NULL DEFAULT '0',\r\n" + " \"descp\" VARCHAR(255),\r\n"
        //                        + " CONSTRAINT \"user_pkey\" PRIMARY KEY (\"id\")\r\n" + " ) ;\r\n"
        //                        + "COMMENT ON TABLE \"db_test\".\"user\" IS 'user用户表';\r\n"
        //                        + "COMMENT ON COLUMN \"db_test\".\"user\".\"id\" IS 'id主键';\r\n"
        //                        + "COMMENT ON COLUMN \"db_test\".\"user\".\"name\" IS 'name名称';\r\n"
        //                        + "COMMENT ON COLUMN \"db_test\".\"user\".\"money\" IS 'money金额';\r\n"
        //                        + "COMMENT ON COLUMN \"db_test\".\"user\".\"state\" IS 'state状态：0禁用，1启用';\r\n"
        //                        + "COMMENT ON COLUMN \"db_test\".\"user\".\"descp\" IS 'descp描述';");
    }

    @Override
    @Test
    void testCreateTableMulitiKey() {
        String sql = dialect.ddl().createTable(getMultiKeyTableModel());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableDropColumn() {
        String sql = dialect.ddl().alterTableDropColumn(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableAddColumns() {
        String sql = dialect.ddl().alterTableAddColumn(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableModifyColumns() {
        String sql = dialect.ddl().alterTableModifyColumn("user_info", getModifyColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test
    void testBuildInsertBatchSql() {
        String sql = null;

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 1);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?)");

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 2);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?)");

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 3);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?)");

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 5);
        assertEquals(sql,
                "INSERT INTO \"user\" (\"id\", \"name\", \"descp\") VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testPaginationSql() {
        String pageNamedParamSql = dialect.getPaginationSql("select * from user", 11, 10);
        System.out.println(pageNamedParamSql);
        assertEquals(pageNamedParamSql, "select * from user LIMIT ? OFFSET ?");
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

    @Override
    @Test
    void testCreateIndex() {
        String sql = dialect.ddl().createIndex(table, index, new String[] { "user_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON \"user\"(\"user_id\");");

        sql = dialect.ddl().createIndex(table, index, new String[] { "user_id", "role_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON \"user\"(\"user_id\",\"role_id\");");

        sql = dialect.ddl().createIndex(table, index, new String[] { "user_id" }, true);
        assertEquals(sql, "CREATE UNIQUE INDEX username_uq ON \"user\"(\"user_id\");");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testUpsert() {
        String sql = dialect.dml().upsert("user", new String[] { "id", "name", "age" }, "id");
        System.out.println(sql);
        assertEquals(sql,
                "INSERT INTO \"user\" (\"id\", \"name\", \"age\") VALUES (?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"age\"=EXCLUDED.\"age\"");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testUpsertBatch() {
        String sql = dialect.dml().upsertBatch("user", new String[] { "id", "name", "age" }, "id", 2);
        System.out.println(sql);
        assertEquals(sql,
                "INSERT INTO \"user\" (\"id\", \"name\", \"age\") VALUES (?, ?, ?),(?, ?, ?) ON CONFLICT (id) DO UPDATE SET \"name\"=EXCLUDED.\"name\", \"age\"=EXCLUDED.\"age\"");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testInsert() {
        String sql = dialect.dml().insert("user", new String[] { "id", "name", "age" });
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"age\") VALUES (?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    void testInsertBatch() {
        String sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "age" }, 2);
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO \"user\" (\"id\", \"name\", \"age\") VALUES (?, ?, ?),(?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testDeleteFrom() {
        String sql = dialect.dml().deleteFrom("user");
        System.out.println(sql);
        assertEquals(sql, "DELETE FROM \"user\"");

        sql = dialect.dml().deleteFrom("user", "u");
        System.out.println(sql);
        assertEquals(sql, "DELETE FROM \"user\" \"u\"");
    }
}
