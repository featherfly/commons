
package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.dialect.Dialect;

/**
 * SqlUpdateSetBasicBuilder. .
 *
 * @author zhongj
 */
public class SqlDeleteFromBasicBuilder implements SqlBuilder {

    private String tableName;

    private String tableAlias;

    private Dialect dialect;

    private List<SqlJoinOnBuilder> sqlJoinOnBuilders;

    /**
     * Instantiates a new sql delete from basic builder.
     *
     * @param dialect the dialect
     */
    public SqlDeleteFromBasicBuilder(Dialect dialect) {
        this(dialect, null);
    }

    /**
     * Instantiates a new sql delete from basic builder.
     *
     * @param dialect   the dialect
     * @param tableName the table name
     */
    public SqlDeleteFromBasicBuilder(Dialect dialect, String tableName) {
        this(dialect, tableName, null);
    }

    /**
     * Instantiates a new sql delete from basic builder.
     *
     * @param dialect    the dialect
     * @param tableName  the table name
     * @param tableAlias the table alias
     */
    public SqlDeleteFromBasicBuilder(Dialect dialect, String tableName, String tableAlias) {
        this.tableName = tableName;
        this.dialect = dialect;
        this.tableAlias = tableAlias;
        sqlJoinOnBuilders = new ArrayList<>(0);
    }

    /**
     * 设置tableName.
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 返回tableName.
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * get tableAlias value.
     *
     * @return tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * set tableAlias value.
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * Adds the sql join on builder.
     *
     * @param sqlJoinOnBuilder the sql join on builder
     * @return the sql delete from basic builder
     */
    public SqlDeleteFromBasicBuilder join(SqlJoinOnBuilder sqlJoinOnBuilder) {
        sqlJoinOnBuilders.add(sqlJoinOnBuilder);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        if (sqlJoinOnBuilders.isEmpty()) {
            return dialect.buildDeleteFromSql(tableName, tableAlias);
        } else {
            StringBuilder sql = new StringBuilder();
            sql.append(dialect.buildDeleteFromSql(tableName, tableAlias));
            for (SqlJoinOnBuilder sqlJoinOnBuilder : sqlJoinOnBuilders) {
                sql.append(Chars.SPACE_CHAR).append(sqlJoinOnBuilder.build());
            }
            return sql.toString();
        }
    }

}
