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

    /**
     * value of TimeRange.
     *
     * @param value the value
     * @return the TimeRange
     */
    public static TimeRange valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of TimeRange.
     *
     * @param value the value
     * @return the TimeRange
     */
    public static TimeRange valueOf(int value) {
        for (TimeRange p : TimeRange.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
