package cn.featherfly.common.db.dialect;

import java.lang.reflect.Array;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * MySQL Dialect.
 *
 * @author zhongj
 */
public class MySQLDialect extends AbstractDialect {

    /** The Constant DEFAULT_COLLATE_CASE_INSENSITIVE. */
    public static final String DEFAULT_COLLATE_CASE_INSENSITIVE = "UTF8_GENERAL_CI";

    private String collateCaseInsensitive = DEFAULT_COLLATE_CASE_INSENSITIVE;

    /**
     * Instantiates a new my SQL
     */
    public MySQLDialect() {
        super();
    }

    /**
     * get collateCaseInsensitive value.
     *
     * @return collateCaseInsensitive
     */
    public String getCollateCaseInsensitive() {
        return collateCaseInsensitive;
    }

    /**
     * set collateCaseInsensitive value.
     *
     * @param collateCaseInsensitive collateCaseInsensitive
     */
    public void setCollateCaseInsensitive(String collateCaseInsensitive) {
        this.collateCaseInsensitive = collateCaseInsensitive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPaginationSql(String sql, int start, int limit) {
        return getPaginationSql(sql, start, false, PARAM_NAME_START_SYMBOL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParamNamedPaginationSql(String sql, int start, int limit) {
        return getParamNamedPaginationSql(sql, start, limit, PARAM_NAME_START_SYMBOL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParamNamedPaginationSql(String sql, int start, int limit, char startSymbol) {
        return getPaginationSql(sql, start, true, startSymbol);
    }

    private String getPaginationSql(String sql, int start, boolean isParamNamed, char startSymbol) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (isForUpdate(sql)) {
            sql = sql.substring(0, sql.length() - UPDATE_STRING.length());
            isForUpdate = true;
        }
        final int sqlLengthOffset = 50;
        StringBuilder pagingSelect = new StringBuilder(sql.length() + sqlLengthOffset);
        pagingSelect.append(sql);
        if (isParamNamed) {
            if (start > 0) {
                pagingSelect
                    .append(Strings.format(" LIMIT {0}{1},{0}{2}", startSymbol, START_PARAM_NAME, LIMIT_PARAM_NAME));
            } else {
                pagingSelect.append(Strings.format(" LIMIT {0}{1}", startSymbol, LIMIT_PARAM_NAME));
            }
        } else {
            if (start > 0) {
                pagingSelect.append(" LIMIT ?,?");
            } else {
                pagingSelect.append(" LIMIT ?");
            }
        }
        if (isForUpdate) {
            pagingSelect.append(UPDATE_STRING);
        }
        logger.debug("原始Sql：{}", sql);
        logger.debug("分页Sql：{}", pagingSelect);
        return pagingSelect.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertValueToSql(Object value, int sqlType) {
        StringBuilder sqlPart = new StringBuilder();
        if (value == null) {
            sqlPart.append("null");
        } else {
            switch (sqlType) {
                case Types.BIGINT:
                case Types.BIT:
                case Types.BOOLEAN:
                case Types.DECIMAL:
                case Types.DOUBLE:
                case Types.FLOAT:
                case Types.INTEGER:
                case Types.NULL:
                case Types.NUMERIC:
                case Types.TINYINT:
                    sqlPart.append(value);
                    break;
                case Types.DATE:
                case Types.TIME:
                case Types.TIMESTAMP:
                    if (value instanceof Date) {
                        sqlPart.append("'").append(Dates.formatTime((Date) value)).append("'");
                    } else {
                        sqlPart.append("'").append(value).append("'");
                        break;
                    }
                    break;
                default:
                    sqlPart.append("'").append(SqlUtils.transferStringForSql(value.toString())).append("'");
                    break;
            }
        }
        return sqlPart.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWrapSymbol() {
        return "`";
    }

    public String getFkCheck(boolean check) {
        return "SET FOREIGN_KEY_CHECKS=" + (check ? 1 : 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getAutoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "AUTO_INCREMENT";
        } else {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnTypeName(SQLType sqlType) {
        JDBCType type = JDBCType.valueOf(sqlType.getVendorTypeNumber());
        switch (type) {
            case INTEGER:
                return "INT";
            default:
                return super.getColumnTypeName(sqlType);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlHeader() {
        return getFkCheck(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlFooter() {
        return getFkCheck(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultSchema(String catalog) {
        return catalog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildDeleteFromSql(String tableName, String tableAlias) {
        String alias = Lang.isEmpty(tableAlias) ? null : wrapName(tableAlias);
        return BuilderUtils.link(getKeyword(Keywords.DELETE), alias, getKeyword(Keywords.FROM), wrapName(tableName),
            alias);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildDropIndexDDL(String schema, String tableName, String indexName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        AssertIllegalArgument.isNotEmpty(indexName, "indexName");
        String in = Lang.isEmpty(schema) ? wrapName(indexName) : wrapName(schema) + Chars.DOT + wrapName(indexName);
        String tn = Lang.isEmpty(schema) ? wrapName(tableName) : wrapName(schema) + Chars.DOT + wrapName(tableName);
        if (ifExists) {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.INDEX), getKeyword(Keywords.IF),
                getKeyword(Keywords.EXISTS), in, getKeyword(Keywords.ON), tn) + Chars.SEMI;
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.INDEX), in, getKeyword(Keywords.ON),
                tn) + Chars.SEMI;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildUpsertBatchSql(String tableName, String[] columnNames, String[] uniqueColumns,
        int insertAmount) {
        String sql = buildInsertBatchSql(tableName, columnNames, insertAmount);
        sql = BuilderUtils.link(sql, "ON DUPLICATE KEY UPDATE");

        List<String> columns = ArrayUtils.toList(columnNames);
        for (String uc : uniqueColumns) {
            if (ArrayUtils.contain(columnNames, uc)) {
                columns.remove(uc);
            }
        }

        StringBuilder columnsSql = new StringBuilder();
        for (String columnName : columns) {
            BuilderUtils.link(columnsSql, Strings.format("{0}=values({0}),", wrapName(columnName)));
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
    protected String getKeywordLikeCaseInsensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.COLLATE) + " " + collateCaseInsensitive + " " + getKeyword(Keywords.NOT) + " "
                + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.COLLATE) + " " + collateCaseInsensitive + " " + getKeyword(Keywords.LIKE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKeywordLikeCaseSensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword(Keywords.LIKE) + Chars.SPACE
                + getKeyword(Keywords.BINARY);

        } else {
            return getKeyword(Keywords.LIKE) + Chars.SPACE + getKeyword(Keywords.BINARY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy) {
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
                condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(collateCaseInsensitive);
                break;
            case CASE_SENSITIVE:
                condition.append(getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(isIn ? getKeywords().in() : getKeywords().notIn()).append(" (");
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
    public String getBetweenOrNotBetweenExpression(boolean isBetween, String name, Object values,
        MatchStrategy matchStrategy) {
        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                caseSensitive = true;
                break;
            default:
                return getBetweenOrNotBetweenExpression(isBetween, name, values);
        }
        StringBuilder condition = new StringBuilder();
        if (caseSensitive) {
            condition.append(getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
        } else {
            condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                .append(collateCaseInsensitive);
        }
        condition.append(Chars.SPACE).append(!isBetween ? getKeyword(Keywords.NOT) + Chars.SPACE : "") //
            .append(getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
            .append(Chars.QUESTION).append(Chars.SPACE) //
            .append(getKeyword(Keywords.AND)).append(Chars.SPACE) //
            .append(Chars.QUESTION);
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBetweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max,
        MatchStrategy matchStrategy) {
        boolean caseSensitive = false;
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                break;
            case CASE_SENSITIVE:
                caseSensitive = true;
                break;
            default:
                return getBetweenOrNotBetweenExpression(isBetween, name, min, max);
        }
        StringBuilder condition = new StringBuilder();
        if (caseSensitive) {
            condition.append(getKeyword(Keywords.BINARY)).append(Chars.SPACE).append(name);
        } else {
            condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                .append(collateCaseInsensitive);
        }
        condition.append(Chars.SPACE).append(!isBetween ? getKeyword(Keywords.NOT) + Chars.SPACE : "") //
            .append(getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
            .append(min.toSql()).append(Chars.SPACE) //
            .append(getKeyword(Keywords.AND)).append(Chars.SPACE) //
            .append(max.toSql());
        return condition.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getCompareExpression0(ComparisonOperator comparisonOperator, String name, Object values,
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
                condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(collateCaseInsensitive);
                break;
            case CASE_SENSITIVE:
                condition.append(getKeyword("BINARY")).append(Chars.SPACE).append(name);
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(getOperator(comparisonOperator)).append(Chars.SPACE)
            .append(Chars.QUESTION);
        return condition.toString();
    }

    @Override
    protected String getCompareExpression0(ComparisonOperator comparisonOperator, String name, SqlElement values,
        MatchStrategy matchStrategy) {
        StringBuilder condition = new StringBuilder();
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(collateCaseInsensitive);
                break;
            case CASE_SENSITIVE:
                condition.append(getKeyword("BINARY")).append(Chars.SPACE).append(name);
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(getOperator(comparisonOperator)).append(Chars.SPACE)
            .append(values.toSql());
        return condition.toString();
    }

    @Override
    public boolean supportBatchUpdates(DatabaseMetaData metaData) throws SQLException {
        return metaData.supportsBatchUpdates() && metaData.getURL().contains("rewriteBatchedStatements=true");
    }
}