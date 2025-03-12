
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-13 02:18:13
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * Bits.
 *
 * @author zhongj
 */
public final class BitMask {

    /**
     * Instantiates a new bits.
     */
    private BitMask() {
        super();
    }

    /**
     * Sets the.
     *
     * @param mask the mask
     * @param index the index
     * @param state the state
     * @return the byte
     */
    public static byte set(byte mask, int index, boolean state) {
        AssertIllegalArgument.isInRange(index, 0, Byte.SIZE, "index");
        if (state) {
            return mask |= 1 << index;
        } else {
            return mask &= ~(1 << index);
        }
    }

    /**
     * Sets the.
     *
     * @param mask the mask
     * @param index the index
     * @param state the state
     * @return the short
     */
    public static short set(short mask, int index, boolean state) {
        AssertIllegalArgument.isInRange(index, 0, Short.SIZE, "index");
        if (state) {
            return mask |= 1 << index;
        } else {
            return mask &= ~(1 << index);
        }
    }

    /**
     * Sets the.
     *
     * @param mask the mask
     * @param index the index
     * @param state the state
     * @return the int
     */
    public static int set(int mask, int index, boolean state) {
        AssertIllegalArgument.isInRange(index, 0, Integer.SIZE, "index");
        if (state) {
            return mask |= 1 << index;
        } else {
            return mask &= ~(1 << index);
        }
    }

    /**
     * Sets the.
     *
     * @param mask the mask
     * @param index the index
     * @param state the state
     * @return the long
     */
    public static long set(long mask, int index, boolean state) {
        AssertIllegalArgument.isInRange(index, 0, Long.SIZE, "index");
        if (state) {
            return mask |= 1L << index;
        } else {
            return mask &= ~(1L << index);
        }
    }

