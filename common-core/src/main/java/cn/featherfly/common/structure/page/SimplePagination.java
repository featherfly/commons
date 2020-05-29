package cn.featherfly.common.structure.page;

/**
 * <p>
 * 简单分页模型实现
 * </p>
 * .
 *
 * @author zhongj
 */
public class SimplePagination implements Pagination {

    /**
     * Instantiates a new simple pagination.
     */
    public SimplePagination() {
    }

    /**
     * Instantiates a new simple pagination.
     *
     * @param limit limit
     */
    public SimplePagination(Limit limit) {
        this(limit.getOffset(), limit.getLimit());
    }

    /**
     * Instantiates a new simple pagination.
     *
     * @param offset offset
     * @param limit  limit
     */
    public SimplePagination(int offset, int limit) {
        size = limit;
        number = (offset + limit) / limit;
    }

    private Integer total;

    private Integer size;

    private Integer number;

    /**
     * <p>
     * 设置总数
     * </p>
     * .
     *
     * @param total 总数
     * @return the simple pagination
     */
    public SimplePagination setTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotalPage() {
        return (getTotal() + size - 1) / size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotal() {
        return total;
    }

    /**
     * <p>
     * 设置每页数量
     * </p>
     * .
     *
     * @param size 每页数量
     * @return the simple pagination
     */
    public SimplePagination setSize(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * <p>
     * 设置当前页数（第几页）
     * </p>
     * .
     *
     * @param number 当前页数（第几页）
     * @return the simple pagination
     */
    public SimplePagination setNumber(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNumber() {
        return number;
    }

    /**
     * <p>
     * 设置每页数量
     * </p>
     * .
     *
     * @param pageSize 每页数量
     * @deprecated {@link #setSize(Integer)}
     */
    @Deprecated
    public void setPageSize(Integer pageSize) {
        size = pageSize;
    }

    /**
     * <p>
     * 设置页数
     * </p>
     * .
     *
     * @param pageNumber 页数
     * @deprecated {@link #setNumber(Integer)}
     */
    @Deprecated
    public void setPageNumber(Integer pageNumber) {
        number = pageNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Integer getPageSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Integer getPageNumber() {
        return number;
    }
}
