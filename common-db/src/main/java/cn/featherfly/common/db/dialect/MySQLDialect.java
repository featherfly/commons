package cn.featherfly.common.db.dialect;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * <p>
 * MySQL Dialect.
 * </p>
 *
 * @author zhongj
 */
public class MySQLDialect extends AbstractDialect {

    /**
     */
    public MySQLDialect() {
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
                        Lang.array(PARAM_NAME_START_SYMBOL, START_PARAM_NAME, LIMIT_PARAM_NAME)));
            } else {
                pagingSelect
                        .append(Strings.format(" LIMIT {0}{1}", Lang.array(PARAM_NAME_START_SYMBOL, LIMIT_PARAM_NAME)));
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
    public String wrapName(String name) {
        if (Lang.isNotEmpty(name)) {
            return getWrapSign() + name + getWrapSign();
        }
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWrapSign() {
        return "`";
    }

    /**
     * {@inheritDoc}
     */
    public String getFkCheck(boolean check) {
        return "SET FOREIGN_KEY_CHECKS=" + (check ? 1 : 0);
    }

    @Override
    protected String getAutoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "AUTO_INCREMENT";
        } else {
            return "";
        }
    }

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
        StringBuilder columns = new StringBuilder();
        for (String columnName : columnNames) {
            BuilderUtils.link(columns, Strings.format("{0}=values({0}),", columnName));
        }
        if (columns.length() > 0) {
            columns.deleteCharAt(columns.length() - 1);
        }
        return BuilderUtils.link(sql, columns.toString());
    }
}