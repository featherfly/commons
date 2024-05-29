package cn.featherfly.common.structure.page;

/**
 * 简单分页模型实现 .
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
     * @param limit limit
     */
    public SimplePagination(int offset, int limit) {
        size = limit;
        number = (offset + limit) / limit;
    }

    private long total;

    private int size;

    private int number;

    /**
     * 设置总数 .
     *
     * @param total 总数
     * @return the simple pagination
     */
    public SimplePagination setTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTotalPage() {
        return (getTotal() + size - 1) / size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTotal() {
        return total;
    }

    /**
     * 设置每页数量 .
     *
     * @param size 每页数量
     * @return the simple pagination
     */
    public SimplePagination setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置当前页数（第几页） .
     *
     * @param number 当前页数（第几页）
     * @return the simple pagination
     */
    public SimplePagination setNumber(int number) {
        this.number = number;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }
}
