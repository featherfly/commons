
package cn.featherfly.common.db;

import java.sql.SQLType;

import cn.featherfly.common.repository.Field;

/**
 * Column.
 *
 * @author zhongj
 */
public interface Column extends Field {

    /**
     * {@inheritDoc}
     */
    @Override
    default String name() {
        return getName();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the type name.
     *
     * @return the type name
     */
    String getTypeName();

    /**
     * Gets the remark.
     *
     * @return the remark
     */
    String getRemark();

    /**
     * Gets the default value.
     *
     * @return the default value
     */
    String getDefaultValue();

    /**
     * Gets the sql type.
     *
     * @return the sql type
     */
    SQLType getSqlType();

    /**
     * Gets the type.
     *
     * @return the type
     */
    int getType();

    /**
     * Gets the size.
     *
     * @return the size
     */
    int getSize();

    /**
     * Gets the column index.
     *
     * @return the column index
     */
    int getColumnIndex();

    /**
     * Checks if is nullable.
     *
     * @return true, if is nullable
     */
    boolean isNullable();

    /**
     * Checks if is primary key.
     *
     * @return true, if is primary key
     */
    boolean isPrimaryKey();

    /**
     * Gets the decimal digits.
     *
     * @return the decimal digits
     */
    int getDecimalDigits();

    /**
     * Checks if is autoincrement.
     *
     * @return true, if is autoincrement
     */
    boolean isAutoincrement();

    /**
     * Gets the table.
     *
     * @return the table
     */
    Table getTable();
    //    boolean isUnique();
}
