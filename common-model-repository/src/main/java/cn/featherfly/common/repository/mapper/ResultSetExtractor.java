
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 16:53:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

/**
 * ResultSetExtractor.
 *
 * @author zhongj
 * @param <E> the element type
 * @param <R> the generic type
 */
public interface ResultSetExtractor<E, R extends ResultSet> {

    /**
     * Map.
     *
     * @param resultSet the result set
     * @return the r
     */
    E extract(R resultSet);
}
