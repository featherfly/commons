
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 15:22:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.util.Map;

/**
 * muliti query tuple mapper builder.
 *
 * @author zhongj
 */
public interface MulitiQueryTupleMapperBuilder {

    /**
     * Map.
     *
     * @return the muliti row mapper 1
     */
    MulitiQueryRowMapper1<Map<String, Object>> map();

    /**
     * Map.
     *
     * @param <T1> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 1
     */
    <T1> MulitiQueryRowMapper1<T1> map(Class<T1> mappingType);

    /**
     * Map.
     *
     * @param <T1> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 1
     */
    <T1> MulitiQueryRowMapper1<T1> map(RowMapper<T1> rowMapper);
}
