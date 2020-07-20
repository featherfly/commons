
package cn.featherfly.common.db.metadata;

import java.util.Collection;

import cn.featherfly.common.db.model.AbstractTable;

/**
 * <p>
 * 表元数据
 * </p>
 *
 * @author zhongj
 */
public class TableMetadata extends AbstractTable<ColumnMetadata> {

    /**
     * @param databaseMetadata 数据库元数据
     */
    public TableMetadata(DatabaseMetadata databaseMetadata) {
        this.databaseMetadata = databaseMetadata;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columnMetadata 列元数据对象
     */
    public void addColumn(ColumnMetadata columnMetadata) {
        add(columnMetadata);
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

    // ********************************************************************
    //	property
    // ********************************************************************

    private DatabaseMetadata databaseMetadata;

    /**
     * @return 返回databaseMetadata
     */
    public DatabaseMetadata getDatabaseMetadata() {
        return databaseMetadata;
    }

    /**
     * @param type 设置type
     */
    void setType(String type) {
        this.type = type;
    }

    /**
     * @param name 设置name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @param remark 设置remarks
     */
    void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @param catalog 设置catalog
     */
    void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
