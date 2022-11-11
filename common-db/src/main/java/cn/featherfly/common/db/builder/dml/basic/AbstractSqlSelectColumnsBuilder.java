package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.featherfly.common.db.builder.model.SelectColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.operator.AggregateFunction;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 * @param <B> the generic type
 */
public abstract class AbstractSqlSelectColumnsBuilder<B extends AbstractSqlSelectColumnsBuilder<B>>
        implements SqlSelectColumnsBuilder<B> {

    /** The table alias. */
    protected String tableAlias;

    /** The columns. */
    protected List<SelectColumnElement> columns = new ArrayList<>(0);

    /** The dialect. */
    protected Dialect dialect;

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect dialect
     */
    public AbstractSqlSelectColumnsBuilder(Dialect dialect) {
        this(dialect, null);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect    dialect
     * @param tableAlias table name alias
     */
    public AbstractSqlSelectColumnsBuilder(Dialect dialect, String tableAlias) {
        AssertIllegalArgument.isNotNull(dialect, "dialect");
        this.dialect = dialect;
        this.tableAlias = tableAlias;
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
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(AggregateFunction aggregateFunction, String column) {
        columns.add(new SelectColumnElement(dialect, column, tableAlias, aggregateFunction));
        return (B) this;
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @param asName            alias name
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(AggregateFunction aggregateFunction, String column, String asName) {
        columns.add(new SelectColumnElement(dialect, column, tableAlias, aggregateFunction, asName));
        return (B) this;
    }

    /**
     * add column.
     *
     * @param column column
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(String column) {
        columns.add(new SelectColumnElement(dialect, column, tableAlias));
        return (B) this;
    }

    /**
     * add column.
     *
     * @param column column
     * @param asName asName
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(String column, String asName) {
        columns.add(new SelectColumnElement(dialect, column, tableAlias, asName));
        return (B) this;
    }

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumns(String... columns) {
        for (String c : columns) {
            addColumn(c);
        }
        return (B) this;
    }

    /**
     * addColumns.
     *
     * @param columns columns
     * @return this
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumns(Collection<String> columns) {
        for (String c : columns) {
            addColumn(c);
        }
        return (B) this;
    }
}
