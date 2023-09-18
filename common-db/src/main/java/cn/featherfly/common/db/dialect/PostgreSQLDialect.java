package cn.featherfly.common.db.dialect;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * PostgreSQL Dialect.
 *
 * @author zhongj
 */
public class PostgreSQLDialect extends AbstractDialect {

    /**
     * Instantiates a new postgre SQL dialect.
     */
    public PostgreSQLDialect() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getPaginationSqlParameter(Object[] params, int start, int limit) {
        Object[] pagingParams = null;
        if (limit > Chars.ZERO) {
            logger.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            logger.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            logger.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            pagingParams = new Object[] { Integer.valueOf(limit), Integer.valueOf(start) };
        } else {
            logger.debug("start < 0 , don't use start");
            pagingParams = new Object[] { Integer.valueOf(limit) };
        }
        return (Object[]) ArrayUtils.concat(params, pagingParams);
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
                pagingSelect.append(Strings.format(" LIMIT {0}{1} OFFSET {0}{2}",
                        Lang.array(startSymbol, LIMIT_PARAM_NAME, START_PARAM_NAME)));
            } else {
                pagingSelect.append(Strings.format(" LIMIT {0}{1}", Lang.array(startSymbol, LIMIT_PARAM_NAME)));
            }
        } else {
            if (start > 0) {
                pagingSelect.append(" LIMIT ? OFFSET ?");
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
        return "\"";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildCreateTableDDL(Table table) {
        final StringBuilder comments = new StringBuilder();
        for (Column column : table.getColumns()) {
            if (Lang.isNotEmpty(column.getRemark())) {
                StringBuilder comment = new StringBuilder();
                String columnName = wrapName(table.getName()) + Chars.DOT + wrapName(column.getName());
                if (Lang.isNotEmpty(table.getSchema())) {
                    columnName = wrapName(table.getSchema()) + Chars.DOT + columnName;
                }
                BuilderUtils.link(comment, getKeyword(Keywords.COMMENT), getKeyword(Keywords.ON),
                        getKeyword(Keywords.COLUMN), columnName, getKeyword(Keywords.IS),
                        Chars.QM + column.getRemark() + Chars.QM);
                comment.append(Chars.SEMI).append(Chars.NEW_LINE);
                comments.append(comment);
            }
        }
        String result = super.buildCreateTableDDL(table);
        if (comments.length() > 0) {
            comments.deleteCharAt(comments.length() - 1);
            result += Chars.SEMI + Chars.NEW_LINE + comments.toString();
        }
        return result;
    }

    /**
     * Gets the table comment.
     *
     * @param table the table
     * @return the table comment
     */
    @Override
    protected String getTableComment(Table table) {
        //        COMMENT ON TABLE "p"."user4" IS 'user用户表';
        return Lang.isEmpty(table.getRemark()) ? ""
                : BuilderUtils.link(Chars.SEMI + Chars.NEW_LINE + getKeyword(Keywords.COMMENT), getKeyword(Keywords.ON),
                        getKeyword(Keywords.TABLE),
                        Lang.ifEmpty(table.getSchema(), () -> wrapName(table.getName()),
                                () -> wrapName(table.getSchema()) + Chars.DOT + wrapName(table.getName())),
                        getKeyword(Keywords.IS), Chars.QM + table.getRemark() + Chars.QM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPrimaryKeyDDL(Table table) {
        StringBuilder result = new StringBuilder(Chars.PAREN_L);
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                result.append(wrapName(column.getName())).append(Chars.DOT);
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append(Chars.PAREN_R);
        return BuilderUtils.link(getKeyword(Keywords.CONSTRAINT), wrapName(table.getName() + "_" + "pkey"),
                getKeyword(Keywords.PRIMARY), getKeyword(Keywords.KEY), result.toString());
    }

    /**
     * Gets the column DDL.
     *
     * @param column the column
     * @return the column DDL
     */
    @Override
    protected String getColumnDDL(Column column) {
        // FIXME 注释要用单独语句，不能放在列语句中
        if (column.isAutoincrement()) {
            return BuilderUtils.link(wrapName(column.getName()), getSerial(column.getSqlType()),
                    getColumnNotNull(column));
        } else {
            return BuilderUtils.link(wrapName(column.getName()), getColumnTypeDDL(column), getColumnNotNull(column),
                    getDefaultValue(column));
        }
    }

    private String getSerial(SQLType sqlType) {
        JDBCType type = JDBCType.valueOf(sqlType.getVendorTypeNumber());
        switch (type) {
            case SMALLINT:
                return "SERIAL2";
            case INTEGER:
                return "SERIAL4";
            case BIGINT:
                return "SERIAL8";
            default:
                throw new DialectException(
                        "serial only support for JDBCType.BIGINT, JDBCType.INTEGER, JDBCType.SMALLINT");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnTypeName(SQLType sqlType) {
        JDBCType type = JDBCType.valueOf(sqlType.getVendorTypeNumber());
        switch (type) {
            case TINYINT:
                return "INT2"; // postgresql默认不支持1字节的整数tinyint
            case SMALLINT:
                return "INT2";
            case INTEGER:
                return "INT4";
            case BIGINT:
                return "INT8";
            case FLOAT:
                return "FLOAT4";
            case DOUBLE:
                return "FLOAT8";
            default:
                return super.getColumnTypeName(sqlType);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getAutoIncrement(Column column) {
        // FIXME 未实现
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
    String getKeywordLikeCaseInsensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + " " + getKeyword("ILIKE");
        } else {
            return getKeyword("ILIKE");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getKeywordLikeCaseSensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + " " + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getKeywordEqCaseInsensitive() {
        // FIXME 使用like有隐患，后续再来优化
        return getKeyword("ILIKE");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getKeywordEqCaseSensitive() {
        return Chars.EQ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getKeywordNeCaseInsensitive() {
        // FIXME 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getKeywordNeCaseSensitive() {
        return "!=";
    }

}