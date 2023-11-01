
package cn.featherfly.common.db.dialect;

import java.lang.reflect.Array;
import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;

/**
 * SQLite Dialect .
 *
 * @author zhongj
 */
public class SQLiteDialect extends AbstractDialect {

    /** The Constant TEXT_TYPE. */
    public static final String TEXT_TYPE = "TEXT";

    /**
     * Instantiates a new SQ lite dialect.
     */
    public SQLiteDialect() {
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
                pagingSelect.append(Strings.format(" LIMIT {0}{1},{0}{2}",
                        Lang.array(startSymbol, START_PARAM_NAME, LIMIT_PARAM_NAME)));
            } else {
                pagingSelect.append(Strings.format(" LIMIT {0}{1}", Lang.array(startSymbol, LIMIT_PARAM_NAME)));
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTableColumnsDDL(Table table) {
        StringBuilder ddl = new StringBuilder();
        int pkNo = 0;
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                pkNo++;
            }
        }
        boolean mulityPk = pkNo > 1;
        int i = 0;
        int size = table.getColumns().size();
        for (Column column : table.getColumns()) {
            i++;
            if (mulityPk) {
                BuilderUtils.link(ddl, getColumnDDL(column));
                ddl.append(Chars.COMMA);
            } else {
                BuilderUtils.link(ddl, wrapName(column.getName()), getColumnTypeDDL(column), getColumnNotNull(column),
                        getDefaultValue(column), getAutoIncrement(column));
                if (i < size) {
                    ddl.append(Chars.COMMA);
                }
            }
            BuilderUtils.link(ddl, getColumnComment(column));
            ddl.append(Chars.NEW_LINE);
        }
        if (mulityPk) {
            BuilderUtils.link(ddl, getPrimaryKeyDDL(table));
        }
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTableComment(Table table) {
        return Lang.isEmpty(table.getRemark()) ? "" : BuilderUtils.link("--", table.getRemark());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getColumnComment(Column column) {
        return Lang.isEmpty(column.getRemark()) ? "" : BuilderUtils.link("--", column.getRemark());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getColumnDDL(Column column) {
        return BuilderUtils.link(wrapName(column.getName()), getColumnTypeDDL(column), getColumnNotNull(column),
                getDefaultValue(column), getAutoIncrement(column));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getAutoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "PRIMARY KEY AUTOINCREMENT";
        } else {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getColumnTypeDDL(Column column, String extra) {
        int size = column.getSize();
        int decimalDigits = column.getDecimalDigits();
        String result = getColumnTypeName(column.getSqlType());
        if (size > 0 && (result.equals(JDBCType.REAL.getName()) || result.equals(TEXT_TYPE))) {
            result += Chars.PAREN_L + size;
            if (decimalDigits > 0) {
                result += Chars.COMMA + decimalDigits;
            }
            result += Chars.PAREN_R;
        }
        if (Lang.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnTypeName(SQLType sqlType) {
        JDBCType type = JDBCType.valueOf(sqlType.getVendorTypeNumber());
        switch (type) {
            case BOOLEAN:
            case TINYINT:
            case SMALLINT:
            case INTEGER:
            case BIGINT:
                return JDBCType.INTEGER.getName();
            case CHAR:
            case VARCHAR:
            case NCHAR:
            case NVARCHAR:
            case DATE:
            case TIME:
            case TIMESTAMP:
                return TEXT_TYPE;
            case BLOB:
                return JDBCType.BLOB.getName();
            case FLOAT:
            case DOUBLE:
            case NUMERIC:
            case DECIMAL:
            case REAL:
                return JDBCType.REAL.getName();
            default:
                throw new JdbcException(getClass().getSimpleName() + " not support type " + type.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildInsertBatchSql(String tableName, String[] columnNames, int insertAmount) {
        if (insertAmount == 1) {
            return buildInsertSql(tableName, columnNames);
        }
        StringBuilder sql = new StringBuilder();
        BuilderUtils.link(sql, getKeyword(Keywords.INSERT), getKeyword(Keywords.INTO), wrapName(tableName),
                getKeyword(Keywords.SELECT));

        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(sql, Chars.QUESTION, getKeyword(Keywords.AS), wrapName(columnNames[i]) + Chars.COMMA);
        }
        sql.deleteCharAt(sql.length() - 1);
        for (int index = 1; index < insertAmount; index++) {
            BuilderUtils.link(sql, getKeyword(Keywords.UNION), getKeyword(Keywords.SELECT));
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
    public boolean supportAutoGenerateKeyBatch() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableAddColumnDDL(String schema, String tableName, Column... columns) {
        // TODO 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDropColumnDDL(String schema, String tableName, String... columnNames) {
        // TODO 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDropColumnDDL(String schema, String tableName, Column... columns) {
        // TODO 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableModifyColumnDDL(String schema, String tableName, Column... columns) {
        // TODO 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlHeader() {
        // FIXME 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlFooter() {
        // FIXME 未实现
        throw new UnsupportedException();
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
                        .append(getKeyword("NOCASE"));
                break;
            case CASE_SENSITIVE:
                condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                        .append(getKeywords().binary());
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
            condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(getKeywords().binary());
        } else {
            condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                    .append(getKeyword("NOCASE"));
        }
        condition.append(!isBetween ? getKeyword(Keywords.NOT) + Chars.SPACE : "") //
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
                        .append(getKeyword("NOCASE"));
                break;
            case CASE_SENSITIVE:
                condition.append(name).append(Chars.SPACE).append(getKeyword(Keywords.COLLATE)).append(Chars.SPACE)
                        .append(getKeywords().binary());
                break;
            default:
                condition.append(name);
                break;
        }
        condition.append(Chars.SPACE).append(getOperator(comparisonOperator)).append(Chars.SPACE)
                .append(Chars.QUESTION);
        return condition.toString();
    }
}
