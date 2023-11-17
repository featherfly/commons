package cn.featherfly.common.repository;

import cn.featherfly.common.lang.Lang;

/**
 * alias repository.
 *
 * @author zhongj
 */
public interface AliasRepository extends Repository, Alias {

    /**
     * get alias or name. when alias is empty try to get name.
     *
     * @return the alias or name
     */
    default String getAliasOrName() {
        return Lang.ifEmpty(alias(), this::name);
    }
}