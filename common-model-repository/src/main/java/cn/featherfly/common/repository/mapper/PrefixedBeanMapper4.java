package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple4;

/**
 * prefixed bean mapper4.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 */
public interface PrefixedBeanMapper4<T1, T2, T3, T4> extends PrefixedBeanMapper<Tuple4<T1, T2, T3, T4>> {

    /**
     * Map.
     *
     * @param prefix the prefix
     * @return the prefix bean property mapper 5
     */
    default PrefixedBeanMapper5<T1, T2, T3, T4, Map<String, Serializable>> map(String prefix) {
        return map(prefix, MAP_TYPE);
    }

    /**
     * Map.
     *
     * @param <T5> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 5
     */
    <T5> PrefixedBeanMapper5<T1, T2, T3, T4, T5> map(String prefix, Class<T5> type);
}