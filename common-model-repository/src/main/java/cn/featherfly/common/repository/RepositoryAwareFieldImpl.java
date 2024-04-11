
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-03-29 18:23:29
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * RepositoryAwareFieldImpl.
 *
 * @author zhongj
 * @param <R> the generic type
 */
public class RepositoryAwareFieldImpl<R extends Repository> implements RepositoryAwareField<R> {

    private String name;

    private R repository;

    /**
     * Instantiates a new repository aware field impl.
     *
     * @param name       the name
     * @param repository the repository
     */
    public RepositoryAwareFieldImpl(String name, R repository) {
        super();
        this.name = name;
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R repository() {
        return repository;
    }

}
