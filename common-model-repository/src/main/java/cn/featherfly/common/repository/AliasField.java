package cn.featherfly.common.repository;

import cn.featherfly.common.lang.Lang;

/**
 * alias field.
 *
 * @author zhongj
 */
public interface AliasField extends Field, Alias {

    /**
     * get alias or name. when alias is empty try to get name.
     *
     * @return the alias or name
     */
    default String getAliasOrName() {
        return Lang.ifEmpty(alias(), this::name);
    }
}