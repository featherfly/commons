package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple3;

/**
 * prefixed bean mapper3.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 */
public interface PrefixedBeanMapper3<T1, T2, T3> extends PrefixedBeanMapper<Tuple3<T1, T2, T3>> {

    /**
     * Map.
     *
     * @param prefix the prefix
     * @return the prefix bean property mapper 4
     */
    default PrefixedBeanMapper4<T1, T2, T3, Map<String, Serializable>> map(String prefix) {
        return map(prefix, MAP_TYPE);
    }

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