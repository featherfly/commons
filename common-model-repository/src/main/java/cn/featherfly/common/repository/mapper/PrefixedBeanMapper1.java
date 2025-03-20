package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Map;

/**
 * prefixed bean mapper1.
 *
 * @author zhongj
 * @param <T1> the generic type
 */
public interface PrefixedBeanMapper1<T1> extends PrefixedBeanMapper<T1> {

    /**
     * Map.
     *
     * @param prefix the prefix
     * @return the prefix bean property mapper 2
     */
    default PrefixedBeanMapper2<T1, Map<String, Serializable>> map(String prefix) {
        return map(prefix, MAP_TYPE);
    }

    /**
     * Map.
     *
     * @param <T2> the generic type
     * @param prefix the prefix
     * @param type the type
     * @return the prefix bean property mapper 2
     */
    <T2> PrefixedBeanMapper2<T1, T2> map(String prefix, Class<T2> type);
}