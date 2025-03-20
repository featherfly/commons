
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2022-05-10 18:29:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.ExecutionExecutorEx;
import cn.featherfly.common.repository.RowIterable;
import cn.featherfly.common.structure.page.PaginationResults;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.Tuples;

/**
 * prefix bean mapped executor 2.
 *
 * @author zhongj
 * @param <E1> the generic type
 * @param <E2> the generic type
 * @param <T1> the generic type
 * @param <T2> the generic type
 */
public class PrefixedBeanMappedExecutor2Impl<E1 extends ExecutionExecutorEx<E2>, E2, T1, T2>
    extends AbstractPrefixedBeanMappedExecutor<E1, E2, Object, Tuple2<String, String>>
    implements PrefixedBeanMappedExecutor2<T1, T2> {

    private final Class<T1> type1;

    private final Class<T2> type2;

    /**
     * Instantiates a new tpl prefix entity mapper 2.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     */
    public PrefixedBeanMappedExecutor2Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2) {
        this(executor, execution, params, type1, type2, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 2.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor2Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Tuple2<String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
    }

    /**
     * Instantiates a new tpl prefix entity mapper 2.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     */
    public PrefixedBeanMappedExecutor2Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1,
        Class<T2> type2) {
        this(executor, execution, params, type1, type2, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 2.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor2Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Tuple2<String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple2<T1, T2> single() {
        if (params instanceof Map) {
            return executor.single(execution, type1, type2, prefixes, getParamsMap());
        } else {
            return executor.single(execution, type1, type2, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple2<T1, T2> unique() {
        if (params instanceof Map) {
            return executor.unique(execution, type1, type2, prefixes, getParamsMap());
        } else {
            return executor.unique(execution, type1, type2, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple2<T1, T2>> list() {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, prefixes, getParamsMap());
        } else {
            return executor.list(execution, type1, type2, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple2<T1, T2>> list(int offset, int limit) {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.list(execution, type1, type2, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple2<T1, T2>> each() {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, prefixes, getParamsMap());
        } else {
            return executor.each(execution, type1, type2, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple2<T1, T2>> each(int offset, int limit) {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.each(execution, type1, type2, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResults<Tuple2<T1, T2>> pagination(int offset, int limit) {
        if (params instanceof Map) {
            return executor.pagination(execution, type1, type2, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.pagination(execution, type1, type2, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T3> PrefixedBeanMappedExecutor3<T1, T2, T3> map(String prefix, Class<T3> type) {
        if (params instanceof Map) {
            return new PrefixedBeanMappedExecutor3Impl<>(executor, execution, getParamsMap(), type1, type2, type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefix));
        } else {
            return new PrefixedBeanMappedExecutor3Impl<>(executor, execution, getParams(), type1, type2, type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefix));
        }
    }
}
