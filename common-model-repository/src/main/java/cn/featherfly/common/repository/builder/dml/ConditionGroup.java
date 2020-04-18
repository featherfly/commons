
package cn.featherfly.common.repository.builder.dml;

import java.util.List;

/**
 * <p>
 * ConditionGroup
 * </p>
 * 
 * @author zhongj
 */
public interface ConditionGroup extends ConditionBuilder, LogicBuilder , ParamedExpression {
    /**
     * <p>
     * 返回参数值
     * </p>
     * @return 参数
     */
    List<Object> getParamValues();
}