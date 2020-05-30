package cn.featherfly.common.db.builder.dml;

import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * sql find builder
 * </p>
 *
 * @author zhongj
 */
public class SqlSelectBuilder extends AbstractSqlSelectBuilder implements SelectBuilder {

    /**
     * @param dialect          dialect
     * @param conditionBuilder conditionBuilder
     */
    public SqlSelectBuilder(Dialect dialect, SqlConditionGroup conditionBuilder) {
        this(dialect, null, null, conditionBuilder);
    }

    /**
     * @param dialect          dialect
     * @param tableName        tableName
     * @param conditionBuilder conditionBuilder
     */
    public SqlSelectBuilder(Dialect dialect, String tableName, SqlConditionGroup conditionBuilder) {
        this(dialect, tableName, null, conditionBuilder);
    }

    /**
     * @param dialect          dialect
     * @param tableName        tableName
     * @param alias            alias
     * @param conditionBuilder conditionBuilder
     */
    public SqlSelectBuilder(Dialect dialect, String tableName, String alias, SqlConditionGroup conditionBuilder) {
        super(dialect, tableName, alias, conditionBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, AggregateFunction aggregateFunction) {
        addSelectColumn(columnName, aggregateFunction);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, AggregateFunction aggregateFunction, String asName) {
        addSelectColumn(columnName, aggregateFunction, asName);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(Map<String, String> columnNames) {
        addSelectColumn(columnNames);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName) {
        addSelectColumn(columnName, null, null);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, String asName) {
        addSelectColumn(columnName, asName);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String[] columnNames) {
        if (Lang.isNotEmpty(columnNames)) {
            for (String columnName : columnNames) {
                select(columnName);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(Collection<String> columnNames) {
        if (Lang.isNotEmpty(columnNames)) {
            for (String columnName : columnNames) {
                select(columnName);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(String tableName) {
        return from(tableName, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(String tableName, String alias) {
        setTableName(tableName);
        setTableAlias(alias);
        conditionBuilder.setQueryAlias(alias);
        return conditionBuilder;
    }
}
