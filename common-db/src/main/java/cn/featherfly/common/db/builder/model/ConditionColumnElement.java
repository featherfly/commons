
package cn.featherfly.common.db.builder.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.function.Predicate;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.FieldValueOperator;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.QueryOperator;
import cn.featherfly.common.operator.QueryOperator.QueryPolicy;
import cn.featherfly.common.repository.Params;

/**
 * condition column element .
 *
 * @author zhongj
 */
public class ConditionColumnElement extends ParamedColumnElement {

    private QueryOperator queryOperator;

    private QueryPolicy queryPolicy;

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect       dialect
     * @param name          name
     * @param value         param value
     * @param queryOperator queryOperator
     * @param ignorePolicy  the ignore policy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, QueryOperator queryOperator,
            Predicate<Object> ignorePolicy) {
        this(dialect, name, value, queryOperator, null, ignorePolicy);
    }

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect       dialect
     * @param name          name
     * @param value         param value
     * @param queryOperator queryOperator
     * @param tableAlias    tableAlias
     * @param ignorePolicy  the ignore policy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, QueryOperator queryOperator,
            String tableAlias, Predicate<Object> ignorePolicy) {
        this(dialect, name, value, queryOperator, QueryPolicy.AUTO, tableAlias, ignorePolicy);
    }

    /**
     * Instantiates a new condition column element.
     *
     * @param dialect       dialect
     * @param name          name
     * @param value         param value
     * @param queryOperator queryOperator
     * @param queryPolicy   the query policy
     * @param tableAlias    tableAlias
     * @param ignorePolicy  the ignore policy
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, QueryOperator queryOperator,
            QueryPolicy queryPolicy, String tableAlias, Predicate<Object> ignorePolicy) {
        super(dialect, name, value, tableAlias, ignorePolicy);
        AssertIllegalArgument.isNotNull(queryOperator, "queryOperator");
        if (queryPolicy == null) {
            queryPolicy = QueryPolicy.AUTO;
        }
        this.queryOperator = queryOperator;
        this.queryPolicy = queryPolicy;

        if (!ignore(value)) { // 不忽略
            setParam(processParam(value, queryOperator));
        }
    }

    private boolean ignore(Object value) {
        if (value instanceof FieldValueOperator) {
            return ignorePolicy.test(((FieldValueOperator<?>) value).getValue());
        } else {
            return ignorePolicy.test(value);
        }
    }

    @SuppressWarnings("unchecked")
    private Object processParam(Object value, QueryOperator queryOperator) {
        switch (queryOperator) {
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
        if (QueryOperator.ISN == queryOperator || QueryOperator.INN == queryOperator) {
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
            if (QueryOperator.IN == queryOperator || QueryOperator.NIN == queryOperator) {
                int length = 1;
                if (value instanceof Collection) {
                    length = ((Collection<?>) value).size();
                } else if (value.getClass().isArray()) {
                    length = Array.getLength(value);
                }
                condition.append(name).append(Chars.SPACE).append(toOperator(queryOperator)).append(" (");
                for (int i = 0; i < length; i++) {
                    if (i > 0) {
                        condition.append(Chars.COMMA);
                    }
                    condition.append(Chars.QUESTION);
                }
                condition.append(")");
            } else {
                condition.append(name).append(Chars.SPACE);
                if (QueryOperator.ISN == queryOperator) {
                    if ((Boolean) value) {
                        condition.append(toOperator(queryOperator));
                    } else {
                        condition.append(toOperator(QueryOperator.INN));
                    }
                } else if (QueryOperator.INN == queryOperator) {
                    if ((Boolean) value) {
                        condition.append(toOperator(queryOperator));
                    } else {
                        condition.append(toOperator(QueryOperator.ISN));
                    }
                } else {
                    condition.append(toOperator(queryOperator)).append(Chars.SPACE).append(Chars.QUESTION);
                }
            }
        }
        return condition.toString();
    }

    private String toOperator(QueryOperator queryOperator) {
        switch (queryOperator) {
            case LT:
                return "<";
            case LE:
                return "<=";
            case GT:
                return ">";
            case GE:
                return ">=";
            case EQ:
                return dialect.getKeywords().eq(queryPolicy);
            case NE:
                return "!=";
            case SW:
                return dialect.getKeywords().like(queryPolicy);
            case CO:
                return dialect.getKeywords().like(queryPolicy);
            case EW:
                return dialect.getKeywords().like(queryPolicy);
            case ISN:
                return dialect.getKeywords().isNull();
            case INN:
                return dialect.getKeywords().isNotNull();
            case IN:
                return dialect.getKeywords().in();
            case NIN:
                return dialect.getKeywords().notIn();
            case LK:
                return dialect.getKeywords().like(queryPolicy);
            default:
                return dialect.getKeywords().eq(queryPolicy);
        }
    }
}
