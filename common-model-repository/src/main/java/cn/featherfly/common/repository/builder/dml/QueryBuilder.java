
package cn.featherfly.common.repository.builder.dml;

import cn.featherfly.common.repository.builder.Builder;

/**
 * <p>
 * sql query builder
 * </p>
 *
 * @author zhongj
 */
public interface QueryBuilder extends Builder {
    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @param target target
     * @return ExpressionBuilder
     */
    FindBuilder find(String target);

    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @param target target
     * @param alias  alias
     * @return ExpressionBuilder
     */
    FindBuilder find(String target, String alias);
}
