
package cn.featherfly.common.db.builder.model;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * Column
 * </p>
 *
 * @author zhongj
 */
public class TableElement extends AbstractSqlElement {

    private String name;

    private String alias;

    /**
     * @param dialect dialect
     * @param name    name
     */
    public TableElement(Dialect dialect, String name) {
        this(dialect, name, null);
    }

    /**
     * @param dialect dialect
     * @param name    name
     * @param alias   alias
     */
    public TableElement(Dialect dialect, String name, String alias) {
        super(dialect);
        this.name = name;
        this.alias = alias;
    }

    /**
     * 返回name
     *
     * @return name
     */
    public String getName() {
        return name;
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
     * 返回tableAlias
     *
     * @return tableAlias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置tableAlias
     *
     * @param alias tableAlias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        return dialect.buildTableSql(name, alias);
    }
}
