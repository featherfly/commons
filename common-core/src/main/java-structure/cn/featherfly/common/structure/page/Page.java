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
     */
    Integer getPageSize();

    /**
     * <p>
     * 获取页数
     * </p>
     * 
     * @return 页数
     */
    Integer getPageNumber();
}
