
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 17:08:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import java.io.Serializable;

import cn.featherfly.common.lang.UUIDGenerator.Type;

/**
 * UUIDGenerator.
 *
 * @author zhongj
 */
public class UUIDGenerator implements IdGenerator {

    private final Type type;

    /**
     * Instantiates a new UUID generator.
     */
    public UUIDGenerator() {
        this(Type.UUID32);
    }

    /**
     * Instantiates a new UUID generator.
     *
     * @param type the type
     */
    public UUIDGenerator(Type type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Serializable generate(E entity) {
        return cn.featherfly.common.lang.UUIDGenerator.generateUUID(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOrdered() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDatabaseGeneration() {
        return false;
    }

}
