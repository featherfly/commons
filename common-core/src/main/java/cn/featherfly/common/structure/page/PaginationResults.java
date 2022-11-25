package cn.featherfly.common.structure.page;

import java.util.List;

/**
 * <p>
 * 带结果集的分页模型
 * </p>
 *
 * @param <E> 存放的对象类型
 * @author zhongj
 */
public interface PaginationResults<E> extends Pagination {
    /**
     * 获取结果集.
     *
     * @return 结果集
     */
    List<E> getPageResults();

    /**
     * 设置结果集.
     *
     * @param pageReults 结果集
     */
    void setPageResults(List<E> pageReults);

    /**
     * 获取结果集数量.
     *
     * @return 结果数量
     */
    Integer getResultSize();
}
