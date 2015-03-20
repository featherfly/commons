package cn.featherfly.common.structure.page;



/**
 * <p>
 * 分页模型
 * </p>
 *
 * @author 钟冀
 */
public interface Pagination {
	/**
	 * <p>
	 * 获取每页数量
	 * </p>
	 * @return 每页数量
	 */
	Integer getPageSize();
	/**
	 * <p>
	 * 获取页数
	 * </p>
	 * @return 页数
	 */
	Integer getPageNumber();
	/**
	 * <p>
	 * 获取总页数
	 * </p>
	 * @return 总页数
	 */
	Integer getTotalPage();
	/**
	 * <p>
	 * 获取总数
	 * </p>
	 * @return 总数
	 */
	Integer getTotal();
	/**
	 * <p>
	 * 设置每页数量
	 * </p>
	 * @param pageSize 每页数量
	 */
	void setPageSize(Integer pageSize);
	/**
	 * <p>
	 * 设置页数
	 * </p>
	 * @param pageNumber 页数
	 */
	void setPageNumber(Integer pageNumber);
	/**
	 * <p>
	 * 设置总数
	 * </p>
	 * @param total 总数
	 */
	void setTotal(Integer total);
}





