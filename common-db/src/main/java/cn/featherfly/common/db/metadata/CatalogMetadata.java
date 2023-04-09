
package cn.featherfly.common.db.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * CatalogMetadata.
 *
 * @author zhongj
 */
public class CatalogMetadata {

    /**
     * Instantiates a new catalog metadata.
     *
     * @param databaseMetadata the database metadata
     */
    public CatalogMetadata(DatabaseMetadata databaseMetadata) {
        super();
        this.databaseMetadata = databaseMetadata;
    }

    /**
     * 返回指定名称的模式元数据对象. 没有找到返回null.
     *
     * @param schemaName 模式名称
     * @return 模式元数据对象
     */
    public SchemaMetadata getSchema(String schemaName) {
        return schemaMap.get(schemaName.toUpperCase());
    }

    /**
     * 返回所有模式元数据对象的集合.
     *
     * @return 所有模式元数据对象的集合
     */
    public Collection<SchemaMetadata> getSchemas() {
        return schemaMap.values();
    }

    //    /**
    //     * 返回所有模式元数据对象的MAP.
    //     *
    //     * @return 所有模式元数据对象的MAP
    //     */
    //    public Map<String, SchemaMetadata> getSchemaMap() {
    //        return schemaMap;
    //    }

    /**
     * 添加模式元数据.
     *
     * @param schema 模式元数据对象
     */
    void addSchema(SchemaMetadata schema) {
        addSchema(schema, false);
    }

    /**
     * 添加模式元数据.
     *
     * @param schema    模式元数据对象
     * @param isDefault is default schema
     */
    void addSchema(SchemaMetadata schema, boolean isDefault) {
        if (isDefault) {
            this.schema = schema;
        }
        schemaMap.put(schema.getName().toUpperCase(), schema);
    }

    /**
     * 添加模式元数据.
     *
     * @param tables 模式元数据对象数组
     */
    void addSchema(SchemaMetadata... schemas) {
        for (SchemaMetadata schema : schemas) {
            addSchema(schema);
        }
    }

    /**
     * 添加模式元数据.
     *
     * @param tables 模式元数据对象集合
     */
    void addTable(Collection<SchemaMetadata> schemas) {
        for (SchemaMetadata schema : schemas) {
            addSchema(schema);
        }
    }

    // ********************************************************************
    //  property
    // ********************************************************************

    private DatabaseMetadata databaseMetadata;

    private SchemaMetadata schema; // default schema

    private Map<String, SchemaMetadata> schemaMap = new HashMap<>(0);

    private String name;

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
     * 返回databaseMetadata
     *
     * @return databaseMetadata
     */
    public DatabaseMetadata getDatabaseMetadata() {
        return databaseMetadata;
    }

    /**
     * get default schema
     *
     * @return schema
     */
    public SchemaMetadata getSchema() {
        return schema;
    }
}
