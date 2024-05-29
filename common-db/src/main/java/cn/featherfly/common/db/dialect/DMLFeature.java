
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:54:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.db.builder.model.TableElement;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.Function;

/**
 * DML features.
 *
 * @author zhongj
 */
public interface DMLFeature {
    /**
     * Builds the insert sql.
     *
     * @param tableName the table name
     * @return the delete from sql
     */
    default String deleteFrom(String tableName) {
        return deleteFrom(tableName, null);
    }

    /**
     * Builds the insert sql.
     *
     * @param tableName the table name
     * @param tableAlias the table alias
     * @return the delete from sql
     */
    String deleteFrom(String tableName, String tableAlias);

    /**
     * Builds the insert sql.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param autoIncrement the auto increment
     * @return the string
     */
    default String insert(String tableName, String[] columnNames, boolean autoIncrement) {
        return insert(tableName, null, columnNames, autoIncrement);
    }

    /**
     * Builds the insert sql.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param autoIncrement the auto increment
     * @return the string
     */
    String insert(String tableName, String pkColumnName, String[] columnNames, boolean autoIncrement);

    /**
     * dialect for database supports batch insert.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch insert
     */
    default String insertBatch(String tableName, String[] columnNames, int insertAmount, boolean autoIncrement) {
        return insertBatch(tableName, null, columnNames, insertAmount, autoIncrement);
    }

    /**
     * dialect for database supports batch insert.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch insert
     */
    String insertBatch(String tableName, String pkColumnName, String[] columnNames, int insertAmount,
        boolean autoIncrement);

    /**
     * Builds the upsert sql.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param uniqueColumn the unique column
     * @param autoIncrement the auto increment
     * @return sql of upsert
     */
    default String upsert(String tableName, String[] columnNames, String uniqueColumn, boolean autoIncrement) {
        return upsert(tableName, columnNames, new String[] { uniqueColumn }, autoIncrement);
    }

    /**
     * Builds the upsert sql.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param uniqueColumn the unique column
     * @param autoIncrement the auto increment
     * @return sql of upsert
     */
    default String upsert(String tableName, String pkColumnName, String[] columnNames, String uniqueColumn,
        boolean autoIncrement) {
        return upsert(tableName, pkColumnName, columnNames, new String[] { uniqueColumn }, autoIncrement);
    }

    /**
     * Builds the sql of upsert.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param uniqueColumns the unique columns
     * @param autoIncrement the auto increment
     * @return sql of upsert
     */
    default String upsert(String tableName, String[] columnNames, String[] uniqueColumns, boolean autoIncrement) {
        return upsertBatch(tableName, columnNames, uniqueColumns, 1, autoIncrement);
    }

    /**
     * Builds the sql of upsert.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param uniqueColumns the unique columns
     * @param autoIncrement the auto increment
     * @return sql of upsert
     */
    default String upsert(String tableName, String pkColumnName, String[] columnNames, String[] uniqueColumns,
        boolean autoIncrement) {
        return upsertBatch(tableName, pkColumnName, columnNames, uniqueColumns, 1, autoIncrement);
    }

    /**
     * Builds the sql of batch upsert.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param uniqueColumn the unique column
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch upsert
     */
    default String upsertBatch(String tableName, String[] columnNames, String uniqueColumn, int insertAmount,
        boolean autoIncrement) {
        return upsertBatch(tableName, null, columnNames, uniqueColumn, insertAmount, autoIncrement);
    }

    /**
     * Builds the sql of batch upsert.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param uniqueColumn the unique column
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch upsert
     */
    default String upsertBatch(String tableName, String pkColumnName, String[] columnNames, String uniqueColumn,
        int insertAmount, boolean autoIncrement) {
        return upsertBatch(tableName, pkColumnName, columnNames, new String[] { uniqueColumn }, insertAmount,
            autoIncrement);
    }

    /**
     * Builds the sql of batch upsert.
     *
     * @param tableName the table name
     * @param columnNames the column names
     * @param uniqueColumns the unique columns
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch upsert
     */
    default String upsertBatch(String tableName, String[] columnNames, String[] uniqueColumns, int insertAmount,
        boolean autoIncrement) {
        return upsertBatch(tableName, null, columnNames, uniqueColumns, insertAmount, autoIncrement);
    }

