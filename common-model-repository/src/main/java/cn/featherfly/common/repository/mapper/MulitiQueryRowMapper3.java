
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
import java.util.function.Function;

import cn.featherfly.common.tuple.Tuple3;

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
     * mapper.
     *
     * @return the muliti row mapper 4
     */
    MulitiQueryRowMapper4<T1, T2, T3, Map<String, Serializable>> mapper();

    /**
     * mapper.
     *
     * @param <T4> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 4
     */
    <T4> MulitiQueryRowMapper4<T1, T2, T3, T4> mapper(Class<T4> mappingType);

    /**
     * mapper.
     *
     * @param <T4> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 4
     */
    <T4> MulitiQueryRowMapper4<T1, T2, T3, T4> mapper(RowMapper<T4> rowMapper);

    /**
     * mapper.
     *
     * @param <T4> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 4
     */
    <T4> MulitiQueryRowMapper4<T1, T2, T3, T4> mapper(Function<TupleRowMapperBuilder, RowMapper<T4>> rowMapper);
}
