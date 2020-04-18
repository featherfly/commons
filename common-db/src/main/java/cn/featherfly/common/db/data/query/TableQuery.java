
package cn.featherfly.common.db.data.query;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.repository.Query;

/**
 * <p>
 * TableQuery
 * </p>
 *
 * @author zhongj
 */
public class TableQuery implements Query {

    /**
     * @param tableName tableName
     */
    public TableQuery(String tableName) {
        //	    this(tableName, null)
        AssertIllegalArgument.isNotEmpty(tableName, "tableName不能为空");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(tableName);
        this.sql = sql.toString();
        this.tableName = tableName;
    }
    //	/**
    //	 * @param tableName tableName
    //	 * @param conditionBuilder ConditionBuilder
    //	 */
    //	public TableQuery(String tableName, ConditionBuilder conditionBuilder){
    //		AssertIllegalArgument.isNotEmpty(tableName, "tableName不能为空");
    //		StringBuilder sql = new StringBuilder();
    //		sql.append("select * from ").append(tableName);
    //		if (conditionBuilder != null) {
    //			conditionBuilder.setBuildWithWhere(true);
    //			String condition = conditionBuilder.build();
    //			if (condition != null) {
    //				sql.append(" ").append(condition);
    //			}
    //		}
    //		this.sql = sql.toString();
    //		this.tableName = tableName;
    //	}

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