    /**
     * Builds the sql of batch upsert.
     *
     * @param tableName the table name
     * @param pkColumnName the pk column name
     * @param columnNames the column names
     * @param uniqueColumns the unique columns
     * @param insertAmount the insert amount
     * @param autoIncrement the auto increment
     * @return sql of batch upsert
     */
    String upsertBatch(String tableName, String pkColumnName, String[] columnNames, String[] uniqueColumns,
        int insertAmount, boolean autoIncrement);

    /**
     * build sql for column with aggregate function.
     *
     * @param function function
     * @param columnName columnName
     * @return sql of column part
     */
    default String column(Function function, String columnName) {
        return column(function, false, null, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param columnName columnName
     * @return sql of column part
     */
    default String column(AggregateFunction aggregateFunction, String columnName) {
        return column(aggregateFunction, false, null, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @return sql of column part
     */
    default String column(String tableAlias, String columnName) {
        return column(false, tableAlias, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param tableAlias tableAlias
     * @param columnName the column name
     * @param columnAlias the column alias
     * @return sql of column part
     */
    default String column(String tableAlias, String columnName, String columnAlias) {
        return column(null, false, tableAlias, columnName, columnAlias);
    }

    //    /**
    //     * build sql for column with tableAlias and aggregate function.
    //     *
    //     * @param columnName        columnName
    //     * @param tableAlias        tableAlias
    //     * @param aggregateFunction aggregateFunction
    //     * @return sql
    //     */
    //    default String buildColumnSql(AggregateFunction aggregateFunction, String columnName, String tableAlias) {
    //        return buildColumnSql(aggregateFunction, false, tableAlias, columnName, null);
    //    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param function the function
     * @param distinct the distinct
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @param columnAlias the column alias
     * @return sql of column part
     */
    default String column(Function function, boolean distinct, String tableAlias, String columnName,
        String columnAlias) {
        if (function instanceof AggregateFunction) {
            return column(function, distinct, tableAlias, columnName, columnAlias);
        }
        // YUFEI_TODO 后续添加其他实现
        throw new DialectException("只实现了 AggregateFunction，未实现的 function" + function.getClass().getName());
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param distinct the distinct
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @param columnAlias the column alias
     * @return sql of column part
     */
    default String column(boolean distinct, String tableAlias, String columnName, String columnAlias) {
        return column(null, distinct, tableAlias, columnName, columnAlias);
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @param columnAlias the column alias
     * @return sql of column part
     */
    default String column(AggregateFunction aggregateFunction, String tableAlias, String columnName,
        String columnAlias) {
        return column(aggregateFunction, false, tableAlias, columnName, columnAlias);
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param distinct the distinct
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @param columnAlias the column alias
     * @return sql of column part
     */
    String column(AggregateFunction aggregateFunction, boolean distinct, String tableAlias, String columnName,
        String columnAlias);

    //    /**
    //     * build sql for column with tableAlias and aggregate function.
    //     *
    //     * @param columnName columnName
    //     * @param tableAlias tableAlias
    //     * @param function   function
    //     * @return sql
    //     */
    //    default String buildColumnSql(Function function, String columnName, String tableAlias) {
    //        if (function instanceof AggregateFunction) {
    //            return buildColumnSql(function, columnName, tableAlias);
    //        }
    //        // TODO 后续添加其他实现
    //        throw new DialectException("只实现了 AggregateFunction，未实现的 function" + function.getClass().getName());
    //    }

    /**
     * build sql for table with tableAlias if necessity.
     *
     * @param table table
     * @return sql of table part
     */
    default String table(TableElement table) {
        return table(table.getName(), table.getAlias());
    }

    /**
     * build sql for table.
     *
     * @param tableName tableName
     * @return sql of table part
     */
    default String table(String tableName) {
        return table(tableName, null);
    }

    /**
     * build sql for table with tableAlias.
     *
     * @param tableName tableName
     * @param tableAlias tableAlias
     * @return sql of table part
     */
    String table(String tableName, String tableAlias);

    /**
     * the compare expression.
     *
     * @param operator the operator
     * @param name the name
     * @param values the values
     * @return the compare expression
     */
    default String compareExpression(ComparisonOperator operator, String name, Object values) {
        return compareExpression(operator, name, values, MatchStrategy.AUTO);
    }

    /**
     * the compare expression.
     *
     * @param operator the operator
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @return the compare expression
     */
    String compareExpression(ComparisonOperator operator, String columnName, Object values, String tableAlias);

    /**
     * the compare expression.
     *
     * @param operator the operator
     * @param name the name
     * @param values the values
     * @param matchStrategy the match strategy
     * @return the compare expression
     */
    String compareExpression(ComparisonOperator operator, String name, Object values, MatchStrategy matchStrategy);

    /**
     * the compare expression.
     *
     * @param operator the operator
     * @param name the name
     * @param values the values
     * @param matchStrategy the match strategy
     * @return the compare expression
     */
    String compareExpression(ComparisonOperator operator, String name, SqlElement values, MatchStrategy matchStrategy);

    /**
     * Gets the compare expression.
     *
     * @param operator the operator
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @param matchStrategy the match strategy
     * @return the compare expression
     */
    String compareExpression(ComparisonOperator operator, String columnName, Object values, String tableAlias,
        MatchStrategy matchStrategy);

    /**
     * Gets the checks if is null or not is null expression.
     *
     * @param isNull the is null
     * @param name the name
     * @return the checks if is null or not is null expression
     */
    String isNullOrNotIsNullExpression(boolean isNull, String name);

    /**
     * Gets the checks if is null or not is null expression.
     *
     * @param isNull the is null
     * @param columnName the column name
     * @param tableAlias the table alias
     * @return the checks if is null or not is null expression
     */
    default String isNullOrNotIsNullExpression(boolean isNull, String columnName, String tableAlias) {
        return isNullOrNotIsNullExpression(isNull, column(tableAlias, columnName));
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param name the name
     * @param value the value
     * @return the between or not between expression
     */
    String betweenOrNotBetweenExpression(boolean isBetween, String name, Object value);

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @return the between or not between expression
     */
    default String betweenOrNotBetweenExpression(boolean isBetween, String columnName, Object values,
        String tableAlias) {
        return betweenOrNotBetweenExpression(isBetween, column(tableAlias, columnName), values);
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param name the name
     * @param values the values
     * @param matchStrategy the match strategy
     * @return the between or not between expression
     */
    String betweenOrNotBetweenExpression(boolean isBetween, String name, Object values, MatchStrategy matchStrategy);

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @param matchStrategy the match strategy
     * @return the between or not between expression
     */
    default String betweenOrNotBetweenExpression(boolean isBetween, String columnName, Object values, String tableAlias,
        MatchStrategy matchStrategy) {
        return betweenOrNotBetweenExpression(isBetween, column(tableAlias, columnName), values, matchStrategy);
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param name the name
     * @param min the min
     * @param max the max
     * @return the between or not between expression
     */
    String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max);

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param columnName the column name
     * @param min the min
     * @param max the max
     * @param matchStrategy the match strategy
     * @return the between or not between expression
     */
    String betweenOrNotBetweenExpression(boolean isBetween, String columnName, SqlElement min, SqlElement max,
        MatchStrategy matchStrategy);

    /**
     * Gets the in or not in expression.
     *
     * @param isIn the is in
     * @param name the name
     * @param values the values
     * @return the in or not in expression
     */
    String inOrNotInExpression(boolean isIn, String name, Object values);

    /**
     * Gets the in or not in expression.
     *
     * @param isIn the is in
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @return the in or not in expression
     */
    default String inOrNotInExpression(boolean isIn, String columnName, Object values, String tableAlias) {
        return inOrNotInExpression(isIn, column(tableAlias, columnName), values);
    }

    /**
     * Gets the in or not in expression.
     *
     * @param isIn the is in
     * @param name the name
     * @param values the values
     * @param matchStrategy the match strategy
     * @return the in or not in expression
     */
    String inOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy);

    /**
     * Gets the in or not in expression.
     *
     * @param isIn the is in
     * @param columnName the column name
     * @param values the values
     * @param tableAlias the table alias
     * @param matchStrategy the match strategy
     * @return the in or not in expression
     */
    default String inOrNotInExpression(boolean isIn, String columnName, Object values, String tableAlias,
        MatchStrategy matchStrategy) {
        return inOrNotInExpression(isIn, column(tableAlias, columnName), values, matchStrategy);
    }
}
