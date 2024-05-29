
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:56:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Index;

/**
 * abstract DDL features.
 *
 * @author zhongj
 */
public abstract class AbstractDDLFeature<D extends Dialect> implements DDLFeature {

    /** The dialect. */
    protected final D dialect;

    /**
     * Instantiates a new abstract DDL features.
     *
     * @param dialect the dialect
     */
    protected AbstractDDLFeature(D dialect) {
        super();
        this.dialect = dialect;
    }

    private boolean defineNeedSize(SQLType sqlType) {
        return sqlType == JDBCType.VARCHAR || sqlType == JDBCType.NVARCHAR || sqlType == JDBCType.CHAR
            || sqlType == JDBCType.NCHAR || sqlType == JDBCType.LONGVARCHAR || sqlType == JDBCType.LONGNVARCHAR
            || sqlType == JDBCType.FLOAT || sqlType == JDBCType.DOUBLE || sqlType == JDBCType.DECIMAL
            || sqlType == JDBCType.NUMERIC;
    }

    @Override
    public String createDataBase(String dataBaseName) {
        AssertIllegalArgument.isNotEmpty(dataBaseName, "dataBaseName");
        return BuilderUtils.link(dialect.getKeyword(Keywords.CREATE), dialect.getKeyword(Keywords.DATABASE),
            dialect.wrapName(dataBaseName)) + Chars.SEMI;
    }

