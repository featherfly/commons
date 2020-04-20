
package cn.featherfly.common.db.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;

/**
 * <p>
 * 表元数据
 * </p>
 *
 * @author zhongj
 */
public class TableMetadata implements Table {

    /**
     * @param databaseMetadata 数据库元数据
     */
    public TableMetadata(DatabaseMetadata databaseMetadata) {
        this.databaseMetadata = databaseMetadata;
    }

    /**
     * <p>
     * 返回指定名称的列元数据对象. 没有找到返回null.
     * </p>
     *
     * @param columnName 表名称
     * @return 列元数据对象
     */
    @Override
    public Column getColumn(String columnName) {
        return columnMap.get(columnName.toUpperCase());
    }

    /**
     * <p>
     * 返回所有列元数据对象的集合.
     * </p>
     *
     * @return 所有列元数据对象的集合
     */
    @Override
    public Collection<Column> getColumns() {
        return columnMap.values();
    }

    /**
     * <p>
     * 返回所有列元数据对象的MAP.
     * </p>
     *
     * @return 所有列元数据对象的MAP
     */
    @Override
    public Map<String, Column> getColumnMap() {
        return columnMap;
    }

    /**
     * <p>
     * 返回所有主键列元数据对象的列表.
     * </p>
     *
     * @return 所有主键列元数据对象的列表.
     */
    @Override
    public List<Column> getPrimaryColumns() {
        return primaryColumns;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columnMetadata 列元数据对象
     */
    public void addColumn(ColumnMetadata columnMetadata) {
        if (columnMetadata.isPrimaryKey()) {
            primaryColumns.add(columnMetadata);
        }
        columnMap.put(columnMetadata.getName().toUpperCase(), columnMetadata);
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columnMetadatas 列元数据对象数组
     */
    public void addColumn(ColumnMetadata... columnMetadatas) {
        for (ColumnMetadata columnMetadata : columnMetadatas) {
            addColumn(columnMetadata);
        }
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columnMetadatas 列元数据对象集合
     */
    public void addColumn(Collection<ColumnMetadata> columnMetadatas) {
        for (ColumnMetadata columnMetadata : columnMetadatas) {
            addColumn(columnMetadata);
        }
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
            TableMetadata tm = (TableMetadata) obj;
            if (getCatalog() == null) {
                return getName().equals(tm.getName());
            } else {
                return getName().equals(tm.getName()) && getCatalog().equals(tm.getCatalog());
            }
        }
        return false;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private String type;

    private String name;

    private String remark;

    private String catalog;

    private DatabaseMetadata databaseMetadata;

    private List<Column> primaryColumns = new ArrayList<>(1);

    private Map<String, Column> columnMap = new HashMap<>(0);

    /**
     * @return 返回databaseMetadata
     */
    public DatabaseMetadata getDatabaseMetadata() {
        return databaseMetadata;
    }

    /**
     * @return 返回type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @param type 设置type
     */
    void setType(String type) {
        this.type = type;
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
     * @return 返回remarks
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 设置remarks
     */
    void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 返回catalog
     */
    @Override
    public String getCatalog() {
        return catalog;
    }

    /**
     * @param catalog 设置catalog
     */
    void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
