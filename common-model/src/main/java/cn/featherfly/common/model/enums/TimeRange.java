package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * TimeRange.
 *
 * @author zhongj
 */
public enum TimeRange implements Property<Integer> {

    DAYS,

    WEEKS,

    MONTHS,

    QUARTER,

    YEAR;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }
}
