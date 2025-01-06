
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 03:20:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.dml;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.db.dialect.AbstractDMLFeature;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.PostgreSQLDialect;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.DateFunction;

/**
 * PostgreSQLDMLFeature.
 *
 * @author zhongj
 */
public class PostgreSQLDMLFeature extends AbstractDMLFeature<PostgreSQLDialect> {

    /**
     * Mysql DML feature.
     *
     * @param dialect the dialect
     */
    public PostgreSQLDMLFeature(PostgreSQLDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String inOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy) {
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
            case CASE_SENSITIVE:
                throw new DialectException("in operator unsupported " + matchStrategy);
            default:
                return inOrNotInExpression(isIn, name, values);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, Object values,
        MatchStrategy matchStrategy) {
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
            case CASE_SENSITIVE:
                throw new DialectException("between and operator unsupported " + matchStrategy);
            default:
                return betweenOrNotBetweenExpression(isBetween, name, values);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max,
        MatchStrategy matchStrategy) {
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
            case CASE_SENSITIVE:
                throw new DialectException("between and operator unsupported " + matchStrategy);
            default:
                return betweenOrNotBetweenExpression(isBetween, name, min, max);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String compareExpression0(ComparisonOperator comparisonOperator, String name, Object values,
        MatchStrategy matchStrategy) {
        switch (comparisonOperator) {
            case EQ:
            case NE:
            case SW:
            case NSW:
            case CO:
            case NCO:
            case EW:
            case NEW:
            case LK:
            case NL:
            case LT:
            case LE:
            case GT:
            case GE:
                break;
            default:
                throw new DialectException("unsupported for " + comparisonOperator);
        }

        StringBuilder condition = new StringBuilder();
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
            case CASE_SENSITIVE:
                throw new DialectException(
                    Str.format("{} operator unsupported {}", comparisonOperator, matchStrategy));
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(dialect.getOperator(comparisonOperator)).append(Chars.SPACE)
            .append(Chars.QUESTION);
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String compareExpression0(ComparisonOperator comparisonOperator, String name, SqlElement values,
        MatchStrategy matchStrategy) {
        StringBuilder condition = new StringBuilder();
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
            case CASE_SENSITIVE:
                throw new DialectException(
                    Str.format("{} operator unsupported {}", comparisonOperator, matchStrategy));
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(dialect.getOperator(comparisonOperator)).append(Chars.SPACE)
            .append(values.toSql());
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String preparePrimaryKeyColumnForInsert(String tableName, String columnName, boolean autoIncrement) {
        if (autoIncrement) {
            return "DEFAULT";
        } else {
            return Chars.QUESTION;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String column(boolean distinct, String tableAlias, String columnName,
        String columnAlias, DateFunction function, Object... argus) {
        // NOIMPL 未实现
        throw new DialectException("未实现的 function" + function.getClass().getName());
    }

}
