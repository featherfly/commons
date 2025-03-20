
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 00:18:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.tuple.Tuple5;

/**
 * MulitiRowMapper5.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 * @param <T5> the generic type
 */
public interface MulitiQueryRowMapper5<T1, T2, T3, T4, T5>
    extends MulitiQueryRowMapper<Tuple5<List<T1>, List<T2>, List<T3>, List<T4>, List<T5>>> {
    /**
     * mapper.
     *
     * @return the muliti row mapper 6
     */
    MulitiQueryRowMapper6<T1, T2, T3, T4, T5, Map<String, Serializable>> mapper();

    /**
     * mapper.
     *
     * @param <T6> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 6
     */
    <T6> MulitiQueryRowMapper6<T1, T2, T3, T4, T5, T6> mapper(Class<T6> mappingType);

    /**
     * mapper.
     *
     * @param <T6> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 2
     */
    <T6> MulitiQueryRowMapper6<T1, T2, T3, T4, T5, T6> mapper(RowMapper<T6> rowMapper);

    /**
     * mapper.
     *
     * @param <T6> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 2
     */
    <T6> MulitiQueryRowMapper6<T1, T2, T3, T4, T5, T6> mapper(Function<TupleRowMapperBuilder, RowMapper<T6>> rowMapper);
}
