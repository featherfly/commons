
package cn.featherfly.common.db.dialect;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.ddl.SQLiteDDLFeature;
import cn.featherfly.common.db.dialect.dml.SQLiteDMLFeature;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * SQLite Dialect .
 *
 * @author zhongj
 */
public class SQLiteDialect extends AbstractDialect {

    /** The Constant TEXT_TYPE. */
    public static final String TEXT_TYPE = "TEXT";

    private final SQLiteDDLFeature ddlFeature;
    private final SQLiteDMLFeature dmlFeature;

    /**
     * Instantiates a new SQ lite dialect.
     */
    public SQLiteDialect() {
        super();
        ddlFeature = new SQLiteDDLFeature(this);
        dmlFeature = new SQLiteDMLFeature(this);
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
                    .append(Str.format(" LIMIT {0}{1},{0}{2}", startSymbol, START_PARAM_NAME, LIMIT_PARAM_NAME));
            } else {
                pagingSelect.append(Str.format(" LIMIT {0}{1}", startSymbol, LIMIT_PARAM_NAME));
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
        return "`";
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
    public boolean supportAutoGenerateKeyBatch() {
        return false;
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
    public IdGenerator getIdGenerator(String table, String column) {
        return DEFAULT_ID_GENERATOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDatabaseName() {
        return "SQLite";
    }
}
