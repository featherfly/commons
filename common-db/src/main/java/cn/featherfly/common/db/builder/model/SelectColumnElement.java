
package cn.featherfly.common.db.builder.model;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * select column element .
 *
 * @author zhongj
 */
public class SelectColumnElement extends ColumnElement {

    /** The aggregate functions. */
    protected AggregateFunction aggregateFunction;

    /** The alias. */
    protected String alias;

    /** The distinct. */
    protected boolean distinct;

    /**
     * Instantiates a new select column element.
     *
     * @param element the element
     */
    public SelectColumnElement(SelectColumnElement element) {
        this(element.getDialect(), element.getAggregateFunction(), element.isDistinct(), element.getTableAlias(),
            element.getName(), element.getAlias());
    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param name name
     */
    public SelectColumnElement(Dialect dialect, String name) {
        this(dialect, false, name);
    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param distinct the distinct
     * @param name name
     */
    public SelectColumnElement(Dialect dialect, boolean distinct, String name) {
        this(dialect, null, false, name);
    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param aggregateFunction the aggregate function
     * @param distinct the distinct
     * @param name name
     */
    public SelectColumnElement(Dialect dialect, AggregateFunction aggregateFunction, boolean distinct, String name) {
        this(dialect, aggregateFunction, distinct, null, name, null);
    }

    //    /**
    //     * Instantiates a new select column element.
    //     *
    //     * @param dialect dialect
    //     * @param name    name
    //     * @param alias   the alias
    //     */
    //    public SelectColumnElement(Dialect dialect, String name, String alias) {
    //        this(dialect, null, null, name, alias);
    //    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param tableAlias table alias
     * @param name column name
     * @param alias the column alias
     */
    public SelectColumnElement(Dialect dialect, String tableAlias, String name, String alias) {
        this(dialect, null, tableAlias, name, alias);
    }

    //    /**
    //     * Instantiates a new select column element.
    //     *
    //     * @param dialect           dialect
    //     * @param aggregateFunction the aggregate function
    //     * @param name              name
    //     * @param alias             the alias
    //     */
    //    public SelectColumnElement(Dialect dialect, AggregateFunction aggregateFunction, String name, String alias) {
    //        this(dialect, aggregateFunction, null, name, alias);
    //    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param aggregateFunction the aggregate function
     * @param tableAlias table alias
     * @param name column name
     * @param alias the column alias
     */
    public SelectColumnElement(Dialect dialect, AggregateFunction aggregateFunction, String tableAlias, String name,
        String alias) {
        this(dialect, aggregateFunction, false, tableAlias, name, alias);
    }

    //    /**
    //     * Instantiates a new select column element.
    //     *
    //     * @param dialect           dialect
    //     * @param aggregateFunction the aggregate function
    //     * @param distinct          the distinct
    //     * @param tableAlias        table alias
    //     * @param name              column name
    //     */
    //    public SelectColumnElement(Dialect dialect, AggregateFunction aggregateFunction, boolean distinct,
    //            String tableAlias, String name) {
    //        this(dialect, aggregateFunction, distinct, tableAlias, name, null);
    //    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param distinct the distinct
     * @param tableAlias table alias
     * @param name column name
     * @param alias column alias
     */
    public SelectColumnElement(Dialect dialect, boolean distinct, String tableAlias, String name, String alias) {
        this(dialect, null, distinct, tableAlias, name, alias);
    }

    /**
     * Instantiates a new select column element.
     *
     * @param dialect dialect
     * @param aggregateFunction the aggregate function
     * @param distinct the distinct
     * @param tableAlias table alias
     * @param name column name
     * @param alias column alias
     */
    public SelectColumnElement(Dialect dialect, AggregateFunction aggregateFunction, boolean distinct,
        String tableAlias, String name, String alias) {
        super(dialect, tableAlias, name);
        this.aggregateFunction = aggregateFunction;
        this.alias = alias;
        this.distinct = distinct;
    }

    /**
     * 返回aggregateFunction.
     *
     * @return aggregateFunction
     */
    public AggregateFunction getAggregateFunction() {
        return aggregateFunction;
    }

    /**
     * 返回alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 返回distinct.
     *
     * @return distinct
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Sets the alias.
     *
     * @param alias the new alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        return dialect.dml().column(aggregateFunction, distinct, tableAlias, name, alias);
    }
}
