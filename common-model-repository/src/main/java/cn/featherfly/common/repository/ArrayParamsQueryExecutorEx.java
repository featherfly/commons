
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 19:48:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.List;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuple3;
import com.speedment.common.tuple.Tuple4;
import com.speedment.common.tuple.Tuple5;
import com.speedment.common.tuple.Tuple6;

import cn.featherfly.common.lang.AutoCloseableIterable;
import cn.featherfly.common.structure.page.Limit;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.PaginationResults;

/**
 * array params query executor ex.
 *
 * @author zhongj
 * @param <E0> the generic type
 */
public interface ArrayParamsQueryExecutorEx<E0> extends ArrayParamsQueryExecutor<E0> {

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @return the tuple 2
     */
    default <T1, T2> Tuple2<T1, T2> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Serializable... params) {
        return single(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the tpl execute id
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 2
     */
    <T1, T2> Tuple2<T1, T2> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @return the tuple 3
     */
    default <T1, T2, T3> Tuple3<T1, T2, T3> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Serializable... params) {
        return single(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null,
            params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 3
     */
    <T1, T2, T3> Tuple3<T1, T2, T3> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @return the tuple 4
     */
    default <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Serializable... params) {
        return single(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 4
     */
    <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Tuple4<String, String, String, String> prefixes,
        Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @return the tuple 5
     */
    default <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> single(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Serializable... params) {
        return single(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 5
     */
    <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> single(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable... params);

    /**
     * Single.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @return the tuple 6
     */
    default <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> single(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Serializable... params) {
        return single(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params);
    }

    /**
     * Single.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 6
     */
    <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> single(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable... params);

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @return the tuple 2
     */
    default <T1, T2> Tuple2<T1, T2> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Serializable... params) {
        return unique(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params);
    }

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 2
     */
    <T1, T2> Tuple2<T1, T2> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable... params);

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @return the tuple 3
     */
    default <T1, T2, T3> Tuple3<T1, T2, T3> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Serializable... params) {
        return unique(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null,
            params);
    }

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 3
     */
    <T1, T2, T3> Tuple3<T1, T2, T3> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable... params);

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @return the tuple 4
     */
    default <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Serializable... params) {
        return unique(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params);
    }

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 4
     */
    <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Tuple4<String, String, String, String> prefixes,
        Serializable... params);

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @return the tuple 5
     */
    default <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> unique(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Serializable... params) {
        return unique(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params);
    }

    /**
     * query unique, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 5
     */
    <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> unique(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable... params);

    /**
     * Unique.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @return the tuple 6
     */
    default <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> unique(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Serializable... params) {
        return unique(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params);
    }

    /**
     * Unique.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @return the tuple 6
     */
    <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> unique(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable... params);

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @return tuple2 list
     */
    default <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Serializable... params) {
        return list(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 list
     */
    default <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Serializable[] params, int offset, int limit) {
        return list(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param page the page
     * @return tuple2 list
     */
    default <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple2 list
     */
    <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 list
     */
    <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple2 list
     */
    default <T1, T2> List<Tuple2<T1, T2>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, prefixes, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @return tuple3 list
     */
    default <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Serializable... params) {
        return list(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 list
     */
    default <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Serializable[] params, int offset, int limit) {
        return list(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null, params,
            offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param page the page
     * @return tuple3 list
     */
    default <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple3 list
     */
    <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 list
     */
    <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple3 list
     */
    default <T1, T2, T3> List<Tuple3<T1, T2, T3>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, prefixes, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable... params) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, int offset,
        int limit) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param page the page
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple4 list
     */
    <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Tuple4<String, String, String, String> prefixes,
        Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 list
     */
    <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Class<T3> mappingType3, Class<T4> mappingType4, Tuple4<String, String, String, String> prefixes,
        Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> List<Tuple4<T1, T2, T3, T4>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, prefixes, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Serializable... params) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple5 list
     */
    <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Serializable[] params, int offset, int limit) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param page the page
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 list
     */
    <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> List<Tuple5<T1, T2, T3, T4, T5>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, prefixes, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Serializable... params) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params);
    }

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Serializable[] params, int offset, int limit) {
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param page the page
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            params, limit.getOffset(), limit.getLimit());
    }

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @return LogicExpressionist
     */
    <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable... params);

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return LogicExpressionist
     */
    <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes, Serializable[] params,
        int offset, int limit);

    /**
     * List.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> List<Tuple6<T1, T2, T3, T4, T5, T6>> list(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return list(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            prefixes, params, limit.getOffset(), limit.getLimit());
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @return tuple2 list
     */
    default <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Serializable... params) {
        return each(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 list
     */
    default <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Serializable[] params, int offset, int limit) {
        return each(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param page the page
     * @return tuple2 list
     */
    default <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple2 list
     */
    <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 list
     */
    <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple2 list
     */
    default <T1, T2> AutoCloseableIterable<Tuple2<T1, T2>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Tuple2<String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, prefixes, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @return tuple3 list
     */
    default <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Serializable... params) {
        return each(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 list
     */
    default <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Serializable[] params, int offset, int limit) {
        return each(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null, params,
            offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param page the page
     * @return tuple3 list
     */
    default <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple3 list
     */
    <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Tuple3<String, String, String> prefixes,
        Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 list
     */
    <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params,
        int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple3 list
     */
    default <T1, T2, T3> AutoCloseableIterable<Tuple3<T1, T2, T3>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, prefixes, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable... params) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, int offset,
        int limit) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param page the page
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple4 list
     */
    <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 list
     */
    <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple4 list
     */
    default <T1, T2, T3, T4> AutoCloseableIterable<Tuple4<T1, T2, T3, T4>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, prefixes, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Serializable... params) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @return tuple5 list
     */
    <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable... params);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Serializable[] params, int offset, int limit) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param page the page
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 list
     */
    <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple5 list
     */
    default <T1, T2, T3, T4, T5> AutoCloseableIterable<Tuple5<T1, T2, T3, T4, T5>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Tuple5<String, String, String, String, String> prefixes, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, prefixes, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Serializable... params) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params);
    }

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Serializable[] params, int offset, int limit) {
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param page the page
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @return LogicExpressionist
     */
    <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable... params);

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return LogicExpressionist
     */
    <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable[] params, int offset, int limit);

    /**
     * query each.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> AutoCloseableIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return each(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            prefixes, params, limit.getOffset(), limit.getLimit());
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 pagination
     */
    default <T1, T2> PaginationResults<Tuple2<T1, T2>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Serializable[] params, int offset, int limit) {
        return pagination(execution, mappingType1, mappingType2, (Tuple2<String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param params the params
     * @param page the page
     * @return tuple2 pagination
     */
    default <T1, T2> PaginationResults<Tuple2<T1, T2>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple2 pagination
     */
    <T1, T2> PaginationResults<Tuple2<T1, T2>> pagination(E0 execution, Class<T1> mappingType1, Class<T2> mappingType2,
        Tuple2<String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple2 pagination
     */
    default <T1, T2> PaginationResults<Tuple2<T1, T2>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Tuple2<String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, prefixes, params, limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 pagination
     */
    default <T1, T2, T3> PaginationResults<Tuple3<T1, T2, T3>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Serializable[] params, int offset, int limit) {
        return pagination(execution, mappingType1, mappingType2, mappingType3, (Tuple3<String, String, String>) null,
            params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param params the params
     * @param page the page
     * @return tuple3 pagination
     */
    default <T1, T2, T3> PaginationResults<Tuple3<T1, T2, T3>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple3 pagination
     */
    <T1, T2, T3> PaginationResults<Tuple3<T1, T2, T3>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params,
        int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple3 pagination
     */
    default <T1, T2, T3> PaginationResults<Tuple3<T1, T2, T3>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Tuple3<String, String, String> prefixes, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, prefixes, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 pagination
     */
    default <T1, T2, T3, T4> PaginationResults<Tuple4<T1, T2, T3, T4>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, int offset,
        int limit) {
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4,
            (Tuple4<String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param params the params
     * @param page the page
     * @return tuple4 pagination
     */
    default <T1, T2, T3, T4> PaginationResults<Tuple4<T1, T2, T3, T4>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, params, limit.getOffset(),
            limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple4 pagination
     */
    <T1, T2, T3, T4> PaginationResults<Tuple4<T1, T2, T3, T4>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple4 pagination
     */
    default <T1, T2, T3, T4> PaginationResults<Tuple4<T1, T2, T3, T4>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Tuple4<String, String, String, String> prefixes, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, prefixes, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 pagination
     */
    default <T1, T2, T3, T4, T5> PaginationResults<Tuple5<T1, T2, T3, T4, T5>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Serializable[] params, int offset, int limit) {
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5,
            (Tuple5<String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param params the params
     * @param page the page
     * @return tuple5 pagination
     */
    default <T1, T2, T3, T4, T5> PaginationResults<Tuple5<T1, T2, T3, T4, T5>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, params,
            limit.getOffset(), limit.getLimit());
    }

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return tuple5 pagination
     */
    <T1, T2, T3, T4, T5> PaginationResults<Tuple5<T1, T2, T3, T4, T5>> pagination(E0 execution, Class<T1> mappingType1,
        Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4, Class<T5> mappingType5,
        Tuple5<String, String, String, String, String> prefixes, Serializable[] params, int offset, int limit);

    /**
     * query single, use query str in template find with executeId.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return tuple5 pagination
     */
    default <T1, T2, T3, T4, T5> PaginationResults<Tuple5<T1, T2, T3, T4, T5>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Tuple5<String, String, String, String, String> prefixes, Serializable[] params,
        Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, prefixes,
            params, limit.getOffset(), limit.getLimit());
    }

    /**
     * Pagination.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return the pagination results
     */
    default <T1, T2, T3, T4, T5, T6> PaginationResults<Tuple6<T1, T2, T3, T4, T5, T6>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Serializable[] params, int offset, int limit) {
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            (Tuple6<String, String, String, String, String, String>) null, params, offset, limit);
    }

    /**
     * Pagination.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param params the params
     * @param page the page
     * @return the pagination results
     */
    default <T1, T2, T3, T4, T5, T6> PaginationResults<Tuple6<T1, T2, T3, T4, T5, T6>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            params, limit.getOffset(), limit.getLimit());
    }

    /**
     * Pagination.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 mapping type 1
     * @param mappingType2 mapping type 2
     * @param mappingType3 mapping type 3
     * @param mappingType4 mapping type 4
     * @param mappingType5 mapping type 5
     * @param mappingType6 mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param offset the offset
     * @param limit the limit
     * @return LogicExpressionist
     */
    <T1, T2, T3, T4, T5, T6> PaginationResults<Tuple6<T1, T2, T3, T4, T5, T6>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable[] params, int offset, int limit);

    /**
     * Pagination.
     *
     * @param <T1> the generic type
     * @param <T2> the generic type
     * @param <T3> the generic type
     * @param <T4> the generic type
     * @param <T5> the generic type
     * @param <T6> the generic type
     * @param execution the execution
     * @param mappingType1 the mapping type 1
     * @param mappingType2 the mapping type 2
     * @param mappingType3 the mapping type 3
     * @param mappingType4 the mapping type 4
     * @param mappingType5 the mapping type 5
     * @param mappingType6 the mapping type 6
     * @param prefixes the prefixes
     * @param params the params
     * @param page the page
     * @return LogicExpressionist
     */
    default <T1, T2, T3, T4, T5, T6> PaginationResults<Tuple6<T1, T2, T3, T4, T5, T6>> pagination(E0 execution,
        Class<T1> mappingType1, Class<T2> mappingType2, Class<T3> mappingType3, Class<T4> mappingType4,
        Class<T5> mappingType5, Class<T6> mappingType6, Tuple6<String, String, String, String, String, String> prefixes,
        Serializable[] params, Page page) {
        Limit limit = new Limit(page);
        return pagination(execution, mappingType1, mappingType2, mappingType3, mappingType4, mappingType5, mappingType6,
            prefixes, params, limit.getOffset(), limit.getLimit());
    }
}
