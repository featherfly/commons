
package cn.featherfly.common.lang.date;

/**
 * <p>
 * Week
 * </p>
 *
 * @author zhongj
 */
public enum Week {

    /**
     * 星期一.
     */
    MONDAY(1),

    /**
     * 星期二.
     */
    TUESDAY(2),

    /**
     * 星期三.
     */
    WEDNESDAY(3),

    /**
     * 星期四.
     */
    THURSDAY(4),

    /**
     * 星期五.
     */
    FRIDAY(5),
    /**
     * 星期六.
     */
    SATURDAY(6),
    /**
     * 星期日.
     */
    SUNDAY(7);

    private Week(int value) {
        this.value = value;
    }

    private int value;

    /**
     * 返回value
     *
     * @return value
     */
    public int getValue() {
        return value;
    }
}
