
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
     * 添加select的列.
     *
     * @param propertyName propertyName
     * @return FindBuilder
     */
    FindBuilder property(String propertyName);

    /**
     * 批量添加select的列.
     *
     * @param propertyNames propertyNames
     * @return FindBuilder
     */
    FindBuilder property(String... propertyNames);

    /**
     * 批量添加select的列.
     *
     * @param propertyNames propertyNames
     * @return FindBuilder
     */
    FindBuilder property(Collection<String> propertyNames);

    /**
     * 进入条件表达式.
     *
     * @return ConditionBuilder
     */
    ConditionBuilder where();
}
