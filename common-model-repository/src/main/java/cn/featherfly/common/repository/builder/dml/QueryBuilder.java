
package cn.featherfly.common.repository.builder.dml;

import cn.featherfly.common.repository.builder.Builder;

/**
 * sql query builder.
 *
 * @author zhongj
 */
public interface QueryBuilder extends Builder {
    /**
     * 进入条件表达式.
     *
     * @param target target
     * @return ExpressionBuilder
     */
    FindBuilder find(String target);

    /**
     * 进入条件表达式.
     *
     * @param target target
     * @param alias  alias
     * @return ExpressionBuilder
     */
    FindBuilder find(String target, String alias);
}
