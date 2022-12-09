package cn.featherfly.common.db.builder.dml;

import java.util.Map;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectBasicBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.repository.builder.AliasManager;

/**
 * abstract sql Select builder.
 *
 * @author zhongj
 */
public abstract class AbstractSqlSelectBuilder implements SqlBuilder {

    private SqlSelectBasicBuilder selectBuilder;

    /** The condition builder. */
    protected SqlConditionGroup conditionBuilder;

    /**
     * Instantiates a new abstract sql select builder.
     *
     * @param dialect          dialect
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, SqlConditionGroup conditionBuilder) {
        this(dialect, null, null, conditionBuilder);
    }

    /**
     * Instantiates a new abstract sql select builder.
     *
     * @param dialect          dialect
     * @param tableName        tableName
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, String tableName, SqlConditionGroup conditionBuilder) {
        this(dialect, tableName, null, conditionBuilder);
    }

    /**
     * Instantiates a new abstract sql select builder.
     *
     * @param dialect          dialect
     * @param tableName        tableName
     * @param alias            alias
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, String tableName, String alias,
            SqlConditionGroup conditionBuilder) {
        this.conditionBuilder = conditionBuilder;
        selectBuilder = new SqlSelectBasicBuilder(dialect, tableName, alias, new AliasManager());
    }

    /**
     * addSelectColumn.
     *
     * @param function   function
     * @param columnName columnName
     */
    protected void addSelectColumn(AggregateFunction function, String columnName) {
        selectBuilder.addColumn(function, columnName);
    }

    /**
     * addSelectColumn.
     *
     * @param columnName columnName
     */
    protected void addSelectColumn(String columnName) {
        selectBuilder.addColumn(columnName);
    }

    /**
     * addSelectColumn.
     *
     * @param columnName columnName
     * @param asName     asName
     */
    protected void addSelectColumn(String columnName, String asName) {
        selectBuilder.addColumn(columnName, asName);
    }

    /**
     * addSelectColumn.
     *
     * @param function    function
     * @param columnNames columnNames
     * @param asName      asName
     */
    protected void addSelectColumn(AggregateFunction function, String columnNames, String asName) {
        selectBuilder.addColumn(function, columnNames, asName);
    }

    /**
     * addSelectColumn.
     *
     * @param columnNames columnNames
     */
    protected void addSelectColumn(Map<String, String> columnNames) {
        columnNames.forEach((columnName, asName) -> {
            selectBuilder.addColumn(columnName, asName);
        });
    }

    //    /**
    //     * 返回tableAlias.
    //     *
    //     * @return tableAlias
    //     */
    //    public String getTableAlias() {
    //        return selectBuilder.getTableAlias();
    //    }
    //
    //    /**
    //     * 设置alias.
    //     *
    //     * @param tableAlias tableAlias
    //     */
    //    public void setTableAlias(String tableAlias) {
    //        selectBuilder.setTableAlias(tableAlias);
    //    }
    //
    //    /**
    //     * 返回tableName.
    //     *
    //     * @return tableName
    //     */
    //    public String getTableName() {
    //        return selectBuilder.getTableName();
    //    }
    //
    //    /**
    //     * 设置tableName.
    //     *
    //     * @param tableName tableName
    //     */
    //    public void setTableName(String tableName) {
    //        selectBuilder.setTableName(tableName);
    //    }

    /**
     * 返回buildWithFrom.
     *
     * @return buildWithFrom
     */
    public boolean isBuildWithFrom() {
        return selectBuilder.isBuildWithFrom();
    }

    /**
     * 设置buildWithFrom.
     *
     * @param buildWithFrom buildWithFrom
     */
    public void setBuildWithFrom(boolean buildWithFrom) {
        selectBuilder.setBuildWithFrom(buildWithFrom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return selectBuilder.build();
    }

    public String getAlias() {
        return selectBuilder.getDefaultTableAlias();
    }

    public String getTableName() {
        return selectBuilder.getAliasManager().getName(getAlias());
    }

}
