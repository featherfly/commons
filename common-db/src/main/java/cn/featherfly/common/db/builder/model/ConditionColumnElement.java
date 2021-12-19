
package cn.featherfly.common.db.builder.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.function.Predicate;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.operate.QueryOperator;

/**
 * <p>
 * condition column element
 * </p>
 * .
 *
 * @author zhongj
 */
public class ConditionColumnElement extends ParamedColumnElement {

    private QueryOperator queryOperator;

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
        super(dialect, name, value, tableAlias, ignorePolicy);
        AssertIllegalArgument.isNotNull(queryOperator, "queryOperator");
        this.queryOperator = queryOperator;

        if (!ignorePolicy.test(value)) { // 不忽略
            Object paramValue = null;
            switch (queryOperator) {
                case SW:
                    paramValue = value + "%";
                    break;
                case CO:
                    paramValue = "%" + value + "%";
                    break;
                case EW:
                    paramValue = "%" + value;
                    break;
                default:
                    paramValue = value;
            }
            setParam(paramValue);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParam() {
        if (QueryOperator.ISN == queryOperator || QueryOperator.INN == queryOperator) {
            return null;
        } else {
            return param;
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
        String name = dialect.buildColumnSql(this.name, tableAlias);
        if (ignorePolicy.test(value)) { // 忽略
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
                return "=";
            case NE:
                return "!=";
            case SW:
                return dialect.getKeywords().like();
            case CO:
                return dialect.getKeywords().like();
            case EW:
                return dialect.getKeywords().like();
            case ISN:
                return dialect.getKeywords().isNull();
            case INN:
                return dialect.getKeywords().isNotNull();
            case IN:
                return dialect.getKeywords().in();
            case NIN:
                return dialect.getKeywords().notIn();
            case LK:
                return dialect.getKeywords().like();
            default:
                return "=";
        }
    }
}
