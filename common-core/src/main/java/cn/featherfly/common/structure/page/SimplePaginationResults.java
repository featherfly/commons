package cn.featherfly.common.structure.page;

import java.util.List;

/**
 * 带结果集的简单分页模型实现.
 *
 * @param <E> 存放的对象类型
 * @author zhongj
 */
public class SimplePaginationResults<E> extends SimplePagination implements PaginationResults<E> {

    /**
     */
    public SimplePaginationResults() {
    }

    /**
     * @param limit limit
     */
    public SimplePaginationResults(Limit limit) {
        super(limit);
    }

    /**
     * @param offset offset
     * @param limit  limit
     */
    public SimplePaginationResults(int offset, int limit) {
        super(offset, limit);
    }

    private List<E> pageResults;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPageResults(List<E> pageResults) {
        this.pageResults = pageResults;
    }

    /**
     * 返回resultSize
     *
     * @return resultSize
     */
    @Override
    public Integer getResultSize() {
        if (pageResults != null) {
            return pageResults.size();
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> getPageResults() {
        return pageResults;
    }

}
