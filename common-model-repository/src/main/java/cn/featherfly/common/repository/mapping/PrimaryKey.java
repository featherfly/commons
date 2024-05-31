
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

    private final boolean databaseGeneration;

    /**
     * Instantiates a new primary key.
     *
     * @param idGenerator the id generator
     * @param databaseGeneration the generated
     */
    public PrimaryKey(IdGenerator idGenerator, boolean databaseGeneration) {
        super();
        this.idGenerator = idGenerator;
        this.databaseGeneration = databaseGeneration;
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

    /**
     * Checks if is database generation.
     *
     * @return true, if is database generation
     */
    public boolean isDatabaseGeneration() {
        return databaseGeneration;
    }
}
