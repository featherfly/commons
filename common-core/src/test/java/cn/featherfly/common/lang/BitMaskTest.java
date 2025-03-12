
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-13 03:06:13
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigInteger;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.BitMask.Bit;

/**
 * BitsTest.
 *
 * @author zhongj
 */
public class BitMaskTest {

    @Test
    void byteBit() {
        Bit<Byte> bit = BitMask.valueOf((byte) 0);
        assertEquals(bit.get().byteValue(), 0);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(7), false);

        bit.set((byte) 1);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(7), false);

        bit.set((byte) 2);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(7), false);

        bit.set((byte) 126);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(7), false);

        bit.set((byte) (Byte.MAX_VALUE - 1));
        show(bit);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(6), true);
        assertEquals(bit.get(7), false);

        bit.set(Byte.MAX_VALUE);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(7), false);

        bit.set(Byte.MIN_VALUE);
        show(bit);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(7), true);

        bit.set((byte) 0);
        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
        }
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(7), true);
        assertEquals(bit.get().byteValue(), -1);
        assertEquals(Byte.toUnsignedInt(bit.get()), 255);

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, false);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(!bit.get(i));
        }
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(7), false);
        assertEquals(bit.get().byteValue(), 0);

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
            bit.toggle(i);
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
        }
    }

    @Test
    void byteBit2() {
        byte mask = 0;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 7), false);

        mask = 1;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 7), false);

        mask = 2;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 7), false);

        mask = Byte.MAX_VALUE - 1;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 6), true);
        assertEquals(BitMask.get(mask, 7), false);

        mask = Byte.MAX_VALUE;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 6), true);
        assertEquals(BitMask.get(mask, 7), false);

        mask = Byte.MIN_VALUE;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 7), true);

        mask = 0;
        for (int i = 0; i < Byte.SIZE; i++) {
            mask = BitMask.set(mask, i, true);
        }
        Console.log("mask: {}", mask);
        for (int i = 0; i < Byte.SIZE; i++) {
            assertTrue(BitMask.get(mask, i));
        }
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 7), true);
        assertEquals(mask, -1);
        assertEquals(Byte.toUnsignedInt(mask), 255);

        for (int i = 0; i < Byte.SIZE; i++) {
            mask = BitMask.set(mask, i, false);
        }
        for (int i = 0; i < Byte.SIZE; i++) {
            assertTrue(!BitMask.get(mask, i));
        }
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 7), false);
        assertEquals(mask, 0);

        for (int i = 0; i < Byte.SIZE; i++) {
            mask = BitMask.set(mask, i, true);
        }
        for (int i = 0; i < Byte.SIZE; i++) {
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
        }
    }

    @Test
    void intBit() {
        Bit<Integer> bit = BitMask.valueOf(0);
        assertEquals(bit.get().byteValue(), 0);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(31), false);

        bit.set(1);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(31), false);

        bit.set(2);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(31), false);

        bit.set(Integer.MAX_VALUE - 1);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(30), true);
        assertEquals(bit.get(31), false);

        bit.set(Integer.MAX_VALUE);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(30), true);
        assertEquals(bit.get(31), false);

        bit.set(Integer.MIN_VALUE);
        show(bit);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(31), true);

        bit.set(0);
        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
        }
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(30), true);
        assertEquals(bit.get(31), true);
        assertEquals(bit.get().byteValue(), -1);
        assertEquals(Integer.toUnsignedLong(bit.get()), Integer.MAX_VALUE - (long) Integer.MIN_VALUE);

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, false);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(!bit.get(i));
        }

        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(31), false);
        assertEquals(bit.get().byteValue(), 0);

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
            bit.toggle(i);
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
        }
    }

    @Test
    void intBit2() {
        final int length = Integer.SIZE;
        int mask = 0;
        assertEquals(mask, 0);
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, 31), false);

        mask = 1;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, 31), false);

        mask = 2;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, 31), false);

        mask = Integer.MAX_VALUE - 1;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 30), true);
        assertEquals(BitMask.get(mask, 31), false);

        mask = Integer.MAX_VALUE;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 30), true);
        assertEquals(BitMask.get(mask, 31), false);

        mask = Integer.MIN_VALUE;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, 31), true);

        mask = 0;
        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, true);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(BitMask.get(mask, i));
        }
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, 30), true);
        assertEquals(BitMask.get(mask, 31), true);
        assertEquals(mask, -1);
        assertEquals(Integer.toUnsignedLong(mask), Integer.MAX_VALUE - (long) Integer.MIN_VALUE);

        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, false);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(!BitMask.get(mask, i));
        }

        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, 31), false);
        assertEquals(mask, 0);

        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, true);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
        }
    }

    @Test
    void longBit() {
        Bit<Long> bit = BitMask.valueOf(0L);
        assertEquals(bit.get().byteValue(), 0);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(bit.getLength() - 2), false);
        assertEquals(bit.get(bit.getLength() - 1), false);

        bit.set(1L);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(30), false);
        assertEquals(bit.get(bit.getLength() - 1), false);

        bit.set(2L);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(bit.getLength() - 2), false);
        assertEquals(bit.get(bit.getLength() - 1), false);

        bit.set(Long.MAX_VALUE - 1);
        show(bit);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(bit.getLength() - 2), true);
        assertEquals(bit.get(bit.getLength() - 1), false);

        bit.set(Long.MAX_VALUE);
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(bit.getLength() - 2), true);
        assertEquals(bit.get(bit.getLength() - 1), false);

        bit.set(Long.MIN_VALUE);
        show(bit);
        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(bit.getLength() - 2), false);
        assertEquals(bit.get(bit.getLength() - 1), true);

        bit.set(0L);
        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
        }
        assertEquals(bit.get(0), true);
        assertEquals(bit.get(1), true);
        assertEquals(bit.get(2), true);
        assertEquals(bit.get(bit.getLength() - 2), true);
        assertEquals(bit.get(bit.getLength() - 1), true);
        assertEquals(bit.get().byteValue(), -1);
        assertEquals(Long.toUnsignedString(bit.get()),
            BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.valueOf(Long.MIN_VALUE).abs()).toString());

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, false);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(!bit.get(i));
        }

        assertEquals(bit.get(0), false);
        assertEquals(bit.get(1), false);
        assertEquals(bit.get(2), false);
        assertEquals(bit.get(bit.getLength() - 2), false);
        assertEquals(bit.get(bit.getLength() - 1), false);
        assertEquals(bit.get().byteValue(), 0);

        for (int i = 0; i < bit.getLength(); i++) {
            bit.set(i, true);
        }
        for (int i = 0; i < bit.getLength(); i++) {
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
            bit.toggle(i);
            assertTrue(bit.get(i));
            bit.toggle(i);
            assertTrue(!bit.get(i));
        }
    }

    @Test
    void longBit2() {
        final int length = Long.SIZE;
        long mask = 0L;
        assertEquals(mask, 0);
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, length - 2), false);
        assertEquals(BitMask.get(mask, length - 1), false);

        mask = 1L;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, 30), false);
        assertEquals(BitMask.get(mask, length - 1), false);

        mask = 2L;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, length - 2), false);
        assertEquals(BitMask.get(mask, length - 1), false);

        mask = Long.MAX_VALUE - 1;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, length - 2), true);
        assertEquals(BitMask.get(mask, length - 1), false);

        mask = Long.MAX_VALUE;
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, length - 2), true);
        assertEquals(BitMask.get(mask, length - 1), false);

        mask = Long.MIN_VALUE;
        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, length - 2), false);
        assertEquals(BitMask.get(mask, length - 1), true);

        mask = 0L;
        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, true);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(BitMask.get(mask, i));
        }
        assertEquals(BitMask.get(mask, 0), true);
        assertEquals(BitMask.get(mask, 1), true);
        assertEquals(BitMask.get(mask, 2), true);
        assertEquals(BitMask.get(mask, length - 2), true);
        assertEquals(BitMask.get(mask, length - 1), true);
        assertEquals(mask, -1);
        assertEquals(Long.toUnsignedString(mask),
            BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.valueOf(Long.MIN_VALUE).abs()).toString());

        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, false);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(!BitMask.get(mask, i));
        }

        assertEquals(BitMask.get(mask, 0), false);
        assertEquals(BitMask.get(mask, 1), false);
        assertEquals(BitMask.get(mask, 2), false);
        assertEquals(BitMask.get(mask, length - 2), false);
        assertEquals(BitMask.get(mask, length - 1), false);
        assertEquals(mask, 0);

        for (int i = 0; i < length; i++) {
            mask = BitMask.set(mask, i, true);
        }
        for (int i = 0; i < length; i++) {
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(BitMask.get(mask, i));
            mask = BitMask.toggle(mask, i);
            assertTrue(!BitMask.get(mask, i));
        }
    }

    /**
     * Show.
     *
     * @param bit the bit
     * @param length the length
     */
    static void show(Bit<?> bit) {
        int length = bit.getLength();
        Console.log("bit value: {}, length: {}", bit.get(), bit.getLength());
        Console.log("get {} : {}", 0, bit.get(0));
        Console.log("get {} : {}", 1, bit.get(1));
        Console.log("get {} : {}", 2, bit.get(2));
        Console.log("get {} : {}", length - 2, bit.get(length - 2));
        Console.log("get {} : {}", length - 1, bit.get(length - 1));
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Bit<Byte> bit = BitMask.valueOf((byte) 0);
        show(bit);

        bit = BitMask.valueOf((byte) 1);
        show(bit);

        bit = BitMask.valueOf((byte) 2);
        show(bit);

        bit = BitMask.valueOf((byte) 126);
        show(bit);

        bit = BitMask.valueOf(Byte.MAX_VALUE);
        show(bit);

        bit = BitMask.valueOf(Byte.MIN_VALUE);
        show(bit);

        bit = BitMask.valueOf((byte) 0);
        for (int i = 0; i < 8; i++) {
            bit.set(i, true);
        }
        show(bit);
        System.out.println(Byte.toUnsignedInt(bit.get()));
        System.out.println(Integer.valueOf(Byte.toUnsignedInt(bit.get())).byteValue());
    }
}
