
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
 * ParamedQueryUniqueExecutor.
 *
 * @author zhongj
 */
public interface ParamedQueryUniqueExecutor {

    /**
     * query unique.
     *
     * @return map
     */
    Map<String, Object> unique();

    /**
     * query unique.
     *
     * @param <T> the generic type
     * @param mappingType the mapping type
     * @return mapped object
     */
    <T> T unique(Class<T> mappingType);

    /**
     * query unique.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @return mapped object
     */
    <T> T unique(RowMapper<T> rowMapper);
}
