
package cn.featherfly.common.db;

import java.sql.SQLType;

/**
 * <p>
 * Column
 * </p>
 *
 * @author zhongj
 */
public interface Column {

    String getName();

    String getTypeName();

    String getRemark();

    String getDefaultValue();

    SQLType getSqlType();

    int getType();

    int getSize();

    int getColumnIndex();

    boolean isNullable();

    boolean isPrimaryKey();

    int getDecimalDigits();

    boolean isAutoincrement();

    Table getTable();
    //    boolean isUnique();
}
