package cn.featherfly.common.structure.page;

/**
 * 分页模型.
 *
 * @author zhongj
 */
public interface Pagination extends Page {

    /**
     * 获取总页数.
     *
     * @return 总页数
     */
    Integer getTotalPage();

    /**
     * 获取总数.
     *
     * @return 总数
     */
    Integer getTotal();

    //    /**
    //     * <p>
    //     * 设置每页数量
    //     * </p>
    //     *
    //     * @param pageSize 每页数量
    //     */
    //    void setPageSize(Integer pageSize);
    //
    //    /**
    //     * <p>
    //     * 设置页数
    //     * </p>
    //     *
    //     * @param pageNumber 页数
    //     */
    //    void setPageNumber(Integer pageNumber);
    //
    //    /**
    //     * <p>
    //     * 设置总数
    //     * </p>
    //     *
    //     * @param total 总数
    //     */
    //    void setTotal(Integer total);
}
