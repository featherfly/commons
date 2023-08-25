/*
 * All rights Reserved, Designed By zhongj
 * @Title: ConditionColumnElement.java
 * @Package cn.featherfly.common.db.builder.model
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2023年7月31日 下午4:23:07
 * @version V1.0
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */

package cn.featherfly.common.db.builder.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.function.Predicate;

import cn.featherfly.common.constant.Chars;
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

    private boolean ignore(Object value) {
        if (value instanceof FieldValueOperator) {
            return ignoreStrategy.test(((FieldValueOperator<?>) value).getValue());
        } else {
            return ignoreStrategy.test(value);
        }
    }

    @SuppressWarnings("unchecked")
    private Object processParam(Object value, ComparisonOperator comparisonOperator) {
        switch (comparisonOperator) {
            case SW:
                if (value instanceof FieldValueOperator) {
                    FieldValueOperator<String> o = (FieldValueOperator<String>) value;
                    o.setValue(o.getValue() + "%");
                    return o;
                } else {
                    return value + "%";
                }
            case CO:
                if (value instanceof FieldValueOperator) {
                    FieldValueOperator<String> o = (FieldValueOperator<String>) value;
                    o.setValue("%" + o.getValue() + "%");
                    return o;
                } else {
                    return "%" + value + "%";
                }
            case EW:
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
            if (ignore(param)) {
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
        StringBuilder condition = new StringBuilder();
        Object value = param;
        String name = dialect.buildColumnSql(tableAlias, this.name);
        if (ignore(value)) { // 忽略
            return "";
        } else {
            if (ComparisonOperator.IN == comparisonOperator || ComparisonOperator.NIN == comparisonOperator) {
                int length = 1;
                if (value instanceof Collection) {
                    length = ((Collection<?>) value).size();
                } else if (value.getClass().isArray()) {
                    length = Array.getLength(value);
                }
                condition.append(name).append(Chars.SPACE).append(toOperator(comparisonOperator)).append(" (");
                for (int i = 0; i < length; i++) {
                    if (i > 0) {
                        condition.append(Chars.COMMA);
                    }
                    condition.append(Chars.QUESTION);
                }
                condition.append(")");
            } else {
                condition.append(name).append(Chars.SPACE);
                if (ComparisonOperator.ISN == comparisonOperator) {
                    if ((Boolean) value) {
                        condition.append(toOperator(comparisonOperator));
                    } else {
                        condition.append(toOperator(ComparisonOperator.INN));
                    }
                } else if (ComparisonOperator.INN == comparisonOperator) {
                    if ((Boolean) value) {
                        condition.append(toOperator(comparisonOperator));
                    } else {
                        condition.append(toOperator(ComparisonOperator.ISN));
                    }
                } else {
                    condition.append(toOperator(comparisonOperator)).append(Chars.SPACE).append(Chars.QUESTION);
                }
            }
        }
        return condition.toString();
    }

    private String toOperator(ComparisonOperator comparisonOperator) {
        switch (comparisonOperator) {
            case LT:
                return "<";
            case LE:
                return "<=";
            case GT:
                return ">";
            case GE:
                return ">=";
            case EQ:
                return dialect.getKeywords().eq(matchStrategy);
            case NE:
                return "!=";
            case SW:
                return dialect.getKeywords().like(matchStrategy);
            case CO:
                return dialect.getKeywords().like(matchStrategy);
            case EW:
                return dialect.getKeywords().like(matchStrategy);
            case ISN:
                return dialect.getKeywords().isNull();
            case INN:
                return dialect.getKeywords().isNotNull();
            case IN:
                return dialect.getKeywords().in();
            case NIN:
                return dialect.getKeywords().notIn();
            case LK:
                return dialect.getKeywords().like(matchStrategy);
            default:
                return dialect.getKeywords().eq(matchStrategy);
        }
    }
}
