/*
 * All rights Reserved, Designed By zhongj
 * @Title: ConditionColumnElement.java
 * @Package cn.featherfly.common.db.builder.model
 * @Description: ConditionColumnElement
 * @author: zhongj
 * @date: 2023年7月31日 下午4:23:07
 * @version V1.0
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */

package cn.featherfly.common.db.builder.model;

import java.util.function.Predicate;

import cn.featherfly.common.db.FieldValueOperator;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.repository.Params;

/**
 * condition column element .
 *
 * @author zhongj
 */
public class ConditionColumnElement extends ParamedColumnElement {

    private ComparisonOperator comparisonOperator;

    private MatchStrategy matchStrategy;

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect            dialect
     * @param name               name
     * @param value              param value
     * @param comparisonOperator comparisonOperator
     * @param ignoreStrategy     the ignore strategy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, ComparisonOperator comparisonOperator,
            Predicate<?> ignoreStrategy) {
        this(dialect, name, value, comparisonOperator, null, ignoreStrategy);
    }

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect            dialect
     * @param name               name
     * @param value              param value
     * @param comparisonOperator comparisonOperator
     * @param tableAlias         tableAlias
     * @param ignoreStrategy     the ignore strategy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, ComparisonOperator comparisonOperator,
            String tableAlias, Predicate<?> ignoreStrategy) {
        this(dialect, name, value, comparisonOperator, MatchStrategy.AUTO, tableAlias, ignoreStrategy);
    }

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect            the dialect
     * @param name               the name
     * @param value              the value
     * @param comparisonOperator the comparison operator
     * @param matchStrategy      the match strategy
     * @param tableAlias         the table alias
     * @param ignoreStrategy     the ignore strategy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, ComparisonOperator comparisonOperator,
            MatchStrategy matchStrategy, String tableAlias, Predicate<?> ignoreStrategy) {
        super(dialect, name, value, tableAlias, ignoreStrategy);
        AssertIllegalArgument.isNotNull(comparisonOperator, "comparisonOperator");
        if (matchStrategy == null) {
            matchStrategy = MatchStrategy.AUTO;
        }
        this.comparisonOperator = comparisonOperator;
        this.matchStrategy = matchStrategy;

        if (!ignore(value)) { // 不忽略
            setParam(processParam(value, comparisonOperator));
        }
    }

    @SuppressWarnings("unchecked")
    private Object processParam(Object value, ComparisonOperator comparisonOperator) {
        switch (comparisonOperator) {
            case SW:
            case NSW:
                if (value instanceof FieldValueOperator) {
                    FieldValueOperator<String> o = (FieldValueOperator<String>) value;
                    o.setValue(o.getValue() + "%");
                    return o;
                } else {
                    return value + "%";
                }
            case CO:
            case NCO:
                if (value instanceof FieldValueOperator) {
                    FieldValueOperator<String> o = (FieldValueOperator<String>) value;
                    o.setValue("%" + o.getValue() + "%");
                    return o;
                } else {
                    return "%" + value + "%";
                }
            case EW:
            case NEW:
                if (value instanceof FieldValueOperator) {
                    FieldValueOperator<String> o = (FieldValueOperator<String>) value;
                    o.setValue("%" + o.getValue());
                    return o;
                } else {
                    return "%" + value;
                }
            default:
                return value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        if (ComparisonOperator.ISN == comparisonOperator || ComparisonOperator.INN == comparisonOperator) {
            return Params.NONE;
        } else {
            if (param instanceof SqlElement) {
                return super.getParam();
            } else if (ignore(param)) {
                return Params.NONE;
            } else {
                return param;
            }
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
        String name = dialect.dml().column(tableAlias, this.name);
        if (value instanceof SqlElement) {
            return dialect.dml().compareExpression(comparisonOperator, name, (SqlElement) value, matchStrategy);
        } else if (ignore(value)) { // 忽略
            return "";
        } else {
            return dialect.dml().compareExpression(comparisonOperator, name, value, matchStrategy);
        }
    }
}
