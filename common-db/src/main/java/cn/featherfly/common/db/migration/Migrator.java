
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
import cn.featherfly.common.repository.Index;
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
    //    private String schema;

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
            //            this.schema = schema;
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
        //        sql.append(dialect.getInitSqlHeader()).append(Chars.SEMI).append(Chars.NEW_LINE);
        appendSqlWithEnd(sql, dialect.getInitSqlHeader());
        for (ClassMapping<?> classMapping : classMappings) {
            //            sql.append(createSql(classMapping, true)).append(Chars.SEMI).append(Chars.NEW_LINE);
            appendSqlWithEnd(sql, createSql(classMapping, true));
        }
        //        sql.append(dialect.getInitSqlFooter()).append(Chars.SEMI).append(Chars.NEW_LINE);
        appendSqlWithEnd(sql, dialect.getInitSqlFooter());
        String result = sql.toString();
        LOGGER.debug("create init sql -> \n{}", result);
        //        if (Lang.isEmpty(schema)) {
        //            LOGGER.debug("create init sql -> \n{}", result);
        //        } else {
        //            LOGGER.debug("create init sql for {} -> \n{}", schema, result);
        //        }
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
     * @param tableMapping the table mapping
     * @param dropIfExists the drop if exists
     * @return the string
     */
    private String createSql(TableMapping tableMapping, boolean dropIfExists) {
        return createSql(tableMapping.table, dropIfExists,
                tableMapping.classMapping == null ? null : tableMapping.classMapping.getType());
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
            appendSqlWithEnd(sql, dialect.buildDropTableDDL(table.getSchema(), table.getName(), true));
        }
        String result = sql.append(dialect.buildCreateTableDDL(table)).toString();
        if (type == null) {
            LOGGER.debug("create sql for table {} -> \n{}", table.getName(), result);
        } else {
            LOGGER.debug("create sql for entity {} -> \n{}", type.getName(), result);
        }
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
     * @param previousDataSource the previous data source
     * @param currentDataSource  the current data source
     * @return the string
     */
    public String updateSql(DataSource previousDataSource, DataSource currentDataSource) {
        return updateSql(previousDataSource, currentDataSource, ModifyType.MODIFY);
    }

    /**
     * Update sql.
     *
     * @param previousDataSource the previous data source
     * @param currentDataSource  the current data source
     * @param modifyType         the modify type
     * @return the string
     */
    public String updateSql(DataSource previousDataSource, DataSource currentDataSource, ModifyType modifyType) {
        return updateSql(previousDataSource, currentDataSource, modifyType, true);
    }

    /**
     * Update sql.
     *
     * @param previousDataSource the previous data source
     * @param tableModifyType    the table modify type
     * @param dropNotExistTable  the drop not exist table
     * @param columnModifyType   the column modify type
     * @param dropNotExistColumn the drop not exist column
     * @param dropNotExistIndex  the drop not exist index
     * @return the string
     */
    public String updateSql(DataSource previousDataSource, ModifyType tableModifyType, boolean dropNotExistTable,
            ModifyType columnModifyType, boolean dropNotExistColumn, boolean dropNotExistIndex) {
        return updateSql(previousDataSource, sqlExecutor.getDataSource(), tableModifyType, dropNotExistTable,
                columnModifyType, dropNotExistColumn, dropNotExistIndex);
    }

    /**
     * Update sql.
     *
     * @param previousDataSource the previous data source
     * @param currentDataSource  the current data source
     * @param modifyType         the modify type
     * @param dropNotExist       the drop not exist
     * @return the string
     */
    public String updateSql(DataSource previousDataSource, DataSource currentDataSource, ModifyType modifyType,
            boolean dropNotExist) {
        return updateSql(previousDataSource, currentDataSource, modifyType, dropNotExist, modifyType, dropNotExist,
                dropNotExist);
    }

    /**
     * Update sql.
     *
     * @param previousDataSource the previous data source
     * @param currentDataSource  the current data source
     * @param tableModifyType    the table modify type
     * @param dropNotExistTable  the drop not exist table
     * @param columnModifyType   the column modify type
     * @param dropNotExistColumn the drop not exist column
     * @param dropNotExistIndex  the drop not exist index
     * @return the string
     */
    public String updateSql(DataSource previousDataSource, DataSource currentDataSource, ModifyType tableModifyType,
            boolean dropNotExistTable, ModifyType columnModifyType, boolean dropNotExistColumn,
            boolean dropNotExistIndex) {
        DatabaseMetadata previousMetadata = DatabaseMetadataManager.getDefaultManager().reCreate(previousDataSource);
        DatabaseMetadata currentMetadata = DatabaseMetadataManager.getDefaultManager().reCreate(currentDataSource);
        return updateSql(previousMetadata, currentMetadata, tableModifyType, dropNotExistTable, columnModifyType,
                dropNotExistColumn, dropNotExistIndex);
    }

    /**
     * Update sql.
     *
     * @param previousMetadata   the previous metadata
     * @param currentMetadata    the current metadata
     * @param tableModifyType    the table modify type
     * @param dropNotExistTable  the drop not exist table
     * @param columnModifyType   the column modify type
     * @param dropNotExistColumn the drop not exist column
     * @param dropNotExistIndex  the drop not exist index
     * @return the string
     */
    public String updateSql(DatabaseMetadata previousMetadata, DatabaseMetadata currentMetadata,
            ModifyType tableModifyType, boolean dropNotExistTable, ModifyType columnModifyType,
            boolean dropNotExistColumn, boolean dropNotExistIndex) {
        Diff diff = new Diff();
        Set<String> tableNameSet = new HashSet<>();
        for (Table current : currentMetadata.getTables()) {
            Table previous = previousMetadata.getTable(current.getName());
            tableNameSet.add(diff(previous, current, null, diff));
        }
        for (Table previous : previousMetadata.getTables()) {
            // 判断数据库表是否没有映射
            if (!tableNameSet.contains(previous.getName())) {
                diff.notExistTables.add(previous);
            }
        }
        return diffSql(diff, tableModifyType, dropNotExistTable, columnModifyType, dropNotExistColumn,
                dropNotExistIndex);
    }

    /**
     * Gets the update sql.
     *
     * @param classMappings the class mappings
     * @return the inits the sql
     */
    public String updateSql(Set<ClassMapping<?>> classMappings) {
        return updateSql(classMappings, ModifyType.MODIFY, true);
    }

    /**
     * Update sql.
     *
     * @param classMappings the class mappings
     * @param modifyType    the modify type for table and column
     * @param dropNoMapping the drop no mapping for table, column, index
     * @return the string
     */
    public String updateSql(Set<ClassMapping<?>> classMappings, ModifyType modifyType, boolean dropNoMapping) {
        return updateSql(classMappings, modifyType, dropNoMapping, modifyType, dropNoMapping, dropNoMapping);
    }

    /**
     * Gets the update sql with cached DatabaseMetadata(maybe different from
     * database).
     *
     * @param classMappings       the class mappings
     * @param tableModifyType     the table modify type
     * @param dropNoMappingTable  if true, drop the table which no mapping with
     *                            object; if false, do nothing.
     * @param columnModifyType    the column modify type
     * @param dropNoMappingColumn if true, drop the column which no mapping with
     *                            object; if false, do nothing.
     * @param dropNoMappingIndex  if true, drop the index which no mapping with
     *                            object; if false, do nothing.
     * @return the inits the sql
     */
    public String updateSql(Set<ClassMapping<?>> classMappings, ModifyType tableModifyType, boolean dropNoMappingTable,
            ModifyType columnModifyType, boolean dropNoMappingColumn, boolean dropNoMappingIndex) {
        return updateSql(classMappings, tableModifyType, dropNoMappingTable, columnModifyType, dropNoMappingColumn,
                dropNoMappingIndex, DatabaseMetadataManager.getDefaultManager().reCreate(sqlExecutor.getDataSource()));
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
     * @param dropNoMappingIndex  if true, drop the index which no mapping with
     *                            object; if false, do nothing.
     * @param databaseMetadata    database metadata
     * @return the inits the sql
     */
    public String updateSql(Set<ClassMapping<?>> classMappings, ModifyType tableModifyType, boolean dropNoMappingTable,
            ModifyType columnModifyType, boolean dropNoMappingColumn, boolean dropNoMappingIndex,
            DatabaseMetadata databaseMetadata) {
        //        UpdateMapping updateMapping = new UpdateMapping();
        Diff diff = new Diff();
        Set<String> tableNameSet = new HashSet<>();
        for (ClassMapping<?> classMapping : classMappings) {
            Table tableMetadata = databaseMetadata.getTable(classMapping.getRepositoryName());
            Table table = ClassMappingUtils.createTable(classMapping, dialect, sqlTypeMappingManager);
            tableNameSet.add(diff(tableMetadata, table, classMapping, diff));
        }
        for (Table tableMetadata : databaseMetadata.getTables()) {
            // 判断数据库表是否没有映射
            if (!tableNameSet.contains(tableMetadata.getName())) {
                diff.notExistTables.add(tableMetadata);
            }
        }
        return diffSql(diff, tableModifyType, dropNoMappingTable, columnModifyType, dropNoMappingColumn,
                dropNoMappingIndex);
    }

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
     * @param dropIndexNotMapping  the drop index not mapping
     */
    public void update(Set<ClassMapping<?>> classMappings, ModifyType tableModifyType, boolean dropTableNotMapping,
            ModifyType columnModifyType, boolean dropColumnNotMapping, boolean dropIndexNotMapping) {
        sqlExecutor.execute(updateSql(classMappings, tableModifyType, dropTableNotMapping, columnModifyType,
                dropColumnNotMapping, dropIndexNotMapping));
    }

    private String diff(Table previous, Table current, ClassMapping<?> classMapping, Diff diff) {
        if (previous == null) {
            // 数据库没有该表，添加新表
            diff.newTables.add(new TableMapping(current, classMapping));
        } else if (!previous.equals(current)) {
            ModifyTable modifyTable = diff.modifyTables.getModifyTable(current);
            if (modifyTable == null) {
                modifyTable = new ModifyTable(current, classMapping);
                diff.modifyTables.put(modifyTable);
            }
            for (Column column : current.getColumns()) {
                Column columnMetadata = previous.getColumn(column.getName());
                if (columnMetadata == null) {
                    // 数据库元数据没有该列，添加新列
                    modifyTable.newColumns.put(classMapping.getPropertyMappingByPersitField(column.getName()), column);
                } else if (!columnMetadata.equals(column)) {
                    modifyTable.modifyColumns.put(classMapping.getPropertyMappingByPersitField(column.getName()),
                            column);
                }
            }
            for (Column columnMetadata : previous.getColumns()) {
                // 判断数据库表的列是否没有映射
                if (current.getColumn(columnMetadata.getName()) == null) {
                    //                        String key = table.getName().toUpperCase();
                    modifyTable.noMappingColumns.add(columnMetadata);
                }
            }
            // 索引
            for (Index index : current.getIndexs()) {
                Index indexMetadata = previous.getIndex(index.getName());
                if (indexMetadata == null) {
                    // 数据库索引数据没有该索引，添加新索引
                    modifyTable.addIndexs.add(index);
                } else if (!indexMetadata.equals(index)) {
                    modifyTable.dropIndexs.add(indexMetadata);
                    modifyTable.addIndexs.add(index);
                }
            }
            for (Index indexMetadata : previous.getIndexs()) {
                // 判断数据库表的索引是否没有映射
                if (!current.hasIndex(indexMetadata.getName())) {
                    modifyTable.notExistIndexs.add(indexMetadata);
                }
            }
        }
        return current.getName();
    }

    private String diffSql(Diff diff, ModifyType tableModifyType, boolean dropNotExistTable,
            ModifyType columnModifyType, boolean dropNotExistColumn, boolean dropNotExistIndex) {
        StringBuilder sql = new StringBuilder();
        // 添加头部
        sql.append(dialect.getInitSqlHeader()).append(Chars.SEMI).append(Chars.NEW_LINE);
        // 添加新的对象映射
        diff.newTables.forEach(tableMapping -> {
            appendSqlWithEnd(sql, createSql(tableMapping, true));
        });
        for (Entry<Table, ModifyTable> entry : diff.modifyTables.modifyTableMap.entrySet()) {
            Table table = entry.getKey();
            ModifyTable modifyTable = entry.getValue();
            if (ModifyType.MODIFY == tableModifyType) {
                List<Column> dropColumns = new ArrayList<>();
                List<Index> dropIndex = new ArrayList<>();
                if (dropNotExistColumn) {
                    dropColumns.addAll(modifyTable.noMappingColumns);
                }
                dropIndex.addAll(modifyTable.dropIndexs);
                if (dropNotExistIndex) {
                    dropIndex.addAll(modifyTable.notExistIndexs);
                }

                // 删除的索引
                for (Index index : dropIndex) {
                    appendSqlWithEnd(sql,
                            dialect.buildDropIndexDDL(table.getSchema(), table.getName(), index.getName()));
                }

                if (ModifyType.MODIFY == columnModifyType) {
                    appendSqlWithEnd(sql, dialect.buildAlterTableDDL(table.getSchema(), table.getName(),
                            //  添加新的属性列映射
                            CollectionUtils.toArray(modifyTable.newColumns.values(), Column.class),
                            // 需要修改的对象映射
                            CollectionUtils.toArray(modifyTable.modifyColumns.values(), Column.class),
                            // 删除没有对象映射的列
                            CollectionUtils.toArray(dropColumns, Column.class)));
                } else if (ModifyType.DROP_AND_CREATE == columnModifyType) {
                    dropColumns.addAll(modifyTable.modifyColumns.values());

                    appendSqlWithEnd(sql, dialect.buildAlterTableDropColumnDDL(table.getSchema(), table.getName(),
                            CollectionUtils.toArray(dropColumns, Column.class)));

                    List<Column> addColumns = new ArrayList<>();
                    addColumns.addAll(modifyTable.newColumns.values());
                    addColumns.addAll(modifyTable.modifyColumns.values());

                    appendSqlWithEnd(sql, dialect.buildAlterTableDDL(table.getSchema(), table.getName(),
                            //  添加新的属性列映射
                            CollectionUtils.toArray(addColumns, Column.class),
                            // 需要修改的对象映射
                            new Column[] {},
                            // 删除没有对象映射的列
                            CollectionUtils.toArray(dropColumns, Column.class)));
                } else {
                    throw new JdbcMappingException("no support ModifyType for columnModifyType -> " + columnModifyType);
                }

                // 新增加的索引
                for (Index index : modifyTable.addIndexs) {
                    appendSqlWithEnd(sql, dialect.buildCreateIndexDDL(table.getSchema(), table.getName(),
                            index.getName(), index.getColumns()));
                }

            } else if (ModifyType.DROP_AND_CREATE == tableModifyType) {
                appendSqlWithEnd(sql, createSql(modifyTable.classMapping, true));
            } else {
                throw new JdbcMappingException("no support ModifyType for tableModifyType -> " + tableModifyType);
            }
        }
        // 删除没有对象映射的表
        if (dropNotExistTable) {
            diff.notExistTables.forEach(table -> {
                appendSqlWithEnd(sql, dialect.buildDropTableDDL(table.getSchema(), table.getName()));
            });
        }
        // 添加尾部
        sql.append(dialect.getInitSqlFooter()).append(Chars.SEMI).append(Chars.NEW_LINE);
        String result = sql.toString();
        LOGGER.debug("create update sql -> \n{}", result);
        //        if (Lang.isEmpty(schema)) {
        //            LOGGER.debug("create update sql -> \n{}", result);
        //        } else {
        //            LOGGER.debug("create update sql for {} -> \n{}", schema, result);
        //        }
        return result;
    }

    /**
     * Append sql with end.
     *
     * @param sql       the sql
     * @param appendSql the append sql
     * @return the string builder
     */
    private StringBuilder appendSqlWithEnd(StringBuilder sql, String appendSql) {
        if (Lang.isNotEmpty(appendSql)) {
            sql.append(appendSql);
            if (!appendSql.endsWith(Chars.SEMI)) {
                sql.append(Chars.SEMI);
            }
            return sql.append(Chars.NEW_LINE);
        }
        return sql;
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

    //    /**
    //     * 返回schema.
    //     *
    //     * @return schema
    //     */
    //    public String getSchema() {
    //        return schema;
    //    }
}
