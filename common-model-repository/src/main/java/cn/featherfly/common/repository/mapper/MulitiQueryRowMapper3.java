
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-04-20 00:18:20
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.speedment.common.tuple.Tuple3;

/**
 * MulitiRowMapper3.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 */
public interface MulitiQueryRowMapper3<T1, T2, T3> extends MulitiQueryRowMapper<Tuple3<List<T1>, List<T2>, List<T3>>> {
    /**
     * Map.
     *
     * @return the muliti row mapper 4
     */
    MulitiQueryRowMapper4<T1, T2, T3, Map<String, Serializable>> map();

    /**
     * Map.
     *
     * @param <T4> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 4
     */
    <T4> MulitiQueryRowMapper4<T1, T2, T3, T4> map(Class<T4> mappingType);

    /**
     * Map.
     *
     * @param <T4> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 4
     */
    <T4> MulitiQueryRowMapper4<T1, T2, T3, T4> map(RowMapper<T4> rowMapper);
}
