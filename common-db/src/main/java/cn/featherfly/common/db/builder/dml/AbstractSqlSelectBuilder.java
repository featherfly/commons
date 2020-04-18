package cn.featherfly.common.db.builder.dml;

import java.util.Map;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlSelectBasicBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * abstract sql Select builder
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractSqlSelectBuilder implements SqlBuilder {

    private SqlSelectBasicBuilder selectBuilder;

    protected SqlConditionGroup conditionBuilder;

    /**
     * @param dialect          dialect
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, SqlConditionGroup conditionBuilder) {
        this(dialect, null, null, conditionBuilder);
    }

    /**
     * @param dialect          dialect
     * @param tableName        tableName
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, String tableName, SqlConditionGroup conditionBuilder) {
        this(dialect, tableName, null, conditionBuilder);
    }

    /**
     * @param dialect          dialect
     * @param tableName        tableName
     * @param alias            alias
     * @param conditionBuilder conditionBuilder
     */
    public AbstractSqlSelectBuilder(Dialect dialect, String tableName, String alias,
            SqlConditionGroup conditionBuilder) {
        this.conditionBuilder = conditionBuilder;
        selectBuilder = new SqlSelectBasicBuilder(dialect, tableName, alias);
    }

    /**
     * addSelectColumn
     *
     * @param columnName columnName
     * @param function   function
     */
    protected void addSelectColumn(String columnName, AggregateFunction function) {
        selectBuilder.addSelectColumn(columnName, function);
    }

    /**
     * addSelectColumn
     *
     * @param columnName columnName
     * @param asName     asName
     */
    protected void addSelectColumn(String columnName, String asName) {
        selectBuilder.addSelectColumn(columnName, asName);
    }

    /**
     * addSelectColumn
     *
     * @param columnNames columnNames
     * @param function    function
     * @param asName      asName
     */
    protected void addSelectColumn(String columnNames, AggregateFunction function, String asName) {
        selectBuilder.addSelectColumn(columnNames, function, asName);
    }

    /**
     * addSelectColumn
     *
     * @param columnNames columnNames
     */
    protected void addSelectColumn(Map<String, String> columnNames) {
        columnNames.forEach((columnName, asName) -> {
            selectBuilder.addSelectColumn(columnName, asName);
        });
    }

    /**
     * 返回tableAlias
     *
     * @return tableAlias
     */
    public String getTableAlias() {
        return selectBuilder.getTableAlias();
    }

    /**
     * 设置alias
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        selectBuilder.setTableAlias(tableAlias);
    }

    /**
     * 返回tableName
     *
     * @return tableName
     */
    public String getTableName() {
        return selectBuilder.getTableName();
    }

    /**
     * 设置tableName
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        selectBuilder.setTableName(tableName);
    }

    /**
     * 返回buildWithFrom
     *
     * @return buildWithFrom
     */
    public boolean isBuildWithFrom() {
        return selectBuilder.isBuildWithFrom();
    }

    /**
     * 设置buildWithFrom
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
}
