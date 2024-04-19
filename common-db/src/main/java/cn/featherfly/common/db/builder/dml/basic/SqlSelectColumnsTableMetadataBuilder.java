package cn.featherfly.common.db.builder.dml.basic;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.model.SelectColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlSelectColumnsTableMetadataBuilder
    extends AbstractSqlSelectColumnsBuilder<SqlSelectColumnsTableMetadataBuilder> {

    /** The table metadata. */
    protected final Table table;

    /** The prefix column alias name. */
    protected final String prefixColumnAliasName;

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect dialect
     * @param table   the table metadata
     */
    public SqlSelectColumnsTableMetadataBuilder(Dialect dialect, Table table) {
        this(dialect, table, null);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect    dialect
     * @param table      the table metadata
     * @param tableAlias table name alias
     */
    public SqlSelectColumnsTableMetadataBuilder(Dialect dialect, Table table, String tableAlias) {
        this(dialect, table, tableAlias, tableAlias);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect               dialect
     * @param table                 the table metadata
     * @param tableAlias            table name alias
     * @param prefixColumnAliasName the prefix column alias name
     */
    public SqlSelectColumnsTableMetadataBuilder(Dialect dialect, Table table, String tableAlias,
        String prefixColumnAliasName) {
        super(dialect, tableAlias);
        AssertIllegalArgument.isNotNull(table, "table");
        this.table = table;
        this.prefixColumnAliasName = prefixColumnAliasName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder columnsBuilder = new StringBuilder();
        if (columns.isEmpty()) {
            columnsBuilder.append(buildSelectColumnsSql(table, tableAlias, getPrefixColumnAliasName(), dialect));
        } else {
            for (SelectColumnElement c : columns) {
                SelectColumnElement column = new SelectColumnElement(c);
                column.setTableAlias(tableAlias);
                column.setAlias(buildAsName(Lang.pick(column.getAlias(), column.getName()), getPrefixColumnAliasName(),
                    dialect, false));
                columnsBuilder.append(column).append(Chars.COMMA).append(Chars.SPACE);
            }
            columnsBuilder.delete(columnsBuilder.length() - 2, columnsBuilder.length());
        }
        return columnsBuilder.toString();
    }

    private String getPrefixColumnAliasName() {
        return enableColumnAliasPrefixTableAlias ? prefixColumnAliasName : null;
    }

    private static String buildSelectColumnsSql(Table table, String tableAlias, String prefixColumnAliasName,
        Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        for (Column column : table.getColumns()) {
            selectSql.append(buildSelectColumnsSql(tableAlias, column, prefixColumnAliasName, dialect));
        }
        if (selectSql.length() > 2) {
            selectSql.delete(selectSql.length() - 2, selectSql.length());
        }
        return selectSql.toString();
    }

    private static String buildSelectColumnsSql(String tableAlias, Column column, String prefixColumnAliasName,
        Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        if (Strings.isNotBlank(tableAlias)) {
            selectSql.append(tableAlias).append(Chars.DOT);
        }
        selectSql.append(dialect.wrapName(column.name())).append(Chars.SPACE)
            .append(buildAsName(column.name(), prefixColumnAliasName, dialect)).append(Chars.COMMA).append(Chars.SPACE);
        return selectSql.toString();
    }

    private static String buildAsName(String column, String prefixColumnAliasName, Dialect dialect) {
        return buildAsName(column, prefixColumnAliasName, dialect, true);
    }

    private static String buildAsName(String column, String prefixColumnAliasName, Dialect dialect, boolean warp) {
        if (warp) {
            if (StringUtils.isBlank(prefixColumnAliasName)) {
                return dialect.wrapName(column);
            } else {
                return dialect.wrapName(prefixColumnAliasName + Chars.DOT + column);
            }
        } else {
            if (StringUtils.isBlank(prefixColumnAliasName)) {
                return column;
            } else {
                return prefixColumnAliasName + Chars.DOT + column;
            }
        }
    }
}
