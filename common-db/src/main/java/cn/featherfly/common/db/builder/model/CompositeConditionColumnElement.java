
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

    private ParamedColumnElement column;

    /**
     * Instantiates a new composite condition column element.
     *
     * @param dialect the dialect
     * @param column the column
     * @param value the value
     * @param comparisonOperator the comparison operator
     * @param ignoreStrategy the ignore strategy
     */
    public CompositeConditionColumnElement(Dialect dialect, ParamedColumnElement column, Object value,
        ComparisonOperator comparisonOperator, Predicate<?> ignoreStrategy) {
        this(dialect, column, value, comparisonOperator, null, ignoreStrategy);
    }

    /**
     * Instantiates a new composite condition column element.
     *
     * @param dialect the dialect
     * @param column the column
     * @param value the value
     * @param comparisonOperator the comparison operator
     * @param tableAlias the table alias
     * @param ignoreStrategy the ignore strategy
     */
    public CompositeConditionColumnElement(Dialect dialect, ParamedColumnElement column, Object value,
        ComparisonOperator comparisonOperator, String tableAlias, Predicate<?> ignoreStrategy) {
        super(dialect, column.name, value, comparisonOperator, tableAlias, ignoreStrategy);
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
    public CompositeConditionColumnElement(Dialect dialect, ParamedColumnElement column, Object value,
        ComparisonOperator comparisonOperator, MatchStrategy matchStrategy, String tableAlias,
        Predicate<?> ignoreStrategy) {
        super(dialect, column.name, value, comparisonOperator, matchStrategy, tableAlias, ignoreStrategy);
        this.column = column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        return SqlUtils.flatParams(column.getParam(), super.getParam());
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
