package cn.featherfly.common.db.dialect;

import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Map;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.Function;
import cn.featherfly.common.operator.LogicOperator;
import cn.featherfly.common.operator.SortOperator;
import cn.featherfly.common.repository.id.IdGenerator;

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
     * Gets the database name.
     *
     * @return the database name
     */
    String getDatabaseName();

    /**
     * ddl feature.
     *
     * @return the DDL feature
     */
    DDLFeature ddl();

    /**
     * dml feature.
     *
     * @return the DML feature
     */
    DMLFeature dml();

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
     * @param sql 带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @return 返回转换好的分页sql
     */
    String getPaginationSql(String sql, int start, int limit);

    /**
     * 返回分页参数的数组.
     *
     * @param params 参数数组
     * @param start 起始数
     * @param limit 数量
     * @return 分页参数的数组
     */
    Serializable[] getPaginationSqlParameter(Serializable[] params, int start, int limit);

    /**
     * 返回分页参数的MAP.
     *
     * @param params 参数MAP
     * @param start 起始数
     * @param limit 数量
     * @return 分页参数的MAP
     */
    Map<String, Serializable> getPaginationSqlParameter(Map<String, Serializable> params, int start, int limit);

    /**
     * 转换普通命名sql为带分页的sql,此sql为带命名参数sql, 如select * from user where user_name = :username .
     *
     * @param sql 带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @return 返回转换好的分页sql
     */
    String getNamedParamPaginationSql(String sql, int start, int limit);

    /**
     * 转换普通命名sql为带分页的sql,此sql为带命名参数sql, 如select * from user where user_name = {startSymal}username..
     *
     * @param sql 带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @param startSymbol 命名参数的起始符号
     * @return 返回转换好的分页sql
     */
    String getNamedParamPaginationSql(String sql, int start, int limit, char startSymbol);

    /**
     * 转换为SQL语句中使用的字符串 .
     *
     * @param value 值
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
     * Supports batch updates.
     *
     * @param metaData the meta data
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    default boolean supportBatchUpdates(DatabaseMetaData metaData) throws SQLException {
        return metaData.supportsBatchUpdates();
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
     * Checks if is table and column name to uppercase or lowercase or do nothing.
     *
     * @return true, if is table and column name uppercase
     */
    default StringCase keywordsCase() {
        return StringCase.NONE;
    }

    /**
     * Checks if is table and column name to uppercase or lowercase or do nothing.
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
     * @see #getKeywords()
     */
    default Keyworld keywords() {
        return getKeywords();
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
     * @param matchStrategy the match strategy
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
     * Gets the wrap symbol.
     *
     * @return the wrap symbol
     * @throws DialectException when wrap with {@linkplain #getLeftWrapSymbol() left} and
     *         {@linkplain #getRightWrapSymbol() right} different symbol.
     */
    String getWrapSymbol();

    /**
     * Gets the wrap symbol.
     *
     * @return the wrap symbol
     */
    default String getLeftWrapSymbol() {
        return getWrapSymbol();
    }

    /**
     * Gets the wrap symbol.
     *
     * @return the wrap symbol
     */
    default String getRightWrapSymbol() {
        return getWrapSymbol();
    }

    /**
     * Gets the default id generator.
     *
     * @param table the table
     * @param column the column
     * @return the id generator
     */
    IdGenerator getIdGenerator(String table, String column);

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
        public String if0() {
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