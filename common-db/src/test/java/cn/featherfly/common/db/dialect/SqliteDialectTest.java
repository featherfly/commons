
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.NotImplementedException;

/**
 * SqliteDialectTest.
 *
 * @author zhongj
 */
public class SqliteDialectTest extends DialectTest {

    Dialect dialect = Dialects.sqlite();

    @Override
    @Test
    void testCreateDatabase() {
        String database = "db_test";
        String sql = dialect.ddl().createDataBase(database);
        assertEquals(sql, "CREATE DATABASE `db_test`;");
    }

    @Override
    @Test
    void testDrop() {
        String sql = dialect.ddl().dropDataBase(database);
        assertEquals(sql, "DROP DATABASE `db_test`;");

        sql = dialect.ddl().dropTable(table);
        assertEquals(sql, "DROP TABLE `user`;");

        sql = dialect.ddl().dropTable(database, table);
        assertEquals(sql, "DROP TABLE `db_test`.`user`;");

        sql = dialect.ddl().dropView(view);
        assertEquals(sql, "DROP VIEW `user_view`;");

        sql = dialect.ddl().dropView(database, view);
        assertEquals(sql, "DROP VIEW `db_test`.`user_view`;");

        sql = dialect.ddl().dropIndex(table, index);
        assertEquals(sql, "DROP INDEX `username_uq`;");

        sql = dialect.ddl().dropIndex(database, table, index);
        assertEquals(sql, "DROP INDEX `db_test`.`username_uq`;");
    }

    @Override
    @Test
    void testDropIfExists() {
        String sql = dialect.ddl().dropDataBase(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS `db_test`;");

        sql = dialect.ddl().dropTable(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `user`;");

        sql = dialect.ddl().dropTable(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `db_test`.`user`;");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.ddl().createTable(getTableModel());
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
        String sql = dialect.ddl().createTable(getMultiKeyTableModel());
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
    @Test(expectedExceptions = { NotImplementedException.class })
    void testAlterTableDropColumn() {
        String sql = dialect.ddl().alterTableDropColumn(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test(expectedExceptions = { NotImplementedException.class })
    void testAlterTableAddColumns() {
        String sql = dialect.ddl().alterTableAddColumn(table, getColumnModels());
        System.out.println(sql);
        // TODO 后续有环境了把测试补上
        //        String s = "";
        //        assertEquals(sql, s);
    }

    @Override
    @Test(expectedExceptions = { NotImplementedException.class })
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
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `descp`) VALUES (?, ?, ?)");

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 2);
        assertEquals(sql, "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ?");
        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 3);
        assertEquals(sql,
                "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ? UNION SELECT ?, ?, ?");

        sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "descp" }, 5);
        assertEquals(sql,
                "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `descp` UNION SELECT ?, ?, ? UNION SELECT ?, ?, ? UNION SELECT ?, ?, ? UNION SELECT ?, ?, ?");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testPaginationSql() {
        String pageNamedParamSql = dialect.getPaginationSql("select * from user", 11, 10);
        System.out.println(pageNamedParamSql);
        assertEquals(pageNamedParamSql, "select * from user LIMIT ?,?");
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
        String sql = dialect.ddl().createIndex(table, index, new String[] { "user_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON `user`(`user_id`);");

        sql = dialect.ddl().createIndex(table, index, new String[] { "user_id", "role_id" });
        assertEquals(sql, "CREATE INDEX username_uq ON `user`(`user_id`,`role_id`);");

        sql = dialect.ddl().createIndex(table, index, new String[] { "user_id" }, true);
        assertEquals(sql, "CREATE UNIQUE INDEX username_uq ON `user`(`user_id`);");
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
                "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?) ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `age`=EXCLUDED.`age`");
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
                "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `age` UNION SELECT ?, ?, ? ON CONFLICT (id) DO UPDATE SET `name`=EXCLUDED.`name`, `age`=EXCLUDED.`age`");
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    void testInsert() {
        String sql = dialect.dml().insert("user", new String[] { "id", "name", "age" });
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    void testInsertBatch() {
        String sql = dialect.dml().insertBatch("user", new String[] { "id", "name", "age" }, 2);
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO `user` SELECT ? AS `id`, ? AS `name`, ? AS `age` UNION SELECT ?, ?, ?");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testDeleteFrom() {
        String sql = dialect.dml().deleteFrom("user");
        System.out.println(sql);
        assertEquals(sql, "DELETE FROM `user`");

        sql = dialect.dml().deleteFrom("user", "u");
        System.out.println(sql);
        assertEquals(sql, "DELETE FROM `user` `u`");
    }
}
