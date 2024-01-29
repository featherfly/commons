
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-21 13:03:21
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * Nameable.
 *
 * @author zhongj
 * @param <N> the number type
 */
public interface Nameable<N extends Naming> {

    /**
     * Name.
     *
     * @param name the name
     * @return the n
     */
    N name(String name);

}
