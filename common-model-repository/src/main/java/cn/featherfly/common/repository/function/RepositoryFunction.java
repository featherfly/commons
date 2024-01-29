
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-27 18:55:27
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.function;

import java.util.function.Function;

import cn.featherfly.common.repository.Repository;

/**
 * The Interface RepositoryFunction.
 *
 * @author zhongj
 */
public interface RepositoryFunction extends Function<String, String> {

    /**
     * Applies this function to the given argument.
     *
     * @param repository the function argument
     * @return the function result
     */
    default String apply(Repository repository) {
        return apply(repository.name());
    }
}
