
package cn.featherfly.common.db.builder.model;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.operator.CalculationOperator;
import cn.featherfly.common.repository.IgnoreStrategy;

/**
 * ArithmeticColumnElement .
 *
 * @author zhongj
 */
public class ArithmeticColumnElement extends ParamedColumnElement {

    private final List<CalculationOperator> calculationOperators = new ArrayList<>();

    private final List<Object> params = new ArrayList<>();

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param name the name
     */
    public ArithmeticColumnElement(Dialect dialect, String name) {
        this(dialect, name, null);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param name the name
     * @param tableAlias the table alias
     */
    public ArithmeticColumnElement(Dialect dialect, String name, String tableAlias) {
        super(dialect, name, null, tableAlias, IgnoreStrategy.NONE);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param name the name
     * @param calculationOperator the calculation operator
     * @param param the param
     */
    public ArithmeticColumnElement(Dialect dialect, String name, CalculationOperator calculationOperator,
        Object param) {
        this(dialect, name, calculationOperator, param, null);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param name the name
     * @param calculationOperator the calculation operator
     * @param param the param
     * @param tableAlias the table alias
     */
    public ArithmeticColumnElement(Dialect dialect, String name, CalculationOperator calculationOperator, Object param,
        String tableAlias) {
        super(dialect, name, param, tableAlias, IgnoreStrategy.NONE);
        add(calculationOperator, param);
    }

    /**
     * Gets the calculation operator.
     *
     * @return the calculation operator
     */
    public CalculationOperator[] getCalculationOperator() {
        return CollectionUtils.toArray(calculationOperators, CalculationOperator.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        if (calculationOperators == null) {
            return dialect.dml().column(getTableAlias(), getName());
        } else {
            StringBuilder sb = new StringBuilder(dialect.dml().column(getTableAlias(), getName()));
            for (CalculationOperator calculationOperator : calculationOperators) {
                sb.append(Chars.SPACE_CHAR).append(getSymbol(calculationOperator)).append(Chars.SPACE_CHAR)
                    .append(Chars.QUESTION_CHAR);
            }
            return sb.toString();
        }
    }

    private char getSymbol(CalculationOperator calculationOperator) {
        switch (calculationOperator) {
            case PLUS:
                return '+';
            case SUBTRACT:
                return '-';
            case MULTIPLY:
                return '*';
            case DIVIDE:
                return '/';
            default:
                throw new UnsupportedException("unknow Operator " + calculationOperator.name());
        }
    }

    /**
     * add calculation operator with param.
     *
     * @param calculationOperator the calculation operator
     * @param param the param
     * @return the arithmetic column element
     */
    public ArithmeticColumnElement add(CalculationOperator calculationOperator, Object param) {
        AssertIllegalArgument.isNotNull(param, "calculationOperator param value");
        calculationOperators.add(calculationOperator);
        params.add(param);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        return params;
    }
}
