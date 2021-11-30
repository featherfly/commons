package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

public enum State implements Property<Integer> {

    DISABLEMENT, ENABLEMENT;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }
}
