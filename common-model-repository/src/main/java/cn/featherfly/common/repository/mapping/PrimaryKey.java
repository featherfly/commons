
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-29 14:47:29
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapping;

import cn.featherfly.common.repository.id.IdGenerator;

/**
 * PrimaryKey.
 *
 * @author zhongj
 */
public class PrimaryKey {

    private final IdGenerator idGenerator;

    /**
     * Instantiates a new primary key.
     *
     * @param idGenerator the id generator
     */
    public PrimaryKey(IdGenerator idGenerator) {
        super();
        this.idGenerator = idGenerator;
    }

    /**
     * Checks if is ordered.
     *
     * @return true, if is ordered
     */
    public boolean isOrdered() {
        return idGenerator != null && idGenerator.isOrdered();
    }

    /**
     * Gets the id generator.
     *
     * @return the id generator
     */
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
}
