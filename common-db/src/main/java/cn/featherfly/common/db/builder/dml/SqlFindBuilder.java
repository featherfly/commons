package cn.featherfly.common.db.builder.dml;

import java.util.Collection;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;
import cn.featherfly.common.repository.builder.dml.FindBuilder;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * sql find builder
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlFindBuilder extends AbstractSqlSelectBuilder implements FindBuilder, SqlBuilder {

    /**
     * Instantiates a new sql find builder.
     *
     * @param dialect          dialect
     * @param conditionBuilder conditionBuilder
     */
    public SqlFindBuilder(Dialect dialect, SqlConditionGroup conditionBuilder) {
        this(dialect, null, null, conditionBuilder);
    }

    /**
     * Instantiates a new sql find builder.
     *
     * @param dialect          dialect
     * @param tableName        tableName
     * @param conditionBuilder conditionBuilder
     */
    public SqlFindBuilder(Dialect dialect, String tableName, SqlConditionGroup conditionBuilder) {
        this(dialect, tableName, null, conditionBuilder);
    }

    /**
     * Instantiates a new sql find builder.
     *
     * @param dialect          dialect
     * @param tableName        tableName
     * @param alias            alias
     * @param conditionBuilder conditionBuilder
     */
    public SqlFindBuilder(Dialect dialect, String tableName, String alias, SqlConditionGroup conditionBuilder) {
        super(dialect, tableName, alias, conditionBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBuilder property(String... propertyNames) {
        if (Lang.isNotEmpty(propertyNames)) {
            for (String p : propertyNames) {
                property(p);
            }
        }
        return this;
    }

    /**
     * With.
     *
     * @param propertyName      the property name
     * @param aggregateFunction the aggregate function
     * @return the find builder
     */
    public FindBuilder with(String propertyName, AggregateFunction aggregateFunction) {
        addSelectColumn(propertyName, aggregateFunction);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBuilder property(String propertyName) {
        return with(propertyName, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBuilder property(Collection<String> propertyNames) {
        if (Lang.isNotEmpty(propertyNames)) {
            for (String p : propertyNames) {
                property(p);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConditionBuilder where() {
        return conditionBuilder;
    }
}
