
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 18:49:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.ExecutionExecutorEx;
import cn.featherfly.common.repository.RowIterable;
import cn.featherfly.common.structure.page.PaginationResults;
import cn.featherfly.common.tuple.Tuple5;
import cn.featherfly.common.tuple.Tuples;

/**
 * prefix bean mapped executor 5.
 *
 * @author zhongj
 * @param <E1> the generic type
 * @param <E2> the generic type
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 * @param <T5> the generic type
 */
public class PrefixedBeanMappedExecutor5Impl<E1 extends ExecutionExecutorEx<E2>, E2, T1, T2, T3, T4, T5>
    extends AbstractPrefixedBeanMappedExecutor<E1, E2, Object, Tuple5<String, String, String, String, String>>
    implements PrefixedBeanMappedExecutor5<T1, T2, T3, T4, T5> {

    private final Class<T1> type1;
    private final Class<T2> type2;
    private final Class<T3> type3;
    private final Class<T4> type4;
    private final Class<T5> type5;

    /**
     * Instantiates a new tpl prefix entity mapper 5.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     */
    public PrefixedBeanMappedExecutor5Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5) {
        this(executor, execution, params, type1, type2, type3, type4, type5, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 5.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor5Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5,
        Tuple5<String, String, String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
    }

    /**
     * Instantiates a new tpl prefix entity mapper 5.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     */
    public PrefixedBeanMappedExecutor5Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Class<T4> type4, Class<T5> type5) {
        this(executor, execution, params, type1, type2, type3, type4, type5, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 5.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor5Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Class<T4> type4, Class<T5> type5, Tuple5<String, String, String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple5<T1, T2, T3, T4, T5> single() {
        if (params instanceof Map) {
            return executor.single(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap());
        } else {
            return executor.single(execution, type1, type2, type3, type4, type5, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple5<T1, T2, T3, T4, T5> unique() {
        if (params instanceof Map) {
            return executor.unique(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap());
        } else {
            return executor.unique(execution, type1, type2, type3, type4, type5, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple5<T1, T2, T3, T4, T5>> list() {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap());
        } else {
            return executor.list(execution, type1, type2, type3, type4, type5, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple5<T1, T2, T3, T4, T5>> list(int offset, int limit) {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.list(execution, type1, type2, type3, type4, type5, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple5<T1, T2, T3, T4, T5>> each() {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap());
        } else {
            return executor.each(execution, type1, type2, type3, type4, type5, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple5<T1, T2, T3, T4, T5>> each(int offset, int limit) {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap(), offset, limit);
        } else {
            return executor.each(execution, type1, type2, type3, type4, type5, prefixes, getParams(), offset, limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResults<Tuple5<T1, T2, T3, T4, T5>> pagination(int offset, int limit) {
        if (params instanceof Map) {
            return executor.pagination(execution, type1, type2, type3, type4, type5, prefixes, getParamsMap(), offset,
                limit);
        } else {
            return executor.pagination(execution, type1, type2, type3, type4, type5, prefixes, getParams(), offset,
                limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T6> PrefixedBeanMappedExecutor6<T1, T2, T3, T4, T5, T6> map(String prefix, Class<T6> type) {
        if (params instanceof Map) {
            return new PrefixedBeanMappedExecutor6Impl<>(executor, execution, getParamsMap(), type1, type2, type3,
                type4, type5,
                type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefixes.get2(), prefixes.get3(), prefixes.get4(), prefix));
        } else {
            return new PrefixedBeanMappedExecutor6Impl<>(executor, execution, getParams(), type1, type2, type3, type4,
                type5,
                type,
                Tuples.of(prefixes.get0(), prefixes.get1(), prefixes.get2(), prefixes.get3(), prefixes.get4(), prefix));
        }
    }

}
