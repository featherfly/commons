
package cn.featherfly.common.repository.builder.dml;

/**
 * condition builder. 条件查询构造器，例如（ name = ?）.
 *
 * @author zhongj
 */
public interface ConditionBuilder extends ExpressionBuilder, ParamedExpression {

    //    LogicBuilder repository(String repository, Consumer<ExpressionBuilder> consumer);
    //
    //    LogicBuilder repository(int repositoryIndex, Consumer<ExpressionBuilder> consumer);
}
