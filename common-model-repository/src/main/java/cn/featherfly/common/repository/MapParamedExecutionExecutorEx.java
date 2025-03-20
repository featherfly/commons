
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

import cn.featherfly.common.repository.mapper.RowMapper;
import cn.featherfly.common.structure.page.PaginationResults;

/**
 * TemplateParamedExecutionExecutor.
 *
 * @author zhongj
 * @param <E1> the generic type
 * @param <E2> the generic type
 */
public class MapParamedExecutionExecutorEx<E1 extends ExecutionExecutorEx<E2>, E2>
    extends MapParamedExecutionExecutor<E1, E2> implements ParamedExecutionExecutorEx {

    /**
     * Instantiates a new template paramed execution executor.
     *
     * @param executor the executor
     * @param execution the execution
     * @param params the params
     */
    public MapParamedExecutionExecutorEx(E1 executor, E2 execution, Map<String, Serializable> params) {
        super(executor, execution, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Serializable>> list(int offset, int limit) {
        return executor.list(execution, params, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> List<E> list(Class<E> mapType, int offset, int limit) {
        return executor.list(execution, mapType, params, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> list(RowMapper<T> rowMapper, int offset, int limit) {
        return executor.list(execution, rowMapper, params, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResults<Map<String, Serializable>> pagination(int offset, int limit) {
        return executor.pagination(execution, params, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> PaginationResults<T> pagination(Class<T> mapType, int offset, int limit) {
        return executor.pagination(execution, mapType, params, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> PaginationResults<T> pagination(RowMapper<T> rowMapper, int offset, int limit) {
        return executor.pagination(execution, rowMapper, params, offset, limit);
    }
}
