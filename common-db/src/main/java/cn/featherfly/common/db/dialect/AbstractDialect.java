package cn.featherfly.common.db.dialect;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.repository.Index;

/**
 * 数据库方言的抽象类. 实现了一些通用内容.
 *
 * @author zhongj
 */
public abstract class AbstractDialect implements Dialect {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Keyworld keyworld;

    private Operator operator;

    /** for update的后置. */
    protected static final String UPDATE_STRING = " for update";

    /** The keywords uppercase. */
    private StringCase keywordCase = StringCase.NONE;

    /** The table and column name uppercase. */
    private StringCase tableAndColumnNameCase = StringCase.NONE;

    /**
     * Instantiates a new abstract dialect.
     */
    public AbstractDialect() {
        keyworld = new Keyworld(this);
        operator = new Operator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringCase keywordsCase() {
        return keywordCase;
    }

    /**
     * set keywordCase value.
     *
     * @param keywordCase keywordCase
     */
    public void setKeywordCase(StringCase keywordCase) {
        this.keywordCase = keywordCase;
    }

    /**
     * set tableAndColumnNameCase value.
     *
     * @param tableAndColumnNameCase tableAndColumnNameCase
     */
    public void setTableAndColumnNameCase(StringCase tableAndColumnNameCase) {
        this.tableAndColumnNameCase = tableAndColumnNameCase;
    }

    /**
     * 返回tableAndColumnNameUppercase.
     *
     * @return StringConverter
     */
    @Override
    public StringCase tableAndColumnNameCase() {
        return tableAndColumnNameCase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDDL(String schema, String tableName, Column[] addColumns, Column[] modifyColumns,
            Column[] dropColumns) {
        if (Lang.isEmpty(addColumns) && Lang.isEmpty(modifyColumns) && Lang.isEmpty(dropColumns)) {
            return "";
        }
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(schema, tableName));
        ddl.append(Chars.NEW_LINE);
        if (Lang.isNotEmpty(addColumns)) {
            ddl.append(buildAddColumnDDL(addColumns)).toString();
            if (Lang.isNotEmpty(modifyColumns) || Lang.isNotEmpty(dropColumns)) {
                ddl.append(Chars.COMMA);
            }
            ddl.append(Chars.NEW_LINE);
        }
        if (Lang.isNotEmpty(modifyColumns)) {
            ddl.append(buildModifyColumnDDL(modifyColumns)).toString();
            if (Lang.isNotEmpty(dropColumns)) {
                ddl.append(Chars.COMMA);
            }
            ddl.append(Chars.NEW_LINE);
        }
        if (Lang.isNotEmpty(dropColumns)) {
            ddl.append(buildDropColumnDDL(dropColumns)).toString();
            ddl.append(Chars.NEW_LINE);
        }
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableAddColumnDDL(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(buildAddColumnDDL(columns)).append(Chars.SEMI).toString();
    }

    /**
     * Builds the add column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String buildAddColumnDDL(Column... columns) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
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
    public String buildAlterTableModifyColumnDDL(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(buildModifyColumnDDL(columns)).append(Chars.SEMI).toString();
    }

    /**
     * Builds the modify column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String buildModifyColumnDDL(Column... columns) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
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
    public String buildAlterTableDropColumnDDL(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(buildDropColumnDDL(columns)).append(Chars.SEMI).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildAlterTableDropColumnDDL(String schema, String tableName, String... columnNames) {
        StringBuilder ddl = new StringBuilder(buildAlterTableDDL(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(buildDropColumnDDL(columnNames)).append(Chars.SEMI).toString();
    }

    /**
     * Builds the drop column DDL.
     *
     * @param columns the columns
     * @return the string
     */
    protected String buildDropColumnDDL(Column... columns) {
        String[] columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            columnNames[i] = columns[i].getName();
        }
        return buildDropColumnDDL(columnNames);
    }

    /**
     * Builds the drop column DDL.
     *
     * @param columnNames the column names
     * @return the string
     */
    protected String buildDropColumnDDL(String... columnNames) {
        StringBuilder ddl = new StringBuilder(Chars.SPACE);
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
     * @param table the table
     * @return the string
     */
    @Override
    public String buildCreateTableDDL(Table table) {
        AssertIllegalArgument.isNotEmpty(table, "table");
        String schema = table.getSchema();
        StringBuilder sql = new StringBuilder();
        String tableName = Lang.isEmpty(schema) ? wrapName(table.getName())
                : wrapName(schema) + Chars.DOT + wrapName(table.getName());
        BuilderUtils.link(sql, getKeyword(Keywords.CREATE), getKeyword(Keywords.TABLE), tableName, Chars.PAREN_L);
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, getTableColumnsDDL(table));
        sql.append(Chars.NEW_LINE);
        BuilderUtils.link(sql, Chars.PAREN_R, getTableComment(table));

        if (table.getIndexs().size() > 0) {
            sql.append(Chars.SEMI).append(Chars.NEW_LINE);
            for (Index index : table.getIndexs()) {
                sql.append(buildCreateIndexDDL(schema, table.getName(), index.getName(), index.getColumns(),
                        index.isUnique())).append(Chars.NEW_LINE);
            }
            sql.deleteCharAt(sql.length() - 1);
        } else {
            sql.append(Chars.SEMI);
        }
        return sql.toString();
    }

    /**
     * Gets the table comment.
     *
     * @param table the table
     * @return the table comment
     */
    protected String getTableComment(Table table) {
        return Lang.isEmpty(table.getRemark()) ? ""
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
        if (Lang.isEmpty(column.getDefaultValue())) {
            return "";
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DEFAULT), "'" + column.getDefaultValue() + "'");
        }
    }

    /**
     * Gets the identity.
     *
     * @param column the column
     * @return the identity
     */
    protected abstract String getAutoIncrement(Column column);

    /**
     * Gets the column comment.
     *
     * @param column the column
     * @return the column comment
     */
    protected String getColumnComment(Column column) {
        return Lang.isEmpty(column.getRemark()) ? ""
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
        if (defineNeedSize(column.getSqlType())) {
            if (size <= 0) {
                throw new JdbcMappingException("#size.not.define", Lang.array(column.getName()));
            } else {
                result += Chars.PAREN_L + size;
                if (decimalDigits > 0) {
                    result += Chars.COMMA + decimalDigits;
                }
                result += Chars.PAREN_R;
            }
        }
        if (Lang.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;
    }

    private boolean defineNeedSize(SQLType sqlType) {
        return sqlType == JDBCType.VARCHAR || sqlType == JDBCType.NVARCHAR || sqlType == JDBCType.CHAR
                || sqlType == JDBCType.NCHAR || sqlType == JDBCType.LONGVARCHAR || sqlType == JDBCType.LONGNVARCHAR
                || sqlType == JDBCType.FLOAT || sqlType == JDBCType.DOUBLE || sqlType == JDBCType.DECIMAL
                || sqlType == JDBCType.NUMERIC;
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
    public Operator getOperators() {
        return operator;
    }

    @Override
    public String getCompareExpression(ComparisonOperator comparisonOperator, String name, Object values,
            MatchStrategy matchStrategy) {
        if (ComparisonOperator.IN == comparisonOperator || ComparisonOperator.NI == comparisonOperator) {
            return getInOrNotInExpression(comparisonOperator == ComparisonOperator.IN, name, values, matchStrategy);
        } else if (ComparisonOperator.BA == comparisonOperator || ComparisonOperator.NBA == comparisonOperator) {
            return getBetweenOrNotBetweenExpression(comparisonOperator == ComparisonOperator.BA, name, values,
                    matchStrategy);
        } else {
            if (ComparisonOperator.ISN == comparisonOperator) {
                return getIsNullOrNotIsNullExpression(values == null || (Boolean) values, name);
            } else if (ComparisonOperator.INN == comparisonOperator) {
                return getIsNullOrNotIsNullExpression(values != null && !(Boolean) values, name);
            } else {
                return getCompareExpression0(comparisonOperator, name, values, matchStrategy);
            }
        }

    }

    protected abstract String getCompareExpression0(ComparisonOperator comparisonOperator, String columnName,
            Object values, MatchStrategy matchStrategy);

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeywordLike(MatchStrategy matchStrategy) {
        if (matchStrategy == null) {
            matchStrategy = MatchStrategy.AUTO;
        }
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                return getKeywordLikeCaseInsensitive(false);
            case CASE_SENSITIVE:
                return getKeywordLikeCaseSensitive(false);
            default:
                return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeywordNotLike(MatchStrategy matchStrategy) {
        if (matchStrategy == null) {
            matchStrategy = MatchStrategy.AUTO;
        }
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                return getKeywordLikeCaseInsensitive(true);
            case CASE_SENSITIVE:
                return getKeywordLikeCaseSensitive(true);
            default:
                return getKeyword(Keywords.NOT) + " " + getKeyword(Keywords.LIKE);
        }
    }

    /**
     * Gets the keyword like case insensitive.
     *
     * @param reverse the reverse
     * @return the keyword like case insensitive
     */
    protected String getKeywordLikeCaseInsensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * Gets the keyword like case sensitive.
     *
     * @param reverse the reverse
     * @return the keyword like case sensitive
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildUpsertBatchSql(String tableName, String[] columnNames, String[] uniqueColumns,
            int insertAmount) {
        String sql = buildInsertBatchSql(tableName, columnNames, insertAmount);
        StringBuilder conflict = new StringBuilder();
        List<String> columns = ArrayUtils.toList(columnNames);
        for (String uc : uniqueColumns) {
            conflict.append(uc).append(",");
            if (ArrayUtils.contain(columnNames, uc)) {
                columns.remove(uc);
            }
        }
        if (conflict.length() > 0) {
            conflict.deleteCharAt(conflict.length() - 1);
            conflict.insert(0, "(");
            conflict.append(")");
        }
        sql = BuilderUtils.link(sql, "ON CONFLICT", conflict.toString(), "DO UPDATE SET");
        StringBuilder columnsSql = new StringBuilder();
        for (String columnName : columns) {
            BuilderUtils.link(columnsSql, Strings.format("{0}=EXCLUDED.{0},", wrapName(columnName)));
        }
        if (columnsSql.length() > 0) {
            columnsSql.deleteCharAt(columnsSql.length() - 1);
        }
        return BuilderUtils.link(sql, columnsSql.toString());
    }
}