    /**
     * Toggle.
     *
     * @param mask the mask
     * @param index the index
     * @return the byte
     */
    public static byte toggle(byte mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Byte.SIZE, "index");
        return mask ^= 1 << index;
    }

    /**
     * Toggle.
     *
     * @param mask the mask
     * @param index the index
     * @return the short
     */
    public static short toggle(short mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Short.SIZE, "index");
        return mask ^= 1 << index;
    }

    /**
     * Toggle.
     *
     * @param mask the mask
     * @param index the index
     * @return the int
     */
    public static int toggle(int mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Integer.SIZE, "index");
        return mask ^= 1 << index;
    }

    /**
     * Toggle.
     *
     * @param mask the mask
     * @param index the index
     * @return the long
     */
    public static long toggle(long mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Long.SIZE, "index");
        return mask ^= 1L << index;
    }

    /**
     * Gets the.
     *
     * @param mask the mask
     * @param index the index
     * @return true, if successful
     */
    public static boolean get(byte mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Byte.SIZE, "index");
        return !((mask & 1 << index) == 0);
    }

    /**
     * Gets the.
     *
     * @param mask the mask
     * @param index the index
     * @return true, if successful
     */
    public static boolean get(short mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Short.SIZE, "index");
        return !((mask & 1 << index) == 0);
    }

    /**
     * Gets the.
     *
     * @param mask the mask
     * @param index the index
     * @return true, if successful
     */
    public static boolean get(int mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Integer.SIZE, "index");
        return !((mask & 1 << index) == 0);
    }

    /**
     * Gets the.
     *
     * @param mask the mask
     * @param index the index
     * @return true, if successful
     */
    public static boolean get(long mask, int index) {
        AssertIllegalArgument.isInRange(index, 0, Long.SIZE, "index");
        return !((mask & 1L << index) == 0);
    }

    /**
     * Value of.
     *
     * @param mask the mask
     * @return the bits
     */
    public static Bit<Byte> valueOf(byte mask) {
        return new ByteBit(mask);
    }

    /**
     * Value of.
     *
     * @param mask the mask
     * @return the bits
     */
    public static Bit<Short> valueOf(short mask) {
        return new ShortBit(mask);
    }

    /**
     * Value of.
     *
     * @param mask the mask
     * @return the bits
     */
    public static Bit<Integer> valueOf(int mask) {
        return new IntBit(mask);
    }

    /**
     * Value of.
     *
     * @param mask the mask
     * @return the bits
     */
    public static Bit<Long> valueOf(long mask) {
        return new LongBit(mask);
    }

    /**
     * The Interface Bit.
     *
     * @param <V> the value type
     */
    public interface Bit<V extends Number> {

        /**
         * Sets the.
         *
         * @param index the index
         * @param state the state
         */
        void set(int index, boolean state);

        /**
         * toggle bit, and return the new state.
         *
         * @param index the index
         * @return true, if successful
         */
        void toggle(int index);

        /**
         * Gets the.
         *
         * @param index the index
         * @return true, if successful
         */
        boolean get(int index);

        /**
         * Gets the length.
         *
         * @return the length
         */
        int getLength();

        /**
         * Gets the value.
         *
         * @param value the value
         * @return the value
         */
        Bit<V> set(V value);

        /**
         * Gets the value.
         *
         * @return the value
         */
        V get();
    }

    private static abstract class AbstractBit<V extends Number> implements Bit<V> {

        private final int length;

        protected AbstractBit(int length) {
            this.length = length;
        }

        @Override
        public int getLength() {
            return length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(int index, boolean state) {
            AssertIllegalArgument.isInRange(index, 0, length - 1, "index");
            doSet(index, state);
        }

        protected abstract void doSet(int index, boolean state);

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean get(int index) {
            AssertIllegalArgument.isInRange(index, 0, length - 1, "index");
            return doGet(index);
        }

        protected abstract boolean doGet(int index);

        @Override
        public void toggle(int index) {
            AssertIllegalArgument.isInRange(index, 0, length - 1, "index");
            doToggle(index);
        }

        protected abstract void doToggle(int index);
    }

    private static class ByteBit extends AbstractBit<Byte> {

        private byte mask;

        public ByteBit(byte mask) {
            super(8);
            this.mask = mask;
        }

        @Override
        public void doSet(int index, boolean state) {
            if (state) {
                mask |= 1 << index;
            } else {
                mask &= ~(1 << index);
            }
        }

        @Override
        public void doToggle(int index) {
            mask ^= 1 << index;
        }

        @Override
        public boolean doGet(int index) {
            return !((mask & 1 << index) == 0);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Byte get() {
            return mask;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Bit<Byte> set(Byte value) {
            mask = value;
            return this;
        }
    }

    private static class ShortBit extends AbstractBit<Short> {

        private short mask;

        public ShortBit(short mask) {
            super(16);
            this.mask = mask;
        }

        @Override
        public void doSet(int index, boolean state) {
            if (state) {
                mask = mask |= 1 << index;
            } else {
                mask = mask &= ~(1 << index);
            }
        }

        @Override
        public boolean doGet(int index) {
            return !((mask & 1 << index) == 0);
        }

        @Override
        public void doToggle(int index) {
            mask ^= 1 << index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Short get() {
            return mask;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Bit<Short> set(Short value) {
            mask = value;
            return this;
        }
    }

    private static class IntBit extends AbstractBit<Integer> {

        private int mask;

        public IntBit(int mask) {
            super(32);
            this.mask = mask;
        }

        @Override
        public void doSet(int index, boolean state) {
            if (state) {
                mask = mask |= 1 << index;
            } else {
                mask = mask &= ~(1 << index);
            }
        }

        @Override
        public boolean doGet(int index) {
            return !((mask & 1 << index) == 0);
        }

        @Override
        public void doToggle(int index) {
            mask ^= 1 << index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Integer get() {
            return mask;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Bit<Integer> set(Integer value) {
            mask = value;
            return this;
        }
    }

    private static class LongBit extends AbstractBit<Long> {

        private long mask;

        public LongBit(long mask) {
            super(64);
            this.mask = mask;
        }

        @Override
        public void doSet(int index, boolean state) {
            if (state) {
                mask = mask |= 1L << index;
            } else {
                mask = mask &= ~(1L << index);
            }
        }

        @Override
        public boolean doGet(int index) {
            return !((mask & 1L << index) == 0);
        }

        @Override
        public void doToggle(int index) {
            mask ^= 1L << index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Long get() {
            return mask;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Bit<Long> set(Long value) {
            mask = value;
            return this;
        }
    }
}