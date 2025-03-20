package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple5;

/**
 * prefixed bean mapper5.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 * @param <T5> the generic type
 */
public interface PrefixedBeanMapper5<T1, T2, T3, T4, T5> extends PrefixedBeanMapper<Tuple5<T1, T2, T3, T4, T5>> {

    /**
     * Map.
     *
     * @param prefix the prefix
     * @return the prefix bean property mapper 6
     */
    default PrefixedBeanMapper6<T1, T2, T3, T4, T5, Map<String, Serializable>> map(String prefix) {
        return map(prefix, MAP_TYPE);
    }

    /**
     * Map.
     *
     * @param <T6> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 6
     */
    <T6> PrefixedBeanMapper6<T1, T2, T3, T4, T5, T6> map(String prefix, Class<T6> type);
}