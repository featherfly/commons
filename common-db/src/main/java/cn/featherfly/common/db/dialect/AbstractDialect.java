package cn.featherfly.common.db.dialect;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.metadata.Column;
import cn.featherfly.common.db.metadata.SqlType;
import cn.featherfly.common.db.metadata.Table;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 数据库方言的抽象类. 实现了一些通用内容.
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractDialect implements Dialect {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The keyworld. */
    private Keyworld keyworld;

    /** for update的后置. */
    protected static final String UPDATE_STRING = " for update";

    /** The keywords uppercase. */
    private boolean keywordsUppercase = true;

    /** The table and column name uppercase. */
    private boolean tableAndColumnNameUppercase = false;

    /**
     * Instantiates a new abstract dialect.
     */
    public AbstractDialect() {
        keyworld = new Keyworld(this);
    }

    /**
     * 返回keywordsUppercase.
     *
     * @return keywordsUppercase
     */
    @Override
    public boolean isKeywordsUppercase() {
        return keywordsUppercase;
    }

    /**
     * 设置keywordsUppercase.
     *
     * @param keywordsUppercase keywordsUppercase
     */
    public void setKeywordsUppercase(boolean keywordsUppercase) {
        this.keywordsUppercase = keywordsUppercase;
    }

    /**
     * 返回tableAndColumnNameUppercase.
     *
     * @return tableAndColumnNameUppercase
     */
    @Override
    public boolean isTableAndColumnNameUppercase() {
        return tableAndColumnNameUppercase;
    }

    /**
     * 设置tableAndColumnNameUppercase.
     *
     * @param tableAndColumnNameUppercase tableAndColumnNameUppercase
     */
    public void setTableAndColumnNameUppercase(boolean tableAndColumnNameUppercase) {
        this.tableAndColumnNameUppercase = tableAndColumnNameUppercase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableAddColumnDDL(String databaseName, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(databaseName, tableName));
        ddl.append(Chars.NEW_LINE);
        for (Column column : columns) {
            BuilderUtils.link(ddl, getKeyword(Keywords.ADD), getKeyword(Keywords.COLUMN), getColumnDDL(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableModifyColumnDDL(String databaseName, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(databaseName, tableName));
        ddl.append(Chars.NEW_LINE);
        for (Column column : columns) {
            BuilderUtils.link(ddl, getKeyword(Keywords.MODIFY), getKeyword(Keywords.COLUMN), getColumnDDL(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDropColumnDDL(String databaseName, String tableName, Column... columns) {
        String[] columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            columnNames[i] = columns[i].getName();
        }
        return buildAlterTableDropColumnDDL(databaseName, tableName, columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDropColumnDDL(String databaseName, String tableName, String... columnNames) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(databaseName, tableName));
        ddl.append(Chars.NEW_LINE);
        for (String columnName : columnNames) {
            BuilderUtils.link(ddl, getKeyword(Keywords.DROP), getKeyword(Keywords.COLUMN), wrapName(columnName));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        ddl.deleteCharAt(ddl.length() - 1).deleteCharAt(ddl.length() - 1);
        return ddl.toString();
    }

    /**
     * Builds the create table sql.
     *
     * @param dataBaseName the data base name
     * @param table        the table
     * @return the string
     */
    @Override
    public String buildCreateTableDDL(String dataBaseName, Table table) {
        AssertIllegalArgument.isNotEmpty(table, "table");
        StringBuilder sql = new StringBuilder();
        String tableName = LangUtils.isEmpty(dataBaseName) ? wrapName(table.getName())
                : wrapName(dataBaseName) + Chars.DOT + wrapName(table.getName());
        BuilderUtils.link(sql, getKeyword(Keywords.CREATE), getKeyword(Keywords.TABLE), tableName, Chars.PAREN_L);
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, getTableColumnsDDL(table));
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, Chars.PAREN_R, getTableComment(table));
        return sql.toString();
    }

    /**
     * Gets the table comment.
     *
     * @param table the table
     * @return the table comment
     */
    protected String getTableComment(Table table) {
        return LangUtils.isEmpty(table.getRemark()) ? ""
                : BuilderUtils.link(getKeyword(Keywords.COMMENT), "'" + table.getRemark() + "'");
    }

    /**
     * Gets the table columns DDL.
     *
     * @param table the table
     * @return the table columns DDL
     */
    protected String getTableColumnsDDL(Table table) {
        StringBuilder ddl = new StringBuilder();
        for (Column column : table.getColumns()) {
            BuilderUtils.link(ddl, getColumnDDL(column));
            ddl.append(Chars.COMMA);
            ddl.append(Chars.NEW_LINE);
        }
        BuilderUtils.link(ddl, getPrimaryKeyDDL(table));
        return ddl.toString();
    }

    /**
     * Gets the column DDL.
     *
     * @param column the column
     * @return the column DDL
     */
    protected String getColumnDDL(Column column) {
        return BuilderUtils.link(wrapName(column.getName()), getColumnTypeDDL(column), getColumnNotNull(column),
                getDefaultValue(column), getAutoIncrement(column), getColumnComment(column));
    }

    /**
     * Gets the primary key DDL.
     *
     * @param table the table
     * @return the primary key DDL
     */
    protected String getPrimaryKeyDDL(Table table) {
        StringBuilder result = new StringBuilder(Chars.PAREN_L);
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                result.append(wrapName(column.getName())).append(Chars.COMMA);
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append(Chars.PAREN_R);
        return BuilderUtils.link(getKeyword(Keywords.PRIMARY), getKeyword(Keywords.KEY), result.toString());
    }

    /**
     * Gets the default value.
     *
     * @param column the column
     * @return the default value
     */
    protected String getDefaultValue(Column column) {
        if (LangUtils.isEmpty(column.getDefaultValue())) {
            return "";
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DEFAULT), "'" + column.getDefaultValue() + "'");
        }
    }

    /**
     * Gets the auto increment.
     *
     * @param column the column
     * @return the auto increment
     */
    protected String getAutoIncrement(Column column) {
        return "";
    }

    /**
     * Gets the column comment.
     *
     * @param column the column
     * @return the column comment
     */
    protected String getColumnComment(Column column) {
        return LangUtils.isEmpty(column.getRemark()) ? ""
                : BuilderUtils.link(getKeyword(Keywords.COMMENT), "'" + column.getRemark() + "'");
    }

    /**
     * Gets the column not null.
     *
     * @param column the column
     * @return the column not null
     */
    protected String getColumnNotNull(Column column) {
        if (column.isNullable()) {
            return "";
        } else {
            return BuilderUtils.link(getKeyword(Keywords.NOT), getKeyword(Keywords.NULL));
        }
    }

    /**
     * Gets the column type DDL.
     *
     * @param column the column
     * @return the column type DDL
     */
    protected String getColumnTypeDDL(Column column) {
        return getColumnTypeDDL(column, null);
    }

    /**
     * Gets the column type DDL.
     *
     * @param column the column
     * @param extra  the extra
     * @return the column type DDL
     */
    protected String getColumnTypeDDL(Column column, String extra) {
        int size = column.getSize();
        int decimalDigits = column.getDecimalDigits();
        String result = getColumnTypeName(column.getSqlType());
        if (size > 0) {
            result += Chars.PAREN_L + size;
            if (decimalDigits > 0) {
                result += Chars.DOT + decimalDigits;
            }
            result += Chars.PAREN_R;
        }
        if (LangUtils.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;
    }

    /**
     * Gets the column type name.
     *
     * @param sqlType the sql type
     * @return the column type name
     */
    protected String getColumnTypeName(SqlType sqlType) {
        return sqlType.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Keyworld getKeywords() {
        return keyworld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getPaginationSqlParameter(Object[] params, int start, int limit) {
        Object[] pagingParams = null;
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            pagingParams = new Object[2];
            pagingParams[0] = Integer.valueOf(start);
        } else {
            logger.debug("start < 0 , don't use start");
            pagingParams = new Object[1];
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
        pagingParams[pagingParams.length - 1] = limit;
        return (Object[]) ArrayUtils.concat(params, pagingParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getPaginationSqlParameter(Map<String, Object> params, int start, int limit) {
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            params.put(START_PARAM_NAME, start);
        } else {
            logger.debug("start < 0 , don't use start");
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
        params.put(LIMIT_PARAM_NAME, limit);
        return params;
    }

    /**
     * <p>
     * 判断传入sql是否带有使用for update语法
     * </p>
     * .
     *
     * @param sql sql
     * @return sql是否带有使用for update语法
     */
    protected boolean isForUpdate(String sql) {
        return sql.toLowerCase().endsWith(UPDATE_STRING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueToSql(Object value, int sqlType) {
        return convertValueToSql(value, sqlType);
    }

    /**
     * <p>
     * 转换值为字符串
     * </p>
     * .
     *
     * @param value   value
     * @param sqlType sqlType
     * @return 字符串
     */
    protected abstract String convertValueToSql(Object value, int sqlType);
}