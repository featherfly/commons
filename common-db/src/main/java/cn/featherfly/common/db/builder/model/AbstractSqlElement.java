
package cn.featherfly.common.db.builder.model;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * SqlElement
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class AbstractSqlElement implements SqlElement {

    /** The dialect. */
    protected Dialect dialect;

    /**
     * Instantiates a new abstract sql element.
     *
     * @param dialect dialect
     */
    public AbstractSqlElement(Dialect dialect) {
        super();
        this.dialect = dialect;
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return toSql();
    }
}
