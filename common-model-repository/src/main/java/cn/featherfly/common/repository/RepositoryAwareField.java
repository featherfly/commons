package cn.featherfly.common.repository;

/*
 * package cn.featherfly.common.repository; /** RepositoryField.
 * @author zhongj
 */
public interface RepositoryAwareField<R extends Repository> extends Field {

    /**
     * Repository.
     *
     * @return the repository
     */
    R repository();
}
