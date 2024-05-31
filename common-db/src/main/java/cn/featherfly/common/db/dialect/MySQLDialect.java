package cn.featherfly.common.db.dialect;

import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Date;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.ddl.MysqlDDLFeature;
import cn.featherfly.common.db.dialect.dml.MysqlDMLFeature;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * MySQL Dialect.
 *
 * @author zhongj
 */
public class MySQLDialect extends AbstractDialect {

    /** The Constant DEFAULT_COLLATE_CASE_INSENSITIVE. */
    public static final String DEFAULT_COLLATE_CASE_INSENSITIVE = "UTF8_GENERAL_CI";

    private String collateCaseInsensitive = DEFAULT_COLLATE_CASE_INSENSITIVE;

    private final DDLFeature ddlFeature;
    private final DMLFeature dmlFeature;

    /**
     * Instantiates a new my SQL
     */
    public MySQLDialect() {
        super();
        ddlFeature = new MysqlDDLFeature(this);
        dmlFeature = new MysqlDMLFeature(this);
    }

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

    public String getFkCheck(boolean check) {
        return "SET FOREIGN_KEY_CHECKS=" + (check ? 1 : 0);
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

    @Override
    public boolean supportBatchUpdates(DatabaseMetaData metaData) throws SQLException {
        return metaData.supportsBatchUpdates() && metaData.getURL().contains("rewriteBatchedStatements=true");
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
        return "MySQL";
    }
}