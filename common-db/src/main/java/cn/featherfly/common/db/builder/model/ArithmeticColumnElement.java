
package cn.featherfly.common.db.builder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.operator.CalculationOperator;
import cn.featherfly.common.repository.IgnoreStrategy;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.Tuples;

/**
 * ArithmeticColumnElement .
 *
 * @author zhongj
 */
public class ArithmeticColumnElement extends ParamedColumnElement {

    private ColumnElement columnElement;

    //    private final List<CalculationOperator> calculationOperators = new ArrayList<>();
    //
    //    private final List<Object> params = new ArrayList<>();

    private final List<Tuple2<CalculationOperator, Object>> calculationOperators = new ArrayList<>();

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param columnElement the column element
     * @param calculationOperator the calculation operator
     * @param param the param
     */
    public ArithmeticColumnElement(ColumnElement columnElement, CalculationOperator calculationOperator,
        Object param) {
        this(columnElement.getDialect(), columnElement.getName());
        this.columnElement = columnElement;
        add(calculationOperator, param);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param name the name
     */
    public ArithmeticColumnElement(Dialect dialect, String name) {
        this(dialect, null, name);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param tableAlias the table alias
     * @param name the name
     */
    public ArithmeticColumnElement(Dialect dialect, String tableAlias, String name) {
        super(dialect, tableAlias, name, null, IgnoreStrategy.NONE);
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
        this(dialect, null, name, calculationOperator, param);
    }

    /**
     * Instantiates a new arithmetic column element.
     *
     * @param dialect the dialect
     * @param tableAlias the table alias
     * @param name the name
     * @param calculationOperator the calculation operator
     * @param param the param
     */
    public ArithmeticColumnElement(Dialect dialect, String tableAlias, String name,
        CalculationOperator calculationOperator, Object param) {
        super(dialect, tableAlias, name, param, IgnoreStrategy.NONE);
        add(calculationOperator, param);
    }

    /**
     * Gets the calculation operator.
     *
     * @return the calculation operator
     */
    public CalculationOperator[] getCalculationOperator() {
        //        return CollectionUtils.toArray(calculationOperators, CalculationOperator.class);
        return calculationOperators.stream().map(t -> t.get0()).toArray(n -> new CalculationOperator[n]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        return calculationOperators.stream()
            //            .filter(t -> !(t.get1() instanceof SqlElement))
            .map(t -> t.get1())
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        if (calculationOperators.isEmpty()) {
            if (columnElement == null) {
                //            return dialect.dml().column(getTableAlias(), getName());
                return super.toSql();
            } else {
                return columnElement.toSql();
            }
        } else {
            StringBuilder sb;
            if (columnElement == null) {
                sb = new StringBuilder(dialect.dml().column(getTableAlias(), getName()));
            } else {
                sb = new StringBuilder(columnElement.toSql());
            }
            for (Tuple2<CalculationOperator, Object> calculationOperator : calculationOperators) {
                sb.append(Chars.SPACE).append(getSymbol(calculationOperator.get0())).append(Chars.SPACE)
                    .append(calculationOperator.get1() instanceof SqlElement
                        ? ((SqlElement) calculationOperator.get1()).toSql()
                        : Chars.QUESTION);
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
        //        calculationOperators.add(calculationOperator);
        //        params.add(param);
        calculationOperators.add(Tuples.of(calculationOperator, param));
        return this;
    }

}
