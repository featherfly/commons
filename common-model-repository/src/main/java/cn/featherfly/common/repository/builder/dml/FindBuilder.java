
package cn.featherfly.common.repository.builder.dml;

import java.util.Collection;

import cn.featherfly.common.repository.builder.Builder;

/**
 * find target builder 查找目标构造器.
 *
 * @author zhongj
 */
public interface FindBuilder extends Builder {

    /**
     * <p>
     * 添加select的列
     * </p>
     *
     * @param propertyName propertyName
     * @return FindBuilder
     */
    FindBuilder property(String propertyName);

    /**
     * <p>
     * 批量添加select的列
     * </p>
     *
     * @param propertyNames propertyNames
     * @return FindBuilder
     */
    FindBuilder property(String... propertyNames);

    /**
     * <p>
     * 批量添加select的列
     * </p>
     *
     * @param propertyNames propertyNames
     * @return FindBuilder
     */
    FindBuilder property(Collection<String> propertyNames);

    /**
     * <p>
     * 进入条件表达式
     * </p>
     *
     * @return ConditionBuilder
     */
    ConditionBuilder where();
}
