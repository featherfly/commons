
package cn.featherfly.common.db.metadata;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.Table;

/**
 * <p>
 * 库元数据
 * </p>
 * .
 *
 * @author zhongj
 */
public class DatabaseMetadata {
    // FIXME 多个schema内有相同名称表的会出错
    // TODO 后续加入多个schema管理，当前的方法都不该，只是代理到default schema去获取
    /**
     * Instantiates a new database metadata.
     */
    DatabaseMetadata() {
    }

    // ********************************************************************
    //	catalog start
    // ********************************************************************

    /**
     * Gets the catalog.
     *
     * @param catalogName the catalog name
     * @return the catalog metadata
     */
    public CatalogMetadata getCatalog(String catalogName) {
        return catalogMap.get(catalogName);
    }

    /**
     * Gets the catalogs.
     *
     * @return the catalog metadata collection
     */
    public Collection<CatalogMetadata> getCatalogs() {
        return catalogMap.values();
    }

    //    /**
    //     * Gets the catalog map.
    //     *
    //     * @return the catalog map
    //     */
    //    public Map<String, CatalogMetadata> getCatalogMap() {
    //        return catalogMap;
    //    }

    /**
     * Adds the table.
     *
     * @param catalogs the catalogs
     */
    void addCatalog(CatalogMetadata... catalogs) {
        for (CatalogMetadata catalog : catalogs) {
            catalogMap.put(catalog.getName(), catalog);
        }
    }

    /**
     * Adds the catalog.
     *
     * @param catalogs the catalogs
     */
    void addCatalog(Collection<CatalogMetadata> catalogs) {
        for (CatalogMetadata catalog : catalogs) {
            catalogMap.put(catalog.getName(), catalog);
        }
    }

    // ********************************************************************
    //	catalog end
    // ********************************************************************

    /**
     * 默认catalog 返回指定名称的模式（schema）. 没有找到返回null.
     *
     * @param schemaName the schema name
     * @return the schema
     * @see cn.featherfly.common.db.metadata.CatalogMetadata#getSchema(java.lang.String)
     */
    public SchemaMetadata getSchema(String schemaName) {
        if (defaultCatalog == null) {
            return null;
        }
        return defaultCatalog.getSchema(schemaName);
    }

    /**
     * 默认catalog 返回模式（schema）集合.
     *
     * @return the schemas
     * @see cn.featherfly.common.db.metadata.CatalogMetadata#getSchemas()
     */
    @SuppressWarnings("unchecked")
    public Collection<SchemaMetadata> getSchemas() {
        if (defaultCatalog == null) {
            return Collections.EMPTY_LIST;
        }
        return defaultCatalog.getSchemas();
    }

    //    /**
    //     * 默认catalog 返回模式（schema）MAP.
    //     *
    //     * @return the schema map
    //     * @see cn.featherfly.common.db.metadata.CatalogMetadata#getSchemaMap()
    //     */
    //    public Map<String, SchemaMetadata> getSchemaMap() {
    //        return defaultCatalog.getSchemaMap();
    //    }

    /**
     * 默认模式（schema）返回指定名称的表元数据对象. 没有找到返回null.
     *
     * @param tableName 表名称
     * @return 表元数据对象
     */
    public Table getTable(String tableName) {
        if (defaultCatalog == null) {
            return null;
        }
        return defaultCatalog.getSchema().getTable(tableName);
    }

    /**
     * 默认模式（schema）返回表元数据对象的集合.
     *
     * @return 所有表元数据对象的集合
     */
    @SuppressWarnings("unchecked")
    public Collection<Table> getTables() {
        if (defaultCatalog == null) {
            return Collections.EMPTY_LIST;
        }
        return defaultCatalog.getSchema().getTables();
    }

    //    /**
    //     * 默认模式（schema）返回表元数据对象的MAP.
    //     *
    //     * @return 所有表元数据对象的MAP
    //     */
    //    public Map<String, Table> getTableMap() {
    //        //        return tableMap;
    //        return defaultSchema.getTableMap();
    //    }

    //    /**
    //     * 添加表元数据到默认模式（schema）.
    //     *
    //     * @param table 表元数据对象
    //     */
    //    void addTable(Table table) {
    //        //        tableMap.put(table.getName().toUpperCase(), table);
    //        defaultSchema.addTable(table);
    //    }
    //
    //    /**
    //     * 添加表元数据到默认模式（schema）.
    //     *
    //     * @param tables 表元数据对象数组
    //     */
    //    void addTable(Table... tables) {
    //        for (Table Table : tables) {
    //            addTable(Table);
    //        }
    //    }
    //
    //    /**
    //     * 添加表元数据到默认模式（schema）.
    //     *
    //     * @param tables 表元数据对象集合
    //     */
    //    void addTable(Collection<Table> tables) {
    //        for (Table Table : tables) {
    //            addTable(Table);
    //        }
    //    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private CatalogMetadata defaultCatalog;

    private Map<String, CatalogMetadata> catalogMap = new HashMap<>(0);

    //    private Map<String, Table> tableMap = new HashMap<>(0);

    private String name;

    private String productName;

    private String productVersion;

    private Integer majorVersion;

    private Integer minorVersion;

    private boolean supportsBatchUpdates;

    /**
     * Sets the supports batch update.
     *
     * @param supportsBatchUpdates the new supports batch update
     */
    void setSupportsBatchUpdate(boolean supportsBatchUpdates) {
        this.supportsBatchUpdates = supportsBatchUpdates;
    }

    /**
     * Supports batch updates.
     *
     * @return true, if successful
     */
    public boolean supportsBatchUpdates() {
        return supportsBatchUpdates;
    }

    /**
     * 返回name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name.
     *
     * @param name name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * 返回productName.
     *
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 返回productVersion.
     *
     * @return productVersion
     */
    public String getProductVersion() {
        return productVersion;
    }

    /**
     * 设置productName.
     *
     * @param productName productName
     */
    void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 设置productVersion.
     *
     * @param productVersion productVersion
     */
    void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    /**
     * 返回majorVersion.
     *
     * @return majorVersion
     */
    public Integer getMajorVersion() {
        return majorVersion;
    }

    /**
     * 设置majorVersion.
     *
     * @param majorVersion majorVersion
     */
    void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    /**
     * 返回minorVersion.
     *
     * @return minorVersion
     */
    public Integer getMinorVersion() {
        return minorVersion;
    }

    /**
     * 设置minorVersion.
     *
     * @param minorVersion minorVersion
     */
    void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }
}
