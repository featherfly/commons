package cn.featherfly.common.db.dialect;

import java.lang.reflect.Array;
import java.sql.JDBCType;
import java.sql.SQLType;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.model.TableElement;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.Function;
import cn.featherfly.common.operator.LogicOperator;
import cn.featherfly.common.operator.SortOperator;

/**
 * database dialect.
 *
 * @author zhongj
 */
public interface Dialect {

    /** 命名参数查询的查询条件默认起始位置名称. */
    String START_PARAM_NAME = "dialect_paging_start";

    /** 命名参数查询的查询条件默认数量名称. */
    String LIMIT_PARAM_NAME = "dialect_paging_limit";

    /** The primary key index name. */
    String PRIMARY_KEY_INDEX_NAME = "PRIMARY";

    /** The param name start symbol. */
    char PARAM_NAME_START_SYMBOL = Chars.COLON_CHAR;

    /** 默认的查询返回条数. */
    int DEFAULT_LIMIT = 10;

    /**
     * The Enum StringConverter.
     *
     * @author zhongj
     */
    public enum StringCase {

        /** The none. */
        NONE,
        /** The upper case. */
        UPPER_CASE,
        /** The lower case. */
        LOWER_CASE
    }

    /**
     * 转换普通sql为带分页的sql.
     *
     * @param sql   带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @return 返回转换好的分页sql
     */
    String getPaginationSql(String sql, int start, int limit);

    /**
     * <p>
     * 返回分页参数的数组
     * </p>
     * .
     *
     * @param params 参数数组
     * @param start  起始数
     * @param limit  数量
     * @return 分页参数的数组
     */
    Object[] getPaginationSqlParameter(Object[] params, int start, int limit);

    /**
     * <p>
     * 返回分页参数的MAP
     * </p>
     * .
     *
     * @param params 参数MAP
     * @param start  起始数
     * @param limit  数量
     * @return 分页参数的MAP
     */
    Map<String, Object> getPaginationSqlParameter(Map<String, Object> params, int start, int limit);

    /**
     * <p>
     * 转换普通命名sql为带分页的sql,此sql为带命名参数sql, 如select * from user where user_name =
     * :username
     * </p>
     * .
     *
     * @param sql   带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @return 返回转换好的分页sql
     */
    String getParamNamedPaginationSql(String sql, int start, int limit);

    /**
     * <p>
     * 转换普通命名sql为带分页的sql,此sql为带命名参数sql, 如select * from user where user_name =
     * {startSymal}username.
     * </p>
     * .
     *
     * @param sql         带转换的sql
     * @param start       起始数
     * @param limit       数量
     * @param startSymbol 命名参数的起始符号
     * @return 返回转换好的分页sql
     */
    String getParamNamedPaginationSql(String sql, int start, int limit, char startSymbol);

    /**
     * <p>
     * 转换为SQL语句中使用的字符串
     * </p>
     * .
     *
     * @param value   值
     * @param sqlType sql类型
     * @return the string
     */
    String valueToSql(Object value, int sqlType);

    /**
     * 包装名称，避免关键字问题 .
     *
     * @param name 名称（列明，表名等）
     * @return 包装后的名称
     */
    default String wrapName(String name) {
        if (Lang.isNotEmpty(name)) {
            return getWrapSymbol() + name + getWrapSymbol();
        }
        return name;
    }

    /**
     * Support auto generate key batch.
     *
     * @return true, if successful
     */
    default boolean supportAutoGenerateKeyBatch() {
        return true;
    }

    /**
     * Support insert batch.
     *
     * @return true, if is insert batch
     */
    default boolean supportInsertBatch() {
        return true;
    }

    /**
     * Support upsert.
     *
     * @return true, if is upsert
     */
    default boolean supportUpsert() {
        return true;
    }

    /**
     * Support upsert batch.
     *
     * @return true, if is upsert batch
     */
    default boolean supportUpsertBatch() {
        return true;
    }

    /**
     * Gets the inits the sql header.
     *
     * @return the inits the sql header
     */
    String getInitSqlHeader();

    /**
     * Gets the inits the sql footer.
     *
     * @return the inits the sql footer
     */
    String getInitSqlFooter();

    /**
     * Builds the insert sql.
     *
     * @param tableName the table name
     * @return the delete from sql
     */
    default String buildDeleteFromSql(String tableName) {
        return buildDeleteFromSql(tableName, null);
    }

    /**
     * Builds the insert sql.
     *
     * @param tableName  the table name
     * @param tableAlias the table alias
     * @return the delete from sql
     */
    default String buildDeleteFromSql(String tableName, String tableAlias) {
        return BuilderUtils.link(getKeyword(Keywords.DELETE), getKeyword(Keywords.FROM), wrapName(tableName),
                Lang.isEmpty(tableAlias) ? null : wrapName(tableAlias));
    }

