
package cn.featherfly.common.db.metadata;

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

    SqlType getSqlType();

    int getType();

    int getSize();

    int getColumnIndex();

    boolean isNullable();

    boolean isPrimaryKey();

    int getDecimalDigits();

    boolean isAutoincrement();
}
