
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:14:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import java.io.Serializable;

import cn.featherfly.common.lang.number.Radix;
import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * time id generator.
 *
 * @author zhongj
 */
public class TimeIdGenerator implements IdGenerator {

    private final cn.featherfly.common.lang.TimeIdGenerator generator;

    /**
     * Instantiates a new time id generator.
     */
    public TimeIdGenerator() {
        this(Radix.RADIX10, false);
    }

    /**
     * Instantiates a new time id generator.
     *
     * @param radix the radix
     * @param appendIp the append ip
     */
    public TimeIdGenerator(Radix radix, boolean appendIp) {
        generator = new cn.featherfly.common.lang.TimeIdGenerator(radix, appendIp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOrdered() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDatabaseGeneration() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Serializable generate(E entity, PropertyMapping<?> idPropertyMapping) {
        Serializable id = generator.generate();
        idPropertyMapping.getSetter().accept(entity, id);
        return id;
    }
}
