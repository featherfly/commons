package cn.featherfly.common.structure.page;



/**
 * <p>
 * 带结果集的分页模型
 * </p>
 * @param <E> 存放的对象类型
 * @author 钟冀
 */
public interface PaginationResults<E> extends Pagination{
    /**
     * <p>
     * 获取结果集
     * </p>
     * @return 结果集
     */
    Iterable<E> getPageResults();
    /**
     * <p>
     * 设置结果集
     * </p>
     * @param pageReults 结果集
     */
    void setPageResults(Iterable<E> pageReults);
    /**
     * <p>
     * 获取结果集数量
     * </p>
     * @return 结果数量 
     */
    Integer getResultSize();
}





