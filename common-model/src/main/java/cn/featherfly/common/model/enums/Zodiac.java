package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * Zodiac.
 * <p>
 * 十二星座.
 * </p>
 *
 * @author zhongj
 */
public enum Zodiac implements Property<Integer> {
    /**
     * 白羊座
     */
    ARIES,
    /**
     * 金牛座
     */
    TAURUS,
    /**
     * 双子座
     */
    GEMINI,
    /**
     * 巨蟹座
     */
    CACER,
    /**
     * 狮子座
     */
    LEO,
    /**
     * 处女座
     */
    VIRGO,
    /**
     * 天秤座
     */
    LIBRA,
    /**
     * 天蝎座
     */
    SCORPIO,
    /**
     * 射手座
     */
    SAGITTARIUS,
    /**
     * 摩羯座
     */

    CAPRICOM,
    /**
     * 水瓶座
     */
    AQUARIUS,
    /**
     * 双鱼座
     */
    PISCES;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }

    /**
     * value of Gender.
     *
     * @param value the value
     * @return the platforms
     */
    public static Zodiac valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of Gender.
     *
     * @param value the value
     * @return the platforms
     */
    public static Zodiac valueOf(int value) {
        for (Zodiac p : Zodiac.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
