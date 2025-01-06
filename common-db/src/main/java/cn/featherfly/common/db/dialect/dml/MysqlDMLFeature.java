
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
import cn.featherfly.common.db.dialect.Dialect.StringCase;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.MySQLDialect;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.DateFunction;

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
    public String upsertBatch(String tableName, String pkColumnName, String[] columnNames, String[] uniqueColumns,
        int insertAmount, boolean autoIncrement) {
        String sql = insertBatch(tableName, pkColumnName, columnNames, insertAmount, autoIncrement);
        sql = BuilderUtils.link(sql, "ON DUPLICATE KEY UPDATE");

        List<String> columns = ArrayUtils.toList(columnNames);
        for (String uc : uniqueColumns) {
            if (ArrayUtils.contain(columnNames, uc)) {
                columns.remove(uc);
            }
        }

        StringBuilder columnsSql = new StringBuilder();
        for (String columnName : columns) {
            BuilderUtils.link(columnsSql, Str.format("{0}=values({0}),", dialect.wrapName(columnName)));
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected String preparePrimaryKeyColumnForInsert(String tableName, String columnName, boolean autoIncrement) {
        return Chars.QUESTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String column(boolean distinct, String tableAlias, String columnName,
        String columnAlias, DateFunction function, Object... argus) {
        String name = null;
        switch (function) {
            case GET_YEAR:
                name = "year";
                break;
            case GET_MONTH:
                name = "month";
                break;
            case GET_DAY_OF_MONTH:
                name = "dayofmonth";
                break;
            case GET_HOUR:
                name = "hour";
                break;
            case GET_MINUTE:
                name = "minute";
                break;
            case GET_SECOND:
                name = "second";
                break;
            case GET_WEEKDAY:
                name = "weekday";
                break;
            case GET_WEEK_OF_YEAR:
                name = "weekofyear";
                break;
            case GET_DAY_OF_YEAR:
                name = "dayofyear";
                break;
            case GET_QUARTER:
                name = "quarter";
                break;
            case DATE_FORMAT:
                name = "date_format";
                break;
            case TIME_FORMAT:
                name = "time_format";
                break;
            default:
                throw new DialectException("not support date function " + function.name());
        }
        if (dialect.tableAndColumnNameCase() == StringCase.UPPER_CASE) {
            name = name.toUpperCase();
        }

        String column = dialect.wrapName(dialect.convertTableOrColumnName(columnName));
        if (Lang.isNotEmpty(tableAlias)) {
            column = tableAlias + Chars.DOT + column;
        }
        // 拼接方法
        column = name + Chars.PAREN_L + column;
        if (function.getParameterCount() > 1) {
            for (Object argu : argus) {
                column = column + Chars.COMMA + Chars.SPACE + Chars.QM + argu + Chars.QM;
            }
        }
        column += Chars.PAREN_R;
        if (distinct) {
            column = dialect.getKeywords().distinct() + Chars.SPACE + column;
        }
        if (Lang.isNotEmpty(columnAlias)) {
            column = column + Chars.SPACE + dialect.wrapName(columnAlias);
        }
        return column;
    }

}
