package cn.featherfly.common.db.builder.dml.basic;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Join;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlJoinOnBasicBuilder2 implements SqlJoinOnBuilder {

    /** The table alias. */
    protected String tableAlias;

    /** The table name. */
    protected String tableName;

    /** The dialect. */
    protected Dialect dialect;

    /** The join. */
    protected Join join;

    /** The on sql. */
    protected String onSql;

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect    dialect
     * @param tableName  tableName
     * @param tableAlias alias
     * @param onSql      the on sql
     */
    public SqlJoinOnBasicBuilder2(Dialect dialect, String tableName, String tableAlias, String onSql) {
        this(dialect, null, tableName, tableAlias, onSql);
    }

    /**
     * Instantiates a new sql join on basic builder.
     *
     * @param dialect    dialect
     * @param join       join type
     * @param tableName  tableName
     * @param tableAlias alias
     * @param onSql      the on sql
     */
    public SqlJoinOnBasicBuilder2(Dialect dialect, Join join, String tableName, String tableAlias, String onSql) {
        this.dialect = dialect;
        if (join == null) {
            this.join = Join.INNER_JOIN;
        } else {
            this.join = join;
        }
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.onSql = onSql;
    }

    /**
     * 返回alias.
     *
     * @return alias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 设置alias.
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
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
     * 设置tableName.
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return new StringBuilder().append(dialect.getKeywords().join(join)).append(Chars.SPACE)
            .append(dialect.buildTableSql(tableName, tableAlias)).append(Chars.SPACE).append(dialect.getKeywords().on())
            .append(Chars.SPACE).append(onSql).toString();
    }
}
