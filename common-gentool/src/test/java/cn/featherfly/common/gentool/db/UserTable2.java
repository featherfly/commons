
package cn.featherfly.common.gentool.db;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.model.SimpleColumn;
import cn.featherfly.common.db.model.SimpleTable;

/**
 * <p>
 * UserTable
 * </p>
 *
 * @author zhongj
 */
public class UserTable2 extends SimpleTable {

    public final Column id = new SimpleColumn();

    public final Column name = new SimpleColumn().setName("").setPrimaryKey(false).setAutoincrement(false)
            .setColumnIndex(1).setDecimalDigits(0).setDefaultValue("").setNullable(false).setRemark("").setSize(1)
            .setType(1).setTypeName("");

    UserTable2() {
        add(id);
    }
}
