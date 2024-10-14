package cn.featherfly.common.repository.mapper;

import cn.featherfly.common.repository.ParamedMappedExecutor;
import cn.featherfly.common.tuple.Tuple3;

/**
 * prefixed bean mapper3.
 *
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 */
public interface PrefixedBeanMapper3<T1, T2, T3> extends ParamedMappedExecutor<Tuple3<T1, T2, T3>> {

    /**
     * Map.
     *
     * @param <T4> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 4
     */
    <T4> PrefixedBeanMapper4<T1, T2, T3, T4> map(String prefix, Class<T4> type);
}