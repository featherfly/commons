
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
import cn.featherfly.common.db.dialect.OracleDialect;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * Oracle DML feature.
 *
 * @author zhongj
 */
public class OracleDMLFeature extends AbstractDMLFeature<OracleDialect> {

    /**
     * Mysql DML feature.
     *
     * @param dialect the dialect
     */
    public OracleDMLFeature(OracleDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, Object values,
            MatchStrategy matchStrategy) {
        //        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                //                caseSensitive = true;
                break;
            default:
                return betweenOrNotBetweenExpression(isBetween, name, values);
        }
        // FIXME 未实现，后续来实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max,
            MatchStrategy matchStrategy) {
        //        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                //                caseSensitive = true;
                break;
            default:
                return betweenOrNotBetweenExpression(isBetween, name, min, max);
        }
        // FIXME 未实现，后续来实现
        throw new UnsupportedException();
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
                // FIXME 未实现，后续来实现
                throw new UnsupportedException();
            case CASE_SENSITIVE:
                // FIXME 未实现，后续来实现
                throw new UnsupportedException();
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(dialect.getOperator(comparisonOperator)).append(Chars.SPACE)
                .append(Chars.QUESTION);
        return condition.toString();
    }

    @Override
    protected String compareExpression0(ComparisonOperator comparisonOperator, String name, SqlElement values,
            MatchStrategy matchStrategy) {
        StringBuilder condition = new StringBuilder();
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                // FIXME 未实现，后续来实现
                throw new UnsupportedException();
            case CASE_SENSITIVE:
                // FIXME 未实现，后续来实现
                throw new UnsupportedException();
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
    public String inOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

}
