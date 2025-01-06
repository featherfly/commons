package cn.featherfly.common.db.dialect;

import java.io.Serializable;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.ddl.OracleDDLFeature;
import cn.featherfly.common.db.dialect.dml.OracleDMLFeature;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * Oracle Dialect.
 *
 * @author zhongj
 */
public class OracleDialect extends AbstractDialect {

    private final OracleDDLFeature ddlFeature;

    private final OracleDMLFeature dmlFeature;

    /**
     * Instantiates a new oracle dialect.
     */
    public OracleDialect() {
        super();
        ddlFeature = new OracleDDLFeature(this);
        dmlFeature = new OracleDMLFeature(this);
    }

    @Override
    public OracleDDLFeature ddl() {
        return ddlFeature;
    }

    @Override
    public OracleDMLFeature dml() {
        return dmlFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable[] getPaginationSqlParameter(Serializable[] params, int start, int limit) {
        Serializable[] pagingParams = null;
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            pagingParams = new Serializable[2];
            pagingParams[0] = Integer.valueOf(start);
        } else {
            logger.debug("start < 0 , don't use start");
            pagingParams = new Serializable[1];
        }
        if (limit > Chars.ZERO) {
            logger.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            logger.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            logger.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        pagingParams[pagingParams.length - 1] = start + limit;
        return (Serializable[]) ArrayUtils.concat(params, pagingParams);
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
    public String getNamedParamPaginationSql(String sql, int start, int limit) {
        return getNamedParamPaginationSql(sql, start, limit, PARAM_NAME_START_SYMBOL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamedParamPaginationSql(String sql, int start, int limit, char startSymbol) {
        return getPaginationSql(sql, start, false, startSymbol);
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
        if (start > 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (isParamNamed) {
            if (start > 0) {
                pagingSelect.append(Str.format(" ) row_ ) where rownum_ > {0}{1} and rownum_ <= {0}{2} ",
                    startSymbol, START_PARAM_NAME, LIMIT_PARAM_NAME));
            } else {
                pagingSelect.append(Str.format(") where rownum <= {0}{1}", startSymbol, LIMIT_PARAM_NAME));
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
        logger.debug("原始Sql：{}", sql);
        logger.debug("分页Sql：{}", pagingSelect);
        return pagingSelect.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueToSql(Object value, int sqlType) {
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
                        sqlPart.append("to_date('").append(Dates.formatTime((Date) value))
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
    public String getWrapSymbol() {
        return "'";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlHeader() {
        // NOIMPL 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlFooter() {
        // NOIMPL 未实现
        throw new UnsupportedException();
    }

    @Override
    public boolean supportUpsertBatch() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdGenerator getIdGenerator(String table, String column) {
        // NOIMPL 未实现
        // 不确定oracle是否实现了postgresql serial 这样自动创建序列的能力
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDatabaseName() {
        return "Oracle";
    }
}