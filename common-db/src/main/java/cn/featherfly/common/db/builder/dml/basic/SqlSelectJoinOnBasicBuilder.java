
package cn.featherfly.common.db.builder.dml.basic;

import java.util.Collection;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * SqlSelectJoinOnBasicBuilder
 * </p>
 *
 * @author zhongj
 */
public class SqlSelectJoinOnBasicBuilder implements SqlBuilder {

    private SqlSelectBasicBuilder selectBuilder;

    private SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder;

    private boolean fetched;

    /**
     * Instantiates a new sql select join on basic builder.
     *
     * @param selectBuilder            the select builder
     * @param joinSelectColumnsBuilder the join select columns builder
     */
    public SqlSelectJoinOnBasicBuilder(SqlSelectBasicBuilder selectBuilder,
            SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder) {
        super();
        this.selectBuilder = selectBuilder;
        this.joinSelectColumnsBuilder = joinSelectColumnsBuilder;
    }

    /**
     * add column
     *
     * @param column            column
     * @param aggregateFunction aggregateFunction
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumn(String column, AggregateFunction aggregateFunction) {
        joinSelectColumnsBuilder.addSelectColumn(column, aggregateFunction);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column
     *
     * @param column            column
     * @param aggregateFunction aggregateFunction
     * @param asName            alias name
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumn(String column, AggregateFunction aggregateFunction,
            String asName) {
        joinSelectColumnsBuilder.addSelectColumn(column, aggregateFunction, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column
     *
     * @param column column
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumn(String column) {
        joinSelectColumnsBuilder.addSelectColumn(column);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * add column
     *
     * @param column column
     * @param asName asName
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumn(String column, String asName) {
        joinSelectColumnsBuilder.addSelectColumn(column, asName);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * addColumns
     *
     * @param columns columns
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumns(String... columns) {
        joinSelectColumnsBuilder.addSelectColumns(columns);
        addJoinSelectColumnsBuilder();
        return this;
    }

    /**
     * addColumns
     *
     * @param columns columns
     * @return this
     */
    public SqlSelectJoinOnBasicBuilder addSelectColumns(Collection<String> columns) {
        joinSelectColumnsBuilder.addSelectColumns(columns);
        addJoinSelectColumnsBuilder();
        return this;
    }

    public SqlSelectBasicBuilder endJoin() {
        return selectBuilder;
    }

    public SqlSelectBasicBuilder fetch() {
        addJoinSelectColumnsBuilder();
        return endJoin();
    }

    public SqlSelectBasicBuilder fetch(String fetchProperty, String fetchPropertyAlias) {
        if (!fetched) {
            selectBuilder.addSelectProperty(fetchProperty, fetchPropertyAlias);
            fetched = true;
        }
        return endJoin();
    }

    private void addJoinSelectColumnsBuilder() {
        if (!fetched) {
            selectBuilder.addJoinSelectColumnsBasicBuilder(joinSelectColumnsBuilder);
            fetched = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return selectBuilder.build();
    }

}
