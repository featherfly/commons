
package cn.featherfly.common.db.builder.dml.basic;

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
     * get tableAlias value
     *
     * @return tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * set tableAlias value
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return dialect.buildDeleteFromSql(tableName, tableAlias);
    }

}
