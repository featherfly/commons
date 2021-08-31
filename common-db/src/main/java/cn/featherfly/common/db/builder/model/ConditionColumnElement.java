
package cn.featherfly.common.db.builder.model;

import java.lang.reflect.Array;
import java.util.Collection;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.operate.QueryOperator;

/**
 * <p>
 * condition column element
 * </p>
 *
 * @author zhongj
 */
public class ConditionColumnElement extends ParamedColumnElement {

    private QueryOperator queryOperator;

    /**
     * @param dialect       dialect
     * @param name          name
     * @param value         param value
     * @param queryOperator queryOperator
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, QueryOperator queryOperator) {
        this(dialect, name, value, queryOperator, null);
    }

    /**
     * @param dialect       dialect
     * @param name          name
     * @param tableAlias    tableAlias
     * @param value         param value
     * @param queryOperator queryOperator
     */
    public ConditionColumnElement(Dialect dialect, String name, Object value, QueryOperator queryOperator,
            String tableAlias) {
        super(dialect, name, value, tableAlias);
        this.queryOperator = queryOperator;
        // TODO 后续加入异常检测
        // if (queryOperator == null) {
        // throw new BuilderException("#query.operator.null");
        // }

        if (Lang.isNotEmpty(value)) {
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
        // if (Lang.isNotEmpty(tableAlias)) {
        // condition.append(tableAlias).append(".");
        // }
        if (Lang.isNotEmpty(value)) {
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
        } else {
            return "";
        }
        return condition.toString();

        // return dialect.buildColumnSql(getName(), getTableAlias()) +
        // Chars.SPACE + operator.toString() + Chars.SPACE
        // + Chars.QUESTION;
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
