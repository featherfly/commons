
package cn.featherfly.common.gentool.db;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.model.ReadonlyTable;
import cn.featherfly.common.db.model.SimpleColumn;

/**
 * <p>
 * UserTable
 * </p>
 *
 * @author zhongj
 */
public class UserTable2 extends ReadonlyTable {

    public final Column id = new SimpleColumn();

    public final Column name = new ColumnModel().setName("").setPrimaryKey(false).setAutoincrement(false)
            .setColumnIndex(1).setDecimalDigits(0).setDefaultValue("").setNullable(false).setRemark("").setSize(1)
            .setType(1).setTypeName("").setTable(Tables.USER);

    //    UserTable2() {
    //        add(id);
    //    }

    UserTable2(String type, String name, String remark, String catalog, String schema) {
        super(type, name, remark, catalog, schema);
        add(id);
    }
}
