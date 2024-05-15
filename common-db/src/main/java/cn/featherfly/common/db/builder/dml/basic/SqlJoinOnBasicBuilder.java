package cn.featherfly.common.db.builder.dml.basic;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Join;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlJoinOnBasicBuilder implements SqlJoinOnBuilder {

    /** The table alias. */
    protected String tableAlias;

    /** The table name. */
    protected String tableName;

    /** The column name. */
    protected String columnName;

    /** The condition table alias. */
    protected String conditionTableAlias;

    /** The condition column. */
    protected String conditionColumn;

    /** The dialect. */
    protected Dialect dialect;

    /** The join. */
    protected Join join;

    protected String onSql;

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect         dialect
     * @param tableName       tableName
     * @param columnName      columnName
     * @param conditionColumn conditionColumn
     */
    public SqlJoinOnBasicBuilder(Dialect dialect, String tableName, String columnName, String conditionColumn) {
        this(dialect, null, tableName, columnName, conditionColumn);
    }

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect         dialect
     * @param join            join type
     * @param tableName       tableName
     * @param columnName      columnName
     * @param conditionColumn conditionColumn
     */
    public SqlJoinOnBasicBuilder(Dialect dialect, Join join, String tableName, String columnName,
            String conditionColumn) {
        this(dialect, join, tableName, null, columnName, null, conditionColumn);
    }

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect             dialect
     * @param tableName           tableName
     * @param tableAlias          alias
     * @param columnName          columnName
     * @param conditionTableAlias conditionTableAlias
     * @param conditionColumn     conditionColumn
     */
    public SqlJoinOnBasicBuilder(Dialect dialect, String tableName, String tableAlias, String columnName,
            String conditionTableAlias, String conditionColumn) {
        this(dialect, null, tableName, tableAlias, columnName, conditionTableAlias, conditionColumn);
    }

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect             dialect
     * @param join                join type
     * @param tableName           tableName
     * @param tableAlias          alias
     * @param columnName          columnName
     * @param conditionTableAlias conditionTableAlias
     * @param conditionColumn     conditionColumn
     */
    public SqlJoinOnBasicBuilder(Dialect dialect, Join join, String tableName, String tableAlias, String columnName,
            String conditionTableAlias, String conditionColumn) {
        this.dialect = dialect;
        if (join == null) {
            this.join = Join.INNER_JOIN;
        } else {
            this.join = join;
        }
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.conditionTableAlias = conditionTableAlias;
        this.conditionColumn = conditionColumn;
    }

    /**
     * 返回alias.
     *
     * @return alias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 设置alias.
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * 返回tableName.
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置tableName.
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return new StringBuilder().append(dialect.getKeywords().join(join)).append(Chars.SPACE)
                .append(dialect.dml().table(tableName, tableAlias)).append(Chars.SPACE)
                .append(dialect.getKeywords().on()).append(Chars.SPACE)
                .append(dialect.dml().column(tableAlias, columnName)).append(Chars.SPACE).append(Chars.EQ)
                .append(Chars.SPACE).append(dialect.dml().column(conditionTableAlias, conditionColumn)).toString();
    }
}
