
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.util.Map;

import cn.featherfly.common.repository.mapper.RowMapper;

/**
 * ParamedQuerySingleExecutor.
 *
 * @author zhongj
 */
public interface ParamedQuerySingleExecutor {

    /**
     * query single.
     *
     * @return map
     */
    Map<String, Object> single();

    /**
     * query single.
     *
     * @param <T> the generic type
     * @param mappingType the mapping type
     * @return mapped object
     */
    <T> T single(Class<T> mappingType);

    /**
     * query single.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @return mapped object
     */
    <T> T single(RowMapper<T> rowMapper);
}
