package cn.featherfly.common.db.dialect;

import java.sql.Types;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.DateUtils;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * PostgreSQL Dialect.
 * </p>
 *
 * @author zhongj
 */
public class PostgreSQLDialect extends AbstractDialect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreSQLDialect.class);

    /**
     */
    public PostgreSQLDialect() {
        setTableAndColumnNameUppercase(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPaginationSql(String sql, int start, int limit) {
        return getPaginationSql(sql, start, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParamNamedPaginationSql(String sql, int start, int limit) {
        return getPaginationSql(sql, start, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getPaginationSqlParameter(Object[] params, int start, int limit) {
        Object[] pagingParams = null;
        if (limit > Chars.ZERO) {
            LOGGER.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            LOGGER.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            LOGGER.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        if (start > 0) {
            LOGGER.debug("start > 0 , use start {}", start);
            pagingParams = new Object[] { Integer.valueOf(limit), Integer.valueOf(start) };
        } else {
            LOGGER.debug("start < 0 , don't use start");
            pagingParams = new Object[] { Integer.valueOf(limit) };
        }
        return (Object[]) ArrayUtils.concat(params, pagingParams);
    }

    private String getPaginationSql(String sql, int start, boolean isParamNamed) {
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
                pagingSelect.append(String.format(" LIMIT :%s OFFSET :%s", LIMIT_PARAM_NAME, START_PARAM_NAME));
            } else {
                pagingSelect.append(String.format(" LIMIT :%s", LIMIT_PARAM_NAME));
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
        LOGGER.debug("原始Sql：{}", sql);
        LOGGER.debug("分页Sql：{}", pagingSelect);
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
                        sqlPart.append("'").append(DateUtils.formart((Date) value, "yyyy-MM-dd HH:mm:ss")).append("'");
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
        if (LangUtils.isNotEmpty(name)) {
            return getWrapSign() + name + getWrapSign();
        }
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFkCheck(boolean check) {
        // FIXME 未实现方法
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWrapSign() {
        return "\"";
    }
}