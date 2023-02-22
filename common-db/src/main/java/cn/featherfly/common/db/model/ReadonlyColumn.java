
package cn.featherfly.common.db.model;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.db.Table;

/**
 * The Class ReadonlyColumn.
 *
 * @author zhongj
 */
public class ReadonlyColumn extends AbstractColumn {

    /**
     * Instantiates a new readonly column.
     *
     * @param name          the name
     * @param sqlType       the sql type
     * @param typeName      the type name
     * @param size          the size
     * @param remark        the remark
     * @param defaultValue  the default value
     * @param nullable      the nullable
     * @param columnIndex   the column index
     * @param primaryKey    the primary key
     * @param decimalDigits the decimal digits
     * @param autoincrement the autoincrement
     * @param table         the table
     */
    public ReadonlyColumn(String name, SQLType sqlType, String typeName, int size, String remark, String defaultValue,
            boolean nullable, int columnIndex, boolean primaryKey, int decimalDigits, boolean autoincrement,
            Table table) {
        this(name, sqlType, sqlType.getVendorTypeNumber(), typeName, size, remark, defaultValue, nullable, columnIndex,
                primaryKey, decimalDigits, autoincrement, table);
    }

    /**
     * Instantiates a new readonly column.
     *
     * @param name          the name
     * @param type          the type
     * @param typeName      the type name
     * @param size          the size
     * @param remark        the remark
     * @param defaultValue  the default value
     * @param nullable      the nullable
     * @param columnIndex   the column index
     * @param primaryKey    the primary key
     * @param decimalDigits the decimal digits
     * @param autoincrement the autoincrement
     * @param table         the table
     */
    public ReadonlyColumn(String name, int type, String typeName, int size, String remark, String defaultValue,
            boolean nullable, int columnIndex, boolean primaryKey, int decimalDigits, boolean autoincrement,
            Table table) {
        this(name, JDBCType.valueOf(type), type, typeName, size, remark, defaultValue, nullable, columnIndex,
                primaryKey, decimalDigits, autoincrement, table);
    }

    /**
     * Instantiates a new readonly column.
     *
     * @param name          the name
     * @param sqlType       the sql type
     * @param type          the type
     * @param typeName      the type name
     * @param size          the size
     * @param remark        the remark
     * @param defaultValue  the default value
     * @param nullable      the nullable
     * @param columnIndex   the column index
     * @param primaryKey    the primary key
     * @param decimalDigits the decimal digits
     * @param autoincrement the autoincrement
     * @param table         the table
     */
    public ReadonlyColumn(String name, SQLType sqlType, int type, String typeName, int size, String remark,
            String defaultValue, boolean nullable, int columnIndex, boolean primaryKey, int decimalDigits,
            boolean autoincrement, Table table) {
        super();
        this.name = name;
        this.sqlType = sqlType;
        this.type = type;
        this.typeName = typeName;
        this.size = size;
        this.remark = remark;
        this.defaultValue = defaultValue;
        this.nullable = nullable;
        this.columnIndex = columnIndex;
        this.primaryKey = primaryKey;
        this.decimalDigits = decimalDigits;
        this.autoincrement = autoincrement;
        this.table = table;
    }

}
