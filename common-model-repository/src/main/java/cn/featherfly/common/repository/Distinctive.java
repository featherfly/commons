
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-21 13:01:21
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * Distinctive.
 *
 * @author zhongj
 * @param <D> the generic type
 */
public interface Distinctive<D extends Distinct> {
    /**
     * enable distinct.
     *
     * @return the d
     */
    default D distinct() {
        return distinct(true);
    }

    /**
     * Distinct.
     *
     * @param distinct the distinct
     * @return the d
     */
    D distinct(boolean distinct);
}
