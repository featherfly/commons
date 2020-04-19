
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
        assertEquals(sql, "CREATE DATABASE `db_test`");
    }

    @Override
    @Test
    void testDrop() {
        String sql = dialect.buildDropDataBaseDDL(database);
        assertEquals(sql, "DROP DATABASE `db_test`");

        sql = dialect.buildDropTableDDL(table);
        assertEquals(sql, "DROP TABLE `user`");

        sql = dialect.buildDropTableDDL(database, table);
        assertEquals(sql, "DROP TABLE `db_test`.`user`");

        sql = dialect.buildDropViewDDL(view);
        assertEquals(sql, "DROP VIEW `user_view`");

        sql = dialect.buildDropViewDDL(database, view);
        assertEquals(sql, "DROP VIEW `db_test`.`user_view`");

        sql = dialect.buildDropIndexDDL(table, index);
        assertEquals(sql, "ALTER TABLE `user` DROP INDEX `username_uq`");

        sql = dialect.buildDropIndexDDL(database, table, index);
        assertEquals(sql, "ALTER TABLE `db_test`.`user` DROP INDEX `username_uq`");
    }

    @Override
    @Test
    void testDropIfExists() {
        String sql = dialect.buildDropDataBaseDDL(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS `db_test`");

        sql = dialect.buildDropTableDDL(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `user`");

        sql = dialect.buildDropTableDDL(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `db_test`.`user`");
    }

    @Override
    @Test
    void testCreateTable() {
        String sql = dialect.buildCreateTableDDL(getTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `db_test`.`user` (\n" + " `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',\n"
                + " `name` VARCHAR(255) NOT NULL COMMENT 'name名称',\n"
                + " `money` DECIMAL(11.2) NOT NULL COMMENT 'money金额',\n"
                + " `state` TINYINT(4) NOT NULL DEFAULT '0' COMMENT 'state状态：0禁用，1启用',\n"
                + " `descp` VARCHAR(255) COMMENT 'descp描述',\n" + " PRIMARY KEY (`id`)\n" + " ) COMMENT 'user用户表'";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testCreateTableMulitiKey() {
        String sql = dialect.buildCreateTableDDL(getMultiKeyTableModel());
        System.out.println(sql);
        String s = "CREATE TABLE `user_role` (\n" + " `user_id` int(11) NOT NULL COMMENT 'user id',\n"
                + " `role_id` int(11) NOT NULL COMMENT 'role id',\n" + " `descp` VARCHAR(255) COMMENT 'descp描述',\n"
                + " PRIMARY KEY (`user_id`,`role_id`)\n" + " ) COMMENT 'user role 关系表'";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableDropColumn() {
        String sql = dialect.buildAlterTableDropColumnDDL(table, getColumnModels());
        System.out.println(sql);
        String s = "ALTER TABLE `user`\n" + " DROP COLUMN `user_id`,\n" + " DROP COLUMN `role_id`,\n"
                + " DROP COLUMN `descp`";
        assertEquals(sql, s);
    }

    @Override
    @Test
    void testAlterTableAddColumns() {
        String sql = dialect.buildAlterTableAddColumnDDL(table, getColumnModels());
        System.out.println(sql);
        String s = "ALTER TABLE `user`\n" + " ADD COLUMN `user_id` int(11) NOT NULL COMMENT 'user id',\n"
                + " ADD COLUMN `role_id` int(11) NOT NULL COMMENT 'role id',\n"
                + " ADD COLUMN `descp` VARCHAR(255) COMMENT 'descp描述'";
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
                + " MODIFY COLUMN `district` VARCHAR(222) COMMENT '区'";
        assertEquals(sql, s);
    }
}