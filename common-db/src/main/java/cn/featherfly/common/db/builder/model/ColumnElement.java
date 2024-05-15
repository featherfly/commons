
package cn.featherfly.common.db.builder.model;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * Column
 * </p>
 * .
 *
 * @author zhongj
 */
public class ColumnElement extends AbstractSqlElement {

    /** The name. */
    protected String name;

    /** The table alias. */
    protected String tableAlias;

    /**
     * Instantiates a new column element.
     *
     * @param dialect dialect
     * @param name    name
     */
    public ColumnElement(Dialect dialect, String name) {
        this(dialect, name, null);
    }

    /**
     * Instantiates a new column element.
     *
     * @param dialect    dialect
     * @param name       name
     * @param tableAlias tableAlias
     */
    public ColumnElement(Dialect dialect, String name, String tableAlias) {
        super(dialect);
        this.name = name;
        this.tableAlias = tableAlias;
    }

    /**
     * 返回name.
     *
     * @return name
     */
    public String getName() {
        return name;
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
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置tableAlias
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
    public String toSql() {
        return dialect.dml().column(getTableAlias(), getName());
    }
}
