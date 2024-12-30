package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * TimeRange.
 *
 * @author zhongj
 */
public enum TimeRange implements Property<Integer> {

    /** The day. */
    DAY,

    /** The week. */
    WEEK,

    /** The month. */
    MONTH,

    /** The quarter. */
    QUARTER,

    /** The half year. */
    HALF_YEAR,

    /** The year. */
    YEAR;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }
}
