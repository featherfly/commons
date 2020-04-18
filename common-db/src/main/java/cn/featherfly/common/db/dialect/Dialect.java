package cn.featherfly.common.db.dialect;

import java.util.Map;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.repository.operate.AggregateFunction;
import cn.featherfly.common.repository.operate.Function;
import cn.featherfly.common.repository.operate.LogicOperator;
import cn.featherfly.common.repository.operate.SortOperator;

/**
 * <p>
 * database dialect.
 * </p>
 *
 * @author zhongj
 */
public interface Dialect {
    /**
     * 命名参数查询的查询条件默认起始位置名称
     */
    String START_PARAM_NAME = "dialect_paging_start";
    /**
     * 命名参数查询的查询条件默认数量名称
     */
    String LIMIT_PARAM_NAME = "dialect_paging_limit";
    /**
     * 默认的查询返回条数
     */
    int DEFAULT_LIMIT = 10;

    /**
     * <p>
     * 转换普通sql为带分页的sql
     * </p>
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
     *
     * @param sql   带转换的sql
     * @param start 起始数
     * @param limit 数量
     * @return 返回转换好的分页sql
     */
    String getParamNamedPaginationSql(String sql, int start, int limit);

    /**
     * <p>
     * 转换为SQL语句中使用的字符串
     * </p>
     *
     * @param value   值
     * @param sqlType sql类型
     */
    String valueToSql(Object value, int sqlType);

    /**
     * <p>
     * 包装名称，避免关键字问题
     * </p>
     *
     * @param name 名称（列明，表名等）
     * @return 包装后的名称
     */
    String wrapName(String name);

    /**
     * <p>
     * 返回设值外检检查SQL语句
     * </p>
     *
     * @param check 是否检查外检
     * @return 设值外检检查SQL语句
     */
    String getFkCheck(boolean check);

    default boolean isKeywordsUppercase() {
        return true;
    }

    default boolean isTableAndColumnNameUppercase() {
        return false;
    }

    /**
     * get converted keywords
     *
     * @return sql key words
     */
    default Keyworld getKeywords() {
        return new Keyworld(this);
    }

    /**
     * get converted keywords
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(SortOperator keywords) {
        if (isKeywordsUppercase()) {
            return keywords.toString();
        } else {
            return keywords.toString().toLowerCase();
        }
    }

    /**
     * get converted keywords
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(LogicOperator keywords) {
        if (isKeywordsUppercase()) {
            return keywords.toString();
        } else {
            return keywords.toString().toLowerCase();
        }
    }

    /**
     * get converted keywords
     *
     * @param keywords sql keywords
     * @return sql key words
     */
    default String getKeyword(Keywords keywords) {
        if (isKeywordsUppercase()) {
            return keywords.toString();
        } else {
            return keywords.toString().toLowerCase();
        }
    }

