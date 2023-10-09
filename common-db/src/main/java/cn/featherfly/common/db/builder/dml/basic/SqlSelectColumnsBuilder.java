
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SqlSelectColumnsBasicBuilder.java
 * @Package cn.featherfly.common.db.builder.dml.basic
 * @Description: SqlSelectColumnsBasicBuilder
 * @author: zhongj
 * @date: 2022-08-16 17:14:16
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.builder.dml.basic;

import java.util.Collection;
import java.util.function.BiFunction;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * SqlSelectColumnsBuilder.
 *
 * @author zhongj
 * @param <B> the generic type
 */
public interface SqlSelectColumnsBuilder<B extends SqlSelectColumnsBuilder<B>> extends SqlBuilder {

    /**
     * Clear columns.
     *
     * @return the b
     */
    B clearColumns();

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @return this
     */
    default B addColumn(AggregateFunction aggregateFunction, String column) {
        return addColumn(aggregateFunction, false, column);
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param distinct          the distinct
     * @param column            column
     * @return this
     */
    B addColumn(AggregateFunction aggregateFunction, boolean distinct, String column);

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @param alias             alias name
     * @return this
     */
    default B addColumn(AggregateFunction aggregateFunction, String column, String alias) {
        return addColumn(aggregateFunction, false, column, alias);
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param distinct          the distinct
     * @param column            column
     * @param alias             alias name
     * @return this
     */
    B addColumn(AggregateFunction aggregateFunction, boolean distinct, String column, String alias);

    /**
     * add column.
     *
     * @param column column
     * @return this
     */
    default B addColumn(String column) {
        return addColumn(false, column);
    }

    /**
     * add column.
     *
     * @param distinct the distinct
     * @param column   column
     * @return this
     */
    B addColumn(boolean distinct, String column);

    /**
     * add column.
     *
     * @param column column
     * @param alias  alias name
     * @return this
     */
    default B addColumn(String column, String alias) {
        return addColumn(false, column, alias);
    }

    /**
     * add column.
     *
     * @param distinct the distinct
     * @param column   column
     * @param alias    alias name
     * @return this
     */
    B addColumn(boolean distinct, String column, String alias);

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    B addColumns(String... columns);

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    B addColumns(Collection<String> columns);

    /**
     * Sets the column alias prefix.
     *
     * @param columnAliasPrefixTableAlias the column alias prefix table alias
     * @return the this
     */
    B setColumnAliasPrefixTableAlias(boolean columnAliasPrefixTableAlias);

    //    /**
    //     * Sets the column alias prefix.
    //     *
    //     * @param columnAliasPrefix the column alias prefix
    //     * @return the this
    //     */
    //    B setColumnAliasPrefix(String columnAliasPrefix);
    //
    //    /**
    //     * Gets the column alias prefix.
    //     *
    //     * @return the column alias prefix
    //     */
    //    String getColumnAliasPrefix();

    /**
     * Sets the column alias creator.
     *
     * @param columnAliasPrefixProcessor the column alias prefix processor
     *                                   <ul>
     *                                   <li>argu1 columnAliasPrefix
     *                                   <li>argu2 columnAliasPrefixTableAlias
     *                                   <li>argu3 new columnAliasPrefix
     *                                   </ul>
     */
    void setColumnAliasPrefixProcessor(BiFunction<String, Boolean, String> columnAliasPrefixProcessor);
}
