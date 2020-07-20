
package cn.featherfly.common.db.dialect;

import java.sql.Types;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.builder.TableModel;

/**
 * <p>
 * DialectTest
 * </p>
 *
 * @author zhongj
 */
public abstract class DialectTest {

    String database = "db_test";
    String table = "user";
    String view = "user_view";
    String index = "username_uq";

    public TableModel getTableModel() {
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
        id.setSize(0);
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
        return tableModel;
    }

    public TableModel getMultiKeyTableModel() {
        TableModel tableModel = new TableModel();
        tableModel.setName("user_role");
        tableModel.setRemark("user role 关系表");
        //        tableModel.setCatalog(database);

        int index = 0;
        ColumnModel userId = new ColumnModel();
        userId.setName("user_id");
        userId.setPrimaryKey(true);
        userId.setColumnIndex(index);
        userId.setType(Types.INTEGER);
        userId.setSize(0);
        userId.setNullable(false);
        userId.setRemark("user id");

        index++;
        ColumnModel roleId = new ColumnModel();
        roleId.setName("role_id");
        roleId.setPrimaryKey(true);
        roleId.setColumnIndex(index);
        roleId.setType(Types.INTEGER);
        roleId.setSize(0);
        roleId.setNullable(false);
        roleId.setRemark("role id");

        index++;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(255);
        descp.setNullable(true);
        descp.setRemark("descp描述");

        tableModel.addColumn(userId, roleId, descp);
        return tableModel;
    }

    public Column[] getColumnModels() {
        int index = 0;
        ColumnModel userId = new ColumnModel();
        userId.setName("user_id");
        userId.setPrimaryKey(true);
        userId.setColumnIndex(index);
        userId.setType(Types.INTEGER);
        userId.setSize(0);
        userId.setNullable(false);
        userId.setRemark("user id");

        index++;
        ColumnModel roleId = new ColumnModel();
        roleId.setName("role_id");
        roleId.setPrimaryKey(true);
        roleId.setColumnIndex(index);
        roleId.setType(Types.INTEGER);
        roleId.setSize(0);
        roleId.setNullable(false);
        roleId.setRemark("role id");

        index++;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(255);
        descp.setNullable(true);
        descp.setRemark("descp描述");

        return new ColumnModel[] { userId, roleId, descp };
    }

    public Column[] getModifyColumnModels() {
        int index = 0;
        int size = 222;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(size);
        descp.setNullable(true);
        descp.setRemark("descp");

        index++;
        ColumnModel province = new ColumnModel();
        province.setName("province");
        province.setColumnIndex(index);
        province.setType(Types.VARCHAR);
        province.setSize(size);
        province.setNullable(true);
        province.setRemark("省");

        index++;
        ColumnModel city = new ColumnModel();
        city.setName("city");
        city.setColumnIndex(index);
        city.setType(Types.VARCHAR);
        city.setSize(size);
        city.setNullable(true);
        city.setRemark("市");

        index++;
        ColumnModel district = new ColumnModel();
        district.setName("district");
        district.setColumnIndex(index);
        district.setType(Types.VARCHAR);
        district.setSize(size);
        district.setNullable(true);
        district.setRemark("区");

        return new ColumnModel[] { descp, province, city, district };
    }

    abstract void testCreateDatabase();

    abstract void testDrop();

    abstract void testDropIfExists();

    abstract void testCreateTable();

    abstract void testCreateTableMulitiKey();

    abstract void testAlterTableDropColumn();

    abstract void testAlterTableAddColumns();

    abstract void testAlterTableModifyColumns();

    abstract void testBuildInsertBatchSql();

    abstract void testParamNamedPaginationSql();
}
