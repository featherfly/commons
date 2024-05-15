
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 02:41:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * abstract DML feature.
 *
 * @author zhongj
 */
public abstract class AbstractDMLFeature<D extends Dialect> implements DMLFeature {

    /** The dialect. */
    protected final D dialect;

    /**
     * Instantiates a new abstract DML feature.
     *
     * @param dialect the dialect
     */
    protected AbstractDMLFeature(D dialect) {
        super();
        this.dialect = dialect;
    }

    @Override
    public String deleteFrom(String tableName, String tableAlias) {
        return BuilderUtils.link(dialect.getKeyword(Keywords.DELETE), dialect.getKeyword(Keywords.FROM),
                dialect.wrapName(tableName), Lang.isEmpty(tableAlias) ? null : dialect.wrapName(tableAlias));
    }

    @Override
    public String insert(String tableName, String[] columnNames) {
        String sql = BuilderUtils.link(dialect.getKeyword(Keywords.INSERT), dialect.getKeyword(Keywords.INTO),
                dialect.wrapName(tableName), Chars.PAREN_L);
        StringBuilder names = new StringBuilder();
        for (String column : columnNames) {
            BuilderUtils.link(names, dialect.wrapName(column) + Chars.COMMA);
        }
        names.deleteCharAt(names.length() - 1).append(Chars.PAREN_R);
        sql += names.toString();
        sql = BuilderUtils.link(sql, dialect.getKeyword(Keywords.VALUES), Chars.PAREN_L);
        StringBuilder ques = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
        }
        ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
        sql += ques.toString();
        return sql;
    }

    @Override
    public String insertBatch(String tableName, String[] columnNames, int insertAmount) {
        String sql = BuilderUtils.link(dialect.getKeyword(Keywords.INSERT), dialect.getKeyword(Keywords.INTO),
                dialect.wrapName(tableName), Chars.PAREN_L);
        StringBuilder names = new StringBuilder();
        for (String column : columnNames) {
            BuilderUtils.link(names, dialect.wrapName(column) + Chars.COMMA);
        }
        names.deleteCharAt(names.length() - 1).append(Chars.PAREN_R);
        sql += names.toString();
        sql = BuilderUtils.link(sql, dialect.getKeyword(Keywords.VALUES), Chars.PAREN_L);
        StringBuilder ques = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
        }
        ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
        sql += ques.toString();
        for (int index = 1; index < insertAmount; index++) {
            ques = new StringBuilder();
            for (int j = 0; j < columnNames.length; j++) {
                BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
            }
            ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
            sql += Chars.COMMA + Chars.PAREN_L + ques.toString();
        }
        return sql;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsertBatch(String tableName, String[] columnNames, String[] uniqueColumns, int insertAmount) {
        String sql = insertBatch(tableName, columnNames, insertAmount);
        StringBuilder conflict = new StringBuilder();
        List<String> columns = ArrayUtils.toList(columnNames);
        for (String uc : uniqueColumns) {
            conflict.append(uc).append(",");
            if (ArrayUtils.contain(columnNames, uc)) {
                columns.remove(uc);
            }
        }
        if (conflict.length() > 0) {
            conflict.deleteCharAt(conflict.length() - 1);
            conflict.insert(0, "(");
            conflict.append(")");
        }
        sql = BuilderUtils.link(sql, "ON CONFLICT", conflict.toString(), "DO UPDATE SET");
        StringBuilder columnsSql = new StringBuilder();
        for (String columnName : columns) {
            BuilderUtils.link(columnsSql, Strings.format("{0}=EXCLUDED.{0},", dialect.wrapName(columnName)));
        }
        if (columnsSql.length() > 0) {
            columnsSql.deleteCharAt(columnsSql.length() - 1);
        }
        return BuilderUtils.link(sql, columnsSql.toString());
    }

    @Override
    public String column(AggregateFunction aggregateFunction, boolean distinct, String tableAlias, String columnName,
            String columnAlias) {
        String column = columnName;
        if (!Chars.STAR.equals(columnName)) {
            column = dialect.wrapName(dialect.convertTableOrColumnName(columnName));
        }
        if (Lang.isNotEmpty(tableAlias) && !Chars.STAR.equals(columnName)) {
            column = tableAlias + Chars.DOT + column;
        }
        if (distinct) {
            column = dialect.getKeywords().distinct() + Chars.SPACE + column;
        }
        if (aggregateFunction != null) {
            column = dialect.getFunction(aggregateFunction) + Chars.PAREN_L + column + Chars.PAREN_R;
        }
        if (Lang.isNotEmpty(columnAlias)) {
            column = column + Chars.SPACE + dialect.wrapName(columnAlias);
        }
        return column;
    }

    @Override
    public String table(String tableName, String tableAlias) {
        String result = dialect.wrapName(dialect.convertTableOrColumnName(tableName));
        if (Lang.isNotEmpty(tableAlias)) {
            result = result + " " + dialect.wrapName(tableAlias);
        }
        return result;
    }

    @Override
    public String compareExpression(ComparisonOperator operator, String columnName, Object values, String tableAlias) {
        return compareExpression(operator, column(tableAlias, columnName), values);
    }

    @Override
    public String compareExpression(ComparisonOperator comparisonOperator, String name, Object values,
            MatchStrategy matchStrategy) {
        if (ComparisonOperator.IN == comparisonOperator || ComparisonOperator.NI == comparisonOperator) {
            return inOrNotInExpression(comparisonOperator == ComparisonOperator.IN, name, values, matchStrategy);
        } else if (ComparisonOperator.BA == comparisonOperator || ComparisonOperator.NBA == comparisonOperator) {
            return betweenOrNotBetweenExpression(comparisonOperator == ComparisonOperator.BA, name, values,
                    matchStrategy);
        } else {
            if (ComparisonOperator.ISN == comparisonOperator) {
                return isNullOrNotIsNullExpression(values == null || (Boolean) values, name);
            } else if (ComparisonOperator.INN == comparisonOperator) {
                return isNullOrNotIsNullExpression(values != null && !(Boolean) values, name);
            } else {
                return compareExpression0(comparisonOperator, name, values, matchStrategy);
            }
        }
    }

    @Override
    public String compareExpression(ComparisonOperator comparisonOperator, String name, SqlElement values,
            MatchStrategy matchStrategy) {
        switch (comparisonOperator) {
            case ISN:
            case INN:
                throw new DialectException(
                        Strings.format("unspport for {} with {} ", values.getClass().getName(), comparisonOperator));
            default:
                break;
        }
        // FIXME 后续来处理between, not between
        return compareExpression0(comparisonOperator, name, values, matchStrategy);
    }

    @Override
    public String compareExpression(ComparisonOperator operator, String columnName, Object values, String tableAlias,
            MatchStrategy matchStrategy) {
        return compareExpression(operator, column(tableAlias, columnName), values, matchStrategy);
    }

    protected abstract String compareExpression0(ComparisonOperator comparisonOperator, String columnName,
            Object values, MatchStrategy matchStrategy);

    protected abstract String compareExpression0(ComparisonOperator comparisonOperator, String columnName,
            SqlElement values, MatchStrategy matchStrategy);

    @Override
    public String isNullOrNotIsNullExpression(boolean isNull, String name) {
        StringBuilder condition = new StringBuilder();
        condition.append(name).append(Chars.SPACE);
        if (isNull) {
            condition.append(dialect.getOperator(ComparisonOperator.ISN));
        } else {
            condition.append(dialect.getOperator(ComparisonOperator.INN));
        }
        return condition.toString();
    }

    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, Object value) {
        StringBuilder condition = new StringBuilder();
        condition.append(name).append(Chars.SPACE) //
                .append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
                .append(dialect.getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
                .append(Chars.QUESTION).append(Chars.SPACE) //
                .append(dialect.getKeyword(Keywords.AND)).append(Chars.SPACE) //
                .append(Chars.QUESTION);
        return condition.toString();
    }

    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max) {
        StringBuilder condition = new StringBuilder();
        condition.append(name).append(Chars.SPACE) //
                .append(!isBetween ? dialect.getKeyword(Keywords.NOT) + Chars.SPACE : "") //
                .append(dialect.getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
                .append(min.toSql()).append(Chars.SPACE) //
                .append(dialect.getKeyword(Keywords.AND)).append(Chars.SPACE) //
                .append(max.toSql());
        return condition.toString();
    }

    @Override
    public String inOrNotInExpression(boolean isIn, String name, Object values) {
        StringBuilder condition = new StringBuilder();
        int length = 1;
        if (values != null) {
            if (values instanceof Collection) {
                length = ((Collection<?>) values).size();
            } else if (values.getClass().isArray()) {
                length = Array.getLength(values);
            }
        }
        condition.append(name).append(Chars.SPACE)
                .append(isIn ? dialect.getKeywords().in() : dialect.getKeywords().notIn()).append(" (");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                condition.append(Chars.COMMA);
            }
            condition.append(Chars.QUESTION);
        }
        condition.append(")");
        return condition.toString();
    }
}
