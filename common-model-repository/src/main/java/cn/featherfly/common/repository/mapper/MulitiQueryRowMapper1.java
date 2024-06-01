
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

import com.speedment.common.tuple.Tuple1;

/**
 * MulitiRowMapper1.
 *
 * @author zhongj
 * @param <T1> the generic type
 */
public interface MulitiQueryRowMapper1<T1> extends MulitiQueryRowMapper<Tuple1<List<T1>>> {
    /**
     * Map.
     *
     * @return the muliti row mapper 2
     */
    MulitiQueryRowMapper2<T1, Map<String, Serializable>> map();

    /**
     * Map.
     *
     * @param <T2> the generic type
     * @param mappingType the mapping type
     * @return the muliti row mapper 2
     */
    <T2> MulitiQueryRowMapper2<T1, T2> map(Class<T2> mappingType);

    /**
     * Map.
     *
     * @param <T2> the generic type
     * @param rowMapper the row mapper
     * @return the muliti row mapper 2
     */
    <T2> MulitiQueryRowMapper2<T1, T2> map(RowMapper<T2> rowMapper);

    /**
     * Gets the row mapper.
     *
     * @return the row mapper
     */
    RowMapper<T1> getRowMapper1();
}
