
package cn.featherfly.common.db.data.query;

import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.dml.SqlConditionGroup;
import cn.featherfly.common.db.builder.dml.SqlSortBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Query;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * <p>
 * TableQuery
 * </p>
 *
 * @author zhongj
 */
public class TableQuery implements Query {

    /**
     * @param dialect   dialect
     * @param tableName tableName
     */
    public TableQuery(Dialect dialect, String tableName) {
        this(dialect, tableName, null);
    }

    /**
     * @param dialect          dialect
     * @param tableName        tableName
     * @param conditionBuilder ConditionBuilder
     */
    public TableQuery(Dialect dialect, String tableName, ConditionBuilder conditionBuilder) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(dialect.buildTableSql(tableName));
        if (conditionBuilder != null) {
            String condition = conditionBuilder.build();
            if (Lang.isNotEmpty(condition)) {
                BuilderUtils.link(sql, dialect.getKeyword(Keywords.WHERE), condition);
            }
        }
        this.sql = sql.toString();
        this.tableName = tableName;
    }

    public static void main(String[] args) {
        ConditionBuilder builder = new SqlConditionGroup(Dialects.MYSQL, new SqlSortBuilder(Dialects.MYSQL));
        builder.eq("name", "yufei").and().gt("age", 18);
        TableQuery q = new TableQuery(Dialects.MYSQL, "user", builder);
        System.out.println(q.sql);
    }

    private String tableName;

    private String sql;

    private Object[] params = new Object[0];

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return tableName;
    }

    public String getSql() {
        return sql;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getParams() {
        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExecution() {
        return sql;
    }
}
