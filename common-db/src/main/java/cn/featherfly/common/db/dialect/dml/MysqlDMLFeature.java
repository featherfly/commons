
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 02:57:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.dml;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.db.dialect.AbstractDMLFeature;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.MySQLDialect;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * Mysql DML feature
 *
 * @author zhongj
 */
public class MysqlDMLFeature extends AbstractDMLFeature<MySQLDialect> {

    /**
     * Instantiates a new mysql DML feature.
     *
     * @param dialect the dialect
     */
    public MysqlDMLFeature(MySQLDialect dialect) {
        super(dialect);
    }

    @Override
    public String deleteFrom(String tableName, String tableAlias) {
        if (Lang.isEmpty(tableAlias)) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DELETE), dialect.getKeyword(Keywords.FROM),
                    dialect.wrapName(tableName));
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DELETE), dialect.wrapName(tableAlias),
                    dialect.getKeyword(Keywords.FROM), dialect.wrapName(tableName), dialect.wrapName(tableAlias));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsertBatch(String tableName, String[] columnNames, String[] uniqueColumns, int insertAmount) {
        String sql = insertBatch(tableName, columnNames, insertAmount);
        sql = BuilderUtils.link(sql, "ON DUPLICATE KEY UPDATE");

        List<String> columns = ArrayUtils.toList(columnNames);
        for (String uc : uniqueColumns) {
            if (ArrayUtils.contain(columnNames, uc)) {
                columns.remove(uc);
            }
        }

        StringBuilder columnsSql = new StringBuilder();
        for (String columnName : columns) {
            BuilderUtils.link(columnsSql, Strings.format("{0}=values({0}),", dialect.wrapName(columnName)));
        }
        if (columnsSql.length() > 0) {
            columnsSql.deleteCharAt(columnsSql.length() - 1);
        }
        return BuilderUtils.link(sql, columnsSql.toString());
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
                        .append(Chars.SPACE).append(dialect.getCollateCaseInsensitive());
                break;
            case CASE_SENSITIVE:
                condition.append(dialect.getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
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
            condition.append(dialect.getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
        } else {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getCollateCaseInsensitive());
        }
        condition.append(Chars.SPACE).append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
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
            condition.append(dialect.getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
        } else {
            condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(dialect.getCollateCaseInsensitive());
        }
        condition.append(Chars.SPACE).append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
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
                        .append(Chars.SPACE).append(dialect.getCollateCaseInsensitive());
                break;
            case CASE_SENSITIVE:
                condition.append(dialect.getKeyword("BINARY")).append(Chars.SPACE).append(name);
                break;
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
                condition.append(name).append(Chars.SPACE).append(dialect.getKeyword(Keywords.COLLATE))
                        .append(Chars.SPACE).append(dialect.getCollateCaseInsensitive());
                break;
            case CASE_SENSITIVE:
                condition.append(dialect.getKeyword("BINARY")).append(Chars.SPACE).append(name);
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
