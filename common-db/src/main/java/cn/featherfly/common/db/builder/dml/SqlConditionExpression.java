
package cn.featherfly.common.db.builder.dml;

import java.util.function.Predicate;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.model.ConditionColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.operator.QueryOperator;
import cn.featherfly.common.repository.builder.BuilderException;
import cn.featherfly.common.repository.builder.BuilderExceptionCode;
import cn.featherfly.common.repository.builder.dml.ParamedExpression;

/**
 * <p>
 * sql condition expression sql 条件表达式
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlConditionExpression implements ParamedExpression, SqlBuilder {

    private ConditionColumnElement conditionColumnElement;

    /**
     * Instantiates a new sql condition expression.
     *
     * @param dialect        dialect
     * @param name           名称
     * @param value          值
     * @param queryOperator  查询运算符（查询类型）
     * @param ignoreStrategy the ignore strategy
     */
    SqlConditionExpression(Dialect dialect, String name, Object value, QueryOperator queryOperator,
            Predicate<?> ignoreStrategy) {
        this(dialect, name, value, queryOperator, null, ignoreStrategy);
    }

    /**
     * Instantiates a new sql condition expression.
     *
     * @param dialect        dialect
     * @param name           名称
     * @param value          值
     * @param queryOperator  查询运算符（查询类型）
     * @param queryAlias     查询别名
     * @param ignoreStrategy the ignore strategy
     */
    SqlConditionExpression(Dialect dialect, String name, Object value, QueryOperator queryOperator, String queryAlias,
            Predicate<?> ignoreStrategy) {
        if (queryOperator == null) {
            throw new BuilderException(BuilderExceptionCode.createQueryOperatorNullCode());
        }
        conditionColumnElement = new ConditionColumnElement(dialect, name, value, queryOperator, queryAlias,
                ignoreStrategy);
    }

    /**
     * 返回conditionColumnElement.
     *
     * @return conditionColumnElement
     */
    public ConditionColumnElement getConditionColumnElement() {
        return conditionColumnElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParamValue() {
        return conditionColumnElement.getParam();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return conditionColumnElement.toSql();
    }
}
