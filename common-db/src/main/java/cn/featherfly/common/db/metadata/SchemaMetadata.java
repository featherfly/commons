
package cn.featherfly.common.db.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.Table;

/**
 * SchemaMetadata.
 *
 * @author zhongj
 */
public class SchemaMetadata {

    /**
     * @param catalogMetadata
     */
    public SchemaMetadata(CatalogMetadata catalogMetadata) {
        super();
        this.catalogMetadata = catalogMetadata;
    }

    /**
     * 添加表元数据.
     *
     * @param table 表元数据对象
     */
    void addTable(Table table) {
        tableMap.put(table.getName().toUpperCase(), table);
    }

    /**
     * 添加表元数据.
     *
     * @param tables 表元数据对象数组
     */
    void addTable(Table... tables) {
        for (Table Table : tables) {
            addTable(Table);
        }
    }

    /**
     * 添加表元数据.
     *
     * @param tables 表元数据对象集合
     */
    void addTable(Collection<Table> tables) {
        for (Table Table : tables) {
            addTable(Table);
        }
    }

    // ********************************************************************
    //  property
    // ********************************************************************

    private CatalogMetadata catalogMetadata;

    private String name;

    private Map<String, Table> tableMap = new HashMap<>(0);

    /**
     * 返回指定名称的表元数据对象. 没有找到返回null.
     *
     * @param tableName 表名称
     * @return 表元数据对象
     */
    public Table getTable(String tableName) {
        return tableMap.get(tableName.toUpperCase());
    }

    /**
     * 返回所有表元数据对象的集合.
     *
     * @return 所有表元数据对象的集合
     */
    public Collection<Table> getTables() {
        return tableMap.values();
    }

    //    /**
    //     * 返回所有表元数据对象的MAP.
    //     *
    //     * @return 所有表元数据对象的MAP
    //     */
    //    public Map<String, Table> getTableMap() {
    //        return tableMap;
    //    }

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
    void setName(String name) {
        this.name = name;
    }

    /**
     * 返回catalogMetadata
     *
     * @return catalogMetadata
     */
    public CatalogMetadata getCatalogMetadata() {
        return catalogMetadata;
    }

}
