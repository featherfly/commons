package cn.featherfly.common.db.builder.dml;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.dml.basic.SqlOrderByBasicBuilder;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.repository.Field;
import cn.featherfly.common.repository.builder.dml.SortBuilder;

/**
 * sql sort builder.
 *
 * @author zhongj
 */
public class SqlSortBuilder implements SortBuilder, SqlBuilder {

    private SqlOrderByBasicBuilder orderByBuilder;

    private String tableAlias;

    /**
     * Instantiates a new sql sort builder.
     *
     * @param dialect the dialect
     */
    public SqlSortBuilder(Dialect dialect) {
        this(dialect, null);
    }

    /**
     * Instantiates a new sql sort builder.
     *
     * @param dialect    the dialect
     * @param tableAlias the table alias
     */
    public SqlSortBuilder(Dialect dialect, String tableAlias) {
        orderByBuilder = new SqlOrderByBasicBuilder(dialect);
        this.tableAlias = tableAlias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortBuilder asc(String... names) {
        for (String name : names) {
            orderByBuilder.addAsc(name, tableAlias);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortBuilder asc(String tableAlias, Field field) {
        orderByBuilder.addAsc(field.name(), tableAlias);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortBuilder desc(String... names) {
        for (String name : names) {
            orderByBuilder.addDesc(name, tableAlias);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortBuilder desc(String tableAlias, Field field) {
        orderByBuilder.addDesc(field.name(), tableAlias);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return orderByBuilder.build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getName() + " : " + build();
    }

    /**
     * 返回tableAlias.
     *
     * @return tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 设置tableAlias.
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

}