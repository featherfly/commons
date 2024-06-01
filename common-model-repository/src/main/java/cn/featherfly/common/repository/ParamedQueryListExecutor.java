
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 14:41:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.mapper.RowMapper;

/**
 * ParamedQueryListExecutor.
 *
 * @author zhongj
 */
public interface ParamedQueryListExecutor {

    /**
     * query list.
     *
     * @return map
     */
    List<Map<String, Serializable>> list();

    /**
     * query list.
     *
     * @param <T> the generic type
     * @param mappingType the mapping type
     * @return mapped object
     */
    <T> List<T> list(Class<T> mappingType);

    /**
     * query list.
     *
     * @param <T> the generic type
     * @param rowMapper the row mapper
     * @return mapped object
     */
    <T> List<T> list(RowMapper<T> rowMapper);
}
