
package cn.featherfly.common.db.model;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;

/**
 * AbstractColumn .
 *
 * @author zhongj
 * @param <C> the generic type
 */
public abstract class AbstractColumnPojo<C extends Column> extends AbstractColumn {

    /**
     * Instantiates a new abstract column pojo.
     */
    protected AbstractColumnPojo() {
        super();
    }

    /**
     * Instantiates a new abstract column pojo.
     *
     * @param name the name
     */
    protected AbstractColumnPojo(String name) {
        super(name);
    }

    /**
     * 设置name.
     *
     * @param name name
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setName(String name) {
        this.name = name;
        return (C) this;
    }

    /**
     * 设置type.
     *
     * @param type type
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setType(int type) {
        this.type = type;
        sqlType = JDBCType.valueOf(type);
        return (C) this;
    }

    /**
     * 设置typeName.
     *
     * @param typeName typeName
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setTypeName(String typeName) {
        this.typeName = typeName;
        return (C) this;
    }

    /**
     * 设置size.
     *
     * @param size size
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setSize(int size) {
        this.size = size;
        return (C) this;
    }

    /**
     * 设置remark.
     *
     * @param remark remark
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setRemark(String remark) {
        this.remark = remark;
        return (C) this;
    }

    /**
     * 设置defaultValue.
     *
     * @param defaultValue defaultValue
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return (C) this;
    }

    /**
     * 设置nullable.
     *
     * @param nullable nullable
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setNullable(boolean nullable) {
        this.nullable = nullable;
        return (C) this;
    }

    /**
     * 设置columnIndex.
     *
     * @param columnIndex columnIndex
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
        return (C) this;
    }

    /**
     * 设置primaryKey.
     *
     * @param primaryKey primaryKey
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
        if (primaryKey) {
            nullable = false;
        }
        return (C) this;
    }

    /**
     * 设置decimalDigits.
     *
     * @param decimalDigits decimalDigits
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
        return (C) this;
    }

    /**
     * 设置autoincrement.
     *
     * @param autoincrement autoincrement
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
        return (C) this;
    }

    /**
     * 设置sqlType.
     *
     * @param sqlType sqlType
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public C setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getVendorTypeNumber();
        return (C) this;
    }

    /**
     * 设置table.
     *
     * @param table table
     * @return the c
     */
    @SuppressWarnings("unchecked")
    public C setTable(Table table) {
        this.table = table;
        return (C) this;
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
