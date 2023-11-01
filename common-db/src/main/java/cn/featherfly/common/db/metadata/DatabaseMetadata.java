
package cn.featherfly.common.db.metadata;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.Table;

/**
 * database metadata. 数据库元数据.
 *
 * @author zhongj
 */
public class DatabaseMetadata {

    /**
     * Instantiates a new database metadata.
     *
     * @param databaseMetaData the database meta data
     */
    DatabaseMetadata(DatabaseMetaData databaseMetaData) {
        reload(databaseMetaData);
    }

    /**
     * Reload.
     *
     * @param databaseMetaData the database meta data
     */
    public void reload(DatabaseMetaData databaseMetaData) {
        try {
            productName = databaseMetaData.getDatabaseProductName();
            productVersion = databaseMetaData.getDatabaseProductVersion();
            majorVersion = databaseMetaData.getDatabaseMajorVersion();
            minorVersion = databaseMetaData.getDatabaseMinorVersion();

            jdbcMajorVersion = databaseMetaData.getJDBCMajorVersion();
            jdbcMinorVersion = databaseMetaData.getJDBCMinorVersion();

            driverMajorVersion = databaseMetaData.getDriverMajorVersion();
            driverMinorVersion = databaseMetaData.getDriverMinorVersion();
            driverName = databaseMetaData.getDriverName();
            driverVersion = databaseMetaData.getDriverVersion();

            jdbcMajorVersion = databaseMetaData.getJDBCMajorVersion();
            jdbcMinorVersion = databaseMetaData.getJDBCMinorVersion();

            features = new Features(databaseMetaData);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
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

    /**
     * Adds the catalog.
     *
     * @param catalog   the catalog
     * @param isDefault the is default
     */
    void addCatalog(CatalogMetadata catalog, boolean isDefault) {
        if (isDefault) {
            defaultCatalog = catalog;
        }
        catalogMap.put(catalog.getName(), catalog);
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
    public Collection<SchemaMetadata> getSchemas() {
        if (defaultCatalog == null) {
            return Collections.emptyList();
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
    public Collection<Table> getTables() {
        if (defaultCatalog == null) {
            return Collections.emptyList();
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

    private String name;

    private String productName;

    private String productVersion;

    private Integer majorVersion;

    private Integer minorVersion;

    private String driverName;

    private String driverVersion;

    private Integer driverMajorVersion;

    private Integer driverMinorVersion;

    private Integer jdbcMajorVersion;

    private Integer jdbcMinorVersion;

    private Features features;

    /**
     * Gets the features.
     *
     * @return the features
     */
    public Features getFeatures() {
        return features;
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
     * 返回majorVersion.
     *
     * @return majorVersion
     */
    public Integer getMajorVersion() {
        return majorVersion;
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
     * get driverName value.
     *
     * @return driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * get driverVersion value.
     *
     * @return driverVersion
     */
    public String getDriverVersion() {
        return driverVersion;
    }

    /**
     * get driverMajorVersion value.
     *
     * @return driverMajorVersion
     */
    public Integer getDriverMajorVersion() {
        return driverMajorVersion;
    }

    /**
     * get driverMinorVersion value.
     *
     * @return driverMinorVersion
     */
    public Integer getDriverMinorVersion() {
        return driverMinorVersion;
    }

    /**
     * get jdbcMajorVersion value.
     *
     * @return jdbcMajorVersion
     */
    public Integer getJdbcMajorVersion() {
        return jdbcMajorVersion;
    }

    /**
     * get jdbcMinorVersion value.
     *
     * @return jdbcMinorVersion
     */
    public Integer getJdbcMinorVersion() {
        return jdbcMinorVersion;
    }
}
