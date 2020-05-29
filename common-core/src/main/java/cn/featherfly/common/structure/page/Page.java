package cn.featherfly.common.structure.page;

/**
 * <p>
 * 分页模型
 * </p>
 *
 * @author zhongj
 */
public interface Page {
    /**
     * <p>
     * 获取每页数量
     * </p>
     *
     * @return 每页数量
     * @deprecated {@link #getSize()}
     */
    @Deprecated
    Integer getPageSize();

    /**
     * <p>
     * 获取每页数量
     * </p>
     *
     * @return 每页数量
     */
    Integer getSize();

    /**
     * <p>
     * 获取当前页数（第几页）
     * </p>
     *
     * @return 当前页数（第几页）
     * @deprecated {@link #getNumber()}
     */
    @Deprecated
    Integer getPageNumber();

    /**
     * <p>
     * 获取当前页数（第几页）
     * </p>
     *
     * @return 当前页数（第几页）
     */
    Integer getNumber();
}
