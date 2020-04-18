
package cn.featherfly.common.db.builder.dml;

import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * SelectBuilder
 * </p>
 *
 * @author zhongj
 */
public interface SelectBuilder extends SqlBuilder {

    /**
     * <p>
     * 添加select的列
     * </p>
     *
     * @param columnName columnName
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName);

    /**
     * <p>
     * 添加select的列
     * </p>
     *
     * @param columnName columnName
     * @param asName     asName
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName, String asName);

    /**
     * <p>
     * 添加select的列
     * </p>
     *
     * @param columnName        columnName
     * @param aggregateFunction aggregateFunction
     * @param asName            asName
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName, AggregateFunction aggregateFunction, String asName);

    /**
     * <p>
     * 添加select的列
     * </p>
     *
     * @param columnName        columnName
     * @param aggregateFunction aggregateFunction
     * @return SelectBuilder
     */
    SelectBuilder select(String columnName, AggregateFunction aggregateFunction);

    /**
     * <p>
     * 批量添加select的列
     * </p>
     *
     * @param columnNames columnNames
     * @return SelectBuilder
     */
    SelectBuilder select(String[] columnNames);

    /**
     * <p>
     * 批量添加select的列
     * </p>
     *
     * @param columnNames columnNames
     * @return SelectBuilder
     */
    SelectBuilder select(Collection<String> columnNames);

    /**
     * <p>
     * 批量添加select的列
     * </p>
     *
     * @param columnNames columnNames map, key is columnName , value is asName
     * @return SelectBuilder
     */
    SelectBuilder select(Map<String, String> columnNames);

    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @param tableName tableName
     * @return ConditionBuilder
     */
    SqlConditionBuilder from(String tableName);

    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @param tableName  tableName
     * @param tableAlias tableAlias
     * @return ConditionBuilder
     */
    SqlConditionBuilder from(String tableName, String tableAlias);
}
