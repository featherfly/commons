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
 * Oracle Dialect.
 * </p>
 *
 * @author zhongj
 */
public class OracleDialect extends AbstractDialect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDialect.class);

    /**
     */
    public OracleDialect() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getPaginationSqlParameter(Object[] params, int start, int limit) {
        Object[] pagingParams = null;
        if (start > 0) {
            LOGGER.debug("start > 0 , use start {}", start);
            pagingParams = new Object[2];
            pagingParams[0] = Integer.valueOf(start);
        } else {
            LOGGER.debug("start < 0 , don't use start");
            pagingParams = new Object[1];
        }
        if (limit > Chars.ZERO) {
            LOGGER.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            LOGGER.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            LOGGER.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        pagingParams[pagingParams.length - 1] = start + limit;
        return (Object[]) ArrayUtils.concat(params, pagingParams);
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

    private String getPaginationSql(String sql, int start, boolean isParamNamed) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (isForUpdate(sql)) {
            sql = sql.substring(0, sql.length() - UPDATE_STRING.length());
            isForUpdate = true;
        }
        final int sqlLengthOffset = 50;
        StringBuilder pagingSelect = new StringBuilder(sql.length() + sqlLengthOffset);
        if (start > 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (isParamNamed) {
            if (start > 0) {
                pagingSelect.append(String.format(" ) row_ ) where rownum_ > :%s and rownum_ <= :%s ", START_PARAM_NAME,
                        LIMIT_PARAM_NAME));
            } else {
                pagingSelect.append(String.format(") where rownum <= :%s", LIMIT_PARAM_NAME));
            }
        } else {
            if (start > 0) {
                pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
            } else {
                pagingSelect.append("select * from ( ");
            }
            pagingSelect.append(sql);
            if (start > 0) {
                pagingSelect.append(" ) row_ ) where rownum_ > ? and rownum_ <= ?");
            } else {
                pagingSelect.append(" ) where rownum <= ?");
            }
        }
        if (isForUpdate) {
            pagingSelect.append(" for update");
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
                        sqlPart.append("to_date('").append(DateUtils.formart((Date) value, "yyyy-MM-dd HH:mm:ss"))
                                .append("' , 'yyyy-mm-dd hh24:mi:ss')");
                    } else {
                        sqlPart.append("to_date('").append(value).append("' , 'yyyy-mm-dd hh24:mi:ss')");
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
        return "'";
    }
}