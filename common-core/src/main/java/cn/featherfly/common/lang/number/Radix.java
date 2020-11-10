
package cn.featherfly.common.lang.number;

/**
 * <p>
 * Radixs
 * </p>
 * .
 *
 * @author zhongj
 */
public enum Radix {

    /** The radix2. */
    RADIX2(2),

    /** The radix8. */
    RADIX8(8),

    /** The radix10. */
    RADIX10(10),

    /** The radix16. */
    RADIX16(16),

    /** The radix10. */
    RADIX36(36),

    /** The radix62. */
    RADIX62(62),

    /** The radix64. */
    RADIX64(64),

    /** The radix128. */
    RADIX128(128);

    /** The value. */
    private int value;

    /**
     * Instantiates a new radixs.
     *
     * @param value the value
     */
    private Radix(int value) {
        this.value = value;
    }

    /**
     * Value.
     *
     * @return the int
     */
    public int value() {
        return value;
    }

}
