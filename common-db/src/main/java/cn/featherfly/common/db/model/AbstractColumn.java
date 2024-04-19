
package cn.featherfly.common.db.model;

import java.sql.SQLType;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.lang.Lang;

/**
 * AbstractColumn.
 *
 * @author zhongj
 */
public abstract class AbstractColumn implements Column {

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
    protected String remark = "";

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

    // TODO 后续加入unique实现
    //    protected boolean unique;

    /** The table. */
    protected Table table;

    /**
     * Instantiates a new abstract column.
     */
    protected AbstractColumn() {
        super();
    }

    /**
     * Instantiates a new abstract column.
     *
     * @param name the name
     */
    protected AbstractColumn(String name) {
        super();
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (autoincrement ? 1231 : 1237);
        //        result = prime * result + columnIndex;
        result = prime * result + decimalDigits;
        result = prime * result + (defaultValue == null ? 0 : defaultValue.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (nullable ? 1231 : 1237);
        result = prime * result + (primaryKey ? 1231 : 1237);
        result = prime * result + (remark == null ? 0 : remark.hashCode());
        result = prime * result + size;
        result = prime * result + (sqlType == null ? 0 : sqlType.hashCode());
        result = prime * result + type;
        result = prime * result + (typeName == null ? 0 : typeName.hashCode());
        if (table != null && Lang.isNotEmpty(table.getName())) {
            result = prime * result + table.getName().hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Column)) {
            return false;
        }
        Column other = (Column) obj;
        if (autoincrement != other.isAutoincrement()) {
            return false;
        }
        //        if (columnIndex != other.getColumnIndex()) {
        //            return false;
        //        }
        if (decimalDigits != other.getDecimalDigits()) {
            return false;
        }
        if (!Lang.equals(defaultValue, other.getDefaultValue())) {
            return false;
        }
        if (!Lang.equals(name, other.getName())) {
            return false;
        }
        if (nullable != other.isNullable()) {
            return false;
        }
        if (primaryKey != other.isPrimaryKey()) {
            return false;
        }
        if (!Lang.equals(remark, other.getRemark())) {
            return false;
        }
        if (size != other.getSize()) {
            return false;
        }
        if (!Lang.equals(sqlType, other.getSqlType())) {
            return false;
        }
        if (type != other.getType()) {
            return false;
        }
        if (!Lang.equals(typeName, other.getTypeName())) {
            return false;
        }
        if (table != null && Lang.isNotEmpty(table.getName())) {
            if (!Lang.equals(table.getName(), other.getTable().getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Column [name=" + name + ", sqlType=" + sqlType + ", type=" + type + ", typeName=" + typeName + ", size="
            + size + ", remark=" + remark + ", defaultValue=" + defaultValue + ", nullable=" + nullable
            + ", columnIndex=" + columnIndex + ", primaryKey=" + primaryKey + ", decimalDigits=" + decimalDigits
            + ", autoincrement=" + autoincrement + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullable() {
        return nullable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDecimalDigits() {
        return decimalDigits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLType getSqlType() {
        return sqlType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable() {
        return table;
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
