package cn.featherfly.common.db.builder.dml;

import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * sql select builder .
 *
 * @author zhongj
 */
public class SqlSelectBuilder extends AbstractSqlSelectBuilder implements SelectBuilder {

    /**
     * @param dialect          dialect
     * @param conditionBuilder conditionBuilder
     */
    public SqlSelectBuilder(Dialect dialect, SqlConditionGroup conditionBuilder) {
        this(dialect, null, conditionBuilder);
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
    public SelectBuilder select(AggregateFunction aggregateFunction, String columnName) {
        addSelectColumn(aggregateFunction, columnName);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(AggregateFunction aggregateFunction, String columnName, String asName) {
        addSelectColumn(aggregateFunction, columnName, asName);
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
        addSelectColumn(columnName);
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
        //        setTableName(tableName);
        //        setTableAlias(alias);
        // FIXME 这里需要设置table alias
        conditionBuilder.setQueryAlias(alias);
        return conditionBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(String[] tableNames) {
        // TODO 需要从底层开始加入多tableName tableAlias的Builder
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(Collection<String> tableNames) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(Map<String, String> tableNames) {
        // TODO Auto-generated method stub
        return null;
    }
}
