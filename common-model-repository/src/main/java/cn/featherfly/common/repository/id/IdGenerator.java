
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:14:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import java.io.Serializable;

/**
 * id generator.
 *
 * @author zhongj
 */
public interface IdGenerator {

    /**
     * Checks if is ordered.
     *
     * @return true, if is ordered
     */
    boolean isOrdered();

    /**
     * Checks if is database generation.
     *
     * @return true, if is database generation
     */
    boolean isDatabaseGeneration();

    /**
     * Generate.
     *
     * @param <E> the element type
     * @param entity the entity
     * @return the serializable
     */
    <E> Serializable generate(E entity);
}
