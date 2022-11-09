
package cn.featherfly.common.repository.builder.dml;

/**
 * 逻辑构造器.
 *
 * @author zhongj
 */
public interface LogicBuilder {
    /**
     * <p>
     * 逻辑与
     * </p>
     *
     * @return ExpressionBuilder
     */
    ExpressionBuilder and();

    /**
     * <p>
     * 逻辑或
     * </p>
     *
     * @return ExpressionBuilder
     */
    ExpressionBuilder or();

    /**
     * 结束当前条件逻辑组并返回上一级逻辑组 {@link ExpressionBuilder#group()}
     *
     * @return parent LogicBuilder
     */
    LogicBuilder endGroup();

    /**
     * 结束当前条件并进入排序器
     *
     * @return SortBuilder
     */
    SortBuilder sort();
}
