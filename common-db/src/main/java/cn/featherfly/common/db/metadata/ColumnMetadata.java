
package cn.featherfly.common.db.metadata;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.model.AbstractColumn;

/**
 * <p>
 * 列元数据
 * </p>
 * .
 *
 * @author zhongj
 */
public class ColumnMetadata extends AbstractColumn {

    /**
     * Instantiates a new column metadata.
     *
     * @param tableMetadata 表元数据
     */
    public ColumnMetadata(TableMetadata tableMetadata) {
        table = tableMetadata;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    /**
     * Sets the primary key.
     *
     * @param primaryKey 设置primaryKey
     */
    void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Sets the type.
     *
     * @param type 设置type
     */
    void setType(int type) {
        this.type = type;
        sqlType = JDBCType.valueOf(type);
    }

    /**
     * Sets the column index.
     *
     * @param columnIndex 设置columnIndex
     */

    void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * Sets the size.
     *
     * @param size 设置size
     */

    void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the name.
     *
     * @param name 设置name
     */

    void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type name.
     *
     * @param typeName 设置typeName
     */

    void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Sets the remark.
     *
     * @param remark 设置remark
     */

    void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Sets the default value.
     *
     * @param defaultValue 设置defaultValue
     */

    void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the nullable.
     *
     * @param nullable 设置nullable
     */

    void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 设置decimalDigits.
     *
     * @param decimalDigits decimalDigits
     */

    void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 设置autoincrement.
     *
     * @param autoincrement autoincrement
     */

    void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    /**
     * 设置sqlType.
     *
     * @param sqlType sqlType
     */
    void setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getVendorTypeNumber();
    }
}
