package cn.featherfly.common.repository;

/**
 * RepositoryField.
 *
 * @author zhongj
 * @param <R> the generic type
 */
public interface RepositoryAwareField<R extends Repository> extends Field {

    /**
     * Repository.
     *
     * @return the repository
     */
    R repository();
}
