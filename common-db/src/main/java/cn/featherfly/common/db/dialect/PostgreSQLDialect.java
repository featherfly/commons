package cn.featherfly.common.db.dialect;

import java.io.Serializable;
import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.ddl.PostgreSQLDDLFeature;
import cn.featherfly.common.db.dialect.dml.PostgreSQLDMLFeature;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * PostgreSQL Dialect.
 *
 * @author zhongj
 */
public class PostgreSQLDialect extends AbstractDialect {

    private final PostgreSQLDDLFeature ddlFeature;

    private final PostgreSQLDMLFeature dmlFeature;

    /**
     * Instantiates a new postgre SQL dialect.
     */
    public PostgreSQLDialect() {
        super();
        ddlFeature = new PostgreSQLDDLFeature(this);
        dmlFeature = new PostgreSQLDMLFeature(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DDLFeature ddl() {
        return ddlFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DMLFeature dml() {
        return dmlFeature;
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
        return getPaginationSql(sql, start, true, startSymbol);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable[] getPaginationSqlParameter(Serializable[] params, int start, int limit) {
        Serializable[] pagingParams = null;
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
            pagingParams = new Serializable[] { Integer.valueOf(limit), Integer.valueOf(start) };
        } else {
            logger.debug("start < 0 , don't use start");
            pagingParams = new Serializable[] { Integer.valueOf(limit) };
        }
        return (Serializable[]) ArrayUtils.concat(params, pagingParams);
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
                pagingSelect.append(
                    Strings.format(" LIMIT {0}{1} OFFSET {0}{2}", startSymbol, LIMIT_PARAM_NAME, START_PARAM_NAME));
            } else {
                pagingSelect.append(Strings.format(" LIMIT {0}{1}", startSymbol, LIMIT_PARAM_NAME));
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
    protected String getKeywordLikeCaseInsensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword("ILIKE");
        } else {
            return getKeyword("ILIKE");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKeywordLikeCaseSensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdGenerator getIdGenerator(String table, String name) {
        // 使用Serial类型，会自动创建序列
        // 使用Serial类型时，不需要传入参数，即不把id字段的值放入执行sql的参数数组中，insert xxx (id, name) values(DEFAULT, 'your name')
        // 不确定传入null会不会出现问题，后续测试
        return DEFAULT_ID_GENERATOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDatabaseName() {
        // YUFEI_TODO Auto-generated method stub
        return "PostgreSQL";
    }
}