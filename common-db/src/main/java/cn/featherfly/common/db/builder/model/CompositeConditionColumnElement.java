
package cn.featherfly.common.db.builder.model;

import java.util.function.Predicate;

import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * ArithmeticColumnElement .
 *
 * @author zhongj
 */
public class CompositeConditionColumnElement extends ConditionColumnElement {

    private ColumnElement column;

    /**
     * Instantiates a new composite condition column element.
     *
     * @param dialect the dialect
     * @param column the column
     * @param value the value
     * @param comparisonOperator the comparison operator
     * @param ignoreStrategy the ignore strategy
     */
    public CompositeConditionColumnElement(Dialect dialect, ColumnElement column, Object value,
        ComparisonOperator comparisonOperator, Predicate<?> ignoreStrategy) {
        this(dialect, column.tableAlias, column, value, comparisonOperator, ignoreStrategy);
    }

    /**
     * Instantiates a new composite condition column element.
     *
     * @param dialect the dialect
     * @param tableAlias the table alias
     * @param column the column
     * @param value the value
     * @param comparisonOperator the comparison operator
     * @param ignoreStrategy the ignore strategy
     */
    public CompositeConditionColumnElement(Dialect dialect, String tableAlias, ColumnElement column, Object value,
        ComparisonOperator comparisonOperator, Predicate<?> ignoreStrategy) {
        super(dialect, tableAlias, column.name, value, comparisonOperator, ignoreStrategy);
        this.column = column;
    }

    /**
     * Instantiates a new composite condition column element.
     *
     * @param dialect the dialect
     * @param column the column
     * @param value the value
     * @param comparisonOperator the comparison operator
     * @param matchStrategy the match strategy
     * @param tableAlias the table alias
     * @param ignoreStrategy the ignore strategy
     */
    public CompositeConditionColumnElement(Dialect dialect, String tableAlias, ColumnElement column, Object value,
        ComparisonOperator comparisonOperator, MatchStrategy matchStrategy,
        Predicate<?> ignoreStrategy) {
        super(dialect, tableAlias, column.name, value, comparisonOperator, matchStrategy, ignoreStrategy);
        this.column = column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        if (column instanceof ParamedColumnElement) {
            return SqlUtils.flatParams(((ParamedColumnElement) column).getParam(), super.getParam());
        } else {
            return super.getParam();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        if (Lang.isEmpty(name)) {
            return "";
        }
        Object value = param;
        String name = column.toSql();
        if (value instanceof SqlElement) {
            return dialect.dml().compareExpression(comparisonOperator, name, (SqlElement) value, matchStrategy);
        } else if (ignore(value)) { // 忽略
            return "";
        } else {
            return dialect.dml().compareExpression(comparisonOperator, name, value, matchStrategy);
        }
    }
}
