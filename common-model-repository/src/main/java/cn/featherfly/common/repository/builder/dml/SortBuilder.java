package cn.featherfly.common.repository.builder.dml;

import cn.featherfly.common.repository.builder.Builder;

/**
 * <p>
 * 排序构建接口
 * </p>
 *
 * @author zhongj
 */
public interface SortBuilder extends Builder {
    /**
     * <p>
     * 添加升序条件
     * </p>
     * 
     * @param names
     *            名称
     * @return this
     */
    SortBuilder asc(String... names);

    /**
     * <p>
     * 添加降序条件
     * </p>
     * 
     * @param names
     *            名称
     * @return this
     */
    SortBuilder desc(String... names);

}