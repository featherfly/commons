
package cn.featherfly.common.db.builder.dml;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.builder.dml.FindBuilder;
import cn.featherfly.common.repository.builder.dml.QueryBuilder;
import cn.featherfly.common.repository.operate.AggregateFunction;

/**
 * <p>
 * sql query builder
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlQueryBuilder implements SelectBuilder, QueryBuilder {

    private SqlConditionGroup conditionGroup;

    private SqlSortBuilder sortBuilder;

    private SqlFindBuilder findBuilder;

    private SqlSelectBuilder selectBuilder;

    /**
     * Instantiates a new sql query builder.
     *
     * @param dialect      dialect
     * @param ignorePolicy the ignore policy
     */
    public SqlQueryBuilder(Dialect dialect, Predicate<Object> ignorePolicy) {
        this.dialect = dialect;
        sortBuilder = new SqlSortBuilder(dialect);
        conditionGroup = new SqlConditionGroup(dialect, ignorePolicy, sortBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder result = new StringBuilder();
        if (findBuilder != null) {
            result.append(findBuilder.build());
        } else if (selectBuilder != null) {
            result.append(selectBuilder.build());
        }
        String condition = conditionGroup.build();
        if (Strings.isNotBlank(condition)) {
            BuilderUtils.link(result, "WHERE");
            BuilderUtils.link(result, condition);
        }
        BuilderUtils.link(result, sortBuilder.build());
        return result.toString().trim();
    }

    /**
     * <p>
     * 获取查询参数
     * </p>
     * .
     *
     * @return 查询参数
     */
    @SuppressWarnings("unchecked")
    public List<Object> getParams() {
        List<Object> result = new ArrayList<>();
        Object param = conditionGroup.getParamValue();
        if (param == null) {
            result.add(param);
        } else if (param instanceof Collection) {
            result.addAll((Collection<Object>) param);
        } else if (param.getClass().isArray()) {
            int length = Array.getLength(param);
            for (int i = 0; i < length; i++) {
                result.add(Array.get(param, i));
            }
        }
        //        if (pagination != null) {
        //            if (dialect == null) {
        //                throw new BuilderException("需要分页时，dialect不能为空");
        //            }
        //            PaginationWrapper<Object> pw = new PaginationWrapper<>(pagination);
        //            Object[] params = dialect.getPaginationSqlParameter(result.toArray(), pw.getStart(), pw.getLimit());
        //            result.clear();
        //            CollectionUtils.addAll(result, params);
        //        }
        return result;
    }

    // ********************************************************************
    // find
    // ********************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBuilder find(String target) {
        return find(target, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBuilder find(String target, String alias) {
        conditionGroup.setQueryAlias(alias);
        sortBuilder.setTableAlias(alias);
        findBuilder = new SqlFindBuilder(dialect, target, alias, conditionGroup);
        return findBuilder;
    }

    // ********************************************************************
    // select
    // ********************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, AggregateFunction aggregateFunction) {
        return selectBuilder().select(columnName, aggregateFunction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName) {
        return selectBuilder().select(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, String asName) {
        return selectBuilder().select(columnName, asName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String columnName, AggregateFunction aggregateFunction, String asName) {
        return selectBuilder().select(columnName, aggregateFunction, asName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(Map<String, String> columnNames) {
        return selectBuilder().select(columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(String[] columnNames) {
        return selectBuilder().select(columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectBuilder select(Collection<String> columnNames) {
        return selectBuilder().select(columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(String tableName) {
        return from(tableName, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlConditionBuilder from(String tableName, String alias) {
        conditionGroup.setQueryAlias(alias);
        sortBuilder.setTableAlias(alias);
        return selectBuilder().from(tableName, alias);
    }

    // ********************************************************************
    // property
    // ********************************************************************

    private Dialect dialect;

    //    private Pagination pagination;

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * 设置dialect.
     *
     * @param dialect dialect
     */
    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    /**
     * 返回selectBuilder
     *
     * @return selectBuilder
     */
    private SqlSelectBuilder selectBuilder() {
        if (selectBuilder == null) {
            selectBuilder = new SqlSelectBuilder(dialect, conditionGroup);
        }
        return selectBuilder;
    }
}
