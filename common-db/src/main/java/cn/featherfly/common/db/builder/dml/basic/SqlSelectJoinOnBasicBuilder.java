
package cn.featherfly.common.db.builder.dml.basic;

import java.util.Collection;
import java.util.function.BiFunction;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * SqlSelectJoinOnBasicBuilder .
 *
 * @author zhongj
 */
public class SqlSelectJoinOnBasicBuilder implements SqlBuilder {

    /** The select builder. */
    private SqlSelectBasicBuilder selectBuilder;

    private SqlSelectColumnsBuilder<?> joinSelectColumnsBuilder;

    private boolean fetched;

    /**
     * Instantiates a new sql select join on basic builder.
     *
     * @param selectBuilder the select builder
     * @param joinSelectColumnsBuilder the join select columns builder
     */
    public SqlSelectJoinOnBasicBuilder(SqlSelectBasicBuilder selectBuilder,
        SqlSelectColumnsBuilder<?> joinSelectColumnsBuilder) {
        super();
        this.selectBuilder = selectBuilder;
        this.joinSelectColumnsBuilder = joinSelectColumnsBuilder;
    }

    /**
     * add column.
     *
     * @param column column
     * @param aggregateFunction aggregateFunction
     * @return the sql select join on basic builder
     * @deprecated {@link #addColumn(AggregateFunction, String)}
     */
    @Deprecated
    public SqlSelectJoinOnBasicBuilder addColumn(String column, AggregateFunction aggregateFunction) {
        return addColumn(aggregateFunction, column);
    }

    /**
     * add column.
     *
     * @param column column
     * @param aggregateFunction aggregateFunction
     * @param asName alias name
     * @return this
     * @deprecated {@link #addColumn(AggregateFunction, String, String)}
     */
    @Deprecated
    public SqlSelectJoinOnBasicBuilder addColumn(String column, AggregateFunction aggregateFunction, String asName) {
        return addColumn(aggregateFunction, column, asName);
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column column
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(AggregateFunction aggregateFunction, String column) {
        joinSelectColumnsBuilder.addColumn(aggregateFunction, column);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column column
     * @param asName alias name
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(AggregateFunction aggregateFunction, String column, String asName) {
        joinSelectColumnsBuilder.addColumn(aggregateFunction, column, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param distinct the distinct
     * @param column column
     * @param asName alias name
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(AggregateFunction aggregateFunction, boolean distinct, String column,
        String asName) {
        joinSelectColumnsBuilder.addColumn(aggregateFunction, distinct, column, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column.
     *
     * @param column column
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(String column) {
        joinSelectColumnsBuilder.addColumn(column);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column.
     *
     * @param column column
     * @param asName asName
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(String column, String asName) {
        joinSelectColumnsBuilder.addColumn(column, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column.
     *
     * @param distinct the distinct
     * @param column column
     * @param asName asName
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumn(boolean distinct, String column, String asName) {
        joinSelectColumnsBuilder.addColumn(distinct, column, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumns(String... columns) {
        joinSelectColumnsBuilder.addColumns(columns);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addColumns(Collection<String> columns) {
        joinSelectColumnsBuilder.addColumns(columns);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * End join.
     *
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder endJoin() {
        return selectBuilder;
    }

    /**
     * Fetch.
     *
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch() {
        addJoinSelectColumnsBuilder();
        return endJoin();
    }

    /**
     * Fetch.
     *
     * @param columnAliasProcessor the column alias processor .
     *        <p>
     *        arguments see
     *        {@link SqlSelectColumnsBuilder#setColumnAliasPrefixProcessor(BiFunction)}
     *        </p>
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch(BiFunction<Boolean, String, String> columnAliasProcessor) {
        joinSelectColumnsBuilder.setColumnAliasPrefixProcessor(columnAliasProcessor);
        addJoinSelectColumnsBuilder();
        return endJoin();
    }

    //    /**
    //     * Fetch.
    //     *
    //     * @param columnAliasPrefix the column alias prefix
    //     * @return the sql select basic builder
    //     */
    //    public SqlSelectBasicBuilder fetch(String columnAliasPrefix) {
    //        joinSelectColumnsBuilder.setColumnAliasPrefix(columnAliasPrefix);
    //        addJoinSelectColumnsBuilder();
    //        return endJoin();
    //    }

    /**
     * Fetch.
     *
     * @param fetchColumn the fetch column
     * @param fetchColumnAlias the fetch column alias
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch(String fetchColumn, String fetchColumnAlias) {
        return fetch(false, fetchColumn, fetchColumnAlias);
    }

    /**
     * Fetch.
     *
     * @param distinct the distinct
     * @param fetchColumn the fetch column
     * @param fetchColumnAlias the fetch column alias
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch(boolean distinct, String fetchColumn, String fetchColumnAlias) {
        if (!fetched) {
            selectBuilder.addColumn(distinct, fetchColumn, fetchColumnAlias);
            fetched = true;
        }
        return endJoin();
    }

    /**
     * Fetch.
     *
     * @param aggregateFunction the aggregate function
     * @param fetchColumn the fetch column
     * @param fetchColumnAlias the fetch column alias
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch(AggregateFunction aggregateFunction, String fetchColumn,
        String fetchColumnAlias) {
        return fetch(aggregateFunction, false, fetchColumn, fetchColumnAlias);
    }

    /**
     * Fetch.
     *
     * @param aggregateFunction the aggregate function
     * @param distinct the distinct
     * @param fetchColumn the fetch column
     * @param fetchColumnAlias the fetch column alias
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder fetch(AggregateFunction aggregateFunction, boolean distinct, String fetchColumn,
        String fetchColumnAlias) {
        if (!fetched) {
            selectBuilder.addColumn(distinct, fetchColumn, fetchColumnAlias);
            fetched = true;
        }
        return endJoin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return selectBuilder.build();
    }

    private void addJoinSelectColumnsBuilder() {
        if (!fetched) {
            selectBuilder.addJoinSelectColumnsBasicBuilder(joinSelectColumnsBuilder);
            fetched = true;
        }
    }

    //    private void addJoinSelectColumnsBuilder(String columnAliasPrefix) {
    //        if (!fetched) {
    //            joinSelectColumnsBuilder.setColumnAliasPrefix(columnAliasPrefix);
    //            selectBuilder.addJoinSelectColumnsBasicBuilder(joinSelectColumnsBuilder);
    //            fetched = true;
    //        }
    //    }
}
