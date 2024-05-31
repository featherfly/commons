
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-29 15:25:29
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.id;

import java.io.Serializable;

import cn.featherfly.common.repository.id.IdGenerator;

/**
 * The Class DoNothingIdGenerator.
 *
 * @author zhongj
 */
public class DatabaseGenerationIdGenerator implements IdGenerator {

    private final boolean ordered;

    /**
     * Instantiates a new id generator donothing.
     *
     * @param ordered the ordered
     */
    public DatabaseGenerationIdGenerator(boolean ordered) {
        super();
        this.ordered = ordered;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Serializable generate(E entity) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDatabaseGeneration() {
        return true;
    }
}
