
package cn.featherfly.common.db.data.query;

import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Query;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * <p>
 * TableQuery
 * </p>
 * .
 *
 * @author zhongj
 */
public class TableQuery implements Query {

    /**
     * Instantiates a new table query.
     *
     * @param dialect   dialect
     * @param tableName tableName
     */
    public TableQuery(Dialect dialect, String tableName) {
        this(dialect, tableName, null);
    }

    /**
     * Instantiates a new table query.
     *
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
            params = (Object[]) conditionBuilder.getParamValue();
            if (Lang.isNotEmpty(condition)) {
                BuilderUtils.link(sql, dialect.getKeyword(Keywords.WHERE), condition);
            }
        }
        this.sql = sql.toString();
        this.tableName = tableName;
    }

    private String tableName;

    private String sql;

    private Object[] params = ArrayUtils.EMPTY_OBJECT_ARRAY;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return tableName;
    }

    /**
     * Gets the sql.
     *
     * @return the sql
     */
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
