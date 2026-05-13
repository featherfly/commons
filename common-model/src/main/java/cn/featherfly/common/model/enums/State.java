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

    /**
     * value of State.
     *
     * @param value the value
     * @return the State
     */
    public static State valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of State.
     *
     * @param value the value
     * @return the State
     */
    public static State valueOf(int value) {
        for (State p : State.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
