
package cn.featherfly.common.db.builder.dml;

import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * SelectBuilder.
 *
 * @author zhongj
 */
public interface SelectBuilder extends SqlBuilder {

    /**
     * 添加select的列.
     *
     * @param columnName columnName
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName);

    /**
     * 添加select的列.
     *
     * @param columnName columnName
     * @param asName     asName
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName, String asName);

    /**
     * 添加select的列.
     *
     * @param aggregateFunction aggregateFunction
     * @param columnName        columnName
     * @param asName            asName
     * @return SelectBuilder
     */
    SelectBuilder select(AggregateFunction aggregateFunction, String columnName, String asName);

    /**
     * 添加select的列.
     *
     * @param aggregateFunction aggregateFunction
     * @param columnName        columnName
     * @return SelectBuilder
     */
    SelectBuilder select(AggregateFunction aggregateFunction, String columnName);

    /**
     * 批量添加select的列.
     *
     * @param columnNames columnNames
     * @return SelectBuilder
     */
    SelectBuilder select(String[] columnNames);

    /**
     * 批量添加select的列.
     *
     * @param columnNames columnNames
     * @return SelectBuilder
     */
    SelectBuilder select(Collection<String> columnNames);

    /**
     * 批量添加select的列.
     *
     * @param columnNames columnNames map, key is columnName , value is asName
     * @return SelectBuilder
     */
    SelectBuilder select(Map<String, String> columnNames);

    /**
     * 添加from的表.
     *
     * @param tableName tableName
     * @return SqlConditionBuilder
     */
    SqlConditionBuilder from(String tableName);

    /**
     * 添加from的表.
     *
     * @param tableName  tableName
     * @param tableAlias tableAlias
     * @return SqlConditionBuilder
     */
    SqlConditionBuilder from(String tableName, String tableAlias);

    /**
     * 批量添加select的列.
     *
     * @param tableNames the table names
     * @return SqlConditionBuilder
     */
    SqlConditionBuilder from(String[] tableNames);

    /**
     * 批量添加select的列.
     *
     * @param tableNames the table names
     * @return SqlConditionBuilder
     */
    SqlConditionBuilder from(Collection<String> tableNames);

    /**
     * 添加from的表.
     *
     * @param tableNames tableNames map, key is tableName , value is tableAlias
     * @return SqlConditionBuilder
     */
    SqlConditionBuilder from(Map<String, String> tableNames);
}
