package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * The Enum State.
 *
 * @author zhongj
 */
public enum State implements Property<Integer> {

    /** The disablement. */
    DISABLEMENT,
    /** The enablement. */
    ENABLEMENT;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }
}
