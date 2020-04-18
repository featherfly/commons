
package cn.featherfly.common.db.metadata;

/**
 * <p>
 * 列元数据
 * </p>
 *
 * @author zhongj
 */
public class ColumnMetadata implements Column {

    /**
     * @param tableMetadata 表元数据
     */
    public ColumnMetadata(TableMetadata tableMetadata) {
        this.tableMetadata = tableMetadata;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        if (getName() != null && getName().length() > 0) {
            byte[] bs = getName().getBytes();
            for (byte b : bs) {
                hashCode += b;
            }
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass().isInstance(obj)) {
            ColumnMetadata cm = (ColumnMetadata) obj;
            if (getTableMetadata() == null || !getTableMetadata().equals(cm.getTableMetadata())) {
                return false;
            }
            return getName().equals(cm.getName());
        }
        return false;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private TableMetadata tableMetadata;

    private String name;

    private int type;

    private String typeName;

    private int size;

    private String remark;

    private String defaultValue;

    private boolean nullable;

    private int columnIndex;

    private boolean primaryKey;

    private SqlType sqlType;

    /**
     * 小数位数
     */
    private int decimalDigits;

    private boolean autoincrement;

    /**
     * @return 返回primaryKey
     */
    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey 设置primaryKey
     */
    void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return 返回type
     */
    @Override
    public int getType() {
        return type;
    }

    /**
     * @param type 设置type
     */
    void setType(int type) {
        this.type = type;
        sqlType = SqlType.value(type);
    }

    /**
     * @return 返回columnIndex
     */
    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * @param columnIndex 设置columnIndex
     */
    void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * @return 返回size
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * @param size 设置size
     */
    void setSize(int size) {
        this.size = size;
    }

    /**
     * @return 返回tableMetadata
     */
    public TableMetadata getTableMetadata() {
        return tableMetadata;
    }

    /**
     * @return 返回name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name 设置name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @return 返回typeName
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName 设置typeName
     */
    void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return 返回remarks
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 设置remark
     */
    void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 返回defaultValue
     */
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue 设置defaultValue
     */
    void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return 返回nullable
     */
    @Override
    public boolean isNullable() {
        return nullable;
    }

    /**
     * @param nullable 设置nullable
     */
    void setNullable(boolean nullable) {
        this.nullable = nullable;
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
     * 返回autoincrement
     *
     * @return autoincrement
     */
    @Override
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * 设置decimalDigits
     *
     * @param decimalDigits decimalDigits
     */
    void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 设置autoincrement
     *
     * @param autoincrement autoincrement
     */
    void setAutoincrement(boolean autoincrement) {
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
    void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
        type = sqlType.getValue();
    }
}
