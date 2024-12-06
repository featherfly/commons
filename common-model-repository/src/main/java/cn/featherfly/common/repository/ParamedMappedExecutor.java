
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.util.List;

import cn.featherfly.common.structure.page.Limit;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.PaginationResults;

/**
 * ParamedMappedExecutor.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface ParamedMappedExecutor<T> {
    /**
     * query single.
     *
     * @return mapped object
     */
    T single();

    /**
     * query unique.
     *
     * @return mapped object
     */
    T unique();

    /**
     * query each.
     *
     * @return mapped object list
     */
    RowIterable<T> each();

    /**
     * query each.
     *
     * @param offset the offset
     * @param limit the limit
     * @return mapped object list
     */
    RowIterable<T> each(int offset, int limit);

    /**
     * query list.
     *
     * @return mapped object list
     */
    List<T> list();

    /**
     * query list.
     *
     * @param offset the offset
     * @param limit the limit
     * @return mapped object list
     */
    List<T> list(int offset, int limit);

    /**
     * query list.
     *
     * @param page the page
     * @return mapped object list
     */
    default List<T> list(Page page) {
        Limit limit = new Limit(page);
        return list(limit.getOffset(), limit.getLimit());
    }

    /**
     * query page.
     *
     * @param offset the offset
     * @param limit the limit
     * @return mapped object pagination
     */
    PaginationResults<T> pagination(int offset, int limit);

    /**
     * query page.
     *
     * @param page the page
     * @return mapped object pagination
     */
    default PaginationResults<T> pagination(Page page) {
        Limit limit = new Limit(page);
        return pagination(limit.getOffset(), limit.getLimit());
    }
}
