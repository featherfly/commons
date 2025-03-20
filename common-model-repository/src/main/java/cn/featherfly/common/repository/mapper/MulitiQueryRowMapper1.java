
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

import cn.featherfly.common.tuple.Tuple1;

/**
 * MulitiRowMapper1.
 *
 * @author zhongj
 * @param <T1> the generic type
 */
public interface MulitiQueryRowMapper1<T1> extends MulitiQueryRowMapper<Tuple1<List<T1>>> {
    /**
     * mapper.
     *
     * @return the muliti row mapper 2
     */
    MulitiQueryRowMapper2<T1, Map<String, Serializable>> mapper();

    /**
     * mapper.
     *
     * @param <T2> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 2
     */
    <T2> MulitiQueryRowMapper2<T1, T2> mapper(Class<T2> mappingType);

    /**
     * mapper.
     *
     * @param <T2> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 2
     */
    <T2> MulitiQueryRowMapper2<T1, T2> mapper(RowMapper<T2> rowMapper);

    /**
     * mapper.
     *
     * @param <T2> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 2
     */
    <T2> MulitiQueryRowMapper2<T1, T2> mapper(Function<TupleRowMapperBuilder, RowMapper<T2>> rowMapper);
}
