
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-05-20 00:18:20
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple2;

/**
 * MulitiRowMapper2.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 */
public interface MulitiQueryRowMapper2<T1, T2> extends MulitiQueryRowMapper<Tuple2<List<T1>, List<T2>>> {
    /**
     * Map.
     *
     * @return the muliti row mapper 3
     */
    MulitiQueryRowMapper3<T1, T2, Map<String, Serializable>> map();

    /**
     * Map.
     *
     * @param <T3> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 3
     */
    <T3> MulitiQueryRowMapper3<T1, T2, T3> map(Class<T3> mappingType);

    /**
     * Map.
     *
     * @param <T3> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 3
     */
    <T3> MulitiQueryRowMapper3<T1, T2, T3> map(RowMapper<T3> rowMapper);
}
