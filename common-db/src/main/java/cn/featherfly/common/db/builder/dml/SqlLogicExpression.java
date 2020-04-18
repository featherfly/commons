
package cn.featherfly.common.db.builder.dml;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.repository.builder.dml.LogicExpression;
import cn.featherfly.common.repository.operate.LogicOperator;

/**
 * <p>
 * sql logic expression
 * </p>
 *
 * @author zhongj
 */
public class SqlLogicExpression extends LogicExpression implements SqlBuilder {

    /**
     * @param logicOperator logicOperator
     */
    public SqlLogicExpression(LogicOperator logicOperator) {
        super(logicOperator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        return getLogicOperator().toString();
    }
}
