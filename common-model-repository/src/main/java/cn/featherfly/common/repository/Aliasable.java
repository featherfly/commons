
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-10 18:01:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * Aliasable.
 *
 * @author zhongj
 * @param <A> the generic type
 */
public interface Aliasable<A extends Alias> {

    /**
     * short name of alias.
     *
     * @see #alias(String)
     * @param alias the alias
     * @return the a
     */
    default A as(String alias) {
        return alias(alias);
    }

    /**
     * Alias.
     *
     * @param alias the alias
     * @return the a
     */
    A alias(String alias);

}
