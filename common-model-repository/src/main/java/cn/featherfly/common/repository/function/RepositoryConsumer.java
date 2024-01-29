
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-27 18:55:27
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.function;

import java.util.function.Consumer;

import cn.featherfly.common.repository.Repository;

/**
 * The Interface RepositoryConsumer.
 *
 * @author zhongj
 */
public interface RepositoryConsumer extends Consumer<String> {

    /**
     * Performs this operation on the given argument.
     *
     * @param repository the field
     */
    default void accept(Repository repository) {
        accept(repository.name());
    }
}
