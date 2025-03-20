package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.tuple.Tuple2;

/**
 * prefixed bean mapper2.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 */
public interface PrefixedBeanMapper2<T1, T2> extends PrefixedBeanMapper<Tuple2<T1, T2>> {

    /**
     * Map.
     *
     * @param prefix the prefix
     * @return the prefix bean property mapper 3
     */
    default PrefixedBeanMapper3<T1, T2, Map<String, Serializable>> map(String prefix) {
        return map(prefix, MAP_TYPE);
    }

    /**
     * Map.
     *
     * @param <T3> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 3
     */
    <T3> PrefixedBeanMapper3<T1, T2, T3> map(String prefix, Class<T3> type);
}