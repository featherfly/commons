
package cn.featherfly.common.db.migration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.SqlExecutor;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.mapping.ClassMappingUtils;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * <p>
 * Migrator
 * </p>
 * .
 *
 * @author zhongj
 */
public class Migrator {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Migrator.class);

    /**
     * The Enum ModifyType.
     */
    public enum ModifyType {

        /** The modify. */
        MODIFY,

        /** The drop and create. */
        DROP_AND_CREATE
    }

    /** The dialect. */
    private Dialect dialect;

    /** The sql executor. */
    private SqlExecutor sqlExecutor;

    /** The sql type mapping manager. */
    private SqlTypeMappingManager sqlTypeMappingManager;

    /** The database name. */
    private String schema;

    /**
     * Instantiates a new migrator.
     *
     * @param dataSource            the data source
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public Migrator(DataSource dataSource, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager) {
        this(dataSource, dialect, sqlTypeMappingManager, true);
    }

    /**
     * Instantiates a new migrator.
     *
     * @param dataSource            the data source
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param generateSchema        the generate schema
     */
    public Migrator(DataSource dataSource, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager,
            boolean generateSchema) {
        this(dataSource, dialect, sqlTypeMappingManager, generateSchema,
                dialect.getDefaultSchema(JdbcUtils.getCatalog(dataSource)));
    }

    /**
     * Instantiates a new migrator.
     *
     * @param dataSource            the data source
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param generateSchema        the generate schema
     * @param schema                the schema
     */
    public Migrator(DataSource dataSource, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager,
            boolean generateSchema, String schema) {
        super();
        this.dialect = dialect;
        this.sqlTypeMappingManager = sqlTypeMappingManager;
        sqlExecutor = new SqlExecutor(dataSource);
        if (generateSchema) {
            this.schema = schema;
        }
    }

    /**
     * get the init sql.
     *
     * @param classMappings the class mappings
     * @return the inits the sql
     */
    public String initSql(Set<ClassMapping<?>> classMappings) {
        StringBuilder sql = new StringBuilder();
        sql.append(dialect.getInitSqlHeader()).append(Chars.SEMI).append(Chars.NEW_LINE);
        for (ClassMapping<?> classMapping : classMappings) {
            sql.append(createSql(classMapping, true)).append(Chars.SEMI).append(Chars.NEW_LINE);
        }
        sql.append(dialect.getInitSqlFooter()).append(Chars.SEMI).append(Chars.NEW_LINE);
        String result = sql.toString();
        if (Lang.isEmpty(schema)) {
            LOGGER.debug("create init sql -> \n{}", result);
        } else {
            LOGGER.debug("create init sql for {} -> \n{}", schema, result);
        }
        return result;
    }

    /**
     * Creates the sql.
     *
     * @param classMapping the class mapping
     * @return the string
     */
    public String createSql(ClassMapping<?> classMapping) {
        return createSql(classMapping, false);
    }

    /**
     * Creates the sql.
     *
     * @param classMapping the class mapping
     * @param dropIfExists the drop if exists
     * @return the string
     */
    public String createSql(ClassMapping<?> classMapping, boolean dropIfExists) {
        return createSql(ClassMappingUtils.createTable(classMapping, dialect, sqlTypeMappingManager), dropIfExists,
                classMapping.getType());
    }

    /**
     * Creates the sql.
     *
     * @param table        the table
     * @param dropIfExists the drop if exists
     * @param type         the type
     * @return the string
     */
    private String createSql(Table table, boolean dropIfExists, Class<?> type) {
        StringBuilder sql = new StringBuilder();
        if (dropIfExists) {
            appendSqlWithEnd(sql, dialect.buildDropTableDDL(schema, table.getName(), true));
        }
        String result = sql.append(dialect.buildCreateTableDDL(table)).toString();
        LOGGER.debug("create sql for entity {} -> \n{}", type.getName(), result);
        return result;
    }

    /**
     * create database info for classMappings.
     *
     * @param classMappings the class mappings
     */
    public void create(Set<ClassMapping<?>> classMappings) {
        sqlExecutor.execute(initSql(classMappings));
    }

    /**
     * Gets the update sql.
     *
     * @param classMappings the class mappings
     * @return the inits the sql
     */
    public String updateSql(Set<ClassMapping<?>> classMappings) {
        return updateSql(classMappings, ModifyType.MODIFY, false);
    }

    /**
     * Update sql.
     *
     * @param classMappings the class mappings
     * @param modifyType    the modify type for table and column
     * @param dropNoMapping the drop no mapping for table and column
     * @return the string
     */
    public String updateSql(Set<ClassMapping<?>> classMappings, ModifyType modifyType, boolean dropNoMapping) {
        return updateSql(classMappings, modifyType, dropNoMapping, modifyType, dropNoMapping);
    }

    /**
     * Gets the update sql.
     *
     * @param classMappings       the class mappings
     * @param tableModifyType     the table modify type
     * @param dropNoMappingTable  if true, drop the table which no mapping with
     *                            object; if false, do nothing.
     * @param columnModifyType    the column modify type
     * @param dropNoMappingColumn if true, drop the column which no mapping with
     *                            object; if false, do nothing.
     * @return the inits the sql
     */
    public String updateSql(Set<ClassMapping<?>> classMappings, ModifyType tableModifyType, boolean dropNoMappingTable,
            ModifyType columnModifyType, boolean dropNoMappingColumn) {
        // TODO 这里是否需要reCreate后续再来确定
        //        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().reCreate(sqlExecutor.getDataSource());
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(sqlExecutor.getDataSource());

        UpdateMapping updateMapping = new UpdateMapping();

        // TODO 判断index的更新情况
        Set<String> tableNameSet = new HashSet<>();
        for (ClassMapping<?> classMapping : classMappings) {
            Table tableMetadata = metadata.getTable(classMapping.getRepositoryName());
            Table table = ClassMappingUtils.createTable(classMapping, dialect, sqlTypeMappingManager);
            tableNameSet.add(table.getName());
            if (tableMetadata == null) {
                // 数据库没有该表，添加新表
                updateMapping.newClassMappings.put(classMapping, table);
            } else if (!tableMetadata.equals(table)) {
                ModifyTable modifyTable = updateMapping.modifyTables.getModifyTable(table);
                if (modifyTable == null) {
                    modifyTable = new ModifyTable(table, classMapping);
                    updateMapping.modifyTables.put(modifyTable);
                }
                for (Column column : table.getColumns()) {
                    Column columnMetadata = tableMetadata.getColumn(column.getName());
                    if (columnMetadata == null) {
                        // 数据库元数据没有该列，添加新列
                        modifyTable.newColumns.put(classMapping.getPropertyMappingByPersitField(column.getName()),
                                column);
                    } else if (!columnMetadata.equals(column)) {
                        //                        System.err.println(column);
                        //                        System.err.println(columnMetadata);
                        modifyTable.modifyColumns.put(classMapping.getPropertyMappingByPersitField(column.getName()),
                                column);
                    }
                }
                for (Column columnMetadata : tableMetadata.getColumns()) {
                    // 判断数据库表的列是否没有映射
                    if (table.getColumn(columnMetadata.getName()) == null) {
                        //                        String key = table.getName().toUpperCase();
                        modifyTable.noMappingColumns.add(columnMetadata);
                    }
                }
            }
        }
        for (Table tableMetadata : metadata.getTables()) {
            // 判断数据库表是否没有映射
            if (!tableNameSet.contains(tableMetadata.getName())) {
                updateMapping.noMappingTables.add(tableMetadata);
            }
        }
        StringBuilder sql = new StringBuilder();
        // 添加头部
        sql.append(dialect.getInitSqlHeader()).append(Chars.SEMI).append(Chars.NEW_LINE);
        // 添加新的对象映射
        updateMapping.newClassMappings.forEach((classMapping, table) -> {
            sql.append(createSql(table, true, classMapping.getType())).append(Chars.SEMI).append(Chars.NEW_LINE);
        });
        for (Entry<Table, ModifyTable> entry : updateMapping.modifyTables.modifyTableMap.entrySet()) {
            Table table = entry.getKey();
            ModifyTable modifyTable = entry.getValue();
            if (ModifyType.MODIFY == tableModifyType) {
                List<Column> dropColumns = new ArrayList<>();
                if (dropNoMappingColumn) {
                    dropColumns.addAll(modifyTable.noMappingColumns);
                }
                if (ModifyType.MODIFY == columnModifyType) {
                    appendSqlWithEnd(sql, dialect.buildAlterTableDDL(schema, table.getName(),
                            //  添加新的属性列映射
                            CollectionUtils.toArray(modifyTable.newColumns.values(), Column.class),
                            // 需要修改的对象映射
                            CollectionUtils.toArray(modifyTable.modifyColumns.values(), Column.class),
                            // 删除没有对象映射的列
                            CollectionUtils.toArray(dropColumns, Column.class)));
                } else if (ModifyType.DROP_AND_CREATE == columnModifyType) {
                    dropColumns.addAll(modifyTable.modifyColumns.values());

                    appendSqlWithEnd(sql, dialect.buildAlterTableDropColumnDDL(schema, table.getName(),
                            CollectionUtils.toArray(dropColumns, Column.class)));

                    List<Column> addColumns = new ArrayList<>();
                    addColumns.addAll(modifyTable.newColumns.values());
                    addColumns.addAll(modifyTable.modifyColumns.values());

                    appendSqlWithEnd(sql, dialect.buildAlterTableDDL(schema, table.getName(),
                            //  添加新的属性列映射
                            CollectionUtils.toArray(addColumns, Column.class),
                            // 需要修改的对象映射
                            new Column[] {},
                            // 删除没有对象映射的列
                            CollectionUtils.toArray(dropColumns, Column.class)));
                } else {
                    throw new JdbcMappingException("no support ModifyType for columnModifyType -> " + columnModifyType);
                }
            } else if (ModifyType.DROP_AND_CREATE == tableModifyType) {
                appendSqlWithEnd(sql, createSql(modifyTable.classMapping, true));
            } else {
                throw new JdbcMappingException("no support ModifyType for tableModifyType -> " + tableModifyType);
            }

        }
        // 删除没有对象映射的表
        if (dropNoMappingTable) {
            updateMapping.noMappingTables.forEach(table -> {
                appendSqlWithEnd(sql, dialect.buildDropTableDDL(schema, table.getName()));
            });
        }
        // 添加尾部
        sql.append(dialect.getInitSqlFooter()).append(Chars.SEMI).append(Chars.NEW_LINE);
        String result = sql.toString();
        if (Lang.isEmpty(schema)) {
            LOGGER.debug("create update sql -> \n{}", result);
        } else {
            LOGGER.debug("create update sql for {} -> \n{}", schema, result);
        }
        return result;
    }

    //    /**
    //     * Gets the update sql.
    //     *
    //     * @param classMapping         the class mapping
    //     * @param propertyMappings     the property mappings
    //     * @param tableModifyType      the table modify type
    //     * @param dropTableNotMapping  if true, drop the table which no mapping with
    //     *                             object; if false, do nothing.
    //     * @param columnModifyType     the column modify type
    //     * @param dropColumnNotMapping if true, drop the column which no mapping
    //     *                             with object; if false, do nothing.
    //     * @return the inits the sql
    //     */
    //    private String updateSql(ClassMapping<?> classMapping, List<PropertyMapping> propertyMappings,
    //            ModifyType tableModifyType, boolean dropTableNotMapping, ModifyType columnModifyType,
    //            boolean dropColumnNotMapping) {
    //        Table table = ClassMappingUtils.createTable(classMapping, dialect, sqlTypeMappingManager);
    //        StringBuilder sql = new StringBuilder();
    //        if (ModifyType.DROP_AND_CREATE == tableModifyType) {
    //            // drop and create table
    //            sql.append(createSql(classMapping, true)).append(Chars.SEMI).append(Chars.NEW_LINE);
    //        } else {
    //            for (PropertyMapping propertyMapping : propertyMappings) {
    //                Column column = table.getColumn(propertyMapping.getRepositoryFieldName());
    //                if (ModifyType.DROP_AND_CREATE == columnModifyType) {
    //                    sql.append(dialect.buildAlterTableDropColumnDDL(table.getName(), column)).append(Chars.SEMI)
    //                            .append(Chars.NEW_LINE);
    //                    sql.append(dialect.buildAlterTableAddColumnDDL(table.getName(), column)).append(Chars.SEMI)
    //                            .append(Chars.NEW_LINE);
    //                } else {
    //                    sql.append(dialect.buildAlterTableModifyColumnDDL(table.getName(), column)).append(Chars.SEMI)
    //                            .append(Chars.NEW_LINE);
    //                }
    //            }
    //        }
    //        return sql.toString();
    //    }

    /**
     * merge database info for classMappings.
     *
     * @param classMappings the class mappings
     */
    public void update(Set<ClassMapping<?>> classMappings) {
        sqlExecutor.execute(updateSql(classMappings));
    }

    /**
     * merge database info for classMappings.
     *
     * @param classMappings        the class mappings
     * @param tableModifyType      the table modify type
     * @param dropTableNotMapping  the drop table not mapping
     * @param columnModifyType     the column modify type
     * @param dropColumnNotMapping the drop column not mapping
     */
    public void update(Set<ClassMapping<?>> classMappings, ModifyType tableModifyType, boolean dropTableNotMapping,
            ModifyType columnModifyType, boolean dropColumnNotMapping) {
        sqlExecutor.execute(
                updateSql(classMappings, tableModifyType, dropTableNotMapping, columnModifyType, dropColumnNotMapping));
    }

    /**
     * Append sql with end.
     *
     * @param sql       the sql
     * @param appendSql the append sql
     * @return the string builder
     */
    private StringBuilder appendSqlWithEnd(StringBuilder sql, String appendSql) {
        return sql.append(appendSql).append(Chars.SEMI).append(Chars.NEW_LINE);
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * 返回sqlExecutor.
     *
     * @return sqlExecutor
     */
    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    /**
     * 返回sqlTypeMappingManager.
     *
     * @return sqlTypeMappingManager
     */
    public SqlTypeMappingManager getSqlTypeMappingManager() {
        return sqlTypeMappingManager;
    }

    /**
     * 返回schema
     *
     * @return schema
     */
    public String getSchema() {
        return schema;
    }
}
