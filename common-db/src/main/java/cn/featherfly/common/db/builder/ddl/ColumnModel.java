
package cn.featherfly.common.db.builder.ddl;

import cn.featherfly.common.db.metadata.Column;
import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * ColumnModel
 * </p>
 *
 * @author zhongj
 */
public class ColumnModel implements Column {

    private String name;

    private SqlType sqlType;

    private int type;

    private String typeName;

    private int size;

    private String remark;

    private String defaultValue;

    private boolean nullable;

    private int columnIndex;

    private boolean primaryKey;

    /**
     * 小数位数
     */
    private int decimalDigits;

    private boolean autoincrement;

    /**
     * 返回name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回type
     *
     * @return type
     */
    @Override
    public int getType() {
        return type;
    }

    /**
     * 设置type
     *
     * @param type type
     */
    public void setType(int type) {
        this.type = type;
        sqlType = SqlType.value(type);
    }

    /**
     * 返回typeName
     *
     * @return typeName
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置typeName
     *
     * @param typeName typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 返回size
     *
     * @return size
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 设置size
     *
     * @param size size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 返回remark
     *
     * @return remark
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 返回defaultValue
     *
     * @return defaultValue
     */
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置defaultValue
     *
     * @param defaultValue defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 返回nullable
     *
     * @return nullable
     */
    @Override
    public boolean isNullable() {
        return nullable;
    }

    /**
     * 设置nullable
     *
     * @param nullable nullable
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 返回columnIndex
     *
     * @return columnIndex
     */
    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * 设置columnIndex
     *
     * @param columnIndex columnIndex
     */
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * 返回primaryKey
     *
     * @return primaryKey
     */
    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * 设置primaryKey
     *
     * @param primaryKey primaryKey
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
        if (primaryKey) {
            nullable = false;
        }
    }

    /**
     * 返回decimalDigits
     *
     * @return decimalDigits
     */
    @Override
    public int getDecimalDigits() {
        return decimalDigits;
    }

    /**
     * 设置decimalDigits
     *
     * @param decimalDigits decimalDigits
     */
    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 返回autoincrement
     *
     * @return autoincrement
     */
    @Override
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * 设置autoincrement
     *
     * @param autoincrement autoincrement
     */
    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    /**
     * 返回sqlType
     *
     * @return sqlType
     */
    @Override
    public SqlType getSqlType() {
        return sqlType;
    }

    /**
     * 设置sqlType
     *
     * @param sqlType sqlType
     */
    public void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getValue();
    }
}
