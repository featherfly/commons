
package cn.featherfly.common.gentool.db;

import cn.featherfly.common.db.metadata.TableType;

/**
 * <p>
 * Tables
 * </p>
 *
 * @author zhongj
 */
public interface Tables {

    UserTable USER = (UserTable) new UserTable().setName("user").setRemark("remark").setType(TableType.TABLE.name())
            .setCatalog("catalog");

}
