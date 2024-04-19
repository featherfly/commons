package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialect.Keyworld;
import cn.featherfly.common.db.dialect.Join;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.repository.builder.AliasManager;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlSelectBasicBuilder implements SqlSelectColumnsBuilder<SqlSelectBasicBuilder> {

    /** The table alias. */
    //    protected String tableAlias;

    /** The table name. */
    //    protected String tableName;

    /** The build with from. */
    protected boolean buildWithFrom = true;

    /** The dialect. */
    protected Dialect dialect;

    /** The class mapping. */
    protected JdbcClassMapping<?> classMapping;

    /** The table metadata. */
    protected Table table;

    private AliasManager aliasManager;

    /** The default select columns basic builder. */
    protected Map<String, SqlSelectColumnsBuilder<?>> selectColumnsBasicBuilders = new LinkedHashMap<>(1);

    /** The sql join on basic builders. */
    protected List<SqlJoinOnBuilder> sqlJoinOnBasicBuilders = new ArrayList<>(0);

    /** The join select columns basic builders. */
    protected List<SqlSelectColumnsBuilder<?>> joinSelectColumnsBasicBuilders = new ArrayList<>(0);

    //    /** The mapping factory. */
    //    protected MappingFactory mappingFactory;

    //    protected String columnAliasPrefix;

    /** The column alias prefix table alias. */
    protected boolean columnAliasPrefixTableAlias;

    //    private BiFunction<String, Boolean, String> columnAliasPrefixProcessor;

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect   dialect
     * @param tableName tableName
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName) {
        this(dialect, tableName, new AliasManager());
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      dialect
     * @param tableName    tableName
     * @param aliasManager the alias manager
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName, AliasManager aliasManager) {
        this(dialect, tableName, null, aliasManager);
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect    dialect
     * @param tableName  tableName
     * @param tableAlias alias
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName, String tableAlias) {
        this(dialect, tableName, tableAlias, new AliasManager());
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      dialect
     * @param tableName    tableName
     * @param tableAlias   alias
     * @param aliasManager the alias manager
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName, String tableAlias, AliasManager aliasManager) {
        this.dialect = dialect;
        this.aliasManager = aliasManager;
        if (Lang.isEmpty(tableAlias)) {
            tableAlias = aliasManager.put(tableName);
        } else {
            aliasManager.put(tableName, tableAlias);
        }
        selectColumnsBasicBuilders.put(tableAlias, new SqlSelectColumnsBasicBuilder(dialect, tableAlias));
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect    the dialect
     * @param tableNames the table names, alias is key, value is name
     */
    public SqlSelectBasicBuilder(Dialect dialect, Map<String, String> tableNames) {
        this(dialect, tableNames, new AliasManager());
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      the dialect
     * @param tableNames   the table names, alias is key, value is name
     * @param aliasManager the alias manager
     */
    public SqlSelectBasicBuilder(Dialect dialect, Map<String, String> tableNames, AliasManager aliasManager) {
        AssertIllegalArgument.isNotEmpty(tableNames, "tableNames");
        this.dialect = dialect;
        this.aliasManager = aliasManager;
        for (Entry<String, String> e : tableNames.entrySet()) {
            selectColumnsBasicBuilders.put(e.getKey(), new SqlSelectColumnsBasicBuilder(dialect, e.getKey()));
            aliasManager.put(e.getValue(), e.getKey());
        }
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect    the dialect
     * @param table      the table
     * @param tableAlias the table alias
     */
    public SqlSelectBasicBuilder(Dialect dialect, Table table, String tableAlias) {
        this(dialect, table, tableAlias, new AliasManager());
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      dialect
     * @param table        the table
     * @param tableAlias   alias
     * @param aliasManager the alias manager
     */
    public SqlSelectBasicBuilder(Dialect dialect, Table table, String tableAlias, AliasManager aliasManager) {
        AssertIllegalArgument.isNotNull(dialect, "dialect");
        AssertIllegalArgument.isNotNull(table, "classMapping");

        this.dialect = dialect;
        this.table = table;
        this.aliasManager = aliasManager;

        if (Lang.isEmpty(tableAlias)) {
            tableAlias = aliasManager.put(table.name());
        } else {
            aliasManager.put(table.name(), tableAlias);
        }
        selectColumnsBasicBuilders.put(tableAlias,
            new SqlSelectColumnsTableMetadataBuilder(dialect, table, tableAlias));
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      the dialect
     * @param classMapping the class mapping
     * @param tableAlias   the table alias
     */
    public SqlSelectBasicBuilder(Dialect dialect, JdbcClassMapping<?> classMapping, String tableAlias) {
        this(dialect, classMapping, tableAlias, new AliasManager());
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     * @param tableAlias   alias
     * @param aliasManager the alias manager
     */
    public SqlSelectBasicBuilder(Dialect dialect, JdbcClassMapping<?> classMapping, String tableAlias,
        AliasManager aliasManager) {
        AssertIllegalArgument.isNotNull(dialect, "dialect");
        AssertIllegalArgument.isNotNull(classMapping, "classMapping");

        this.dialect = dialect;
        this.classMapping = classMapping;
        this.aliasManager = aliasManager;

        if (Lang.isEmpty(tableAlias)) {
            tableAlias = aliasManager.put(classMapping.getRepositoryName());
        } else {
            aliasManager.put(classMapping.getRepositoryName(), tableAlias);
        }
        selectColumnsBasicBuilders.put(tableAlias,
            new SqlSelectColumnsClassMappingBuilder(dialect, classMapping, tableAlias));
    }

    //    /**
    //     * Instantiates a new sql select basic builder.
    //     *
    //     * @param dialect        dialect
    //     * @param classMapping   classMapping
    //     * @param mappingFactory the mapping factory
    //     */
    //    public SqlSelectBasicBuilder(Dialect dialect, ClassMapping<?> classMapping, MappingFactory mappingFactory) {
    //        this(dialect, classMapping, null, mappingFactory);
    //    }
    //
    //
    //    /**
    //     * Instantiates a new sql select basic builder.
    //     *
    //     * @param dialect        dialect
    //     * @param classMapping   classMapping
    //     * @param tableAlias     alias
    //     * @param mappingFactory the mapping factory
    //     */
    //    public SqlSelectBasicBuilder(Dialect dialect, ClassMapping<?> classMapping, String tableAlias,
    //            MappingFactory mappingFactory) {
    //        this.dialect = dialect;
    //        this.classMapping = classMapping;
    //        //        tableName = classMapping.getRepositoryName();
    //        //        this.tableAlias = tableAlias;
    //        this.mappingFactory = mappingFactory;
    //        //        defaultSelectColumnsBasicBuilder = new SqlSelectColumnsBasicBuilderImpl(dialect, classMapping, tableAlias,
    //        //                mappingFactory);
    //        AssertIllegalArgument.isNotNull(classMapping, "classMapping");
    //
    //        if (Lang.isEmpty(tableAlias)) {
    //            tableAlias = aliasManager.put(classMapping.getRepositoryName());
    //        } else {
    //            aliasManager.put(classMapping.getRepositoryName(), tableAlias);
    //        }
    //        //        defaultTable = classMapping.getRepositoryName();
    //        //        tableNames.put(classMapping.getRepositoryName(), tableAlias);
    //        selectColumnsBasicBuilders.put(tableAlias, new SqlSelectColumnsBasicBuilder(dialect, tableAlias));
    //    }

    /**
     * 返回buildWithFrom.
     *
     * @return buildWithFrom
     */
    public boolean isBuildWithFrom() {
        return buildWithFrom;
    }

    /**
     * 设置buildWithFrom.
     *
     * @param buildWithFrom buildWithFrom
     */
    public void setBuildWithFrom(boolean buildWithFrom) {
        this.buildWithFrom = buildWithFrom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder clearColumns() {
        getDefaultBuilder().clearColumns();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(AggregateFunction aggregateFunction, boolean distinct, String column) {
        getDefaultBuilder().addColumn(aggregateFunction, distinct, column);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(AggregateFunction aggregateFunction, boolean distinct, String column,
        String alias) {
        getDefaultBuilder().addColumn(aggregateFunction, distinct, column, alias);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(boolean distinct, String column) {
        getDefaultBuilder().addColumn(distinct, column);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(boolean distinct, String column, String alias) {
        getDefaultBuilder().addColumn(distinct, column, alias);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumns(String... columns) {
        getDefaultBuilder().addColumns(columns);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumns(Collection<String> columns) {
        getDefaultBuilder().addColumns(columns);
        return this;
    }

    /**
     * Adds the table column.
     *
     * @param tableName   the table name
     * @param tableSelect the table select
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder table(String tableName, Consumer<SqlSelectColumnsBuilder<?>> tableSelect) {
        tableSelect.accept(selectColumnsBasicBuilders.get(aliasManager.getAlias(tableName)));
        return this;
    }

    /**
     * Adds the table column.
     *
     * @param tableIndex  the table index
     * @param tableSelect the table select
     * @return the sql select basic builder
     */
    public SqlSelectBasicBuilder table(int tableIndex, Consumer<SqlSelectColumnsBuilder<?>> tableSelect) {
        tableSelect.accept(selectColumnsBasicBuilders.get(aliasManager.getAlias(tableIndex)));
        return this;
    }

    /**
     * Join.
     *
     * @param conditionColumn     the condition column
     * @param joinTableName       the join table name
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(String conditionColumn, String joinTableName, String joinTableAlias,
        String joinTableColumnName) {
        return join(Join.INNER_JOIN, conditionColumn, joinTableName, joinTableAlias, joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param conditionTableAlias the condition table alias
     * @param conditionColumn     the condition column
     * @param joinTableName       the join table name
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(String conditionTableAlias, String conditionColumn, String joinTableName,
        String joinTableAlias, String joinTableColumnName) {
        return join(Join.INNER_JOIN, conditionTableAlias, conditionColumn, joinTableName, joinTableAlias,
            joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param conditionColumn     the condition column
     * @param joinTableName       the join table name
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionColumn, String joinTableName,
        String joinTableAlias, String joinTableColumnName) {
        return join(join, getDefaultTableAlias(), conditionColumn, joinTableName, joinTableAlias, joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param conditionTableAlias the condition table alias
     * @param conditionColumn     the condition column
     * @param joinTableName       the join table name
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionTableAlias, String conditionColumn,
        String joinTableName, String joinTableAlias, String joinTableColumnName) {
        SqlJoinOnBasicBuilder joinOnBuilder = new SqlJoinOnBasicBuilder(dialect, join, joinTableName, joinTableAlias,
            joinTableColumnName, conditionTableAlias, conditionColumn);
        sqlJoinOnBasicBuilders.add(joinOnBuilder);
        SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder = new SqlSelectColumnsBasicBuilder(dialect,
            joinTableAlias);
        return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
    }

    /**
     * Join.
     *
     * @param joinTableName  the join table name
     * @param joinTableAlias the join table alias
     * @param onSql          the on sql
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(String joinTableName, String joinTableAlias, String onSql) {
        return join(Join.INNER_JOIN, joinTableName, joinTableAlias, onSql);
    }

    /**
     * Join.
     *
     * @param join           the join
     * @param joinTableName  the join table name
     * @param joinTableAlias the join table alias
     * @param onSql          the on sql
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, String joinTableName, String joinTableAlias, String onSql) {
        SqlJoinOnBasicBuilder2 joinOnBuilder = new SqlJoinOnBasicBuilder2(dialect, join, joinTableName, joinTableAlias,
            onSql);
        sqlJoinOnBasicBuilders.add(joinOnBuilder);
        SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder = new SqlSelectColumnsBasicBuilder(dialect,
            joinTableAlias);
        return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
    }

    /**
     * Join.
     *
     * @param joinTable      the join table
     * @param joinTableAlias the join table alias
     * @param onSql          the on sql
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Table joinTable, String joinTableAlias, String onSql) {
        return join(Join.INNER_JOIN, joinTable, joinTableAlias, onSql);
    }

    /**
     * Join.
     *
     * @param join           the join
     * @param joinTable      the join table metadata
     * @param joinTableAlias the join table alias
     * @param onSql          the on sql
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, Table joinTable, String joinTableAlias, String onSql) {
        SqlJoinOnBasicBuilder2 joinOnBuilder = new SqlJoinOnBasicBuilder2(dialect, join, joinTable.name(),
            joinTableAlias, onSql);
        sqlJoinOnBasicBuilders.add(joinOnBuilder);
        SqlSelectColumnsTableMetadataBuilder joinSelectColumnsBuilder = new SqlSelectColumnsTableMetadataBuilder(
            dialect, joinTable, joinTableAlias);
        return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
    }

    /**
     * Join.
     *
     * @param joinTable           the join table metadata
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @param sourceColumn        the source column
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Table joinTable, String joinTableAlias, String joinTableColumnName,
        String sourceColumn) {
        return join(Join.INNER_JOIN, joinTable, joinTableAlias, joinTableColumnName, sourceColumn);
    }

    /**
     * Join.
     *
     * @param joinTable           the join table metadata
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @param sourceTableAlias    the source table alias
     * @param sourceColumn        the source column
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Table joinTable, String joinTableAlias, String joinTableColumnName,
        String sourceTableAlias, String sourceColumn) {
        return join(Join.INNER_JOIN, joinTable, joinTableAlias, joinTableColumnName, sourceTableAlias, sourceColumn);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param joinTable           the join table metadata
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @param sourceColumn        the source column
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, Table joinTable, String joinTableAlias,
        String joinTableColumnName, String sourceColumn) {
        return join(join, joinTable, joinTableAlias, joinTableColumnName, getDefaultTableAlias(), sourceColumn);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param joinTable           the join table metadata
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @param sourceTableAlias    the source table alias
     * @param sourceColumn        the source column
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, Table joinTable, String joinTableAlias,
        String joinTableColumnName, String sourceTableAlias, String sourceColumn) {
        SqlJoinOnBasicBuilder joinOnBuilder = new SqlJoinOnBasicBuilder(dialect, join, joinTable.name(), joinTableAlias,
            joinTableColumnName, sourceTableAlias, sourceColumn);
        sqlJoinOnBasicBuilders.add(joinOnBuilder);
        SqlSelectColumnsTableMetadataBuilder joinSelectColumnsBuilder = new SqlSelectColumnsTableMetadataBuilder(
            dialect, joinTable, joinTableAlias);
        return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
    }

    //    /**
    //     * Join.
    //     *
    //     * @param conditionTableAlias the condition table alias
    //     * @param conditionColumn     the condition column
    //     * @param joinClassMapping    the join class mapping
    //     * @param joinTableAlias      the join table alias
    //     * @return the sql select join on basic builder
    //     */
    //    public SqlSelectJoinOnBasicBuilder join(String conditionTableAlias, String conditionColumn,
    //            ClassMapping<?> joinClassMapping, String joinTableAlias) {
    //        return join(Join.INNER_JOIN, conditionTableAlias, conditionColumn, joinClassMapping, joinTableAlias);
    //    }

    /**
     * Join.
     *
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(JdbcClassMapping<?> joinClassMapping, String joinTableAlias,
        String joinTableColumnName) {
        return join(Join.INNER_JOIN, joinClassMapping, joinTableAlias, joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param conditionColumn     the condition column
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(String conditionColumn, JdbcClassMapping<?> joinClassMapping,
        String joinTableAlias, String joinTableColumnName) {
        return join(Join.INNER_JOIN, conditionColumn, joinClassMapping, joinTableAlias, joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param conditionTableAlias the condition table alias
     * @param conditionColumn     the condition column
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(String conditionTableAlias, String conditionColumn,
        JdbcClassMapping<?> joinClassMapping, String joinTableAlias, String joinTableColumnName) {
        return join(Join.INNER_JOIN, conditionTableAlias, conditionColumn, joinClassMapping, joinTableAlias,
            joinTableColumnName);
    }

    //    /**
    //     * Join.
    //     *
    //     * @param join                the join
    //     * @param conditionTableAlias the condition table alias
    //     * @param conditionColumn     the condition column
    //     * @param joinClassMapping    the join class mapping
    //     * @param joinTableAlias      the join table alias
    //     * @return the sql select join on basic builder
    //     */
    //    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionTableAlias, String conditionColumn,
    //            ClassMapping<?> joinClassMapping, String joinTableAlias) {
    //        return join(join, conditionTableAlias, conditionColumn, joinClassMapping, joinTableAlias,
    //                joinClassMapping.getPrivaryKeyPropertyMappings().get(0).getRepositoryFieldName());
    //    }

    /**
     * Join.
     *
     * @param join                the join
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, JdbcClassMapping<?> joinClassMapping, String joinTableAlias,
        String joinTableColumnName) {
        return join(join, classMapping.getPrivaryKeyPropertyMappings().get(0).getRepositoryFieldName(),
            joinClassMapping, joinTableAlias, joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param conditionColumn     the condition column
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionColumn, JdbcClassMapping<?> joinClassMapping,
        String joinTableAlias, String joinTableColumnName) {
        //        return join(join, getDefaultTableAlias(), conditionColumn, joinTableColumnName, joinTableAlias,
        //                joinTableColumnName);
        return join(join, getDefaultTableAlias(), conditionColumn, joinClassMapping, joinTableAlias,
            joinTableColumnName);
    }

    /**
     * Join.
     *
     * @param join                the join
     * @param conditionTableAlias the condition table alias
     * @param conditionColumn     the condition column
     * @param joinClassMapping    the join class mapping
     * @param joinTableAlias      the join table alias
     * @param joinTableColumnName the join table column name
     * @return the sql select join on basic builder
     */
    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionTableAlias, String conditionColumn,
        JdbcClassMapping<?> joinClassMapping, String joinTableAlias, String joinTableColumnName) {

        SqlJoinOnBasicBuilder joinOnBuilder = new SqlJoinOnBasicBuilder(dialect, join,
            joinClassMapping.getRepositoryName(), joinTableAlias, joinTableColumnName, conditionTableAlias,
            conditionColumn);
        sqlJoinOnBasicBuilders.add(joinOnBuilder);

        //        SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder = new SqlSelectColumnsBasicBuilder(dialect, classMapping,
        //                tableAlias, mappingFactory);
        SqlSelectColumnsClassMappingBuilder joinSelectColumnsBuilder = new SqlSelectColumnsClassMappingBuilder(dialect,
            joinClassMapping, joinTableAlias);
        return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
        // return join(join, conditionTableAlias, conditionColumn, classMapping,
        // tableAlias, joinTableColumnName, null);
    }

    // public SqlSelectJoinOnBasicBuilder join(Join join,
    // String conditionTableAlias, String conditionColumn,
    // ClassMapping<?> classMapping, String tableAlias,
    // String joinTableColumnName, String fetchProeprtyName) {
    //
    // SqlJoinOnBasicBuilder joinOnBuilder = new SqlJoinOnBasicBuilder(dialect,
    // join, classMapping.getRepositoryName(), tableAlias,
    // joinTableColumnName, conditionTableAlias, conditionColumn);
    // sqlJoinOnBasicBuilders.add(joinOnBuilder);
    // SqlSelectColumnsBasicBuilder joinSelectColumnsBuilder = new
    // SqlSelectColumnsBasicBuilder(
    // dialect, classMapping, tableAlias, mappingFactory);
    // return new SqlSelectJoinOnBasicBuilder(this, joinSelectColumnsBuilder);
    // }

    /**
     * Adds the join select columns basic builder.
     *
     * @param joinSelectColumnsBuilder the join select columns builder
     */
    void addJoinSelectColumnsBasicBuilder(SqlSelectColumnsBuilder<?> joinSelectColumnsBuilder) {
        joinSelectColumnsBasicBuilders.add(joinSelectColumnsBuilder);
    }

    /**
     * Builds the.
     *
     * @param filter the filter
     * @return the string
     */
    public String build(BiPredicate<String, String> filter) {
        StringBuilder select = new StringBuilder();
        Keyworld keyworld = dialect.getKeywords();
        select.append(keyworld.select());
        for (SqlSelectColumnsBuilder<?> selectColumnsBuilder : selectColumnsBasicBuilders.values()) {
            selectColumnsBuilder.setColumnAliasPrefixTableAlias(columnAliasPrefixTableAlias);
            select.append(Chars.SPACE_CHAR).append(selectColumnsBuilder.build()).append(Chars.COMMA_CHAR);
        }
        select.deleteCharAt(select.length() - 1);

        for (SqlSelectColumnsBuilder<?> joinColumnsBuilder : joinSelectColumnsBasicBuilders) {
            joinColumnsBuilder.setColumnAliasPrefixTableAlias(columnAliasPrefixTableAlias);
            select.append(Chars.COMMA_CHAR).append(Chars.SPACE_CHAR).append(joinColumnsBuilder.build());
        }
        // if (columns.isEmpty()) {
        // if (classMapping == null) {
        // if (Lang.isEmpty(tableAlias)) {
        // select.append(Chars.SPACE).append(Chars.STAR);
        // } else {
        // select.append(Chars.SPACE).append(tableAlias).append(Chars.DOT).append(Chars.STAR);
        // }
        // } else {
        // select.append(Chars.SPACE)
        // .append(ClassMappingUtils.getSelectColumnsSql(classMapping,
        // tableAlias, dialect));
        // }
        // } else {
        // for (SelectColumnElement column : columns) {
        // // 基础构建器一个实例对应一个table
        // column.setTableAlias(tableAlias);
        // select.append(Chars.SPACE).append(column).append(Chars.COMMA);
        // }
        // select.deleteCharAt(select.length() - 1);
        // }

        if (buildWithFrom) {
            AssertIllegalArgument.isNotEmpty(aliasManager.getNameAlias(), "buildWithFrom=true时，tableNames不能为空");
            select.append(Chars.SPACE).append(keyworld.from());
            for (Entry<String, String> entry : aliasManager.getNameAlias().entrySet()) {
                String tableAlias = entry.getKey();
                String tableName = entry.getValue();
                if (filter.test(tableName, tableAlias)) {
                    select.append(Chars.SPACE).append(dialect.buildTableSql(tableName, tableAlias)).append(Chars.COMMA);
                }
            }
            select.deleteCharAt(select.length() - 1);

            sqlJoinOnBasicBuilders.forEach(builder -> {
                select.append(Chars.SPACE).append(builder.build());
            });
        }
        return select.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return build((name, alias) -> true);
    }

    private SqlSelectColumnsBuilder<?> getDefaultBuilder() {
        return selectColumnsBasicBuilders.get(getDefaultTableAlias());
    }

    /**
     * Gets the default table alias.
     *
     * @return the default table alias
     */
    public String getDefaultTableAlias() {
        return aliasManager.getAlias(0);
    }

    /**
     * set aliasManager value.
     *
     * @return the alias manager
     */
    public AliasManager getAliasManager() {
        return aliasManager;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public SqlSelectBasicBuilder setColumnAliasPrefix(String columnAliasPrefix) {
    //        this.columnAliasPrefix = columnAliasPrefix;
    //        return this;
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
    @Override
    public SqlSelectBasicBuilder setColumnAliasPrefixTableAlias(boolean columnAliasPrefixTableAlias) {
        this.columnAliasPrefixTableAlias = columnAliasPrefixTableAlias;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnAliasPrefixProcessor(BiFunction<Boolean, String, String> columnAliasPrefixProcessor) {
        //        this.columnAliasPrefixProcessor = columnAliasPrefixProcessor;
        throw new UnsupportedException();
    }

}
