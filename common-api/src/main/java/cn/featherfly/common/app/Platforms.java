
package cn.featherfly.common.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Platforms.
 *
 * @author zhongj
 */
public enum Platforms implements Platform {

    /** The mobile. */
    MOBILE(-1, null),

    /** The android. */
    ANDROID(0, MOBILE),

    /** The ios. */
    IOS(1, MOBILE),

    /** The windows phone. */
    WINDOWS_PHONE(2, MOBILE),

    /** The pc. */
    PC(-2, null),

    /** The windows. */
    WINDOWS(20, PC),

    /** The linux. */
    LINUX(21, PC),

    /** The macos. */
    MACOS(22, PC),

    /** The freebsd. */
    FREEBSD(23, PC),

    /** The rtos. */
    RTOS(-3, null),

    /** The free rtos. */
    FREE_RTOS(40, RTOS),

    /** The rt thread. */
    RT_THREAD(41, RTOS),

    /** The alios things. */
    ALIOS_THINGS(42, RTOS),

    /** The huawei liteos. */
    HUAWEI_LITEOS(43, RTOS),

    /** The tencentos tiny. */
    TENCENTOS_TINY(44, RTOS),

    /** The uclinux. */
    UCLINUX(45, RTOS),

    /** The ucosii. */
    UCOSII(46, RTOS),

    /** The ecos. */
    ECOS(47, RTOS),

    /** The mbedos. */
    MBEDOS(48, RTOS),

    /** The rtx. */
    RTX(49, RTOS),

    /** The vxworks. */
    VXWORKS(50, RTOS),

    /** The qnx. */
    QNX(51, RTOS),

    /** The nuttx. */
    NUTTX(52, RTOS),

    /** The sylixos. */
    SYLIXOS(53, RTOS),

    /** The tock. */
    TOCK(54, RTOS),

    /** The embedded. */
    EMBEDDED(-4, null),

    /** The arduino. */
    ARDUINO(60, EMBEDDED),

    /** The rust halt. */
    RUST_HALT(61, EMBEDDED),

    /** The stm32 cube. */
    STM32_CUBE(62, EMBEDDED),

    /** The esp iot. */
    ESP_IOT(63, EMBEDDED);

    private int id;

    private Platforms group;

    private List<Platform> platforms = new ArrayList<>();

    /**
     * Instantiates a new platforms.
     *
     * @param id    the id
     * @param group the group
     */
    Platforms(int id, Platforms group) {
        this.id = id;
        this.group = group;
        if (this.group != null) {
            this.group.platforms.add(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int id() {
        return id;
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
     * value of id.
     *
     * @param id the id
     * @return the platforms
     */
    public static Platforms valueOf(Integer id) {
        if (id == null) {
            return null;
        }
        return valueOf(id.intValue());
    }

    /**
     * value of id.
     *
     * @param id the id
     * @return the platforms
     */
    public static Platforms valueOf(int id) {
        for (Platforms p : Platforms.values()) {
            if (p.id() == id) {
                return p;
            }
        }
        return null;
    }
}
