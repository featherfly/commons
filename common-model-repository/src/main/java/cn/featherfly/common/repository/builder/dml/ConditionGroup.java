
package cn.featherfly.common.repository.builder.dml;

import java.util.List;

/**
 * ConditionGroup.
 *
 * @author zhongj
 */
public interface ConditionGroup extends ConditionBuilder, LogicBuilder, ParamedExpression {
    /**
     * 返回参数值.
     *
     * @return 参数
     */
    List<Object> getParamValues();
}