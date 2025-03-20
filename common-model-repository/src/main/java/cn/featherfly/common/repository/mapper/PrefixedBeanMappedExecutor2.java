package cn.featherfly.common.repository.mapper;

import cn.featherfly.common.repository.ParamedMappedExecutor;
import cn.featherfly.common.tuple.Tuple2;

/**
 * prefixed bean mapper executor2.
 *
 * @param <T1> the generic type
 * @param <T2> the generic type
 */
public interface PrefixedBeanMappedExecutor2<T1, T2> extends ParamedMappedExecutor<Tuple2<T1, T2>> {

    /**
     * Map.
     *
     * @param <T3> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 3
     */
    <T3> PrefixedBeanMappedExecutor3<T1, T2, T3> map(String prefix, Class<T3> type);
}