    @Override
    public String dropDataBase(String dataBaseName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(dataBaseName, "dataBaseName");
        if (ifExists) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.DATABASE),
                dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), dialect.wrapName(dataBaseName))
                + Chars.SEMI;
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.DATABASE),
                dialect.wrapName(dataBaseName)) + Chars.SEMI;
        }
    }

    @Override
    public String createSchema(String schemaName) {
        AssertIllegalArgument.isNotEmpty(schemaName, "schemaName");
        return BuilderUtils.link(dialect.getKeyword(Keywords.CREATE), dialect.getKeyword(Keywords.SCHEMA),
            dialect.wrapName(schemaName)) + Chars.SEMI;
    }

    @Override
    public String dropSchema(String schemaName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(schemaName, "dataBaseName");
        if (ifExists) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.SCHEMA),
                dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), dialect.wrapName(schemaName))
                + Chars.SEMI;
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.SCHEMA),
                dialect.wrapName(schemaName)) + Chars.SEMI;
        }
    }

    /**
     * Builds the drop table sql.
     *
     * @param schema the database schema
     * @param tableName the table name
     * @param ifExists the if exists
     * @param cascade the cascade
     * @return the string
     */
    @Override
    public String dropTable(String schema, String tableName, boolean ifExists, boolean cascade) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        String tn = Lang.isEmpty(schema) ? dialect.wrapName(tableName)
            : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(tableName);
        String ddl = "";
        if (ifExists) {
            ddl = BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.TABLE),
                dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), tn);
        } else {
            ddl = BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.TABLE), tn);
        }
        if (cascade) {
            ddl += Chars.SPACE + Keywords.CASCADE;
        }
        return ddl + Chars.SEMI;
    }

    @Override
    public String alterTable(String schema, String tableName) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        if (Lang.isEmpty(schema)) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.ALTER), dialect.getKeyword(Keywords.TABLE),
                dialect.wrapName(tableName));
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.ALTER), dialect.getKeyword(Keywords.TABLE),
                dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(tableName));
        }
    }

    @Override
    public String dropView(String schema, String viewName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(viewName, "viewName");
        return drop(schema, viewName, Keywords.VIEW, ifExists);
    }

    @Override
    public String drop(String schema, String name, Keywords type, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(name, "name");
        AssertIllegalArgument.isNotEmpty(type, "type");
        String tn = Lang.isEmpty(schema) ? dialect.wrapName(name)
            : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(name);
        if (ifExists) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(type),
                dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), tn) + Chars.SEMI;
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(type), tn) + Chars.SEMI;
        }
    }

    @Override
    public String createIndex(String schema, String tableName, String indexName, String[] columns, boolean unique) {
        AssertIllegalArgument.isNotEmpty(columns, "columns");
        StringBuilder ddl = new StringBuilder();
        String tn = Lang.isEmpty(schema) ? dialect.wrapName(tableName)
            : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(tableName);
        StringBuilder cols = new StringBuilder();
        cols.append(Chars.PAREN_L);
        for (String column : columns) {
            cols.append(dialect.wrapName(column)).append(Chars.COMMA);
        }
        cols.deleteCharAt(cols.length() - 1);
        cols.append(Chars.PAREN_R);
        String indexKeyWords = unique
            ? dialect.getKeyword(Keywords.UNIQUE) + Chars.SPACE + dialect.getKeyword(Keywords.INDEX)
            : dialect.getKeyword(Keywords.INDEX);
        BuilderUtils.link(ddl, dialect.getKeyword(Keywords.CREATE), indexKeyWords, indexName,
            dialect.getKeyword(Keywords.ON), tn + cols.toString());
        return ddl.toString() + Chars.SEMI;
    }

    @Override
    public String dropIndex(String schema, String tableName, String indexName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(indexName, "indexName");
        String name = Lang.isEmpty(schema) ? dialect.wrapName(indexName)
            : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(indexName);
        if (ifExists) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.INDEX),
                dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), name) + Chars.SEMI;
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.INDEX), name)
                + Chars.SEMI;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTable(String schema, String tableName, Column[] addColumns, Column[] modifyColumns,
        Column[] dropColumns) {
        if (Lang.isEmpty(addColumns) && Lang.isEmpty(modifyColumns) && Lang.isEmpty(dropColumns)) {
            return "";
        }
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        ddl.append(Chars.NEW_LINE);
        if (Lang.isNotEmpty(addColumns)) {
            ddl.append(addColumn(addColumns)).toString();
            if (Lang.isNotEmpty(modifyColumns) || Lang.isNotEmpty(dropColumns)) {
                ddl.append(Chars.COMMA);
            }
            ddl.append(Chars.NEW_LINE);
        }
        if (Lang.isNotEmpty(modifyColumns)) {
            ddl.append(modifyColumn(modifyColumns)).toString();
            if (Lang.isNotEmpty(dropColumns)) {
                ddl.append(Chars.COMMA);
            }
            ddl.append(Chars.NEW_LINE);
        }
        if (Lang.isNotEmpty(dropColumns)) {
            ddl.append(dropColumn(dropColumns)).toString();
            ddl.append(Chars.NEW_LINE);
        }
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableAddColumn(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(addColumn(columns)).append(Chars.SEMI).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableModifyColumn(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(modifyColumn(columns)).append(Chars.SEMI).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(dropColumn(columns)).append(Chars.SEMI).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, String... columnNames) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(dropColumn(columnNames)).append(Chars.SEMI).toString();
    }

    /**
     * Builds the create table sql.
     *
     * @param table the table
     * @return the string
     */
    @Override
    public String createTable(Table table) {
        AssertIllegalArgument.isNotEmpty(table, "table");
        String schema = table.getSchema();
        StringBuilder sql = new StringBuilder();
        String tableName = Lang.isEmpty(schema) ? dialect.wrapName(table.getName())
            : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(table.getName());
        BuilderUtils.link(sql, dialect.getKeyword(Keywords.CREATE), dialect.getKeyword(Keywords.TABLE), tableName,
            Chars.PAREN_L);
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, tableColumns(table));
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, Chars.PAREN_R, tableComment(table));

        if (table.getIndexs().size() > 0) {
            sql.append(Chars.SEMI).append(Chars.NEW_LINE);
            for (Index index : table.getIndexs()) {
                sql.append(createIndex(schema, table.getName(), index.getName(), index.getColumns(), index.isUnique()))
                    .append(Chars.NEW_LINE);
            }
            sql.deleteCharAt(sql.length() - 1);
        } else {
            sql.append(Chars.SEMI);
        }
        return sql.toString();
    }

    // ****************************************************************************************************************
    // protected
    // ****************************************************************************************************************

    /**
     * Gets the column comment.
     *
     * @param column the column
     * @return the column comment
     */
    protected String columnComment(Column column) {
        return Lang.isEmpty(column.getRemark()) ? ""
            : BuilderUtils.link(dialect.getKeyword(Keywords.COMMENT), "'" + column.getRemark() + "'");
    }

    /**
     * Gets the column not null.
     *
     * @param column the column
     * @return the column not null
     */
    protected String columnNotNull(Column column) {
        if (column.isNullable()) {
            return "";
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.NOT), dialect.getKeyword(Keywords.NULL));
        }
    }

    /**
     * Gets the default value.
     *
     * @param column the column
     * @return the default value
     */
    protected String defaultValue(Column column) {
        if (Lang.isEmpty(column.getDefaultValue())) {
            return "";
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DEFAULT), "'" + column.getDefaultValue() + "'");
        }
    }

    /**
     * Gets the identity.
     *
     * @param column the column
     * @return the identity
     */
    protected abstract String autoIncrement(Column column);

    /**
     * Builds the add column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String addColumn(Column... columns) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
        for (Column column : columns) {
            BuilderUtils.link(ddl, dialect.getKeyword(Keywords.ADD), dialect.getKeyword(Keywords.COLUMN),
                column(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * Builds the modify column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String modifyColumn(Column... columns) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
        for (Column column : columns) {
            BuilderUtils.link(ddl, dialect.getKeyword(Keywords.MODIFY), dialect.getKeyword(Keywords.COLUMN),
                column(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * Builds the drop column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String dropColumn(Column... columns) {
        String[] columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            columnNames[i] = columns[i].getName();
        }
        return dropColumn(columnNames);
    }

    /**
     * Builds the drop column DDL.
     *
     * @param columnNames the column names
     * @return the string
     */
    protected String dropColumn(String... columnNames) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
        for (String columnName : columnNames) {
            BuilderUtils.link(ddl, dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.COLUMN),
                dialect.wrapName(columnName));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * Gets the table comment.
     *
     * @param table the table
     * @return the table comment
     */
    protected String tableComment(Table table) {
        return Lang.isEmpty(table.getRemark()) ? ""
            : BuilderUtils.link(dialect.getKeyword(Keywords.COMMENT), "'" + table.getRemark() + "'");
    }

    /**
     * Gets the table columns DDL.
     *
     * @param table the table
     * @return the table columns DDL
     */
    protected String tableColumns(Table table) {
        StringBuilder ddl = new StringBuilder();
        for (Column column : table.getColumns()) {
            BuilderUtils.link(ddl, column(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        BuilderUtils.link(ddl, primaryKey(table));
        return ddl.toString();
    }

    /**
     * Gets the column DDL.
     *
     * @param column the column
     * @return the column DDL
     */
    protected String column(Column column) {
        return BuilderUtils.link(dialect.wrapName(column.getName()), columnType(column), columnNotNull(column),
            defaultValue(column), autoIncrement(column), columnComment(column));
    }

    /**
     * Gets the primary key DDL.
     *
     * @param table the table
     * @return the primary key DDL
     */
    protected String primaryKey(Table table) {
        StringBuilder result = new StringBuilder(Chars.PAREN_L);
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                result.append(dialect.wrapName(column.getName())).append(Chars.COMMA);
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append(Chars.PAREN_R);
        return BuilderUtils.link(dialect.getKeyword(Keywords.PRIMARY), dialect.getKeyword(Keywords.KEY),
            result.toString());
    }

    /**
     * Gets the column type DDL.
     *
     * @param column the column
     * @return the column type DDL
     */
    protected String columnType(Column column) {
        return columnType(column, null);
    }

    /**
     * Gets the column type DDL.
     *
     * @param column the column
     * @param extra the extra
     * @return the column type DDL
     */
    protected String columnType(Column column, String extra) {
        int size = column.getSize();
        int decimalDigits = column.getDecimalDigits();
        String result = dialect.getColumnTypeName(column.getSqlType());
        if (defineNeedSize(column.getSqlType())) {
            if (size <= 0) {
                throw new JdbcMappingException("#size.not.define", Lang.array(column.getName()));
            } else {
                result += Chars.PAREN_L + size;
                if (decimalDigits > 0) {
                    result += Chars.COMMA + decimalDigits;
                }
                result += Chars.PAREN_R;
            }
        }
        if (Lang.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;
    }

}
