package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialect.Keyworld;
import cn.featherfly.common.db.dialect.Join;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.AggregateFunction;
import cn.featherfly.common.repository.builder.AliasManager;
import cn.featherfly.common.repository.mapping.ClassMapping;

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
    protected ClassMapping<?> classMapping;

    private AliasManager aliasManager = new AliasManager();

    /** The default select columns basic builder. */
    protected Map<String, SqlSelectColumnsBuilder<?>> selectColumnsBasicBuilders = new LinkedHashMap<>(1);

    /** The sql join on basic builders. */
    protected List<SqlJoinOnBasicBuilder> sqlJoinOnBasicBuilders = new ArrayList<>(0);

    /** The join select columns basic builders. */
    protected List<SqlSelectColumnsBuilder<?>> joinSelectColumnsBasicBuilders = new ArrayList<>(0);

    //    /** The mapping factory. */
    //    protected MappingFactory mappingFactory;

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect   dialect
     * @param tableName tableName
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName) {
        this(dialect, tableName, null);
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect    dialect
     * @param tableName  tableName
     * @param tableAlias alias
     */
    public SqlSelectBasicBuilder(Dialect dialect, String tableName, String tableAlias) {
        this.dialect = dialect;
        //        this.tableAlias = tableAlias;
        //        this.tableName = tableName;
        //        defaultTable = tableName;
        aliasManager.put(tableName, tableAlias);
        selectColumnsBasicBuilders.put(tableAlias, new SqlSelectColumnsBasicBuilder(dialect, tableAlias));
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect    the dialect
     * @param tableNames the table names, alias is key, value is name
     */
    public SqlSelectBasicBuilder(Dialect dialect, Map<String, String> tableNames) {
        this.dialect = dialect;
        //        this.tableAlias = tableAlias;
        //        this.tableName = tableName;
        AssertIllegalArgument.isNotEmpty(tableNames, "tableNames");
        //        boolean first = true;
        for (Entry<String, String> e : tableNames.entrySet()) {
            //            if (first) {
            //                defaultTable = e.getValue();
            //                first = false;
            //            }
            selectColumnsBasicBuilders.put(e.getKey(), new SqlSelectColumnsBasicBuilder(dialect, e.getKey()));
            aliasManager.put(e.getValue(), e.getKey());
        }
    }

    /**
     * Instantiates a new sql select basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     * @param tableAlias   alias
     */
    public SqlSelectBasicBuilder(Dialect dialect, ClassMapping<?> classMapping, String tableAlias) {
        AssertIllegalArgument.isNotNull(dialect, "dialect");
        AssertIllegalArgument.isNotNull(classMapping, "classMapping");

        this.dialect = dialect;
        this.classMapping = classMapping;

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

    //    /**
    //     * 返回alias.
    //     *
    //     * @return alias
    //     */
    //    public String getTableAlias() {
    //        return tableAlias;
    //    }
    //
    //    /**
    //     * 设置alias.
    //     *
    //     * @param tableAlias tableAlias
    //     */
    //    public void setTableAlias(String tableAlias) {
    //        this.tableAlias = tableAlias;
    //        defaultSelectColumnsBasicBuilder.setTableAlias(tableAlias);
    //    }

    //    /**
    //     * 返回tableName.
    //     *
    //     * @return tableName
    //     */
    //    public String getTableName() {
    //        return tableName;
    //    }
    //
    //    /**
    //     * 设置tableName.
    //     *
    //     * @param tableName tableName
    //     */
    //    public void setTableName(String tableName) {
    //        this.tableName = tableName;
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
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @return this
     */
    @Override
    public SqlSelectBasicBuilder addColumn(AggregateFunction aggregateFunction, String column) {
        getDefaultBuilder().addColumn(aggregateFunction, column);
        return this;
    }

    /**
     * add column.
     *
     * @param aggregateFunction aggregateFunction
     * @param column            column
     * @param asName            alias name
     * @return this
     */
    @Override
    public SqlSelectBasicBuilder addColumn(AggregateFunction aggregateFunction, String column, String asName) {
        getDefaultBuilder().addColumn(aggregateFunction, column, asName);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(String column) {
        getDefaultBuilder().addColumn(column);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlSelectBasicBuilder addColumn(String column, String asName) {
        getDefaultBuilder().addColumn(column, asName);
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
    public SqlSelectJoinOnBasicBuilder join(ClassMapping<?> joinClassMapping, String joinTableAlias,
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
    public SqlSelectJoinOnBasicBuilder join(String conditionColumn, ClassMapping<?> joinClassMapping,
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
            ClassMapping<?> joinClassMapping, String joinTableAlias, String joinTableColumnName) {
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
    public SqlSelectJoinOnBasicBuilder join(Join join, ClassMapping<?> joinClassMapping, String joinTableAlias,
            String joinTableColumnName) {
        return join(join, classMapping.getPrivaryKeyPropertyMappings().get(0).getRepositoryFieldName(),
                joinTableColumnName, joinTableAlias, joinTableColumnName);
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
    public SqlSelectJoinOnBasicBuilder join(Join join, String conditionColumn, ClassMapping<?> joinClassMapping,
            String joinTableAlias, String joinTableColumnName) {
        return join(join, getDefaultTableAlias(), conditionColumn, joinTableColumnName, joinTableAlias,
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
            ClassMapping<?> joinClassMapping, String joinTableAlias, String joinTableColumnName) {

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
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder select = new StringBuilder();
        Keyworld keyworld = dialect.getKeywords();
        select.append(keyworld.select());
        for (SqlSelectColumnsBuilder<?> selectColumnsBuilder : selectColumnsBasicBuilders.values()) {
            select.append(Chars.SPACE_CHAR).append(selectColumnsBuilder.build()).append(Chars.COMMA_CHAR);
        }
        select.deleteCharAt(select.length() - 1);

        for (SqlSelectColumnsBuilder<?> joinColumnsBuilder : joinSelectColumnsBasicBuilders) {
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

                select.append(Chars.SPACE).append(dialect.buildTableSql(tableName, tableAlias)).append(Chars.COMMA);
            }
            select.deleteCharAt(select.length() - 1);

            sqlJoinOnBasicBuilders.forEach(builder -> {
                select.append(Chars.SPACE).append(builder.build());
            });
        }
        return select.toString();
    }

    private SqlSelectColumnsBuilder<?> getDefaultBuilder() {
        return selectColumnsBasicBuilders.get(getDefaultTableAlias());
    }

    private String getDefaultTableAlias() {
        return aliasManager.getAlias(0);
    }

}
