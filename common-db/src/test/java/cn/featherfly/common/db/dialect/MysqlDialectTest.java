
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
public class MysqlDialectTest extends DialectTest {

    Dialect dialect = Dialects.MYSQL;

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

        sql = dialect.buildDropViewDDL(database, view, true);
        assertEquals(sql, "DROP VIEW IF EXISTS `db_test`.`user_view`;");

        sql = dialect.buildDropIndexDDL(table, index);
        assertEquals(sql, "DROP INDEX `username_uq` ON `user`;");

        sql = dialect.buildDropIndexDDL(database, table, index);
        assertEquals(sql, "DROP INDEX `db_test`.`username_uq` ON `db_test`.`user`;");
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

        sql = dialect.buildDropIndexDDL(table, index, true);
        assertEquals(sql, "DROP INDEX IF EXISTS `username_uq` ON `user`;");

        sql = dialect.buildDropIndexDDL(database, table, index, true);
        assertEquals(sql, "DROP INDEX IF EXISTS `db_test`.`username_uq` ON `db_test`.`user`;");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.buildCreateTableDDL(getTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `db_test`.`user` (\n" + " `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id主键',\n"
                + " `name` VARCHAR(255) NOT NULL COMMENT 'name名称',\n"
                + " `money` DECIMAL(11,2) NOT NULL COMMENT 'money金额',\n"
                + " `state` TINYINT NOT NULL DEFAULT '0' COMMENT 'state状态：0禁用，1启用',\n"
                + " `descp` VARCHAR(255) COMMENT 'descp描述',\n" + " PRIMARY KEY (`id`)\n" + " ) COMMENT 'user用户表';";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testCreateTableMulitiKey() {
        String sql = dialect.buildCreateTableDDL(getMultiKeyTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `user_role` (\n" + " `user_id` INT NOT NULL COMMENT 'user id',\n"
                + " `role_id` INT NOT NULL COMMENT 'role id',\n" + " `descp` VARCHAR(255) COMMENT 'descp描述',\n"
                + " PRIMARY KEY (`user_id`,`role_id`)\n" + " ) COMMENT 'user role 关系表';";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableDropColumn() {
        String sql = dialect.buildAlterTableDropColumnDDL(table, getColumnModels());
        System.out.println(sql);
        String s = "ALTER TABLE `user`\n" + " DROP COLUMN `user_id`,\n" + " DROP COLUMN `role_id`,\n"
                + " DROP COLUMN `descp`;";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableAddColumns() {
        String sql = dialect.buildAlterTableAddColumnDDL(table, getColumnModels());
        System.out.println(sql);
        String s = "ALTER TABLE `user`\n" + " ADD COLUMN `user_id` INT NOT NULL COMMENT 'user id',\n"
                + " ADD COLUMN `role_id` INT NOT NULL COMMENT 'role id',\n"
                + " ADD COLUMN `descp` VARCHAR(255) COMMENT 'descp描述';";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableModifyColumns() {
        String sql = dialect.buildAlterTableModifyColumnDDL("user_info", getModifyColumnModels());
        System.out.println(sql);
        String s = "ALTER TABLE `user_info`\n" + " MODIFY COLUMN `descp` VARCHAR(222) COMMENT 'descp',\n"
                + " MODIFY COLUMN `province` VARCHAR(222) COMMENT '省',\n"
                + " MODIFY COLUMN `city` VARCHAR(222) COMMENT '市',\n"
                + " MODIFY COLUMN `district` VARCHAR(222) COMMENT '区';";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testBuildInsertBatchSql() {
        String sql = null;

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 1);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `descp`) VALUES (?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 2);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `descp`) VALUES (?, ?, ?),(?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 3);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `descp`) VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?)");

        sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "descp" }, 5);
        assertEquals(sql,
                "INSERT INTO `user` (`id`, `name`, `descp`) VALUES (?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?),(?, ?, ?)");
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

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testUpsert() {
        String sql = dialect.buildUpsertSql("user", new String[] { "id", "name", "age" }, "id");
        System.out.println(sql);
        assertEquals(sql,
                "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `age`=values(`age`)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testUpsertBatch() {
        String sql = dialect.buildUpsertBatchSql("user", new String[] { "id", "name", "age" }, "id", 2);
        System.out.println(sql);
        assertEquals(sql,
                "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?),(?, ?, ?) ON DUPLICATE KEY UPDATE `name`=values(`name`), `age`=values(`age`)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    void testInsert() {
        String sql = dialect.buildInsertSql("user", new String[] { "id", "name", "age" });
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?)");
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    void testInsertBatch() {
        String sql = dialect.buildInsertBatchSql("user", new String[] { "id", "name", "age" }, 2);
        System.out.println(sql);
        assertEquals(sql, "INSERT INTO `user` (`id`, `name`, `age`) VALUES (?, ?, ?),(?, ?, ?)");
    }
}