    /**
     * Builds the insert sql.
     *
     * @param tableName   the table name
     * @param columnNames the column names
     * @return the string
     */
    default String buildInsertSql(String tableName, String[] columnNames) {
        String sql = BuilderUtils.link(getKeyword(Keywords.INSERT), getKeyword(Keywords.INTO), wrapName(tableName),
                Chars.PAREN_L);
        StringBuilder names = new StringBuilder();
        for (String column : columnNames) {
            BuilderUtils.link(names, wrapName(column) + Chars.COMMA);
        }
        names.deleteCharAt(names.length() - 1).append(Chars.PAREN_R);
        sql += names.toString();
        sql = BuilderUtils.link(sql, getKeyword(Keywords.VALUES), Chars.PAREN_L);
        StringBuilder ques = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
        }
        ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
        sql += ques.toString();
        return sql;
    }

    /**
     * dialect for database supports batch insert.
     *
     * @param tableName    the table name
     * @param columnNames  the column names
     * @param insertAmount the insert amount
     * @return the string
     */
    default String buildInsertBatchSql(String tableName, String[] columnNames, int insertAmount) {
        String sql = BuilderUtils.link(getKeyword(Keywords.INSERT), getKeyword(Keywords.INTO), wrapName(tableName),
                Chars.PAREN_L);
        StringBuilder names = new StringBuilder();
        for (String column : columnNames) {
            BuilderUtils.link(names, wrapName(column) + Chars.COMMA);
        }
        names.deleteCharAt(names.length() - 1).append(Chars.PAREN_R);
        sql += names.toString();
        sql = BuilderUtils.link(sql, getKeyword(Keywords.VALUES), Chars.PAREN_L);
        StringBuilder ques = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
        }
        ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
        sql += ques.toString();
        for (int index = 1; index < insertAmount; index++) {
            ques = new StringBuilder();
            for (int j = 0; j < columnNames.length; j++) {
                BuilderUtils.link(ques, Chars.QUESTION + Chars.COMMA);
            }
            ques.deleteCharAt(ques.length() - 1).append(Chars.PAREN_R);
            sql += Chars.COMMA + Chars.PAREN_L + ques.toString();
        }
        return sql;
    }

    /**
     * Builds the upsert sql.
     *
     * @param tableName    the table name
     * @param columnNames  the column names
     * @param uniqueColumn the unique column
     * @return the string
     */
    default String buildUpsertSql(String tableName, String[] columnNames, String uniqueColumn) {
        return buildUpsertSql(tableName, columnNames, new String[] { uniqueColumn });
    }

    /**
     * Builds the upsert sql.
     *
     * @param tableName     the table name
     * @param columnNames   the column names
     * @param uniqueColumns the unique columns
     * @return the string
     */
    default String buildUpsertSql(String tableName, String[] columnNames, String[] uniqueColumns) {
        return buildUpsertBatchSql(tableName, columnNames, uniqueColumns, 1);
    }

    /**
     * Builds the upsert batch sql.
     *
     * @param tableName    the table name
     * @param columnNames  the column names
     * @param uniqueColumn the unique column
     * @param insertAmount the insert amount
     * @return the string
     */
    default String buildUpsertBatchSql(String tableName, String[] columnNames, String uniqueColumn, int insertAmount) {
        return buildUpsertBatchSql(tableName, columnNames, new String[] { uniqueColumn }, insertAmount);
    }

    /**
     * Builds the upsert batch sql.
     *
     * @param tableName     the table name
     * @param columnNames   the column names
     * @param uniqueColumns the unique columns
     * @param insertAmount  the insert amount
     * @return the string
     */
    String buildUpsertBatchSql(String tableName, String[] columnNames, String[] uniqueColumns, int insertAmount);

    /**
     * Checks if is table and column name to uppercase or lowercase or do
     * nothing.
     *
     * @return true, if is table and column name uppercase
     */
    default StringCase keywordsCase() {
        return StringCase.NONE;
    }

    /**
     * Checks if is table and column name to uppercase or lowercase or do
     * nothing.
     *
     * @return true, if is table and column name uppercase
     */
    default StringCase tableAndColumnNameCase() {
        return StringCase.NONE;
    }

    /**
     * get converted keywords.
     *
     * @return sql key words
     */
    Keyworld getKeywords();

    /**
     * Gets the operators.
     *
     * @return the operators
     */
    Operator getOperators();

    /**
     * Gets the operator.
     *
     * @param comparisonOperator the comparison operator
     * @return the operator
     */
    default String getOperator(ComparisonOperator comparisonOperator) {
        return getOperator(comparisonOperator, MatchStrategy.AUTO);
    }

    /**
     * Gets the operator.
     *
     * @param comparisonOperator the comparison operator
     * @param matchStrategy      the match strategy
     * @return the operator
     */
    default String getOperator(ComparisonOperator comparisonOperator, MatchStrategy matchStrategy) {
        switch (comparisonOperator) {
            case EQ:
                return getOperators().eq();
            case NE:
                return getOperators().ne();
            case SW:
                return getKeywords().like(matchStrategy);
            case NSW:
                return getKeywords().notLike(matchStrategy);
            case CO:
                return getKeywords().like(matchStrategy);
            case NCO:
                return getKeywords().notLike(matchStrategy);
            case EW:
                return getKeywords().like(matchStrategy);
            case NEW:
                return getKeywords().notLike(matchStrategy);
            case LK:
                return getKeywords().like(matchStrategy);
            case NL:
                return getKeywords().notLike(matchStrategy);
            case LT:
                return getOperators().lt();
            case LE:
                return getOperators().le();
            case GT:
                return getOperators().gt();
            case GE:
                return getOperators().ge();
            case IN:
                return getKeywords().in();
            case NI:
                return getKeywords().notIn();
            case ISN:
                return getKeywords().isNull();
            case INN:
                return getKeywords().isNotNull();
            default:
                throw new DialectException("unsupported for " + comparisonOperator);
        }
    }

    /**
     * Gets the keyword.
     *
     * @param keyword the keyword
     * @return the keyword
     */
    default String getKeyword(String keyword) {
        if (Lang.isEmpty(keyword)) {
            return "";
        }
        switch (keywordsCase()) {
            case LOWER_CASE:
                return keyword.toLowerCase();
            case UPPER_CASE:
                return keyword.toUpperCase();
            default:
                return keyword;
        }
    }

    /**
     * get converted keywords.
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(SortOperator keywords) {
        switch (keywordsCase()) {
            case LOWER_CASE:
                return keywords.toString().toLowerCase();
            //            case UPPER_CASE:
            //                return keywords.toString().toUpperCase();
            default:
                return keywords.toString();
        }
    }

    /**
     * get converted keywords.
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(LogicOperator keywords) {
        switch (keywordsCase()) {
            case LOWER_CASE:
                return keywords.toString().toLowerCase();
            //            case UPPER_CASE:
            //                return keywords.toString().toUpperCase();
            default:
                return keywords.toString();
        }
    }

    /**
     * get converted keywords.
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(Keywords keywords) {
        switch (keywordsCase()) {
            case LOWER_CASE:
                return keywords.toString().toLowerCase();
            case UPPER_CASE:
                return keywords.toString().toUpperCase();
            default:
                return keywords.toString();
        }
    }

    /**
     * Gets the keyword like.
     *
     * @param matchStrategy the like query policy
     * @return the keyword like
     */
    String getKeywordLike(MatchStrategy matchStrategy);

    /**
     * Gets the keyword like.
     *
     * @param matchStrategy the like query policy
     * @return the keyword like
     */
    String getKeywordNotLike(MatchStrategy matchStrategy);

    /**
     * Gets the compare expression.
     *
     * @param operator the operator
     * @param name     the name
     * @param values   the values
     * @return the compare expression
     */
    default String getCompareExpression(ComparisonOperator operator, String name, Object values) {
        return getCompareExpression(operator, name, values, MatchStrategy.AUTO);
    }

    /**
     * Gets the compare expression.
     *
     * @param operator   the operator
     * @param columnName the column name
     * @param values     the values
     * @param tableAlias the table alias
     * @return the compare expression
     */
    default String getCompareExpression(ComparisonOperator operator, String columnName, Object values,
            String tableAlias) {
        return getCompareExpression(operator, buildColumnSql(tableAlias, columnName), values);
    }

    /**
     * Gets the compare expression.
     *
     * @param operator      the operator
     * @param name          the name
     * @param values        the values
     * @param matchStrategy the match strategy
     * @return the compare expression
     */
    String getCompareExpression(ComparisonOperator operator, String name, Object values, MatchStrategy matchStrategy);

    /**
     * Gets the compare expression.
     *
     * @param operator      the operator
     * @param columnName    the column name
     * @param values        the values
     * @param tableAlias    the table alias
     * @param matchStrategy the match strategy
     * @return the compare expression
     */
    default String getCompareExpression(ComparisonOperator operator, String columnName, Object values,
            String tableAlias, MatchStrategy matchStrategy) {
        return getCompareExpression(operator, buildColumnSql(tableAlias, columnName), values, matchStrategy);
    }

    /**
     * Gets the checks if is null or not is null expression.
     *
     * @param isNull the is null
     * @param name   the name
     * @return the checks if is null or not is null expression
     */
    default String getIsNullOrNotIsNullExpression(boolean isNull, String name) {
        StringBuilder condition = new StringBuilder();
        condition.append(name).append(Chars.SPACE);
        if (isNull) {
            condition.append(getOperator(ComparisonOperator.ISN));
        } else {
            condition.append(getOperator(ComparisonOperator.INN));
        }
        return condition.toString();
    }

    /**
     * Gets the checks if is null or not is null expression.
     *
     * @param isNull     the is null
     * @param columnName the column name
     * @param tableAlias the table alias
     * @return the checks if is null or not is null expression
     */
    default String getIsNullOrNotIsNullExpression(boolean isNull, String columnName, String tableAlias) {
        return getIsNullOrNotIsNullExpression(isNull, buildColumnSql(tableAlias, columnName));
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween the is between
     * @param name      the name
     * @param value     the value
     * @return the between or not between expression
     */
    default String getBetweenOrNotBetweenExpression(boolean isBetween, String name, Object value) {
        StringBuilder condition = new StringBuilder();
        condition.append(name).append(Chars.SPACE) //
                .append(!isBetween ? getKeyword(Keywords.NOT) + Chars.SPACE : "") //
                .append(getKeyword(Keywords.BETWEEN)).append(Chars.SPACE) //
                .append(Chars.QUESTION).append(Chars.SPACE) //
                .append(getKeyword(Keywords.AND)).append(Chars.SPACE) //
                .append(Chars.QUESTION);
        return condition.toString();
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween  the is between
     * @param columnName the column name
     * @param values     the values
     * @param tableAlias the table alias
     * @return the between or not between expression
     */
    default String getBetweenOrNotBetweenExpression(boolean isBetween, String columnName, Object values,
            String tableAlias) {
        return getBetweenOrNotBetweenExpression(isBetween, buildColumnSql(tableAlias, columnName), values);
    }

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween     the is between
     * @param name          the name
     * @param values        the values
     * @param matchStrategy the match strategy
     * @return the between or not between expression
     */
    String getBetweenOrNotBetweenExpression(boolean isBetween, String name, Object values, MatchStrategy matchStrategy);

    /**
     * Gets the between or not between expression.
     *
     * @param isBetween     the is between
     * @param columnName    the column name
     * @param values        the values
     * @param tableAlias    the table alias
     * @param matchStrategy the match strategy
     * @return the between or not between expression
     */
    default String getBetweenOrNotBetweenExpression(boolean isBetween, String columnName, Object values,
            String tableAlias, MatchStrategy matchStrategy) {
        return getBetweenOrNotBetweenExpression(isBetween, buildColumnSql(tableAlias, columnName), values,
                matchStrategy);
    }

    /**
     * Gets the in or not in expression.
     *
     * @param isIn   the is in
     * @param name   the name
     * @param values the values
     * @return the in or not in expression
     */
    default String getInOrNotInExpression(boolean isIn, String name, Object values) {
        StringBuilder condition = new StringBuilder();
        int length = 1;
        if (values != null) {
            if (values instanceof Collection) {
                length = ((Collection<?>) values).size();
            } else if (values.getClass().isArray()) {
                length = Array.getLength(values);
            }
        }
        condition.append(name).append(Chars.SPACE).append(isIn ? getKeywords().in() : getKeywords().notIn())
                .append(" (");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                condition.append(Chars.COMMA);
            }
            condition.append(Chars.QUESTION);
        }
        condition.append(")");
        return condition.toString();
    }

    /**
     * Gets the in or not in expression.
     *
     * @param isIn       the is in
     * @param columnName the column name
     * @param values     the values
     * @param tableAlias the table alias
     * @return the in or not in expression
     */
    default String getInOrNotInExpression(boolean isIn, String columnName, Object values, String tableAlias) {
        return getInOrNotInExpression(isIn, buildColumnSql(tableAlias, columnName), values);
    }

    /**
     * Gets the in or not in expression.
     *
     * @param isIn          the is in
     * @param name          the name
     * @param values        the values
     * @param matchStrategy the match strategy
     * @return the in or not in expression
     */
    String getInOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy);

    /**
     * Gets the in or not in expression.
     *
     * @param isIn          the is in
     * @param columnName    the column name
     * @param values        the values
     * @param tableAlias    the table alias
     * @param matchStrategy the match strategy
     * @return the in or not in expression
     */
    default String getInOrNotInExpression(boolean isIn, String columnName, Object values, String tableAlias,
            MatchStrategy matchStrategy) {
        return getInOrNotInExpression(isIn, buildColumnSql(tableAlias, columnName), values, matchStrategy);
    }

    /**
     * get converted aggregate function.
     *
     * @param function aggregate function
     * @return sql aggregate function
     */
    default String getFunction(Function function) {
        switch (keywordsCase()) {
            case LOWER_CASE:
                return function.toString().toLowerCase();
            //            case UPPER_CASE:
            //                return function.toString().toUpperCase();
            default:
                return function.toString();
        }
    }

    //    /**
    //     * build sql for table
    //     *
    //     * @param table
    //     *            table
    //     * @return sql
    //     */
    //    default String buildTableSql(TableElement table) {
    //        return buildTableSql(table.getName(), table.getAlias());
    //    }
    //
    /**
     * build sql for column with aggregate function.
     *
     * @param function   function
     * @param columnName columnName
     * @return sql
     */
    default String buildColumnSql(Function function, String columnName) {
        return buildColumnSql(function, false, null, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param columnName        columnName
     * @return sql
     */
    default String buildColumnSql(AggregateFunction aggregateFunction, String columnName) {
        return buildColumnSql(aggregateFunction, false, null, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param tableAlias tableAlias
     * @param columnName columnName
     * @return sql
     */
    default String buildColumnSql(String tableAlias, String columnName) {
        return buildColumnSql(false, tableAlias, columnName, null);
    }

    /**
     * build sql for column with aggregate function.
     *
     * @param tableAlias  tableAlias
     * @param columnName  the column name
     * @param columnAlias the column alias
     * @return sql
     */
    default String buildColumnSql(String tableAlias, String columnName, String columnAlias) {
        return buildColumnSql(null, false, tableAlias, columnName, columnAlias);
    }

    //    /**
    //     * build sql for column with tableAlias and aggregate function.
    //     *
    //     * @param columnName        columnName
    //     * @param tableAlias        tableAlias
    //     * @param aggregateFunction aggregateFunction
    //     * @return sql
    //     */
    //    default String buildColumnSql(AggregateFunction aggregateFunction, String columnName, String tableAlias) {
    //        return buildColumnSql(aggregateFunction, false, tableAlias, columnName, null);
    //    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param function    the function
     * @param distinct    the distinct
     * @param tableAlias  tableAlias
     * @param columnName  columnName
     * @param columnAlias the column alias
     * @return sql
     */
    default String buildColumnSql(Function function, boolean distinct, String tableAlias, String columnName,
            String columnAlias) {
        if (function instanceof AggregateFunction) {
            return buildColumnSql(function, distinct, tableAlias, columnName, columnAlias);
        }
        // YUFEI_TODO 后续添加其他实现
        throw new DialectException("只实现了 AggregateFunction，未实现的 function" + function.getClass().getName());
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param distinct    the distinct
     * @param tableAlias  tableAlias
     * @param columnName  columnName
     * @param columnAlias the column alias
     * @return sql
     */
    default String buildColumnSql(boolean distinct, String tableAlias, String columnName, String columnAlias) {
        return buildColumnSql(null, distinct, tableAlias, columnName, columnAlias);
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param tableAlias        tableAlias
     * @param columnName        columnName
     * @param columnAlias       the column alias
     * @return sql
     */
    default String buildColumnSql(AggregateFunction aggregateFunction, String tableAlias, String columnName,
            String columnAlias) {
        return buildColumnSql(aggregateFunction, false, tableAlias, columnName, columnAlias);
    }

    /**
     * build sql for column with tableAlias and aggregate function.
     *
     * @param aggregateFunction aggregateFunction
     * @param distinct          the distinct
     * @param tableAlias        tableAlias
     * @param columnName        columnName
     * @param columnAlias       the column alias
     * @return sql
     */
    default String buildColumnSql(AggregateFunction aggregateFunction, boolean distinct, String tableAlias,
            String columnName, String columnAlias) {
        String column = columnName;
        if (!Chars.STAR.equals(columnName)) {
            column = wrapName(convertTableOrColumnName(columnName));
        }
        if (Lang.isNotEmpty(tableAlias) && !Chars.STAR.equals(columnName)) {
            column = tableAlias + Chars.DOT + column;
        }
        if (distinct) {
            column = getKeywords().distinct() + Chars.SPACE + column;
        }
        if (aggregateFunction != null) {
            column = getFunction(aggregateFunction) + Chars.PAREN_L + column + Chars.PAREN_R;
        }
        if (Lang.isNotEmpty(columnAlias)) {
            column = column + Chars.SPACE + wrapName(columnAlias);
        }
        return column;
    }

    //    /**
    //     * build sql for column with tableAlias and aggregate function.
    //     *
    //     * @param columnName columnName
    //     * @param tableAlias tableAlias
    //     * @param function   function
    //     * @return sql
    //     */
    //    default String buildColumnSql(Function function, String columnName, String tableAlias) {
    //        if (function instanceof AggregateFunction) {
    //            return buildColumnSql(function, columnName, tableAlias);
    //        }
    //        // TODO 后续添加其他实现
    //        throw new DialectException("只实现了 AggregateFunction，未实现的 function" + function.getClass().getName());
    //    }

    /**
     * convert column or table name if necessary.
     *
     * @param tableOrColumnName column or table name
     * @return sql
     */
    default String convertTableOrColumnName(String tableOrColumnName) {
        if (Lang.isEmpty(tableOrColumnName)) {
            return tableOrColumnName;
        }
        String result = tableOrColumnName;
        switch (tableAndColumnNameCase()) {
            case UPPER_CASE:
                return result.toUpperCase();
            case LOWER_CASE:
                return result.toLowerCase();
            default:
                return result;
        }
    }

    /**
     * build sql for table.
     *
     * @param table table
     * @return sql
     */
    default String buildTableSql(TableElement table) {
        return buildTableSql(table.getName(), table.getAlias());
    }

    /**
     * build sql for table.
     *
     * @param tableName tableName
     * @return sql
     */
    default String buildTableSql(String tableName) {
        return buildTableSql(tableName, null);
    }

    /**
     * build sql for table with tableAlias.
     *
     * @param tableName  tableName
     * @param tableAlias tableAlias
     * @return sql
     */
    default String buildTableSql(String tableName, String tableAlias) {
        String result = wrapName(convertTableOrColumnName(tableName));
        if (Lang.isNotEmpty(tableAlias)) {
            result = result + " " + tableAlias;
        }
        return result;
    }

    /**
     * Builds the create data base sql.
     *
     * @param dataBaseName the data base name
     * @return the string
     */
    default String buildCreateDataBaseDDL(String dataBaseName) {
        AssertIllegalArgument.isNotEmpty(dataBaseName, "dataBaseName");
        return BuilderUtils.link(getKeyword(Keywords.CREATE), getKeyword(Keywords.DATABASE), wrapName(dataBaseName))
                + Chars.SEMI;
    }

    /**
     * Builds the drop data base sql.
     *
     * @param dataBaseName the data base name
     * @return the string
     */
    default String buildDropDataBaseDDL(String dataBaseName) {
        return buildDropDataBaseDDL(dataBaseName, false);
    }

    /**
     * Builds the drop data base sql.
     *
     * @param dataBaseName the data base name
     * @param ifExists     the if exists
     * @return the string
     */
    default String buildDropDataBaseDDL(String dataBaseName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(dataBaseName, "dataBaseName");
        if (ifExists) {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.DATABASE), getKeyword(Keywords.IF),
                    getKeyword(Keywords.EXISTS), wrapName(dataBaseName)) + Chars.SEMI;
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.DATABASE), wrapName(dataBaseName))
                    + Chars.SEMI;
        }
    }

    /**
     * Builds the create schema DDL.
     *
     * @param schemaName the schema name
     * @return the string
     */
    default String buildCreateSchemaDDL(String schemaName) {
        AssertIllegalArgument.isNotEmpty(schemaName, "schemaName");
        return BuilderUtils.link(getKeyword(Keywords.CREATE), getKeyword(Keywords.SCHEMA), wrapName(schemaName))
                + Chars.SEMI;
    }

    /**
     * Builds the drop schema DDL.
     *
     * @param schemaName the schema name
     * @return the string
     */
    default String buildDropSchemaDDL(String schemaName) {
        return buildDropSchemaDDL(schemaName, false);
    }

    /**
     * Builds the drop schema DDL.
     *
     * @param schemaName the schema name
     * @param ifExists   the if exists
     * @return the string
     */
    default String buildDropSchemaDDL(String schemaName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(schemaName, "dataBaseName");
        if (ifExists) {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.SCHEMA), getKeyword(Keywords.IF),
                    getKeyword(Keywords.EXISTS), wrapName(schemaName)) + Chars.SEMI;
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.SCHEMA), wrapName(schemaName))
                    + Chars.SEMI;
        }
    }

    /**
     * Builds the create table sql.
     *
     * @param table the table
     * @return the string
     */
    String buildCreateTableDDL(Table table);

    /**
     * Builds the drop table sql.
     *
     * @param tableName the table name
     * @return the string
     */
    default String buildDropTableDDL(String tableName) {
        return buildDropTableDDL(null, tableName);
    }

    /**
     * Builds the drop table sql.
     *
     * @param tableName the table name
     * @param ifExists  the if exists
     * @return the string
     */
    default String buildDropTableDDL(String tableName, boolean ifExists) {
        return buildDropTableDDL(null, tableName, ifExists);
    }

    /**
     * Builds the drop table sql.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @return the string
     */
    default String buildDropTableDDL(String schema, String tableName) {
        return buildDropTableDDL(schema, tableName, false);
    }

    /**
     * Builds the drop table sql.
     *
     * @param schema    the database schema
     * @param tableName the table name
     * @param ifExists  the if exists
     * @return the string
     */
    default String buildDropTableDDL(String schema, String tableName, boolean ifExists) {
        return buildDropTableDDL(schema, tableName, ifExists, false);
    }

    /**
     * Builds the drop table sql.
     *
     * @param schema    the database schema
     * @param tableName the table name
     * @param ifExists  the if exists
     * @param cascade   the cascade
     * @return the string
     */
    default String buildDropTableDDL(String schema, String tableName, boolean ifExists, boolean cascade) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        String tn = Lang.isEmpty(schema) ? wrapName(tableName) : wrapName(schema) + Chars.DOT + wrapName(tableName);
        String ddl = "";
        if (ifExists) {
            ddl = BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.TABLE), getKeyword(Keywords.IF),
                    getKeyword(Keywords.EXISTS), tn);
        } else {
            ddl = BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.TABLE), tn);
        }
        if (cascade) {
            ddl += Chars.SPACE + Keywords.CASCADE;
        }
        return ddl + Chars.SEMI;
    }

    /**
     * Builds the alter table sql.
     *
     * @param tableName the table name
     * @return the string
     */
    default String buildAlterTableDDL(String tableName) {
        return buildAlterTableDDL(null, tableName);
    }

    /**
     * Builds the alter table sql.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @return the string
     */
    default String buildAlterTableDDL(String schema, String tableName) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        if (Lang.isEmpty(schema)) {
            return BuilderUtils.link(getKeyword(Keywords.ALTER), getKeyword(Keywords.TABLE), wrapName(tableName));
        } else {
            return BuilderUtils.link(getKeyword(Keywords.ALTER), getKeyword(Keywords.TABLE),
                    wrapName(schema) + Chars.DOT + wrapName(tableName));
        }
    }

    /**
     * Builds the alter table DDL.
     *
     * @param schema        the database name
     * @param tableName     the table name
     * @param addColumns    the add columns
     * @param modifyColumns the modify columns
     * @param dropColumns   the drop columns
     * @return the string
     */
    String buildAlterTableDDL(String schema, String tableName, Column[] addColumns, Column[] modifyColumns,
            Column[] dropColumns);

    /**
     * Builds the alter table add column DDL.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    default String buildAlterTableAddColumnDDL(String tableName, Column... columns) {
        return buildAlterTableAddColumnDDL(null, tableName, columns);
    }

    /**
     * Builds the alter table add column DDL.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    String buildAlterTableAddColumnDDL(String schema, String tableName, Column... columns);

    /**
     * Builds the alter table modify column DDL.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    default String buildAlterTableModifyColumnDDL(String tableName, Column... columns) {
        return buildAlterTableModifyColumnDDL(null, tableName, columns);
    }

    /**
     * Builds the alter table modify column DDL.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    String buildAlterTableModifyColumnDDL(String schema, String tableName, Column... columns);

    /**
     * Builds the alter table drop column DDL.
     *
     * @param column the column
     * @return the string
     */
    default String buildAlterTableDropColumnDDL(Column column) {
        return buildAlterTableDropColumnDDL(null, column.getName(), column);
    }

    /**
     * Builds the alter table drop column DDL.
     *
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    default String buildAlterTableDropColumnDDL(String tableName, Column... columns) {
        return buildAlterTableDropColumnDDL(null, tableName, columns);
    }

    /**
     * Builds the alter table drop column DDL.
     *
     * @param schema    the database name
     * @param tableName the table name
     * @param columns   the columns
     * @return the string
     */
    String buildAlterTableDropColumnDDL(String schema, String tableName, Column... columns);

    /**
     * Builds the alter table drop column DDL.
     *
     * @param schema      the database name
     * @param tableName   the table name
     * @param columnNames the column names
     * @return the string
     */
    String buildAlterTableDropColumnDDL(String schema, String tableName, String... columnNames);

    /**
     * Builds the drop view sql.
     *
     * @param viewName the view name
     * @return the string
     */
    default String buildDropViewDDL(String viewName) {
        return buildDropViewDDL(null, viewName);
    }

    /**
     * Builds the drop view sql.
     *
     * @param schema   the database name
     * @param viewName the view name
     * @return the string
     */
    default String buildDropViewDDL(String schema, String viewName) {
        return buildDropViewDDL(schema, viewName, false);
    }

    /**
     * Builds the drop view sql.
     *
     * @param schema   the database name
     * @param viewName the view name
     * @param ifExists the if exists
     * @return the string
     */
    default String buildDropViewDDL(String schema, String viewName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(viewName, "viewName");
        return buildDropDDL(schema, viewName, Keywords.VIEW, ifExists);
    }

    /**
     * Builds the drop DDL.
     *
     * @param schema   the schema
     * @param name     the name
     * @param type     the type
     * @param ifExists the if exists
     * @return the string
     */
    default String buildDropDDL(String schema, String name, Keywords type, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(name, "name");
        AssertIllegalArgument.isNotEmpty(type, "type");
        String tn = Lang.isEmpty(schema) ? wrapName(name) : wrapName(schema) + Chars.DOT + wrapName(name);
        if (ifExists) {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(type), getKeyword(Keywords.IF),
                    getKeyword(Keywords.EXISTS), tn) + Chars.SEMI;
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(type), tn) + Chars.SEMI;
        }
    }

    /**
     * Builds the create index DDL.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @return the string
     */
    default String buildCreateIndexDDL(String tableName, String indexName, String[] columns) {
        return buildCreateIndexDDL(tableName, indexName, columns, false);
    }

    /**
     * Builds the create index DDL.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @param unique    the unique
     * @return the string
     */
    default String buildCreateIndexDDL(String tableName, String indexName, String[] columns, boolean unique) {
        return buildCreateIndexDDL(null, tableName, indexName, columns, unique);
    }

    /**
     * Builds the create index DDL.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @return the string
     */
    default String buildCreateIndexDDL(String schema, String tableName, String indexName, String[] columns) {
        return buildCreateIndexDDL(schema, tableName, indexName, columns, false);
    }

    /**
     * Builds the create index DDL.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param columns   the columns
     * @param unique    the unique
     * @return the string
     */
    default String buildCreateIndexDDL(String schema, String tableName, String indexName, String[] columns,
            boolean unique) {
        AssertIllegalArgument.isNotEmpty(columns, "columns");
        StringBuilder ddl = new StringBuilder();
        String tn = Lang.isEmpty(schema) ? wrapName(tableName) : wrapName(schema) + Chars.DOT + wrapName(tableName);
        StringBuilder cols = new StringBuilder();
        cols.append(Chars.PAREN_L);
        for (String column : columns) {
            cols.append(wrapName(column)).append(Chars.COMMA);
        }
        cols.deleteCharAt(cols.length() - 1);
        cols.append(Chars.PAREN_R);
        String indexKeyWords = unique ? getKeyword(Keywords.UNIQUE) + Chars.SPACE + getKeyword(Keywords.INDEX)
                : getKeyword(Keywords.INDEX);
        BuilderUtils.link(ddl, getKeyword(Keywords.CREATE), indexKeyWords, indexName, getKeyword(Keywords.ON),
                tn + cols.toString());
        return ddl.toString() + Chars.SEMI;
    }

    /**
     * Builds the drop index sql.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @return the string
     */
    default String buildDropIndexDDL(String tableName, String indexName) {
        return buildDropIndexDDL(null, tableName, indexName);
    }

    /**
     * Builds the drop index sql.
     *
     * @param tableName the table name
     * @param indexName the index name
     * @param ifExists  the if exists
     * @return the string
     */
    default String buildDropIndexDDL(String tableName, String indexName, boolean ifExists) {
        return buildDropIndexDDL(null, tableName, indexName, ifExists);
    }

    /**
     * Builds the drop index sql.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @return the string
     */
    default String buildDropIndexDDL(String schema, String tableName, String indexName) {
        return buildDropIndexDDL(schema, tableName, indexName, false);
    }

    /**
     * Builds the drop index sql.
     *
     * @param schema    the schema
     * @param tableName the table name
     * @param indexName the index name
     * @param ifExists  the if exists
     * @return the string
     */
    default String buildDropIndexDDL(String schema, String tableName, String indexName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(indexName, "indexName");
        //        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        String name = Lang.isEmpty(schema) ? wrapName(indexName) : wrapName(schema) + Chars.DOT + wrapName(indexName);
        if (ifExists) {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.INDEX), getKeyword(Keywords.IF),
                    getKeyword(Keywords.EXISTS), name) + Chars.SEMI;
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DROP), getKeyword(Keywords.INDEX), name) + Chars.SEMI;
        }
    }

    /**
     * Gets the column type name.
     *
     * @param sqlType the sql type
     * @return the column type name
     */
    default String getColumnTypeName(SQLType sqlType) {
        return sqlType.getName();
    }

    /**
     * Gets the default schema.
     *
     * @param catalog the catalog
     * @return the default schema
     */
    default String getDefaultSchema(String catalog) {
        return "public";
    }

    /**
     * Gets the default size.
     *
     * @param sqlType the sql type
     * @return the default size
     */
    default int getDefaultSize(SQLType sqlType) {
        if (sqlType == JDBCType.BIGINT) {
            return 19;
        } else if (sqlType == JDBCType.INTEGER) {
            return 10;
        } else if (sqlType == JDBCType.SMALLINT) {
            return 5;
        } else if (sqlType == JDBCType.TINYINT) {
            return 3;
        } else if (sqlType == JDBCType.TIMESTAMP) {
            return 19;
        } else if (sqlType == JDBCType.DATE) {
            return 10;
        } else if (sqlType == JDBCType.TIME) {
            return 8;
        } else {
            return 0;
        }
    }

    /**
     * Gets the primary key index name.
     *
     * @return the primary key index name
     */
    default String getPrimaryKeyIndexName() {
        return PRIMARY_KEY_INDEX_NAME;
    }

    /**
     * Gets the wrap sign.
     *
     * @return the wrap sign
     * @deprecated {@link #getWrapSymbol()}
     */
    @Deprecated
    default String getWrapSign() {
        return getWrapSymbol();
    }

    /**
     * Gets the wrap symbol.
     *
     * @return the wrap symbol
     */
    String getWrapSymbol();

    /**
     * The Class Keyworld.
     *
     * @author zhongj
     */
    public static class Keyworld {

        /** The dialect. */
        private Dialect dialect;

        /**
         * Instantiates a new keyworld.
         *
         * @param dialect the dialect
         */
        Keyworld(Dialect dialect) {
            this.dialect = dialect;
        }

        /**
         * Select.
         *
         * @return the string
         */
        public String select() {
            return dialect.getKeyword(Keywords.SELECT);
        }

        /**
         * From.
         *
         * @return the string
         */
        public String from() {
            return dialect.getKeyword(Keywords.FROM);
        }

        /**
         * Where.
         *
         * @return the string
         */
        public String where() {
            return dialect.getKeyword(Keywords.WHERE);
        }

        /**
         * Update.
         *
         * @return the string
         */
        public String update() {
            return dialect.getKeyword(Keywords.UPDATE);
        }

        /**
         * Sets the.
         *
         * @return the string
         */
        public String set() {
            return dialect.getKeyword(Keywords.SET);
        }

        /**
         * Delete.
         *
         * @return the string
         */
        public String delete() {
            return dialect.getKeyword(Keywords.DELETE);
        }

        /**
         * Delete from.
         *
         * @return the string
         */
        public String deleteFrom() {
            return delete() + Chars.SPACE + from();
        }

        /**
         * Insert.
         *
         * @return the string
         */
        public String insert() {
            return dialect.getKeyword(Keywords.INSERT);
        }

        /**
         * Into.
         *
         * @return the string
         */
        public String into() {
            return dialect.getKeyword(Keywords.INTO);
        }

        /**
         * Values.
         *
         * @return the string
         */
        public String values() {
            return dialect.getKeyword(Keywords.VALUES);
        }

        /**
         * Join.
         *
         * @return the string
         */
        public String join() {
            return dialect.getKeyword(Keywords.JOIN);
        }

        /**
         * Join.
         *
         * @param join the join
         * @return the string
         */
        public String join(Join join) {
            switch (join) {
                case INNER_JOIN:
                    return join();
                case LEFT_JOIN:
                    return left() + Chars.SPACE + join();
                case RIGHT_JOIN:
                    return right() + Chars.SPACE + join();
                case FULL_JOIN:
                    return full() + Chars.SPACE + join();
                default:
                    return join();
            }
        }

        /**
         * On.
         *
         * @return the string
         */
        public String on() {
            return dialect.getKeyword(Keywords.ON);
        }

        /**
         * Inner.
         *
         * @return the string
         */
        public String inner() {
            return dialect.getKeyword(Keywords.INNER);
        }

        /**
         * Left.
         *
         * @return the string
         */
        public String left() {
            return dialect.getKeyword(Keywords.LEFT);
        }

        /**
         * Right.
         *
         * @return the string
         */
        public String right() {
            return dialect.getKeyword(Keywords.RIGHT);
        }

        /**
         * Creates the.
         *
         * @return the string
         */
        public String create() {
            return dialect.getKeyword(Keywords.CREATE);
        }

        /**
         * Drop.
         *
         * @return the string
         */
        public String drop() {
            return dialect.getKeyword(Keywords.DROP);
        }

        /**
         * After.
         *
         * @return the string
         */
        public String after() {
            return dialect.getKeyword(Keywords.AFTER);
        }

        /**
         * Table.
         *
         * @return the string
         */
        public String table() {
            return dialect.getKeyword(Keywords.TABLE);
        }

        /**
         * Adds the.
         *
         * @return the string
         */
        public String add() {
            return dialect.getKeyword(Keywords.ADD);
        }

        /**
         * Truncate.
         *
         * @return the string
         */
        public String truncate() {
            return dialect.getKeyword(Keywords.TRUNCATE);
        }

        /**
         * Outer.
         *
         * @return the string
         */
        public String outer() {
            return dialect.getKeyword(Keywords.OUTER);
        }

        /**
         * Full.
         *
         * @return the string
         */
        public String full() {
            return dialect.getKeyword(Keywords.FULL);
        }

        /**
         * Cross.
         *
         * @return the string
         */
        public String cross() {
            return dialect.getKeyword(Keywords.CORSS);
        }

        /**
         * Order.
         *
         * @return the string
         */
        public String order() {
            return dialect.getKeyword(Keywords.ORDER);
        }

        /**
         * By.
         *
         * @return the string
         */
        public String by() {
            return dialect.getKeyword(Keywords.BY);
        }

        /**
         * Order by.
         *
         * @return the string
         */
        public String orderBy() {
            return order() + Chars.SPACE + by();
        }

        /**
         * Desc.
         *
         * @return the string
         */
        public String desc() {
            return dialect.getKeyword(Keywords.DESC);
        }

        /**
         * Asc.
         *
         * @return the string
         */
        public String asc() {
            return dialect.getKeyword(Keywords.ASC);
        }

        /**
         * In.
         *
         * @return the string
         */
        public String in() {
            return dialect.getKeyword(Keywords.IN);
        }

        /**
         * Checks if is.
         *
         * @return the string
         */
        public String is() {
            return dialect.getKeyword(Keywords.IS);
        }

        /**
         * Checks if is null.
         *
         * @return the string
         */
        public String isNull() {
            return is() + Chars.SPACE + nullText();
        }

        /**
         * Checks if is not null.
         *
         * @return the string
         */
        public String isNotNull() {
            return is() + Chars.SPACE + not() + Chars.SPACE + nullText();
        }

        /**
         * Like.
         *
         * @return the string
         */
        public String like() {
            return like(MatchStrategy.AUTO);
        }

        /**
         * Like.
         *
         * @param matchStrategy the like query policy
         * @return the string
         */
        public String like(MatchStrategy matchStrategy) {
            return dialect.getKeywordLike(matchStrategy);
        }

        /**
         * not like.
         *
         * @return the string
         */
        public String notLike() {
            return notLike(MatchStrategy.AUTO);
        }

        /**
         * Like.
         *
         * @param matchStrategy the like query policy
         * @return the string
         */
        public String notLike(MatchStrategy matchStrategy) {
            return dialect.getKeywordNotLike(matchStrategy);
        }

        /**
         * Null text.
         *
         * @return the string
         */
        public String nullText() {
            return dialect.getKeyword(Keywords.NULL);
        }

        /**
         * Not.
         *
         * @return the string
         */
        public String not() {
            return dialect.getKeyword(Keywords.NOT);
        }

        /**
         * Not in.
         *
         * @return the string
         */
        public String notIn() {
            return not() + Chars.SPACE + in();
        }

        /**
         * Between.
         *
         * @return the string
         */
        public String between() {
            return dialect.getKeyword(Keywords.BETWEEN);
        }

        /**
         * Union.
         *
         * @return the string
         */
        public String union() {
            return dialect.getKeyword(Keywords.UNION);
        }

        /**
         * Intersect.
         *
         * @return the string
         */
        public String intersect() {
            return dialect.getKeyword(Keywords.INTERSECT);
        }

        /**
         * Except.
         *
         * @return the string
         */
        public String except() {
            return dialect.getKeyword(Keywords.EXCEPT);
        }

        /**
         * And.
         *
         * @return the string
         */
        public String and() {
            return dialect.getKeyword(Keywords.AND);
        }

        /**
         * Or.
         *
         * @return the string
         */
        public String or() {
            return dialect.getKeyword(Keywords.OR);
        }

        /**
         * All.
         *
         * @return the string
         */
        public String all() {
            return dialect.getKeyword(Keywords.ALL);
        }

        /**
         * As.
         *
         * @return the string
         */
        public String as() {
            return dialect.getKeyword(Keywords.AS);
        }

        /**
         * Count.
         *
         * @return the string
         */
        public String count() {
            return dialect.getKeyword(Keywords.COUNT);
        }

        /**
         * Sum.
         *
         * @return the string
         */
        public String sum() {
            return dialect.getKeyword(Keywords.SUM);
        }

        /**
         * Max.
         *
         * @return the string
         */
        public String max() {
            return dialect.getKeyword(Keywords.MAX);
        }

        /**
         * Min.
         *
         * @return the string
         */
        public String min() {
            return dialect.getKeyword(Keywords.MIN);
        }

        /**
         * Avg.
         *
         * @return the string
         */
        public String avg() {
            return dialect.getKeyword(Keywords.AVG);
        }

        /**
         * Distinct.
         *
         * @return the string
         */
        public String distinct() {
            return dialect.getKeyword(Keywords.DISTINCT);
        }

        /**
         * View.
         *
         * @return the string
         */
        public String view() {
            return dialect.getKeyword(Keywords.VIEW);
        }

        /**
         * Index.
         *
         * @return the string
         */
        public String index() {
            return dialect.getKeyword(Keywords.INDEX);
        }

        /**
         * Default.
         *
         * @return the string
         */
        public String default_() {
            return dialect.getKeyword(Keywords.DEFAULT);
        }

        /**
         * Alter.
         *
         * @return the string
         */
        public String alter() {
            return dialect.getKeyword(Keywords.ALTER);
        }

        /**
         * Column.
         *
         * @return the string
         */
        public String column() {
            return dialect.getKeyword(Keywords.COLUMN);
        }

        /**
         * If.
         *
         * @return the string
         */
        public String if_() {
            return dialect.getKeyword(Keywords.IF);
        }

        /**
         * Exists.
         *
         * @return the string
         */
        public String exists() {
            return dialect.getKeyword(Keywords.EXISTS);
        }

        /**
         * Comment.
         *
         * @return the string
         */
        public String comment() {
            return dialect.getKeyword(Keywords.COMMENT);
        }

        /**
         * Primary.
         *
         * @return the string
         */
        public String primary() {
            return dialect.getKeyword(Keywords.PRIMARY);
        }

        /**
         * Key.
         *
         * @return the string
         */
        public String key() {
            return dialect.getKeyword(Keywords.KEY);
        }

        /**
         * Constraint.
         *
         * @return the string
         */
        public String constraint() {
            return dialect.getKeyword(Keywords.CONSTRAINT);
        }

        /**
         * cascade.
         *
         * @return the string
         */
        public String cascade() {
            return dialect.getKeyword(Keywords.CASCADE);
        }

        /**
         * Collate.
         *
         * @return the string
         */
        public String collate() {
            return dialect.getKeyword(Keywords.COLLATE);
        }

        /**
         * Binary.
         *
         * @return the string
         */
        public String binary() {
            return dialect.getKeyword(Keywords.BINARY);
        }
    }

    /**
     * The Class Operator.
     *
     * @author zhongj
     */
    public static class Operator {

        /**
         * Instantiates a new keyworld.
         *
         * @param dialect the dialect
         */
        Operator() {
        }

        /**
         * equals.
         *
         * @return the string
         */
        public String eq() {
            return "=";
        }

        /**
         * not equals.
         *
         * @return the string
         */
        public String ne() {
            return "!=";
        }

        /**
         * great equals.
         *
         * @return the string
         */
        public String ge() {
            return ">=";
        }

        /**
         * great than.
         *
         * @return the string
         */
        public String gt() {
            return ">";
        }

        /**
         * less equals.
         *
         * @return the string
         */
        public String le() {
            return "<=";
        }

        /**
         * less than.
         *
         * @return the string
         */
        public String lt() {
            return "<";
        }
    }
}