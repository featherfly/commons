
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 19:48:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.mapper.RowMapper;
import cn.featherfly.common.structure.page.Limit;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.PaginationResults;

/**
 * ArrayParamsQueryExecutor.
 *
 * @author zhongj
 * @param <E0> the generic type
 */
public interface ArrayParamsQueryExecutor<E0> {

    /**
     * query boolean value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return boolean
     */
    boolean bool(E0 execution, Serializable... params);

    /**
     * query int value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return int
     */
    int intValue(E0 execution, Serializable... params);

    /**
     * query long value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return long
     */
    long longValue(E0 execution, Serializable... params);

    /**
     * query double value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return double
     */
    double doubleValue(E0 execution, Serializable... params);

    /**
     * query value, use query str in template find with executeId.
     *
     * @param <V> the value type
     * @param execution the execution
     * @param params the params
     * @return value
     */
    <V> V value(E0 execution, Serializable... params);

    /**
     * query value, use query str in template find with executeId.
     *
     * @param <V> the value type
     * @param execution the execution
     * @param valueType the value type
     * @param params the params
     * @return value
     */
    <V> V value(E0 execution, Class<V> valueType, Serializable... params);

    /**
     * query number value, use query str in template find with executeId.
     *
     * @param <N> the number type
     * @param execution the execution
     * @param numberType the number type
     * @param params the params
     * @return number value
     */
    default <N extends Number> N number(E0 execution, Class<N> numberType, Serializable... params) {
        return value(execution, numberType, params);
    }

    /**
     * query int value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return Integer
     */
    default Integer numberInt(E0 execution, Serializable... params) {
        return value(execution, Integer.class, params);
    }

    /**
     * query long value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return Long
     */
    default Long numberLong(E0 execution, Serializable... params) {
        return value(execution, Long.class, params);
    }

    /**
     * query double value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return BigDecimal
     */
    default Double numberDouble(E0 execution, Serializable... params) {
        return value(execution, Double.class, params);
    }

    /**
     * query BigInteger value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return BigInteger
     */
    default BigInteger numberBigInteger(E0 execution, Serializable... params) {
        return value(execution, BigInteger.class, params);
    }

    /**
     * query BigDecimal value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return BigDecimal
     */
    default BigDecimal numberBigDecimal(E0 execution, Serializable... params) {
        return value(execution, BigDecimal.class, params);
    }

    /**
     * query boolean value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return boolean
     */
    default Boolean boolType(E0 execution, Serializable... params) {
        return value(execution, Boolean.class, params);
    }

    /**
     * query string value, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return String
     */
    default String string(E0 execution, Serializable... params) {
        return value(execution, String.class, params);
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query single, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return map
     */
    Map<String, Serializable> single(E0 execution, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T> the generic type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @return entity
     */
    <T> T single(E0 execution, Class<T> mappingType, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T> the generic type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @return entity
     */
    <T> T single(E0 execution, RowMapper<T> rowMapper, Serializable... params);

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return map
     */
    Map<String, Serializable> unique(E0 execution, Serializable... params);

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @return entity
     */
    <T> T unique(E0 execution, Class<T> mappingType, Serializable... params);

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @return entity
     */
    <T> T unique(E0 execution, RowMapper<T> rowMapper, Serializable... params);

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query list, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return map list
     */
    List<Map<String, Serializable>> list(E0 execution, Serializable... params);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @return entity list
     */
    <T> List<T> list(E0 execution, Class<T> mappingType, Serializable... params);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the generic type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @return entity list
     */
    <T> List<T> list(E0 execution, RowMapper<T> rowMapper, Serializable... params);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return map list
     */
    List<Map<String, Serializable>> list(E0 execution, Serializable[] params, int offset, int limit);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity list
     */
    <T> List<T> list(E0 execution, Class<T> mappingType, Serializable[] params, int offset, int limit);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity list
     */
    <T> List<T> list(E0 execution, RowMapper<T> rowMapper, Serializable[] params, int offset, int limit);

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param page the page
     * @return entity list
     */
    default <T> List<T> list(E0 execution, Class<T> mappingType, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query list, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param page the page
     * @return entity list
     */
    default <T> List<T> list(E0 execution, RowMapper<T> rowMapper, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, rowMapper, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query list, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param page the page
     * @return map list
     */
    default List<Map<String, Serializable>> list(E0 execution, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, params, limit.getOffset(), limit.getLimit());
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query each, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @return map list
     */
    RowIterable<Map<String, Serializable>> each(E0 execution, Serializable... params);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @return entity list
     */
    <T> RowIterable<T> each(E0 execution, Class<T> mappingType, Serializable... params);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the generic type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @return entity list
     */
    <T> RowIterable<T> each(E0 execution, RowMapper<T> rowMapper, Serializable... params);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return map list
     */
    RowIterable<Map<String, Serializable>> each(E0 execution, Serializable[] params, int offset, int limit);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity list
     */
    <T> RowIterable<T> each(E0 execution, Class<T> mappingType, Serializable[] params, int offset, int limit);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity list
     */
    <T> RowIterable<T> each(E0 execution, RowMapper<T> rowMapper, Serializable[] params, int offset, int limit);

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param page the page
     * @return entity list
     */
    default <T> RowIterable<T> each(E0 execution, Class<T> mappingType, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query each, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param page the page
     * @return entity list
     */
    default <T> RowIterable<T> each(E0 execution, RowMapper<T> rowMapper, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, rowMapper, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query each, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param page the page
     * @return map list
     */
    default RowIterable<Map<String, Serializable>> each(E0 execution, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, params, limit.getOffset(), limit.getLimit());
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query page, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return map pagination
     */
    PaginationResults<Map<String, Serializable>> pagination(E0 execution, Serializable[] params, int offset, int limit);

    /**
     * query page, use query str in template find with executeId.
     *
     * @param execution the execution
     * @param params the params
     * @param page the page
     * @return map pagination
     */
    default PaginationResults<Map<String, Serializable>> pagination(E0 execution, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query page, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity pagination
     */
    <T> PaginationResults<T> pagination(E0 execution, Class<T> mappingType, Serializable[] params, int offset,
        int limit);

    /**
     * query page, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return entity pagination
     */
    <T> PaginationResults<T> pagination(E0 execution, RowMapper<T> rowMapper, Serializable[] params, int offset,
        int limit);

    /**
     * query page, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param mappingType mapping type
     * @param params the params
     * @param page the page
     * @return entity pagination
     */
    default <T> PaginationResults<T> pagination(E0 execution, Class<T> mappingType, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query page, use query str in template find with executeId.
     *
     * @param <T> the element type
     * @param execution the execution
     * @param rowMapper the row mapper
     * @param params the params
     * @param page the page
     * @return entity pagination
     */
    default <T> PaginationResults<T> pagination(E0 execution, RowMapper<T> rowMapper, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, rowMapper, params, limit.getOffset(), limit.getLimit());
    }
}
