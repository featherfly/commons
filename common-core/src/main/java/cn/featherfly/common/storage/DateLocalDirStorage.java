
package cn.featherfly.common.storage;

import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * 日期格式目录存储
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @param <E> 存储的对象类型
 * @author zhongj
 */
public abstract class DateLocalDirStorage<E> extends LocalDirStorage<E> {

    /**
     */
    public DateLocalDirStorage() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getExtDir() {
        String result = "";
        if (withYear) {
            result = UriUtils.linkUri(Dates.getCurrentYear() + "");
        }
        if (withMonth) {
            result = UriUtils.linkUri(result, Dates.getCurrentMonth() + "");
        }
        if (withDay) {
            result = UriUtils.linkUri(result, Dates.getCurrentDayOfMonth() + "");
        }
        return result;
    }

    // ********************************************************************
    //
    // ********************************************************************

    private boolean withYear = true;

    private boolean withMonth = true;

    private boolean withDay = true;

    /**
     * 返回withYear
     * 
     * @return withYear
     */
    public boolean isWithYear() {
        return withYear;
    }

    /**
     * 设置withYear
     * 
     * @param withYear withYear
     */
    public void setWithYear(boolean withYear) {
        this.withYear = withYear;
    }

    /**
     * 返回withMonth
     * 
     * @return withMonth
     */
    public boolean isWithMonth() {
        return withMonth;
    }

    /**
     * 设置withMonth
     * 
     * @param withMonth withMonth
     */
    public void setWithMonth(boolean withMonth) {
        this.withMonth = withMonth;
    }

    /**
     * 返回withDay
     * 
     * @return withDay
     */
    public boolean isWithDay() {
        return withDay;
    }

    /**
     * 设置withDay
     * 
     * @param withDay withDay
     */
    public void setWithDay(boolean withDay) {
        this.withDay = withDay;
    }
}
