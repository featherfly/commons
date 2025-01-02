
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-12-28 18:12:28
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.operator;

/**
 * DateFunctions.
 *
 * @author zhongj
 */
public enum DateFunction implements Function {

    /** The get year. */
    GET_YEAR(1),

    /** The get month. */
    GET_MONTH(1),

    /** The get day of month. */
    GET_DAY_OF_MONTH(1),

    /** The get hour. */
    GET_HOUR(1),

    /** The get minute. */
    GET_MINUTE(1),

    /** The get second. */
    GET_SECOND(1),

    /** The weekday. */
    GET_WEEKDAY(1),

    /** The get week of year. */
    GET_WEEK_OF_YEAR(1),

    /** The get day of year. */
    GET_DAY_OF_YEAR(1),

    /** The get quarter. */
    GET_QUARTER(1),

    /** The format. */
    DATE_FORMAT(2),

    /** The time format. */
    TIME_FORMAT(2);

    private int parameterCount = 1;

    /**
     * Instantiates a new date functions.
     *
     * @param parameterCount the parameter count
     */
    DateFunction(int parameterCount) {
        this.parameterCount = parameterCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getParameterCount() {
        return parameterCount;
    }

}
