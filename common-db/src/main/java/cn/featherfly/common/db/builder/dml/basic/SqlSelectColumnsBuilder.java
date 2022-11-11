
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

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * SqlSelectColumnsBuilder.
 *
 * @author zhongj
 */
public interface SqlSelectColumnsBuilder<B extends SqlSelectColumnsBuilder<B>> extends SqlBuilder {

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @return this
     */
    B addColumn(AggregateFunction aggregateFunction, String column);

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @param asName            alias name
     * @return this
     */
    B addColumn(AggregateFunction aggregateFunction, String column, String asName);

    /**
     * add column.
     *
     * @param column column
     * @return this
     */
    B addColumn(String column);

    /**
     * add column.
     *
     * @param column column
     * @param asName asName
     * @return this
     */
    B addColumn(String column, String asName);

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
}
