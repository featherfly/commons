
package cn.featherfly.common.db.dialect;

import static org.testng.Assert.assertEquals;

import java.sql.Types;

import org.testng.annotations.Test;

import cn.featherfly.common.db.builder.ddl.ColumnModel;
import cn.featherfly.common.db.builder.ddl.TableModel;

/**
 * <p>
 * MysqlDialectTest
 * </p>
 *
 * @author zhongj
 */
public class MysqlDialectTest {

    String database = "db_test";
    String table = "user";
    String view = "user_view";
    String index = "username_uq";

    Dialect dialect = Dialects.MYSQL;

    @Test
    void testCreateDatabase() {
        String database = "db_test";
        String sql = dialect.buildCreateDataBaseSql(database);
        assertEquals(sql, "CREATE DATABASE `db_test`");
    }

    @Test
    void testDrop() {
        String sql = dialect.buildDropDataBaseSql(database);
        assertEquals(sql, "DROP DATABASE `db_test`");

        sql = dialect.buildDropTableSql(table);
        assertEquals(sql, "DROP TABLE `user`");

        sql = dialect.buildDropTableSql(database, table);
        assertEquals(sql, "DROP TABLE `db_test`.`user`");

        sql = dialect.buildDropViewSql(view);
        assertEquals(sql, "DROP VIEW `user_view`");

        sql = dialect.buildDropViewSql(database, view);
        assertEquals(sql, "DROP VIEW `db_test`.`user_view`");

        sql = dialect.buildDropIndexSql(table, index);
        assertEquals(sql, "ALTER TABLE `user` DROP INDEX `username_uq`");

        sql = dialect.buildDropIndexSql(database, table, index);
        assertEquals(sql, "ALTER TABLE `db_test`.`user` DROP INDEX `username_uq`");
    }

    @Test
    void testDropIfExists() {
        String sql = dialect.buildDropDataBaseSql(database, true);
        assertEquals(sql, "DROP DATABASE IF EXISTS `db_test`");

        sql = dialect.buildDropTableSql(table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `user`");

        sql = dialect.buildDropTableSql(database, table, true);
        assertEquals(sql, "DROP TABLE IF EXISTS `db_test`.`user`");
    }

    @Test
    void testCreateTable() {
        TableModel tableModel = new TableModel();
        tableModel.setName("user");
        tableModel.setRemark("user用户表");
        tableModel.setCatalog(database);

        int index = 0;
        ColumnModel id = new ColumnModel();
        id.setName("id");
        id.setPrimaryKey(true);
        id.setAutoincrement(true);
        id.setColumnIndex(index);
        id.setType(Types.INTEGER);
        id.setSize(11);
        id.setNullable(false);
        id.setRemark("id主键");

        index++;
        ColumnModel name = new ColumnModel();
        name.setName("name");
        name.setColumnIndex(index);
        name.setType(Types.VARCHAR);
        name.setSize(255);
        name.setNullable(false);
        name.setRemark("name名称");

        index++;
        ColumnModel money = new ColumnModel();
        money.setName("money");
        money.setColumnIndex(index);
        money.setType(Types.DECIMAL);
        money.setSize(11);
        money.setDecimalDigits(2);
        money.setNullable(false);
        money.setRemark("money金额");

        index++;
        ColumnModel state = new ColumnModel();
        state.setName("state");
        state.setColumnIndex(index);
        state.setType(Types.TINYINT);
        state.setSize(4);
        state.setDefaultValue("0");
        state.setNullable(false);
        state.setRemark("state状态：0禁用，1启用");

        index++;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(255);
        descp.setNullable(true);
        descp.setRemark("descp描述");

        tableModel.addColumn(id, name, money, state, descp);

        String sql = dialect.buildCreateTableSql(tableModel);

        System.out.println(sql);
    }
}
