
package cn.featherfly.common.repository;

import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.mapper.RowMapper;

/**
 * MulitiQuery.
 *
 * @author zhongj
 */
public interface MulitiQuery extends AutoCloseable {

    /**
     * Checks for next.
     *
     * @return true, if successful
     */
    boolean hasNext();

    /**
     * Next.
     *
     * @return the list
     */
    List<Map<String, Object>> next();

    /**
     * Next.
     *
     * @param <E> the element type
     * @param elementType the element type
     * @return the list
     */
    <E> List<E> next(Class<E> elementType);

    /**
     * Next.
     *
     * @param <E> the element type
     * @param mapper the mapper
     * @return the list
     */
    <E> List<E> next(RowMapper<E> mapper);

    /**
     * Gets the index.
     *
     * @return the index
     */
    int getIndex();
}
