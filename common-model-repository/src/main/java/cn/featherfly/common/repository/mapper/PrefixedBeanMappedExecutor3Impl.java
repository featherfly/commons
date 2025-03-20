
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-05-10 18:39:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.ExecutionExecutorEx;
import cn.featherfly.common.repository.RowIterable;
import cn.featherfly.common.structure.page.PaginationResults;
import cn.featherfly.common.tuple.Tuple3;
import cn.featherfly.common.tuple.Tuples;

/**
 * prefix bean mapped executor 3.
 *
 * @author zhongj
 * @param <E1> the generic type
 * @param <E2> the generic type
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 */
public class PrefixedBeanMappedExecutor3Impl<E1 extends ExecutionExecutorEx<E2>, E2, T1, T2, T3>
    extends AbstractPrefixedBeanMappedExecutor<E1, E2, Object, Tuple3<String, String, String>>
    implements PrefixedBeanMappedExecutor3<T1, T2, T3> {

    private final Class<T1> type1;
    private final Class<T2> type2;
    private final Class<T3> type3;

    /**
     * Instantiates a new tpl prefix entity mapper 3.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     */
    public PrefixedBeanMappedExecutor3Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3) {
        this(executor, execution, params, type1, type2, type3, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 3.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor3Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Tuple3<String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }

    /**
     * Instantiates a new tpl prefix entity mapper 3.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     */
    public PrefixedBeanMappedExecutor3Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3) {
        this(executor, execution, params, type1, type2, type3, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 3.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor3Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Tuple3<String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple3<T1, T2, T3> single() {
        if (params instanceof Map) {
            return executor.single(execution, type1, type2, type3, prefixes, getParamsMap());
        } else {
            return executor.single(execution, type1, type2, type3, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple3<T1, T2, T3> unique() {
        if (params instanceof Map) {
            return executor.unique(execution, type1, type2, type3, prefixes, getParamsMap());
        } else {
            return executor.unique(execution, type1, type2, type3, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple3<T1, T2, T3>> list() {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, prefixes, getParamsMap());
        } else {
            return executor.list(execution, type1, type2, type3, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple3<T1, T2, T3>> list(int offset, int limit) {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.list(execution, type1, type2, type3, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple3<T1, T2, T3>> each() {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, prefixes, getParamsMap());
        } else {
            return executor.each(execution, type1, type2, type3, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple3<T1, T2, T3>> each(int offset, int limit) {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.each(execution, type1, type2, type3, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResults<Tuple3<T1, T2, T3>> pagination(int offset, int limit) {
        if (params instanceof Map) {
            return executor.pagination(execution, type1, type2, type3, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.pagination(execution, type1, type2, type3, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T4> PrefixedBeanMappedExecutor4<T1, T2, T3, T4> map(String prefix, Class<T4> type) {
        if (params instanceof Map) {
            return new PrefixedBeanMappedExecutor4Impl<>(executor, execution, getParamsMap(), type1, type2, type3, type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefixes.get2(), prefix));
        } else {
            return new PrefixedBeanMappedExecutor4Impl<>(executor, execution, getParams(), type1, type2, type3, type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefixes.get2(), prefix));
        }
    }

}
