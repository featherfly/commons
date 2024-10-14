
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 00:18:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.util.List;

import cn.featherfly.common.tuple.Tuple6;

/**
 * MulitiRowMapper6.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 * @param <T5> the generic type
 * @param <T6> the generic type
 */
public interface MulitiQueryRowMapper6<T1, T2, T3, T4, T5, T6>
    extends MulitiQueryRowMapper<Tuple6<List<T1>, List<T2>, List<T3>, List<T4>, List<T5>, List<T6>>> {
}
