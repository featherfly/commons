
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.UnsupportedException;

/**
 * <p>
 * SqliteDialectTest2
 * </p>
 *
 * @author zhongj
 */
public class SqliteDialectTest extends DialectTest {

    Dialect dialect = Dialects.SQLITE;

    @Override
    @Test
    void testCreateDatabase() {
        String database = "db_test";
        String sql = dialect.buildCreateDataBaseDDL(database);
        assertEquals(sql, "CREATE DATABASE `db_test`;");
    }

    @Override
    @Test
    void testDrop() {
        String sql = dialect.buildDropDataBaseDDL(database);
        assertEquals(sql, "DROP DATABASE `db_test`;");

        sql = dialect.buildDropTableDDL(table);
        assertEquals(sql, "DROP TABLE `user`;");

        sql = dialect.buildDropTableDDL(database, table);
        assertEquals(sql, "DROP TABLE `db_test`.`user`;");

        sql = dialect.buildDropViewDDL(view);
        assertEquals(sql, "DROP VIEW `user_view`;");

        sql = dialect.buildDropViewDDL(database, view);
        assertEquals(sql, "DROP VIEW `db_test`.`user_view`;");

        sql = dialect.buildDropIndexDDL(table, index);
        assertEquals(sql, "DROP INDEX `username_uq`;");

        sql = dialect.buildDropIndexDDL(database, table, index);
        assertEquals(sql, "DROP INDEX `db_test`.`username_uq`;");
    }

    @Override
    @Test
    void testDropIfExists() {
        String sql = dialect.buildDropDataBaseDDL(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS `db_test`;");

        sql = dialect.buildDropTableDDL(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `user`;");

        sql = dialect.buildDropTableDDL(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `db_test`.`user`;");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.buildCreateTableDDL(getTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `db_test`.`user` (\n" + " `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, -- id主键\n"
                + " `name` TEXT(255) NOT NULL , -- name名称\n" + " `money` REAL(11,2) NOT NULL , -- money金额\n"
                + " `state` INTEGER NOT NULL DEFAULT '0' , -- state状态：0禁用，1启用\n" + " `descp` TEXT(255) -- descp描述\n"
                + " ) -- user用户表;";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testCreateTableMulitiKey() {
        String sql = dialect.buildCreateTableDDL(getMultiKeyTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `user_role` (\n" + " `user_id` INTEGER NOT NULL, -- user id\n"
                + " `role_id` INTEGER NOT NULL, -- role id\n" + " `descp` TEXT(255), -- descp描述\n"
                + " PRIMARY KEY (`user_id`,`role_id`)\n" + " ) -- user role 关系表;";
        assertEquals(sql, s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test(expectedExceptions = { UnsupportedException.class })
    void testAlterTableDropColumn() {
        String sql = dialect.buildAlterTableDropColumnDDL(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test(expectedExceptions = { UnsupportedException.class })
    void testAlterTableAddColumns() {
        String sql = dialect.buildAlterTableAddColumnDDL(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test(expectedExceptions = { UnsupportedException.class })
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
        assertEquals(sql, "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp`");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 2);
        assertEquals(sql, "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ?");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 3);
        assertEquals(sql,
                "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ? UNION SELECT ?, ?, ?");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 5);
        assertEquals(sql,
                "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ? UNION SELECT ?, ?, ? UNION SELECT ?, ?, ? UNION SELECT ?, ?, ?");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testParamNamedPaginationSql() {
        String pageNamedParamSql = dialect.getParamNamedPaginationSql("select * from user", 11, 10);
        System.out.println(pageNamedParamSql);
        assertEquals(pageNamedParamSql, "select * from user LIMIT :dialect_paging_start,:dialect_paging_limit");
    }

    @Override
    @Test
    void testCreateIndex() {
        String sql = dialect.buildCreateIndexDDL(table, index, new String[] { "user_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON `user`(`user_id`);");

        sql = dialect.buildCreateIndexDDL(table, index, new String[] { "user_id", "role_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON `user`(`user_id`,`role_id`);");

        sql = dialect.buildCreateIndexDDL(table, index, new String[] { "user_id" }, true);
        assertEquals(sql, "CREATE UNIQUE INDEX username_uq ON `user`(`user_id`);");
    }
}
