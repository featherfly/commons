
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 17:17:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor1Impl;
import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor2Impl;
import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor3Impl;
import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor4Impl;
import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor5Impl;
import cn.featherfly.common.repository.mapper.PrefixedBeanMappedExecutor6Impl;
import cn.featherfly.common.repository.mapper.RowMapper;
import cn.featherfly.common.repository.mapper.TupleMappedExecutorBuilder;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.Tuple3;
import cn.featherfly.common.tuple.Tuple4;
import cn.featherfly.common.tuple.Tuple5;
import cn.featherfly.common.tuple.Tuple6;

/**
 * TemplateParamedExecutionExecutor.
 *
 * @author zhongj
 * @param <E1> the generic type
 * @param <E2> the generic type
 */
public class MapParamedExecutionExecutor<E1 extends ExecutionExecutorEx<E2>, E2> implements ParamedExecutionExecutor {

    /** The executor. */
    protected final E1 executor;

    /** The execution. */
    protected final E2 execution;

    /** The params. */
    protected final Map<String, Serializable> params;

    /**
     * Instantiates a new template paramed execution executor.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     */
    public MapParamedExecutionExecutor(E1 executor, E2 execution, Map<String, Serializable> params) {
        super();
        this.executor = executor;
        this.execution = execution;
        this.params = params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> ParamedMappedExecutor<T> mapper(Class<T> mapType) {
        return new PrefixedBeanMappedExecutor1Impl<>(executor, execution, params, mapType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, T2> ParamedMappedExecutor<Tuple2<T1, T2>> mapper(Class<T1> type1, Class<T2> type2) {
        return new PrefixedBeanMappedExecutor2Impl<>(executor, execution, params, type1, type2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, T2, T3> ParamedMappedExecutor<Tuple3<T1, T2, T3>> mapper(Class<T1> type1, Class<T2> type2,
        Class<T3> type3) {
        return new PrefixedBeanMappedExecutor3Impl<>(executor, execution, params, type1, type2, type3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, T2, T3, T4> ParamedMappedExecutor<Tuple4<T1, T2, T3, T4>> mapper(Class<T1> type1, Class<T2> type2,
        Class<T3> type3, Class<T4> type4) {
        return new PrefixedBeanMappedExecutor4Impl<>(executor, execution, params, type1, type2, type3, type4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, T2, T3, T4, T5> ParamedMappedExecutor<Tuple5<T1, T2, T3, T4, T5>> mapper(Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5) {
        return new PrefixedBeanMappedExecutor5Impl<>(executor, execution, params, type1, type2, type3, type4, type5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, T2, T3, T4, T5, T6> ParamedMappedExecutor<Tuple6<T1, T2, T3, T4, T5, T6>> mapper(Class<T1> type1,
        Class<T2> type2, Class<T3> type3, Class<T4> type4, Class<T5> type5, Class<T6> type6) {
        return new PrefixedBeanMappedExecutor6Impl<>(executor, execution, params, type1, type2, type3, type4, type5,
            type6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> ParamedMappedExecutor<T> mapper(
        Function<TupleMappedExecutorBuilder, ParamedMappedExecutor<T>> mapperBuilderFunction) {
        return mapperBuilderFunction.apply(new MapParamsTupleMapperBuilder<>(executor, execution, params));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        return executor.execute(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int intValue() {
        return executor.intValue(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long longValue() {
        return executor.longValue(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doubleValue() {
        return executor.doubleValue(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean bool() {
        return executor.bool(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> V value() {
        return executor.value(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> V value(Class<V> valueType) {
        return executor.value(execution, valueType, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Serializable> single() {
        return executor.single(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T single(Class<T> mapType) {
        return executor.single(execution, mapType, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T single(RowMapper<T> rowMapper) {
        return executor.single(execution, rowMapper, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Serializable> unique() {
        return executor.unique(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unique(Class<T> mapType) {
        return executor.unique(execution, mapType, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unique(RowMapper<T> rowMapper) {
        return executor.unique(execution, rowMapper, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Serializable>> list() {
        return executor.list(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> list(Class<T> mapType) {
        return executor.list(execution, mapType, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> list(RowMapper<T> rowMapper) {
        return executor.list(execution, rowMapper, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RowIterable<Map<String, Serializable>> each() {
        return executor.each(execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> RowIterable<T> each(Class<T> mappingType) {
        return executor.each(execution, mappingType, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> RowIterable<T> each(RowMapper<T> rowMapper) {
        return executor.each(execution, rowMapper, params);
    }
}
