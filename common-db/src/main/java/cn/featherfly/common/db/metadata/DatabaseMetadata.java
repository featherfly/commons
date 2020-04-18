
package cn.featherfly.common.db.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 库元数据
 * </p>
 *
 * @author zhongj
 */
public class DatabaseMetadata {

    /**
     */
    DatabaseMetadata() {
    }

    /**
     * <p>
     * 返回指定名称的表元数据对象. 没有找到返回null.
     * </p>
     *
     * @param tableName 表名称
     * @return 表元数据对象
     */
    public TableMetadata getTable(String tableName) {
        return tableMap.get(tableName.toUpperCase());
    }

    /**
     * <p>
     * 返回所有表元数据对象的集合.
     * </p>
     *
     * @return 所有表元数据对象的集合
     */
    public Collection<TableMetadata> getTables() {
        return tableMap.values();
    }

    /**
     * <p>
     * 返回所有表元数据对象的MAP.
     * </p>
     *
     * @return 所有表元数据对象的MAP
     */
    public Map<String, TableMetadata> getTableMap() {
        return tableMap;
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param tableMetadata 表元数据对象
     */
    public void addTable(TableMetadata tableMetadata) {
        tableMap.put(tableMetadata.getName().toUpperCase(), tableMetadata);
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param tableMetadatas 表元数据对象数组
     */
    public void addTable(TableMetadata... tableMetadatas) {
        for (TableMetadata tableMetadata : tableMetadatas) {
            addTable(tableMetadata);
        }
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param tableMetadatas 表元数据对象集合
     */
    public void addTable(Collection<TableMetadata> tableMetadatas) {
        for (TableMetadata tableMetadata : tableMetadatas) {
            addTable(tableMetadata);
        }
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private Map<String, TableMetadata> tableMap = new HashMap<>(0);

    private String name;

    /**
     * 返回name
     *
     * @return name
     */
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
}
