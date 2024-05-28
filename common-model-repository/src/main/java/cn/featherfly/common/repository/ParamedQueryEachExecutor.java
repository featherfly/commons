
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.util.Map;

import cn.featherfly.common.lang.AutoCloseableIterable;
import cn.featherfly.common.repository.mapper.RowMapper;

/**
 * ParamedQueryEachExecutor.
 *
 * @author zhongj
 */
public interface ParamedQueryEachExecutor {

    /**
     * query each.
     *
     * @return map
     */
    AutoCloseableIterable<Map<String, Object>> each();

    /**
     * query each.
     *
     * @param <T> the generic type
     * @param mappingType the mapping type
     * @return mapped object
     */
    <T> AutoCloseableIterable<T> each(Class<T> mappingType);

    /**
     * query each.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @return mapped object
     */
    <T> AutoCloseableIterable<T> each(RowMapper<T> rowMapper);
}
