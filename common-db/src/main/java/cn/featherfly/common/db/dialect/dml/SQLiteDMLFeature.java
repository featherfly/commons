
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 03:38:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.dml;

import java.lang.reflect.Array;
import java.util.Collection;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.db.dialect.AbstractDMLFeature;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.SQLiteDialect;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * SQLiteDMLFeature.
 *
 * @author zhongj
 */
public class SQLiteDMLFeature extends AbstractDMLFeature<SQLiteDialect> {

    /**
     * Instantiates a new SQ lite DML feature.
     *
     * @param dialect the dialect
     */
    public SQLiteDMLFeature(SQLiteDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String insertBatch(String tableName, String[] columnNames, int insertAmount) {
        if (insertAmount == 1) {
            return insert(tableName, columnNames);
        }
        StringBuilder sql = new StringBuilder();
        BuilderUtils.link(sql, dialect.getKeyword(Keywords.INSERT), dialect.getKeyword(Keywords.INTO),
                dialect.wrapName(tableName), dialect.getKeyword(Keywords.SELECT));

        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(sql, Chars.QUESTION, dialect.getKeyword(Keywords.AS),
                    dialect.wrapName(columnNames[i]) + Chars.COMMA);
        }
        sql.deleteCharAt(sql.length() - 1);
        for (int index = 1; index < insertAmount; index++) {
            BuilderUtils.link(sql, dialect.getKeyword(Keywords.UNION), dialect.getKeyword(Keywords.SELECT));
            for (int j = 0; j < columnNames.length; j++) {
                BuilderUtils.link(sql, Chars.QUESTION + Chars.COMMA);
            }
            sql.deleteCharAt(sql.length() - 1);
        }
        return sql.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String inOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy) {
        StringBuilder condition = new StringBuilder();
        int length = 1;
        if (values != null) {
            if (values instanceof Collection) {
                length = ((Collection<?>) values).size();
            } else if (values.getClass().isArray()) {
                length = Array.getLength(values);
            }
        }
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeyword("NOCASE"));
                break;
            case CASE_SENSITIVE:
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeywords().binary());
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(isIn ? dialect.getKeywords().in() : dialect.getKeywords().notIn())
                .append(" (");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                condition.append(Chars.COMMA);
            }
            condition.append(Chars.QUESTION);
        }
        condition.append(")");
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, Object values,
            MatchStrategy matchStrategy) {
        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                caseSensitive = true;
                break;
            default:
                return betweenOrNotBetweenExpression(isBetween, name, values);
        }
        StringBuilder condition = new StringBuilder();
        if (caseSensitive) {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getKeywords().binary());
        } else {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getKeyword("NOCASE"));
        }
        condition.append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
                .append(dialect.getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
                .append(Chars.QUESTION).append(Chars.SPACE) //
                .append(dialect.getKeyword(Keywords.AND)).append(Chars.SPACE) //
                .append(Chars.QUESTION);
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max,
            MatchStrategy matchStrategy) {
        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                caseSensitive = true;
                break;
            default:
                return betweenOrNotBetweenExpression(isBetween, name, min, max);
        }
        StringBuilder condition = new StringBuilder();
        if (caseSensitive) {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getKeywords().binary());
        } else {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getKeyword("NOCASE"));
        }
        condition.append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
                .append(dialect.getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
                .append(min.toSql()).append(Chars.SPACE) //
                .append(dialect.getKeyword(Keywords.AND)).append(Chars.SPACE) //
                .append(max.toSql());
        return condition.toString();
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
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeyword("NOCASE"));
                break;
            case CASE_SENSITIVE:
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeywords().binary());
                break;
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
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeyword("NOCASE"));
                break;
            case CASE_SENSITIVE:
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getKeywords().binary());
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(dialect.getOperator(comparisonOperator)).append(Chars.SPACE)
                .append(values.toSql());
        return condition.toString();
    }
}
