
package cn.featherfly.common.db.builder.dml.basic;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * SqlUpdateSetBasicBuilder
 * </p>
 *
 * @author zhongj
 */
public class SqlDeleteFromBasicBuilder implements SqlBuilder {

    private String tableName;

    private Dialect dialect;

    public SqlDeleteFromBasicBuilder(Dialect dialect) {
        this(dialect, null);
    }

    public SqlDeleteFromBasicBuilder(Dialect dialect, String tableName) {
        this.tableName = tableName;
        this.dialect = dialect;
    }

    /**
     * 设置tableName
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 返回tableName
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder deleteSql = new StringBuilder();
        return deleteSql.append(dialect.getKeywords().deleteFrom()).append(Chars.SPACE)
                .append(dialect.buildTableSql(tableName)).toString();
    }

}
