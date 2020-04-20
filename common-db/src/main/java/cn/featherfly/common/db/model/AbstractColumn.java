
package cn.featherfly.common.db.model;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * AbstractColumn
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractColumn<T extends Column> implements Column {

    protected String name;

    protected SqlType sqlType;

    protected int type;

    protected String typeName;

    protected int size;

    protected String remark;

    protected String defaultValue;

    protected boolean nullable;

    protected int columnIndex;

    protected boolean primaryKey;

    /**
     * 小数位数
     */
    protected int decimalDigits;

    protected boolean autoincrement;

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
    @SuppressWarnings("unchecked")
    public T setName(String name) {
        this.name = name;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setType(int type) {
        this.type = type;
        sqlType = SqlType.value(type);
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setTypeName(String typeName) {
        this.typeName = typeName;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setSize(int size) {
        this.size = size;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setRemark(String remark) {
        this.remark = remark;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setNullable(boolean nullable) {
        this.nullable = nullable;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
        if (primaryKey) {
            nullable = false;
        }
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
        return (T) this;
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
    @SuppressWarnings("unchecked")
    public T setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getValue();
        return (T) this;
    }
}
