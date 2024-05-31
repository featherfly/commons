
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 19:02:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import java.io.Serializable;

/**
 * AssignGenerator.
 *
 * @author zhongj
 */
public class AssignGenerator implements IdGenerator {

    private final boolean ordered;

    /**
     * Instantiates a new assign generator.
     */
    public AssignGenerator() {
        this(false);
    }

    /**
     * Instantiates a new assign generator.
     *
     * @param ordered the ordered
     */
    protected AssignGenerator(boolean ordered) {
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
        return false;
    }
}
