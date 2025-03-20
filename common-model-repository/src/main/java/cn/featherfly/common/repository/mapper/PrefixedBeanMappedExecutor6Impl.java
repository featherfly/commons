
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
import cn.featherfly.common.tuple.Tuple6;

/**
 * prefix bean mapped executor 6.
 *
 * @author zhongj
 * @param <T1> the generic type
 * @param <T2> the generic type
 * @param <T3> the generic type
 * @param <T4> the generic type
 * @param <T5> the generic type
 * @param <T6> the generic type
 */
public class PrefixedBeanMappedExecutor6Impl<E1 extends ExecutionExecutorEx<E2>, E2, T1, T2, T3, T4, T5, T6>
    extends AbstractPrefixedBeanMappedExecutor<E1, E2, Object, Tuple6<String, String, String, String, String, String>>
    implements PrefixedBeanMappedExecutor6<T1, T2, T3, T4, T5, T6> {

    private final Class<T1> type1;
    private final Class<T2> type2;
    private final Class<T3> type3;
    private final Class<T4> type4;
    private final Class<T5> type5;
    private final Class<T6> type6;

    /**
     * Instantiates a new tpl prefix entity mapper 6.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param type6 the type 6
     */
    public PrefixedBeanMappedExecutor6Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5, Class<T6> type6) {
        this(executor, execution, params, type1, type2, type3, type4, type5, type6, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 6.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param type6 the type 6
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor6Impl(E1 executor, E2 execution, Map<String, Serializable> params, Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5, Class<T6> type6,
        Tuple6<String, String, String, String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
        this.type6 = type6;
    }

    /**
     * Instantiates a new tpl prefix entity mapper 6.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param type6 the type 6
     */
    public PrefixedBeanMappedExecutor6Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Class<T4> type4, Class<T5> type5, Class<T6> type6) {
        this(executor, execution, params, type1, type2, type3, type4, type5, type6, null);
    }

    /**
     * Instantiates a new tpl prefix entity mapper 6.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     * @param type1 the type 1
     * @param type2 the type 2
     * @param type3 the type 3
     * @param type4 the type 4
     * @param type5 the type 5
     * @param type6 the type 6
     * @param prefixes the prefixes
     */
    public PrefixedBeanMappedExecutor6Impl(E1 executor, E2 execution, Object[] params, Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Class<T4> type4, Class<T5> type5, Class<T6> type6,
        Tuple6<String, String, String, String, String, String> prefixes) {
        super(executor, execution, params, prefixes);
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
        this.type6 = type6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple6<T1, T2, T3, T4, T5, T6> single() {
        if (params instanceof Map) {
            return executor.single(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap());
        } else {
            return executor.single(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple6<T1, T2, T3, T4, T5, T6> unique() {
        if (params instanceof Map) {
            return executor.unique(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap());
        } else {
            return executor.unique(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple6<T1, T2, T3, T4, T5, T6>> list() {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap());
        } else {
            return executor.list(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tuple6<T1, T2, T3, T4, T5, T6>> list(int offset, int limit) {
        if (params instanceof Map) {
            return executor.list(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap(), offset,
                limit);
        } else {
            return executor.list(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams(), offset,
                limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each() {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap());
        } else {
            return executor.each(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Tuple6<T1, T2, T3, T4, T5, T6>> each(int offset, int limit) {
        if (params instanceof Map) {
            return executor.each(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap(), offset,
                limit);
        } else {
            return executor.each(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams(), offset,
                limit);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResults<Tuple6<T1, T2, T3, T4, T5, T6>> pagination(int offset, int limit) {
        if (params instanceof Map) {
            return executor.pagination(execution, type1, type2, type3, type4, type5, type6, prefixes, getParamsMap(),
                offset, limit);
        } else {
            return executor.pagination(execution, type1, type2, type3, type4, type5, type6, prefixes, getParams(),
                offset, limit);
        }
    }

}
