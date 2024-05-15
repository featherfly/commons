
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:54:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * DDL features.
 *
 * @author zhongj
 */
public interface DDLFeature {

    /**
     * Builds the sql of create database.
     *
     * @param dataBaseName the data base name
     * @return sql of create database
     */
    String createDataBase(String dataBaseName);

    /**
     * Builds the drop data base sql.
     *
     * @param dataBaseName the data base name
     * @return the string
     */
    default String dropDataBase(String dataBaseName) {
        return dropDataBase(dataBaseName, false);
    }

    /**
     * Builds the sql of drop database.
     *
     * @param dataBaseName the data base name
     * @param ifExists     the if exists
     * @return sql of drop database
     */
    String dropDataBase(String dataBaseName, boolean ifExists);

    /**
     * Builds the create schema DDL.
     *
     * @param schemaName the schema name
     * @return the string
     */
    String createSchema(String schemaName);

    /**
     * Builds the sql of drop schema.
     *
     * @param schemaName the schema name
     * @return sql of drop schema
     */
    default String dropSchema(String schemaName) {
        return dropSchema(schemaName, false);
    }

    /**
     * Builds the sql of drop schema.
     *
     * @param schemaName the schema name
     * @param ifExists   the if exists
     * @return sql of drop schema
     */
    String dropSchema(String schemaName, boolean ifExists);

    /**
     * Builds the sql of create table.
     *
     * @param table the table
     * @return sql of create table
     */
    String createTable(Table table);

    /**
     * Builds the sql of drop table.
     *
     * @param tableName the table name
     * @return sql of drop table
     */
    default String dropTable(String tableName) {
        return dropTable(null, tableName);
    }

    /**
     * Builds the sql of drop table.
     *
     * @param tableName the table name
     * @param ifExists  the if exists
     * @return sql of drop table
     */
    default String dropTable(String tableName, boolean ifExists) {
        return dropTable(null, tableName, ifExists);
    }

    /**
     * Builds the sql of drop table.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @return sql of drop table
     */
    default String dropTable(String schema, String tableName) {
        return dropTable(schema, tableName, false);
    }

    /**
     * Builds the sql of drop table.
     *
     * @param schema    the database schema
     * @param tableName the table name
     * @param ifExists  the if exists
     * @return sql of drop table
     */
    default String dropTable(String schema, String tableName, boolean ifExists) {
        return dropTable(schema, tableName, ifExists, false);
    }

    /**
     * Builds the sql of drop table.
     *
     * @param schema    the database schema
     * @param tableName the table name
     * @param ifExists  the if exists
     * @param cascade   the cascade
     * @return sql of drop table
     */
    String dropTable(String schema, String tableName, boolean ifExists, boolean cascade);

    /**
     * Builds the sql of alter table.
     *
     * @param tableName the table name
     * @return sql of alter table
     */
    default String alterTable(String tableName) {
        return alterTable(null, tableName);
    }

    /**
     * Builds the alter table sql.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @return sql of alter table
     */
    String alterTable(String schema, String tableName);

    /**
     * Builds the sql of alter table add, modify, drop column.
     *
     * @param schema        the database name
     * @param tableName     the table name
     * @param addColumns    the add columns
     * @param modifyColumns the modify columns
     * @param dropColumns   the drop columns
     * @return sql of alter table add, modify, drop column
     */
    String alterTable(String schema, String tableName, Column[] addColumns, Column[] modifyColumns,
            Column[] dropColumns);

    /**
     * Builds the sql of alter table add column.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return sql of alter table add column
     */
    default String alterTableAddColumn(String tableName, Column... columns) {
        return alterTableAddColumn(null, tableName, columns);
    }

    /**
     * Builds the sql of alter table add column.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return sql of alter table add column
     */
    String alterTableAddColumn(String schema, String tableName, Column... columns);

