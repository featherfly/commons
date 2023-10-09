package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

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

    /** The column alias prefix. */
    //    protected String columnAliasPrefix;

    protected boolean columnAliasPrefixTableAlias;

    protected BiFunction<String, Boolean, String> columnAliasPrefixProcessor;

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

        columnAliasPrefixProcessor = (aliasPrefix, prefixTableAlias) -> columnAliasPrefixTableAlias ? aliasPrefix : "";
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
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B clearColumns() {
        columns.clear();
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(AggregateFunction aggregateFunction, boolean distinct, String column) {
        columns.add(new SelectColumnElement(dialect, aggregateFunction, distinct, tableAlias, column, null));
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(AggregateFunction aggregateFunction, boolean distinct, String column, String alias) {
        columns.add(new SelectColumnElement(dialect, aggregateFunction, distinct, tableAlias, column,
                columnAliasPrefixProcessor.apply(alias, columnAliasPrefixTableAlias)));
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(boolean distinct, String column) {
        columns.add(new SelectColumnElement(dialect, distinct, tableAlias, column, null));
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumn(boolean distinct, String column, String alias) {
        columns.add(new SelectColumnElement(dialect, distinct, tableAlias, column,
                columnAliasPrefixProcessor.apply(alias, columnAliasPrefixTableAlias)));
        return (B) this;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B addColumns(Collection<String> columns) {
        for (String c : columns) {
            addColumn(c);
        }
        return (B) this;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @SuppressWarnings("unchecked")
    //    @Override
    //    public B setColumnAliasPrefix(String columnAliasPrefix) {
    //        this.columnAliasPrefix = columnAliasPrefix;
    //        return (B) this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public String getColumnAliasPrefix() {
    //        return columnAliasPrefix;
    //    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public B setColumnAliasPrefixTableAlias(boolean columnAliasPrefixTableAlias) {
        this.columnAliasPrefixTableAlias = columnAliasPrefixTableAlias;
        return (B) this;
    }

    @Override
    public void setColumnAliasPrefixProcessor(BiFunction<String, Boolean, String> columnAliasPrefixProcessor) {
        this.columnAliasPrefixProcessor = columnAliasPrefixProcessor;
    }
}
