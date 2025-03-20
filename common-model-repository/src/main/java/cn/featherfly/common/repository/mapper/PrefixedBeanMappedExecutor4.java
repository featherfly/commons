package cn.featherfly.common.repository.mapper;

import cn.featherfly.common.repository.ParamedMappedExecutor;
import cn.featherfly.common.tuple.Tuple4;

/**
 * prefixed bean mapper executor4.
 *
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 */
public interface PrefixedBeanMappedExecutor4<T1, T2, T3, T4> extends ParamedMappedExecutor<Tuple4<T1, T2, T3, T4>> {

    /**
     * Map.
     *
     * @param <T5> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 5
     */
    <T5> PrefixedBeanMappedExecutor5<T1, T2, T3, T4, T5> map(String prefix, Class<T5> type);
}