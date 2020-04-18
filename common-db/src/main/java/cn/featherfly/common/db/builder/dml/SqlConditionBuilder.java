
package cn.featherfly.common.db.builder.dml;

import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * <p>
 * SqlConditoinBuilder
 * </p>
 *
 * @author zhongj
 */
public interface SqlConditionBuilder extends ConditionBuilder, SqlBuilder {
    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @return ConditionBuilder
     */
    ConditionBuilder where();

    // TODO 后续加入join 实现
    // JoinGroup join();
}