    /**
     * Builds the sql of alter table modify column.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return sql of alter table modify column
     */
    default String alterTableModifyColumn(String tableName, Column... columns) {
        return alterTableModifyColumn(null, tableName, columns);
    }

    /**
     * Builds the sql of alter table modify column.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return sql of alter table modify column
     */
    String alterTableModifyColumn(String schema, String tableName, Column... columns);

    /**
     * Builds the sql of alter table drop column.
     *
     * @param column the column
     * @return sql of alter table drop column
     */
    default String alterTableDropColumn(Column column) {
        return alterTableDropColumn(null, column.getName(), column);
    }

    /**
     * Builds the sql of alter table drop column.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return sql of alter table drop column
     */
    default String alterTableDropColumn(String tableName, Column... columns) {
        return alterTableDropColumn(null, tableName, columns);
    }

    /**
     * Builds the alter table drop column DDL.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    String alterTableDropColumn(String schema, String tableName, Column... columns);

    /**
     * Builds the sql of alter table drop column.
     *
     * @param schema      the database name
     * @param tableName   the table name
     * @param columnNames the column names
     * @return sql of alter table drop column
     */
    String alterTableDropColumn(String schema, String tableName, String... columnNames);

    /**
     * Builds the sql of drop view.
     *
     * @param viewName the view name
     * @return sql of drop view
     */
    default String dropView(String viewName) {
        return dropView(null, viewName);
    }

    /**
     * Builds the sql of drop view.
     *
     * @param schema   the database name
     * @param viewName the view name
     * @return sql of drop view
     */
    default String dropView(String schema, String viewName) {
        return dropView(schema, viewName, false);
    }

    /**
     * Builds the sql of drop view.
     *
     * @param schema   the database name
     * @param viewName the view name
     * @param ifExists the if exists
     * @return sql of drop view
     */
    default String dropView(String schema, String viewName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(viewName, "viewName");
        return drop(schema, viewName, Keywords.VIEW, ifExists);
    }

    /**
     * Builds sql of drop index.
     *
     * @param schema   the schema
     * @param name     the name
     * @param type     the type
     * @param ifExists the if exists
     * @return sql of drop type
     */
    String drop(String schema, String name, Keywords type, boolean ifExists);

    /**
     * Builds the sql of create index.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @return the string
     */
    default String createIndex(String tableName, String indexName, String[] columns) {
        return createIndex(tableName, indexName, columns, false);
    }

    /**
     * Builds the sql of create index.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @param unique    the unique
     * @return sql of create index
     */
    default String createIndex(String tableName, String indexName, String[] columns, boolean unique) {
        return createIndex(null, tableName, indexName, columns, unique);
    }

    /**
     * Builds the sql of create index.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @return sql of create index
     */
    default String createIndex(String schema, String tableName, String indexName, String[] columns) {
        return createIndex(schema, tableName, indexName, columns, false);
    }

    /**
     * Builds the sql of create index .
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @param unique    the unique
     * @return sql of create index
     */
    String createIndex(String schema, String tableName, String indexName, String[] columns, boolean unique);

    /**
     * Builds the sql of drop index .
     *
     * @param tableName the table name
     * @param indexName the index name
     * @return sql of drop index
     */
    default String dropIndex(String tableName, String indexName) {
        return dropIndex(null, tableName, indexName);
    }

    /**
     * Builds the sql of drop index .
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param ifExists  the if exists
     * @return sql of drop index
     */
    default String dropIndex(String tableName, String indexName, boolean ifExists) {
        return dropIndex(null, tableName, indexName, ifExists);
    }

    /**
     * Builds the sql of drop index .
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @return sql of drop index
     */
    default String dropIndex(String schema, String tableName, String indexName) {
        return dropIndex(schema, tableName, indexName, false);
    }

    /**
     * Builds the sql of drop index .
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param ifExists  the if exists
     * @return sql of drop index
     */
    String dropIndex(String schema, String tableName, String indexName, boolean ifExists);
}
