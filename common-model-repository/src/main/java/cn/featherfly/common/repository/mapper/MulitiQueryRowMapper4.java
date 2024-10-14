
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-04-20 00:18:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple4;

/**
 * MulitiRowMapper4.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 */
public interface MulitiQueryRowMapper4<T1, T2, T3, T4>
    extends MulitiQueryRowMapper<Tuple4<List<T1>, List<T2>, List<T3>, List<T4>>> {
    /**
     * Map.
     *
     * @return the muliti row mapper 5
     */
    MulitiQueryRowMapper5<T1, T2, T3, T4, Map<String, Serializable>> map();

    /**
     * Map.
     *
     * @param <T5> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 5
     */
    <T5> MulitiQueryRowMapper5<T1, T2, T3, T4, T5> map(Class<T5> mappingType);

    /**
     * Map.
     *
     * @param <T5> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 5
     */
    <T5> MulitiQueryRowMapper5<T1, T2, T3, T4, T5> map(RowMapper<T5> rowMapper);
}
