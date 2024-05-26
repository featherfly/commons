
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.mapper.RowMapper;
import cn.featherfly.common.structure.page.Limit;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.PaginationResults;

/**
 * ParamedQueryPageExecutor.
 *
 * @author zhongj
 */
public interface ParamedQueryPageExecutor {

    /**
     * query paged list.
     *
     * @param offset the offset
     * @param limit the limit
     * @return paged map list
     */
    List<Map<String, Object>> list(int offset, int limit);

    /**
     * query paged list.
     *
     * @param page the page
     * @return paged map list
     */
    default List<Map<String, Object>> list(Page page) {
        Limit limit = new Limit(page);
        return list(limit.getOffset(), limit.getLimit());
    }

    /**
     * query paged list.
     *
     * @param <T> the element type
     * @param mappingType the map type
     * @param offset the offset
     * @param limit the limit
     * @return paged map object list
     */
    <T> List<T> list(Class<T> mappingType, int offset, int limit);

    /**
     * query paged list.
     *
     * @param <T> the element type
     * @param mappingType the map type
     * @param page the page
     * @return paged map object list
     */
    default <T> List<T> list(Class<T> mappingType, Page page) {
        Limit limit = new Limit(page);
        return list(mappingType, limit.getOffset(), limit.getLimit());
    }

    /**
     * query paged list.
     *
     * @param <T> the element type
     * @param rowMapper the row mapper
     * @param offset the offset
     * @param limit the limit
     * @return paged map object list
     */
    <T> List<T> list(RowMapper<T> rowMapper, int offset, int limit);

    /**
     * query paged list.
     *
     * @param <T> the element type
     * @param rowMapper the row mapper
     * @param page the page
     * @return paged map object list
     */
    default <T> List<T> list(RowMapper<T> rowMapper, Page page) {
        Limit limit = new Limit(page);
        return list(rowMapper, limit.getOffset(), limit.getLimit());
    }

    /**
     * query page.
     *
     * @param offset the offset
     * @param limit the limit
     * @return map pagination
     */
    PaginationResults<Map<String, Object>> pagination(int offset, int limit);

    /**
     * query page.
     *
     * @param page the page
     * @return map pagination
     */
    default PaginationResults<Map<String, Object>> pagination(Page page) {
        Limit limit = new Limit(page);
        return pagination(limit.getOffset(), limit.getLimit());
    }

    /**
     * query page.
     *
     * @param <T> the generic type
     * @param mappingType the map type
     * @param offset the offset
     * @param limit the limit
     * @return map object pagination
     */
    <T> PaginationResults<T> pagination(Class<T> mappingType, int offset, int limit);

    /**
     * query page.
     *
     * @param <T> the generic type
     * @param mappingType the map type
     * @param page the page
     * @return map object pagination
     */
    default <T> PaginationResults<T> pagination(Class<T> mappingType, Page page) {
        Limit limit = new Limit(page);
        return pagination(mappingType, limit.getOffset(), limit.getLimit());
    }

    /**
     * query page.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @param offset the offset
     * @param limit the limit
     * @return map object pagination
     */
    <T> PaginationResults<T> pagination(RowMapper<T> rowMapper, int offset, int limit);

    /**
     * query page.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @param page the page
     * @return map object pagination
     */
    default <T> PaginationResults<T> pagination(RowMapper<T> rowMapper, Page page) {
        Limit limit = new Limit(page);
        return pagination(rowMapper, limit.getOffset(), limit.getLimit());
    }
}
