package cn.featherfly.common.structure.page;

/**
 * <p>
 * 简单分页模型实现
 * </p>
 *
 * @author 钟冀
 */
public class SimplePagination<E> implements PaginationResults<E>{

	private Integer total;

	private Integer pageSize;

	private Integer pageNumber;

	private Iterable<E> pageResults;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPageResults(Iterable<E> pageResults) {
		this.pageResults = pageResults;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTotal(Integer total) {
		this.total = total;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getTotalPage() {
		return (getTotal() + pageSize - 1) / pageSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getTotal() {
		return total;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<E> getPageResults() {
		return pageResults;
	}
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Pagination<E> next() {
//		return to(pageNumber + 1);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Pagination<E> previous() {
//		return to(pageNumber - 1);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Pagination<E> first() {
//		return to(1);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Pagination<E> last() {
//		return to(getTotalPage());
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Pagination<E> to(Integer pageNumber) {
//		this.pageNumber = pageNumber;
////		setPageResults(null);
//		return this;
//	}
}