    /**
     * get converted aggregate function
     *
     * @param function aggregate function
     * @return sql aggregate function
     */
    default String getFunction(Function function) {
        if (isKeywordsUppercase()) {
            return function.toString().toUpperCase();
        } else {
            return function.toString().toLowerCase();
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
     * build sql for column with aggregate function
     *
     * @param columnName columnName
     * @param function   function
     * @return sql
     */
    default String buildColumnSql(String columnName, Function function) {
        return buildColumnSql(columnName, null, function);
    }

    /**
     * build sql for column with aggregate function
     *
     * @param columnName        columnName
     * @param aggregateFunction aggregateFunction
     * @return sql
     */
    default String buildColumnSql(String columnName, AggregateFunction aggregateFunction) {
        return buildColumnSql(columnName, null, aggregateFunction);
    }

    /**
     * build sql for column with aggregate function
     *
     * @param columnName columnName
     * @param tableAlias tableAlias
     * @return sql
     */
    default String buildColumnSql(String columnName, String tableAlias) {
        return buildColumnSql(columnName, tableAlias, null, null);
    }

    /**
     * build sql for column with aggregate function
     *
     * @param columnName columnName
     * @param tableAlias tableAlias
     * @param asName     asName
     * @return sql
     */
    default String buildColumnSql(String columnName, String tableAlias, String asName) {
        return buildColumnSql(columnName, tableAlias, null, asName);
    }

    /**
     * build sql for column with tableAlias and aggregate function
     *
     * @param columnName        columnName
     * @param tableAlias        tableAlias
     * @param aggregateFunction aggregateFunction
     * @return sql
     */
    default String buildColumnSql(String columnName, String tableAlias, AggregateFunction aggregateFunction) {
        return buildColumnSql(columnName, tableAlias, aggregateFunction, null);
    }

    /**
     * build sql for column with tableAlias and aggregate function
     *
     * @param columnName        columnName
     * @param tableAlias        tableAlias
     * @param aggregateFunction aggregateFunction
     * @param asName            asName
     * @return sql
     */
    default String buildColumnSql(String columnName, String tableAlias, AggregateFunction aggregateFunction,
            String asName) {
        String column = columnName;
        if (!Chars.STAR.equals(columnName)) {
            column = wrapName(convertTableOrColumnName(columnName));
        }
        if (LangUtils.isNotEmpty(tableAlias) && !Chars.STAR.equals(columnName)) {
            column = tableAlias + Chars.DOT + column;
        }
        if (aggregateFunction != null) {
            switch (aggregateFunction) {
                case DISTINCT:
                    column = getFunction(aggregateFunction) + Chars.SPACE + column;
                    break;
                default:
                    column = getFunction(aggregateFunction) + Chars.PAREN_L + column + Chars.PAREN_R;
            }
        }
        if (LangUtils.isNotEmpty(asName)) {
            column = column + Chars.SPACE + wrapName(asName);
        }
        return column;
    }

    /**
     * build sql for column with tableAlias and aggregate function
     *
     * @param columnName columnName
     * @param tableAlias tableAlias
     * @param function   function
     * @return sql
     */
    default String buildColumnSql(String columnName, String tableAlias, Function function) {
        if (function instanceof AggregateFunction) {
            return buildColumnSql(columnName, tableAlias, function);
        }
        // TODO 后续添加其他实现
        throw new DialectException("只实现了 AggregateFunction，未实现的 function" + function.getClass().getName());
    }

    /**
     * convert column or table name if necessary
     *
     * @param tableOrColumnName column or table name
     * @return sql
     */
    default String convertTableOrColumnName(String tableOrColumnName) {
        if (LangUtils.isEmpty(tableOrColumnName)) {
            return tableOrColumnName;
        }
        String result = tableOrColumnName;
        if (isTableAndColumnNameUppercase()) {
            result = result.toUpperCase();
        } else {
            result = result.toLowerCase();
        }
        return result;
    }

    /**
     * build sql for table
     *
     * @param tableName tableName
     * @return sql
     */
    default String buildTableSql(String tableName) {
        return buildTableSql(tableName, null);
    }

    /**
     * build sql for table with tableAlias
     *
     * @param tableName  tableName
     * @param tableAlias tableAlias
     * @return sql
     */
    default String buildTableSql(String tableName, String tableAlias) {
        String result = wrapName(convertTableOrColumnName(tableName));
        if (LangUtils.isNotEmpty(tableAlias)) {
            result = result + " " + tableAlias;
        }
        return result;
    }

    String getWrapSign();

    public static class Keyworld {
        private Dialect dialect;

        /**
         * @param dialect
         */
        Keyworld(Dialect dialect) {
            this.dialect = dialect;
        }

        public String select() {
            return dialect.getKeyword(Keywords.SELECT);
        }

        public String from() {
            return dialect.getKeyword(Keywords.FROM);
        }

        public String where() {
            return dialect.getKeyword(Keywords.WHERE);
        }

        public String update() {
            return dialect.getKeyword(Keywords.UPDATE);
        }

        public String set() {
            return dialect.getKeyword(Keywords.SET);
        }

        public String delete() {
            return dialect.getKeyword(Keywords.DELETE);
        }

        public String deleteFrom() {
            return delete() + Chars.SPACE + from();
        }

        public String insert() {
            return dialect.getKeyword(Keywords.INSERT);
        }

        public String into() {
            return dialect.getKeyword(Keywords.INTO);
        }

        public String values() {
            return dialect.getKeyword(Keywords.VALUES);
        }

        public String join() {
            return dialect.getKeyword(Keywords.JOIN);
        }

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

        public String on() {
            return dialect.getKeyword(Keywords.ON);
        }

        public String inner() {
            return dialect.getKeyword(Keywords.INNER);
        }

        public String left() {
            return dialect.getKeyword(Keywords.LEFT);
        }

        public String right() {
            return dialect.getKeyword(Keywords.RIGHT);
        }

        public String create() {
            return dialect.getKeyword(Keywords.CREATE);
        }

        public String drop() {
            return dialect.getKeyword(Keywords.DROP);
        }

        public String after() {
            return dialect.getKeyword(Keywords.AFTER);
        }

        public String table() {
            return dialect.getKeyword(Keywords.TABLE);
        }

        public String add() {
            return dialect.getKeyword(Keywords.ADD);
        }

        public String truncate() {
            return dialect.getKeyword(Keywords.TRUNCATE);
        }

        public String outer() {
            return dialect.getKeyword(Keywords.OUTER);
        }

        public String full() {
            return dialect.getKeyword(Keywords.FULL);
        }

        public String cross() {
            return dialect.getKeyword(Keywords.CORSS);
        }

        public String order() {
            return dialect.getKeyword(Keywords.ORDER);
        }

        public String by() {
            return dialect.getKeyword(Keywords.BY);
        }

        public String orderBy() {
            return order() + Chars.SPACE + by();
        }

        public String desc() {
            return dialect.getKeyword(Keywords.DESC);
        }

        public String asc() {
            return dialect.getKeyword(Keywords.ASC);
        }

        public String in() {
            return dialect.getKeyword(Keywords.IN);
        }

        public String is() {
            return dialect.getKeyword(Keywords.IS);
        }

        public String isNull() {
            return is() + Chars.SPACE + nullText();
        }

        public String isNotNull() {
            return is() + Chars.SPACE + not() + Chars.SPACE + nullText();
        }

        public String like() {
            return dialect.getKeyword(Keywords.LIKE);
        }

        public String nullText() {
            return dialect.getKeyword(Keywords.NULL);
        }

        public String not() {
            return dialect.getKeyword(Keywords.NOT);
        }

        public String notIn() {
            return not() + Chars.SPACE + in();
        }

        public String between() {
            return dialect.getKeyword(Keywords.BETWEEN);
        }

        public String union() {
            return dialect.getKeyword(Keywords.UNION);
        }

        public String intersect() {
            return dialect.getKeyword(Keywords.INTERSECT);
        }

        public String except() {
            return dialect.getKeyword(Keywords.EXCEPT);
        }

        public String and() {
            return dialect.getKeyword(Keywords.AND);
        }

        public String or() {
            return dialect.getKeyword(Keywords.OR);
        }

        public String all() {
            return dialect.getKeyword(Keywords.ALL);
        }

        public String as() {
            return dialect.getKeyword(Keywords.AS);
        }

        public String count() {
            return dialect.getKeyword(Keywords.COUNT);
        }

        public String sum() {
            return dialect.getKeyword(Keywords.SUM);
        }

        public String max() {
            return dialect.getKeyword(Keywords.MAX);
        }

        public String min() {
            return dialect.getKeyword(Keywords.MIN);
        }

        public String avg() {
            return dialect.getKeyword(Keywords.AVG);
        }

        public String distinct() {
            return dialect.getKeyword(Keywords.DISTINCT);
        }
    }
}