
package cn.featherfly.common.db.metadata;

import java.util.Collection;

import cn.featherfly.common.db.model.AbstractTable;
import cn.featherfly.common.repository.Index;

/**
 * <p>
 * 表元数据
 * </p>
 * .
 *
 * @author zhongj
 */
public class TableMetadata extends AbstractTable<ColumnMetadata> {

    /**
     * Instantiates a new table metadata.
     *
     * @param databaseMetadata 数据库元数据
     */
    public TableMetadata(DatabaseMetadata databaseMetadata) {
        this.databaseMetadata = databaseMetadata;
    }

    /**
     * 添加列元数据.
     *
     * @param columnMetadata 列元数据对象
     */
    void addColumn(ColumnMetadata columnMetadata) {
        add(columnMetadata);
    }

    /**
     * 添加列元数据.
     *
     * @param columnMetadatas 列元数据对象数组
     */
    void addColumn(ColumnMetadata... columnMetadatas) {
        for (ColumnMetadata columnMetadata : columnMetadatas) {
            addColumn(columnMetadata);
        }
    }

    /**
     * 添加列元数据.
     *
     * @param columnMetadatas 列元数据对象集合
     */
    void addColumn(Collection<ColumnMetadata> columnMetadatas) {
        for (ColumnMetadata columnMetadata : columnMetadatas) {
            addColumn(columnMetadata);
        }
    }

    /**
     * 添加索引.
     *
     * @param index 索引
     */
    void addIndex(Index index) {
        add(index);
    }

    /**
     * 添加索引.
     *
     * @param indexs 索引对象数组
     */
    void addIndex(Index... indexs) {
        for (Index index : indexs) {
            addIndex(index);
        }
    }

    /**
     * 添加索引.
     *
     * @param indexs 索引集合
     */
    void addIndex(Collection<Index> indexs) {
        for (Index index : indexs) {
            addIndex(index);
        }
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private DatabaseMetadata databaseMetadata;

    /**
     * Gets the database metadata.
     *
     * @return 返回databaseMetadata
     */
    public DatabaseMetadata getDatabaseMetadata() {
        return databaseMetadata;
    }

    /**
     * Sets the type.
     *
     * @param type 设置type
     */
    void setType(String type) {
        this.type = type;
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
     * Sets the remark.
     *
     * @param remark 设置remarks
     */
    void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Sets the catalog.
     *
     * @param catalog 设置catalog
     */
    void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * Sets the schema.
     *
     * @param schema the new schema
     */
    void setSchema(String schema) {
        this.schema = schema;
    }
}
