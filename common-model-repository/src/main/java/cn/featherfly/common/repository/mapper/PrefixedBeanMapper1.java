package cn.featherfly.common.repository.mapper;

import cn.featherfly.common.repository.ParamedMappedExecutor;

/**
 * prefixed bean mapper1.
 *
 * @param <T1> the generic type
 */
public interface PrefixedBeanMapper1<T1> extends ParamedMappedExecutor<T1> {

    /**
     * Map.
     *
     * @param <T2>   the generic type
     * @param prefix the prefix
     * @param type   the type
     * @return the prefix bean property mapper 2
     */
    <T2> PrefixedBeanMapper2<T1, T2> map(String prefix, Class<T2> type);
}