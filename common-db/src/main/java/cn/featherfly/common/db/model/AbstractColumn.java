
package cn.featherfly.common.db.model;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.Column;

/**
 * <p>
 * AbstractColumn
 * </p>
 * .
 *
 * @author zhongj
 * @param <T> the generic type
 */
public abstract class AbstractColumn<T extends Column> implements Column {

    /** The name. */
    protected String name;

    /** The sql type. */
    protected SQLType sqlType;

    /** The type. */
    protected int type;

    /** The type name. */
    protected String typeName;

    /** The size. */
    protected int size;

    /** The remark. */
    protected String remark;

    /** The default value. */
    protected String defaultValue;

    /** The nullable. */
    protected boolean nullable = true;

    /** The column index. */
    protected int columnIndex;

    /** The primary key. */
    protected boolean primaryKey;

    /** 小数位数. */
    protected int decimalDigits;

    /** The autoincrement. */
    protected boolean autoincrement;

    //    protected boolean unique;

    /**
     * 返回name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name.
     *
     * @param name name
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * 返回type.
     *
     * @return type
     */
    @Override
    public int getType() {
        return type;
    }

    /**
     * 设置type.
     *
     * @param type type
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setType(int type) {
        this.type = type;
        sqlType = JDBCType.valueOf(type);
        return (T) this;
    }

    /**
     * 返回typeName.
     *
     * @return typeName
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置typeName.
     *
     * @param typeName typeName
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setTypeName(String typeName) {
        this.typeName = typeName;
        return (T) this;
    }

    /**
     * 返回size.
     *
     * @return size
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 设置size.
     *
     * @param size size
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setSize(int size) {
        this.size = size;
        return (T) this;
    }

    /**
     * 返回remark.
     *
     * @return remark
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark.
     *
     * @param remark remark
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setRemark(String remark) {
        this.remark = remark;
        return (T) this;
    }

    /**
     * 返回defaultValue.
     *
     * @return defaultValue
     */
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置defaultValue.
     *
     * @param defaultValue defaultValue
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return (T) this;
    }

    /**
     * 返回nullable.
     *
     * @return nullable
     */
    @Override
    public boolean isNullable() {
        return nullable;
    }

    /**
     * 设置nullable.
     *
     * @param nullable nullable
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setNullable(boolean nullable) {
        this.nullable = nullable;
        return (T) this;
    }

    /**
     * 返回columnIndex.
     *
     * @return columnIndex
     */
    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * 设置columnIndex.
     *
     * @param columnIndex columnIndex
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
        return (T) this;
    }

    /**
     * 返回primaryKey.
     *
     * @return primaryKey
     */
    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * 设置primaryKey.
     *
     * @param primaryKey primaryKey
     * @return the t
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
     * 返回decimalDigits.
     *
     * @return decimalDigits
     */
    @Override
    public int getDecimalDigits() {
        return decimalDigits;
    }

    /**
     * 设置decimalDigits.
     *
     * @param decimalDigits decimalDigits
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
        return (T) this;
    }

    /**
     * 返回autoincrement.
     *
     * @return autoincrement
     */
    @Override
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * 设置autoincrement.
     *
     * @param autoincrement autoincrement
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
        return (T) this;
    }

    /**
     * 返回sqlType.
     *
     * @return sqlType
     */
    @Override
    public SQLType getSqlType() {
        return sqlType;
    }

    /**
     * 设置sqlType.
     *
     * @param sqlType sqlType
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getVendorTypeNumber();
        return (T) this;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public boolean isUnique() {
    //        return unique;
    //    }
    //
    //    /**
    //     * 设置unique
    //     *
    //     * @param unique unique
    //     */
    //    public void setUnique(boolean unique) {
    //        this.unique = unique;
    //    }
}
