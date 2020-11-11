
package cn.featherfly.common.db.builder.dml;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.builder.BuilderException;
import cn.featherfly.common.repository.builder.BuilderExceptionCode;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;
import cn.featherfly.common.repository.builder.dml.ConditionGroup;
import cn.featherfly.common.repository.builder.dml.Expression;
import cn.featherfly.common.repository.builder.dml.ExpressionBuilder;
import cn.featherfly.common.repository.builder.dml.LogicBuilder;
import cn.featherfly.common.repository.builder.dml.ParamedExpression;
import cn.featherfly.common.repository.builder.dml.SortBuilder;
import cn.featherfly.common.repository.operate.LogicOperator;
import cn.featherfly.common.repository.operate.QueryOperator;

/**
 * <p>
 * sql condition group builder sql条件逻辑组构造器
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlConditionGroup implements ConditionGroup, SqlConditionBuilder {

    /**
     * Instantiates a new sql condition group.
     *
     * @param dialect dialect
     * @param sort    SortBuilder
     */
    public SqlConditionGroup(Dialect dialect, SqlSortBuilder sort) {
        this(dialect, null, sort);
    }

    /**
     * Instantiates a new sql condition group.
     *
     * @param dialect    dialect
     * @param queryAlias queryAlias
     * @param sort       SortBuilder
     */
    public SqlConditionGroup(Dialect dialect, String queryAlias, SqlSortBuilder sort) {
        this(dialect, null, sort, queryAlias);
    }

    /**
     * Instantiates a new sql condition group.
     *
     * @param dialect    dialect
     * @param parent     上级组
     * @param sort       排序器
     * @param queryAlias queryAlias
     */
    SqlConditionGroup(Dialect dialect, SqlConditionGroup parent, SqlSortBuilder sort, String queryAlias) {
        this.dialect = dialect;
        this.sort = sort;
        this.parent = parent;
        this.queryAlias = queryAlias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExpressionBuilder and() {
        addCondition(new SqlLogicExpression(LogicOperator.AND));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExpressionBuilder or() {
        addCondition(new SqlLogicExpression(LogicOperator.OR));
        return this;
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public LogicBuilder add(String name, Object value,
    // QueryOperator queryOperator) {
    // addCondition(new SqlConditionExpression(dialect, name, queryAlias, value,
    // queryOperator));
    // return this;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder lt(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.LT, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder le(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.LE, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder eq(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.EQ, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder ne(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.NE, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder ge(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.GE, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder gt(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.GT, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder sw(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.SW, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder co(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.CO, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder ew(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.EW, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder in(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.IN, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder nin(String name, Object value) {
        addCondition(new SqlConditionExpression(dialect, name, value, QueryOperator.NIN, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder isn(String name) {
        addCondition(new SqlConditionExpression(dialect, name, null, QueryOperator.ISN, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder inn(String name) {
        addCondition(new SqlConditionExpression(dialect, name, null, QueryOperator.INN, queryAlias));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExpressionBuilder group() {
        SqlConditionGroup group = new SqlConditionGroup(dialect, this, sort, queryAlias);
        addCondition(group);
        return group;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogicBuilder endGroup() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortBuilder sort() {
        return sort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder result = new StringBuilder();
        if (conditions.size() > 0) {
            Expression last = conditions.get(conditions.size() - 1);
            if (last instanceof SqlLogicExpression) {
                throw new BuilderException(BuilderExceptionCode
                        .createNoConditionBehindCode(((SqlLogicExpression) last).getLogicOperator().name()));
            }
        }

        List<String> availableConditions = new ArrayList<>();
        List<Expression> availableExpressions = new ArrayList<>();
        for (Expression expression : conditions) {
            String condition = expression.build();
            if (Strings.isNotBlank(condition)) {
                availableConditions.add(condition);
                availableExpressions.add(expression);
            } else {
                if (availableExpressions.size() > 0) {
                    Expression pre = availableExpressions.get(availableExpressions.size() - 1);
                    if (pre instanceof SqlLogicExpression) {
                        availableExpressions.remove(availableExpressions.size() - 1);
                        availableConditions.remove(availableConditions.size() - 1);
                    }
                }
            }
        }

        if (availableExpressions.size() > 0) {
            if (availableExpressions.get(0) instanceof SqlLogicExpression) {
                availableExpressions.remove(0);
                availableConditions.remove(0);
            }
        }

        for (String condition : availableConditions) {
            BuilderUtils.link(result, condition);
        }
        if (result.length() > 0 && parent != null) {
            return " ( " + result.toString() + " ) ";
        } else {
            return result.toString();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParamValue() {
        return getParamValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getParamValues() {
        List<Object> params = new ArrayList<>();
        for (Expression condition : conditions) {
            if (condition instanceof ParamedExpression) {
                Object param = ((ParamedExpression) condition).getParamValue();
                if (Lang.isNotEmpty(param)) {
                    if (param instanceof Collection) {
                        params.addAll((Collection<?>) param);
                    } else if (param.getClass().isArray()) {
                        int length = Array.getLength(param);
                        for (int i = 0; i < length; i++) {
                            params.add(Array.get(param, i));
                        }
                    } else {
                        params.add(param);
                    }
                }
            }
        }
        return params;
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    private void addCondition(Expression condition) {
        if (previousCondition != null) {
            if (previousCondition.getClass().isInstance(condition)) {
                throw new BuilderException(
                        BuilderExceptionCode.createNextToSameConditionCode(condition.getClass().getName()));
            }
        }
        previousCondition = condition;
        conditions.add(condition);
    }

    // ********************************************************************
    // property
    // ********************************************************************

    private List<Expression> conditions = new ArrayList<>();

    private Dialect dialect;

    private SqlConditionGroup parent;

    private SqlSortBuilder sort;

    private Expression previousCondition;

    private String queryAlias;

    /*
     * 忽略空值
     */
    private boolean ignoreEmpty = true;

    /**
     * Checks if is ignore empty.
     *
     * @return true, if is ignore empty
     */
    public boolean isIgnoreEmpty() {
        return ignoreEmpty;
    }

    /**
     * Sets the ignore empty.
     *
     * @param ignoreEmpty the new ignore empty
     */
    public void setIgnoreEmpty(boolean ignoreEmpty) {
        this.ignoreEmpty = ignoreEmpty;
    }

    /**
     * Gets the conditions.
     *
     * @return the conditions
     */
    public List<Expression> getConditions() {
        return conditions;
    }

    /**
     * 返回queryAlias.
     *
     * @return queryAlias
     */
    public String getQueryAlias() {
        return queryAlias;
    }

    /**
     * 设置queryAlias.
     *
     * @param queryAlias queryAlias
     */
    public void setQueryAlias(String queryAlias) {
        this.queryAlias = queryAlias;
        sort.setTableAlias(queryAlias);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConditionBuilder where() {
        return this;
    }
}
