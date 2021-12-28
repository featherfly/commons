
package cn.featherfly.common.model.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Platforms.
 *
 * @author zhongj
 */
public enum Platforms implements Platform {

    /** The mobile. */
    MOBILE(1, null),

    /** The mobile html5 */
    MOBILE_H5(100, MOBILE),

    /** The android. */
    ANDROID(101, MOBILE),

    /** The ios. */
    IOS(102, MOBILE),

    /** The windows phone. */
    WINDOWS_PHONE(103, MOBILE),

    /** The pc. */
    PC(2, null),

    /** The pc html5 */
    PC_H5(200, MOBILE),

    /** The windows. */
    WINDOWS(201, PC),

    /** The linux. */
    LINUX(202, PC),

    /** The macos. */
    MACOS(203, PC),

    /** The freebsd. */
    FREEBSD(204, PC),

    /** The rtos. */
    RTOS(3, null),

    /** The free rtos. */
    FREE_RTOS(300, RTOS),

    /** The rt thread. */
    RT_THREAD(301, RTOS),

    /** The alios things. */
    ALIOS_THINGS(302, RTOS),

    /** The huawei liteos. */
    HUAWEI_LITEOS(303, RTOS),

    /** The tencentos tiny. */
    TENCENTOS_TINY(304, RTOS),

    /** The uclinux. */
    UCLINUX(305, RTOS),

    /** The ucosii. */
    UCOSII(306, RTOS),

    /** The ecos. */
    ECOS(307, RTOS),

    /** The mbedos. */
    MBEDOS(308, RTOS),

    /** The rtx. */
    RTX(309, RTOS),

    /** The vxworks. */
    VXWORKS(310, RTOS),

    /** The qnx. */
    QNX(311, RTOS),

    /** The nuttx. */
    NUTTX(312, RTOS),

    /** The sylixos. */
    SYLIXOS(313, RTOS),

    /** The tock. */
    TOCK(314, RTOS),

    /** The embedded. */
    EMBEDDED(4, null),

    /** The arduino. */
    ARDUINO(400, EMBEDDED),

    /** The rust halt. */
    RUST_HALT(401, EMBEDDED),

    /** The stm32 cube. */
    STM32_CUBE(402, EMBEDDED),

    /** The esp valuef. */
    ESP_IDF(403, EMBEDDED),

    /** The rm cmsis. */
    ARM_CMSIS(404, EMBEDDED);

    private int value;

    private Platforms group;

    private List<Platform> platforms = new ArrayList<>();

    /**
     * Instantiates a new platforms.
     *
     * @param value the value
     * @param group the group
     */
    Platforms(int value, Platforms group) {
        this.value = value;
        this.group = group;
        if (this.group != null) {
            this.group.platforms.add(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return value;
    }

    /**
     * Checks if is group.
     *
     * @return true, if is group
     */
    public boolean isGroup() {
        return group == null;
    }

    /**
     * get group value.
     *
     * @return parent
     */
    public Platforms group() {
        return group;
    }

    /**
     * get platforms value.
     *
     * @return platforms
     */
    public List<Platform> platforms() {
        return new ArrayList<>(platforms);
    }

    /**
     * value of value.
     *
     * @param value the value
     * @return the platforms
     */
    public static Platforms valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of value.
     *
     * @param value the value
     * @return the platforms
     */
    public static Platforms valueOf(int value) {
        for (Platforms p : Platforms.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
