package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

import cn.featherfly.common.lang.ClassUtils;

/**
 * prefixed bean mapper1.
 *
 * @author zhongj
 * @param <T> the generic type
 */
@FunctionalInterface
public interface PrefixedBeanMapper<T> extends Supplier<RowMapper<T>> {

    /** The map type. */
    Class<Map<String, Serializable>> MAP_TYPE = ClassUtils.castGenericType(Map.class,
        Collections.emptyMap());

    /**
     * Mapper.
     *
     * @return the row mapper
     */
    RowMapper<T> mapper();

    /**
     * {@inheritDoc}
     */
    @Override
    default RowMapper<T> get() {
        return mapper();
    }
}