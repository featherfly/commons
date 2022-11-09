package cn.featherfly.common.repository;

/**
 * alias repository.
 *
 * @author zhongj
 */
public interface AliasRepository extends Repository {

    /**
     * get repository name alias.
     *
     * @return name alias
     */
    String alias();
}